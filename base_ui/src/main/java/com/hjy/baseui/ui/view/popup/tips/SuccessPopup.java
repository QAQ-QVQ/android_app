package com.hjy.baseui.ui.view.popup.tips;

import android.app.Activity;
import android.view.View;

import com.hjy.baseui.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 14:26
 * 描述: 成功提示PopupWindow
 */
public class SuccessPopup extends BaseTipsPopup {

    public SuccessPopup(Activity activity) {
        super(activity);
    }

    public SuccessPopup(Activity activity, int width, int height) {
        super(activity, width, height);
    }

    @Override
    public Object getLayout() {
        return R.layout.bui_success_popup;
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void listener() {

    }

}
