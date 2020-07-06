package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjy.baserequest.bean.AccountInfoBean;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.enumclass.SmsEnum;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 我的-》设置 -》账号与安全 -》身份验证
 * Date: 2020/6/30 09:23
 * Des:
 *
 * @author dy
 */
public class ActivityVerification extends BaseActivitySubordinate {
    /**
     * 手机号
     */
    private TextView verificationPhone;
    /**
     * 输入框
     */
    private EditText verificationEdit;
    /**
     * 验证码
     */
    private Button verificationRegister;
    /**
     * 下一步
     */
    private Button verificationNext;
    @Override
    public Object getLayout() {
        return R.layout.activity_verification;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "身份验证");
        verificationPhone = findViewById(R.id.mine_verification_phone);
        verificationEdit = findViewById(R.id.mine_verification_ed);
        verificationRegister = findViewById(R.id.mine_verification_register);
        verificationNext = findViewById(R.id.mine_verification_next);
        verificationNext.setBackground(setCanClickDrawable());
        verificationRegister.setBackground(setNotClickDrawable());
    }

    @Override
    public void initData() {
        verificationPhone.setText(String.valueOf(UserDataContainer.getInstance().getUserData().getPhone()));
        Request.getInstance().smsVerificationCode(String.valueOf(UserDataContainer.getInstance().getUserData().getPhone()), SmsEnum.i().getKey(SmsEnum.VALUE4),verficationJsonEntityCallback );
        countDown();
    }

    @Override
    public void listener() {
        verificationNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String verification =  verificationEdit.getText().toString();
             if (verification.isEmpty()){
                 ToastUtil.tost("请输入正确的验证码");
             }else if (verification.length() >= 5){
                 Request.getInstance().editPassword(verification,editPasswordInfoJsonEntityCallback);
             }
            }
        });
        verificationRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request.getInstance().smsVerificationCode(String.valueOf(UserDataContainer.getInstance().getUserData().getPhone()), SmsEnum.i().getKey(SmsEnum.VALUE4),verficationJsonEntityCallback );
                countDown();
            }
        });

    }
    /**
     * 单背景样式
     *
     * @return
     */
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

    private StateListDrawable setNotClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_gray))
                .buid();
        return stateListDrawable;
    }

    private StateListDrawable setVerificationCanClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                .setColorBorder(ContextCompat.getColor(this, R.color.red_light))
                .setBorderWidth(1)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_white))
                .buid();
        return stateListDrawable;
    }

    private void setRegisterCanClick() {
        verificationRegister.setEnabled(true);
        verificationRegister.setBackground(setVerificationCanClickDrawable());
        verificationRegister.setTextColor(ContextCompat.getColor(this, R.color.red_light));
    }

    private void setRegisterNotClick() {
        verificationRegister.setEnabled(false);
        verificationRegister.setBackground(setNotClickDrawable());
        verificationRegister.setTextColor(ContextCompat.getColor(this, R.color.bui_white));
    }
    /**
     * 倒计时
     */
    private int time = 60;
    private TimerTask timerTask;
    private void countDown() {
        if (timerTask != null){
            timerTask.cancel();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                --time;
                if (time <= 1) {
                    cancel();
                    time = 60;
                    if (!getActivity().isFinishing()){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 设置可编辑状态：
                                setRegisterCanClick();
                                verificationRegister.setText("重新获取");
                            }
                        });
                    }

                } else {
                    if (!getActivity().isFinishing()){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setRegisterNotClick();
                                verificationRegister.setText(time + "s");
                            }
                        });
                    }
                }
            }
        };
        new Timer().schedule(timerTask, 100, 1000);
    }

    /**
     * 身份验证
     */
    JsonEntityCallback editPasswordInfoJsonEntityCallback = new JsonEntityCallback<AccountInfoBean>(AccountInfoBean.class){

        @Override
        protected void onSuccess(AccountInfoBean accountInfoBean) {
            if (accountInfoBean.getCode() == 200){
                startActivity(new Intent(getContext(), ActivitySetPassword.class));
                finish();
            }else {
                ToastUtil.tost(accountInfoBean.getMsg());
            }
        }
    };
    /**
     * 获取验证码
     */
    JsonEntityCallback verficationJsonEntityCallback= new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
        @Override
        protected void onSuccess(DescAndCode descAndCode) {
            if (descAndCode.getCode() == 200) {
                ToastUtil.tost("验证码获取成功");
            } else {
                ToastUtil.tost(descAndCode.getMsg());
            }
        }
    };
}
