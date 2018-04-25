package com.binny.smartrefreshlayout.viewholderhelper.home;

import android.content.Context;
import android.view.View;

import com.binny.smartrefreshlayout.R;
import com.binny.smartrefreshlayout.bean.HomeBean;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * 作者: binny
 * 时间: 2018/4/25
 * 描述: 首页 list数据
 */
public class HomeViewHolderHelper implements IViewHolderHelper<HomeViewHolder,HomeBean> {
    @Override
    public IViewHolder initItemViewHolder(HomeViewHolder viewHolder, View convertView) {
        viewHolder = new HomeViewHolder();
        viewHolder.mTextView = convertView.findViewById(R.id.title);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<HomeBean> iBaseBeanList, HomeViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(iBaseBeanList.get(position).getText());
    }
}
