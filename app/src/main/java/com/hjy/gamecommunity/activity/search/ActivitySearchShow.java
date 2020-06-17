package com.hjy.gamecommunity.activity.search;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.textview.SuperTextView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.FragmentStatePageAdapter;
import com.hjy.gamecommunity.enumclass.SearchEnum;
import com.hjy.gamecommunity.fragment.search.FragmentSearch;
import com.xuexiang.xui.widget.tabbar.TabSegment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 10:01
 * 描述: 搜索
 */
public class ActivitySearchShow extends BaseActivity {
    public final static String KEYWORDS = "搜索内容";
    public final static String SEARCHTYPE = "搜索类型";
    private AppCompatEditText mAppCompatEditText;
    private ImageButton mIbXx;
    private LinearLayout mLlBar;
    private SuperTextView mStvBack;
    private TabSegment mTabSegment;
    private ViewPager mViewPagerContent;

    @Override
    public Object getLayout() {
        return R.layout.activity_search_show;
    }

    @Override
    public void initView() {
        mAppCompatEditText = findViewById(R.id.appCompatEditText);
        mIbXx = findViewById(R.id.ib_xx);
        mIbXx.setVisibility(View.GONE);
        mLlBar = findViewById(R.id.ll_bar);
        mStvBack = findViewById(R.id.stv_Back);
        mTabSegment = findViewById(R.id.tabSegment);
        mViewPagerContent = findViewById(R.id.viewPager_Content);
    }

    private Map<String, FragmentSearch> mFragments = new LinkedHashMap<>();

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(true);//设置状态栏是否为浅色模式

        String keywords = getIntent().getStringExtra(KEYWORDS);
        String searchType = getIntent().getStringExtra(SEARCHTYPE);


        for (String value : SearchEnum.i().getValue()) {
            mTabSegment.addTab(new TabSegment.Tab(value));

            FragmentSearch fragmentSearchAll = new FragmentSearch();
            fragmentSearchAll.refreshData(SearchEnum.i().getKey(value), keywords);
            mFragments.put(value, fragmentSearchAll);
        }

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.addAll(mFragments.values());
        FragmentStatePageAdapter fragmentStatePageAdapter = new FragmentStatePageAdapter(getSupportFragmentManager(), fragmentList);
        fragmentStatePageAdapter.setDestroyItem(false);
        mViewPagerContent.setAdapter(fragmentStatePageAdapter);
        mViewPagerContent.setOffscreenPageLimit(fragmentList.size());
        mTabSegment.setMode(TabSegment.MODE_FIXED);
        mTabSegment.setupWithViewPager(mViewPagerContent, false);

        //不使用ViewPager的话，必须notifyDataChanged，否则不能正常显示
//        mTabSegment.notifyDataChanged();
//        mTabSegment.selectTab(0);
        if (!TextUtils.isEmpty(keywords)) {
            mAppCompatEditText.setText(keywords);
            selectTab(SearchEnum.i().getValue(searchType));
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
                        for (Map.Entry<String, FragmentSearch> entry : mFragments.entrySet()) {
                            String key = entry.getKey();
                            FragmentSearch value = entry.getValue();
                            value.refreshData(SearchEnum.i().getKey(key), textViewString);
                        }
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

        mTabSegment.setOnTabClickListener(new TabSegment.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                //  ToastUtils.toast("点击了" + index);
            }
        });

        mTabSegment.addOnTabSelectedListener(new TabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                if (mTabSegment != null) {
                    mTabSegment.hideSignCountView(index);
                }
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                if (mTabSegment != null) {
                    mTabSegment.hideSignCountView(index);
                }
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }


    public void selectTab(String title) {
        int indexOf = SearchEnum.i().indexOfV(title);
        mViewPagerContent.setCurrentItem(indexOf);
        // mTabSegment.selectTab(indexOf);
    }


}
