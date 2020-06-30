package com.hjy.gamecommunity.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.AudioManager;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.bean.PhoneLoginUserBean;
import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.App;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.main.MainActivity;
import com.hjy.gamecommunity.dialog.ExitDialog;
import com.hjy.gamecommunity.enumclass.SmsEnum;
import com.hjy.gamecommunity.enumclass.TabEnum;

import java.util.List;
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
    private ColorStateImageView mIbBackImageBar;
    private SuperTextView mTvVisitorLogin;
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
        mIbBackImageBar = findViewById(R.id.iv_back_image_bar);
        mIbBackImageBar.setOnClickListener(this);
        mIbBackImageBar.setVisibility(View.GONE);
        mTvVisitorLogin = (SuperTextView) findViewById(R.id.tv_VisitorLogin);
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


        mBtLogn.setBackground(getStateListDrawable());
        mBtGetVerificationCode.setBackground(getStateListDrawable());

        exitDialog = new ExitDialog(getActivity());

    }

    /**
     * 单背景样式
     *
     * @return
     */
    private StateListDrawable getStateListDrawable() {
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)//设置点击后透明度
                .setRadius(50)//圆角
                .setColorBg(ContextCompat.getColor(getContext(), R.color.bui_gray))//背景颜色
                .buid();
        return stateListDrawable;
    }

    /**
     * 渐变样式
     *
     * @return
     */
    private StateListDrawable getGradientDrawable() {
        //状态2
        int startColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
        int endColor = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)//设置点击后透明度
                .setRadius(50)//圆角
                .setSColors(new int[]{startColor, endColor})
                .setSGradientType(GradientDrawable.LINEAR_GRADIENT)//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
                .setSOrientation(GradientDrawable.Orientation.LEFT_RIGHT)//渐变方向从左到右
                .buid();
        return stateListDrawable;
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
                    mBtLogn.setBackground(getStateListDrawable());
                    mBtGetVerificationCode.setBackground(getStateListDrawable());
                    mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                } else {
                    mBtGetVerificationCode.setBackground(getGradientDrawable());
                    String mEdVerificationCodeString = mEdVerificationCode.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdVerificationCodeString)) {
                        mBtLogn.setBackground(getGradientDrawable());
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
                    mBtLogn.setBackground(getStateListDrawable());
                    mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    mIbXxVerificationCode.setVisibility(View.GONE);
                } else {
                    mIbXxVerificationCode.setVisibility(View.VISIBLE);
                    String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                    if (RegexUtils.isMobileExact(mEdLoginPhoneString)) {
                        mBtLogn.setBackground(getGradientDrawable());
                        mBtLogn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数

            }
        });

        //服务协议

        mTvServiceAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //隐私条款
        mTvPrivacyClause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back_image_bar:
                exitDialog.show();
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
                        ToastUtil.tost(mEdLoginPhone.getHint().toString());
                    } else if (!RegexUtils.isMobileExact(mEdLoginPhoneString)) {
                        ToastUtil.tost("请输入正确的手机号");
                    } else {
                        //获取短信验证码
                        Request.getInstance().smsVerificationCode(mEdLoginPhoneString, SmsEnum.i().getKey(SmsEnum.VALUE1), new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                            @Override
                            protected void onSuccess(DescAndCode descAndCode) {
                                if (descAndCode.getCode() == 200) {
                                    ToastUtil.tost("验证码获取成功");
                                } else {
                                    ToastUtil.tost(descAndCode.getMsg());
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
                    ToastUtil.tost(mEdLoginPhone.getHint().toString());
                } else if (TextUtils.isEmpty(mEdVerificationCodeString)) {
                    ToastUtil.tost(mEdVerificationCode.getHint().toString());
                } else {
                    //手机号登录
                    Request.getInstance().phoneLogin(mEdLoginPhoneString, mEdVerificationCodeString, phoneLoginJsonEntityCallback);
                }
                break;

            case R.id.tv_VisitorLogin://游客登录
                //判断 Activity 是否存在栈中
                if (ActivityUtils.isActivityExistsInStack(MainActivity.class)) {
                    //存在栈中
                    List<Activity> activityList = ActivityUtils.getActivityList();
                    for (Activity activity : activityList) {
                        if (activity instanceof MainActivity) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            final MainActivity mainActivity = (MainActivity) activity;
                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mainActivity.selectTabView(TabEnum.VALUE1);
                                    finish();
                                }
                            }, 200);

                        }
                    }
                } else {
                    //不存在栈中
                    // Request.getInstance().visitorLogin(visitorLoginJsonEntityCallback);
                    startActivity(new Intent(getContext(), MainActivity.class));
                    finish();
                }

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

                                mBtGetVerificationCode.setBackground(getGradientDrawable());
                                mBtGetVerificationCode.setText("重新发送");
                            }
                        });
                } else {
                    if (!getActivity().isFinishing())
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBtGetVerificationCode.setBackground(getStateListDrawable());
                                mBtGetVerificationCode.setText(time + "s后重试");
                            }
                        });
                }
            }
        };
        new Timer().schedule(timerTask, 100, 1000);
    }


    /**
     * 登录成功后操作
     */
    private void loginSuccessful(UserData userData) {
        UserDataContainer.getInstance().setUser(userData);
        App.setAlias();
        startActivity(new Intent(getContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timerTask != null) timerTask.cancel();
    }


    private ExitDialog exitDialog;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d("LogUtils", "音量加");
                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.d("LogUtils", "音量减");
                AudioManager mAudioManager2 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                mAudioManager2.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
                return true;
            case KeyEvent.KEYCODE_BACK:
               // exitDialog.show();
                return true;
        }
        return true;
    }

    /**
     * 游客登录
     */
//    JsonEntityCallback visitorLoginJsonEntityCallback = new JsonEntityCallback<AccountsLoginUserBean>(AccountsLoginUserBean.class) {
//        @Override
//        protected void onSuccess(AccountsLoginUserBean accountsLoginUserBean) {
//            AccountsLoginUserBean.DataBean dataBean = accountsLoginUserBean.getData();
//            if (dataBean != null) {
//                UserData userData = new UserData(String.valueOf(dataBean.getUser_id()), dataBean.getToken());
//                loginSuccessful(userData);
//            } else {
//                ToastUtil.tost(accountsLoginUserBean.getMsg());
//            }
//        }
//    };

    /**
     * 手机号登录
     */
    JsonEntityCallback phoneLoginJsonEntityCallback = new JsonEntityCallback<PhoneLoginUserBean>(PhoneLoginUserBean.class) {
        @Override
        protected void onSuccess(PhoneLoginUserBean user) {
            if (user.getCode() == 200) {
                PhoneLoginUserBean.DataBean data = user.getData();
                if (data != null) {
                    List<PhoneLoginUserBean.DataBean.UserListBean> user_list = data.getUser_list();
                    if (user_list != null || user_list.size() == 0) {
                        if (user_list.size() == 1) {
                            PhoneLoginUserBean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                            UserData userData = new UserData(String.valueOf(userinfo.getUser_id()), userinfo.getToken());
                            loginSuccessful(userData);
                        } else {
                            //TODO 跳转到选择帐号登录  暂时不做
                        }

                    } else {
                        ToastUtil.tost("未查询到历史帐号");
                    }
                }

            } else {
                ToastUtil.tost(user.getMsg());
            }
        }
    };

}
