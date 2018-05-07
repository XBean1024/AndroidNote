package com.example.scrolllayout.scrollview;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.scrolllayout.Logger;

public class UleNestedScrollLayoutParent extends LinearLayout implements NestedScrollingParent {
    private View mHeaderView;
    private View mFooterView;
    private View mSuspensionView;
    private NestedScrollingParentHelper mParentHelper;
    private int mHeaderViewHeight;
    private int mFooterViewHeight;
    private int mContentViewHeight;
    private int mDownY;
    private int mUpY;


    public UleNestedScrollLayoutParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UleNestedScrollLayoutParent(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = getChildAt(0);//要隐藏的view
        mSuspensionView = getChildAt(1);//悬浮的view
        mFooterView = getChildAt(getChildCount() - 1);
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mHeaderViewHeight <= 0) {
                    mHeaderViewHeight = mHeaderView.getMeasuredHeight();
                }
            }
        });
        mFooterView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mFooterViewHeight <= 0) {
                    mFooterViewHeight = mHeaderView.getMeasuredHeight();
                }
            }
        });
        mSuspensionView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mContentViewHeight <= 0) {
                    mContentViewHeight = mSuspensionView.getMeasuredHeight();
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
//        height += mFooterView.getMeasuredHeight();
        Logger.logInfo("mFooterView.getMeasuredHeight() = " + mFooterView.getMeasuredHeight());
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    public int getHeaderViewHeight() {
        return mHeaderView.getMeasuredHeight();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    /**
     * @param target
     * @param dx       父view 在x方向上滚动的相对距离
     * @param dy       父view 在y方向上滚动的相对距离
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

        if (showHeader(dy) || hideHeader(dy)) {//如果父亲自己要滑动，则拦截
            consumed[1] = dy;
            scrollBy(0, dy);
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
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
    public boolean showHeader(int dy) {
        if (dy < 0) {
            if (getScrollY() > 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 上拉的时候，是否要向上滑动，隐藏图片
     */
    public boolean hideHeader(int dy) {
        if (dy > 0) {
            if (getScrollY() < mHeaderViewHeight) {//如果parent外框，还可以往下滑动
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_DOWN || super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) (event.getRawY() + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) (event.getRawY() + 0.5f);
                int dy = mDownY - y;
                mDownY = y;
                if (showHeader(dy) || hideHeader(dy)) {//如果父亲自己要滑动
                    scrollBy(0, dy);
                }
                break;
            case MotionEvent.ACTION_UP:
                mUpY = (int) (event.getRawY() + 0.5f);
                if (mUpY > mDownY) {//向上
                    scrollBy(0, mHeaderViewHeight);
                } else if (mUpY < mDownY) {
                    scrollBy(0, -mHeaderViewHeight);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

}