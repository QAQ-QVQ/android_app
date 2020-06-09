package com.hjy.baseui.ui.view.popup;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baseui.ui.SuperDrawable;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述: 基准PopupWindow
 */
public abstract class BasePopup extends PopupWindow {
    private String TAG = getClass().getSimpleName();
    private Activity activity;

    public BasePopup(Activity activity) {
        super(activity);
        this.activity = activity;
        init(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public BasePopup(Activity activity, int width, int height) {
        super(activity);
        this.activity = activity;
        init(width, height);
    }

    /**
     * @return
     */
    public abstract Object getLayout();

    /**
     * 初始化view
     */
    public abstract void initView(View rootView);

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * view监听写在这里面
     */
    public abstract void listener();


    private View mainRootView;

    private void init(int width, int height) {
        if (getLayout() instanceof Integer) {
            mainRootView = View.inflate(activity, (Integer) getLayout(), null);
        } else if (getLayout() instanceof View) {
            mainRootView = (View) getLayout();
        }

        setContentView(mainRootView);


        setWidth(ConvertUtils.dp2px(width));
        setHeight(ConvertUtils.dp2px(height));

        setOutsideTouchable(true);//外部可点击
        setAnimationStyle(android.R.style.Animation_Toast);//动画




        initView(mainRootView);
        initData();
        listener();

    }

    /**
     * 显示在屏幕中间
     *
     * @param contentView
     */
    public void show(View contentView) {
        showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }

    /**
     * @param contentView 显示在contentView中间
     */
    public void showViewCentre(View contentView) {

        // 获取屏幕的宽高
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕中心坐标 x轴
        int screenWidthCentreX = dm.widthPixels / 2;
        //屏幕中心坐标 y轴
        int screenHeightCentreY = dm.heightPixels / 2;


        int popupWindowdWidth = getWidth();
        int popupWindowdHeight = getHeight();
        int contentViewWidth = contentView.getWidth();
        int contentViewHeight = contentView.getHeight();
        int[] location = new int[2];
        //取在整个屏幕内的绝对坐标
        contentView.getLocationOnScreen(location);
        //view距离window 左边的距离（即x轴方向
        int contentViewX = location[0];
        // view距离window 顶边的距离（即y轴方向）
        int contentViewY = location[1];

        //view中心坐标 x轴
        int viewCentreX = contentViewX + contentViewWidth / 2;
        //view中心坐标 y轴
        int viewCentreY = contentViewY + contentViewHeight / 2;

        int offsetX = (viewCentreX - screenWidthCentreX);
        int offsetY = (viewCentreY - screenHeightCentreY);
        Log.d(TAG, "popupWindowdWidth:" + popupWindowdWidth);
        Log.d(TAG, "popupWindowdHeight:" + popupWindowdHeight);
        Log.d(TAG, "contentViewWidth:" + contentViewWidth);
        Log.d(TAG, "contentViewHeight:" + contentViewHeight);
        Log.d(TAG, "contentViewX:" + contentViewX);
        Log.d(TAG, "contentViewY:" + contentViewY);
        Log.d(TAG, "viewCentreX:" + viewCentreX);
        Log.d(TAG, "viewCentreY:" + viewCentreY);
        Log.d(TAG, "offsetX:" + offsetX);
        Log.d(TAG, "offsetY:" + offsetY);

        showAtLocation(contentView, Gravity.CENTER, offsetX, offsetY);
    }


    /**
     * 单背景样式
     *
     * @return
     */
    private StateListDrawable getStateListDrawable(int radius, int colorId) {
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(1f)//设置点击后透明度
                .setRadius(radius)//圆角
                .setColorBg(ContextCompat.getColor(activity, colorId))//背景颜色
                .buid();
        return stateListDrawable;
    }

    /**
     * 设置PopupWindow透明度
     */
    public void setPopupWindowAlpha(float alpha) {
        getBackground().setAlpha(0);
        mainRootView.setAlpha(alpha);
    }

    /**
     * 设置PopupWindow圆角
     */
    public void setPopupWindowRadiusColos(int radius, int colorId) {
        mainRootView.setBackground(getStateListDrawable(radius, colorId));
    }


    public View getRootView() {
        return mainRootView;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (!activity.isFinishing() && !isShowing()) {
            super.showAtLocation(parent, gravity, x, y);
        }

    }

    @Override
    public void dismiss() {
        if (!activity.isFinishing() && isShowing()) {
            super.dismiss();
        }

    }


}
