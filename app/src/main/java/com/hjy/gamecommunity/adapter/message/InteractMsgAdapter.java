package com.hjy.gamecommunity.adapter.message;

import com.hjy.baseui.adapter.BaseAdapter;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 9:09
 * 描述:
 */
public class InteractMsgAdapter <T> extends BaseAdapter<T> {
    @Override
    public int getLayout(T item, int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {

    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
