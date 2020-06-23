package com.hjy.gamecommunity.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.MetaDataUtils;
import com.hjy.baseutil.ToastUtil;
import com.hjy.baseutil.UtilsManage;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.dialog.BottomPopup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.qqmodel.QZone;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatMoments;
import cn.jiguang.share.weibo.SinaWeibo;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */

public class ShareUtil {

    private static ShareUtil shareUtil;
    private SharingResultsListener sharingResultsListener; //分享结果监听


    public ShareUtil() {

    }


    public static ShareUtil getInstance() {
        if (shareUtil == null) {
            synchronized (ShareUtil.class) {
                shareUtil = new ShareUtil();
            }
        }
        return shareUtil;
    }


    /**
     * 标准分享格式
     *
     * @return
     */

    public static ShareParams getShareParams(String titles, String text, String url) {
        String app_name = MetaDataUtils.getMetaDataInApp("APP_NAME");
        ShareParams shareParams = new ShareParams();
        shareParams.setTitle(titles);
        shareParams.setText(text);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(url);//必须
        // shareParams.setImageData(ImageUtils.getBitmap(R.mipmap.ic_launcher));
        String packageName = UtilsManage.getApplication().getPackageName();

        return shareParams;
    }


    /**
     * 分享纯图
     *
     * @param imageData
     * @return
     */
    public static ShareParams getShareParamImageDatas(Bitmap imageData) {
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setImageData(imageData);
        return shareParams;
    }

    /**
     * 分享纯图
     *
     * @return
     */
    public static ShareParams getShareParamsImageUrl(String imageUrl) {
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setImageUrl(imageUrl);
        return shareParams;
    }

    /**
     * 分享纯图
     *
     * @return
     */
    public static ShareParams getShareParamsImagePath(String imagePath) {
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setImagePath(imagePath);
        return shareParams;
    }


    /**
     * 调用系统分享
     * <p>
     * 只分享图片
     *
     * @param share_mediaString 分享到那些平台 1：QQ，2：微信，3：朋友圈   4：QQ空间  5：新浪微博
     * @param bitmap
     */
    public void shareImg(String share_mediaString, Bitmap bitmap) {
        Intent intent = new Intent();
        // ComponentName cn = new ComponentName("com.boya.shareassistant", "com.boya.shareassistant.MainActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//需要加这个不然会报错
        // intent.setComponent(cn);
        intent.putExtra("share_mediaString", share_mediaString);
        intent.putExtra("umImage", "umImage");
        if (bitmap != null) {
            ImageUtils.save(bitmap,
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/share_image.png",
                    Bitmap.CompressFormat.PNG
            );
        }
        UtilsManage.getApplication().startActivity(intent);
    }

    /**
     * 分享及加载动画
     */
    /**
     * @param activity
     * @param
     */
    private BottomPopup shareBottomPopupDialog;
    private ShareParams shareParams;
    private List<String> platformName;

    public void share_Dialog(final Activity activity, View rootView, ShareParams shareParams, SharingResultsListener sharingResultsListener) {
        if (platformName == null) {
            platformName = new ArrayList<>();
            platformName.add(QQ.Name);
            platformName.add(QZone.Name);
            platformName.add(Wechat.Name);
            platformName.add(WechatMoments.Name);
            platformName.add(SinaWeibo.Name);
        }
        share_Dialog(activity, rootView, shareParams, platformName, sharingResultsListener);
    }

    public void share_Dialog(final Activity activity, View rootView, ShareParams shareParams, List<String> platformName, SharingResultsListener sharingResultsListener) {
        this.shareParams = shareParams;
        this.sharingResultsListener = sharingResultsListener;
        //分享弹窗
        if (shareBottomPopupDialog == null) {
            shareBottomPopupDialog = new BottomPopup(activity, ViewSeting.getScreenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        listener(shareBottomPopupDialog.getViewsList(), platformName);
        shareBottomPopupDialog.show(rootView);
    }

    /**
     * 分享及加载动画
     * 不带面板
     *
     * @param
     */


    public void share_Dialog2(String name, ShareParams shareParams, SharingResultsListener sharingResultsListener) {
        this.sharingResultsListener = sharingResultsListener;
        JShareInterface.share(name, shareParams, mPlatActionListener);
    }

    private void listener(List<View> views, List<String> platformName) {
        if (platformName.contains(QQ.Name)) {

            views.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ShareUtil", "QQ");
                    if (JShareInterface.isClientValid(QQ.Name)) {
                        JShareInterface.share(QQ.Name, shareParams, mPlatActionListener);
                    } else {
                        ToastUtil.tost("分享无效，请检查QQ是否安装！");
                    }
                }
            });
        } else {
            views.get(0).setVisibility(View.GONE);
        }


        if (platformName.contains(QZone.Name)) {
            views.get(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ShareUtil", "qq空间");
                    if (JShareInterface.isClientValid(QZone.Name)) {
                        JShareInterface.share(QZone.Name, shareParams, mPlatActionListener);
                    } else {
                        ToastUtil.tost("分享无效，请检查QQ是否安装！");
                    }
                }
            });
        } else {
            views.get(1).setVisibility(View.GONE);
        }

        if (platformName.contains(Wechat.Name)) {
            views.get(2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ShareUtil", "微信");
                    if (JShareInterface.isClientValid(Wechat.Name)) {
                        JShareInterface.share(Wechat.Name, shareParams, mPlatActionListener);
                    } else {
                        ToastUtil.tost("分享无效，请检查微信是否安装！");
                    }

                }
            });
        } else {
            views.get(2).setVisibility(View.GONE);
        }

        if (platformName.contains(WechatMoments.Name)) {
            views.get(3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ShareUtil", "微信朋友圈");
                    if (JShareInterface.isClientValid(WechatMoments.Name)) {
                        JShareInterface.share(WechatMoments.Name, shareParams, mPlatActionListener);
                    } else {
                        ToastUtil.tost("分享无效，请检查微信是否安装！");
                    }

                }
            });
        } else {
            views.get(3).setVisibility(View.GONE);
        }
        if (platformName.contains(SinaWeibo.Name)) {
            views.get(4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ShareUtil", "微博");
                    if (JShareInterface.isClientValid(SinaWeibo.Name)) {
                        JShareInterface.share(SinaWeibo.Name, shareParams, mPlatActionListener);
                    } else {
                        ToastUtil.tost("分享无效，请检查微博是否安装！");
                    }

                }
            });
        } else {
            views.get(4).setVisibility(View.GONE);
        }

        //取消
        views.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ShareUtil", "取消分享");
                shareBottomPopupDialog.dismiss();
            }
        });
    }


    /**
     * 分享结果监听
     */
    public interface SharingResultsListener {
        void onComplete(Platform platform, int action, HashMap<String, Object> data);

        void onError(Platform platform, int action, int errorCode, Throwable error);

        void onCancel(Platform platform, int action);
    }


    /**
     * 分享结果监听
     */
    private PlatActionListener mPlatActionListener = new PlatActionListener() {
        @Override
        public void onComplete(final Platform platform, final int action, final HashMap<String, Object> data) {
            Log.d("mPlatActionListener", "onComplete--platform:" + platform.toString());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (sharingResultsListener != null){
                        sharingResultsListener.onComplete(platform, action, data);
                    }
                    if (shareBottomPopupDialog != null){
                        shareBottomPopupDialog.dismiss();
                    }
                }
            });


        }

        @Override
        public void onError(final Platform platform, final int action, final int errorCode, final Throwable error) {
            Log.d("mPlatActionListener", "onError--platform:" + platform.toString() + "--error:" + error.getMessage());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (sharingResultsListener != null){
                        sharingResultsListener.onError(platform, action, errorCode, error);
                    }
                    if (shareBottomPopupDialog != null){
                        shareBottomPopupDialog.dismiss();
                    }
                }
            });

        }

        @Override
        public void onCancel(final Platform platform, final int action) {
            Log.d("mPlatActionListener", "onCancel--platform:" + platform.toString());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (sharingResultsListener != null){
                        sharingResultsListener.onCancel(platform, action);
                    }
                    if (shareBottomPopupDialog != null){
                        shareBottomPopupDialog.dismiss();
                    }
                }
            });

        }
    };

}
