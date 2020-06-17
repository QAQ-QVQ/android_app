package com.hjy.gamecommunity.fragment.search;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.news.ActivityNewsDetails;
import com.hjy.gamecommunity.activity.search.ActivitySearchShow;
import com.hjy.gamecommunity.adapter.FamilyAdapter;
import com.hjy.gamecommunity.adapter.GameAdapter;
import com.hjy.gamecommunity.adapter.LiveAdapter;
import com.hjy.gamecommunity.adapter.NewsAdapter;
import com.hjy.gamecommunity.adapter.VideoAdapter;
import com.hjy.gamecommunity.enumclass.SearchEnum;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 11:44
 * 描述: 搜索-全部
 */
public class FragmentSearch extends BaseFragment {

    LinearLayout mLlContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;

    }

    private String currentSearchType, currentKeywords, searchDesc;//当前
    private String loadedSearchType, loadedKeywords;//已加载的

    public void refreshData(final String searchType, final String keywords) {
        currentSearchType = searchType;
        currentKeywords = keywords;
        searchDesc = SearchEnum.i().getValue(currentSearchType);
        if (isVisible) {
            Request.getInstance().search(searchType, keywords, searchJsonEntityCallback);
        }

    }

    @Override
    public void initView(View mRootView) {
        mLlContent = findViewById(R.id.ll_Content);
    }

    private boolean isVisible;

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        this.isVisible = isVisible;
        if (isVisible) {
            //当前显示的内容与搜索内容一致，则不再搜索
            if (!TextUtils.isEmpty(currentKeywords) && !currentKeywords.equals(loadedKeywords)) {
                Request.getInstance().search(currentSearchType, currentKeywords, searchJsonEntityCallback);
            }
        }
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
    private ContentViewContainer getContentViewContainer() {
        View inflate = View.inflate(getContext(), R.layout.title_recyclerview_layout, null);

        TextView mTvTitle = inflate.findViewById(R.id.tv_Title);
        mTvTitle.setTextSize(18);
        mTvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.bui_black_light1));

        SuperTextView mTvMore = inflate.findViewById(R.id.tv_More);
        LinearLayout mLlMsg = inflate.findViewById(R.id.ll_Msg);
        int dp2px = ConvertUtils.dp2px(6);
        int dp2px2 = ConvertUtils.dp2px(12);
        mLlMsg.setPadding(dp2px2, dp2px2, dp2px2, dp2px);
        RecyclerView mRecyclerView = inflate.findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        ContentViewContainer contentViewContainer = new ContentViewContainer(inflate, mTvTitle, mTvMore, mLlMsg, mRecyclerView);
        return contentViewContainer;
    }


    /**
     * 初始化所有RecyclerView
     *
     * @param searchBeanData
     */
    private Map<String, ContentViewContainer> stringFragmentSearchMap = new LinkedHashMap<>();

    private void initRecyclerView(SearchBean.DataBean searchBeanData) {
        List<SearchBean.DataBean.LiveListBean> live_list = searchBeanData.getLive_list();//直播
        List<SearchBean.DataBean.VideoListBean> video_list = searchBeanData.getVideo_list();//视频
        List<SearchBean.DataBean.FamilyListBean> family_list = searchBeanData.getFamily_list();//家族
        List<SearchBean.DataBean.GameListBean> game_list = searchBeanData.getGame_list();//游戏
        List<SearchBean.DataBean.NewsListBean> news_list = searchBeanData.getNews_list();//资讯

        mLlContent.removeAllViews();
        stringFragmentSearchMap.clear();

        if (live_list != null && live_list.size() > 0) {
            ContentViewContainer contentViewContainer = getContentViewContainer();
            stringFragmentSearchMap.put(SearchEnum.VALUE2, contentViewContainer);
            mLlContent.addView(contentViewContainer.getRootView());
            contentViewContainer.getmTvTitle().setText(SearchEnum.VALUE2);

            int dp2px = ConvertUtils.dp2px(8);
            contentViewContainer.getmRecyclerView().setPadding(dp2px, 0, dp2px, 0);

            RecyclerView.LayoutManager layoutManager;
            if (searchDesc.equals(SearchEnum.VALUE1)) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                layoutManager = linearLayoutManager;
            } else {
                layoutManager = new GridLayoutManager(getContext(), 2);
            }
            contentViewContainer.getmRecyclerView().setLayoutManager(layoutManager);

            LiveAdapter liveAdapter = new LiveAdapter(live_list, layoutManager);
            contentViewContainer.getmRecyclerView().setAdapter(liveAdapter);
            initlistener(contentViewContainer, liveAdapter);

        }
        if (video_list != null && video_list.size() > 0) {
            ContentViewContainer contentViewContainer = getContentViewContainer();
            stringFragmentSearchMap.put(SearchEnum.VALUE3, contentViewContainer);
            mLlContent.addView(contentViewContainer.getRootView());
            contentViewContainer.getmTvTitle().setText(SearchEnum.VALUE3);

            int dp2px = ConvertUtils.dp2px(8);
            contentViewContainer.getmRecyclerView().setPadding(dp2px, 0, dp2px, 0);

            RecyclerView.LayoutManager layoutManager;
            if (searchDesc.equals(SearchEnum.VALUE1)) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                layoutManager = linearLayoutManager;
            } else {
                layoutManager = new GridLayoutManager(getContext(), 2);
            }
            contentViewContainer.getmRecyclerView().setLayoutManager(layoutManager);

            VideoAdapter videoAdapter = new VideoAdapter(video_list, layoutManager);
            contentViewContainer.getmRecyclerView().setAdapter(videoAdapter);
            initlistener(contentViewContainer, videoAdapter);

        }
        if (family_list != null && family_list.size() > 0) {
            ContentViewContainer contentViewContainer = getContentViewContainer();
            stringFragmentSearchMap.put(SearchEnum.VALUE4, contentViewContainer);
            mLlContent.addView(contentViewContainer.getRootView());
            contentViewContainer.getmTvTitle().setText(SearchEnum.VALUE4);
            contentViewContainer.getmRecyclerView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));
            contentViewContainer.getmRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));

            FamilyAdapter familyAdapter = new FamilyAdapter(family_list);
            contentViewContainer.getmRecyclerView().setAdapter(familyAdapter);
            initlistener(contentViewContainer, familyAdapter);
        }
        if (game_list != null && game_list.size() > 0) {
            ContentViewContainer contentViewContainer = getContentViewContainer();
            stringFragmentSearchMap.put(SearchEnum.VALUE5, contentViewContainer);
            mLlContent.addView(contentViewContainer.getRootView());
            contentViewContainer.getmTvTitle().setText(SearchEnum.VALUE5);
            contentViewContainer.getmRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
            contentViewContainer.getmRecyclerView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));

            GameAdapter gameAdapter = new GameAdapter(game_list);
            contentViewContainer.getmRecyclerView().setAdapter(gameAdapter);
            initlistener(contentViewContainer, gameAdapter);
        }
        if (news_list != null && news_list.size() > 0) {
            ContentViewContainer contentViewContainer = getContentViewContainer();
            stringFragmentSearchMap.put(SearchEnum.VALUE6, contentViewContainer);
            mLlContent.addView(contentViewContainer.getRootView());
            contentViewContainer.getmTvTitle().setText(SearchEnum.VALUE6);
            contentViewContainer.getmRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
            contentViewContainer.getmRecyclerView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));

            NewsAdapter newsAdapter = new NewsAdapter(news_list,NewsAdapter.ONE_PICTURES);
            contentViewContainer.getmRecyclerView().setAdapter(newsAdapter);
            initlistener(contentViewContainer, newsAdapter);
        }

        if (!searchDesc.equals(SearchEnum.VALUE1)) {
            //如果当前搜索内容不是全部  则隐藏提示view
            ContentViewContainer contentViewContainer = stringFragmentSearchMap.get(searchDesc);
            if (contentViewContainer != null)
                contentViewContainer.getmLlMsg().setVisibility(View.GONE);
        }

        if (mLlContent.getChildCount() < 1) {
            ToastUtil.tost("未搜索到相关内容");
        }

        loadedSearchType = currentSearchType;
        loadedKeywords = currentKeywords;
    }

    private void initlistener(final ContentViewContainer contentViewContainer, BaseAdapter baseAdapter) {
        //更多监听
        contentViewContainer.getmTvMore().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTvTitleString = contentViewContainer.getmTvTitle().getText().toString();

                if (getActivity() instanceof ActivitySearchShow) {
                    ActivitySearchShow activitySearchShow = (ActivitySearchShow) getActivity();
                    activitySearchShow.selectTab(mTvTitleString);
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
                    Intent intent = new Intent(getContext(), ActivityNewsDetails.class);
                    intent.putExtra(ActivityNewsDetails.NEWS_ID, newsListBean.getId());
                    startActivity(intent);
                }
            }
        });
    }

    class ContentViewContainer {
        private View rootView;
        private TextView mTvTitle;
        private SuperTextView mTvMore;
        private LinearLayout mLlMsg;
        private RecyclerView mRecyclerView;

        public ContentViewContainer(View rootView, TextView mTvTitle, SuperTextView mTvMore, LinearLayout mLlMsg, RecyclerView mRecyclerView) {
            this.rootView = rootView;
            this.mTvTitle = mTvTitle;
            this.mTvMore = mTvMore;
            this.mLlMsg = mLlMsg;
            this.mRecyclerView = mRecyclerView;
        }

        public View getRootView() {
            return rootView;
        }

        public void setRootView(View rootView) {
            this.rootView = rootView;
        }

        public TextView getmTvTitle() {
            return mTvTitle;
        }

        public void setmTvTitle(TextView mTvTitle) {
            this.mTvTitle = mTvTitle;
        }

        public SuperTextView getmTvMore() {
            return mTvMore;
        }

        public void setmTvMore(SuperTextView mTvMore) {
            this.mTvMore = mTvMore;
        }

        public LinearLayout getmLlMsg() {
            return mLlMsg;
        }

        public void setmLlMsg(LinearLayout mLlMsg) {
            this.mLlMsg = mLlMsg;
        }

        public RecyclerView getmRecyclerView() {
            return mRecyclerView;
        }

        public void setmRecyclerView(RecyclerView mRecyclerView) {
            this.mRecyclerView = mRecyclerView;
        }
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
