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
    private int mTitleHeight;
    private GestureDetector mGestureDetector;
    private int mScrollY;
    private float y1 = 0;
    private float y2 = 0;
    private Context mContext;
    public SuppressibleTitleLayout(Context context) {
        super(context);
        init(context);
    }

    public SuppressibleTitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
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
        mTitleHeight = getChildAt(0).getMeasuredHeight();
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

        Logger.logInfo(TAG, "mScrollY = " + mScrollY);
        Logger.logInfo(TAG, "distance = " + distance);
        Logger.logInfo(TAG, "mScrollY + distance = " + (mScrollY + distance));
        /*
         *  1 滑动条件
         * distanceY < 0 ：满足下滑条件
         * (scrollY>0)&&(scrollY <= mTitleHeight)：满足滑动条件
         * 2 滑动距离
         *
         * */
        if (distanceY < 0) {
            if (mScrollY + distance >= 0) {
                Logger.logInfo(TAG, "不完全显示");
                scrollBy(0, distance);
            } else {
                Logger.logInfo(TAG, "完全显示");
                scrollBy(0, -mScrollY);
            }

        } else if (distanceY > 0) {
            Logger.logInfo(TAG, "mScrollY = "+mScrollY);
            if (mScrollY + distance < mTitleHeight) {//为完全隐藏
                Logger.logInfo(TAG, "不可以完全隐藏");
                setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getHeight() + mTitleHeight));
                scrollBy(0, distance);
                return false;
            } else {
                Logger.logInfo(TAG, "可以完全隐藏");
                int lastY = mTitleHeight - mScrollY;
                Logger.logInfo(TAG,"lastY"+lastY);
                scrollBy(0, lastY);
                return false;
            }
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
