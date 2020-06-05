package com.hjy.gamecommunity.dialog;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

public class ExitDialog {
    private Activity activity;
    private MaterialDialog titleMessgeMaterialDialog;
    private TextView mTvTitle;
    private TextView mTvText;
    private TextView mTvNo;
    private TextView mTvYes;

    public ExitDialog(Activity activity) {
        this.activity = activity;
        titleMessgeMaterialDialog = new MaterialDialog.Builder(activity)
                .cancelable(true)
                .customView(R.layout.dialog_tips_title_messge, false)
                .build();
        View customView = titleMessgeMaterialDialog.getCustomView();

        mTvTitle = (TextView) customView.findViewById(R.id.tv_title);
        mTvText = (TextView) customView.findViewById(R.id.tv_text);
        mTvNo = (TextView) customView.findViewById(R.id.tv_no);
        mTvYes = (TextView) customView.findViewById(R.id.tv_yes);

        mTvNo.setBackground(new SuperDrawable()
                .setClickAlpha(0.7f)
                .setColorBg(ContextCompat.getColor(activity, R.color.bui_gray))
                .setRadius(5)
                .buid());
        mTvYes.setBackground(new SuperDrawable()
                .setClickAlpha(0.7f)
                .setColorBg(ContextCompat.getColor(activity, R.color.colorPrimaryDark))
                .setRadius(5)
                .buid());


        mTvNo.setTextColor(ContextCompat.getColor(activity, android.R.color.white));
        mTvNo.setText("取消");
        mTvYes.setTextColor(ContextCompat.getColor(activity, android.R.color.white));
        mTvYes.setText("确认");

        String title = "退出《" + AppUtils.getAppName() + "》";
        String text = "确定要退出么？";
        mTvTitle.setText(title);

        mTvText.setText(text);

        listener();
    }

    public void listener() {

        mTvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleMessgeMaterialDialog.dismiss();
            }
        });
        mTvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleMessgeMaterialDialog.dismiss();
               // ActivitysUtil.finishAllActivities();
               // AppUtils.exitApp();
                ActivityUtils.finishAllActivities(true);
            }
        });

    }


    public void show() {
        if (!activity.isFinishing() && !titleMessgeMaterialDialog.isShowing())
            titleMessgeMaterialDialog.show();
    }
}
