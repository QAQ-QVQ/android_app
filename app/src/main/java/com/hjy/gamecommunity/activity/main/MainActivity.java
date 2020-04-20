package com.hjy.gamecommunity.activity.main;

import android.content.Context;
import android.media.AudioManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.entity.TabEntities;
import com.hjy.gamecommunity.fragment.main.FragmenMessage;
import com.hjy.gamecommunity.fragment.main.FragmenPersonalCenter;
import com.hjy.gamecommunity.fragment.main.FragmenVideo;
import com.hjy.gamecommunity.fragment.main.FragmentFamily;
import com.hjy.gamecommunity.fragment.main.FragmentHome;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangqingyou
 * Date: 2020/4/9
 * Des:
 */
public class MainActivity extends BaseActivity {
    private LinearLayout mLinearLayout;
    private LinearLayout mLlTab;
    private LinearLayout mLlFamily;

    @Override
    public Object getLayout() {
        return R.layout.activity_main;
    }

    private List<View> viewList = new ArrayList<>();

    @Override
    public void initView() {
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mLlTab = (LinearLayout) findViewById(R.id.ll_tab);
        mLlFamily = findViewById(R.id.ll_family);

        int childCount = mLlTab.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAtView = mLlTab.getChildAt(i);
            viewList.add(childAtView);
        }

        viewList.add(2, mLlFamily);//家族view添加到中间位置
    }

    private ArrayList<TabEntities> mTabEntities;//tab实体

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(true);//设置状态栏是否为浅色模式

        mTabEntities = new ArrayList();//tab实体


        mTabEntities.add(new TabEntities("发现", FragmentHome.class.getCanonicalName(), R.mipmap.home_select, R.mipmap.home_unselect));
        mTabEntities.add(new TabEntities("视频", FragmenVideo.class.getCanonicalName(), R.mipmap.video_select, R.mipmap.video_unselect));

        mTabEntities.add(new TabEntities("家族", FragmentFamily.class.getCanonicalName(), R.mipmap.family_select, R.mipmap.family_unselect));

        mTabEntities.add(new TabEntities("消息", FragmenMessage.class.getCanonicalName(), R.mipmap.message_select, R.mipmap.message_unselect));
        mTabEntities.add(new TabEntities("我的", FragmenPersonalCenter.class.getCanonicalName(), R.mipmap.personal_center_select, R.mipmap.personal_center_unselect));


        for (int i = 0; i < mTabEntities.size(); i++) {
            TabEntities tabEntities = mTabEntities.get(i);
            View view = viewList.get(i);

            ImageView imageView = view.findViewById(R.id.iv_image);
            //LoadingImageUtil 会有缓存
            // LoadingImageUtil.loadingImag(tabEntities.getTabUnselectImg(), imageView, false);
            imageView.setImageResource((Integer) tabEntities.getTabUnselectImg());
            TextView textView = view.findViewById(R.id.tv_text);
            textView.setText(tabEntities.getTabTitle());


        }


        //默认选中第一个
        TabEntities tabEntities = mTabEntities.get(0);
        selectTabView(tabEntities.getTabTitle());


    }

    @Override
    public void listener() {
        for (int i = 0; i < viewList.size(); i++) {
            viewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = v.findViewById(R.id.tv_text);
                    selectTabView(textView.getText().toString());

                }
            });
        }


    }

    private void selectTabView(String tabTitle) {
        for (int i = 0; i < viewList.size(); i++) {
            View view = viewList.get(i);
            TabEntities tabEntities = mTabEntities.get(i);

            ImageView imageView = view.findViewById(R.id.iv_image);
            TextView textView = view.findViewById(R.id.tv_text);
            if (textView.getText().toString().equals(tabTitle)) {
                imageView.setImageResource((Integer) tabEntities.getTabSelectImg());
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

                selectFragment(tabEntities.getTabTag());
            } else {
                imageView.setImageResource((Integer) tabEntities.getTabUnselectImg());
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.light_black));
            }

        }

    }

    /**
     * @param tabFragmentClass tabclass名
     * @param
     */
    public void selectFragment(String tabFragmentClass) {
        if (FragmentHome.class.getCanonicalName().equals(tabFragmentClass)) {
            showFragment(R.id.linearLayout, FragmentHome.class);
        } else if (FragmenVideo.class.getCanonicalName().equals(tabFragmentClass)) {
            showFragment(R.id.linearLayout, FragmenVideo.class);
        } else if (FragmentFamily.class.getCanonicalName().equals(tabFragmentClass)) {
            showFragment(R.id.linearLayout, FragmentFamily.class);
        } else if (FragmenMessage.class.getCanonicalName().equals(tabFragmentClass)) {
            showFragment(R.id.linearLayout, FragmenMessage.class);
        } else if (FragmenPersonalCenter.class.getCanonicalName().equals(tabFragmentClass)) {
            showFragment(R.id.linearLayout, FragmenPersonalCenter.class);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d("LogUtils", "音量加");
                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.d("LogUtils", "音量减");
                AudioManager mAudioManager2 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                mAudioManager2.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
                return true;
            case KeyEvent.KEYCODE_BACK:
                showExitDialog();
                return true;
        }
        return true;
    }

    private MaterialDialog titleMessgeMaterialDialog;
    private TextView mTvTitle;
    private TextView mTvText;
    private TextView mTvNo;
    private TextView mTvYes;

    public void showExitDialog() {
        if (titleMessgeMaterialDialog == null) {
            titleMessgeMaterialDialog = new MaterialDialog.Builder(getContext())
                    .cancelable(true)
                    .customView(R.layout.dialog_tips_title_messge, false)
                    .build();
            View customView = titleMessgeMaterialDialog.getCustomView();

            mTvTitle = (TextView) customView.findViewById(R.id.tv_title);
            mTvText = (TextView) customView.findViewById(R.id.tv_text);
            mTvNo = (TextView) customView.findViewById(R.id.tv_no);
            mTvYes = (TextView) customView.findViewById(R.id.tv_yes);


            mTvNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleMessgeMaterialDialog.dismiss();
                }
            });
            mTvYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleMessgeMaterialDialog.dismiss();
                    com.hjy.baseutil.AppUtils.finishAllActivities();
                }
            });
        }
        String title = "退出《" + AppUtils.getAppName() + "》";
        String text = "确定要退出么？";
        mTvTitle.setText(title);
        mTvText.setText(text);
        if (!titleMessgeMaterialDialog.isShowing())
            titleMessgeMaterialDialog.show();
    }
}
