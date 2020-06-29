package com.hjy.gamecommunity.adapter.message;

import android.widget.TextView;

import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 9:05
 * 描述: 家族消息
 */
public class FamilyMsgAdapter<T> extends BaseAdapter<T> {


    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_family_msg;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
        TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
        TextView mTvTime = viewHolder.findViewById(R.id.tv_Time);
        TextView mTvText = viewHolder.findViewById(R.id.tv_Text);
        SuperTextView mTvNumMsg = viewHolder.findViewById(R.id.tv_NumMsg);
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
