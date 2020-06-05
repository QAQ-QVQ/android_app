package com.hjy.baseui.ui.view.dialog;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hjy.baseui.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 14:03
 * 描述: 提示Dialog
 */
/*
  调用示例：
        TipsTextDialog loadPopup = new TipsTextDialog(getActivity())
                .setTitle("标题")
                .setText("内容")
                .setOnLeftButtonClickListener("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setOnRightButtonClickListener("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        loadPopup.show();
 */

public class TipsTextDialog extends BaseDialog {

    private TextView mTvTitle;
    private TextView mTvText;
    private TextView mTvButton1;
    private View mVDividingLine;
    private TextView mTvButton2;

    public TipsTextDialog(Activity activity) {
        super(activity);
        initView(getRootView());

    }

    public TipsTextDialog(Activity activity, int width, int height) {
        super(activity, width, height);
        initView(getRootView());
    }


    @Override
    public int getLayout() {
        return R.layout.bui_tips_dialog;
    }

    private void initView(View view) {
        setDialogRadiusColos(5, R.color.bui_white);

        mTvTitle = view.findViewById(R.id.tv_title);
        mTvText = view.findViewById(R.id.tv_text);
        mTvButton1 = view.findViewById(R.id.tv_Button1);
        mVDividingLine = view.findViewById(R.id.v_DividingLine);
        mTvButton2 = view.findViewById(R.id.tv_Button2);

        mTvButton1.setTextSize(18);
        mTvButton2.setTextSize(18);
        mTvButton1.setTextColor(ContextCompat.getColor(getContext(), R.color.bui_black_light1));
        mTvButton2.setTextColor(ContextCompat.getColor(getContext(), R.color.bui_red_light));
        mTvButton1.setText("");
        mTvButton2.setText("");


    }

    public TextView getTitle() {
        return mTvTitle;
    }

    public TextView getText() {
        return mTvText;
    }

    public TextView getLeftButton() {
        return mTvButton1;
    }

    public TextView getRightButton() {
        return mTvButton2;
    }

    public TipsTextDialog setTitle(String mTvTitle) {
        this.mTvTitle.setText(mTvTitle);
        return this;
    }

    public TipsTextDialog setText(String mTvText) {
        this.mTvText.setText(mTvText);
        return this;
    }

    public TipsTextDialog setOnLeftButtonClickListener(String text, View.OnClickListener onNoClickListener) {
        mTvButton1.setText(text);
        mTvButton1.setOnClickListener(onNoClickListener);
        return this;
    }

    public TipsTextDialog setOnRightButtonClickListener(String text, View.OnClickListener onYesClickListener) {
        mTvButton2.setText(text);
        mTvButton2.setOnClickListener(onYesClickListener);
        return this;
    }


    @Override
    public void show() {
        String mTvButton1String = mTvButton1.getText().toString();
        String mTvButton2String = mTvButton2.getText().toString();
        if (TextUtils.isEmpty(mTvButton1String)) {
            mTvButton1.setVisibility(View.GONE);
        } else {
            mTvButton1.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(mTvButton2String)) {
            mTvButton2.setVisibility(View.GONE);
        } else {
            mTvButton2.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(mTvButton1String) || TextUtils.isEmpty(mTvButton2String)) {
            mVDividingLine.setVisibility(View.GONE);
        } else {
            mVDividingLine.setVisibility(View.VISIBLE);
        }

        super.show();
    }

}
