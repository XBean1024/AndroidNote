package com.binny.androidnote;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    private android.widget.Button save;
    private android.widget.Button pick;
    private Button clear;
    private Button cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cover = (Button) findViewById(R.id.cover);
        this.clear = (Button) findViewById(R.id.clear);
        this.pick = (Button) findViewById(R.id.pick);
        this.save = (Button) findViewById(R.id.save);

        mSharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString("test", "tedt");
                mEditor.apply();
            }
        });
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 覆盖旧值
                 * */
                mEditor.putString("test", "tedt2");
                mEditor.apply();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * sp 里面的键值对会消失
                 * */
                mEditor.clear();
                mEditor.commit();
            }
        });
    }
}
