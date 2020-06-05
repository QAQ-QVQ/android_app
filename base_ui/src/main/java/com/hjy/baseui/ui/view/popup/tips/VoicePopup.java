package com.hjy.baseui.ui.view.popup.tips;

import android.app.Activity;

import com.hjy.baseui.R;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/5 14:26
 * 描述: 语音提示PopupWindow
 */
public class VoicePopup extends BaseTipsPopup {

    public VoicePopup(Activity activity) {
        super(activity);
    }

    public VoicePopup(Activity activity, int width, int height) {
        super(activity, width, height);
    }

    @Override
    public int getLayout() {
        return R.layout.bui_voice_popup;
    }

}
