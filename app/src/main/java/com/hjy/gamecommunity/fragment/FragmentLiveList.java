package com.hjy.gamecommunity.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baserequest.bean.LiveList;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.LiveAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/20 17:32
 * 描述: 直播列表
 */
public class FragmentLiveList extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private LiveAdapter<LiveList.DataBean> liveAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_list;

    }

    @Override
    public void initView(View mRootView) {
        mRecyclerView = findViewById(R.id.recyclerView);
        mSmartRefreshLayout = findViewById(R.id.smartRefreshLayout);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }


    @Override
    public void initData() {
        //Adapter
        int dp2px = ConvertUtils.dp2px(8);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setPadding(dp2px, 0, dp2px, 0);
        liveAdapter = new LiveAdapter();
        liveAdapter.setMode(liveAdapter.MATCH_PARENT);
        mRecyclerView.setAdapter(liveAdapter);

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

    private int pageLive = 1, limitLive = 10;//列表 >  页数 - 页大小

    @Override
    public void listener() {
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //视频列表
                Request.getInstance().liveList(pageLive = 1, limitLive = 10, liveListJsonEntityCallback);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //视频列表
                Request.getInstance().liveList(++pageLive, limitLive = 10, liveListJsonEntityCallback);
            }

        });

        liveAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<LiveList.DataBean>() {
            @Override
            public void onItemClick(View view, LiveList.DataBean item, int position) {

            }
        });
    }

    /**
     * 直播列表
     */
    JsonEntityCallback liveListJsonEntityCallback = new JsonEntityCallback<LiveList>(LiveList.class) {
        @Override
        protected void onSuccess(LiveList liveList) {
            List<LiveList.DataBean> dataList = liveList.getData();
            if (dataList != null && dataList.size() > 0) {
                if (pageLive == 1)
                    liveAdapter.replaceAll(dataList);
                else
                    liveAdapter.addItemsToLast(dataList);
            } else {
                if (pageLive == 1) {
                    ToastUtil.tost(liveList.getMsg());
                } else {
                    ToastUtil.tost("没有更多啦!");
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
