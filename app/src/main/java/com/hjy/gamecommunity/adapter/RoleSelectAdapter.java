package com.hjy.gamecommunity.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baserequest.bean.RoleListBean;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.gamecommunity.R;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY DY ON 2020/7/3.
 * TIME BY 9:18.
 *
 * @author DY
 **/
public class RoleSelectAdapter extends RecyclerView.Adapter<RoleSelectAdapter.ViewHolder> {

    private int mPosion = -1;
    private List<RoleListBean.DataBean> roleList;
    private OnSelectListener onSelectListener;

    public RoleSelectAdapter(List<RoleListBean.DataBean> roleList) {
        this.roleList = roleList;
    }

    public OnSelectListener getOnSelectListener() {
        return onSelectListener;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.role_select_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.roleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosion = holder.getAdapterPosition();
                notifyDataSetChanged();
                if (onSelectListener != null) {
                    onSelectListener.OnSelect(mPosion);
                }
            }
        });
        holder.roleSelect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onSelectListener != null) {
                    onSelectListener.LongClick(mPosion);
                }
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.roleName.setText(roleList.get(i).getCp_role_name());
        viewHolder.roleService.setText(roleList.get(i).getCp_server_name());
        if (i == mPosion) {
            viewHolder.choose.setImageResource(R.drawable.qy_chooseed);
        } else {
            viewHolder.choose.setImageResource(R.drawable.qy_choose);
        }

//        if (roleList.get(i).getIs_default() == 2){
//            viewHolder.choose.setImageResource(R.drawable.qy_chooseed);
//        }else if (roleList.get(i).getIs_default() == 1){
//            viewHolder.choose.setImageResource(R.drawable.qy_choose);
//        }
    }

    @Override
    public int getItemCount() {
        return roleList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView roleName;
        TextView roleService;
        ConstraintLayout roleSelect;
        ImageView choose;

        public ViewHolder(View view) {
            super(view);
            roleName = view.findViewById(R.id.role_name);
            roleService = view.findViewById(R.id.role_service);
            roleSelect = view.findViewById(R.id.role_select);
            choose = view.findViewById(R.id.role_choose);
        }
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
