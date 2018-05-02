package com.example.scrolllayout.scrollview;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class UleScrollLayoutChild extends ScrollView implements NestedScrollingChild {
    private String Tag = "UleNestedScrollChild";
    private NestedScrollingChildHelper mScrollingChildHelper;


    public UleScrollLayoutChild(Context context) {
        super(context);
        init(context);
    }

    public UleScrollLayoutChild(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UleScrollLayoutChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        final ViewConfiguration configuration = ViewConfiguration.get(context);

    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        Log.i(Tag, "setNestedScrollingEnabled:" + enabled);
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        Log.i(Tag, "isNestedScrollingEnabled");
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    /**
     *  开启嵌套滚动流程(实际上是进行了一些嵌套滚动前准备工作)。
     *  当找到了能够配合当前子view进行嵌套滚动的父view时，返回值为true
     */
    @Override
    public boolean startNestedScroll(int axes) {
        boolean bl = getScrollingChildHelper().startNestedScroll(axes);
        return bl;
    }

    @Override
    public void stopNestedScroll() {
        Log.i(Tag, "stopNestedScroll");
        getScrollingChildHelper().stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        Log.i(Tag, "hasNestedScrollingParent");
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        Log.i(Tag, "dispatchNestedScroll:dxConsumed:" + dxConsumed + "," +
                "dyConsumed:" + dyConsumed + ",dxUnconsumed:" + dxUnconsumed + ",dyUnconsumed:" +
                dyUnconsumed + ",offsetInWindow:" + offsetInWindow);
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.i(Tag, "dispatchNestedPreScroll:dx" + dx + ",dy:" + dy + ",consumed:" + consumed + ",offsetInWindow:" + offsetInWindow);
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.i(Tag, "dispatchNestedFling:velocityX:" + velocityX + ",velocityY:" + velocityY + ",consumed:" + consumed);
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.i(Tag, "dispatchNestedPreFling:velocityX:" + velocityX + ",velocityY:" + velocityY);
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (mScrollingChildHelper == null) {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
            mScrollingChildHelper.setNestedScrollingEnabled(true);
        }
        return mScrollingChildHelper;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight()+((UleScrollLayoutParent)getParent()).getTopViewHeight());
    }
}