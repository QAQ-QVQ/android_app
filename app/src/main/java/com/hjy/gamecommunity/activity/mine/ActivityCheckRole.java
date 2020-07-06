package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hjy.baserequest.bean.RoleListBean;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.main.MainActivity;
import com.hjy.gamecommunity.adapter.RoleSelectAdapter;

/**
 * 我的-》切换绑定角色
 * Date: 2020/7/02 14:27
 * Des:
 *
 * @author dy
 */
public class ActivityCheckRole extends BaseActivitySubordinate {
    /**
     * 角色列表
     */
    private RecyclerView roleRecyclerView;
    /**
     * 添加
     */
    private ImageView add;
    /**
     * 角色选择适配
     */
    private RoleSelectAdapter roleSelectAdapter;
    /**
     * 绑定角色列表
     */
    private RoleListBean roleList;
    /**
     * 切换
     */
    private Button toSwitch;
    private int mPosion;

    @Override
    public Object getLayout() {
        return R.layout.activity_check_role;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "切换游戏角色");
        roleRecyclerView = findViewById(R.id.switch_role_recyclerview);
        add = findViewById(R.id.tv_text_submit_bar);
        toSwitch = findViewById(R.id.switch_role_toswitch);
        toSwitch.setBackground(setCanClickDrawable());
    }

    @Override
    public void initData() {
        Request.getInstance().getBindRoleList(bindRoleListJsonEntityCallback);
    }

    @Override
    public void listener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityBindRole.class));
            }
        });
        toSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request.getInstance().switchRole(roleList.getData().get(mPosion).getId(), switchRoleJsonEntityCallback);
            }
        });
    }

    /**
     * 绑定角色列表
     */
    JsonEntityCallback bindRoleListJsonEntityCallback = new JsonEntityCallback<RoleListBean>(RoleListBean.class) {

        @Override
        protected void onSuccess(RoleListBean roleListBean) {
            if (roleListBean.getCode() == 200) {
//                RoleListBean.DataBean dataBean = new RoleListBean.DataBean(0,"0","11111","2222","1","2222",1);
//                roleListBean.getData().add(dataBean);
//                roleListBean.getData().add(dataBean);
                roleList = roleListBean;
                roleSelectAdapter = new RoleSelectAdapter(roleListBean.getData());
                roleRecyclerView.setAdapter(roleSelectAdapter);
                roleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                roleSelectAdapter.setOnSelectListener(new RoleSelectAdapter.OnSelectListener() {
                    @Override
                    public void OnSelect(int posion) {
                        mPosion = posion;
                    }

                    /**
                     * @param posion
                     */
                    @Override
                    public void LongClick(int posion) {
                        // TODO: 2020/7/4 长按
                        Request.getInstance().removeRole(roleList.getData().get(posion).getId(), removeRoleJsonEntityCallback);
                    }
                });
            }else {
                ToastUtil.tost(roleListBean.getMsg());
            }
        }
    };
    /**
     * 切换绑定角色
     */
    JsonEntityCallback switchRoleJsonEntityCallback = new JsonEntityCallback<RoleListBean>(RoleListBean.class) {

        @Override
        protected void onSuccess(RoleListBean roleListBean) {
            switch (roleListBean.getCode()) {
                case 200:
                    startActivity(new Intent(getContext(), MainActivity.class));
                    break;
                case 101:
                    ToastUtil.tost(roleListBean.getMsg());
                    break;
                default:
                    ToastUtil.tost(roleListBean.getMsg());
                    break;
            }
        }
    };

    /**
     * 解除绑定
     */
    JsonEntityCallback removeRoleJsonEntityCallback = new JsonEntityCallback<RoleListBean>(RoleListBean.class) {

        @Override
        protected void onSuccess(RoleListBean roleListBean) {
            switch (roleListBean.getCode()) {
                case 200:
                    initData();
                    break;
                case 101:
                    ToastUtil.tost(roleListBean.getMsg());
                    break;
                default:
                    ToastUtil.tost(roleListBean.getMsg());
                    break;
            }
        }
    };

    private StateListDrawable setCanClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                .setClickColorBg(0)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_red_light))
                .buid();
        return stateListDrawable;
    }
}
