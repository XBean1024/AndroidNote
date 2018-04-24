package com.example.handlermessage;

import android.icu.util.Measure;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private Handler mHandler2 = new MyHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Message message = Message.obtain();
        message.what = 452;
        mHandler2.sendMessage(message);
        Log.i("TAG", "onCreate: ");
        mHandler.post(()-> Log.i("mHandler", "mHandler: "));
        mHandler2.post(()-> mHandler2.sendEmptyMessage(12563));
    }
    static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("mHandler2", "handleMessage:  "+msg.what);
        }
    }
}
