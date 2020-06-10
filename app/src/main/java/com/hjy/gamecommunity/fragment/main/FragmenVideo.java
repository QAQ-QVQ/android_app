package com.hjy.gamecommunity.fragment.main;

import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.gamecommunity.R;

/**
 * 视频
 * Author: zhangqingyou
 * Date: 2020/4/8 15:45
 * Des:
 */
public class FragmenVideo extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
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
