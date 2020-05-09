package com.hjy.gamecommunity.activity.login;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.hjy.baserequest.RequestManage;
import com.hjy.baserequest.bean.User;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseutil.UtilsManage;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.main.MainActivity;

/**
 * 帐号密码登录
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/9 15:46
 * Des:
 */
public class ActivityPasswordLogin extends BaseActivity implements View.OnClickListener {


    /**
     * 请输入手机号/帐号
     */
    private EditText mEdLoginPhone;
    private ImageButton mIbXx;
    /**
     * 请输入密码
     */
    private EditText mEdPassword;
    private ImageButton mIbIsVisibility;
    /**
     * 忘记密码
     */
    private TextView mTvForgetPassword;

    /**
     * 登录
     */
    private Button mBtLogn;

    private ImageButton mIbXxPassword;
    private ImageButton mIbBackImageBar;
    private android.widget.LinearLayout mLlBar;


    @Override
    public Object getLayout() {
        return R.layout.activity_password_login;
    }

    @Override
    public void initView() {
        mLlBar = (LinearLayout) findViewById(R.id.ll_bar);
        mIbBackImageBar = (ImageButton) findViewById(R.id.ib_back_image_bar);
        mIbBackImageBar.setOnClickListener(this);

        mEdLoginPhone = (EditText) findViewById(R.id.ed_login_phone);
        mIbXx = (ImageButton) findViewById(R.id.ib_xx);
        mIbXx.setOnClickListener(this);
        mIbXx.setVisibility(View.GONE);
        mEdPassword = (EditText) findViewById(R.id.ed_Password);
        mIbIsVisibility = (ImageButton) findViewById(R.id.ib_IsVisibility);
        mIbIsVisibility.setOnClickListener(this);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_ForgetPassword);
        mTvForgetPassword.setOnClickListener(this);

        mBtLogn = (Button) findViewById(R.id.bt_Logn);
        mBtLogn.setOnClickListener(this);

        mIbXxPassword = (ImageButton) findViewById(R.id.ib_xxPassword);
        mIbXxPassword.setOnClickListener(this);
        mIbXxPassword.setVisibility(View.GONE);

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
        //手机号
        mEdLoginPhone.addTextChangedListener(new TextWatcher() {
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
                    mIbXx.setVisibility(View.GONE);
                } else {
                    mIbXx.setVisibility(View.VISIBLE);

                    String mEdPasswordString = mEdPassword.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdPasswordString)) {
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

        //密码
        mEdPassword.addTextChangedListener(new TextWatcher() {
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
                    mIbXxPassword.setVisibility(View.GONE);
                } else {
                    mIbXxPassword.setVisibility(View.VISIBLE);

                    String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdLoginPhoneString)) {
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


    private boolean isVisibility;//密码是否可见   默认不可见

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
            case R.id.ib_xxPassword://清除密码
                mEdPassword.setText("");
                break;
            case R.id.ib_IsVisibility://密码是否可见
                isVisibility = !isVisibility;
                if (isVisibility) {
                    //密码可见
                    mEdPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIbIsVisibility.setImageResource(R.mipmap.visibility_t);
                } else {
                    //密码不可见，
                    mEdPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    mIbIsVisibility.setImageResource(R.mipmap.gone_t);
                }
                break;
            case R.id.tv_ForgetPassword://忘记密码
                startActivity(new Intent(getContext(), ActivityGetVerificationCode.class));
                break;
            case R.id.bt_Logn://登录
                String mEdLoginPhoneString = mEdLoginPhone.getText().toString();
                String mEdPasswordString = mEdPassword.getText().toString();

                if (StringUtils.isTrimEmpty(mEdLoginPhoneString)) {
                    UtilsManage.tost(mEdLoginPhone.getHint().toString());
                } else if (StringUtils.isTrimEmpty(mEdPasswordString)) {
                    UtilsManage.tost("请输入密码");
                } else if (mEdPasswordString.length() < 6) {
                    UtilsManage.tost("请输入6-18位密码");
                } else {
                    //账号密码登录
                    Request.getInstance().accountPasswordLogin(mEdLoginPhoneString, mEdPasswordString, new JsonEntityCallback<User>(User.class) {
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


        }
    }


}
