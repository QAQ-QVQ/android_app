package com.hjy.gamecommunity.fragment.message;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.divider.HorizontalDividerItemDecoration;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.message.OfficialMsgAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.Arrays;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/28 17:58
 * 描述:  官方消息
 */
public class FragmentOfficialMsg extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private OfficialMsgAdapter officialMsgAdapter;

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
        //RecyclerView分割线
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//            mRecyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速才会有虚线效果
//        }
        Paint paint = new Paint();
        paint.setStrokeWidth(ConvertUtils.dp2px(1));//分割线宽度
        paint.setColor(ContextCompat.getColor(getActivity(), R.color.gray_light3));//分割线颜色
        //DashPathEffect作用是将Path的线段虚线化--
        // 第一个参数是数组，且它的长度必须>=2, 数组的数字就是控制实虚... 长度。
        // phase:为绘制时的偏移量
        // paint.setPathEffect(new DashPathEffect(new float[]{20f, 10f}, 0));//虚线
        HorizontalDividerItemDecoration build = new HorizontalDividerItemDecoration.Builder(getContext())//横向分割线
                .paint(paint)
                .margin(ConvertUtils.dp2px(12), 0)//分割线margin
                .showLastDivider()//显示最后一条
                .startSkipCount(0)//跳过开头的*个分割线不展示
                //.endSkipCount(1)//跳过结尾的2个分割线不展示
                .build();
        mRecyclerView.addItemDecoration(build);
        //Adapter
        officialMsgAdapter = new OfficialMsgAdapter();
        mRecyclerView.setAdapter(officialMsgAdapter);
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
        officialMsgAdapter.replaceAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
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

        officialMsgAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Object>() {
            @Override
            public void onItemClick(View view, Object item, int position) {

            }
        });
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
