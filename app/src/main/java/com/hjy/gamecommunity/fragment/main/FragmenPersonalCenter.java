package com.hjy.gamecommunity.fragment.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hjy.baserequest.bean.BindRoleBean;
import com.hjy.baserequest.bean.CheckUpdateBean;
import com.hjy.baserequest.bean.FamilyInfoBean;
import com.hjy.baserequest.bean.GiftNumBean;
import com.hjy.baserequest.bean.MyGameInfoBean;
import com.hjy.baserequest.bean.PropertyNumberBean;
import com.hjy.baserequest.bean.UserInfo;
import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;

import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseFragment;

import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;

import com.hjy.gamecommunity.activity.mine.ActivityBindRole;
import com.hjy.gamecommunity.activity.mine.ActivityCheckRole;
import com.hjy.gamecommunity.activity.mine.ActivityMyGame;
import com.hjy.gamecommunity.activity.mine.ActivitySet;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的
 * Author: dy
 * Date: 2020/4/8 15:47
 * Des:
 *
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
     * 头像框
     */
    private ImageView iconHead;
    /**
     * 头像容器
     */
    private FrameLayout iconHeadLayout;
    /**
     * 礼包数量
     */
    private int giftNumber = 6;
    /**
     * 小红点
     */
    private ImageView Update;
    /**
     * 加载弹框
     */
    private MiniLoadingDialog mMiniLoadingDialog;

    private List load = new ArrayList();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initView(View mRootView) {
//        setPaddingNumTop(findViewById(R.id.mine_set_layout),12);
        ivSet = findViewById(R.id.mine_set);
        ivSet.setOnClickListener(this);
        nickmane = findViewById(R.id.mine_nickname);
        nickmane.setOnClickListener(this);
        personalsignature = findViewById(R.id.mine_personalsignature);
        personalsignature.setOnClickListener(this);
        goldNum = findViewById(R.id.gold_num);
        silverNum = findViewById(R.id.silver_num);
        flowerNum = findViewById(R.id.flower_num);
        bindName = findViewById(R.id.mine_bind_name);
        bindService = findViewById(R.id.mine_bind_service);
        flowerNum = findViewById(R.id.flower_num);
        giftNum = findViewById(R.id.mine_gift_num);
        ivIcon = findViewById(R.id.mine_icon);
        iconHeadLayout = findViewById(R.id.mine_icon_head_layout);
        iconHeadLayout.setOnClickListener(this);
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
        live = findViewById(R.id.mine_live_linearlayout);
        live.setOnClickListener(this);
        customer = findViewById(R.id.mine_customer);
        customer.setOnClickListener(this);
        iconHead = findViewById(R.id.mine_icon_head);
        Update = findViewById(R.id.mine_set_update);
        mMiniLoadingDialog = WidgetUtils.getMiniLoadingDialog(getContext());
    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            setStatusBarLightMode(true);
        }
    }

    @Override
    public void initData() {
        mMiniLoadingDialog.show();
        Request.getInstance().getUserinfo(userinfoJsonEntityCallback);
        Request.getInstance().getGiftNum(giftNumJsonEntityCallback);
        Request.getInstance().getFamilyInfo(familyInfoJsonEntityCallback);
        Request.getInstance().getMyGameInfo(gameInfoJsonEntityCallback);
        Request.getInstance().getPropertyNumber(PropertyNumberJsonEntityCallback);
        Request.getInstance().checkUpdate(checkUpdateJsonEntityCallback);
        Request.getInstance().getBindRole(bindRoleJsonEntityCallback);
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
            case R.id.mine_icon_head_layout:
                // TODO: 2020/6/23 头像
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
//                  绑定角色
                startActivity(new Intent(getContext(), ActivityBindRole.class));
                break;
            case R.id.mine_bind_change:
                //  切换角色
                startActivity(new Intent(getContext(), ActivityCheckRole.class));
                break;
            case R.id.mine_family:
                // TODO: 2020/6/23 家族
                break;
            case R.id.mine_gift:
                // TODO: 2020/6/23 礼物
                break;
            case R.id.mine_game:
                //  我的游戏
                startActivity(new Intent(getContext(), ActivityMyGame.class));
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

    @Override
    public void onResume() {
        super.onResume();
        if (UserDataContainer.getInstance().isLogin()) {
            initData();
        }
    }

    /**
     * 获取用户信息
     */
    JsonEntityCallback userinfoJsonEntityCallback = new JsonEntityCallback<UserInfo>(UserInfo.class) {
        @Override
        protected void onSuccess(UserInfo userInfo) {
            if (userInfo.getCode() == 200) {
                load.add(1);
                UserData userData = UserDataContainer.getInstance().getUserData();
                userData.setAvatar(userInfo.getData().getAvatar());
                userData.setUsername(userInfo.getData().getUsername());
                userData.setNickname(userInfo.getData().getNickname());
                userData.setPhone(userInfo.getData().getPhone());
                userData.setSignature(userInfo.getData().getSignature());
                userData.setGender(userInfo.getData().getGender());
                userData.setBirthdate(userInfo.getData().getBirthdate());
                userData.setIs_anchor(userInfo.getData().getIs_anchor());
                if (!userInfo.getData().getNickname().isEmpty()) {
                    nickmane.setText(userInfo.getData().getNickname());
                }
                if (!userInfo.getData().getSignature().isEmpty()) {
                    personalsignature.setText("个性签名：" + userInfo.getData().getSignature());
                }
                if (!userInfo.getData().getAvatar().isEmpty()) {
                    LoadingImageUtil.loadingImag(userInfo.getData().getAvatar(), ivIcon, false);
                }
                //1:没主播权限 2：有主播权限
                if (userInfo.getData().getIs_anchor() == 1) {
                    live.setVisibility(View.INVISIBLE);
                } else if (userInfo.getData().getIs_anchor() == 2) {
                    live.setVisibility(View.VISIBLE);
                }
                // TODO: 2020/6/30 头像框
            } else {
                ToastUtil.tost(userInfo.getMsg());
            }
            end();
        }
    };
    /**
     * 获取礼包数量
     */
    JsonEntityCallback giftNumJsonEntityCallback = new JsonEntityCallback<GiftNumBean>(GiftNumBean.class) {

        @Override
        protected void onSuccess(GiftNumBean giftNumBean) {
            if (giftNumBean.getCode() == 200) {
                load.add(2);
                giftNumber = giftNumBean.getData();
                if (giftNumber == 0) {
                    giftNum.setText(" ");
                } else {
                    SpannableString textGift = new SpannableString("还有 " + giftNumber + " 个礼包");
                    StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                    textGift.setSpan(styleSpan, 3, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    textGift.setSpan(new ForegroundColorSpan(Color.parseColor("#EE3847")), 3, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    giftNum.setText(textGift);
                }
            } else {
                ToastUtil.tost(giftNumBean.getMsg());
            }
            end();
        }
    };
    /**
     * 获取家族信息
     */
    JsonEntityCallback familyInfoJsonEntityCallback = new JsonEntityCallback<FamilyInfoBean>(FamilyInfoBean.class) {

        @Override
        protected void onSuccess(FamilyInfoBean familyInfoBean) {
            if (familyInfoBean.getCode() == 200) {
                load.add(3);
                switch (familyInfoBean.getData().size()) {
                    case 0:
                        ivFamilyItem1.setVisibility(View.INVISIBLE);
                        ivFamilyItem2.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        ivFamilyItem2.setVisibility(View.VISIBLE);
                        LoadingImageUtil.loadingImag(familyInfoBean.getData().get(0).getAvatar(), ivFamilyItem2, false);
                        break;
                    default:
                        ivFamilyItem1.setVisibility(View.VISIBLE);
                        ivFamilyItem2.setVisibility(View.VISIBLE);
                        LoadingImageUtil.loadingImag(familyInfoBean.getData().get(0).getAvatar(), ivFamilyItem1, false);
                        LoadingImageUtil.loadingImag(familyInfoBean.getData().get(1).getAvatar(), ivFamilyItem2, false);
                        break;
                }
            } else {
                ToastUtil.tost(familyInfoBean.getMsg());
            }
            end();
        }
    };
    /**
     * 我的游戏
     */
    JsonEntityCallback gameInfoJsonEntityCallback = new JsonEntityCallback<MyGameInfoBean>(MyGameInfoBean.class) {

        @Override
        protected void onSuccess(MyGameInfoBean myGameInfoBean) {
            if (myGameInfoBean.getCode() == 200) {
                load.add(4);
                if (myGameInfoBean.getData().size() == 0) {
                    ivGameItem.setVisibility(View.INVISIBLE);
                } else {
                    ivGameItem.setVisibility(View.VISIBLE);
                    LoadingImageUtil.loadingImag(myGameInfoBean.getData().get(0).getIcon(), ivGameItem, false);
                }
            } else {
                ToastUtil.tost(myGameInfoBean.getMsg());
            }
            end();
        }
    };
    /**
     * 资产数目
     */
    JsonEntityCallback PropertyNumberJsonEntityCallback = new JsonEntityCallback<PropertyNumberBean>(PropertyNumberBean.class) {

        @Override
        protected void onSuccess(PropertyNumberBean propertyNumberBean) {
            if (propertyNumberBean.getCode() == 200) {
                load.add(5);
                goldNum.setText(String.valueOf(propertyNumberBean.getData().getCoin()));
                silverNum.setText(String.valueOf(propertyNumberBean.getData().getUser_credit()));
                flowerNum.setText(String.valueOf(propertyNumberBean.getData().getFlower_number()));
            } else {
                ToastUtil.tost(propertyNumberBean.getMsg());
            }
            end();
        }
    };
    /**
     * 检查更新
     */
    JsonEntityCallback checkUpdateJsonEntityCallback = new JsonEntityCallback<CheckUpdateBean>(CheckUpdateBean.class) {

        @Override
        protected void onSuccess(CheckUpdateBean checkUpdateBean) {
//            switch (checkUpdateBean.getCode()) {
//                case 200:
//                    load.add(6);
////                checkUpdateBean.getData().getVersion_code()
//                    // TODO: 2020/6/29 检查更新
//                    Update.setVisibility(View.INVISIBLE);
//                    Update.setVisibility(View.VISIBLE);
//                    break;
//                case 101:
//                    ToastUtil.tost(checkUpdateBean.getMsg());
//                    break;
//                default:
//                    ToastUtil.tost(checkUpdateBean.getMsg());
//                    break;
//            }
//            end();
        }
    };

    /**
     * 绑定角色数据
     */
    JsonEntityCallback bindRoleJsonEntityCallback = new JsonEntityCallback<BindRoleBean>(BindRoleBean.class) {

        @Override
        protected void onSuccess(BindRoleBean bindRoleBean) {
            if (bindRoleBean.getCode() == 200) {
                load.add(7);
                if (bindRoleBean.getData() != null) {
                    bind.setVisibility(View.GONE);
                    bindChange.setVisibility(View.VISIBLE);
                    bindName.setText(bindRoleBean.getData().getCp_role_name());
                    bindService.setText(bindRoleBean.getData().getCp_server_name());
                } else {
                    bind.setVisibility(View.VISIBLE);
                    bindChange.setVisibility(View.GONE);
                }
            } else {
                ToastUtil.tost(bindRoleBean.getMsg());
            }
            end();
        }
    };

    public void end() {
        if (load.size() >= 6) {
            mMiniLoadingDialog.dismiss();
        }
    }
}
