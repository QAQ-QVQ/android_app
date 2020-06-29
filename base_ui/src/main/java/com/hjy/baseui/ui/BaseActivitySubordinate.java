package com.hjy.baseui.ui;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjy.baseui.R;

/**
 * CREATED BY DY ON 2020/6/28.
 * TIME BY 10:14.
 *
 * @author DY
 **/
public abstract class BaseActivitySubordinate extends BaseActivity {
    /**
     * toobar
     */
    private LinearLayout toolbar;
    /**
     * 标题
     */
    private TextView toolbarTitle;
    /**
     * 返回
     */
    private ImageView toolbarBack;
    /**
     * 确定
     */
    private TextView toolbarSubmit;


    /**
     *
     * @param activity
     * @param title
     * @param submit
     */
    public void  initToobar(final Activity activity, String title, String submit, final OnSubmitClick onSubmitClick){
        toolbar = findViewById(R.id.ll_bar);
        toolbarTitle = findViewById(R.id.tv_text_bar);
        toolbarBack = findViewById(R.id.iv_back_image_bar);
//        toolbarSubmit = findViewById(R.id.tv_text_submit_bar);
        setPaddingTop(toolbar);
        if (!submit.isEmpty()){
            toolbarSubmit.setText(submit);
        }
        toolbarTitle.setText(title);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        toolbarSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClick.Submit();
            }
        });
    }

    public void  initToobar(final Activity activity,String title){
        toolbar = findViewById(R.id.ll_bar);
        toolbarTitle = findViewById(R.id.tv_text_bar);
        toolbarBack = findViewById(R.id.iv_back_image_bar);
//        setPaddingTop(toolbar);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        toolbarTitle.setText(title);
    }

   protected interface OnSubmitClick{
        void Submit();
    }
}
