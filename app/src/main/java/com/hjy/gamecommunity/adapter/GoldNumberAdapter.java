package com.hjy.gamecommunity.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.gamecommunity.R;

import java.util.List;

import static com.xuexiang.xui.XUI.getContext;

/**
 * CREATED BY DY ON 2020/7/7.
 * TIME BY 11:06.
 *
 * @author DY
 **/
public class GoldNumberAdapter extends RecyclerView.Adapter<GoldNumberAdapter.ViewHolder> {
    private List<Integer> numberList;
    private int mPosion ;
    private OnSelectListener onSelectListener;

    public OnSelectListener getOnSelectListener() {
        return onSelectListener;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public GoldNumberAdapter(List<Integer> numberList) {
        this.numberList = numberList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gold_number, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosion = holder.getAdapterPosition();
                notifyDataSetChanged();
                if (onSelectListener != null) {
                    onSelectListener.OnSelect(mPosion);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i == mPosion) {
            viewHolder.constraintLayout.setBackground(setSelectedDrawable());
            viewHolder.textView.setTextColor(ContextCompat.getColor(getContext(),R.color.bui_red_light));
        } else {
            viewHolder.constraintLayout.setBackground(setSelectDrawable());
            viewHolder.textView.setTextColor(ContextCompat.getColor(getContext(),R.color.bui_black_light1));
        }
        SpannableString textGift = new SpannableString(numberList.get(i).toString()+ " 金豆");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.6f);
        textGift.setSpan(sizeSpan,textGift.length()-2,textGift.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        viewHolder.textView.setText(textGift);
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.gold_number_tv);
            constraintLayout = view.findViewById(R.id.gold_bg_layout);
        }
    }

    private StateListDrawable setSelectedDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(4)
                .setClickColorBg(0)
                //描边
                .setColorBorder(ContextCompat.getColor(getContext(), R.color.bui_red_light))
                .setBorderWidth(1)
                //背景颜色
                .setColorBg(ContextCompat.getColor(getContext(), R.color.bui_white))
                .buid();
        return stateListDrawable;
    }

    private StateListDrawable setSelectDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(4)
                .setClickColorBg(0)
                //背景颜色
                .setColorBg(ContextCompat.getColor(getContext(), R.color.bui_white))
                .buid();
        return stateListDrawable;
    }

    /**
     * 获取选中索引
     * @return
     */
    public int getmPosion(){
        return mPosion;
    }

    public interface OnSelectListener {
        /**
         * 选择
         *
         * @param posion
         */
        void OnSelect(int posion);
        void LongClick(int posion);
    }
}
