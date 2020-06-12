package com.hjy.baserequest.request;

import android.app.Activity;
import android.util.Log;

import com.blankj.utilcode.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hjy.baserequest.bean.UploadImageOne;
import com.hjy.baseutil.ToastUtil;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 * <p>
 * 上传图片
 */

public class UploadImage {
    //弱引用的对象
    private WeakReference<Activity> activityWeakReference;

    public UploadImage(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }

    /**
     * 集合转string
     *
     * @param stringListMd5
     * @return
     */
    private String list_String(List<String> stringListMd5) {
        return Arrays.toString(stringListMd5.toArray())
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }


    /**
     * 多图上传
     * <p>
     * 检查服务器是否有该图片，有则直接返回地址，没有则上传
     * 上传成功后，会将图片URL返回回来
     *
     * @param compressPathList //压缩后的图片地址集合
     * @param originalPathList //原图片地址集合
     * @param uploadImageInterface
     */
    private UploadImageInterface uploadImageOnedDataCallBack;
    private int index = 0;

    public void goUploadImage(final List<String> compressPathList, final List<String> originalPathList, final UploadImageInterface uploadImageInterface) {
        index = 0;
        final int size = compressPathList.size();
        if (compressPathList == null || size <= 0) {
            ToastUtil.tost("加载图片失败，请重新选择");
            return;
        }
        final List<String> stringListMd5 = new ArrayList<>();
        for (String s : originalPathList) {
            stringListMd5.add(FileUtils.getFileMD5ToString(new File(s)));
        }

        final List<String> imageUrlList = new ArrayList<>();//服务器图片地址
        uploadImageOnedDataCallBack = new UploadImageInterface<UploadImageOne>() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(UploadImageOne uploadImageOne) {
                if (++index < size) {
                    uploadImageOne(compressPathList.get(index), uploadImageOnedDataCallBack);
                }

                imageUrlList.add(uploadImageOne.getData());
                if (index == size) {
                    if (imageUrlList.size() == size) {
                        uploadImageInterface.onSuccess(list_String(imageUrlList), list_String(stringListMd5));
                    }
                    uploadImageInterface.onFinish();


                }

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
        };
        uploadImageOne(compressPathList.get(index), uploadImageOnedDataCallBack);

    }

    /**
     * 上传图片（多图上传）
     *
     * @param
     */
    private void goUploadMultiImage(List<String> compressPathList, final List<String> originalPathList, final UploadImageInterface uploadImageInterface) {
        Request.getInstance().uploadImage(compressPathList, originalPathList,
                new StringCallback() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        uploadImageInterface.onFinish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        uploadImageInterface.onError();
                        ToastUtil.tost("多图上传连接失败");
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        uploadImageInterface.schedule(progress.fraction);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            com.hjy.baserequest.bean.UploadImage uploadImage = new Gson().fromJson(response.body(), com.hjy.baserequest.bean.UploadImage.class);
                            if (uploadImage.getData() != null && uploadImage.getData().size() > 0) {
                                List<String> stringListMd5 = new ArrayList<>();
                                for (String s : originalPathList) {
                                    stringListMd5.add(FileUtils.getFileMD5ToString(new File(s)));
                                }
                                uploadImageInterface.onSuccess(list_String(uploadImage.getData()), list_String(stringListMd5));
                            } else {
                                ToastUtil.tost("多图上传失败" + uploadImage.getMsg());
                            }
                        } catch (JsonSyntaxException e) {
                            ToastUtil.tost("多图上传-数据格式错误！" + e.getMessage());
                        }
                    }
                }
        );
    }

    /**
     * 单图上传
     *
     * @param urlFile
     */
    private void uploadImageOne(String urlFile, final UploadImageInterface<UploadImageOne> uploadImageInterface) {
        Request.getInstance().uploadImageOne(urlFile, new StringCallback() {
            @Override
            public void onFinish() {
                super.onFinish();
                uploadImageInterface.onFinish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                uploadImageInterface.onError();
                ToastUtil.tost("单图上传连接失败！");
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                uploadImageInterface.schedule(progress.fraction);
            }


            @Override
            public void onSuccess(Response<String> response) {

                String body = response.body();
                Log.d("uploadImageOne", body);
                try {
                    UploadImageOne uploadImageOne = new Gson().fromJson(body, UploadImageOne.class);
                    uploadImageInterface.onSuccess(uploadImageOne);

                } catch (Exception e) {
                    ToastUtil.tost("单图上传-数据格式错误！" + e.getMessage());
                }
            }
        });
    }
}
