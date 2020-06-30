package com.hjy.gamecommunity;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.MetaDataUtils;
import com.bumptech.glide.Glide;
import com.hjy.baserequest.RequestManage;
import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baseui.BaseUIManage;
import com.hjy.baseutil.GlideCacheUtil;
import com.hjy.baseutil.UtilsManage;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.xuexiang.xui.XUI;

import java.util.Locale;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jpush.android.api.JPushInterface;


/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */

public class App extends Application {
    private static Application application;

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initModule();//
        seetingJPush();//极光推送
        jAnalyticsInterface();//极光页面统计
        jshare();//极光分享
        initBugly();
        initTUIKit();//即时通信 IM


        //Fresco 的封装，快速上手，图像后处理，超大图高清预览，缩小放大，双击放大等一一俱全
       // frescoInit();

    }

    /**
     * 即时通信 IM
     */
    private void initTUIKit() {
        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        //configs.setSdkConfig(new TIMSdkConfig(SDKAPPID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        /*
         * TUIKit 的初始化函数
         *
         * @param context  应用的上下文，一般为对应应用的 ApplicationContext
         * @param sdkAppID 您在腾讯云注册应用时分配的 SDKAppID
         * @param configs  TUIKit 的相关配置项，一般使用默认即可，需特殊配置参考 API 文档
         */
        // TUIKit.init(this, "SDKAPPID", configs);
    }


    /**
     * 获取Application
     */
    public static Application getApplication() {
        return application;
    }


    /**
     * 极光推送
     */
    private void seetingJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        setAlias();//极光绑定用户
    }

    /**
     * 极光绑定用户
     */
    public static void setAlias() {
        UserData userData = UserDataContainer.getInstance().getUserData();
        if (userData != null) {
            JPushInterface.setAlias(getApplication(), Integer.parseInt(userData.getUser_id()), userData.getUser_id());
        }
    }


    /**
     * 极光页面统计
     */
    private void jAnalyticsInterface() {
        JAnalyticsInterface.setDebugMode(true);//设置是否开启debug模式。true则会打印更多的日志信息。建议在init接口之前调用。
        JAnalyticsInterface.init(this);//初始化接口。建议在Application的onCreate中调用
        JAnalyticsInterface.initCrashHandler(this);//开启crashlog日志上报
    }

    /**
     * 极光分享
     */
    private void jshare() {

        PlatformConfig platformConfig = new PlatformConfig();
        platformConfig.setQQ("appId", "appKey");
        platformConfig.setWechat("appId", "appSecret");
        platformConfig.setSinaWeibo("appKey", "appSecret", "redirectUrl");
        JShareInterface.init(this, platformConfig);
        JShareInterface.setDebugMode(true);

    }

    /**
     *
     */
    private void initModule() {
        //工具模组初始化
        UtilsManage.init(this);
        //ui模组初始化
        BaseUIManage.init(this);
        //XUI库 (sdk尽量別使用)
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志

        //网络请求模组初始化
        RequestManage.init(this);
        RequestManage.setDEBUG(true);

    }


    /**
     * Fresco 的封装，快速上手，图像后处理，超大图高清预览，缩小放大，双击放大等一一俱全
     */
//    private void frescoInit() {
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
//                .setDownsampleEnabled(true)
//                .build();
//        Fresco.initialize(this, config);
//    }


    /**
     * Bugly配置渠道信息已失效 先初始化Tinker再初始化Bugly
     */
    private static void initBugly() {
        //Tinker
        // 设置是否显示弹窗提示用户重启
        Beta.canNotifyUserRestart = true;
        // 设置是否显示弹窗提示用户重启
        Beta.canNotifyUserRestart = true;
        //自动检查更新开关
        Beta.autoCheckUpgrade = true;
        //自动初始化开关
        Beta.autoInit = false;

        String jpushChannel = MetaDataUtils.getMetaDataInApp("JPUSH_CHANNEL");
        Log.d("initTinker", "appChannel:" + jpushChannel);
        Beta.appChannel = jpushChannel;


        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Log.d("BetaPatchListener", "补丁下载地址");
                Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                String format = String.format(Locale.getDefault(), "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength));

                Log.d("BetaPatchListener", format);

                Toast.makeText(getApplication(), format,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Log.d("BetaPatchListener", "补丁下载成功");
                Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Log.d("BetaPatchListener", "补丁下载失败");
                Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                Log.d("BetaPatchListener", "补丁应用成功");
                Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Log.d("BetaPatchListener", "补丁应用失败");
                Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {
                Log.d("BetaPatchListener", "补丁回滚");
                Toast.makeText(getApplication(), "补丁回滚", Toast.LENGTH_SHORT).show();
            }
        };

        /*注册下载监听，监听下载事件*/
        Beta.registerDownloadListener(new DownloadListener() {
            @Override
            public void onReceive(DownloadTask downloadTask) {
                GlideCacheUtil.getInstance().clearImageAllCache(application);
            }

            @Override
            public void onCompleted(DownloadTask downloadTask) {
                GlideCacheUtil.getInstance().clearImageAllCache(application);
            }

            @Override
            public void onFailed(DownloadTask downloadTask, int i, String s) {

            }
        });
        //必须要所有配置设置完毕才 安装tinker
        Beta.installTinker();

        Bugly.init(getApplication(), "eb4d356ac8", true);

        Beta.init(getApplication(), true);
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}


