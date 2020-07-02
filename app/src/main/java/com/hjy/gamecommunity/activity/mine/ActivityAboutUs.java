package com.hjy.gamecommunity.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hjy.baseui.ui.BaseActivitySubordinate;
import com.hjy.gamecommunity.R;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;
import com.zzhoujay.richtext.callback.OnImageLongClickListener;

import java.util.List;

/**
 * 我的—》设置-》关于我们
 * Date: 2020/6/24 16:12
 * Des:
 *
 * @author dy
 */
public class ActivityAboutUs extends BaseActivitySubordinate {
    /**
     * 关于我们
     */
    private TextView aboutUs;

    @Override
    public Object getLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        //透明状态栏
        transparentStatusBar();
        //设置状态栏是否为浅色模式
        setStatusBarLightMode(true);
        initToobar(getActivity(), "关于我们");
        aboutUs = findViewById(R.id.mine_about_tv);
    }

    @Override
    public void initData() {
        RichText.initCacheDir(this);
        // TODO: 2020/6/29  获取关于我们
        richText("ceshishishi");
    }

    @Override
    public void listener() {

    }

    /**
     * 加载富文本
     *
     * @param richText
     */
    private void richText(String richText) {
        RichText.fromMarkdown(richText)
                .bind(this)
                // 是否自动修复，默认true
                .autoFix(true)
                // gif图片是否自动播放
                .autoPlay(true)
                // 是否显示图片边框
                .showBorder(false)
                // 图片边框颜色
                .borderColor(getResources().getColor(R.color.colorPrimary))
                // 边框尺寸
                .borderSize(5)
                // 图片边框圆角弧度
                .borderRadius(20)
                // 默认false，是否忽略img标签中的宽高尺寸（只在img标签中存在宽高时才有效）
                .resetSize(true)
                // 图片缩放方式
                .scaleType(ImageHolder.ScaleType.fit_auto)
                // 缓存类型，默认为Cache.ALL（缓存图片和图片大小信息和文本样式信息）
                .cache(CacheType.all)
                // 图片占位区域的宽高
                .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                // 是否可点击，默认只有设置了点击监听才可点击
                .clickable(false)
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
                }) //设置图片长按回调
                .into(aboutUs);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭当前界面调用富文本RichText对象的clear方法释放资源
        RichText.clear(this);
        RichText.recycle();
    }
}
