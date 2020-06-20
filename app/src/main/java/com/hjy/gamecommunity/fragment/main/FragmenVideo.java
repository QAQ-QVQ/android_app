package com.hjy.gamecommunity.fragment.main;

import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.tablayout.TabLayoutX;
import com.hjy.gamecommunity.R;

/**
 * 视频
 * Author: zhangqingyou
 * Date: 2020/4/8 15:45
 * Des:
 */
public class FragmenVideo extends BaseFragment {
    private TabLayoutX mTabLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;

    }

    @Override
    public void initView(View mRootView) {
        mTabLayout = findViewById(R.id.tabLayout);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(true);
        }
    }

    @Override
    public void initData() {
        mTabLayout.addTab(mTabLayout.newTab().setText("直播"));
        mTabLayout.addTab(mTabLayout.newTab().setText("游戏视频"));
         mTabLayout.setTabHeight(-1);

    }

    @Override
    public void listener() {
        mTabLayout.addOnTabSelectedListener(new TabLayoutX.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayoutX.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayoutX.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayoutX.Tab tab) {

            }
        });
    }
}
