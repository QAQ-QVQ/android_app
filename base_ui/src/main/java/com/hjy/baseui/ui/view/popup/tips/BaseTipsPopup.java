package com.hjy.baseui.ui.view.popup.tips;

import android.app.Activity;

import com.hjy.baseui.R;
import com.hjy.baseui.ui.view.popup.BasePopup;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 9:03
 * 描述: 提示基准PopupWindow
 */
public abstract class BaseTipsPopup extends BasePopup {

    public BaseTipsPopup(Activity activity) {
        super(activity, 216, 216);
        init();
    }

    public BaseTipsPopup(Activity activity, int width, int height) {
        super(activity, width, height);
        init();
    }

    private void init() {
        setPopupWindowAlpha(0.9f);//设置PopupWindow透明度
        setPopupWindowRadiusColos(5, R.color.bui_black_light);//设置PopupWindow圆角和背景色
    }

}
