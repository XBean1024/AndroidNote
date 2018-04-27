package com.binny.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 作者: binny
 * 时间: 2018/4/27
 * 描述:
 */
public class RefreshLayout extends LinearLayout {
    private float mStartPos;
    private final String TAG = getClass().getSimpleName();
    private View textView;
    private boolean mMoveUp;
    private onRefreshListener mRefreshListener;

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartPos = event.getY();//手指按下的位置
                Log.i(TAG, "onTouch: mStartPos = " + mStartPos);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "action: ACTION_MOVE = " + (event.getY() - mStartPos));
                float currentY = event.getY();
                if (currentY  - mStartPos> 200) {
                    ((TextView) textView).setText("正在刷新。。。。");
                    scrollTo(0, -textView.getHeight());//移动到  textView.getHeight() 的位置
                    mRefreshListener.onRefresh();
                    break;
                }
                if (currentY - mStartPos > 0) {
                    mMoveUp = true;
                    Log.i(TAG, "action: ACTION_MOVE" + textView.getHeight());
                    scrollBy(0, (int) -(event.getY() - mStartPos));
                } else {
                    mMoveUp = false;
                    scrollBy(0, (int) -(event.getY() - mStartPos));
                }
                mStartPos = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mStartPos = event.getY();
                if (mMoveUp) {
                    ((TextView) textView).setText("正在刷新。。。。");
                    scrollTo(0, -textView.getHeight());//移动到  textView.getHeight() 的位置
                    mRefreshListener.onRefresh();
                }

                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i(TAG, "onLayout: ScrollTitleView" + (b - t));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = getResources().getDisplayMetrics();

        final float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        textView = LayoutInflater.from(getContext()).inflate(R.layout.header, null);
        addView(textView, 0);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = getResources().getDisplayMetrics();

        final float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        getChildAt(1).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (getHeight() + density * 40)));
        scrollBy(0, (int) (textView.getMeasuredHeight()));
        Log.i(TAG, "onFinishInflate: ScrollTitleView" + getHeight());
        Log.i(TAG, "onFinishInflate: textView " + textView.getMeasuredHeight());
    }

    public void setOnRefreshListener(onRefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    public void finishRefresh() {
        scrollTo(0, textView.getHeight());//移动到  textView.getHeight() 的位置
    }

    public interface onRefreshListener {
        void onRefresh();
    }
}
