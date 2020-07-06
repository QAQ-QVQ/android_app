package com.hjy.gamecommunity.adapter.message;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.database.model.Conversation;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 9:05
 * 描述: 家族消息
 */
public class FamilyMsgAdapter extends BaseAdapter<Conversation> {


    public FamilyMsgAdapter() {
    }

    @Override
    public int getLayout(Conversation item, int position) {
        return R.layout.item_family_msg;


    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, Conversation item, int position) {

        RadiusImageView mRivImage = viewHolder.findViewById(R.id.riv_image);//家族头像
        TextView mTvTitle = viewHolder.findViewById(R.id.tv_Title);//家族名
        TextView mTvTime = viewHolder.findViewById(R.id.tv_Time);//家族最新消息时间
        TextView mTvText = viewHolder.findViewById(R.id.tv_Text);//家族最新消息内容
        SuperButton mSbNumMsg = viewHolder.findViewById(R.id.sb_NumMsg);//家族未读消息数
        ImageView mIvImg = viewHolder.findViewById(R.id.iv_Img);//免打扰图标

        LoadingImageUtil.loadingImag(item.getFaceUrll(), mRivImage, true);
        mTvTitle.setText(item.getName());
        long timestamp = item.getTimestamp() * 1000;
        if (com.hjy.baseutil.TimeUtils.isYesterday(timestamp)) {
            //是昨天
            String millis2String = TimeUtils.millis2String(timestamp, "HH:mm");
            mTvTime.setText("昨天" + millis2String);
        } else {
            if (com.hjy.baseutil.TimeUtils.isToday(timestamp)) {
                //是今天
                String millis2String = TimeUtils.millis2String(timestamp, "HH:mm");
                mTvTime.setText(millis2String);
            } else {
                String millis2String = TimeUtils.millis2String(timestamp, "MM.dd HH:mm");
                mTvTime.setText(millis2String);
            }
        }


        if (item.getTextElem() != null) {
            mTvText.setText(item.getTextElem());
        }
        int unreadCount = item.getUnreadCount();
        if (unreadCount > 99) {
            int fontHeight = getFontHeight(mSbNumMsg.getTextSize()) + ConvertUtils.dp2px(4);
            mSbNumMsg.setLayoutParams(new LinearLayout.LayoutParams(fontHeight, fontHeight));
            mSbNumMsg.setText(unreadCount + "+");//未读消息数
        } else {
            int fontHeight = getFontHeight(mSbNumMsg.getTextSize());
            mSbNumMsg.setLayoutParams(new LinearLayout.LayoutParams(fontHeight, fontHeight));
            mSbNumMsg.setText(String.valueOf(unreadCount));//未读消息数
        }

        /**
         * •V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_MESSAGE：在线正常接收消息，离线时会有厂商的离线推送通知。
         * •V2TIMGroupInfo.V2TIM_GROUP_NOT_RECEIVE_MESSAGE：不会接收到群消息。
         * •V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_NOT_NOTIFY_MESSAGE：在线正常接收消息，离线不会有推送
         *
         * 群消息接收选项设置为 V2TIMGroupInfo.V2TIM_GROUP_NOT_RECEIVE_MESSAGE 后，群内的任何消息都收不到
         * 群消息接收选项设置为 V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_NOT_NOTIFY_MESSAGE，
         * 当群内收到新消息，会话列表需要更新时，可以通过会话中的 getUnreadCount 获取到消息未读数。
         * 根据 getRecvOpt 判断获取到的群消息接收选项为 V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_NOT_NOTIFY_MESSAGE
         * 时显示小红点而非消息未读数
         *  // V2TIMManager.getGroupManager().setReceiveMessageOpt();
         */

        String recvOpt = item.getIsRemind();//获取消息接收选项（群会话有效）
        switch (recvOpt) {
            case "1"://在线正常接收消息，离线时会有厂商的离线推送通知。
                mIvImg.setVisibility(View.GONE);
                mSbNumMsg.setVisibility(View.VISIBLE);
                break;
            case "2"://在线正常接收消息，离线不会有推送
                mIvImg.setVisibility(View.VISIBLE);
                mSbNumMsg.setVisibility(View.GONE);
                break;
        }


    }

    @Override
    public void listener(BaseViewHolder viewHolder, Conversation item, int position) {

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
