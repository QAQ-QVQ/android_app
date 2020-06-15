package com.hjy.gamecommunity.entity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjy.baseui.ui.view.textview.SuperTextView;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 15:53
 * 描述:
 */
public class TitleRecyclerViewEntities {
    private View rootView;
    private TextView mTvTitle;
    private SuperTextView mTvMore;
    private LinearLayout mLlMsg;
    private RecyclerView mRecyclerView;

    public TitleRecyclerViewEntities(View rootView, TextView mTvTitle, SuperTextView mTvMore, LinearLayout mLlMsg, RecyclerView mRecyclerView) {
        this.rootView = rootView;
        this.mTvTitle = mTvTitle;
        this.mTvMore = mTvMore;
        this.mLlMsg = mLlMsg;
        this.mRecyclerView = mRecyclerView;
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public TextView getmTvTitle() {
        return mTvTitle;
    }

    public void setmTvTitle(TextView mTvTitle) {
        this.mTvTitle = mTvTitle;
    }

    public SuperTextView getmTvMore() {
        return mTvMore;
    }

    public void setmTvMore(SuperTextView mTvMore) {
        this.mTvMore = mTvMore;
    }

    public LinearLayout getmLlMsg() {
        return mLlMsg;
    }

    public void setmLlMsg(LinearLayout mLlMsg) {
        this.mLlMsg = mLlMsg;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }
}
