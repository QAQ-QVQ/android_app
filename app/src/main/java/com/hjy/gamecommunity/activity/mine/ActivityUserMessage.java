package com.hjy.gamecommunity.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjy.baserequest.bean.MineUserSetBean;
import com.hjy.baserequest.bean.MyGameInfoBean;
import com.hjy.baserequest.bean.UploadImageOne;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baserequest.request.UploadImage;
import com.hjy.baserequest.request.UploadImageInterface;
import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.MineUserAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xutil.data.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 我的—》设置-》个人资料
 * Date: 2020/6/24 16:12
 * Des:
 *
 * @author dy
 */
public class ActivityUserMessage extends BaseActivitySubordinate {
    private static final String TAG = "ActivityUserMessage";
    /**
     * 选项
     */
    private RecyclerView userItem;
    /**
     * 个人信息设置适配器
     */
    private MineUserAdapter userAdapter;
    /**
     * 个人资料头像标题
     */
    private TextView userTitle;
    /**
     * 个人资料头像
     */
    private ImageView userIcon;
    /**
     * 个人资料下一步
     */
    private ImageView userNext;
    /**
     * 头像整体
     */
    private RelativeLayout userRelative;
    /**
     * 个人资料列表
     */
    private ArrayList<MineUserSetBean> userList;

    @Override
    public Object getLayout() {
        return R.layout.activity_user_message;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(this, "个人资料");
        userItem = findViewById(R.id.mine_user_recyclerview);
        userTitle = findViewById(R.id.mine_user_title);
        userIcon = findViewById(R.id.mine_user_icon);
        userNext = findViewById(R.id.mine_user_next);
        userRelative = findViewById(R.id.mine_user_relativelayout);
    }

    String signature, nick, icon;
    /**
     * 性别：1=未知，2=男，3=女
     */
    int genderNum, birthdays;

    @Override
    public void initData() {
        if (!UserDataContainer.getInstance().getUserData().getAvatar().isEmpty()) {
            icon = UserDataContainer.getInstance().getUserData().getAvatar();
        }
        LoadingImageUtil.loadingImag(icon, userIcon, true);
        if (UserDataContainer.getInstance().getUserData().getNickname().isEmpty()) {
            nick = "未填写";
        } else {
            nick = UserDataContainer.getInstance().getUserData().getNickname();
        }
        MineUserSetBean nickName = new MineUserSetBean("昵称：", nick, 0);

        genderNum = UserDataContainer.getInstance().getUserData().getGender();
        MineUserSetBean gender = new MineUserSetBean("性别：", checkGenderNum(genderNum), 0);

        MineUserSetBean birthday = new MineUserSetBean("生日：", getBirthdayString(UserDataContainer.getInstance().getUserData().getBirthdate()), 0);

        if (UserDataContainer.getInstance().getUserData().getSignature().isEmpty()) {
            signature = "未填写";
        } else {
            signature = UserDataContainer.getInstance().getUserData().getSignature();
        }
        MineUserSetBean personalsignature = new MineUserSetBean("个性签名：", signature, 0);

        userList = new ArrayList<>();
        userList.add(nickName);
        userList.add(gender);
        userList.add(birthday);
        userList.add(personalsignature);
        userAdapter = new MineUserAdapter(userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        userItem.setLayoutManager(linearLayoutManager);
        userItem.setAdapter(userAdapter);
    }

    @Override
    public void listener() {
        userRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleBottomSheetList();
            }
        });
        userAdapter.setOnclickListener(new MineUserAdapter.OnclickListener() {
            @Override
            public void OnClick(int item) {
                Intent intent;
                switch (item) {
                    case 0:
                        intent = new Intent(getContext(), ActivityEditText.class);
                        intent.putExtra("title", "修改昵称");
                        startActivityForResult(intent, 2000);
                        break;
                    case 1:
                        showSexPickerView();
                        break;
                    case 2:
                        initDatePicker();
                        break;
                    case 3:
                        intent = new Intent(getContext(), ActivityEditText.class);
                        intent.putExtra("title", "个性签名");
                        startActivityForResult(intent, 2000);
                        break;
                    default:
                        Log.i(TAG, "OnClick: ");
                        break;
                }
            }

            @Override
            public void LongOnClick() {
                Log.i(TAG, "LongOnClick: ");
            }
        });
    }

    /**
     * 获取时间 时间戳转日期
     *
     * @param birthday
     * @return
     */
    private String getBirthdayString(int birthday) {
        birthdays = birthday;
        if (birthday == 0) {
            return "未选择";
        } else {
            String date = DateUtils.millis2String(birthday * (long) 1000, new SimpleDateFormat("yyyy.MM.dd"));
            return date;
        }
    }


    /**
     * 转换性别
     *
     * @param gender
     * @return
     */
    private int checkGenderString(String gender) {
        switch (gender) {
            case "未选择":
                return 1;
            case "男":
                return 2;
            case "女":
                return 3;
            default:
                return 1;
        }
    }

    /**
     * 转换性别
     *
     * @param gender
     * @return
     */
    private String checkGenderNum(int gender) {
        switch (gender) {
            case 1:
                return "未选择";
            case 2:
                return "男";
            case 3:
                return "女";
            default:
                return "未选择";
        }
    }

    /**
     * 时间选择
     */
    private void initDatePicker() {
        TimePickerView mDatePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                String string = DateUtils.date2String(date, new SimpleDateFormat("yyyy.MM.dd"));
                birthdays = (int) (date.getTime() / 1000);
                userList.get(2).setMsg(string);
                userAdapter.notifyDataSetChanged();
                Request.getInstance().updataUserInfo(nick, genderNum, birthdays, signature, icon, updataUserInfoJsonEntityCallback);
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setSubmitColor(getResources().getColor(R.color.bui_red_light))
                .setCancelColor(getResources().getColor(R.color.bui_black_light2))
                .setBgColor(getResources().getColor(R.color.bui_white))
                .setTitleBgColor(getResources().getColor(R.color.bui_white))
                .setSubmitText("确认")
                .setSubCalSize(16)
                .setTitleSize(16)
                .setTitleText("日历")
                .setTitleColor(getResources().getColor(R.color.bui_black_light1))
                .setLineSpacingMultiplier(2)
                .build();
        mDatePicker.show();
    }

    /**
     * 性别选择
     */
    private void showSexPickerView() {
        final int[] sexSelectOption = {0};
        final String[] mSexOption = {"男", "女"};
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                userList.get(1).setMsg(mSexOption[options1]);
                sexSelectOption[0] = options1;
                userAdapter.notifyDataSetChanged();
                genderNum = checkGenderString(mSexOption[options1]);
                Request.getInstance().updataUserInfo(nick, genderNum, birthdays, signature, icon, updataUserInfoJsonEntityCallback);
            }
        })
                .setTitleText("请选择性别")
                .setTitleColor(getResources().getColor(R.color.bui_black_light1))
                .setSelectOptions(sexSelectOption[0])
                .setSubmitColor(getResources().getColor(R.color.bui_red_light))
                .setCancelColor(getResources().getColor(R.color.bui_black_light2))
                .setBgColor(getResources().getColor(R.color.bui_white))
                .setTitleBgColor(getResources().getColor(R.color.bui_white))
                .setSubmitText("确认")
                .setCancelText(" ")
                .setSubCalSize(16)
                .setTitleSize(16)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .build();
        pvOptions.setPicker(mSexOption);
        pvOptions.show();
    }

    /**
     * 头像选择
     */
    private void showSimpleBottomSheetList() {
        new BottomSheet.BottomListSheetBuilder(getActivity())
                .addItem("从手机相册选择")
                .addItem("拍照")
                .addItem("取消")
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {

                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        switch (position) {
                            case 0:
                                getPictureSelector();
                                break;
                            case 1:
                                getCamera();
                                break;
                            case 2:
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    /**
     * 获取图片
     */
    public void getPictureSelector() {
        PictureSelector.create(getActivity())
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(false)
                .enableCrop(true)
                .compress(true)
                .previewEggs(true)
                .compress(true)
                .cropCompressQuality(50)
                .minimumCompressSize(100)
                .synOrAsy(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 拍照
     */
    public void getCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .previewImage(true)
                .enableCrop(true)
                .compress(true)
                .previewEggs(true)
                .compress(true)
                .cropCompressQuality(50)
                .minimumCompressSize(100)
                .synOrAsy(true)
                .forResult(PictureConfig.REQUEST_CAMERA);
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            List<LocalMedia> localMediaList;
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    localMediaList = PictureSelector.obtainMultipleResult(data);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    //照相
                    localMediaList = PictureSelector.obtainMultipleResult(data);
                    break;
                default:
                    localMediaList = null;
                    break;
            }
            if (localMediaList != null) {
                selectPic(localMediaList);
            }

        }
        if (requestCode == 2000) {
            String nickname;
            String personalsignature;
            switch (resultCode) {
                //昵称
                case 2001:
                    nickname = data.getExtras().getString("message");
                    if (!nickname.isEmpty()) {
                        userList.get(0).setMsg(nickname);
                        userAdapter.notifyDataSetChanged();
                        nick = nickname;
                        Request.getInstance().updataUserInfo(nick, genderNum, birthdays, signature, icon, updataUserInfoJsonEntityCallback);
                    }
                    break;
                //个性签名
                case 2002:
                    personalsignature = data.getExtras().getString("message");
                    if (!personalsignature.isEmpty()) {
                        userList.get(3).setMsg(personalsignature);
                        userAdapter.notifyDataSetChanged();
                        signature = personalsignature;
                        Request.getInstance().updataUserInfo(nick, genderNum, birthdays, personalsignature, icon, updataUserInfoJsonEntityCallback);
                    }
                    break;
                default:
                    Log.i(TAG, "onActivityResult: ");
                    break;
            }
        }
    }

    /**
     * @param localMediaList 图片列表
     */
    private void selectPic(List<LocalMedia> localMediaList) {
        LocalMedia media = localMediaList.get(0);
        String path;
        if (media.isCut() && !media.isCompressed()) {
            // 裁剪过
            path = media.getCutPath();
        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = media.getCompressPath();
        } else {
            // 原图
            path = media.getPath();
        }
        LoadingImageUtil.loadingImag(path, userIcon, false);
        UploadImage uploadImage = new UploadImage(getActivity());
        uploadImage.uploadImageOne(path, new UploadImageInterface<UploadImageOne>() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(UploadImageOne uploadImageOne) {
                icon = uploadImageOne.getData();
                Request.getInstance().updataUserInfo(nick, genderNum, birthdays, signature, icon, updataUserInfoJsonEntityCallback);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void schedule(float progress) {

            }

            @Override
            public void onSuccess(String imagUrl, String imagMd5) {

            }
        });
        // 例如 LocalMedia 里面返回五种path
        // 1.media.getPath(); 原图path，但在Android Q版本上返回的是content:// Uri类型
        // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
        // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
        // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
        // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform(false);此字段将返回空
        // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
//                    for (LocalMedia media : mSelectList) {
//                        Log.i(TAG, "压缩::" + media.getCompressPath());
//                        Log.i(TAG, "原图::" + media.getPath());
//                        Log.i(TAG, "裁剪::" + media.getCutPath());
//                        Log.i(TAG, "是否开启原图::" + media.isOriginal());
//                        Log.i(TAG, "原图路径::" + media.getOriginalPath());
//                        Log.i(TAG, "Android Q 特有Path::" + media.getAndroidQToPath());
//                    }
    }

    JsonEntityCallback updataUserInfoJsonEntityCallback = new JsonEntityCallback<MyGameInfoBean>(MyGameInfoBean.class) {

        @Override
        protected void onSuccess(MyGameInfoBean myGameInfoBean) {
            switch (myGameInfoBean.getCode()) {
                case 200:
                    ToastUtil.tost(myGameInfoBean.getMsg());
                    //更新昵称,个性签名,性别
                    UserDataContainer.getInstance().getUserData().setNickname(nick);
                    UserDataContainer.getInstance().getUserData().setSignature(signature);
                    UserDataContainer.getInstance().getUserData().setGender(genderNum);
                    UserDataContainer.getInstance().getUserData().setBirthdate(birthdays);
                    UserDataContainer.getInstance().getUserData().setAvatar(icon);
                    initData();
                    listener();
                    break;
                case 101:
                    ToastUtil.tost(myGameInfoBean.getMsg());
                    break;
                default:
                    ToastUtil.tost(myGameInfoBean.getMsg());
                    break;
            }
        }
    };
}
