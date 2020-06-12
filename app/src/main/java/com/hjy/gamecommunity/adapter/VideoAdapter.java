package com.hjy.gamecommunity.adapter;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NumberUtils;
import com.hjy.baserequest.bean.AnchorList;
import com.hjy.baserequest.bean.VideoList;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/10 16:01
 * 描述: 发现-视频Adapter
 */
public class VideoAdapter<T> extends BaseAdapter<T> {


    public VideoAdapter() {

    }

    public VideoAdapter(List<T> beanList) {
        super(beanList);
    }

    @Override
    public int getLayout(T item, int position) {
        int layoutId = 0;
        if (item instanceof AnchorList.DataBean) {
            AnchorList.DataBean dataBean = (AnchorList.DataBean) item;
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
        } else if (item instanceof VideoList.DataBean.ListBean) {
            //视频
            layoutId = R.layout.item_find_video;
            // Log.d("VideoAdapter", "视频layoutId:" +position+"--"+ layoutId);
        }

        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = getLayoutId(viewHolder);
        // Log.d("VideoAdapter", "onBindViewHolder:" +position+"--"+ layoutId);
        if (layoutId == R.layout.item_live_customer_service) {
            //客服直播
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            if (item instanceof AnchorList.DataBean) {
                AnchorList.DataBean dataBean = (AnchorList.DataBean) item;
                LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
                mTvTitle.setText("客服直播");
                if (!TextUtils.isEmpty(dataBean.getNickname()))
                    mTvName.setText(dataBean.getNickname());
                int heat = dataBean.getHeat();
                if (heat < 10000) {
                    mTvHotspotNum.setText(String.valueOf(heat));
                } else {
                    String format = NumberUtils.format(heat / 10000.0f, 2);
                    mTvHotspotNum.setText(format + "万");
                }

            }

        } else if (layoutId == R.layout.item_find_live_game) {
            //游戏直播

            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
            if (item instanceof AnchorList.DataBean) {
                AnchorList.DataBean dataBean = (AnchorList.DataBean) item;
                LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
                mTvTitle.setText("客服直播");
                mTvName.setText(dataBean.getNickname());
                int heat = dataBean.getHeat();
                if (heat < 10000) {
                    mTvHotspotNum.setText(String.valueOf(heat));
                } else {
                    String format = NumberUtils.format(heat / 10000.0f, 2);
                    mTvHotspotNum.setText(format + "万");
                }

            }

        } else if (layoutId == R.layout.item_find_video) {
            //视频

            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            TextView mTvLikedNum = viewHolder.findViewById(R.id.tv_LikedNum);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            if (item instanceof VideoList.DataBean.ListBean) {
                VideoList.DataBean.ListBean listBean = (VideoList.DataBean.ListBean) item;
                LoadingImageUtil.loadingImag(listBean.getCover_picture(), mRivImage, true);
                mTvTitle.setText(listBean.getTitle());

                int sum_like = listBean.getSum_like();
                if (sum_like < 10000) {
                    mTvLikedNum.setText(String.valueOf(sum_like));
                } else {
                    String format = NumberUtils.format(sum_like / 10000.0f, 2);
                    mTvLikedNum.setText(format + "万");
                }

                int sum_play = listBean.getSum_play();
                if (sum_play < 10000) {
                    mTvHotspotNum.setText(String.valueOf(sum_play));
                } else {
                    String format = NumberUtils.format(sum_play / 10000.0f, 2);
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
