package com.hjy.gamecommunity.fragment.main;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baserequest.bean.FindBanner;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseui.ui.view.scrollview.ScrollInterceptScrollView;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.FragmentStatePageAdapter;
import com.hjy.gamecommunity.adapter.RealTimeInfoAdapter;
import com.hjy.gamecommunity.adapter.VideoAdapter;
import com.hjy.gamecommunity.fragment.FragmentCustomerServiceMsg;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.indicator.IndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 发现（首页）
 * Author: zhangqingyou
 * Date: 2020/4/8 15:45
 * Des:
 */
public class FragmentHome extends BaseFragment {
    private BannerViewPager mBannerView;
    private IndicatorView mIndicatorView;
    private SuperTextView mTvMoreVideo;
    private RecyclerView mRecyclerViewVideo;
    private SuperTextView mTvMoreRealTimeInfo;
    private RecyclerView mRecyclerViewRealTimeInfo;
    private ColorStateImageView mScivSearch;
    private VideoAdapter videoAdapter;
    private RealTimeInfoAdapter realTimeInfoAdapter;
    private ScrollInterceptScrollView mScrollInterceptScrollView;
    private ConstraintLayout mClSearch;
    private TextView mTvTitle;
    private ViewPager mViewPagerBannerFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    public void initView(View mRootView) {
        mClSearch = findViewById(R.id.cl_Search);//搜索区
        mTvTitle = findViewById(R.id.tv_Title);

        mScrollInterceptScrollView = findViewById(R.id.scrollInterceptScrollView);
        mViewPagerBannerFragment = findViewById(R.id.viewPagerBannerFragment);
        mScivSearch = findViewById(R.id.sciv_Search);
        mBannerView = findViewById(R.id.banner_view);
        mIndicatorView = findViewById(R.id.indicatorView);

        mTvMoreVideo = findViewById(R.id.tv_MoreVideo);
        mRecyclerViewVideo = findViewById(R.id.RecyclerViewVideo);
        mTvMoreRealTimeInfo = findViewById(R.id.tv_MoreRealTimeInfo);
        mRecyclerViewRealTimeInfo = findViewById(R.id.RecyclerViewRealTimeInfo);

        setPaddingNumTop(mClSearch, 12);
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

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void initData() {
        ViewSeting.setMeasuredHeight(mBannerView, 0.7f);
        //banner
        List<Object> mList = new ArrayList<>();
        mList.add(R.mipmap.home_banner_bg);
        mList.add(R.mipmap.home_banner_bg);
        mList.add(R.mipmap.home_banner_bg);

        // banner客服信息
        for (int i = 0; i < mList.size(); i++) {
            FragmentCustomerServiceMsg fragmentCustomerServiceMsg = new FragmentCustomerServiceMsg();
            fragmentCustomerServiceMsg.setData(i + 1);
            mFragments.add(fragmentCustomerServiceMsg);
        }

        FragmentStatePageAdapter fragmentStatePageAdapter = new FragmentStatePageAdapter(getChildFragmentManager(), mFragments);
        fragmentStatePageAdapter.setDestroyItem(false);
        mViewPagerBannerFragment.setAdapter(fragmentStatePageAdapter);
        //预加载页面数量的方法
        // mViewPager.setOffscreenPageLimit(mTitles.size());//tab数


        //banner图片
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
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        mViewPagerBannerFragment.setCurrentItem(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                })
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position) {

                    }
                }).create(mList);


        //设置搜索图标颜色及点击颜色
        mScivSearch.setImgColor(Color.WHITE);
        mScivSearch.setOnClickImgAlpha(0.5f);
        //（客服直播、游戏直播、游戏视频）Adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerViewVideo.setLayoutManager(linearLayoutManager);
        mRecyclerViewVideo.setNestedScrollingEnabled(false);
        videoAdapter = new VideoAdapter();
        mRecyclerViewVideo.setAdapter(videoAdapter);
        videoAdapter.replaceAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        //资讯Adapter
        mRecyclerViewRealTimeInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewRealTimeInfo.setNestedScrollingEnabled(false);
        realTimeInfoAdapter = new RealTimeInfoAdapter();
        mRecyclerViewRealTimeInfo.setAdapter(realTimeInfoAdapter);
        realTimeInfoAdapter.replaceAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));


        //获取banner
        Request.getInstance().findBanner(new JsonEntityCallback<FindBanner>(FindBanner.class) {
            @Override
            protected void onSuccess(FindBanner findBanner) {

            }
        });
    }

    @Override
    public void listener() {
        mScrollInterceptScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int scroll = 500;//在该距离内滑动改变搜索区域背景颜色

            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d("FragmentHome", "scrollY:" + scrollY);
//                Log.d("FragmentHome", "oldScrollY:" + oldScrollY);
                if (scrollY <= scroll) {
                    float alpha = scrollY / new Float(scroll - 50);
                    if (alpha < 0.2) {
                        mClSearch.setBackgroundColor(Color.TRANSPARENT);
                    } else if (alpha > 0.2 && alpha < 1) {
                        int alphaComponent = ColorUtils.setAlphaComponent(Color.WHITE, alpha);
                        mClSearch.setBackgroundColor(alphaComponent);
                    } else if (alpha > 1) {
                        mClSearch.setBackgroundColor(Color.WHITE);
                    }

                    if (scrollY > scroll / 3) {
                        setStatusBarLightMode(true);//深色状态栏
                        mScivSearch.setImgColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                        mScivSearch.setOnClickImgAlpha(0.7f);
                        mTvTitle.setVisibility(View.VISIBLE);
                    } else {
                        setStatusBarLightMode(false);//浅色状态栏
                        mScivSearch.setImgColor(Color.WHITE);
                        mScivSearch.setOnClickImgAlpha(0.7f);
                        mTvTitle.setVisibility(View.GONE);
                    }
                }


            }
        });


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

        //（客服直播、游戏直播、游戏视频）Adapter
        videoAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {

            }
        });
        //资讯Adapter
        realTimeInfoAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {

            }
        });
    }


}
