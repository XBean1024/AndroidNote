package com.binny.scroll;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private String TAG = "sss";
    private float mStartPos;

    private RefreshLayout mRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefreshLayout = findViewById(R.id.refresh);
        final Handler handler = new Handler();
        mRefreshLayout.setOnRefreshListener(new RefreshLayout.onRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "run: 执行刷新操作");
                        try {
                            sleep(2000);
                            mRefreshLayout.finishRefresh();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },3000);
            }
        });
    }
}
