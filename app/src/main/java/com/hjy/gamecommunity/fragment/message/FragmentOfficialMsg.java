package com.hjy.gamecommunity.fragment.message;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.gamecommunity.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/28 17:58
 * 描述:  官方消息
 */
public class FragmentOfficialMsg extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_official_msg;
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
//        int dp2px = ConvertUtils.dp2px(8);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//        mRecyclerView.setPadding(dp2px, 0, dp2px, 0);
//        videoAdapter = new VideoAdapter();
//        videoAdapter.setMode(videoAdapter.MATCH_PARENT);
//        mRecyclerView.setAdapter(videoAdapter);
//
//        //设置 Header 样式
//        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
//        mSmartRefreshLayout.setRefreshHeader(classicsHeader);
//        //设置 Footer  样式
//        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
//
//        mSmartRefreshLayout.setEnableAutoLoadMore(false);//是否启用列表惯性滑动到底部时自动加载更多
//        //设置刷新加载时禁止所有列表操作
//        mSmartRefreshLayout.setDisableContentWhenRefresh(true);
//        mSmartRefreshLayout.setDisableContentWhenLoading(true);
//
//        mSmartRefreshLayout.autoRefresh();//自动刷新
    }

    private int pageVideo = 1, limitVideo = 10;//视频列表 >  页数 - 页大小

    @Override
    public void listener() {

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //视频列表
                // Request.getInstance().videoList(pageVideo = 1, limitVideo = 10, videoListJsonEntityCallback);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //视频列表
                //  Request.getInstance().videoList(++pageVideo, limitVideo = 10, videoListJsonEntityCallback);
            }

        });

//        videoAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<VideoList.DataBean.ListBean>() {
//            @Override
//            public void onItemClick(View view, VideoList.DataBean.ListBean item, int position) {
//                //游戏视频
//                Intent intent = new Intent(getContext(), ActivityVideoPlay.class);
//                intent.putExtra(ActivityVideoPlay.VIDEO_ID, item.getId());
//                startActivity(intent);
//            }
//        });
    }

//    JsonEntityCallback videoListJsonEntityCallback = new JsonEntityCallback<VideoList>(VideoList.class) {
//        @Override
//        protected void onSuccess(VideoList videoList) {
//            VideoList.DataBean data = videoList.getData();
//            if (data != null) {
//                List<VideoList.DataBean.ListBean> dataList = data.getList();
//                if (dataList != null && dataList.size() > 0) {
//                    if (pageVideo == 1)
//                        videoAdapter.replaceAll(dataList);
//                    else
//                        videoAdapter.addItemsToLast(dataList);
//                } else {
//                    if (pageVideo == 1) {
//                        ToastUtil.tost(videoList.getMsg());
//                    } else {
//                        ToastUtil.tost("没有更多啦!");
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void onFinish() {
//            super.onFinish();
//            mSmartRefreshLayout.finishRefresh(200);
//            mSmartRefreshLayout.finishLoadMore(200);
//        }
//    };
}
