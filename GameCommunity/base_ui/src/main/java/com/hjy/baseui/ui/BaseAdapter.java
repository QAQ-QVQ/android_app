package com.hjy.baseui.ui;

import android.content.Context;
import android.view.View;

import com.zhouyou.recyclerview.adapter.BH;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewHolder;
import com.zhouyou.recyclerview.adapter.DataHelper;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 万能Adapter
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 *
 * @param <T> 数据源对象
 */
public abstract class BaseAdapter<T> extends BaseRecyclerViewAdapter<T> implements DataHelper<T> {
    private OnItemButtonClickListener onItemButtonClickListener;

    private List<Integer> layoutIdsList = new ArrayList<>();

    public List<Integer> getLayoutIdsList() {
        return layoutIdsList;
    }

    /**
     * @param data     数据源
     * @param context  上下文
     * @param layoutId 布局Id
     */
    public BaseAdapter(List<T> data, Context context, int... layoutId) {
        super(data, context, layoutId);
        layoutIdsList.clear();
        for (int i = 0; i < layoutId.length; i++) {
            layoutIdsList.add(layoutId[i]);
        }

    }

    public BaseAdapter(Context context, int... layoutIds) {
        super(context, layoutIds);
        layoutIdsList.clear();
        for (int i = 0; i < layoutIds.length; i++) {
            layoutIdsList.add(layoutIds[i]);
        }
    }


    @Override
    public BaseRecyclerViewHolder createViewHolder(View view, int layoutId) {
        return new HelperRecyclerViewHolder(view, layoutId);
    }

    @Override
    public void onBindData(BH viewHolder, int position, T item) {
        HelperRecyclerViewHolder helperViewHolder = (HelperRecyclerViewHolder) viewHolder;

        HelperBindData(helperViewHolder, position, item);

        //1.赋值相关事件,例如点击长按等
        //2.也可用低二种方式，用BaseRecyclerViewAdapter类中的 adapter.setOnItemClickListener()
        setListener(helperViewHolder, position, item);
    }

    public abstract void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, T item);
//    @Override
//    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, MultipleItemBean item) {
//        //方式一，对应checkLayout中的方式一
//        if(item.getItemType() == 0){//adapter_multi_item1_layout布局对应的操作
//            viewHolder.setText(R.id.name_tv,item.getName());
//        } else if(item.getItemType() == 1){//adapter_multi_item2_layout布局对应的操作
//            viewHolder.setText(R.id.name_tv,item.getName())
//                    .setText(R.id.info_tv,item.getAge());
//        }
//
//        /*//方式二，对应checkLayout中的方式二
//        int layoutType = getItemViewType(position);
//        if(layoutType==R.layout.adapter_multi_item1_layout){
//            viewHolder.setText(R.id.name_tv,item.getName());
//        }else if(layoutType==R.layout.adapter_multi_item2_layout){
//            viewHolder.setText(R.id.name_tv,item.getName())
//                    .setText(R.id.info_tv,item.getAge());
//        }*/


    /****1.数据获取方式*****/
    //旧：传统的写法是从集合中获取再强转,如下：
    //TestBean testBean =(TestBean)datas.get(position);
    //新：baseadapter中提供的有获取当前postion位置对应的数据，直接调用就行了，也不用强转
    // final TestBean testBean = getData(position);

    /****2.view赋值*****/
    //方式一：采用链式的设计的书写方式，一点到尾。（方式一）
//        viewHolder.setText(R.id.text,testBean.getName())
//            .setImageResource(R.id.image, MakePicUtil.makePic(position))
//            /* .setVisible(R.id.text,true);//设置某个view是否可见*/
//            .setOnClickListener(R.id.image, new View.OnClickListener() {//点击事件
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(mContext, "我是子控件" + testBean.getName() + "请看我如何处理View点击事件的", Toast.LENGTH_LONG).show();
//        }
//    });
    //其它更多连写功能请查看viewHolder类中代码

    //方式二：不采用链式的方式，通过getView直接获取控件对象，不需要强转了，采用的是泛型
//    TextView textView =viewHolder.getView(R.id.text2);
//        textView.setText(testBean.getAge());

    /****3.其它更多使用方式，请自己探索*****/
    //举例如果想知道适配器中数据是否为空用isEmpty()就可以了，无需list.size()==0  list.isEmpty()等其它方式
//        if(isEmpty()){
//
//    }
//    }
//
    /*********多item布局使用方式***********/
//    //如果要用多item布局，必须重写checkLayout()方法，来指定哪一条数据对应哪个item布局文件
//    //不重写的时候返回默认是0，也就是只会加载第一个布局
//    @Override
//    public int checkLayout(MultipleItemBean item, int position) {
//        //方式一：判断的类型直接写在model中
//        return item.getItemType();
//        //方式二：根据类型判断
//        /*if(item instanceof A){
//            return R.layout.adapter_multi_item1_layout;
//        }else if(item instanceof B){
//            return R.layout.adapter_multi_item2_layout;
//        }else {
//            return R.layout.adapter_multi_item3_layout;
//        }*/
//    }

    /**
     * 绑定相关事件,例如点击长按等,默认空实现
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    public abstract void setListener(HelperRecyclerViewHolder viewHolder, int position, T item);

    /*******************以下两种item点击事件都可以，自己选择合适的方式**********************************/
    //方式一：此方式是另一种处理：绑定相关事件,例如点击长按等,默认空实现，如果你要使用需要覆写setListener()方法
    //方式二：绑定相关事件,例如点击长按等,默认空实现等我们一般会在适配器外部使用，
    // 例如： mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<TestBean>(){});

   /* @Override
    protected void setListener(HelperRecyclerViewHolder viewHolder, final int position, TestBean item) {
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"我是Item："+position,Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    @Override
    public boolean isEnabled(int position) {
        return position >= 0 && position < mList.size();
    }

    /**
     * 添加单个数据到列表头部
     *
     * @param data
     */
    @Override
    public void addItemToHead(T data) {
        add(0, data);
    }

    /**
     * 添加单个数据到列表尾部
     *
     * @param data 数据
     */
    @Override
    public boolean addItemToLast(T data) {
        boolean result;
        if (data != null) {
            if (mList == null) mList = new ArrayList<>();
            result = mList.add(data);
            notifyDataSetChanged();
        } else
            result = false;

        return result;
    }

    /**
     * 添加数据集到列表头部
     * 注：
     * 1.此方法 Adapter只会刷新添加的数据（效率优化，已添加数据没必要刷新）
     * @param datas
     */
    @Override
    public boolean addItemsToHead(List<T> datas) {
        boolean b;
        if (datas != null && datas.size() > 0) {
            if (mList == null) mList = new ArrayList<>();

            b = mList.addAll(0, datas);

            notifyItemRangeChanged(0, datas.size());//只刷新添加的数据

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

    @Override
    public boolean addItemsToLast(List<T> datas) {
        boolean b;
        if (datas != null && datas.size() > 0) {
            if (mList == null) mList = new ArrayList<>();

            b = mList.addAll(datas);

            int sizes = mList.size();
            int size = datas.size();
            if (sizes > size)
                notifyItemRangeChanged(sizes - sizes, sizes);//只刷新添加的数据
            else
                notifyDataSetChanged();
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
    @Override
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

    @Override
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
    @Override
    public T getData(int index) {
        return getItemCount() == 0 ? null : mList.get(index);
    }

    /**
     * 将某一个数据修改
     *
     * @param oldData 旧的数据
     * @param newData 新的数据
     */
    @Override
    public void alterObj(T oldData, T newData) {
        alterObj(mList.indexOf(oldData), newData);
    }

    /**
     * 修改对应的位置的数据
     *
     * @param index 修改的位置
     * @param data  要代替的的数据
     */
    @Override
    public void alterObj(int index, T data) {
        if (mList == null || data == null) return;
        mList.set(index, data);
        notifyDataSetChanged();
    }

    /**
     * 替换所有数据
     *
     * @param data
     */
    @Override
    public void replaceAll(List<T> data) {
        if (mList == null) mList = new ArrayList<>();
        mList.clear();
        addAll(0, data);
    }

    /**
     * 删除对应的数据
     *
     * @param data
     */
    @Override
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
    @Override
    public void removeToIndex(int index) {
        if (mList == null) return;
        mList.remove(index);
        notifyDataSetChanged();
    }


    /**
     * 清除所有
     */
    @Override
    public void clear() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }


    @Override
    public boolean contains(T data) {
        if (mList == null || mList.isEmpty()) return false;
        return mList.contains(data);
    }

    public List<T> getList() {
        return mList;
    }

    public interface OnItemButtonClickListener<T> {
        void onItemButton(View view, T item, int position);
    }

    public OnItemButtonClickListener getOnItemButtonClickListener() {
        return onItemButtonClickListener;
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener) {
        this.onItemButtonClickListener = onItemButtonClickListener;
    }

}
