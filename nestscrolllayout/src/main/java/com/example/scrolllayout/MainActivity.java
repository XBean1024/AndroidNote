package com.example.scrolllayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.holder.CommonAdapter;

public class MainActivity extends AppCompatActivity {

    private MyListView mListView;


    View header;
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        mListView = findViewById(R.id.child_view);

        Gson gson = new Gson();
        MoocBean mDataBeanList = gson.fromJson(Data.MUTIL_OBJECT, new TypeToken<MoocBean>() {
        }.getType());
        mListView.setAdapter(new CommonAdapter(this, mDataBeanList.getData(), R.layout.list_view_item, new ListDataViewHolderHelper()));
    }


}
