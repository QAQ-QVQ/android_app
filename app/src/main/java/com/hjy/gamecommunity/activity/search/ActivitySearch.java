package com.hjy.gamecommunity.activity.search;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.enumclass.SearchEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 10:01  灰色
 * 描述: 搜索
 */
public class ActivitySearch extends BaseActivity {

    private ColorStateImageView mIvBackImageBar;
    private AppCompatEditText mAppCompatEditText;
    private ImageButton mIbXx;
    private LinearLayout mLlBar;
    private RecyclerView mRecyclerView;

    @Override
    public Object getLayout() {
        return R.layout.activity_search;


    }

    @Override
    public void initView() {
        mIvBackImageBar = findViewById(R.id.iv_back_image_bar);
        mAppCompatEditText = findViewById(R.id.appCompatEditText);
        mIbXx = findViewById(R.id.ib_xx);
        mIbXx.setVisibility(View.GONE);
        mLlBar = findViewById(R.id.ll_bar);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(true);//设置状态栏是否为浅色模式

        mIvBackImageBar.setImgColor(ContextCompat.getColor(getContext(), R.color.bui_black_light));
        mIvBackImageBar.setOnClickImgAlpha(0.5f);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false; //禁止滑动
            }
        };
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);

        List<String> stringList = new ArrayList<>();
        for (String value : SearchEnum.i().getValue()) {
            if (!value.equals(SearchEnum.VALUE1))
                stringList.add("搜" + value);
        }
        mRecyclerView.setAdapter(new BaseAdapter<String>(stringList) {
            @Override
            public int getLayout(String item, int position) {
                return R.layout.item_textview;
            }

            @Override
            public void onBindViewHolder(BaseViewHolder viewHolder, String item, int i) {
                LinearLayout mLlLinearLayout = viewHolder.findViewById(R.id.ll_LinearLayout);
                TextView mTvText = viewHolder.findViewById(R.id.tv_text);
                mTvText.setText(item);
                if ((i + 1) % 3 == 1) {
                    //左边
                    mLlLinearLayout.setGravity(Gravity.LEFT);
                } else if ((i + 1) % 3 == 0) {
                    //右边
                    mLlLinearLayout.setGravity(Gravity.RIGHT);
                } else {
                    //中间
                    mLlLinearLayout.setGravity(Gravity.CENTER);

                }
            }

            @Override
            public void listener(BaseViewHolder viewHolder, String item, int i) {
                viewHolder.findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String editTextString = mAppCompatEditText.getText().toString();
                        jumpOther(editTextString, SearchEnum.i().getKey(item.replace("搜", "")));
                    }
                });
            }
        });
    }

    @Override
    public void listener() {
        mIvBackImageBar.setOnClickListener(new View.OnClickListener() {
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
                        jump(textViewString, SearchEnum.i().getKey(SearchEnum.VALUE1));
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
    }


    /**
     * 跳转搜索全部
     *
     * @param keywords
     * @param searchType
     */
    private void jump(String keywords, String searchType) {
        Intent intent = new Intent(getContext(), ActivitySearchShow.class);
        intent.putExtra(ActivitySearchShow.KEYWORDS, keywords);//搜索内容
        intent.putExtra(ActivitySearchShow.SEARCHTYPE, searchType);//搜索类型
        startActivity(intent);
    }

    /**
     * 跳转搜索单独分类
     *
     * @param keywords
     * @param searchType
     */
    private void jumpOther(String keywords, String searchType) {
        Intent intent = new Intent(getContext(), ActivitySearchOther.class);
        intent.putExtra(ActivitySearchOther.KEYWORDS, keywords);//搜索内容
        intent.putExtra(ActivitySearchOther.SEARCHTYPE, searchType);//搜索类型
        startActivity(intent);
    }

}
