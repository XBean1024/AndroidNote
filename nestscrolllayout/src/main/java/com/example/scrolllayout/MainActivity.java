package com.example.scrolllayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scrolllayout.bean.MoocBean;
import com.example.scrolllayout.viewholder.ListDataViewHolderHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smart.holder.CommonAdapter;

public class MainActivity extends AppCompatActivity {

//    private UleNestedScrollLayoutChildListView mListView;


    View header;
    private TextView detail;
    private MyListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.child_view);

        Gson gson = new Gson();
        MoocBean mDataBeanList = gson.fromJson(Data.MUTIL_OBJECT, new TypeToken<MoocBean>() {
        }.getType());
        mListView.setAdapter(new CommonAdapter(this, mDataBeanList.getData(), R.layout.list_view_item, new ListDataViewHolderHelper()));
    }


    public void text(View view) {
        Toast.makeText(this, "ssss", Toast.LENGTH_SHORT).show();
    }

    public void title(View view) {
        Toast.makeText(this, "比他提", Toast.LENGTH_SHORT).show();
    }
}
