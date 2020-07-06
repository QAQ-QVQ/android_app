package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baserequest.bean.MineSetBean;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseui.ui.view.dialog.TextTipsDialog;
import com.hjy.baseutil.CacheUtil;
import com.hjy.baseutil.GlideCacheUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.App;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.MineSetAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的-》设置
 * Date: 2020/6/23 16:35
 * Des:
 *
 * @author dy
 */
public class ActivitySet extends BaseActivitySubordinate {
    /**
     * 注销
     */
    private Button logout;
    /**
     * 选项
     */
    private RecyclerView setItem;
    /**
     * 设置适配器
     */
    private MineSetAdapter setAdapter;
    /**
     * 设置列表
     */
    private ArrayList<MineSetBean> setList;

    @Override
    public Object getLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "设置");
        logout = findViewById(R.id.mine_logout_button);
        logout.setBackground(setClickDrawable());
        setItem = findViewById(R.id.mine_set_item);
    }


    /**
     *
     */
    String icon,nickname;
    @Override
    public void initData() {
        if (!UserDataContainer.getInstance().getUserData().getAvatar().isEmpty()){
            icon = UserDataContainer.getInstance().getUserData().getAvatar();
        }else {
            icon = "无头像";
        }
        if (!UserDataContainer.getInstance().getUserData().getNickname().isEmpty()){
            nickname = UserDataContainer.getInstance().getUserData().getNickname();
        }else {
            nickname = "暂无昵称";
        }
        MineSetBean userMsg = new MineSetBean(icon, nickname, "", 0);
        MineSetBean security = new MineSetBean("", "账号与安全", "", 0);
        MineSetBean aboutUs = new MineSetBean("", "关于我们", "", 0);
        // TODO: 2020/6/30 获取版本信息
        MineSetBean checkUpdate = new MineSetBean("", "检查更新", "当前版本号V1.0.0", 0);

        MineSetBean clearCache = new MineSetBean("", "清除缓存", getCacheSize(), 0);
        setList = new ArrayList();
        setList.add(userMsg);
        setList.add(security);
        setList.add(aboutUs);
        setList.add(checkUpdate);
        setList.add(clearCache);
        setAdapter = new MineSetAdapter(setList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        setItem.setLayoutManager(linearLayoutManager);
        setItem.setAdapter(setAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserDataContainer.getInstance().isLogin()){
            initData();
            listener();
        }
    }

    @Override
    public void listener() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/6/23 退出登录
            }
        });

        setAdapter.setOnclickListener(new MineSetAdapter.OnclickListener() {
            /**
             * @param item 点击的内容
             */
            @Override
            public void OnClick(int item) {
                switch (item) {
                    //个人资料
                    case 0:
                        startActivity(new Intent(getContext(), ActivityUserMessage.class));
                        break;
                    //账号与安全
                    case 1:
                        startActivity(new Intent(getContext(), ActivitySecurity.class));
                        break;
                    //关于我们
                    case 2:
                        startActivity(new Intent(getContext(), ActivityAboutUs.class));
                        break;
                    //检查更新
                    case 3:
                        checkUpdate();
                        break;
                    //清除缓存
                    case 4:
                        clearCache();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void LongOnClick() {

            }
        });
    }

    /**
     * 单背景样式
     *
     * @return
     */
    private StateListDrawable setClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                .setColorBorder(ContextCompat.getColor(this, R.color.bui_red_light))
                .setBorderWidth(1)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_white))
                .buid();
        return stateListDrawable;
    }

    private void checkUpdate() {
        // TODO: 2020/6/24 检查更新
        TextTipsDialog textTipsDialog = new TextTipsDialog(getActivity());
        textTipsDialog.setTitle("有新版本更新");
        textTipsDialog.setText("内容");
        textTipsDialog.setOnLeftButtonClickListener("取消", new TextTipsDialog.OnClickListener() {
            @Override
            public void onClick(TextTipsDialog textTipsDialog, View v) {
                textTipsDialog.dismiss();
            }
        });
        textTipsDialog.setOnRightButtonClickListener("升级", new TextTipsDialog.OnClickListener() {
            @Override
            public void onClick(TextTipsDialog textTipsDialog, View v) {
                textTipsDialog.dismiss();
            }
        });
        textTipsDialog.show();
    }

    /**
     * @return 获取缓存大小
     */
    private String getCacheSize() {
        String cacheSize = GlideCacheUtil.getInstance().getCacheSize(getActivity());
        String dirSize = CacheUtil.getDirSize(CacheUtil.getStringDataPath());
        if (cacheSize.contains("MB")) {
            String mb = cacheSize.replace("MB", "");
            Double aDouble = Double.valueOf(mb);
            if (aDouble >= 100.00) {
//                mTvCacheSize.setText("图片：" + cacheSize + "\n其他：" + dirSize);
            }
        }
        return cacheSize;
    }

    /**
     * @return 清除缓存
     */
    private void clearCache() {
        // TODO: 2020/6/24 清除缓存
        TextTipsDialog textTipsDialog = new TextTipsDialog(getActivity());
        textTipsDialog.setTitle("清除缓存");
        textTipsDialog.setText("确定要清除缓存吗");
        textTipsDialog.setOnLeftButtonClickListener("取消", new TextTipsDialog.OnClickListener() {
            @Override
            public void onClick(TextTipsDialog textTipsDialog, View v) {
                textTipsDialog.dismiss();
            }
        });
        textTipsDialog.setOnRightButtonClickListener("清除", new TextTipsDialog.OnClickListener() {
            @Override
            public void onClick(TextTipsDialog textTipsDialog, View v) {
                //        CacheUtil.delteDirString(CacheUtil.getStringDataPath());
                GlideCacheUtil.getInstance().clearImageAllCache(getActivity());
                ToastUtil.tost("清除成功");
                setList.get(4).setMsg(getCacheSize());
                setAdapter.notifyDataSetChanged();
                textTipsDialog.dismiss();
            }
        });
        textTipsDialog.show();
    }
}
