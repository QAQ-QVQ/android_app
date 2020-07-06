package com.hjy.gamecommunity.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.hjy.baserequest.bean.MyGameInfoBean;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.gamecommunity.R;

import java.util.List;

/**
 * CREATED BY DY ON 2020/7/6.
 * TIME BY 9:58.
 *
 * @author DY
 **/
public class MyGameAdapter<T> extends BaseAdapter<T> {
    public MyGameAdapter() {
    }

    public MyGameAdapter(List<T> beanList) {
        super(beanList);
    }

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_mine_mygame;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        ImageView gameIcon = viewHolder.findViewById(R.id.game_icon);
        TextView gameName = viewHolder.findViewById(R.id.game_name);
        if (item instanceof MyGameInfoBean.DataBean){
            LoadingImageUtil.loadingImag(((MyGameInfoBean.DataBean) item).getIcon(),gameIcon,true);
            gameName.setText(((MyGameInfoBean.DataBean) item).getName());
        }
    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }
}
