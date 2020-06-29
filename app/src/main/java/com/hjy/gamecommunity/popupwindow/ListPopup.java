package com.hjy.gamecommunity.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.hjy.gamecommunity.R;
import com.xuexiang.xui.widget.popupwindow.popup.XUIListPopup;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/29 16:52
 * 描述:
 */
public class ListPopup<T extends XUIListPopup> extends XUIListPopup {
    public ListPopup(Context context, int direction, BaseAdapter adapter) {
        super(context, direction, adapter);
    }

    public ListPopup(Context context, BaseAdapter adapter) {
        super(context, adapter);
    }

    @Override
    public void setContentView(View root) {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_popup, null, false);
        mArrowDown = (ImageView) layout.findViewById(R.id.arrow_down);
        mArrowUp = (ImageView) layout.findViewById(R.id.arrow_up);
        FrameLayout box = (FrameLayout) layout.findViewById(R.id.box);
        box.addView(root);

        if (root == null) {
            throw new IllegalStateException("setContentView was not called with a view to display.");
        }
        mRootViewWrapper = new RootView(mContext);
        mRootViewWrapper.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mRootView = layout;
        mRootViewWrapper.addView(layout);
        mPopupWindow.setContentView(mRootViewWrapper);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ListPopup.this.onDismiss();
                if (mDismissListener != null) {
                    mDismissListener.onDismiss();
                }
            }
        });
    }

    /**
     * 显示箭头（上/下）
     */
    public void showArrow() {
        setViewVisibility(mArrowDown, false);
        setViewVisibility(mArrowUp, false);
    }
}
