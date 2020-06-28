package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;

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
    private String message;

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
        initToobar(this, title, "完成", new OnSubmitClick() {
            @Override
            public void Submit() {
//                Intent intent = new Intent(getContext(), ActivityUserMessage.class);
//                if (!message.isEmpty()){
//                    intent.putExtra("message", message);
//                }
//                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        switch (title) {
            case "修改昵称":
                multiLineEditText.setMaxCount(10);
                multiLineEditText.setHintText("支持字母、数字、符号和中文");
                message = multiLineEditText.getContentText();
                break;
            case "个性签名":
                multiLineEditText.setMaxCount(300);
                multiLineEditText.setHintText("请添加个性签名");
                message = multiLineEditText.getContentText();
                break;
            default:
                break;
        }

    }

    @Override
    public void listener() {

    }
}
