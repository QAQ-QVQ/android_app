package com.hjy.gamecommunity.activity.mine;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.gamecommunity.App;
import com.hjy.gamecommunity.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的-》设置
 * Date: 2020/6/23 16:35
 * Des:
 *
 * @author dy
 */
public class ActivitySet extends BaseActivitySubordinate {
    /**
     * 标题
     */
//    private Toolbar toolbar;
    /**
     * 注销
     */
    private Button logout;
    /**
     * 选项
     */
    private RecyclerView setItem;
    /**
     * 设置适配器
     */
    private SetAdapter setAdapter;

    @Override
    public Object getLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
//        toolbar = findViewById(R.id.mine_set_toolbar);
//        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(),R.mipmap.bui_back_black));
        initToobar(this,"设置");
        logout = findViewById(R.id.mine_logout_button);
        logout.setBackground(setClickDrawable());
        setItem = findViewById(R.id.mine_set_item);
    }


    /**
     *
     */
    @Override
    public void initData() {
        setBean userMsg = new setBean(R.drawable.ic_launcher_background, "歪嘴猴", "", 0);
        setBean security = new setBean(0, "账号与安全", "", 0);
        setBean aboutUs = new setBean(0, "关于我们", "", 0);
        setBean checkUpdate = new setBean(0, "检查更新", "当前版本号V1.0.0", 0);
        setBean clearCache = new setBean(0, "清除缓存", "15.66MB", 0);
        ArrayList setList = new ArrayList();
        setList.add(userMsg);
        setList.add(security);
        setList.add(aboutUs);
        setList.add(checkUpdate);
        setList.add(clearCache);
        setAdapter = new SetAdapter(setList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        setItem.setLayoutManager(linearLayoutManager);
        setItem.setAdapter(setAdapter);
    }

    @Override
    public void listener() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/6/23 退出登录
            }
        });
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        setAdapter.setOnclickListener(new SetAdapter.OnclickListener() {
            /**
             * @param item 点击的内容
             */
            @Override
            public void OnClick(int item) {
                switch (item) {
                    //个人资料
                    case 0:
                        startActivity(new Intent(getContext(), ActivityUserMessage.class));
                        break;
                    //账号与安全
                    case 1:
                        // TODO: 2020/6/24 账号与安全
                        break;
                    //关于我们
                    case 2:
                        // TODO: 2020/6/24 关于我们
                        break;
                    //检查更新
                    case 3:
                        // TODO: 2020/6/24 检查更新
                        break;
                    //清除缓存
                    case 4:
                        // TODO: 2020/6/24 清除缓存
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void LongOnClick() {

            }
        });
    }

    /**
     * 单背景样式
     *
     * @return
     */
    private StateListDrawable setClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                .setColorBorder(ContextCompat.getColor(this, R.color.bui_red_light))
                .setBorderWidth(1)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_white))
                .buid();
        return stateListDrawable;
    }

    /**
     * 设置对象
     */
    class setBean {
        /**
         * 头像
         */
        private int icon;
        /**
         * 设置标题
         */
        private String title;
        /**
         * 设置信息
         */
        private String msg;
        /**
         * 下一个图标
         */
        private int next;

        public setBean(int icon, String title, String msg, int next) {
            this.icon = icon;
            this.title = title;
            this.msg = msg;
            this.next = next;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }
    }

    /**
     * 设置
     */
    static class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
        private List<setBean> setBeanList;
        private OnclickListener onclickListener;

        public SetAdapter(List<setBean> setBeanList) {
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

        interface OnclickListener {
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
}
