package com.hjy.baserequest.request;


import com.hjy.baserequest.RequestManage;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class API {
    //统一协议
    public static final String URL_DEFAULT = "http://login.5eyx.com";//app统一协议
    public static final String URL_DEFAULT_TEST = "http://login.5eyx.com";//app统一协议 测试
    public static final String URL_DEFAULT_SDK = "http://xxx.xxx.xxx:0000";//sdk统一协议
    public static final String URL_DEFAULT_SDK_TEST = "http://xxx.xxx.xxx:0000";//sdk统一协议 测试

    public static final String CDN = "http://cdn.xxxx.co";//CDN统一地址
    public static final String IMAGEURL_DEFAULT = CDN + "/img";//CDN图片地址
    public static final String FILE_DEFAULT = CDN + "/file";//CDN文件地址

    public static String KEY = "xxxxxxxx";//加密


    //系统
    public static final String appUpdates = "/xxx/xxxxx.do";//检查更新
    public static final String notice = "/xxx/xxx/xxxxx";//获取公告
    public static final String messagePush = "/xxx/xxx/xxxxx";//消息推送
    public static final String deviceInformation = "/xxx/xxx.do";//搜集设备信息
    public static final String checkFile = "/upload/xxx/checkFile";//检查服务器是否有该图片
    public static final String uploadImg = "/upload/xxx/upload_dos";//传图接口


    //用户
    public static final String userInformation = "/xxx/xxx.do";//获取用户信息
    public static final String smsVerificationCode = "/common/common/sendSms";//获取短信验证码
    public static final String accountPasswordLogin = "/login";//账号密码登录
    public static final String phoneLogin = "/login/index/phoneLogin";//手机号登录
    public static final String phoneVerification = "/forgetPwdFirst";//手机验证
    public static final String resetPassword = "/forgetPwdSecond";//重置密码

    public static final String bindMobileNumber = "/xxx/xxx.do";//绑定手机号


    //其他

    public static String getUrl(String urlLast) {
        return (RequestManage.isDEBUG() ? URL_DEFAULT_TEST : URL_DEFAULT) + urlLast;
    }

    public static String getSDKUrl(String urlLast) {
        return (RequestManage.isDEBUG() ? URL_DEFAULT_SDK_TEST : URL_DEFAULT_SDK) + urlLast;
    }
}
