package com.hjy.gamecommunity.adapter;

import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NumberUtils;
import com.hjy.baserequest.bean.LiveList;
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
    public final int MATCH_PARENT = 1;//最大宽度
    public final int FIXED = 2;//固定宽高
    private int mode = MATCH_PARENT;

    public LiveAdapter() {
        initImgWH();

    }

    public LiveAdapter(List<T> beanList) {
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
        int liveType = 0;
        int layoutId = 0;
        if (item instanceof SearchBean.DataBean.LiveListBean) {
            SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
            liveType = dataBean.getType();
        } else if (item instanceof LiveList.DataBean) {
            LiveList.DataBean dataBean = (LiveList.DataBean) item;
            liveType = dataBean.getType();
        }

        switch (liveType) {
            case 1://客服主播
                switch (mode) {
                    case MATCH_PARENT:
                        layoutId = R.layout.item_live_customer_service;
                        break;
                    case FIXED:
                        layoutId = R.layout.item_live_customer_service_fixed;
                        break;
                }
                break;
            case 2://游戏主播
                switch (mode) {
                    case MATCH_PARENT:
                        layoutId = R.layout.item_live_game;
                        break;
                    case FIXED:
                        layoutId = R.layout.item_live_game_fixed;
                        break;
                }
                break;
            default:
        }
        return layoutId;

    }


    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = getLayoutId(viewHolder);
        // Log.d("VideoAdapter", "onBindViewHolder:" +position+"--"+ layoutId);

        if (layoutId == R.layout.item_live_customer_service) {//最大宽度模式
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);


            initCustomerService(item, mRivImage, mTvName, mTvHotspotNum, mTvTitle);
        } else if (layoutId == R.layout.item_live_customer_service_fixed) {//指定宽高模式
            //客服直播
            ConstraintLayout mCl = viewHolder.findViewById(R.id.cl);
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            mCl.setLayoutParams(new LinearLayout.LayoutParams(imgW, imgH));


            initCustomerService(item, mRivImage, mTvName, mTvHotspotNum, mTvTitle);

        } else if (layoutId == R.layout.item_live_game) {//最大宽度模式
            //游戏直播
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);


            initGame(item, mRivImage, mTvName, mTvHotspotNum, mTvTitle);

        } else if (layoutId == R.layout.item_live_game_fixed) {//指定宽高模式
            //游戏直播
            ConstraintLayout mCl = viewHolder.findViewById(R.id.cl);
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            LinearLayout mLlLab = viewHolder.findViewById(R.id.ll_Lab);
            TextView mTvName = viewHolder.findViewById(R.id.tv_Name);
            TextView mTvHotspotNum = viewHolder.findViewById(R.id.tv_HotspotNum);
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);

            mCl.setLayoutParams(new LinearLayout.LayoutParams(imgW, imgH));

            initGame(item, mRivImage, mTvName, mTvHotspotNum, mTvTitle);

        }

        // viewHolder.setWaterRipple();//设置水波纹点击效果


    }

    private void initGame(T item, RadiusImageView mRivImage, TextView mTvName, TextView mTvHotspotNum, TextView mTvTitle) {
        if (item instanceof SearchBean.DataBean.LiveListBean) {
            SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
            LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
            mTvTitle.setText(dataBean.getTitle());
            mTvName.setText(dataBean.getNickname());
            int heat = dataBean.getSum_popular();
            if (heat < 10000) {
                mTvHotspotNum.setText(String.valueOf(heat));
            } else {
                String format = NumberUtils.format(heat / 10000.0f, 2);
                mTvHotspotNum.setText(format + "万");
            }

        } else if (item instanceof LiveList.DataBean) {
            LiveList.DataBean dataBean = (LiveList.DataBean) item;
            LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
            mTvTitle.setText(dataBean.getTitle());

            mTvName.setText(dataBean.getAnchor_nickname());
            int heat = dataBean.getHeat();
            if (heat < 10000) {
                mTvHotspotNum.setText(String.valueOf(heat));
            } else {
                String format = NumberUtils.format(heat / 10000.0f, 2);
                mTvHotspotNum.setText(format + "万");
            }
        }
    }

    private void initCustomerService(T item, RadiusImageView mRivImage, TextView mTvName, TextView mTvHotspotNum, TextView mTvTitle) {
        if (item instanceof SearchBean.DataBean.LiveListBean) {
            SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
            LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
            mTvTitle.setText(dataBean.getTitle());
            if (!TextUtils.isEmpty(dataBean.getNickname()))
                mTvName.setText(dataBean.getNickname());
            int heat = dataBean.getSum_popular();
            if (heat < 10000) {
                mTvHotspotNum.setText(String.valueOf(heat));
            } else {
                String format = NumberUtils.format(heat / 10000.0f, 2);
                mTvHotspotNum.setText(format + "万");
            }

        } else if (item instanceof LiveList.DataBean) {
            LiveList.DataBean dataBean = (LiveList.DataBean) item;
            LoadingImageUtil.loadingImag(dataBean.getCover_picture(), mRivImage, true);
            mTvTitle.setText(dataBean.getTitle());

            mTvName.setText(dataBean.getAnchor_nickname());
            int heat = dataBean.getHeat();
            if (heat < 10000) {
                mTvHotspotNum.setText(String.valueOf(heat));
            } else {
                String format = NumberUtils.format(heat / 10000.0f, 2);
                mTvHotspotNum.setText(format + "万");
            }
        }
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }

}
