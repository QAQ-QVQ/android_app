package com.hjy.gamecommunity.broadcastreceiver;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class TalkReceiver extends JPushMessageReceiver {
    private String TAG = "TalkReceiver";

    private NotificationManager nm;

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);

        Log.d(TAG, customMessage.toString());

        //        if (null == nm) {
//            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        }
//
//        Bundle bundle = intent.getExtras();
//
//        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//            Log.d(TAG, "JPush 用户注册成功");
//
//        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//            Log.d(TAG, "接受到推送下来的自定义消息");
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            App.tost(message);
//        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//            Log.d(TAG, "接受到推送下来的通知");
//        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//            Log.d(TAG, "用户点击打开了通知");
//        } else {
//            Log.d(TAG, "Unhandled intent - " + intent.getAction());
//        }
    }
}
