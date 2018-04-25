package com.binny.smartrefreshlayout.bean;

import java.io.Serializable;

/**
 * 作者: binny
 * 时间: 2018/4/25
 * 描述:
 */
public class HomeBean implements Serializable {
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
