package com.hjy.gamecommunity.fragment.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.login.ActivityPhoneLogin;

/**
 * 个人中心
 * Author: zhangqingyou
 * Date: 2020/4/8 15:47
 * Des:
 */
public class FragmenPersonalCenter extends BaseFragment implements View.OnClickListener {

    private TextView mTv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initView(View mRootView) {

        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(this);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void listener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                startActivity(new Intent(getContext(), ActivityPhoneLogin.class));
                break;
        }
    }
}
