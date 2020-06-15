package com.hjy.gamecommunity.fragment.search;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.LiveAdapter;
import com.hjy.gamecommunity.adapter.VideoAdapter;
import com.hjy.gamecommunity.entity.TitleRecyclerViewEntities;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 11:44
 * 描述: 搜索-全部
 */
public class FragmentSearchAll extends FragmentSearch {

    LinearLayout mLlContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    private boolean isInitView;

    @Override

    public void refreshData(final String searchType, final String keywords) {
        if (isVisible) {
            if (isInitView) {
                Request.getInstance().search(searchType, keywords, searchJsonEntityCallback);
            } else {
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Request.getInstance().search(searchType, keywords, searchJsonEntityCallback);
                    }
                }, 500);
            }
        }


    }

    @Override
    public void initView(View mRootView) {
        isInitView = true;
        mLlContent = findViewById(R.id.ll_Content);
    }

    private boolean isVisible;

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        this.isVisible = isVisible;
    }


    @Override
    public void initData() {

    }

    @Override
    public void listener() {

    }

    /**
     * 获取相关View
     *
     * @return
     */
    private TitleRecyclerViewEntities getTitleRecyclerViewEntities() {
        View inflate = View.inflate(getContext(), R.layout.title_recyclerview_layout, null);

        TextView mTvTitle = inflate.findViewById(R.id.tv_Title);
        mTvTitle.setTextSize(18);
        mTvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.bui_black_light1));

        SuperTextView mTvMore = inflate.findViewById(R.id.tv_More);
        LinearLayout mLlMsg = inflate.findViewById(R.id.ll_Msg);
        mLlMsg.setPadding(6, 12, 6, 12);
        RecyclerView mRecyclerView = inflate.findViewById(R.id.recyclerView);

        TitleRecyclerViewEntities titleRecyclerViewEntities = new TitleRecyclerViewEntities(inflate, mTvTitle, mTvMore, mLlMsg, mRecyclerView);
        return titleRecyclerViewEntities;
    }


    /**
     * 初始化所有RecyclerView
     *
     * @param searchBeanData
     */
    private Map<String, TitleRecyclerViewEntities> stringFragmentSearchMap = new LinkedHashMap<>();

    private void initRecyclerView(SearchBean.DataBean searchBeanData) {
        List<SearchBean.DataBean.LiveListBean> live_list = searchBeanData.getLive_list();//直播
        List<SearchBean.DataBean.VideoListBean> video_list = searchBeanData.getVideo_list();//视频
        List<SearchBean.DataBean.FamilyListBean> family_list = searchBeanData.getFamily_list();//家族
        List<SearchBean.DataBean.GameListBean> game_list = searchBeanData.getGame_list();//游戏
        List<SearchBean.DataBean.NewsListBean> news_list = searchBeanData.getNews_list();//资讯

        mLlContent.removeAllViews();
        stringFragmentSearchMap.clear();
        if (live_list != null && live_list.size() > 0) {
            TitleRecyclerViewEntities titleRecyclerViewEntities = getTitleRecyclerViewEntities();
            stringFragmentSearchMap.put("直播", titleRecyclerViewEntities);
            mLlContent.addView(titleRecyclerViewEntities.getRootView());
            titleRecyclerViewEntities.getmTvTitle().setText("直播");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            titleRecyclerViewEntities.getmRecyclerView().setLayoutManager(linearLayoutManager);
            LiveAdapter liveAdapter = new LiveAdapter(live_list);
            titleRecyclerViewEntities.getmRecyclerView().setAdapter(liveAdapter);
            titleRecyclerViewEntities.getmRecyclerView().setPadding(12, 0, 12, 0);
            initlistener(titleRecyclerViewEntities, liveAdapter);
        }
        if (video_list != null && video_list.size() > 0) {
            TitleRecyclerViewEntities titleRecyclerViewEntities = getTitleRecyclerViewEntities();
            stringFragmentSearchMap.put("视频", titleRecyclerViewEntities);
            mLlContent.addView(titleRecyclerViewEntities.getRootView());
            titleRecyclerViewEntities.getmTvTitle().setText("视频");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            titleRecyclerViewEntities.getmRecyclerView().setLayoutManager(linearLayoutManager);
            VideoAdapter videoAdapter = new VideoAdapter(video_list);
            titleRecyclerViewEntities.getmRecyclerView().setAdapter(videoAdapter);
            titleRecyclerViewEntities.getmRecyclerView().setPadding(12, 0, 12, 0);
            initlistener(titleRecyclerViewEntities, videoAdapter);

        }
        if (family_list != null && family_list.size() > 0) {
            TitleRecyclerViewEntities titleRecyclerViewEntities = getTitleRecyclerViewEntities();
            stringFragmentSearchMap.put("家族", titleRecyclerViewEntities);
            mLlContent.addView(titleRecyclerViewEntities.getRootView());
            titleRecyclerViewEntities.getmTvTitle().setText("家族");
            titleRecyclerViewEntities.getmRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
            titleRecyclerViewEntities.getmRecyclerView().setPadding(12, 0, 12, 0);
            titleRecyclerViewEntities.getmRecyclerView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));
        }
        if (game_list != null && game_list.size() > 0) {
            TitleRecyclerViewEntities titleRecyclerViewEntities = getTitleRecyclerViewEntities();
            stringFragmentSearchMap.put("游戏", titleRecyclerViewEntities);
            mLlContent.addView(titleRecyclerViewEntities.getRootView());
            titleRecyclerViewEntities.getmTvTitle().setText("游戏");
            titleRecyclerViewEntities.getmRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));

        }
        if (news_list != null && news_list.size() > 0) {
            TitleRecyclerViewEntities titleRecyclerViewEntities = getTitleRecyclerViewEntities();
            stringFragmentSearchMap.put("资讯", titleRecyclerViewEntities);
            mLlContent.addView(titleRecyclerViewEntities.getRootView());
            titleRecyclerViewEntities.getmTvTitle().setText("资讯");
            titleRecyclerViewEntities.getmRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    private void initlistener(final TitleRecyclerViewEntities titleRecyclerViewEntities, BaseAdapter baseAdapter) {
        //更多监听
        titleRecyclerViewEntities.getmLlMsg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTvTitleString = titleRecyclerViewEntities.getmTvTitle().getText().toString();
                switch (mTvTitleString) {
                    case "直播":
                        break;
                    case "视频":
                        break;
                    case "家族":
                        break;
                    case "游戏":
                        break;
                    case "资讯":
                        break;
                }
            }
        });
        baseAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                if (item instanceof SearchBean.DataBean.LiveListBean) {
                    //直播
                    SearchBean.DataBean.LiveListBean dataBean = (SearchBean.DataBean.LiveListBean) item;
                    switch (dataBean.getType()) {
                        case 1://客服主播
                            break;
                        case 2://游戏主播
                            break;
                        default:
                    }
                } else if (item instanceof SearchBean.DataBean.VideoListBean) {
                    // 視頻
                    SearchBean.DataBean.VideoListBean videoListBean = (SearchBean.DataBean.VideoListBean) item;
                } else if (item instanceof SearchBean.DataBean.FamilyListBean) {
                    // 家族
                    SearchBean.DataBean.FamilyListBean familyListBean = (SearchBean.DataBean.FamilyListBean) item;
                } else if (item instanceof SearchBean.DataBean.GameListBean) {
                    // 游戏
                    SearchBean.DataBean.GameListBean gameListBean = (SearchBean.DataBean.GameListBean) item;
                } else if (item instanceof SearchBean.DataBean.NewsListBean) {
                    // 资讯
                    SearchBean.DataBean.NewsListBean newsListBean = (SearchBean.DataBean.NewsListBean) item;
                }
            }
        });
    }


    /**
     *
     */
    JsonEntityCallback searchJsonEntityCallback = new JsonEntityCallback<SearchBean>(SearchBean.class) {
        @Override
        protected void onSuccess(SearchBean searchBean) {
            SearchBean.DataBean searchBeanData = searchBean.getData();
            if (searchBeanData != null) {
                initRecyclerView(searchBeanData);
            } else
                ToastUtil.tost(searchBean.getMsg());
        }
    };
}
