package com.hjy.baserequest.request;


/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class API {
    //统一协议
    public static final String SYSTEM_URL_DEFAULT = "http://live.5eyx.com";//app统一协议
    public static final String USER_URL_DEFAULT = "http://user.5eyx.com";//快速登录&账号登录&忘记密码&实名认证&登录验证 -- 统一域名
    public static final String SMS_URL_DEFAULT = "http://sms.5eyx.com";//发送短信 -- 统一域名
    public static final String PAY_URL_DEFAULT = "http://sdk.pay.android.5eyx.com";//支付 -- 统一域名
    public static final String LIVE_URL_DEFAULT = "http://live.5eyx.com";//直播 -- 统一域名

    public static final String CDN = "http://cdn.xxxx.co";//CDN统一地址
    public static final String IMAGEURL_DEFAULT = CDN + "/img";//CDN图片地址
    public static final String FILE_DEFAULT = CDN + "/file";//CDN文件地址

    public static String KEY = "xxxxxxxx";//加密


    //系统
    public static final String appUpdates = SYSTEM_URL_DEFAULT + "/xxx/xxxxx.do";//检查更新
    public static final String notice = SYSTEM_URL_DEFAULT + "/xxx/xxx/xxxxx";//获取公告
    public static final String messagePush = SYSTEM_URL_DEFAULT + "/xxx/xxx/xxxxx";//消息推送
    public static final String deviceInformation = SYSTEM_URL_DEFAULT + "/xxx/xxx.do";//搜集设备信息
    public static final String checkFile = SYSTEM_URL_DEFAULT + "/upload/xxx/checkFile";//检查服务器是否有该图片
    public static final String uploadImg = SYSTEM_URL_DEFAULT + "/upload/xxx/upload_dos";//传图接口


    //用户
    public static final String smsVerificationCode = SMS_URL_DEFAULT + "/sms/phone_send_info/sendSms";//获取短信验证码
    public static final String visitorLogin = USER_URL_DEFAULT + "/user/user/visitorLogin";//游客登录
    public static final String accountPasswordLogin = USER_URL_DEFAULT + "/user/user/accountLogin";//账号密码登录
    public static final String phoneLogin = USER_URL_DEFAULT + "/user/user/appQuickLogin";//手机号登录
    public static final String phoneVerification = USER_URL_DEFAULT + "/user/user/forgetPasswordFirst";//手机验证
    public static final String resetPassword = USER_URL_DEFAULT + "/forgetPwdSecond";//重置密码

    public static final String bindMobileNumber = USER_URL_DEFAULT + "/xxx/xxx.do";//绑定手机号

    //直播相关
    public static final String findBanner = LIVE_URL_DEFAULT + "/home/adpic/adpicList";//发现-banner
    public static final String anchorList = LIVE_URL_DEFAULT + "/live/anchor/anchorList";//发现-（客服直播、游戏直播）
    public static final String videoList = LIVE_URL_DEFAULT + "/home/video/videoList";//发现-视频列表
    public static final String newsList = LIVE_URL_DEFAULT + "/home/news/newsList";//发现-资讯列表
}
