package com.hjy.gamecommunity.fragment.message;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.divider.HorizontalDividerItemDecoration;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.message.FamilyMsgAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.Arrays;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/28 17:48
 * 描述:  家族消息
 */
public class FragmentFamilyMsg extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private FamilyMsgAdapter familyMsgAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_family_msg;
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
        //RecyclerView分割线
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(ContextCompat.getColor(getActivity(), R.color.gray_light3));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{15.0f, 15.0f}, 0));//虚线
        HorizontalDividerItemDecoration build = new HorizontalDividerItemDecoration.Builder(getContext())
                .paint(paint)
                .margin(0)
                .showLastDivider()
           //  .startSkipCount(3)//跳过开头的3个分割线不展示
                .endSkipCount(1)//跳过结尾的2个分割线不展示
                .build();
        mRecyclerView.addItemDecoration(build);
        //Adapter
        familyMsgAdapter = new FamilyMsgAdapter();
        mRecyclerView.setAdapter(familyMsgAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置 Header 样式
        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
        mSmartRefreshLayout.setRefreshHeader(classicsHeader);
        //设置 Footer  样式
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));

        mSmartRefreshLayout.setEnableAutoLoadMore(false);//是否启用列表惯性滑动到底部时自动加载更多
        //设置刷新加载时禁止所有列表操作
        mSmartRefreshLayout.setDisableContentWhenRefresh(true);
        mSmartRefreshLayout.setDisableContentWhenLoading(true);

        //mSmartRefreshLayout.autoRefresh();//自动刷新
        familyMsgAdapter.replaceAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

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
