package com.hjy.gamecommunity.fragment.main;

import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.gamecommunity.R;

/**
 * 消息
 * Author: zhangqingyou
 * Date: 2020/4/8 15:46
 * Des:
 */
public class FragmenMessage extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(View mRootView) {

    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(true);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void listener() {

    }


}
