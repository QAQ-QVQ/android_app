package com.hjy.gamecommunity.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.hjy.baserequest.bean.FindBanner;
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

    private FindBanner.DataBean.ListBean listBean;

    public void setData(FindBanner.DataBean.ListBean listBean) {
        this.listBean = listBean;
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
        if (listBean != null)
            LoadingImageUtil.loadingImag(listBean.getSecond_resource(), mIvImage, true);
    }

    @Override
    public void listener() {
        mIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean.getIs_jump() == 1) {
                    openUrl(listBean.getLink_url());
                }
            }
        });
    }

    /**
     * 跳转链接
     *
     * @param url
     */
    private void openUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}
