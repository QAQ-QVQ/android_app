package com.hjy.baserequest.request;

public interface UploadImageInterface<T> {
    void onError();

    void onSuccess(T t);

    void onFinish();

    //上传进度
    void schedule(float progress);

    void onSuccess(String imagUrl, String imagMd5);
}
