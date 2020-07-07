package com.hjy.gamecommunity.activity.mine;

import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseui.ui.SuperDrawable;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.GoldNumberAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-》我的金豆
 * Date: 2020/07/07 09:29
 * Des:
 *
 * @author dy
 */
public class ActivityMyGold extends BaseActivitySubordinate {
    private static final String TAG = "ActivityMyGold";
    /**
     * 账单
     */
    private TextView bill;
    /**
     * 金豆数量
     */
    private TextView goldNumber;
    /**
     * 充值列表
     */
    private RecyclerView goldRecyclerView;
    /**
     * 充值金额
     */
    private TextView rechargeNumber;
    /**
     * 支付宝
     */
    private RelativeLayout alipay;
    /**
     * 微信
     */
    private RelativeLayout wechat;
    /**
     * 支付宝选择
     */
    private ImageView alipaySelect;
    /**
     * 微信选择
     */
    private ImageView wechatSelect;
    /**
     * 充值
     */
    private Button Recharge;
    /**
     * 充值金额适配器
     */
    private GoldNumberAdapter goldNumberAdapter;
    /**
     * 索引
     */
    private int mPosion;
    /**
     * 数目
     */
    private List numberList;
    private boolean flag = true;
    @Override
    public Object getLayout() {
        return R.layout.activity_my_gold;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "我的金豆");
        bill = findViewById(R.id.tv_text_submit_bar);
        goldNumber = findViewById(R.id.gold_number);
        goldRecyclerView = findViewById(R.id.gold_recyclerView);
        rechargeNumber = findViewById(R.id.gold_choose_number);
        alipay = findViewById(R.id.alipay);
        wechat = findViewById(R.id.wechat);
        alipaySelect = findViewById(R.id.alipay_select);
        wechatSelect = findViewById(R.id.wechat_select);
        Recharge = findViewById(R.id.recharge_torecharge);
        Recharge.setBackground(setCanClickDrawable());
    }

    @Override
    public void initData() {
        goldNumber.setText(getIntent().getStringExtra("gold"));
        numberList = new ArrayList();
        numberList.add(6);
        numberList.add(68);
        numberList.add(88);
        numberList.add(208);
        numberList.add(388);
        numberList.add(988);
        goldNumberAdapter = new GoldNumberAdapter(numberList);
        goldRecyclerView.setAdapter(goldNumberAdapter);
        goldRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rechargeNumber.setText("金额：￥"+numberList.get(mPosion));
    }



    @Override
    public void listener() {
        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                alipaySelect.setImageResource(R.drawable.qy_chooseed);
                wechatSelect.setImageResource(R.drawable.qy_choose);
            }
        });
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                alipaySelect.setImageResource(R.drawable.qy_choose);
                wechatSelect.setImageResource(R.drawable.qy_chooseed);
            }
        });
        //账单
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/7/7 账单

            }
        });
        Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/7/7 支付
                Log.i(TAG, "onClick: " + numberList.get(mPosion));
                //支付宝
                if (flag){

                //微信
                }else {

                }
            }
        });
        goldNumberAdapter.setOnSelectListener(new GoldNumberAdapter.OnSelectListener() {
            @Override
            public void OnSelect(int posion) {
                mPosion = posion;
                rechargeNumber.setText("金额：￥" + numberList.get(posion));
            }

            @Override
            public void LongClick(int posion) {

            }
        });

    }

    private StateListDrawable setCanClickDrawable() {
        //设置点击后透明度
        StateListDrawable stateListDrawable = new SuperDrawable().setClickAlpha(0.7f)
                //圆角
                .setRadius(50)
                .setClickColorBg(0)
                //背景颜色
                .setColorBg(ContextCompat.getColor(this, R.color.bui_red_light))
                .buid();
        return stateListDrawable;
    }
}
