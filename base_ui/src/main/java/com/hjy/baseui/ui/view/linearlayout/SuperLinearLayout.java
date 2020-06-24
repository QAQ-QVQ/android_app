package com.hjy.baseui.ui.view.linearlayout;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 9:09
 * 描述:
 */
public class SuperLinearLayout extends LinearLayout {
    public SuperLinearLayout(Context context) {
        this(context, null, 0);
    }

    public SuperLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        SuperLinearLayoutDrawable superLinearLayoutDrawable = new SuperLinearLayoutDrawable();
        StateListDrawable bg = superLinearLayoutDrawable.initStateListDrawable(context, attrs);
        setBackground(bg);

    }
}
