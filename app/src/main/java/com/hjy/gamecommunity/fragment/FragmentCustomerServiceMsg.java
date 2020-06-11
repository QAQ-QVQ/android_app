package com.hjy.gamecommunity.fragment;

import android.view.View;
import android.widget.ImageView;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.gamecommunity.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 11:44
 * 描述: 发现-banner-描述
 */
public class FragmentCustomerServiceMsg extends BaseFragment {


    private ImageView mIvImage;

    @Override
    public int getLayoutId() {
        // return R.layout.fragment_banner_customer_service_msg_layout;
        return R.layout.image_layout;

    }

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public void initView(View mRootView) {
        mIvImage = findViewById(R.id.iv_image);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void initData() {
        LoadingImageUtil.loadingImag(data, mIvImage, true);
    }

    @Override
    public void listener() {

    }
}
