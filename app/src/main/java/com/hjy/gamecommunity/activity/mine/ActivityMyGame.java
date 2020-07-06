package com.hjy.gamecommunity.activity.mine;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjy.baserequest.bean.MyGameInfoBean;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.MyGameAdapter;

import java.util.List;

/**
 * 我的-》我的游戏
 * Date: 2020/7/04 16:34
 * Des:
 *
 * @author dy
 */
public class ActivityMyGame extends BaseActivitySubordinate {
    /**
     * 游戏图标
     */
    private ImageView gameIcon;
    /**
     * 游戏名称
     */
    private TextView gameName;
    /**
     * 游戏列表
     */
    private RecyclerView gameRecyclerView;
    /**
     * 我玩过的列表
     */
    private ConstraintLayout playedLayout;
    /**
     * 游戏列表适配器
     */
    private MyGameAdapter myGameAdapter;
    /**
     * 游戏列表
     */
    private List<MyGameInfoBean.DataBean> myGameInfoList;
    @Override
    public Object getLayout() {
        return R.layout.activity_my_game;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "我的游戏");
        gameIcon = findViewById(R.id.mine_game_icon_ImageView);
        gameName = findViewById(R.id.mine_game_name);
        gameRecyclerView = findViewById(R.id.mine_game_played_recyclerview);
        playedLayout = findViewById(R.id.mine_game_played);
    }

    @Override
    public void initData() {
        Request.getInstance().getMyGameInfo(gameInfoJsonEntityCallback);
    }

    @Override
    public void listener() {

    }
    /**
     * 我的游戏
     */
    JsonEntityCallback gameInfoJsonEntityCallback = new JsonEntityCallback<MyGameInfoBean>(MyGameInfoBean.class) {

        @Override
        protected void onSuccess(MyGameInfoBean myGameInfoBean) {
            if (myGameInfoBean.getCode() == 200) {
//                MyGameInfoBean.DataBean dataBean = new MyGameInfoBean.DataBean(0,"11111111", null);
//                myGameInfoBean.getData().add(dataBean);
//                myGameInfoBean.getData().add(dataBean);
//                myGameInfoBean.getData().add(dataBean);
//                myGameInfoBean.getData().add(dataBean);
//                myGameInfoBean.getData().add(dataBean);
                if (myGameInfoBean.getData().size() == 0) {
                    // TODO: 2020/7/6 没有游戏时
                    ToastUtil.tost("no game");
                } else if (myGameInfoBean.getData().size() == 1){
                    playedLayout.setVisibility(View.INVISIBLE);
                    LoadingImageUtil.loadingImag(myGameInfoBean.getData().get(0).getIcon(),gameIcon,true);
                    gameName.setText(myGameInfoBean.getData().get(0).getName());
                }else {
                    myGameInfoList = myGameInfoBean.getData();
                    myGameInfoList.remove(0);
                    playedLayout.setVisibility(View.VISIBLE);
                    LoadingImageUtil.loadingImag(myGameInfoBean.getData().get(0).getIcon(),gameIcon,true);
                    gameName.setText(myGameInfoBean.getData().get(0).getName());
                    myGameAdapter = new MyGameAdapter(myGameInfoList);
                    gameRecyclerView.setAdapter(myGameAdapter);
                    gameRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                }
            }else {
                ToastUtil.tost(myGameInfoBean.getMsg());
            }
        }
    };
}
