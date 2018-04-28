package com.example.gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.holder.CommonAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.child_view);
        Gson gson = new Gson();
        MoocBean mDataBeanList = gson.fromJson(Data.MUTIL_OBJECT, new TypeToken<MoocBean>(){}.getType());
        mListView.setAdapter(new CommonAdapter(this, mDataBeanList.getData(), R.layout.list_view_item,new ListDataViewHolderHelper()));
    }
}
