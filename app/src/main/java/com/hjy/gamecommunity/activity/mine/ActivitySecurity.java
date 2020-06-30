package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.gamecommunity.R;
/**
 * 我的-》设置 -》账号与安全
 * Date: 2020/6/30 09:23
 * Des:
 *
 * @author dy
 */
public class ActivitySecurity extends BaseActivitySubordinate {
    /**
     * 手机号码
     */
    private TextView securityPhoneNumber;
    /**
     * 是否设置
     */
    private TextView securityPasswordTv;
    /**
     * 手机容器
     */
    private LinearLayout securityPhone;
    /**
     * 密码容器
     */
    private LinearLayout securityPassword;

    @Override
    public Object getLayout() {
        return R.layout.activity_security;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "账号与安全");
        securityPhoneNumber = findViewById(R.id.mine_security_phone_num);
        securityPhone = findViewById(R.id.mine_security_phone);
        securityPassword = findViewById(R.id.mine_security_password);
        securityPasswordTv = findViewById(R.id.mine_security_password_num);
    }

    @Override
    public void initData() {
        // TODO: 2020/6/30 设置密码
        securityPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityVerification.class));
            }
        });
    }

    @Override
    public void listener() {

    }
}
