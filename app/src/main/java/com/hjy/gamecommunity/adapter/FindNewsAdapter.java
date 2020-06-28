package com.hjy.gamecommunity.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjy.baserequest.bean.NewsList;
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
public class FindNewsAdapter<T> extends BaseAdapter<T> {
    private TextView mTvTitle;
    private RadiusImageView mRivImage1;
    private RadiusImageView mRivImage2;
    private RadiusImageView mRivImage3;
    private LinearLayout mLlImage;

    public FindNewsAdapter() {

    }

    public FindNewsAdapter(List<T> beanList) {
        super(beanList);
    }

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_news_three_pictures;
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

        mTvTitle = viewHolder.findViewById(R.id.tv_Title);
        mLlImage = viewHolder.findViewById(R.id.ll_Image);
        mRivImage1 = viewHolder.findViewById(R.id.riv_image1);
        mRivImage2 = viewHolder.findViewById(R.id.riv_image2);
        mRivImage3 = viewHolder.findViewById(R.id.riv_image3);

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

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int i) {

    }
}
