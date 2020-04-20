package com.hjy.gamecommunity.activity.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseutil.countdownutil.CountdownTimerTask;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.login.ActivityPhoneLogin;

/**
 * Author: zhangqingyou
 * Date: 2020/4/20 11:18
 * Des:
 */
public class ActivitySplash extends BaseActivity implements View.OnClickListener {


    private ImageView mIvImage;

    @Override
    public Object getLayout() {
        return R.layout.activity_splash;

    }

    @Override
    public void initView() {
        mIvImage = findViewById(R.id.iv_image);
    }


    @Override
    public void initData() {
        /**
         * 这个问题有些尴尬，用开发工具直接运行安装是不会出现的，
         * 打包直接点击安装也是不会的，但是上传后直接下载就会出现这个问题，首次安装，
         * 不管进入哪个页面，只要按home键将软件置入后台，从任务管理切换回去是没有问题的，
         * 但是从桌面图标点击进入就会出现从新启动，其实也不是重新启动，因为之前的页面都在，
         * 只不过程序会按照启动页面重新走，这真的苦恼。幸亏网上自有大神在，解决方法是有的。
         *
         * 在启动页面创建的时候加上
         *
         * if (!isTaskRoot()) {
         * finish();
         * return;
         * }
         *
         * 这句代码就行了，isTaskRoot()是用来判断你的页面是不是最后的一个activity，
         * 因为这个是启动页面，只会在程序启动时进行，如果不是最后一个，证明程序出问题了。直接杀死这个页面就行。
         */
        if (!isTaskRoot()) {
            finish();
            return;
        }

        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(false);//设置状态栏是否为浅色模式

        mIvImage.setImageResource(R.drawable.ic_launcher_background);


        CountdownTimerTask countdownTimerTask = new CountdownTimerTask();
        countdownTimerTask.stateCountdownExecution(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getContext(), ActivityGuide.class));
                finish();
            }
        }, 3f);
    }

    @Override
    public void listener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }


}
