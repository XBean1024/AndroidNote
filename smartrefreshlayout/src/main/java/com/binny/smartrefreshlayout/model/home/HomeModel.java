package com.binny.smartrefreshlayout.model.home;

import com.binny.smartrefreshlayout.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: binny
 * 时间: 2018/4/25
 * 描述:
 */
public class HomeModel implements IHomeModel {
    private List<HomeBean> mHomeBeans;


    @Override
    public void setData() {
        mHomeBeans = new ArrayList<>();
        HomeBean homeBean;
        for (int i = 0; i < 20; i++) {
            homeBean = new HomeBean();
            homeBean.setText("测试 " + i);
            mHomeBeans.add(homeBean);
        }
    }

    @Override
    public List<HomeBean> getData() {
        return mHomeBeans;
    }
}
