package com.hjy.gamecommunity.activity.search;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.FamilyAdapter;
import com.hjy.gamecommunity.adapter.GameAdapter;
import com.hjy.gamecommunity.adapter.LiveAdapter;
import com.hjy.gamecommunity.adapter.NewsAdapter;
import com.hjy.gamecommunity.adapter.VideoAdapter;
import com.hjy.gamecommunity.enumclass.TaskSearchType;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/16 15:34
 * 描述: 搜索 -分类
 */
public class ActivitySearchOther extends BaseActivity {
    public final static String KEYWORDS = "搜索内容";
    public final static String SEARCHTYPE = "搜索类型";

    private AppCompatEditText mAppCompatEditText;
    private ImageButton mIbXx;
    private SuperTextView mStvBack;
    private LinearLayout mLlBar;
    private TextView mTvTitle;
    private RecyclerView mRecyclerView;
    private BaseAdapter baseAdapter;
    private String searchDesc;
    private String searchType;
    private String keywords;

    @Override
    public Object getLayout() {
        return R.layout.activity_search_other;

    }

    @Override
    public void initView() {
        mAppCompatEditText = findViewById(R.id.appCompatEditText);
        mIbXx = findViewById(R.id.ib_xx);
        mStvBack = findViewById(R.id.stv_Back);
        mLlBar = findViewById(R.id.ll_bar);
        mTvTitle = findViewById(R.id.tv_Title);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(true);//设置状态栏是否为浅色模式


        keywords = getIntent().getStringExtra(KEYWORDS);
        searchType = getIntent().getStringExtra(SEARCHTYPE);
        searchDesc = TaskSearchType.searchDesc(searchType);

        int dp2px = ConvertUtils.dp2px(8);
        switch (searchDesc) {
            case "直播":
                mRecyclerView.setPadding(dp2px, 0, dp2px, 0);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                baseAdapter = new LiveAdapter(gridLayoutManager);
                break;
            case "视频":
                mRecyclerView.setPadding(dp2px, 0, dp2px, 0);
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
                mRecyclerView.setLayoutManager(gridLayoutManager2);
                baseAdapter = new VideoAdapter(gridLayoutManager2);
                break;
            case "家族":
                mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                baseAdapter = new FamilyAdapter();
                break;
            case "游戏":
                mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                baseAdapter = new GameAdapter();
                break;
            case "资讯":
                mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bui_white));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                baseAdapter = new NewsAdapter();
                break;
        }
        mRecyclerView.setAdapter(baseAdapter);


        mTvTitle.setText("相关" + searchDesc);
        if (!TextUtils.isEmpty(keywords)) {
            mAppCompatEditText.setText(keywords);
            Request.getInstance().search(searchType, keywords, searchJsonEntityCallback);
        }
    }

    @Override
    public void listener() {
        mStvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //为EditText设置监听，注意监听类型为TextWatcher
        mAppCompatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s:变化后的所有字符
                if (StringUtils.isTrimEmpty(s.toString())) {
                    mIbXx.setVisibility(View.GONE);
                } else {
                    mIbXx.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数

            }
        });


        //输入法搜素监听
        mAppCompatEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String textViewString = v.getText().toString();
                    if (!TextUtils.isEmpty(textViewString)) {
                        Request.getInstance().search(searchType, textViewString, searchJsonEntityCallback);
                    } else {
                        ToastUtil.tost("请输入搜索内容");
                    }
                    return true;
                }
                return false;
            }
        });

        mIbXx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppCompatEditText.setText("");
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
                List<SearchBean.DataBean.LiveListBean> live_list = searchBeanData.getLive_list();//直播
                List<SearchBean.DataBean.VideoListBean> video_list = searchBeanData.getVideo_list();//视频
                List<SearchBean.DataBean.FamilyListBean> family_list = searchBeanData.getFamily_list();//家族
                List<SearchBean.DataBean.GameListBean> game_list = searchBeanData.getGame_list();//游戏
                List<SearchBean.DataBean.NewsListBean> news_list = searchBeanData.getNews_list();//资讯

                switch (searchDesc) {
                    case "直播":
                        baseAdapter.replaceAll(live_list);
                        break;
                    case "视频":
                        baseAdapter.replaceAll(video_list);
                        break;
                    case "家族":
                        baseAdapter.replaceAll(family_list);
                        break;
                    case "游戏":
                        baseAdapter.replaceAll(game_list);
                        break;
                    case "资讯":
                        baseAdapter.replaceAll(news_list);
                        break;
                }

                if (baseAdapter.getItemCount() < 1) {
                    ToastUtil.tost("未搜索到相关内容");
                }
            } else
                ToastUtil.tost(searchBean.getMsg());
        }
    };
}
