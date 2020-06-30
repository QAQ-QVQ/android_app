package com.hjy.gamecommunity.activity.mine;

import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.gamecommunity.R;

/**
 * 我的-》设置 -》账号与安全 -》身份验证 -》设置密码
 * Date: 2020/6/30 09:23
 * Des:
 *
 * @author dy
 */
public class ActivitySetPassword extends BaseActivitySubordinate {
    /**
     * 新密码
     */
    private EditText newPassword;
    /**
     * 确认密码
     */
    private EditText confirmPassword;
    /**
     * 新密码显示隐藏
     */
    private ImageView newPasswordIv;
    /**
     * 确认密码显示隐藏
     */
    private ImageView confirmPasswordIv;
    /**
     * 提交
     */
    private Button submit;

    @Override
    public Object getLayout() {
        return R.layout.activity_set_password;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "设置密码");
        newPassword = findViewById(R.id.mine_password_new_ed);
        confirmPassword = findViewById(R.id.mine_password_confirm_ed);
        newPasswordIv = findViewById(R.id.mine_password_new_iv);
        confirmPasswordIv = findViewById(R.id.mine_password_confirm_iv);
        submit = findViewById(R.id.mine_password_next);
        submit.setBackground(setCanClickDrawable());
    }

    private boolean flagNew = false, flagConfirm = false;

    @Override
    public void initData() {
        confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void listener() {
        confirmPasswordIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagConfirm = !flagConfirm;
                if (flagConfirm) {
                    //密码可见
                    confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPasswordIv.setImageResource(R.mipmap.visibility_t);
                } else {
                    //密码不可见，
                    confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPasswordIv.setImageResource(R.mipmap.gone_t);
                }
            }
        });
        newPasswordIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagNew = !flagNew;
                if (flagNew) {
                    //密码可见
                    newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newPasswordIv.setImageResource(R.mipmap.visibility_t);
                } else {
                    //密码不可见，
                    newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newPasswordIv.setImageResource(R.mipmap.gone_t);
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/6/30 提交
                finish();
            }
        });
    }

    private StateListDrawable setCanClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                .setClickColorBg(0)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_red_light))
                .buid();
        return stateListDrawable;
    }
}
