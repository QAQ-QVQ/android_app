package com.hjy.gamecommunity.activity.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 手机验证
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/9 15:46
 * Des:
 */
public class ActivityGetVerificationCode extends BaseActivity implements View.OnClickListener {

    /**
     * 请输入手机号
     */
    private EditText mEdLoginPhone;
    private EditText mEdVerificationCode;
    private ImageButton mIbXxVerificationCode;
    private Button mBtGetVerificationCode;

    private Button mBtNextStep;
    private ImageButton mIbXx;
    private ColorStateImageView mIbBackImageBar;
    private LinearLayout mLlBar;


    @Override
    public Object getLayout() {
        return R.layout.activity_verification_code_login;

    }


    @Override
    public void initView() {
        mLlBar = (LinearLayout) findViewById(R.id.ll_bar);
        mIbBackImageBar = (ColorStateImageView) findViewById(R.id.iv_back_image_bar);
        mIbBackImageBar.setOnClickListener(this);


        mEdLoginPhone = (EditText) findViewById(R.id.ed_login_phone);


        mEdVerificationCode = findViewById(R.id.ed_VerificationCode);
        mIbXxVerificationCode = (ImageButton) findViewById(R.id.ib_xxVerificationCode);
        mIbXxVerificationCode.setOnClickListener(this);
        mIbXxVerificationCode.setVisibility(View.GONE);
        mBtGetVerificationCode = (Button) findViewById(R.id.bt_GetVerificationCode);
        mBtGetVerificationCode.setOnClickListener(this);

        mBtNextStep = (Button) findViewById(R.id.bt_NextStep);
        mBtNextStep.setOnClickListener(this);
        mIbXx = (ImageButton) findViewById(R.id.ib_xx);
        mIbXx.setOnClickListener(this);
        mIbXx.setVisibility(View.GONE);


//        CountdownTimerTask countdownTimerTask = new CountdownTimerTask();
//        countdownTimerTask.stateCountdownExecution(new Runnable() {
//            @Override
//            public void run() {
//                mEdLoginPhone.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(mEdLoginPhone, InputMethodManager.SHOW_FORCED);
//            }
//        }, 0.5f);


    }

    @Override
    public void initData() {
        transparentStatusBar();
        setStatusBarLightMode(true);
        setPaddingTop(mLlBar);
    }

    @Override
    public void listener() {
        //为EditText设置监听，注意监听类型为TextWatcher
        mEdLoginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isTrimEmpty(s.toString())) {
                    mIbXx.setVisibility(View.GONE);
                } else {
                    mIbXx.setVisibility(View.VISIBLE);
                }
                //s:变化后的所有字符
                if (s.toString().length() < 11) {
                    mBtNextStep.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mBtGetVerificationCode.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                } else {
                    mBtGetVerificationCode.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);

                    String mEdVerificationCodeString = mEdVerificationCode.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdVerificationCodeString)) {
                        mBtNextStep.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数

            }
        });
        //验证码
        mEdVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s:变化后的所有字符
                if (StringUtils.isTrimEmpty(s.toString())) {
                    mBtNextStep.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mIbXxVerificationCode.setVisibility(View.GONE);
                } else {
                    mIbXxVerificationCode.setVisibility(View.VISIBLE);
                    String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                    if (RegexUtils.isMobileExact(mEdLoginPhoneString)) {
                        mBtNextStep.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_GetVerificationCode://获取验证码
                String btString = mBtGetVerificationCode.getText().toString();
                if (btString.equals("获取验证码") || btString.equals("重新发送")) {
                    String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                    if (TextUtils.isEmpty(mEdLoginPhoneString)) {
                        ToastUtil.tost(mEdLoginPhone.getHint().toString());
                    } else if (!RegexUtils.isMobileExact(mEdLoginPhoneString)) {
                        ToastUtil.tost("请输入正确的手机号");
                    } else {
                        //获取短信验证码
                        Request.getInstance().smsVerificationCode(mEdLoginPhoneString, "app_forgetPassword", new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                            @Override
                            protected void onSuccess(DescAndCode descAndCode) {
                                if (descAndCode.getCode() == 200) {
                                    ToastUtil.tost("验证码获取成功");
                                } else {
                                    ToastUtil.tost(descAndCode.getMsg());
                                }
                            }
                        });
                        countDown();

                        //倒计时中 设置不可编辑状态：
                        mEdLoginPhone.setFocusable(false);
                        mEdLoginPhone.setFocusableInTouchMode(false);
                        //倒计时中 隐藏清除
                        mIbXx.setVisibility(View.GONE);
                    }

                }
                break;
            case R.id.bt_NextStep://下一步
                String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                String mEdVerificationCodeString = mEdVerificationCode.getText().toString();

                if (TextUtils.isEmpty(mEdLoginPhoneString)) {
                    ToastUtil.tost(mEdLoginPhone.getHint().toString());
                } else if (TextUtils.isEmpty(mEdVerificationCodeString)) {
                    ToastUtil.tost(mEdVerificationCode.getHint().toString());
                } else {
                    //手机验证
                    Request.getInstance().phoneVerification(mEdLoginPhoneString, mEdVerificationCodeString, new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                        @Override
                        protected void onSuccess(DescAndCode descAndCode) {
                            if (descAndCode.getCode() == 200) {
                                //验证成功跳转到下一步 重置密码
                                startActivity(new Intent(getContext(), ActivityPasswordSetNewPassword.class));
                                finish();
                            } else {
                                ToastUtil.tost(descAndCode.getMsg());
                            }
                        }
                    });

                }
                break;
            case R.id.ib_xx:
                mEdLoginPhone.setText("");
                break;
            case R.id.iv_back_image_bar:
                finish();
                break;

        }
    }

    /**
     * 倒计时
     */
    private int time = 60;
    private TimerTask timerTask;

    private void countDown() {
        if (timerTask != null) timerTask.cancel();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                --time;
                if (time <= 1) {
                    cancel();
                    time = 60;
                    if (!getActivity().isFinishing())
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 设置可编辑状态：
                                mEdLoginPhone.setFocusable(true);
                                mEdLoginPhone.setFocusableInTouchMode(true);
                                mIbXx.setVisibility(View.VISIBLE);

                                mBtGetVerificationCode.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                                mBtGetVerificationCode.setText("重新发送");
                            }
                        });
                } else {
                    if (!getActivity().isFinishing())
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBtGetVerificationCode.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                                mBtGetVerificationCode.setText(time + "s后重试");
                            }
                        });
                }
            }
        };
        new Timer().schedule(timerTask, 100, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timerTask != null) timerTask.cancel();
    }
}
