package com.hjy.baseui.ui.view.textview;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Author: zhangqingyou
 * Date: 2020/4/28 11:32
 * Des:
 */
public class SuperTextView extends AppCompatTextView {
    public SuperTextView(Context context) {
        super(context);
        init(context, null);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        StateListDrawable bg = new SuperTextViewDrawable().initStateListDrawable(context, attrs);
        setBackgroundDrawable(bg);
    }
}
