package com.example.scrolllayout.scrollview;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.ListView;

public class UleNestedScrollLayoutChildListView extends ListView implements NestedScrollingChild {
    private NestedScrollingChildHelper mScrollingChildHelper;
    public UleNestedScrollLayoutChildListView(Context context) {
        super(context);
        init(context);
    }

    public UleNestedScrollLayoutChildListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UleNestedScrollLayoutChildListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        final ViewConfiguration configuration = ViewConfiguration.get(context);
    }

    /*
    * 重写 onMeasure()
    * 因为 滑动的时候 父view 会联动一段header的距离  */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + ((UleNestedScrollLayoutParent) getParent()).getHeaderViewHeight());
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    /**
     * 开启嵌套滚动流程(实际上是进行了一些嵌套滚动前准备工作)。
     * 当找到了能够配合当前子view进行嵌套滚动的父view时，返回值为true
     */
    @Override
    public boolean startNestedScroll(int axes) {
        return getScrollingChildHelper().startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }


    /**
     * 在子view自己进行滚动之前调用此方法，询问父view是否要在子view之前进行滚动。
     * 此方法的前两个参数用于告诉父View此次要滚动的距离；而第三第四个参数用于子view获取父view消费掉的距离和父view位置的偏移量。
     * 第一第二个参数为输入参数，即常规的函数参数，调用函数的时候我们需要为其传递确切的值。
     * 而第三第四个参数为输出参数，调用函数时我们只需要传递容器（在这里就是两个数组），在调用结束后，我们就可以从容器中获取函数输出的值。
     * 如果parent消费了一部分或全部距离，则此方法返回true。
     *
     * @param dx             告诉父View此水平次要滚动的距离
     * @param dy             告诉父View此次要滚动的竖直距离
     * @param consumed       从容器中获取函数输出的值。
     * @param offsetInWindow 从容器中获取函数输出的值。
     * @return 如果parent消费了一部分或全部距离，则此方法返回true。
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    /**
     * 在子view自己进行滚动之后调用此方法，询问父view是否还要进行余下(unconsumed)的滚动。
     * 前四个参数为输入参数，用于告诉父view已经消费和尚未消费的距离，最后一个参数为输出参数，用于子view获取父view位置的偏移量。
     *
     * @param dxConsumed     通知父view 已经消费的水平距离
     * @param dyConsumed     通知父view 已经消费的竖直距离
     * @param dxUnconsumed   通知父view 尚未消费的水平距离
     * @param dyUnconsumed   通知父view 尚未消费的水平距离
     * @param offsetInWindow 用于子view获取父view位置的偏移量
     * @return true if the event was dispatched, false if it could not be dispatched.
     */
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {

        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }
    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (mScrollingChildHelper == null) {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
            mScrollingChildHelper.setNestedScrollingEnabled(true);//开启嵌套滚动
        }
        return mScrollingChildHelper;
    }
}