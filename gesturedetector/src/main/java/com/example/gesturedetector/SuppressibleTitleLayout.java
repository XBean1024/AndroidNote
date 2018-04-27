package com.example.gesturedetector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 作者: binny
 * 时间: 2018/4/27
 * 描述:
 */
public class SuppressibleTitleLayout extends LinearLayout implements GestureDetector.OnGestureListener {
    private String TAG = "dddddddddd";
    private int mTitleHeight = 240;
    private GestureDetector mGestureDetector;
    private int mScrollY;
    private float y1 = 0;
    private float y2 = 0;

    public SuppressibleTitleLayout(Context context) {
        super(context);
        init(context);
    }

    public SuppressibleTitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, this);
    }

    public SuppressibleTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /*
         * 获取 子VIew的个数
         * */
        Logger.logInfo(" 获取 子VIew的个数 " + getChildCount());

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mScrollY = getScrollY();
        int distance = (int) distanceY;

        /*
         *  1 滑动条件
         * distanceY < 0 ：满足下滑条件
         * (scrollY>0)&&(scrollY <= mTitleHeight)：满足滑动条件
         * 2 滑动距离
         *
         * */
        if (distanceY < 0) {
            if (mScrollY + distance >= 0) {
                scrollBy(0, distance);
            }else {
                Logger.logInfo("mScrollY 00= "+mScrollY);
                scrollBy(0, -mScrollY);
            }

        } else if (distanceY > 0) {
            if (mScrollY + distance <= mTitleHeight) {//为完全隐藏
                setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getHeight() + mTitleHeight));
                scrollBy(0, distance);
                if (mScrollY + distance == mTitleHeight) {
                    return false;
                }
                return true;
            } else {
                int lastY = mTitleHeight - mScrollY;
                scrollBy(0, lastY);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Logger.logInfo("mScrollY = " + mScrollY);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                y2 = event.getY();
                if (y1 - y2 > 0) {
                    // up

                    if (mScrollY < mTitleHeight) {
                        return true;
                    }
                } else if (y2 - y1 > 0) {
                    //down
                    if (mScrollY > 0) {
                        return true;
                    }
                }
                break;

        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
