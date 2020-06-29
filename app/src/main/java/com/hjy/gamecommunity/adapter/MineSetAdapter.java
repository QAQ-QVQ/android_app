package com.hjy.gamecommunity.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baserequest.bean.MineSetBean;
import com.hjy.gamecommunity.App;
import com.hjy.gamecommunity.R;

import java.util.List;

/**
 * 设置构造
 * CREATED BY DY ON 2020/6/29.
 * TIME BY 9:09.
 *
 * @author DY
 **/
public class MineSetAdapter extends RecyclerView.Adapter<MineSetAdapter.ViewHolder> {
    private List<MineSetBean> setBeanList;
    private OnclickListener onclickListener;

    public MineSetAdapter(List<MineSetBean> setBeanList) {
        this.setBeanList = setBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_set_item, null, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (setBeanList.get(i).getIcon() != 0) {
            viewHolder.setIcon.setVisibility(View.VISIBLE);
            viewHolder.setIcon.setImageDrawable(ContextCompat.getDrawable(App.getApplication(),setBeanList.get(i).getIcon()));
        } else {
            viewHolder.setIcon.setVisibility(View.GONE);
        }
        viewHolder.setTitle.setText(setBeanList.get(i).getTitle());
        viewHolder.setTv.setText(setBeanList.get(i).getMsg());
        if (setBeanList.get(i).getNext() != 0) {
            viewHolder.setNext.setImageDrawable(ContextCompat.getDrawable(App.getApplication(),setBeanList.get(i).getNext()));
        }
        viewHolder.setItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickListener != null) {
                    onclickListener.OnClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return setBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView setIcon;
        TextView setTitle;
        TextView setTv;
        ImageView setNext;
        RelativeLayout setItem;

        public ViewHolder(View view) {
            super(view);
            setIcon = view.findViewById(R.id.mine_set_icon);
            setTitle = view.findViewById(R.id.mine_set_title);
            setTv = view.findViewById(R.id.mine_set_tv);
            setNext = view.findViewById(R.id.mine_set_next);
            setItem = view.findViewById(R.id.set_item);
        }
    }

    public interface OnclickListener {
        /**
         * @param item 点击的内容
         */
        void OnClick(int item);

        void LongOnClick();
    }

    public OnclickListener getOnclickListener() {
        return onclickListener;
    }

    public void setOnclickListener(OnclickListener onclickListener) {
        this.onclickListener = onclickListener;
    }
}
