package com.hjy.gamecommunity.fragment.main;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.hjy.baserequest.bean.AnchorAndVideoList;
import com.hjy.baserequest.bean.FindBanner;
import com.hjy.baserequest.bean.NewsList;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.news.ActivityNewsDetails;
import com.hjy.gamecommunity.activity.news.ActivityNewsList;
import com.hjy.gamecommunity.activity.search.ActivitySearch;
import com.hjy.gamecommunity.adapter.FindNewsAdapter;
import com.hjy.gamecommunity.adapter.FindVideoAdapter;
import com.hjy.gamecommunity.adapter.FragmentStatePageAdapter;
import com.hjy.gamecommunity.fragment.FragmentCustomerServiceMsg;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
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
    private IndicatorView mIndicatorView;
    private SuperTextView mTvMoreVideo;
    private RecyclerView mRecyclerViewVideo;
    private SuperTextView mTvMoreRealTimeInfo;
    private RecyclerView mRecyclerViewRealTimeInfo;
    private ColorStateImageView mScivSearch;
    private FindVideoAdapter videoAdapter;
    private FindNewsAdapter findNewsAdapter;
    private NestedScrollView mScrollInterceptScrollView;
    private ConstraintLayout mClSearch;
    private TextView mTvTitle;
    private ViewPager mViewPagerBannerFragment;
    private SmartRefreshLayout mSrlRealTimeInfo;

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

        mSrlRealTimeInfo = findViewById(R.id.srl_RealTimeInfo);

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

    // banner客服信息
    private List<FindBanner.DataBean.ListBean> bannerDataList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> mList = new ArrayList<>();

    @Override
    public void initData() {
        ViewSeting.setMeasuredHeight(mBannerView, 0.7f);

        //banner
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
                        ViewHolder viewHolder = new ViewHolder<Object>() {
                            @Override
                            public int getLayoutId() {
                                return R.layout.item_banner_layout;
                            }


                            @Override
                            public void onBind(View itemView, Object img, int position, int size) {
                                ImageView mIvImage = itemView.findViewById(R.id.iv_image);
                                LoadingImageUtil.loadingImag(img, mIvImage, true);
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
                        FindBanner.DataBean.ListBean listBean = bannerDataList.get(position);
                        if (listBean.getIs_jump() == 1) {
                            openUrl(listBean.getLink_url());
                        }
                    }
                });


        //设置搜索图标颜色及点击颜色
        mScivSearch.setImgColor(Color.WHITE);
        mScivSearch.setOnClickImgAlpha(0.5f);
        //（客服直播、游戏直播、游戏视频）Adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerViewVideo.setLayoutManager(linearLayoutManager);
        mRecyclerViewVideo.setNestedScrollingEnabled(false);
        videoAdapter = new FindVideoAdapter(linearLayoutManager);
        mRecyclerViewVideo.setAdapter(videoAdapter);

        //资讯Adapter
        mRecyclerViewRealTimeInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewRealTimeInfo.setNestedScrollingEnabled(false);
        findNewsAdapter = new FindNewsAdapter();
        mRecyclerViewRealTimeInfo.setAdapter(findNewsAdapter);


        //设置 Header 样式
        MaterialHeader materialHeader = new MaterialHeader(getContext());
        materialHeader.setProgressBackgroundColorSchemeResource(android.R.color.transparent);
        materialHeader.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mSrlRealTimeInfo.setRefreshHeader(materialHeader);
        //设置 Footer  样式
        mSrlRealTimeInfo.setRefreshFooter(new ClassicsFooter(getContext()));
        mSrlRealTimeInfo.setEnableHeaderTranslationContent(false);//是否下拉Header的时候向下平移列表或者内容
        mSrlRealTimeInfo.setEnableAutoLoadMore(false);//是否启用列表惯性滑动到底部时自动加载更多
        //设置刷新加载时禁止所有列表操作
        mSrlRealTimeInfo.setDisableContentWhenRefresh(true);
        mSrlRealTimeInfo.setDisableContentWhenLoading(true);
        mSrlRealTimeInfo.autoRefresh();//自动刷新
    }

    //private int pageVideo = 1, limitVideo = 10;//视频列表 >  页数 - 页大小
    private int pageNews = 1, limitNews = 10;//资讯列表 >  页数 - 页大小

    @Override
    public void listener() {
        mSrlRealTimeInfo.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //获取banner
                Request.getInstance().findBanner(bannerJsonEntityCallback);

                // 发现-（客服/游戏主播& 视频）
                Request.getInstance().anchorAndVideoList(anchorAndVideoListJsonEntityCallback);

                //发现-资讯列表
                Request.getInstance().newsList(pageNews = 1, limitNews = 10, newsListJsonEntityCallback);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //发现-资讯列表
                Request.getInstance().newsList(++pageNews, limitNews = 10, newsListJsonEntityCallback);
            }


        });


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
                startActivity(new Intent(getContext(), ActivitySearch.class));
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
                startActivity(new Intent(getContext(), ActivityNewsList.class));
            }
        });
        //客服直播、游戏直播、游戏视频  \滑动监听
//        mRecyclerViewVideo.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            //用来标记是否正在向最后一个滑动
//            boolean isSlidingToLast = false;
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                // 当不滚动时
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    //获取最后一个完全显示的ItemPosition
//                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//                    int totalItemCount = manager.getItemCount();
//                    // 判断是否滚动到底部，并且是向右滚动
//                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
//                        //加载更多功能的代码
//                        // 发现-（客服/游戏主播& 视频）
//                        Request.getInstance().anchorAndVideoList(anchorAndVideoListJsonEntityCallback);
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
//                if (dx > 0) {
//                    //大于0表示正在向右滚动
//                    isSlidingToLast = true;
//                } else {
//                    //小于等于0表示停止或向左滚动
//                    isSlidingToLast = false;
//                }
//            }
//        });

        //（客服直播、游戏直播、游戏视频）Adapter
        videoAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                if (item instanceof AnchorAndVideoList.DataBean.AnchorListBean) {
                    //客服直播、游戏直播
                    AnchorAndVideoList.DataBean.AnchorListBean dataBean = (AnchorAndVideoList.DataBean.AnchorListBean) item;
                    switch (dataBean.getType()) {
                        case 1://客服主播

                            break;
                        case 2://游戏主播

                            break;
                        default:
                    }
                } else if (item instanceof AnchorAndVideoList.DataBean.VideoListBean) {
                    //游戏视频
                }
            }
        });

        //资讯Adapter
        findNewsAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                if (item instanceof NewsList.DataBean.ListBean) {
                    NewsList.DataBean.ListBean listBean = (NewsList.DataBean.ListBean) item;

                    Intent intent = new Intent(getContext(), ActivityNewsDetails.class);
                    intent.putExtra(ActivityNewsDetails.NEWS_ID, listBean.getId());
                    startActivity(intent);
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

    /**
     * banner
     */
    JsonEntityCallback bannerJsonEntityCallback = new JsonEntityCallback<FindBanner>(FindBanner.class) {
        @Override
        protected void onSuccess(FindBanner findBanner) {
            FindBanner.DataBean bannerData = findBanner.getData();
            if (bannerData != null) {
                bannerDataList.clear();
                bannerDataList.addAll(bannerData.getList());
                if (bannerDataList != null && bannerDataList.size() > 0) {
                    mList.clear();
                    mFragments.clear();
                    for (FindBanner.DataBean.ListBean listBean : bannerDataList) {
                        mList.add(listBean.getResource());
                        FragmentCustomerServiceMsg fragmentCustomerServiceMsg = new FragmentCustomerServiceMsg();
                        fragmentCustomerServiceMsg.setData(listBean);
                        mFragments.add(fragmentCustomerServiceMsg);
                    }


                    FragmentStatePageAdapter fragmentStatePageAdapter = new FragmentStatePageAdapter(getChildFragmentManager(), mFragments);
                    fragmentStatePageAdapter.setDestroyItem(false);
                    mViewPagerBannerFragment.setAdapter(fragmentStatePageAdapter);
                    //预加载页面数量的方法
                    // mViewPager.setOffscreenPageLimit(mTitles.size());//tab数

                    mBannerView.create(mList);

                } else {
                    ToastUtil.tost(findBanner.getMsg());
                }
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            mSrlRealTimeInfo.finishRefresh(200);
        }
    };

    /**
     * 发现-（客服/游戏主播& 视频）
     */
    JsonEntityCallback anchorAndVideoListJsonEntityCallback = new JsonEntityCallback<AnchorAndVideoList>(AnchorAndVideoList.class) {
        @Override
        protected void onSuccess(AnchorAndVideoList anchorAndVideoList) {
            AnchorAndVideoList.DataBean data = anchorAndVideoList.getData();
            if (data != null) {
                List<AnchorAndVideoList.DataBean.AnchorListBean> anchor_list = data.getAnchor_list();
                List<AnchorAndVideoList.DataBean.VideoListBean> video_list = data.getVideo_list();

                List<Object> objectList = new ArrayList<>();
                if (anchor_list != null)
                    objectList.addAll(anchor_list);
                if (video_list != null)
                    objectList.addAll(video_list);

                videoAdapter.replaceAll(objectList);

            } else {
                ToastUtil.tost(anchorAndVideoList.getMsg());
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            //获取（游戏视频）  -- 游戏视频 将拼接在  客服直播、游戏直播  后面
            // Request.getInstance().videoList(pageVideo = 1, limitVideo = 10, videoListJsonEntityCallback);
        }
    };
    /**
     * 游戏视频列表
     */
//    JsonEntityCallback videoListJsonEntityCallback = new JsonEntityCallback<VideoList>(VideoList.class) {
//        @Override
//        protected void onSuccess(VideoList videoList) {
//            VideoList.DataBean data = videoList.getData();
//            if (data != null) {
//                List<VideoList.DataBean.ListBean> list = data.getList();
//                if (list != null && list.size() > 0) {
//                    videoAdapter.addItemsToLast(list);
//                } else {
//                    if (pageVideo == 1) {
//                        ToastUtil.tost(videoList.getMsg());
//                    } else {
//                        ToastUtil.tost("没有更多啦!");
//                    }
//                }
//            }
//        }
//    };
    /**
     * 发现-资讯列表
     */
    JsonEntityCallback newsListJsonEntityCallback = new JsonEntityCallback<NewsList>(NewsList.class) {
        @Override
        protected void onSuccess(NewsList newsList) {
            NewsList.DataBean newsListData = newsList.getData();
            if (newsListData != null) {
                List<NewsList.DataBean.ListBean> newsListDataList = newsListData.getList();
                if (newsListDataList != null && newsListDataList.size() > 0) {
                    if (pageNews == 1)
                        findNewsAdapter.replaceAll(newsListDataList);
                    else
                        findNewsAdapter.addItemsToLast(newsListDataList);
                } else {
                    if (pageNews == 1) {
                        ToastUtil.tost(newsList.getMsg());
                    } else {
                        ToastUtil.tost("没有更多啦!");
                    }
                }
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            mSrlRealTimeInfo.finishLoadMore(200);
        }
    };


}
