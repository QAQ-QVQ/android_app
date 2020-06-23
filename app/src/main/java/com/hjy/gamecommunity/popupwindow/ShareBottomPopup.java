package com.hjy.gamecommunity.popupwindow;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.baseui.ui.view.popup.BasePopup;
import com.hjy.gamecommunity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述:
 */
public class ShareBottomPopup extends BasePopup {


    private LinearLayout mLlLinearLayout;

    public ShareBottomPopup(Activity activity) {
        super(activity);

    }

    public ShareBottomPopup(Activity activity, int width, int height) {
        super(activity, width, height);
    }

    @Override
    public Object getLayout() {
        return R.layout.share_bottom_dialog;
    }

    private List<View> views;

    @Override
    public void initView(View rootView) {
        mLlLinearLayout = rootView.findViewById(R.id.ll_LinearLayout);

        View share_qq_btnViewById = rootView.findViewById(R.id.share_qq_btn);
        View share_to_qq_zone_btnViewById = rootView.findViewById(R.id.share_to_qq_zone_btn);
        View share_weixin_btnViewById = rootView.findViewById(R.id.share_weixin_btn);
        View share_wxcircle_btnViewById = rootView.findViewById(R.id.share_wxcircle_btn);
        View share_to_weiboViewById = rootView.findViewById(R.id.share_to_weibo);
        View share_pop_cancle_btnViewById = rootView.findViewById(R.id.share_pop_cancle_btn);

        views = new ArrayList<>();
        views.add(share_qq_btnViewById);
        views.add(share_to_qq_zone_btnViewById);
        views.add(share_weixin_btnViewById);
        views.add(share_wxcircle_btnViewById);
        views.add(share_to_weiboViewById);
        views.add(share_pop_cancle_btnViewById);
    }

    @Override
    public void initData() {
       // setAnimationStyle(android.R.style.Animation_Dialog);
        //sdk > 21 解决 标题栏没有办法遮罩的问题
        this.setClippingEnabled(false);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        setPopupWindowAlpha(90);


        mLlLinearLayout.setBackground(new SuperDrawable()
                .setClickAlpha(1.0f)
                .setRadiusTopLeft(10)
                .setRadiusTopRight(10)
                .setColorBg(Color.WHITE)
                .buid());
    }

    @Override
    public void listener() {
        getRootView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });
    }

    @Override
    public void show(View contentView) {
        showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public List<View> getViewsList() {
        return views;
    }
}
