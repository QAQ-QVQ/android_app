package com.hjy.gamecommunity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hjy.gamecommunity.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import moe.codeest.enviews.ENPlayView;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/20 11:10
 * 描述:
 */
public class VideoPlayer extends StandardGSYVideoPlayer {
    public VideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoPlayer(Context context) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_player_layout;
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        //增加title
        getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        getBackButton().setVisibility(View.GONE);
        //
        getFullscreenButton().setVisibility(View.GONE);

        //  mVideoPlayer.setBottomProgressBarDrawable();

//        ClipDrawable d = new ClipDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorPrimary)), Gravity.LEFT, ClipDrawable.HORIZONTAL);
//        mVideoPlayer.setBottomProgressBarDrawable(d);
//        mVideoPlayer.setBottomShowProgressBarDrawable(d, new);


    }

    /**
     * 定义开始按键显示
     */
    @Override
    protected void updateStartImage() {
        if (mStartButton instanceof ENPlayView) {
            ENPlayView enPlayView = (ENPlayView) mStartButton;
            enPlayView.setDuration(500);
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                enPlayView.play();
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                enPlayView.pause();
            } else {
                enPlayView.pause();
            }
        } else if (mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) mStartButton;
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                imageView.setImageResource(R.mipmap.video_suspend_t);
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                imageView.setImageResource(R.drawable.video_click_error_selector);
            } else {
                imageView.setImageResource(R.mipmap.video_player_t);
            }
        }
    }
}
