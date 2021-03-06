package com.hjy.baseui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义基准BaseAdapter
 * <p>
 * Author: zhangqingyou
 * Date: 2020/5/14 13:07
 * Des:
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private List<T> mList;
    private List<Integer> layoutIdsList;
    private Context context;

    public BaseAdapter() {
        mList = new ArrayList<>();
        layoutIdsList = new ArrayList<>();
    }


    public BaseAdapter(List<T> beanList) {
        this.mList = beanList;
        layoutIdsList = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        BaseViewHolder baseViewHolder = null;
        if (mList != null) {
            int checkLayout = getLayout(mList.get(viewType), viewType);
            if (checkLayout != 0) {
                if (!layoutIdsList.contains(checkLayout)) {
                    layoutIdsList.add(checkLayout);
                }
                View inflate = LayoutInflater.from(context).inflate(checkLayout, viewGroup, false);
                baseViewHolder = new BaseViewHolder(inflate, checkLayout);
            }
        }
        return baseViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.BaseViewHolder viewHolder, int position) {
        if (mList != null) {
            onBindViewHolder(viewHolder, mList.get(position), position);
            //绑定监听事件
            onBindItemClickListener(viewHolder, position);
            //自定义监听事件
            listener(viewHolder, mList.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    /**
     * 获取当前加载的那个布局
     *
     * @param
     * @return
     */
    public int getLayoutId(BaseAdapter.BaseViewHolder viewHolder) {
        return viewHolder.getLayoutId();
    }

    public Context getContext() {
        return context;
    }

    public List<T> getDataList() {
        return mList;
    }

    public List<Integer> getLayoutIdsList() {
        return layoutIdsList;
    }

    /**
     * 加载哪个布局id
     *
     * @return
     */
    public abstract @LayoutRes
    int getLayout(T item, int position);

    public abstract void onBindViewHolder(BaseViewHolder viewHolder, T item, int position);

    /**
     * view监听写在这里面
     */
    public abstract void listener(BaseViewHolder viewHolder, T item, int position);

    /**
     * 添加数据集到列表头部
     * 注：
     *
     * @param datas
     */
    public boolean addItemsToHead(List<T> datas) {
        boolean b;
        if (datas != null && datas.size() > 0) {
            if (mList == null) mList = new ArrayList<>();

            b = mList.addAll(0, datas);

            notifyDataSetChanged();

        } else
            b = false;

        return b;
    }

    /**
     * 添加数据集合到最后位置
     * 注：
     * 1.此方法适用于  上拉加载更多
     * 2.此方法 Adapter只会刷新添加的数据（效率优化，前面的数据没必要刷新）
     *
     * @param datas
     * @return 是否添加成功
     */

    public boolean addItemsToLast(List<T> datas) {
        boolean b;
        if (datas != null && datas.size() > 0) {
            if (mList == null) mList = new ArrayList<>();

            b = mList.addAll(datas);

            int sizes = mList.size();
            int size = datas.size();
            if (sizes > size)
                notifyItemRangeChanged(sizes - size, sizes);//只刷新添加的数据
            else
                notifyDataSetChanged();
            b = true;
        } else
            b = false;
        /**
         * ·notifyItemInserted(int position): 列表position位置添加一条数据时可以调用，伴有动画效果
         *
         * ·notifyItemRemoved(int position) :列表position位置移除一条数据时调用，伴有动画效果
         *
         * ·notifyItemMoved(int fromPosition, int toPosition) 列表fromPosition位置的数据移到toPosition位置时调用，伴有动画效果
         *
         * ·notifyItemRangeChanged(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项进行数据刷新
         *
         * ·notifyItemRangeInserted(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项批量添加数据时调用，伴有动画效果
         *
         * ·notifyItemRangeRemoved(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项批量删除数据时调用，伴有动画效果
         *
         * 需要注意的地方，在使用这些方法的时候，google官方文档中提到他们的position会自动增加，但是在实际操作过程中，并没有增加，导致数据错位的问题，所以，当我们需要使用这些特效方法的时候，必须要重新刷新一遍数据，纠正position。

         */
        return b;
    }

    /**
     * 替换所有数据
     *
     * @param data
     */
    public void replaceAll(List<T> data) {
        if (data != null) {
            if (mList == null) mList = new ArrayList<>();
            mList.clear();
            mList.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 设置了item动画必须用notifyItemRangeInserted刷新适配器，否则没有动画效果
     *
     * @param datas
     * @return
     */
    public boolean addAndNotifyItems(List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            return false;
        }
        int pos = mList.size();
        boolean b = mList.addAll(datas);
        notifyItemRangeInserted(pos, datas.size());
        return b;
    }

    /**
     * 添加数据集合到指定位置
     *
     * @param startPosition 数据添加的位置
     * @param datas         数据集合
     */
    public boolean addAll(int startPosition, List<T> datas) {
        if (mList == null || datas == null)
            return false;
        boolean result = mList.addAll(startPosition, datas);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 添加单个数据到指定位置
     *
     * @param startPosition 数据添加的位置
     * @param data          数据
     */

    public void add(int startPosition, T data) {
        if (mList == null || data == null) return;
        mList.add(startPosition, data);
        notifyDataSetChanged();
    }

    /**
     * 获取index对于的数据
     *
     * @param index 数据座标
     * @return 数据对象
     */
    public T getData(int index) {
        return getItemCount() == 0 ? null : mList.get(index);
    }

    /**
     * 将某一个数据修改
     *
     * @param oldData 旧的数据
     * @param newData 新的数据
     */
    public void alterObj(T oldData, T newData) {
        alterObj(mList.indexOf(oldData), newData);
    }

    /**
     * 修改对应的位置的数据
     *
     * @param index 修改的位置
     * @param data  要代替的的数据
     */
    public void alterObj(int index, T data) {
        if (mList == null || data == null) return;
        mList.set(index, data);
        notifyDataSetChanged();
    }

    /**
     * 是否包含某个数据
     *
     * @param data
     * @return
     */
    public boolean contains(T data) {
        if (mList == null || mList.isEmpty()) return false;
        return mList.contains(data);
    }

    /**
     * 删除对应的数据
     *
     * @param data
     */
    public boolean remove(T data) {
        boolean result = false;
        if (data == null) return result;
        result = mList.remove(data);
        notifyDataSetChanged();
        return result;
    }

    /**
     * 删除对应位置的数据
     *
     * @param index
     */
    public void removeToIndex(int index) {
        if (mList == null) return;
        mList.remove(index);
        notifyDataSetChanged();
    }


    /**
     * 清除所有
     */
    public void clear() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }


    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 注册item点击、长按事件
     *
     * @param holder
     * @param position
     */
    protected final void onBindItemClickListener(final BaseViewHolder holder, final int position) {
        if (null != mOnItemClickListener)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, mList.get(position), position);
                }
            });

        if (null != mOnItemLongClickListener)
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v, mList.get(position), position);
                    return true;
                }
            });
    }

//    /**
//     * 是否点击有水波纹效果
//     *
//     * @param isWaterRipple 是否点击有水波纹效果
//     */
//    public void setWaterRipple(boolean isWaterRipple) {
//        this.isWaterRipple = isWaterRipple;
//    }

//    /**
//     * 设置水波纹View宽高 （水波纹View为itemView的父布局）
//     * 默认宽度为  MATCH_PARENT
//     * 默认高度为  WRAP_CONTENT
//     *
//     * @param viewW
//     * @param viewH
//     */
//    int viewW = ViewGroup.LayoutParams.MATCH_PARENT, viewH = ViewGroup.LayoutParams.WRAP_CONTENT;
//
//    public void setWaterRippleViewHW(int viewW, int viewH) {
//        this.viewW = viewW;
//        this.viewH = viewH;
//    }
//
//    /**
//     * 获取水波纹view （把itemView添加到水波纹view中）
//     */
//    private View getWaterRippleView(View itemView) {
//        LinearLayout linearLayout = new LinearLayout(getContext());
//        linearLayout.setGravity(Gravity.CENTER);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(viewW, viewH));
//        linearLayout.addView(itemView);
//
//        TypedValue typedValue = new TypedValue();
//        Resources.Theme theme = itemView.getContext().getTheme();
//        int top = itemView.getPaddingTop();
//        int bottom = itemView.getPaddingBottom();
//        int left = itemView.getPaddingLeft();
//        int right = itemView.getPaddingRight();
//        if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
//            linearLayout.setBackgroundResource(typedValue.resourceId);
//        }
//       // linearLayout.setPadding(left, top, right, bottom);
//
//        return linearLayout;
//    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private int layoutId;

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public BaseViewHolder(View itemView, @LayoutRes int layoutId) {
            this(itemView);
            this.layoutId = layoutId;
            setWaterRipple();
        }


        /**
         * 设置水波纹背景 (itemView未设置Background才有效)
         */
        public void setWaterRipple() {
            if (itemView.getBackground() == null) {
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = itemView.getContext().getTheme();
                int top = itemView.getPaddingTop();
                int bottom = itemView.getPaddingBottom();
                int left = itemView.getPaddingLeft();
                int right = itemView.getPaddingRight();
                if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                    itemView.setBackgroundResource(typedValue.resourceId);
                }
                itemView.setPadding(left, top, right, bottom);
            }
        }


        public <T extends View> T findViewById(int id) {
            return itemView.findViewById(id);
        }

        public int getLayoutId() {
            return layoutId;
        }
    }
}
