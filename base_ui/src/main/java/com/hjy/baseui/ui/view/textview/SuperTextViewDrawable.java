package com.hjy.baseui.ui.view.textview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baseui.R;
import com.hjy.baseui.ui.SuperDrawable;


/**
 * 万能样式 SuperDrawable
 * <p>
 * 1.支持圆角 四个角可以单独设置
 * 2.支持描边大小、描边颜色
 * 3.支持点击颜色变化设置
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/28 9:21
 * Des:
 */
public class SuperTextViewDrawable extends GradientDrawable {


    public StateListDrawable initStateListDrawable(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperTextView);
        ColorStateList colorBg = typedArray.getColorStateList(R.styleable.SuperTextView_zqy_stv_backgroundColor);
        ColorStateList colorBorder = typedArray.getColorStateList(R.styleable.SuperTextView_zqy_stv_borderColor);
        ColorStateList startColor = typedArray.getColorStateList(R.styleable.SuperTextView_zqy_stv_startColor);
        ColorStateList endColor = typedArray.getColorStateList(R.styleable.SuperTextView_zqy_stv_endColor);
        int gradient = typedArray.getInt(R.styleable.SuperTextView_zqy_stv_gradient, -1);
        int orientation = typedArray.getInt(R.styleable.SuperTextView_zqy_stv_orientation, -1);
        int borderWidth = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stv_borderWidth, 0);
        boolean isRadiusAdjustBounds = typedArray.getBoolean(R.styleable.SuperTextView_zqy_stv_isRadiusAdjustBounds, false);
        int mRadius = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stv_radius, ConvertUtils.dp2px(5));
        int mRadiusTopLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stv_radiusTopLeft, 0);
        int mRadiusTopRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stv_radiusTopRight, 0);
        int mRadiusBottomLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stv_radiusBottomLeft, 0);
        int mRadiusBottomRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_zqy_stv_radiusBottomRight, 0);
        typedArray.recycle();

        Orientation orientation1 = Orientation.TOP_BOTTOM;
        Orientation[] values = Orientation.values();
        for (Orientation value : values) {
            if (value.ordinal() == orientation) {
                orientation1 = value;
            }
        }


        /**
         * @param colorBg              未点击状态背景色
         * @param colorBorder          未点击状态的描边色
         * @param clickColorBg         点击状态背景色
         * @param clickColorBorder     点击状态的边框色
         * @param gradient             设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）        
         * @param colors               设置渐变颜色
         * @param orientation          设置渐变方向                       
         * @param borderWidth          边框宽度
         * @param isRadiusAdjustBounds 设置圆角大小是否自动适应为 View 的高度的一半
         * @param radius               四角圆形度数
         * @param radiusTopLeft        左上圆形度数
         * @param radiusTopRight       右上圆形度数
         * @param radiusBottomLeft     左下圆形度数
         * @param radiusBottomRight    右下圆形度数
         * @return
         */
        StateListDrawable buid = new SuperDrawable().setClickAlpha(0.7f)
                .setRadius(mRadius)
                .setBorderWidth(borderWidth)
                .setRadiusAdjustBounds(isRadiusAdjustBounds)
                .setRadiusBottomLeft(mRadiusBottomLeft)
                .setRadiusBottomRight(mRadiusBottomRight)
                .setRadiusTopLeft(mRadiusTopLeft)
                .setRadiusTopRight(mRadiusTopRight)
                .setColorBg(colorBg != null ? colorBg.getDefaultColor() : 0)
                .setColorBorder(colorBorder != null ? colorBorder.getDefaultColor() : 0)
                .setClickColorBg(0)
                .setClickColorBorder(0)
                .setSGradientType(gradient)
                .setSColors(new int[]{startColor != null ? startColor.getDefaultColor() : 0, endColor != null ? endColor.getDefaultColor() : 0})
                .setSOrientation(orientation1)
                .buid();
        return buid;
    }


}
