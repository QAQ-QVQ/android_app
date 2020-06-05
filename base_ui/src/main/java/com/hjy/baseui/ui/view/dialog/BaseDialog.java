package com.hjy.baseui.ui.view.dialog;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hjy.baseui.R;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseutil.ViewSeting;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 14:03
 * 描述: 基准Dialog
 */
public abstract class BaseDialog extends AppCompatDialog {
    private Activity activity;

    protected BaseDialog(Activity activity) {
        super(activity, R.style.transparentDialog);
        this.activity = activity;
        int screenWidth = (int) (ViewSeting.getScreenWidth() * 0.8);
        initView(getLayout(), screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    protected BaseDialog(Activity activity, int width, int height) {
        super(activity, R.style.transparentDialog);
        this.activity = activity;
        initView(getLayout(), width, height);
    }

    /**
     * @return
     */
    public abstract int getLayout();


    private LinearLayout rootView;
    private View mainView;

    private void initView(int layoutId, int width, int height) {
        rootView = new LinearLayout(getContext());
        rootView.setOrientation(android.widget.LinearLayout.VERTICAL);

        mainView = View.inflate(getContext(), layoutId, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        mainView.setLayoutParams(lp);

        rootView.addView(mainView);

        setContentView(rootView);


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
     * 设置Dialog圆角和背景颜色
     */
    public void setDialogRadiusColos(int radius, int colorId) {
        mainView.setBackground(getStateListDrawable(radius, colorId));
    }

    public View getRootView() {
        return mainView;
    }

    @Override
    public void show() {
        if (!activity.isFinishing() && !isShowing()) {
            super.show();
        }

    }

    @Override
    public void dismiss() {
        if (!activity.isFinishing() && isShowing()) {
            super.dismiss();
        }

    }


}
