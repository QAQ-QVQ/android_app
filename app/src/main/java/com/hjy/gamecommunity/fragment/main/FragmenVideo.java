package com.hjy.gamecommunity.fragment.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.tablayout.TabLayoutX;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.FragmentStatePageAdapter;
import com.hjy.gamecommunity.fragment.FragmentGameVideoList;
import com.hjy.gamecommunity.fragment.FragmentLiveList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频
 * Author: zhangqingyou
 * Date: 2020/4/8 15:45
 * Des:
 */
public class FragmenVideo extends BaseFragment {
    private TabLayoutX mTabLayout;
    private ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;


    }

    @Override
    public void initView(View mRootView) {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(true);
        }
    }

    private Map<String, BaseFragment> fragmentMap = new LinkedHashMap<>();

    @Override
    public void initData() {
        FragmentLiveList fragmentLiveList = new FragmentLiveList();
        fragmentMap.put("直播", fragmentLiveList);
        FragmentGameVideoList fragmentGameVideoList = new FragmentGameVideoList();
        fragmentMap.put("游戏视频", fragmentGameVideoList);

        for (String title : fragmentMap.keySet()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.addAll(fragmentMap.values());
        FragmentStatePageAdapter fragmentStatePageAdapter = new FragmentStatePageAdapter(getChildFragmentManager(), fragmentList);
        fragmentStatePageAdapter.setDestroyItem(false);
        mViewPager.setAdapter(fragmentStatePageAdapter);
    }

    @Override
    public void listener() {
        mTabLayout.addOnTabSelectedListener(new TabLayoutX.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayoutX.Tab tab) {
                mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayoutX.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayoutX.Tab tab) {

            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mTabLayout.getTabAt(i).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
