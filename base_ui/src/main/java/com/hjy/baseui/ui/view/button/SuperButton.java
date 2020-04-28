package com.hjy.baseui.ui.view.button;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.hjy.baseui.ui.SuperDrawable;

/**
 * Author: zhangqingyou
 * Date: 2020/4/28 11:32
 * Des:
 */
public class SuperButton extends AppCompatButton {
    public SuperButton(Context context) {
        super(context);
        init(context, null);
    }

    public SuperButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        StateListDrawable bg = new SuperDrawable().initStateListDrawable(context, attrs);
        setBackgroundDrawable(bg);
    }
}
