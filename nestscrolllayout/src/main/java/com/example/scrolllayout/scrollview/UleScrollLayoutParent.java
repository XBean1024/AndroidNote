package com.example.scrolllayout.scrollview;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

public class UleScrollLayoutParent extends LinearLayout implements NestedScrollingParent {
    private String Tag = "UleNestedScrollParent";
    private View mHeaderView;
    private View mContentView;
    private UleScrollLayoutChild mScrollChild;
    private NestedScrollingParentHelper mParentHelper;
    private int mHeaderViewHeight;
    private int mContentViewHeight;
    private int mLastTouchY;
    public boolean topShow;

    public UleScrollLayoutParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UleScrollLayoutParent(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = getChildAt(0);
        mContentView = getChildAt(1);
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mHeaderViewHeight <= 0) {
                    mHeaderViewHeight = mHeaderView.getMeasuredHeight();
                    Log.i(Tag, "mHeaderViewHeight:" + mHeaderViewHeight + ",mContentViewHeight:" + mContentViewHeight);
                }
            }
        });
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mContentViewHeight <= 0) {
                    mContentViewHeight = mContentView.getMeasuredHeight();
                    Log.i(Tag, "mHeaderViewHeight:" + mHeaderViewHeight + ",mContentViewHeight:" + mContentViewHeight);
                }
            }
        });


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int height = 0;
        for (int i = 0; i < count; i++) {
            height += getChildAt(i).getMeasuredHeight();
        }
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    public int getTopViewHeight() {
        Log.i(Tag, "getTopViewHeight--" + mHeaderView.getMeasuredHeight());
        return mHeaderView.getMeasuredHeight();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i(Tag, "onStartNestedScroll--" + "child:" + child + ",target:" + target + ",nestedScrollAxes:" + nestedScrollAxes);
        return true;
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);

    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.i(Tag, "onNestedScrollAccepted" + "child:" + child + ",target:" + target + ",nestedScrollAxes:" + nestedScrollAxes);
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.i(Tag, "onStopNestedScroll--target:" + target);
        mParentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(Tag, "onNestedScroll--" + "target:" + target + ",dxConsumed" + dxConsumed + ",dyConsumed:" + dyConsumed
                + ",dxUnconsumed:" + dxUnconsumed + ",dyUnconsumed:" + dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

        if (showImg(dy) || hideImg(dy)) {//如果父亲自己要滑动，则拦截
            consumed[1] = dy;
            scrollBy(0, dy);
            Log.i("onNestedPreScroll", "Parent滑动：" + dy);
        }
        Log.i(Tag, "onNestedPreScroll--getScrollY():" + getScrollY() + ",dx:" + dx + ",dy:" + dy + ",consumed:" + consumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(Tag, "onNestedFling--target:" + target);
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i(Tag, "onNestedPreFling--target:" + target);
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        Log.i(Tag, "getNestedScrollAxes");
        return 0;
    }


    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mHeaderViewHeight) {
            y = mHeaderViewHeight;
        }

        super.scrollTo(x, y);
    }

    /**
     * 下拉的时候是否要向下滑动显示图片
     */
    public boolean showImg(int dy) {
        if (dy < 0) {

//            if(getScrollY()>0&&mScrollChild.getScrollY()==0){//如果parent外框，还可以往上滑动
            if (getScrollY() > 0) {

                return true;
            }
        }


        return false;
    }


    /**
     * 上拉的时候，是否要向上滑动，隐藏图片
     *
     * @return
     */
    public boolean hideImg(int dy) {
        if (dy > 0) {
            if (getScrollY() < mHeaderViewHeight) {//如果parent外框，还可以往下滑动
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        return super.onInterceptTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("aaa", "getY():getRawY:" + event.getRawY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchY = (int) (event.getRawY() + 0.5f);

                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) (event.getRawY() + 0.5f);
                int dy = mLastTouchY - y;
                mLastTouchY = y;
                if (showImg(dy) || hideImg(dy)) {//如果父亲自己要滑动
                    scrollBy(0, dy);
                }
                break;
            case MotionEvent.ACTION_UP:
                int up_y = (int) (event.getRawY() + 0.5f);
                if (up_y > mLastTouchY) {//向上
                    scrollBy(0, mHeaderViewHeight);
                }else if (up_y < mLastTouchY){
                    scrollBy(0, -mHeaderViewHeight);
                }

                break;
        }
        return super.dispatchTouchEvent(event);
    }

}