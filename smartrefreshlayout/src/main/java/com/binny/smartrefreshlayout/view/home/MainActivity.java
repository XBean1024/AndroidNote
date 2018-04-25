package com.binny.smartrefreshlayout.view.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.binny.smartrefreshlayout.R;
import com.binny.smartrefreshlayout.bean.HomeBean;
import com.binny.smartrefreshlayout.preseenter.home.PHome;
import com.binny.smartrefreshlayout.viewholderhelper.home.HomeViewHolderHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smart.holder.CommonAdapter;

import java.util.List;

/*
 * SmartRefreshLayout 的用法
 *  https://github.com/scwang90/SmartRefreshLayout
 * */
public class MainActivity extends AppCompatActivity implements IHomeView {

    private static final String TAG = "MainActivity";
    private RefreshLayout mRefreshLayout;
    private GridView mGridView;
    private PHome mPHome;
    private int mSize = 20;
    private List<HomeBean> mHomeBeans;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPHome = new PHome(this);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        mPHome.loadData();
    }

    private void initListener() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                HomeBean homeBean;
                int size = mHomeBeans.size();
                for (int i = 0; i < 5; i++) {
                    homeBean = new HomeBean();
                    homeBean.setText("测试 " + (size + i));
                    mHomeBeans.add(homeBean);
                }
                refreshlayout.finishRefresh(/*,false*/);//传入false表示刷新失败
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                mAdapter.notifyDataSetChanged();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void initView() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mGridView = findViewById(R.id.grid_view);
    }

    @Override
    public void setData(List<HomeBean> homeBeanList) {
        mHomeBeans = homeBeanList;
        Log.i(TAG, "setData: MainActivity" + homeBeanList.size());
        mAdapter = new CommonAdapter(this, mHomeBeans, R.layout.grid_view_item, new HomeViewHolderHelper());
        mGridView.setAdapter(mAdapter);
    }
}
