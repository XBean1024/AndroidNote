package com.binny.smartrefreshlayout.model.home;

import com.binny.smartrefreshlayout.bean.HomeBean;

import java.util.List;

/**
 * 作者: binny
 * 时间: 2018/4/25
 * 描述:
 */
public interface IHomeModel {
    /**
     * 设置数据
     */
    void setData();

    /*
    * 获取数据
    * */
    List<HomeBean> getData();
}
