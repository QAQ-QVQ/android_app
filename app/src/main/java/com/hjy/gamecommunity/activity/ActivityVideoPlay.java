package com.hjy.gamecommunity.activity;

import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NumberUtils;
import com.hjy.baserequest.bean.VideoDetail;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.baseutil.LoadingImageUtil;
import com.hjy.baseutil.ToastUtil;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.utils.ShareUtil;
import com.hjy.gamecommunity.view.VideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.HashMap;

import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/20 9:57
 * 描述: 视频播放
 */
public class ActivityVideoPlay extends BaseActivity {
    public final static String VIDEO_ID = "视频id";
    private ColorStateImageView mIvBackImageBar;
    private ColorStateImageView mCsivShare;
    private LinearLayout mLlBar;
    private ImageView mCsivLiked;
    private TextView mTvLikedNum;
    private TextView mTvHotspotNum;
    private TextView mTvTitle;
    private VideoPlayer mVideoPlayer;
    private OrientationUtils orientationUtils;

    @Override
    public Object getLayout() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initView() {
        mIvBackImageBar = findViewById(R.id.iv_back_image_bar);
        mCsivShare = findViewById(R.id.csiv_Share);
        mLlBar = findViewById(R.id.ll_bar);
        mCsivLiked = findViewById(R.id.csiv_liked);
        mTvLikedNum = findViewById(R.id.tv_LikedNum);
        mTvHotspotNum = findViewById(R.id.tv_HotspotNum);

        mTvTitle = findViewById(R.id.tv_Title);
        mVideoPlayer = findViewById(R.id.video_player);


    }

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(false);//设置状态栏是否为浅色模式


        //设置旋转
        orientationUtils = new OrientationUtils(this, mVideoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        mVideoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        mVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        int video_id = getIntent().getIntExtra(VIDEO_ID, -1);
        Request.getInstance().videoDetail(video_id, videoDetailJsonEntityCallback);

    }

    @Override
    public void listener() {
        mIvBackImageBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCsivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareParams shareParams = ShareUtil.getShareParams("标题（字段待确认）", "文本内容（字段待确认）", "https://www.baidu.com");
                ShareUtil.getInstance().shareDialog(getActivity(), mCsivShare, shareParams, new ShareUtil.SharingResultsListener() {
                    @Override
                    public void onComplete(Platform platform, int action, HashMap<String, Object> data) {

                    }

                    @Override
                    public void onError(Platform platform, int action, int errorCode, Throwable error) {

                    }

                    @Override
                    public void onCancel(Platform platform, int action) {

                    }
                });
            }
        });

        mCsivLiked.setOnClickListener(new View.OnClickListener() {
            private boolean isClick;

            @Override
            public void onClick(View v) {
                isClick = !isClick;
                if (isClick) {
                    mCsivLiked.setImageResource(R.mipmap.liked_gradient_t);
                } else {
                    mCsivLiked.setImageResource(R.mipmap.liked_white_t);
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mVideoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        mVideoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    /**
     * 视频详情
     */
    JsonEntityCallback videoDetailJsonEntityCallback = new JsonEntityCallback<VideoDetail>(VideoDetail.class) {
        @Override
        protected void onSuccess(VideoDetail videoDetail) {
            VideoDetail.DataBean data = videoDetail.getData();
            if (data != null) {
                //增加封面
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LoadingImageUtil.loadingImag(data.getCover_picture(), imageView, false);
                mVideoPlayer.setThumbImageView(imageView);

                mVideoPlayer.setUp(data.getVideo_url(), true, "");
                mVideoPlayer.startPlayLogic();

                if (!TextUtils.isEmpty(data.getTitle()))
                    mTvTitle.setText(data.getTitle());

                int sum_like = data.getSum_like();
                if (sum_like < 10000) {
                    mTvLikedNum.setText(String.valueOf(sum_like));
                } else {
                    String format = NumberUtils.format(sum_like / 10000.0f, 2);
                    mTvLikedNum.setText(format + "万");
                }

                int sum_play = data.getSum_play();
                if (sum_play < 10000) {
                    mTvHotspotNum.setText(String.valueOf(sum_play));
                } else {
                    String format = NumberUtils.format(sum_play / 10000.0f, 2);
                    mTvHotspotNum.setText(format + "万");
                }


            } else {
                ToastUtil.tost(videoDetail.getMsg());
            }


        }
    };
}
