package com.hjy.gamecommunity.adapter.message;

import android.graphics.Paint;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 9:08
 * 描述: 官方消息
 */
public class OfficialMsgAdapter<T> extends BaseAdapter<T> {
    @Override
    public int getLayout(T item, int position) {
        return R.layout.item_official_msg;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, T item, int position) {
        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);
        TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);
        TextView mTvTime = viewHolder.findViewById(R.id.tv_Time);
        TextView mTvText = viewHolder.findViewById(R.id.tv_Text);
        SuperButton mSbNumMsg = viewHolder.findViewById(R.id.sb_NumMsg);
        if (position <= 1) {
            int fontHeight = getFontHeight(mSbNumMsg.getTextSize()) + ConvertUtils.dp2px(4);
            mSbNumMsg.setLayoutParams(new LinearLayout.LayoutParams(fontHeight, fontHeight));
            mSbNumMsg.setText("99+");
        } else {
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
