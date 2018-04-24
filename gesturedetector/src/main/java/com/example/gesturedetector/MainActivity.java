package com.example.gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private GestureDetector mGestureDetector ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this,this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Logger.logInfo("onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Logger.logInfo("onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Logger.logInfo("onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Logger.logInfo("onScroll");

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Logger.logInfo("onLongPress");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Logger.logInfo("onFling");

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
