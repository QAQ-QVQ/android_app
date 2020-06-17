package com.hjy.gamecommunity.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjy.baserequest.bean.NewsList;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/16 10:40
 * 描述: 资讯
 */
public class NewsAdapter<T> extends BaseAdapter<T> {
    public final static int ONE_PICTURES = 1;//单图
    public final static int THREE_PICTURES = 3;//三图
    private int mode = ONE_PICTURES;//默认单图

    public NewsAdapter(int mode) {
        this.mode = mode;
    }

    public NewsAdapter(List<T> beanList, int mode) {
        super(beanList);
        this.mode = mode;
    }

    @Override
    public int getLayout(T item, int position) {
        if (mode == ONE_PICTURES) {
            return R.layout.item_news;
        } else if (mode == THREE_PICTURES) {
            return R.layout.item_news_three_pictures;
        } else {
            return -1;
        }

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
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        int layoutId = viewHolder.getLayoutId();
        if (layoutId == R.layout.item_news) {
            RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
            TextView mTvTitleGame = viewHolder.findViewById(R.id.tv_TitleGame);

            if (item instanceof SearchBean.DataBean.NewsListBean) {
                SearchBean.DataBean.NewsListBean newsListBean = (SearchBean.DataBean.NewsListBean) item;
                LoadingImageUtil.loadingImag(newsListBean.getCover_picture(), mRivImage, true);
                mTvTitleGame.setText(newsListBean.getTitle());
            }
        } else if (layoutId == R.layout.item_news_three_pictures) {
            TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
            LinearLayout mLlImage = viewHolder.findViewById(R.id.ll_Image);
            RadiusImageView mRivImage1 = viewHolder.findViewById(R.id.riv_image1);
            RadiusImageView mRivImage2 = viewHolder.findViewById(R.id.riv_image2);
            RadiusImageView mRivImage3 = viewHolder.findViewById(R.id.riv_image3);

            setImgWH(mRivImage1);
            setImgWH(mRivImage2);
            setImgWH(mRivImage3);

            if (item instanceof NewsList.DataBean.ListBean) {
                NewsList.DataBean.ListBean listBean = (NewsList.DataBean.ListBean) item;
                mTvTitle.setText(listBean.getTitle());
                String cover_picture = listBean.getCover_picture();
                if (!TextUtils.isEmpty(cover_picture)) {
                    List<String> stringList = new Gson().fromJson(cover_picture, new TypeToken<List<String>>() {
                    }.getType());

                    for (int i = 0; i < stringList.size(); i++) {
                        View childAt = mLlImage.getChildAt(i);
                        if (childAt instanceof ImageView) {
                            LoadingImageUtil.loadingImag(stringList.get(i), (ImageView) childAt, true);
                        }
                    }
                }
            }
        }


        viewHolder.setWaterRipple();
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
