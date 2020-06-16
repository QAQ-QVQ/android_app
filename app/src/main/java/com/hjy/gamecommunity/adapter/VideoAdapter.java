package com.hjy.gamecommunity.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
public class VideoAdapter<T> extends BaseAdapter<T> {


    public VideoAdapter(RecyclerView.LayoutManager layout) {
        initItemWH(layout);
    }

    public VideoAdapter(List<T> beanList, RecyclerView.LayoutManager layout) {
        super(beanList);
        initItemWH(layout);
    }

    /**
     * @param layout
     */
    private int imgW, imgH;

    private void initItemWH(RecyclerView.LayoutManager layout) {
        if (layout instanceof GridLayoutManager) {
            imgW = (int) (ViewSeting.getScreenWidth() / 2.2f);
            imgH = (int) (imgW / 1.79f);
        } else if (layout instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layout;
            int orientation = linearLayoutManager.getOrientation();
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                imgW = (int) (ViewSeting.getScreenWidth() / 2.3f);
                imgH = (int) (imgW / 1.79f);
            } else {
                imgW = (int) (ViewSeting.getScreenWidth() / 2.2f);
                imgH = (int) (imgW / 1.79f);
            }

        }
    }

    @Override
    public int getLayout(T item, int position) {
        int layoutId = 0;
        if (item instanceof SearchBean.DataBean.VideoListBean) {
            //视频
            layoutId = R.layout.item_find_video;
        }
        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = getLayoutId(viewHolder);
        if (layoutId == R.layout.item_find_video) {
            //视频
            ConstraintLayout mCl = viewHolder.findViewById(R.id.cl);
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            TextView mTvLikedNum = viewHolder.findViewById(R.id.tv_LikedNum);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
            mCl.setLayoutParams(new LinearLayout.LayoutParams(imgW, imgH));

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
            }

        }

        // viewHolder.setWaterRipple();//设置水波纹点击效果


    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }

}
