package com.hjy.gamecommunity.fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseutil.PermissionUtils;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.login.ActivityPhoneLogin;
import com.hjy.gamecommunity.activity.main.MainActivity;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.transform.DepthTransformer;
import com.xuexiang.xui.widget.banner.transform.FadeSlideTransformer;
import com.xuexiang.xui.widget.banner.transform.FlowTransformer;
import com.xuexiang.xui.widget.banner.transform.RotateDownTransformer;
import com.xuexiang.xui.widget.banner.transform.RotateUpTransformer;
import com.xuexiang.xui.widget.banner.transform.ZoomOutSlideTransformer;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;

import java.util.ArrayList;
import java.util.List;


/**
 * app引导
 * Author: zhangqingyou
 * Date: 2020/4/8
 * Des:
 */
public class GuideFragment extends BaseFragment {

    private SimpleGuideBanner sgb;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_guide;
    }


    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void initView(View mRootView) {

    }

    @Override
    public void initData() {
        Class<? extends ViewPager.PageTransformer> transformerClass = transformers[0];

        sgb = findViewById(R.id.sgb);

        sgb
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(transformerClass)
                .barPadding(0, 10, 0, 10)
                .setSource(getUsertGuides())
                .startScroll();
    }

    @Override
    public void listener() {
        sgb.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                checkPermissions();
            }
        });
    }


    public static Class<? extends ViewPager.PageTransformer> transformers[] = new Class[]{
            DepthTransformer.class,
            FadeSlideTransformer.class,
            FlowTransformer.class,
            RotateDownTransformer.class,
            RotateUpTransformer.class,
            ZoomOutSlideTransformer.class,
    };

    public static List<Object> getUsertGuides() {
        List<Object> list = new ArrayList<>();
        list.add(R.mipmap.guide_img_1);
        list.add(R.mipmap.guide_img_2);
        list.add(R.mipmap.guide_img_3);
        list.add(R.mipmap.guide_img_4);
        return list;
    }

    private void checkPermissions() {
        String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        PermissionUtils.request("读写手机存储,读取手机状态!", permissions, new PermissionUtils.PermissionCallback() {
            @Override
            public void onGranted() {
                startActivity(new Intent(getContext(), ActivityPhoneLogin.class));
                getActivity().finish();
            }
        });
    }


}
