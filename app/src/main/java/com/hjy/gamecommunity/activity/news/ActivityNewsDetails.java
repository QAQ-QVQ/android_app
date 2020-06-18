package com.hjy.gamecommunity.activity.news;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjy.baserequest.bean.NewsDetail;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseui.ui.BaseActivity;
import com.hjy.baseui.ui.view.imageview.ColorStateImageView;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.utils.ShareUtil;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichType;
import com.zzhoujay.richtext.callback.ImageFixCallback;
import com.zzhoujay.richtext.callback.OnImageClickListener;
import com.zzhoujay.richtext.callback.OnImageLongClickListener;

import java.util.HashMap;
import java.util.List;

import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 16:04
 * 描述: 资讯-详情
 */
public class ActivityNewsDetails extends BaseActivity {
    public final static String NEWS_ID = "资讯id";
    private ColorStateImageView mIvBackImageBar;
    private TextView mTvTextBar;
    private LinearLayout mLlBar;
    private ColorStateImageView mCsivShare;
    private TextView mTvRichText;

    @Override
    public Object getLayout() {
        return R.layout.activity_news_details;

    }

    @Override
    public void initView() {
        mIvBackImageBar = findViewById(R.id.iv_back_image_bar);
        mTvTextBar = findViewById(R.id.tv_text_bar);
        mLlBar = findViewById(R.id.ll_bar);
        mCsivShare = findViewById(R.id.csiv_Share);
        mTvRichText = findViewById(R.id.tv_RichText);
    }

    @Override
    public void initData() {
        transparentStatusBar();//透明状态栏
        setStatusBarLightMode(true);//设置状态栏是否为浅色模式

        mTvTextBar.setText("资讯详情");

        RichText.initCacheDir(this);

        int news_id = getIntent().getIntExtra(NEWS_ID, -1);
        Request.getInstance().newsDetail(news_id, newsDetailJsonEntityCallback);
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
                if (detailData != null) {
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
            }
        });
    }

    /**
     * 加载富文本
     *
     * @param richText
     */
    private void richText(String richText) {
        RichText.from(richText, RichType.markdown)
                .bind(this)
                .autoFix(false) // 是否自动修复，默认true
                .autoPlay(true) // gif图片是否自动播放
                .showBorder(false) // 是否显示图片边框
                .borderColor(getResources().getColor(R.color.colorPrimary)) // 图片边框颜色
                .borderSize(5) // 边框尺寸
                .borderRadius(20) // 图片边框圆角弧度
                .fix(new ImageFixCallback() {
                    @Override
                    public void onInit(ImageHolder holder) {

                    }

                    @Override
                    public void onLoading(ImageHolder holder) {

                    }

                    @Override
                    public void onSizeReady(ImageHolder holder, int imageWidth, int imageHeight, ImageHolder.SizeHolder sizeHolder) {
//                        int screenWidth = ViewSeting.getScreenWidth();
//                        int width2 = (int) (screenWidth * 0.9);
//                        int height2 = (int) (width2 * 1.5);
//                        holder.setWidth(width2);
//                        holder.setHeight(height2);


                    }

                    @Override
                    public void onImageReady(ImageHolder holder, int width, int height) {

                    }

                    @Override
                    public void onFailure(ImageHolder holder, Exception e) {

                    }
                }) // 设置自定义修复图片宽高
                .resetSize(true) // 默认false，是否忽略img标签中的宽高尺寸（只在img标签中存在宽高时才有效）
                .scaleType(ImageHolder.ScaleType.fit_auto) // 图片缩放方式
                //.size((int) width, (int) height)
                // .errorImage(errorImage) // 设置加载失败的错误图
                .cache(CacheType.all) // 缓存类型，默认为Cache.ALL（缓存图片和图片大小信息和文本样式信息）
                .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT) // 图片占位区域的宽高
                .clickable(true) // 是否可点击，默认只有设置了点击监听才可点击
                .imageClick(new OnImageClickListener() {
                    @Override
                    public void imageClicked(List<String> imageUrls, int position) {
                        if (imageUrls.size() > position) {
                            String imageUrl = imageUrls.get(position);
                            //showMaxImage(imageUrl);
                            Log.d("imageClicked", "imageUrls:" + imageUrls.get(position));
                        }
                    }
                }) //设置图片点击回调
                .imageLongClick(new OnImageLongClickListener() {
                    @Override
                    public boolean imageLongClicked(List<String> imageUrls, int position) {
                        Log.d("imageLongClicked", "imageUrls:" + imageUrls.get(position));
                        return false;
                    }
                }) // 设置图片长按回调
                .into(mTvRichText);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RichText.clear(this);//关闭当前界面调用富文本RichText对象的clear方法释放资源
        RichText.recycle();
    }

    /***
     *
     */
    private NewsDetail.DataBean detailData;
    JsonEntityCallback newsDetailJsonEntityCallback = new JsonEntityCallback<NewsDetail>(NewsDetail.class) {
        @Override
        protected void onSuccess(NewsDetail newsDetail) {
            detailData = newsDetail.getData();
            if (detailData != null) {
                richText(detailData.getContent());
            }
        }
    };
}
