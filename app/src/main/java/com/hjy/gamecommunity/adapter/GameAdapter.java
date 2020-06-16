package com.hjy.gamecommunity.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/16 11:30
 * 描述:
 */
public class GameAdapter<T> extends BaseAdapter<T> {
    private int imgInterval;
    private int imgWH;

    public GameAdapter() {
        imgInterval = ConvertUtils.dp2px(2);
        imgWH = ConvertUtils.dp2px(26);

    }

    public GameAdapter(List<T> beanList) {
        super(beanList);
        imgInterval = ConvertUtils.dp2px(2);
        imgWH = ConvertUtils.dp2px(26);
    }

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_game;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
        TextView mTvTitleGame = viewHolder.findViewById(R.id.tv_TitleGame);
        TextView mTvText = viewHolder.findViewById(R.id.tv_Text);
        LinearLayout mLlImg = viewHolder.findViewById(R.id.ll_Img);

        if (item instanceof SearchBean.DataBean.GameListBean) {
            SearchBean.DataBean.GameListBean gameListBean = (SearchBean.DataBean.GameListBean) item;
            LoadingImageUtil.loadingImag(gameListBean.getIcon(), mRivImage, true);
            mTvTitleGame.setText(gameListBean.getName());
            mTvText.setText("家族数量:" + gameListBean.getFamily_count());

            mLlImg.removeAllViews();
            List<SearchBean.DataBean.GameListBean.FamilysBean> gameListBeanFamilys = gameListBean.getFamilys();
            for (SearchBean.DataBean.GameListBean.FamilysBean family : gameListBeanFamilys) {
                View view = View.inflate(viewHolder.itemView.getContext(), R.layout.radiusimageview_layout, null);
                LinearLayout mLlImage = view.findViewById(R.id.ll_Image);
                mLlImage.setPadding(imgInterval, imgInterval, imgInterval, imgInterval);
                RadiusImageView mRivImage2 = view.findViewById(R.id.riv_Image);
                mRivImage2.setLayoutParams(new LinearLayout.LayoutParams(imgWH, imgWH));
                LoadingImageUtil.loadingImag(family.getAvatar(), mRivImage2, true);

                mLlImg.addView(view);
            }
        }

        viewHolder.setWaterRipple();
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
