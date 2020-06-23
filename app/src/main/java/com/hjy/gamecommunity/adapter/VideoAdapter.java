package com.hjy.gamecommunity.adapter;

import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NumberUtils;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baserequest.bean.VideoList;
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
public class VideoAdapter<T> extends BaseAdapter<T> {
    public final int MATCH_PARENT = 1;//最大宽度
    public final int FIXED = 2;//固定宽高
    private int mode = MATCH_PARENT;

    public VideoAdapter() {
        initImgWH();
    }

    public VideoAdapter(List<T> beanList) {
        super(beanList);
        initImgWH();
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * FIXED模式下设置img宽高
     *
     * @param layout
     */
    private int imgW, imgH;

    private void initImgWH() {
        imgW = (int) (ViewSeting.getScreenWidth() / 2.3f);
        imgH = (int) (imgW / 1.79f);
    }

    @Override
    public int getLayout(T item, int position) {
        int layoutId = 0;
        switch (mode) {
            case MATCH_PARENT:
                layoutId = R.layout.item_video;
                break;
            case FIXED:
                layoutId = R.layout.item_video_fixed;
                break;
        }
        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = getLayoutId(viewHolder);
        if (layoutId == R.layout.item_video) {//最大宽度模式
            //视频
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            TextView mTvLikedNum = viewHolder.findViewById(R.id.tv_LikedNum);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            initData(item, mRivImage, mTvLikedNum, mTvHotspotNum, mTvTitle);

        } else if (layoutId == R.layout.item_video_fixed) {//指定宽高模式
            //视频
            ConstraintLayout mCl = viewHolder.findViewById(R.id.cl);
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            TextView mTvLikedNum = viewHolder.findViewById(R.id.tv_LikedNum);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
            mCl.setLayoutParams(new LinearLayout.LayoutParams(imgW, imgH));

            initData(item, mRivImage, mTvLikedNum, mTvHotspotNum, mTvTitle);

        }

        // viewHolder.setWaterRipple();//设置水波纹点击效果


    }

    private void initData(T item, RadiusImageView mRivImage, TextView mTvLikedNum, TextView mTvHotspotNum, TextView mTvTitle) {
        if (item instanceof SearchBean.DataBean.VideoListBean) {
            SearchBean.DataBean.VideoListBean videoListBean = (SearchBean.DataBean.VideoListBean) item;
            LoadingImageUtil.loadingImag(videoListBean.getCover_picture(), mRivImage, true);
            mTvTitle.setText(videoListBean.getTitle());

            int sum_like = videoListBean.getSum_like();
            if (sum_like < 10000) {
                mTvLikedNum.setText(String.valueOf(sum_like));
            } else {
                String format = NumberUtils.format(sum_like / 10000.0f, 2);
                mTvLikedNum.setText(format + "万");
            }

            int sum_play = videoListBean.getSum_play();
            if (sum_play < 10000) {
                mTvHotspotNum.setText(String.valueOf(sum_play));
            } else {
                String format = NumberUtils.format(sum_play / 10000.0f, 2);
                mTvHotspotNum.setText(format + "万");
            }
        } else if (item instanceof VideoList.DataBean.ListBean) {
            VideoList.DataBean.ListBean videoListBean = (VideoList.DataBean.ListBean) item;
            LoadingImageUtil.loadingImag(videoListBean.getCover_picture(), mRivImage, true);
            mTvTitle.setText(videoListBean.getTitle());

            int sum_like = videoListBean.getSum_like();
            if (sum_like < 10000) {
                mTvLikedNum.setText(String.valueOf(sum_like));
            } else {
                String format = NumberUtils.format(sum_like / 10000.0f, 2);
                mTvLikedNum.setText(format + "万");
            }

            int sum_play = videoListBean.getSum_play();
            if (sum_play < 10000) {
                mTvHotspotNum.setText(String.valueOf(sum_play));
            } else {
                String format = NumberUtils.format(sum_play / 10000.0f, 2);
                mTvHotspotNum.setText(format + "万");
            }

        }
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }

}
