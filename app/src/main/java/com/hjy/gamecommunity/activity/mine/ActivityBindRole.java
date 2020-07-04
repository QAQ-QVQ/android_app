package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.Utils;
import com.hjy.baserequest.bean.GameListBean;
import com.hjy.baserequest.bean.MyGameInfoBean;
import com.hjy.baserequest.bean.ServiceListBean;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.FlowTagAdapter;
import com.xuexiang.xui.adapter.listview.OnListItemListener;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-》绑定游戏角色
 * Date: 2020/7/01 12:04
 * Des:
 *
 * @author dy
 */
public class ActivityBindRole extends BaseActivitySubordinate {
    private static final String TAG = "ActivityBindRole";
    /**
     * 选择游戏
     */
    private FlowTagLayout chooseGame;
    /**
     * 选择区服
     */
    private FlowTagLayout chooseService;
    /**
     * 查找角色
     */
    private Button fondRole;
    /**
     * 游戏更多按钮
     */
    private TextView gameMore;
    /**
     * 区服更多按钮
     */
    private TextView serviceMore;
    /**
     * 游戏更多图标
     */
    private ImageView gameMoreIv;
    /**
     * 区服更多图标
     */
    private ImageView serviceMoreIv;
    /**
     * 游戏适配器
     */
    private FlowTagAdapter gameAdapter;
    /**
     * 区服适配器
     */
    private FlowTagAdapter serviceAdapter;
    /**
     * 游戏列表
     */
    private GameListBean gameList;
    /**
     * 区服列表
     */
    private ServiceListBean serviceList;
    /**
     * 绑定区服布局
     */
    private ConstraintLayout bindService;
    /**
     * 加载弹框
     */
    private MiniLoadingDialog mMiniLoadingDialog;
    private boolean gameFlag = false, serviceFlag = false, nextFlag = false;

    @Override
    public Object getLayout() {
        return R.layout.activity_bind_role;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "绑定游戏角色");
        chooseGame = findViewById(R.id.bind_choose_game);
        chooseService = findViewById(R.id.bind_choose_service);
        fondRole = findViewById(R.id.bind_search_role);
        gameMore = findViewById(R.id.bind_choose_game_more);
        gameMoreIv = findViewById(R.id.bind_choose_game_more_iv);
        serviceMore = findViewById(R.id.bind_choose_service_more);
        serviceMoreIv = findViewById(R.id.bind_choose_service_more_iv);
        bindService = findViewById(R.id.bind_service);
        mMiniLoadingDialog = WidgetUtils.getMiniLoadingDialog(getContext());
        fondRole.setBackground(setCanClickDrawable());
    }

    @Override
    public void initData() {
        mMiniLoadingDialog.show();
        Request.getInstance().getGameList(gameInfoJsonEntityCallback);
    }

    @Override
    public void listener() {
        fondRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameListBean.DataBean game = new GameListBean.DataBean();
                ServiceListBean.DataBean service = new ServiceListBean.DataBean();
                if (nextFlag) {
                    for (int i : chooseGame.getSelectedIndexs()) {
//                        game = gameAdapter.getItem(i);
                        game = gameList.getData().get(i);
                    }
                    for (int i : chooseService.getSelectedIndexs()) {
//                        service = serviceAdapter.getItem(i);
                        service = serviceList.getData().get(i);
                    }
//                    ToastUtil.tost("onClick: " + game + "////" + service);
                    Intent intent = new Intent(getContext(), ActivityBindSelectRole.class);
                    intent.putExtra("game", game);
                    intent.putExtra("service", service);
                    startActivity(intent);
                } else {
                    ToastUtil.tost("请选择游戏和区服");
                }
            }
        });
        chooseGame.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                mMiniLoadingDialog.show();
                Request.getInstance().getServiceList(String.valueOf(gameList.getData().get(position).getId()), serviceInfoJsonEntityCallback);
            }
        });
        chooseService.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {

            }
        });
        //游戏更多按钮
        gameMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameFlag = !gameFlag;
                ViewGroup.LayoutParams layoutParams = chooseGame.getLayoutParams();
                if (gameFlag) {
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    chooseGame.setLayoutParams(layoutParams);
                    gameMore.setText("收起");
                    gameMoreIv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.qy_close));
                } else {
//                    layoutParams.height = SizeUtils.dp2px(65);
                    layoutParams.height = gameAdapter.getHeight() * 2;
                    chooseGame.setLayoutParams(layoutParams);
                    gameMore.setText("更多");
                    gameMoreIv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.qy_more));
                }
            }
        });

        //区服更多按钮
        serviceMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceFlag = !serviceFlag;
                ViewGroup.LayoutParams layoutParams = chooseService.getLayoutParams();
                if (serviceFlag) {
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    chooseService.setLayoutParams(layoutParams);
                    serviceMore.setText("收起");
                    serviceMoreIv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.qy_close));
                } else {
//                    layoutParams.height = SizeUtils.dp2px(65);
                    layoutParams.height = gameAdapter.getHeight() * 2;
                    chooseService.setLayoutParams(layoutParams);
                    serviceMore.setText("更多");
                    serviceMoreIv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.qy_more));
                }
            }
        });
    }

    /**
     * 我的游戏
     */
    JsonEntityCallback gameInfoJsonEntityCallback = new JsonEntityCallback<GameListBean>(GameListBean.class) {

        @Override
        protected void onSuccess(GameListBean gameListBean) {
            if (gameListBean.getCode() == 200) {
                gameList = gameListBean;
                List gameName = new ArrayList();
                for (GameListBean.DataBean game : gameListBean.getData()) {
                    gameName.add(game.getName());
                }
                gameAdapter = new FlowTagAdapter(getContext());
                chooseGame.setAdapter(gameAdapter);
                chooseGame.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                gameAdapter.addTags(gameName);
                gameAdapter.setSelectedPositions(0);
                mMiniLoadingDialog.dismiss();
            }

        }
    };
    /**
     * 区服数据
     */
    JsonEntityCallback serviceInfoJsonEntityCallback = new JsonEntityCallback<ServiceListBean>(ServiceListBean.class) {

        @Override
        protected void onSuccess(ServiceListBean serviceListBean) {
            if (serviceListBean.getCode() == 200) {
                List serviceName = new ArrayList();
                serviceList = serviceListBean;
                if (serviceListBean.getData().size() == 0) {
                    nextFlag = false;
                } else {
                    nextFlag = true;
                    for (ServiceListBean.DataBean service : serviceListBean.getData()) {
                        serviceName.add(service.getCp_server_name());
                    }
                    serviceAdapter = new FlowTagAdapter(getContext());
                    chooseService.setAdapter(serviceAdapter);
                    chooseService.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
                    serviceAdapter.addTags(serviceName);
                    serviceAdapter.setSelectedPositions(0);

                }
                mMiniLoadingDialog.dismiss();
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
