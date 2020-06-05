package com.hjy.baseui.ui.view.popup.tips;

import android.app.Activity;

import com.hjy.baseui.R;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述: 加载中PopupWindow
 */
public class LoadPopup extends BaseTipsPopup {

    public LoadPopup(Activity activity) {
        super(activity);
    }

    public LoadPopup(Activity activity, int width, int height) {
        super(activity, width, height);
    }

    @Override
    public int getLayout() {
        return R.layout.bui_load_popup;
    }


}
