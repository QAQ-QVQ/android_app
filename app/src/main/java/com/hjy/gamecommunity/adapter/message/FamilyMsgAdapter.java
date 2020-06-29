package com.hjy.gamecommunity.adapter.message;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 9:05
 * 描述: 家族消息
 */
public class FamilyMsgAdapter<T> extends BaseAdapter<T> {


    public FamilyMsgAdapter() {
    }

    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_family_msg;


    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
        TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
        TextView mTvTime = viewHolder.findViewById(R.id.tv_Time);
        TextView mTvText = viewHolder.findViewById(R.id.tv_Text);
        SuperButton mSbNumMsg = viewHolder.findViewById(R.id.sb_NumMsg);
        ImageView mIvImg = viewHolder.findViewById(R.id.iv_Img);
        if (position == 0) {
            mIvImg.setVisibility(View.VISIBLE);
            mSbNumMsg.setVisibility(View.GONE);
        } else if (position == 1) {
            mIvImg.setVisibility(View.GONE);
            mSbNumMsg.setVisibility(View.VISIBLE);
            int fontHeight = getFontHeight(mSbNumMsg.getTextSize()) + ConvertUtils.dp2px(4);
            mSbNumMsg.setLayoutParams(new LinearLayout.LayoutParams(fontHeight, fontHeight));
            mSbNumMsg.setText("99+");
        } else {
            mIvImg.setVisibility(View.GONE);
            mSbNumMsg.setVisibility(View.VISIBLE);
            int fontHeight = getFontHeight(mSbNumMsg.getTextSize());
            mSbNumMsg.setLayoutParams(new LinearLayout.LayoutParams(fontHeight, fontHeight));
            mSbNumMsg.setText("99");
        }

    }

    @Override
    public void listener(BaseViewHolder viewHolder, T item, int position) {

    }

    /**
     * 根据字体大小获取高度
     *
     * @param fontSize
     * @return
     */
    public int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }
}
