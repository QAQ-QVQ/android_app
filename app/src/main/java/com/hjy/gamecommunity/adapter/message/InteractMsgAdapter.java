package com.hjy.gamecommunity.adapter.message;

import android.widget.ImageView;
import android.widget.TextView;

import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 9:09
 * 描述: 互动消息
 */
public class InteractMsgAdapter<T> extends BaseAdapter<T> {

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_interact_msg;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
       TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
       TextView mTvTime = viewHolder.findViewById(R.id.tv_Time);
       TextView mTvMsgType = viewHolder.findViewById(R.id.tv_MsgType);
        ImageView  mIvMsgImg = viewHolder.findViewById(R.id.iv_MsgImg);
        TextView mTvMsgText = viewHolder.findViewById(R.id.tv_MsgText);
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
