package com.hjy.gamecommunity.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.SuperDrawable;
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
            //Log.d("VideoAdapter", "客服直播layoutId:" + position + "--" + layoutId);
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
        Log.d("VideoAdapter", "layoutId:" + position + "--" + layoutId);

        viewHolder.itemView.setBackground(new SuperDrawable()
                .setColorBg(Color.WHITE)
                .setClickAlpha(1.0f)
                .setClickColorBg(Color.TRANSPARENT)
                .setRadius(5)
                .buid());
        if (layoutId == R.layout.item_live_customer_service) {
            //客服直播
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            mLlLab.setBackground(getGradientDrawable1());


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

            mLlLab.setBackground(getGradientDrawable2());


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


    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }


    /**
     * 渐变样式
     *
     * @return
     */
    private StateListDrawable getGradientDrawable1() {
        //状态2
        int startColor = Color.parseColor("#EE6A55");
        int endColor = Color.parseColor("#E43947");
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)//设置点击后透明度
                .setRadius(5)//圆角
                .setSColors(new int[]{startColor, endColor})
                .setSGradientType(GradientDrawable.LINEAR_GRADIENT)//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
                .setSOrientation(GradientDrawable.Orientation.LEFT_RIGHT)//渐变方向从左到右
                .buid();
        return stateListDrawable;
    }

    /**
     * 渐变样式
     *
     * @return
     */
    private StateListDrawable getGradientDrawable2() {
        //状态2
        int startColor = Color.parseColor("#EF9437");
        int endColor = Color.parseColor("#ED7730");
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)//设置点击后透明度
                .setRadius(5)//圆角
                .setSColors(new int[]{startColor, endColor})
                .setSGradientType(GradientDrawable.LINEAR_GRADIENT)//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
                .setSOrientation(GradientDrawable.Orientation.LEFT_RIGHT)//渐变方向从左到右
                .buid();
        return stateListDrawable;
    }
}
