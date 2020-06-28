package com.hjy.gamecommunity.activity.mine;

import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.gamecommunity.R;

/**
 * 设置
 * Date: 2020/6/23 16:35
 * Des:
 * @author dy
 */
public class ActivitySet extends BaseActivity {
    /**
     * 标题
     */
    private Toolbar toolbar;
    /**
     * 注销
     */
    private Button logout;
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
        toolbar = findViewById(R.id.mine_set_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.qy_mine_toolbar_back,null));
        logout = findViewById(R.id.mine_logout_button);
        logout.setBackground(setClickDrawable());
    }

    @Override
    public void initData() {

    }

    @Override
    public void listener() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/6/23 退出登录
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
