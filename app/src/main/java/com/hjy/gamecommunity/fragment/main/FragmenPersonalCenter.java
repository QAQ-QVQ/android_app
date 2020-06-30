package com.hjy.gamecommunity.fragment.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseFragment;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.activity.login.ActivityPhoneLogin;
import com.hjy.gamecommunity.activity.mine.ActivitySet;

/**
 * 我的
 * Author: dy
 * Date: 2020/4/8 15:47
 * Des:
 * @author dy
 */
public class FragmenPersonalCenter extends BaseFragment implements View.OnClickListener {

    /**
     * 昵称
     */
    private TextView nickmane;
    /**
     * 个性签名
     */
    private TextView personalsignature;
    /**
     * 金豆数量
     */
    private TextView goldNum;
    /**
     * 银豆数量
     */
    private TextView silverNum;
    /**
     * 鲜花数量
     */
    private TextView flowerNum;
    /**
     * 绑定角色名称
     */
    private TextView bindName;
    /**
     * 绑定区服信息
     */
    private TextView bindService;
    /**
     * 礼包数量
     */
    private TextView giftNum;
    /**
     * 设置
     */
    private ImageView ivSet;
    /**
     * 头像
     */
    private ImageView ivIcon;
    /**
     * 家族图标
     */
    private ImageView ivFamilyItem1;
    /**
     * 家族图标
     */
    private ImageView ivFamilyItem2;
    /**
     * 游戏图标
     */
    private ImageView ivGameItem;

    /**
     * 我的金豆
     */
    private RelativeLayout gold;
    /**
     * 我的银豆
     */
    private RelativeLayout silver;
    /**
     * 我的鲜花
     */
    private RelativeLayout flower;
    /**
     * 绑定账户
     */
    private LinearLayout bind;
    /**
     * 切换绑定账户
     */
    private LinearLayout bindChange;
    /**
     * 我的家族
     */
    private LinearLayout family;
    /**
     * 我的礼包
     */
    private LinearLayout gift;
    /**
     * 我的游戏
     */
    private LinearLayout game;
    /**
     * 直播
     */
    private LinearLayout live;
    /**
     * 客服
     */
    private RelativeLayout customer;
    /**
     * 礼包数量
     */
    private int giftNumber = 6;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initView(View mRootView) {
        ivSet = findViewById(R.id.mine_set);
        ivSet.setOnClickListener(this);
        setPaddingNumTop(ivSet, 12);
        nickmane = findViewById(R.id.mine_nickname);
        nickmane.setOnClickListener(this);
        personalsignature = findViewById(R.id.mine_personalsignature);
        personalsignature.setOnClickListener(this);
        goldNum = findViewById(R.id.gold_num);
        silverNum = findViewById(R.id.silver_name);
        flowerNum = findViewById(R.id.flower_num);
        bindName = findViewById(R.id.mine_bind_name);
        bindService = findViewById(R.id.mine_bind_service);
        flowerNum = findViewById(R.id.flower_num);
        giftNum = findViewById(R.id.mine_gift_num);
        ivIcon = findViewById(R.id.mine_icon);
        ivIcon.setOnClickListener(this);
        ivFamilyItem1 = findViewById(R.id.mine_family_item1);
        ivFamilyItem2 = findViewById(R.id.mine_family_item2);
        ivGameItem = findViewById(R.id.mine_game_item);
        gold = findViewById(R.id.mine_gold);
        gold.setOnClickListener(this);
        silver = findViewById(R.id.mine_silver);
        silver.setOnClickListener(this);
        flower = findViewById(R.id.mine_flower);
        flower.setOnClickListener(this);
        bind = findViewById(R.id.mine_tobinding);
        bind.setOnClickListener(this);
        bindChange = findViewById(R.id.mine_bind_change);
        bindChange.setOnClickListener(this);
        family = findViewById(R.id.mine_family);
        family.setOnClickListener(this);
        gift = findViewById(R.id.mine_gift);
        gift.setOnClickListener(this);
        game = findViewById(R.id.mine_game);
        game.setOnClickListener(this);
        live = findViewById(R.id.mine_live);
        live.setOnClickListener(this);
        customer = findViewById(R.id.mine_customer);
        customer.setOnClickListener(this);
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(true);
        }
    }

    @Override
    public void initData() {
        SpannableString textGift = new SpannableString("还有 " + giftNumber + " 个礼包");
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        textGift.setSpan(styleSpan, 3, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textGift.setSpan(new ForegroundColorSpan(Color.parseColor("#EE3847")), 3, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        giftNum.setText(textGift);
    }

    @Override
    public void listener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_set:
//                startActivity(new Intent(getContext(), ActivityPhoneLogin.class));
                startActivity(new Intent(getContext(), ActivitySet.class));
                break;
            case R.id.mine_icon:
                // TODO: 2020/6/23 头像
                startActivity(new Intent(getContext(), ActivityPhoneLogin.class));
                break;
            case R.id.mine_nickname:
                // TODO: 2020/6/23 昵称
                break;
            case R.id.mine_personalsignature:
                // TODO: 2020/6/23 个性签名
                break;
            case R.id.mine_gold:
                // TODO: 2020/6/23 金豆
                break;
            case R.id.mine_silver:
                // TODO: 2020/6/23 银豆
                break;
            case R.id.mine_flower:
                // TODO: 2020/6/23 鲜花
                break;
            case R.id.mine_tobinding:
                // TODO: 2020/6/23 绑定区服
                break;
            case R.id.mine_bind_change:
                // TODO: 2020/6/23 切换区服
                break;
            case R.id.mine_family:
                // TODO: 2020/6/23 家族
                break;
            case R.id.mine_gift:
                // TODO: 2020/6/23 礼物
                break;
            case R.id.mine_game:
                // TODO: 2020/6/23 游戏
                break;
            case R.id.mine_live:
                // TODO: 2020/6/23 直播
                break;
            case R.id.mine_customer:
                // TODO: 2020/6/23 客服
                break;
            default:
                break;
        }
    }
}
