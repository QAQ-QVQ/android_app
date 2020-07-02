package com.hjy.gamecommunity.fragment.main;

import android.view.View;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.utils.GenerateTestUserSig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;

/**
 * 家族
 * Author: zhangqingyou
 * Date: 2020/4/8 15:45
 * Des:
 */
public class FragmentFamily extends BaseFragment {
    private ConversationLayout mConversationLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_family;
    }

    @Override
    public void initView(View mRootView) {
        mConversationLayout = findViewById(R.id.conversationLayout);

    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(true);
        }
    }

    @Override
    public void initData() {
        String genTestUserSig = GenerateTestUserSig.genTestUserSig("123456");
        TUIKit.login("123456", genTestUserSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
        // 初始化聊天面板
        mConversationLayout.initDefault();

        mConversationLayout.getTitleBar().setVisibility(View.GONE);

    }

    @Override
    public void listener() {

    }


}
