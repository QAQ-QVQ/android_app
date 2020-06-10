package com.hjy.gamecommunity.fragment.main;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.indicator.IndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现（首页）
 * Author: zhangqingyou
 * Date: 2020/4/8 15:45
 * Des:
 */
public class FragmentHome extends BaseFragment {
    private BannerViewPager mBannerView;
    private AppBarLayout mAppbarLayout;
    private IndicatorView mIndicatorView;
    private RadiusImageView mRivCustomerServiceHead;
    private TextView mTvCustomerServiceTitle;
    private TextView mTvCustomerServiceText;
    private TextView mTvCustomerServiceDescribe;
    private LinearLayout mLlCustomerServiceMsg;
    private SuperTextView mTvMoreVideo;
    private RecyclerView mRecyclerViewVideo;
    private SuperTextView mTvMoreRealTimeInfo;
    private RecyclerView mRecyclerViewRealTimeInfo;
    private ColorStateImageView mScivSearch;
    private LinearLayout mLlSearch;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;


    }

    @Override
    public void initView(View mRootView) {
        mLlSearch = findViewById(R.id.ll_Search);//搜索区
        mScivSearch = findViewById(R.id.sciv_Search);
        mBannerView = findViewById(R.id.banner_view);
        mIndicatorView = findViewById(R.id.indicatorView);
        mRivCustomerServiceHead = findViewById(R.id.riv_CustomerServiceHead);
        mTvCustomerServiceTitle = findViewById(R.id.tv_CustomerServiceTitle);
        mTvCustomerServiceText = findViewById(R.id.tv_CustomerServiceText);
        mTvCustomerServiceDescribe = findViewById(R.id.tv_CustomerServiceDescribe);
        mLlCustomerServiceMsg = findViewById(R.id.ll_CustomerServiceMsg);
        mAppbarLayout = findViewById(R.id.appbar_layout);
        mTvMoreVideo = findViewById(R.id.tv_MoreVideo);
        mRecyclerViewVideo = findViewById(R.id.RecyclerViewVideo);
        mTvMoreRealTimeInfo = findViewById(R.id.tv_MoreRealTimeInfo);
        mRecyclerViewRealTimeInfo = findViewById(R.id.RecyclerViewRealTimeInfo);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(false);
            if (mBannerView != null)
                mBannerView.startLoop();
        } else {
            if (mBannerView != null)
                mBannerView.stopLoop();
        }

    }

    @Override
    public void initData() {
        ViewSeting.setMeasuredHeight(mBannerView, 0.7f);

        List<Object> mList = new ArrayList<>();
        mList.add(R.mipmap.home_banner_bg);
        mList.add(R.mipmap.home_banner_bg);
        mList.add(R.mipmap.home_banner_bg);
        mBannerView
                .setInterval(3000)//设置间隔 毫秒
                .setCanLoop(true)//设置可以循环
                .setAutoPlay(true)//设置自动播放
                // .setRoundCorner(20)//banner圆角
                .setIndicatorVisibility(View.VISIBLE)//指示器是否显示
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)//样式
                .setIndicatorSlideMode(IndicatorSlideMode.NORMAL)//模式
                .setIndicatorSliderRadius(ConvertUtils.dp2px(5))//指示点圆角
                .setIndicatorHeight(ConvertUtils.dp2px(5))//指示点高度
                .setIndicatorSliderWidth(ConvertUtils.dp2px(5), ConvertUtils.dp2px(12))//指示器未选中和选中的宽度
                .setIndicatorSliderColor(Color.WHITE, Color.WHITE)//指示器未选中和选中的颜色
                .setIndicatorGravity(IndicatorGravity.CENTER)//指示器位置
                .setIndicatorView(mIndicatorView)//设置自定义指示器
                .setHolderCreator(new HolderCreator() {
                    @Override
                    public ViewHolder createViewHolder() {
                        ViewHolder viewHolder = new ViewHolder<Integer>() {
                            @Override
                            public int getLayoutId() {
                                return R.layout.item_banner_layout;
                            }


                            @Override
                            public void onBind(View itemView, Integer img, int position, int size) {
                                ImageView mIvImage = itemView.findViewById(R.id.iv_image);
                                mIvImage.setImageResource(img);
                            }
                        };
                        return viewHolder;
                    }
                })
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position) {

                    }
                }).create(mList);

        mScivSearch.setImgColor(Color.WHITE);
        mScivSearch.setOnClickImgAlpha(0.5f);
    }

    @Override
    public void listener() {
        //搜索
        mScivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //更多视频
        mTvMoreVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //更多资讯
        mTvMoreRealTimeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
