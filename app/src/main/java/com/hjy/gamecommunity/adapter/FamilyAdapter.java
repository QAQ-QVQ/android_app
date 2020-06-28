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
 * 描述:
 */
public class FamilyAdapter<T> extends BaseAdapter<T> {

    public FamilyAdapter() {
    }

    public FamilyAdapter(List<T> beanList) {
        super(beanList);
    }

    @Override
    public int getLayout(T item, int position) {

        return R.layout.item_family;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
        TextView mTvTitleFamily = viewHolder.findViewById(R.id.tv_TitleFamily);
        TextView mTvFamilyiD = viewHolder.findViewById(R.id.tv_FamilyiD);
        TextView mTvFamilyNumberOfPeople = viewHolder.findViewById(R.id.tv_FamilyNumberOfPeople);
        TextView mTvText = viewHolder.findViewById(R.id.tv_Text);

        if (item instanceof SearchBean.DataBean.FamilyListBean) {
            SearchBean.DataBean.FamilyListBean familyListBean = (SearchBean.DataBean.FamilyListBean) item;
            LoadingImageUtil.loadingImag(familyListBean.getAvatar(), mRivImage, true);
            mTvTitleFamily.setText(familyListBean.getName());
            mTvFamilyiD.setText("ID:" + familyListBean.getId());
            mTvFamilyNumberOfPeople.setText(String.valueOf(familyListBean.getHeadcount()));
            mTvText.setText("游戏:" + familyListBean.getGame_name() + " | 区服：" + familyListBean.getCp_server_name());
        }

    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
