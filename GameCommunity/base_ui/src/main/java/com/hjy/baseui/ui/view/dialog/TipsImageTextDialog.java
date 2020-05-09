package com.hjy.baseui.ui.view.dialog;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.hjy.baseui.R;
import com.hjy.baseutil.LoadingImageUtil;

import java.lang.ref.WeakReference;

public class TipsImageTextDialog {

    private AlertDialog tipsDialog;
    private ImageView iv_image;
    private TextView tv_text;
    private Button bt_yes, bt_no;
    private View view;
    private AlertDialog.Builder builder;
    private WeakReference<Activity> weakReference;

    public TipsImageTextDialog(Activity activity) {
        weakReference = new WeakReference<>(activity);
        if (view == null) {
            view = View.inflate(weakReference.get(), R.layout.bui_tips_imagetext_dialog, null);
            iv_image = view.findViewById(R.id.iv_image);
            tv_text = view.findViewById(R.id.tv_text);
            bt_yes = view.findViewById(R.id.bt_yes);
            bt_no = view.findViewById(R.id.bt_no);
            builder = new AlertDialog.Builder(weakReference.get())
                    .setView(view);

        }

    }

    public TipsImageTextDialog setCancelable(boolean cancelable) {
        if (builder != null)
            builder.setCancelable(cancelable);
        return this;
    }

    public TipsImageTextDialog setImage(Object image) {
        LoadingImageUtil.loadingImag(image, iv_image, true);
        return this;
    }


    public TipsImageTextDialog setMessage(String message) {
        if (tv_text != null) {
            tv_text.setText(message);
        }
        return this;
    }


    public TipsImageTextDialog setButton1(String text, View.OnClickListener onClickListener) {

        if (bt_yes != null) {
            if (text != null) {
                bt_yes.setText(text);
            } else
                bt_yes.setVisibility(View.GONE);
            if (onClickListener != null) {
                bt_yes.setOnClickListener(onClickListener);
            } else {
                bt_yes.setVisibility(View.GONE);
            }
        }
        return this;
    }

    public TipsImageTextDialog setButton2(String text, View.OnClickListener onClickListener) {

        if (bt_no != null) {
            if (text != null) {
                bt_no.setText(text);
            } else
                bt_no.setVisibility(View.GONE);
            if (onClickListener != null) {
                bt_no.setOnClickListener(onClickListener);
            } else {
                bt_no.setVisibility(View.GONE);
            }
        }
        return this;
    }

    public TipsImageTextDialog create() {
        if (builder != null && weakReference != null && weakReference.get() != null) {
            if (!weakReference.get().isFinishing()) {
                tipsDialog = builder.create();
                tipsDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }


        }

        return this;
    }

    public TipsImageTextDialog show() {
        if (tipsDialog != null && weakReference != null && weakReference.get() != null) {
            if (!weakReference.get().isFinishing() && tipsDialog != null && !tipsDialog.isShowing()) {

                if (bt_no != null) {
                    if (StringUtils.isTrimEmpty(bt_no.getText().toString()))
                        bt_no.setVisibility(View.GONE);
                }

                if (bt_yes != null) {
                    if (StringUtils.isTrimEmpty(bt_yes.getText().toString()))
                        bt_yes.setVisibility(View.GONE);
                }

                tipsDialog.show();
            }

        }
        return this;
    }

    public TipsImageTextDialog dismiss() {
        if (tipsDialog != null && weakReference != null && weakReference.get() != null) {
            if (!weakReference.get().isFinishing() && tipsDialog != null && tipsDialog.isShowing())
                tipsDialog.dismiss();
        }

        return this;
    }


}
