package com.hjy.gamecommunity.adapter;

import android.widget.LinearLayout;
import android.widget.TextView;

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
        if (position == 0) {
            //客服直播
            layoutId = R.layout.item_live_customer_service;//
        } else if (position == 1) {
            //游戏直播
            layoutId = R.layout.item_find_live_game;
            return layoutId;
        } else {
            //视频
            layoutId = R.layout.item_find_video;
            return layoutId;
        }
        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = getLayoutId(position);

        if (layoutId == R.layout.item_live_customer_service) {
            //客服直播
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);


            mTvTitle.setText("客服直播");
            mTvName.setText("冯提莫");
            mTvHotspotNum.setText("5.1万");
            LoadingImageUtil.loadingImag(R.mipmap.ic_launcher, mRivImage, true);
        } else if (layoutId == R.layout.item_find_live_game) {
            //游戏直播

            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);


            mTvTitle.setText("游戏直播");
            mTvName.setText("冯提莫");
            mTvHotspotNum.setText("5.1万");
            LoadingImageUtil.loadingImag(R.mipmap.ic_launcher, mRivImage, true);
        } else if (layoutId == R.layout.item_find_video) {
            //视频

            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            TextView mTvLikedNum = viewHolder.findViewById(R.id.tv_LikedNum);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);


            mTvTitle.setText("视频");
            mTvLikedNum.setText("2564");
            mTvHotspotNum.setText("5.1万");
            LoadingImageUtil.loadingImag(R.mipmap.ic_launcher, mRivImage, true);
        }

        viewHolder.setWaterRipple();//设置水波纹点击效果


    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }

}
