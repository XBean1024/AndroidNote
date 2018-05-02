package com.example.scrolllayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * 作者: binny
 * 时间: 2018/4/30
 * 描述:
 */
public class MyListView extends ListView {

    private float mStartPosY;
    private float mEndPosY;
    private boolean mToHide;

    private String TAG = "ssss";


    private View mFooter;


    private TextView detail;

    public MyListView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
    }


    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
