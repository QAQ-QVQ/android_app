package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjy.baserequest.bean.GameListBean;
import com.hjy.baserequest.bean.RoleListBean;
import com.hjy.baserequest.bean.ServiceListBean;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.main.MainActivity;
import com.hjy.gamecommunity.adapter.RoleSelectAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-》绑定游戏角色 ->选择游戏角色
 * Date: 2020/7/02 14:27
 * Des:
 *
 * @author dy
 */
public class ActivityBindSelectRole extends BaseActivitySubordinate {
    /**
     * 游戏头像
     */
    private ImageView gameIcon;
    /**
     * 游戏名称
     */
    private TextView gameName;
    /**
     * 区服名称
     */
    private TextView serviceName;
    /**
     * 联系客服，找回账号
     */
    private Button find;
    /**
     * 没账号，前往游戏创建
     */
    private Button create;
    /**
     * 绑定
     */
    private Button bind;
    /**
     * 游戏
     */
    private GameListBean.DataBean game;
    /**
     * 区服
     */
    private ServiceListBean.DataBean service;
    /**
     * 游戏信息
     */
    private TextView roleMessage;
    /**
     * 角色列表布局
     */
    private RecyclerView roleRecyclerView;
    /**
     * 角色列表适配器
     */
    private RoleSelectAdapter roleSelectAdapter;
    /**
     * 没角色
     */
    private ConstraintLayout noRole;
    /**
     * 有角色
     */
    private ConstraintLayout yesRole;
    /**
     * 账号列表
     */
    private RoleListBean roleList;

    @Override
    public Object getLayout() {
        return R.layout.activity_bind_select_role;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "绑定游戏角色");
        gameIcon = findViewById(R.id.bind_role_game_icon);
        gameName = findViewById(R.id.bind_role_game_name);
        serviceName = findViewById(R.id.bind_role_game_service);
        find = findViewById(R.id.bind_role_find);
        create = findViewById(R.id.bind_role_create);
        roleMessage = findViewById(R.id.bind_role_message);
        roleRecyclerView = findViewById(R.id.bind_role_recyclerview);
        noRole = findViewById(R.id.bind_no_role);
        yesRole = findViewById(R.id.bind_role_layout);
        bind = findViewById(R.id.bind_role_tobind);
        find.setBackground(setClickDrawable());
        create.setBackground(setClickDrawable());
        bind.setBackground(setCanClickDrawable());
    }

    @Override
    public void initData() {
        game = (GameListBean.DataBean) getIntent().getSerializableExtra("game");
        service = (ServiceListBean.DataBean) getIntent().getSerializableExtra("service");
        Request.getInstance().getRoleList(game.getId(), service.getId(), roleListJsonEntityCallback);

    }

    int mPosion = 0;

    @Override
    public void listener() {
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/7/2 联系客服

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/7/2 创建
            }
        });

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/7/3 绑定
                Request.getInstance().toBindRole(roleList.getData().get(mPosion).getId(), bindRoleJsonEntityCallback);
            }
        });
    }

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
    /**
     * 用户列表
     */
    JsonEntityCallback roleListJsonEntityCallback = new JsonEntityCallback<RoleListBean>(RoleListBean.class) {

        @Override
        protected void onSuccess(RoleListBean roleListBean) {
            if (roleListBean.getCode() == 200) {
                String message;
                if (roleListBean.getData().size() != 0) {
//                    RoleListBean.DataBean dataBean = new RoleListBean.DataBean(0,"0","11111","2222","1","2222",1);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);
//                    roleListBean.getData().add(dataBean);

                    roleList = roleListBean;
                    for (int i = 0; i < roleListBean.getData().size(); i++) {
                        roleListBean.getData().get(i).setIs_default(1);
                    }
                    roleSelectAdapter = new RoleSelectAdapter(roleListBean.getData());
                    roleSelectAdapter.setOnSelectListener(new RoleSelectAdapter.OnSelectListener() {
                        @Override
                        public void OnSelect(int posion) {
                            mPosion = posion;
                        }

                        @Override
                        public void LongClick(int posion) {

                        }
                    });
                    roleRecyclerView.setAdapter(roleSelectAdapter);
                    roleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    message = "当前系统检测您有以下角色信息";
                    noRole.setVisibility(View.GONE);
                    yesRole.setVisibility(View.VISIBLE);
                } else {
                    LoadingImageUtil.loadingImag(game.getIcon(), gameIcon, false);
                    gameName.setText("游戏：" + game.getName());
                    serviceName.setText("区服：" + service.getCp_server_name());
                    message = "您的账号（" + UserDataContainer.getInstance().getUserData().getPhone() + "）内没有检测到支持绑定角色";
                    noRole.setVisibility(View.VISIBLE);
                    yesRole.setVisibility(View.GONE);
                }
                roleMessage.setText(message);
            }else {
                ToastUtil.tost(roleListBean.getMsg());
            }
        }
    };
    /**
     * 绑定角色
     */
    JsonEntityCallback bindRoleJsonEntityCallback = new JsonEntityCallback<RoleListBean>(RoleListBean.class) {

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
}
