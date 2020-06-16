package com.hjy.gamecommunity.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.NumberUtils;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 16:54
 * 描述: 直播-Adapter
 */
public class LiveAdapter<T> extends BaseAdapter<T> {


    public LiveAdapter(RecyclerView.LayoutManager layout) {
        initItemWH(layout);

    }

    public LiveAdapter(List<T> beanList, RecyclerView.LayoutManager layout) {
        super(beanList);
        initItemWH(layout);
    }

    /**
     * @param layout
     */
    private int imgW, imgH;

    private void initItemWH(RecyclerView.LayoutManager layout) {
        if (layout instanceof GridLayoutManager) {
            imgW = ViewGroup.LayoutParams.MATCH_PARENT;
            imgH = ConvertUtils.dp2px(96);
        } else if (layout instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layout;
            int orientation = linearLayoutManager.getOrientation();
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                imgW = (int) (ViewSeting.getScreenWidth() / 2.2f);
                imgH = (int) (imgW / 1.79f);
            } else {
                imgW = ViewGroup.LayoutParams.MATCH_PARENT;
                imgH = ConvertUtils.dp2px(96);
            }

        }
    }

    @Override
    public int getLayout(T item, int position) {
        int layoutId = 0;
        if (item instanceof SearchBean.DataBean.LiveListBean) {
            SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
            switch (dataBean.getType()) {
                case 1://客服主播
                    layoutId = R.layout.item_live_customer_service;
                    // Log.d("VideoAdapter", "客服主播layoutId:" +position+"--"+  layoutId);
                    break;
                case 2://游戏主播
                    layoutId = R.layout.item_find_live_game;
                    // Log.d("VideoAdapter", "游戏主播layoutId:" +position+"--"+ layoutId);
                    break;
                default:
            }
        }

        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = getLayoutId(viewHolder);
        // Log.d("VideoAdapter", "onBindViewHolder:" +position+"--"+ layoutId);
        if (layoutId == R.layout.item_live_customer_service) {
            //客服直播
            ConstraintLayout mCl = viewHolder.findViewById(R.id.cl);
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            mCl.setLayoutParams(new LinearLayout.LayoutParams(imgW, imgH));


            if (item instanceof SearchBean.DataBean.LiveListBean) {
                SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
                LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
                mTvTitle.setText("客服直播");
                if (!TextUtils.isEmpty(dataBean.getNickname()))
                    mTvName.setText(dataBean.getNickname());
                int heat = dataBean.getSum_popular();
                if (heat < 10000) {
                    mTvHotspotNum.setText(String.valueOf(heat));
                } else {
                    String format = NumberUtils.format(heat / 10000.0f, 2);
                    mTvHotspotNum.setText(format + "万");
                }

            }

        } else if (layoutId == R.layout.item_find_live_game) {
            //游戏直播
            ConstraintLayout mCl = viewHolder.findViewById(R.id.cl);
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            mCl.setLayoutParams(new LinearLayout.LayoutParams(imgW, imgH));

            if (item instanceof SearchBean.DataBean.LiveListBean) {
                SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
                LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
                mTvTitle.setText("客服直播");
                mTvName.setText(dataBean.getNickname());
                int heat = dataBean.getSum_popular();
                if (heat < 10000) {
                    mTvHotspotNum.setText(String.valueOf(heat));
                } else {
                    String format = NumberUtils.format(heat / 10000.0f, 2);
                    mTvHotspotNum.setText(format + "万");
                }

            }

        }

        // viewHolder.setWaterRipple();//设置水波纹点击效果


    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }

}
