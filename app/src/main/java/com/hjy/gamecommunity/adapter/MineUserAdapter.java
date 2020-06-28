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

import com.hjy.baserequest.bean.MineUserSetBean;
import com.hjy.gamecommunity.App;
import com.hjy.gamecommunity.R;

import java.util.List;

/**
 * 个人资料
 * CREATED BY DY ON 2020/6/28.
 * TIME BY 17:44.
 *
 * @author DY
 **/
public class MineUserAdapter extends RecyclerView.Adapter<MineUserAdapter.ViewHolder> {
    private List<MineUserSetBean> userSetBeanList;
    private OnclickListener onclickListener;

    public MineUserAdapter(List<MineUserSetBean> userSetBeanList) {
        this.userSetBeanList = userSetBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_user_item, null, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Title.setText(userSetBeanList.get(i).getTitle());
        viewHolder.msg.setText(userSetBeanList.get(i).getMsg());
        if (userSetBeanList.get(i).getNext() != 0) {
            viewHolder.Next.setImageDrawable(ContextCompat.getDrawable(App.getApplication(), userSetBeanList.get(i).getNext()));
        }
        viewHolder.userItem.setOnClickListener(new View.OnClickListener() {
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
        return userSetBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        TextView msg;
        ImageView Next;
        RelativeLayout userItem;

        public ViewHolder(View view) {
            super(view);
            Title = view.findViewById(R.id.mine_user_title);
            msg = view.findViewById(R.id.mine_user_msg);
            Next = view.findViewById(R.id.mine_user_next);
            userItem = view.findViewById(R.id.user_item);
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
