package com.hjy.gamecommunity.activity.news;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baserequest.bean.NewsList;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.NewsAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 16:04
 * 描述: 资讯-列表
 */
public class ActivityNewsList extends BaseActivity {

    private ColorStateImageView mIvBackImageBar;
    private TextView mTvTextBar;
    private LinearLayout mLlBar;
    private RecyclerView mRecyclerView;
    private NewsAdapter newsAdapter;
    private SmartRefreshLayout mSmartRefreshLayout;

    @Override
    public Object getLayout() {
        return R.layout.activity_news_list;


    }

    @Override
    public void initView() {
        mSmartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        mIvBackImageBar = findViewById(R.id.iv_back_image_bar);
        mTvTextBar = findViewById(R.id.tv_text_bar);
        mLlBar = findViewById(R.id.ll_bar);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(true);//设置状态栏是否为浅色模式

        mTvTextBar.setText("资讯");

        //资讯Adapter
        int dp2px = ConvertUtils.dp2px(8);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setPadding(dp2px, 0, dp2px, 0);
        newsAdapter = new NewsAdapter(NewsAdapter.THREE_PICTURES);
        mRecyclerView.setAdapter(newsAdapter);

        //设置 Header 样式
        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
        mSmartRefreshLayout.setRefreshHeader(classicsHeader);
        //设置 Footer  样式
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));

        mSmartRefreshLayout.setEnableAutoLoadMore(false);//是否启用列表惯性滑动到底部时自动加载更多
        //设置刷新加载时禁止所有列表操作
        mSmartRefreshLayout.setDisableContentWhenRefresh(true);
        mSmartRefreshLayout.setDisableContentWhenLoading(true);

        mSmartRefreshLayout.autoRefresh();//自动刷新
    }

    private int pageNews = 1, limitNews = 10;//资讯列表 >  页数 - 页大小

    @Override
    public void listener() {
        mIvBackImageBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                //发现-资讯列表
                Request.getInstance().newsList(pageNews = 1, limitNews = 10, newsListJsonEntityCallback);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //发现-资讯列表
                Request.getInstance().newsList(++pageNews, limitNews = 10, newsListJsonEntityCallback);
            }


        });

        newsAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
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
                        newsAdapter.replaceAll(newsListDataList);
                    else
                        newsAdapter.addItemsToLast(newsListDataList);
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
            mSmartRefreshLayout.finishRefresh(200);
            mSmartRefreshLayout.finishLoadMore(200);
        }
    };
}
