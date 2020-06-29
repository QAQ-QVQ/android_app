package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;

/**
 * 我的—》设置-》个人资料-》昵称，个性签名
 * Date: 2020/6/28 15:22
 * Des:
 *
 * @author dy
 */
public class ActivityEditText extends BaseActivitySubordinate {
    /**
     * 标题
     */
    private String title;
    /**
     * 输入框
     */
    private MultiLineEditText multiLineEditText;
    /**
     * 消息
     */
    private String message;
    /**
     * 确定
     */
    private TextView submitTv;
    private ConstraintLayout toolbar;
    private boolean flag;

    @Override
    public Object getLayout() {
        return R.layout.activity_edit_text;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        multiLineEditText = findViewById(R.id.mine_edit);
        title = getIntent().getStringExtra("title");
        initToobar(this, title);
        submitTv = findViewById(R.id.tv_text_submit_bar);
//        multiLineEditText.getEditText().requestFocus();
//        multiLineEditText.getEditText().setFocusable(true);
//        toolbar = findViewById(R.id.toolbar);
//        setPaddingTop(toolbar);
    }

    @Override
    public void initData() {
        switch (title) {
            case "修改昵称":
                multiLineEditText.setMaxCount(10);
                multiLineEditText.setHintText("支持字母、数字、符号和中文");
                flag = true;
                break;
            case "个性签名":
                multiLineEditText.setMaxCount(300);
                multiLineEditText.setHintText("请添加个性签名");
                flag = false;
                break;
            default:
                break;
        }

    }

    @Override
    public void listener() {
        submitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                message = multiLineEditText.getContentText();
                intent.putExtra("message", message);
                if (flag) {
                    setResult(2001, intent);
                } else {
                    setResult(2002, intent);
                }
                finish();
            }
        });
    }
}
