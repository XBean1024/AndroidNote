package com.binny.banner.demo.screenadaptation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        TextView textView = new TextView(this);
        textView.setText("addview");
        TextView textView1 = new TextView(this);
        textView1.setText("addview1");
        TextView textView2 = new TextView(this);
        textView2.setText("addview2");
        TextView textView3 = new TextView(this);
        textView3.setText("addview3");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,300);

        textView.setGravity(Gravity.CENTER);
        /*
        * index 必须是连续的值 中间不能有跳跃，但是可以重复
        *  如： 01234566 可以
        *      02456444 不可以
        *      */
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        linearLayout.addView(textView,0,layoutParams);
        linearLayout.addView(textView1,1,layoutParams);
        linearLayout.addView(textView2,0,layoutParams);
        linearLayout.addView(imageView,0,layoutParams);
    }
}
