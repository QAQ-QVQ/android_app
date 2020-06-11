package com.hjy.gamecommunity.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/10 16:01
 * 描述: 发现-资讯Adapter
 */
public class RealTimeInfoAdapter<T> extends BaseAdapter<T> {
    private TextView mTvTitle;
    private RadiusImageView mRivImage1;
    private RadiusImageView mRivImage2;
    private RadiusImageView mRivImage3;

    public RealTimeInfoAdapter() {
    }

    public RealTimeInfoAdapter(List<T> beanList) {
        super(beanList);
    }

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_realtimeinfo;
    }

    /**
     * 设置图片宽高
     *
     * @param imageView
     */
    private void setImgWH(ImageView imageView) {
        ViewSeting.setMeasuredHeight(imageView, 1.0f);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int i) {

        mTvTitle = viewHolder.findViewById(R.id.tv_Title);
        mRivImage1 = viewHolder.findViewById(R.id.riv_image1);
        mRivImage2 = viewHolder.findViewById(R.id.riv_image2);
        mRivImage3 = viewHolder.findViewById(R.id.riv_image3);

        setImgWH(mRivImage1);
        setImgWH(mRivImage2);
        setImgWH(mRivImage3);

        mTvTitle.setText("xxxxxxxxxxx");
        LoadingImageUtil.loadingImag("", mRivImage1, true);
        LoadingImageUtil.loadingImag("", mRivImage2, true);
        LoadingImageUtil.loadingImag("", mRivImage3, true);

        viewHolder.setWaterRipple();//设置水波纹点击效果
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }
}
