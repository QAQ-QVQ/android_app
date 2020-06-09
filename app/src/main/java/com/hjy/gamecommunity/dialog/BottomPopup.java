package com.hjy.gamecommunity.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.hjy.baseui.ui.view.popup.BasePopup;
import com.hjy.gamecommunity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述:
 */
public class BottomPopup extends BasePopup {


    public BottomPopup(Activity activity) {
        super(activity);
    }

    public BottomPopup(Activity activity, int width, int height) {
        super(activity, width, height);
    }

    @Override
    public Object getLayout() {
        return R.layout.share_bottom_dialog;
    }

    private List<View> views = new ArrayList<>();

    @Override
    public void initView(View rootView) {
        View share_qq_btnViewById = rootView.findViewById(R.id.share_qq_btn);
        View share_to_qq_zone_btnViewById = rootView.findViewById(R.id.share_to_qq_zone_btn);
        View share_weixin_btnViewById = rootView.findViewById(R.id.share_weixin_btn);
        View share_wxcircle_btnViewById = rootView.findViewById(R.id.share_wxcircle_btn);
        View share_to_weiboViewById = rootView.findViewById(R.id.share_to_weibo);
        View share_pop_cancle_btnViewById = rootView.findViewById(R.id.share_pop_cancle_btn);

        views.clear();
        views.add(share_qq_btnViewById);
        views.add(share_to_qq_zone_btnViewById);
        views.add(share_weixin_btnViewById);
        views.add(share_wxcircle_btnViewById);
        views.add(share_to_weiboViewById);
        views.add(share_pop_cancle_btnViewById);
    }

    @Override
    public void initData() {
        setAnimationStyle(android.R.style.Animation_InputMethod);


    }

    @Override
    public void listener() {
        getRootView().setOnTouchListener(new OnTouchListener() {
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
