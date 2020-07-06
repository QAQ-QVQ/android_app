package com.hjy.gamecommunity.fragment.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.popup.tips.LoadPopup;
import com.hjy.baseui.ui.view.tablayout.TabLayoutX;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.FragmentStatePageAdapter;
import com.hjy.gamecommunity.fragment.message.FragmentFamilyMsg;
import com.hjy.gamecommunity.fragment.message.FragmentInteractMsg;
import com.hjy.gamecommunity.fragment.message.FragmentOfficialMsg;
import com.hjy.gamecommunity.utils.GenerateTestUserSig;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMManager;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息
 * Author: zhangqingyou
 * Date: 2020/4/8 15:46
 * Des:
 */
public class FragmenMessage extends BaseFragment {
    private TabLayoutX mTabLayout;
    private ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;

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
        FragmentFamilyMsg fragmentFamilyMsg = new FragmentFamilyMsg();
        fragmentMap.put("家族", fragmentFamilyMsg);
        FragmentOfficialMsg fragmentOfficialMsg = new FragmentOfficialMsg();
        fragmentMap.put("官方", fragmentOfficialMsg);
        FragmentInteractMsg fragmentInteractMsg = new FragmentInteractMsg();
        fragmentMap.put("互动", fragmentInteractMsg);

        for (String title : fragmentMap.keySet()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.addAll(fragmentMap.values());
        FragmentStatePageAdapter fragmentStatePageAdapter = new FragmentStatePageAdapter(getChildFragmentManager(), fragmentList);
        fragmentStatePageAdapter.setDestroyItem(false);

        UserData userData = UserDataContainer.getInstance().getUserData();
        if (userData != null) {
            LoadPopup loadPopup = new LoadPopup(getActivity());
            loadPopup.setText("IM登录中...");
            loadPopup.show(mViewPager);

            String user_id = userData.getUser_id();
            String genTestUserSig = GenerateTestUserSig.genTestUserSig(user_id);
            V2TIMManager.getInstance().login(user_id, genTestUserSig, new V2TIMCallback() {
                @Override
                public void onError(int i, String s) {
                    ToastUtils.toast("IM登录失败！-" + s);
                    loadPopup.dismiss();
                }

                @Override
                public void onSuccess() {
                    mViewPager.setAdapter(fragmentStatePageAdapter);
                    loadPopup.dismiss();
                }
            });
        }

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
