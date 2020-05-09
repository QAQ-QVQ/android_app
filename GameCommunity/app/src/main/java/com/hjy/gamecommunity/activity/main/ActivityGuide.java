package com.hjy.gamecommunity.activity.main;

import android.view.View;

import com.hjy.baseui.ui.BaseActivity;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.fragment.GuideFragment;

/**
 * Author: zhangqingyou
 * Date: 2020/4/9
 * Des:
 */
public class ActivityGuide extends BaseActivity implements View.OnClickListener {


    @Override
    public Object getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {


        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(false);//设置状态栏是否为浅色模式

        showFragment(R.id.ll_LinearLayout, GuideFragment.class);

    }

    @Override
    public void listener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }


}
