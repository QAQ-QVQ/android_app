package com.hjy.gamecommunity.activity.login;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.hjy.baserequest.RequestManage;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.bean.User;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseutil.UtilsManage;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 登录/注册
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/9
 * Des:
 */
public class ActivityPhoneLogin extends BaseActivity implements View.OnClickListener {
    private ImageButton mIbBackImageBar;
    private TextView mTvVisitorLogin;
    private android.support.constraint.ConstraintLayout mClBar;

    /**
     * 请输入手机号/帐号
     */
    private EditText mEdLoginPhone;
    private ImageButton mIbXx;


    /**
     * 登录
     */
    private Button mBtLogn;
    private EditText mEdVerificationCode;
    private ImageButton mIbXxVerificationCode;
    private Button mBtGetVerificationCode;
    private TextView mTvPasswordLogin;
    private TextView mTvServiceAgreement;
    private TextView mTvPrivacyClause;


    @Override
    public Object getLayout() {
        return R.layout.activity_phone_login;
    }

    @Override
    public void initView() {
        mClBar = (ConstraintLayout) findViewById(R.id.cl_bar);
        mIbBackImageBar = (ImageButton) findViewById(R.id.ib_back_image_bar);
        mIbBackImageBar.setOnClickListener(this);

        mTvVisitorLogin = (TextView) findViewById(R.id.tv_VisitorLogin);
        mTvVisitorLogin.setOnClickListener(this);

        mEdLoginPhone = (EditText) findViewById(R.id.ed_login_phone);
        mIbXx = (ImageButton) findViewById(R.id.ib_xx);
        mIbXx.setOnClickListener(this);
        mIbXx.setVisibility(View.GONE);

        mBtLogn = (Button) findViewById(R.id.bt_Logn);
        mBtLogn.setOnClickListener(this);

        mIbXxVerificationCode = (ImageButton) findViewById(R.id.ib_xxVerificationCode);
        mIbXxVerificationCode.setOnClickListener(this);
        mIbXxVerificationCode.setVisibility(View.GONE);
        mBtGetVerificationCode = (Button) findViewById(R.id.bt_GetVerificationCode);
        mBtGetVerificationCode.setOnClickListener(this);
        mTvPasswordLogin = (TextView) findViewById(R.id.tv_PasswordLogin);
        mTvPasswordLogin.setOnClickListener(this);
        mTvServiceAgreement = (TextView) findViewById(R.id.tv_ServiceAgreement);//服务协议
        mTvPrivacyClause = (TextView) findViewById(R.id.tv_PrivacyClause);//隐私条款
        mEdVerificationCode = (EditText) findViewById(R.id.ed_VerificationCode);//输入验证码

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
        setPaddingTop(mClBar);
    }


    @Override
    public void listener() {
        //手机号
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
                    mBtLogn.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mBtGetVerificationCode.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                } else {
                    mBtGetVerificationCode.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                    String mEdVerificationCodeString = mEdVerificationCode.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdVerificationCodeString)) {
                        mBtLogn.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                        mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
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
                    mBtLogn.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    mIbXxVerificationCode.setVisibility(View.GONE);
                } else {
                    mIbXxVerificationCode.setVisibility(View.VISIBLE);
                    String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                    if (RegexUtils.isMobileExact(mEdLoginPhoneString)) {
                        mBtLogn.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                        mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
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
            case R.id.ib_back_image_bar:
                finish();
                break;
            case R.id.ib_xx://清除手机号
                mEdLoginPhone.setText("");
                break;
            case R.id.ib_xxVerificationCode://清除验证码
                mEdVerificationCode.setText("");
                break;
            case R.id.tv_PasswordLogin://帐号密码  登录
                startActivity(new Intent(getContext(), ActivityPasswordLogin.class));
                break;
            case R.id.bt_GetVerificationCode://获取验证码
                String btString = mBtGetVerificationCode.getText().toString();
                if (btString.equals("获取验证码") || btString.equals("重新发送")) {
                    String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                    if (TextUtils.isEmpty(mEdLoginPhoneString)) {
                        UtilsManage.tost(mEdLoginPhone.getHint().toString());
                    } else if (!RegexUtils.isMobileExact(mEdLoginPhoneString)) {
                        UtilsManage.tost("请输入正确的手机号");
                    } else {
                        //获取短信验证码
                        Request.getInstance().smsVerificationCode(mEdLoginPhoneString, "reg", new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                            @Override
                            protected void onSuccess(DescAndCode descAndCode) {
                                if (descAndCode.getCode() == 200) {
                                    UtilsManage.tost("验证码获取成功");
                                } else {
                                    UtilsManage.tost(descAndCode.getMsg());
                                }
                            }
                        });
                        //60秒倒计时
                        countDown();

                        //倒计时中 设置不可编辑状态：
                        mEdLoginPhone.setFocusable(false);
                        mEdLoginPhone.setFocusableInTouchMode(false);
                        //倒计时中隐藏清除
                        mIbXx.setVisibility(View.GONE);
                    }


                }
                break;
            case R.id.bt_Logn://登录
                String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                String mEdVerificationCodeString = mEdVerificationCode.getText().toString();

                if (TextUtils.isEmpty(mEdLoginPhoneString)) {
                    UtilsManage.tost(mEdLoginPhone.getHint().toString());
                } else if (TextUtils.isEmpty(mEdVerificationCodeString)) {
                    UtilsManage.tost(mEdVerificationCode.getHint().toString());
                } else {
                    //手机号登录
                    Request.getInstance().phoneLogin(mEdLoginPhoneString, mEdVerificationCodeString, new JsonEntityCallback<User>(User.class) {
                        @Override
                        protected void onSuccess(User user) {
                            if (user.getCode() == 200) {
                                RequestManage.writeUserData(new Gson().toJson(user));
                                startActivity(new Intent(getContext(), MainActivity.class));
                                finish();
                            } else {
                                UtilsManage.tost(user.getMsg());
                            }
                        }
                    });
                }
                break;

            case R.id.tv_VisitorLogin://游客登录
                UtilsManage.tost("游客登录待开发");
                startActivity(new Intent(getContext(), MainActivity.class));
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
