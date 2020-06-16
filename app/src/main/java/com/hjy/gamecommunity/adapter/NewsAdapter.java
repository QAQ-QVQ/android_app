package com.hjy.gamecommunity.adapter;

import android.widget.TextView;

import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/16 10:40
 * 描述: 资讯
 */
public class NewsAdapter<T> extends BaseAdapter<T> {



    public NewsAdapter() {

    }

    public NewsAdapter(List<T> beanList) {
        super(beanList);
    }

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_news;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
        TextView  mTvTitleGame =  viewHolder.findViewById(R.id.tv_TitleGame);


        if (item instanceof SearchBean.DataBean.NewsListBean) {
            SearchBean.DataBean.NewsListBean newsListBean = (SearchBean.DataBean.NewsListBean) item;
            LoadingImageUtil.loadingImag(newsListBean.getCover_picture(), mRivImage, true);
            mTvTitleGame.setText(newsListBean.getTitle());
        }

        viewHolder.setWaterRipple();
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
