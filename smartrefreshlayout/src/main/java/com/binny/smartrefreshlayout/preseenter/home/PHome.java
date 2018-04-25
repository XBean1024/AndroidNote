package com.binny.smartrefreshlayout.preseenter.home;

import com.binny.smartrefreshlayout.model.home.HomeModel;
import com.binny.smartrefreshlayout.model.home.IHomeModel;
import com.binny.smartrefreshlayout.view.home.IHomeView;

/**
 * 作者: binny
 * 时间: 2018/4/25
 * 描述:
 */
public class PHome implements IPHome{
    private IHomeView mHomeView;
    private IHomeModel mIHomeModel;

    public PHome(IHomeView homeView) {
        mHomeView = homeView;
        mIHomeModel = new HomeModel();
    }

    @Override
    public void loadData() {
        mIHomeModel.setData();
        mHomeView.setData(mIHomeModel.getData());
    }
}
