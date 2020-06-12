package com.hjy.gamecommunity.activity.login;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.StringUtils;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;

/**
 * 设置新密码
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/10
 * Des:
 */
public class ActivityPasswordSetNewPassword extends BaseActivity implements View.OnClickListener {
    private ImageButton mIbXxPassword;
    private ColorStateImageView mIbBackImageBar;
    private LinearLayout mLlBar;
    private EditText mEdNewPassword;
    private ImageButton mIbIsVisibilityPassword;
    private EditText mEdAgainPassword;
    private ImageButton mIbXxAgainPassword;
    private ImageButton mIbIsVisibilityAgainPassword;
    private Button mBtComplete;


    @Override
    public Object getLayout() {
        return R.layout.activity_set_new_password;

    }

    @Override
    public void initView() {
        mIbBackImageBar = findViewById(R.id.iv_back_image_bar);
        mLlBar = findViewById(R.id.ll_bar);
        mEdNewPassword = findViewById(R.id.ed_NewPassword);
        mIbXxPassword = findViewById(R.id.ib_xxPassword);
        mIbIsVisibilityPassword = findViewById(R.id.ib_IsVisibilityPassword);
        mEdAgainPassword = findViewById(R.id.ed_AgainPassword);
        mIbXxAgainPassword = findViewById(R.id.ib_xxAgainPassword);
        mIbIsVisibilityAgainPassword = findViewById(R.id.ib_IsVisibilityAgainPassword);
        mBtComplete = findViewById(R.id.bt_Complete);

        mIbBackImageBar.setOnClickListener(this);

        mIbXxPassword.setOnClickListener(this);
        mIbXxPassword.setVisibility(View.GONE);


        mIbIsVisibilityPassword.setOnClickListener(this);
        mIbIsVisibilityAgainPassword.setOnClickListener(this);

        mBtComplete.setOnClickListener(this);

        mIbXxAgainPassword.setOnClickListener(this);
        mIbXxAgainPassword.setVisibility(View.GONE);


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
        mEdNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s:变化后的所有字符

                if (StringUtils.isTrimEmpty(s.toString())) {
                    mBtComplete.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mBtComplete.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    mIbIsVisibilityPassword.setVisibility(View.GONE);
                } else {
                    mIbIsVisibilityPassword.setVisibility(View.VISIBLE);

                    String mEdPasswordString = mEdAgainPassword.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdPasswordString)) {
                        mBtComplete.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                        mBtComplete.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数

            }
        });

        //密码
        mEdAgainPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s:变化后的所有字符

                if (StringUtils.isTrimEmpty(s.toString())) {
                    mBtComplete.setBackgroundResource(R.drawable.bui_gray_solid_corners_style);
                    mBtComplete.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    mIbXxAgainPassword.setVisibility(View.GONE);
                } else {
                    mIbXxAgainPassword.setVisibility(View.VISIBLE);

                    String mEdLoginPhoneString = mEdNewPassword.getText().toString();
                    if (!StringUtils.isTrimEmpty(mEdLoginPhoneString)) {
                        mBtComplete.setBackgroundResource(R.drawable.bui_colorprimary_gradual_corners_style);
                        mBtComplete.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
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
    private boolean isVisibilityAgainPassword;//密码是否可见   默认不可见

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back_image_bar:
                finish();
                break;
            case R.id.ib_xxPassword://清除密码
                mEdNewPassword.setText("");
                break;
            case R.id.ib_xxAgainPassword://清除密码
                mEdAgainPassword.setText("");
                break;
            case R.id.ib_IsVisibilityPassword://密码是否可见
                isVisibility = !isVisibility;
                if (isVisibility) {
                    //密码可见
                    mEdNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIbIsVisibilityPassword.setImageResource(R.mipmap.visibility_t);
                } else {
                    //密码不可见，
                    mEdNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIbIsVisibilityPassword.setImageResource(R.mipmap.gone_t);
                }
                break;
            case R.id.ib_IsVisibilityAgainPassword://密码是否可见
                isVisibilityAgainPassword = !isVisibilityAgainPassword;
                if (isVisibilityAgainPassword) {
                    //密码可见
                    mEdAgainPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIbIsVisibilityAgainPassword.setImageResource(R.mipmap.visibility_t);
                } else {
                    //密码不可见，
                    mEdAgainPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIbIsVisibilityAgainPassword.setImageResource(R.mipmap.gone_t);
                }
                break;

            case R.id.bt_Complete://完成
                String mEdNewPasswordString = mEdNewPassword.getText().toString();
                String mEdAgainPasswordString = mEdAgainPassword.getText().toString();

                if (TextUtils.isEmpty(mEdNewPasswordString)) {
                    ToastUtil.tost(mEdNewPassword.getHint().toString());
                } else if (TextUtils.isEmpty(mEdAgainPasswordString)) {
                    ToastUtil.tost(mEdAgainPassword.getHint().toString());
                } else if (!mEdNewPasswordString.equals(mEdAgainPasswordString)) {
                    ToastUtil.tost("两次密码不一致！");
                } else {
                    //重置密码
                    Request.getInstance().resetPassword(mEdAgainPasswordString, new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                        @Override
                        protected void onSuccess(DescAndCode descAndCode) {
                            if (descAndCode.getCode() == 200) {
                                ToastUtil.tost("密码设置成功！");
                                startActivity(new Intent(getContext(), ActivityPasswordLogin.class));
                                finish();
                            } else {
                                ToastUtil.tost(descAndCode.getMsg());
                            }
                        }
                    });
                }
                break;


        }
    }


}
