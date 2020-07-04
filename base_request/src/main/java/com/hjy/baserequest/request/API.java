package com.hjy.baserequest.request;


/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class API {
    //统一协议
    /**
     * app统一协议
     */
    public static final String SYSTEM_URL_DEFAULT = "http://live.5eyx.com";
    /**
     * 快速登录&账号登录&忘记密码&实名认证&登录验证 -- 统一域名
     */
    public static final String USER_URL_DEFAULT = "http://user.5eyx.com";
    /**
     * 发送短信 -- 统一域名
     */
    public static final String SMS_URL_DEFAULT = "http://sms.5eyx.com";
    /**
     * 支付 -- 统一域名
     */
    public static final String PAY_URL_DEFAULT = "http://sdk.pay.android.5eyx.com";
    /**
     * 直播 -- 统一域名
     */
    public static final String LIVE_URL_DEFAULT = "http://live.5eyx.com";
    /**
     * 游戏 -- 统一域名
     */
    public static final String GAME_URL_DEFAULT = "http://game.5eyx.com";
    /**
     * 家族 -- 统一域名
     */
    public static final String FAMILY_URL_DEFAULT = "http://family.5eyx.com";

    public static final String CDN = "http://cdn.xxxx.co";//CDN统一地址
    public static final String IMAGEURL_DEFAULT = CDN + "/img";//CDN图片地址
    public static final String FILE_DEFAULT = CDN + "/file";//CDN文件地址

    public static String KEY = "xxxxxxxx";//加密


    //系统
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
    /**
     * 个人信息
     */
    public static final String userinfo = USER_URL_DEFAULT + "/user/user/userInfo";
    /**
     *  家族信息
     */
    public static final String familyInfo = USER_URL_DEFAULT + "/user/user/myFamilys";
    /**
     * 资产数量，金豆银豆鲜花
     */
    public static final String propertyNumber = USER_URL_DEFAULT + "/user/user/myAccount";
    /**
     * 检查更新
     */
    public static final String checkUpdates = USER_URL_DEFAULT + "/user/system/checkForUpdates";

    public static final String bindMobileNumber = USER_URL_DEFAULT + "/xxx/xxx.do";//绑定手机号

    //直播相关
    public static final String findBanner = LIVE_URL_DEFAULT + "/home/adpic/adpicList";//发现-banner
    public static final String anchorAndVideoList = LIVE_URL_DEFAULT + "/live/index/anchorAndVideoList";//发现-（客服/游戏主播& 视频）
    public static final String anchorList = LIVE_URL_DEFAULT + "/live/anchor/anchorList";//发现-（客服直播、游戏直播）
    public static final String videoList = LIVE_URL_DEFAULT + "/home/video/videoList";//视频列表
    public static final String videoDetail = LIVE_URL_DEFAULT + "/home/video/videoDetail";//视频详情
    public static final String videoAddLike = LIVE_URL_DEFAULT + "/home/video/videoAddLike";//视频点赞
    public static final String newsList = LIVE_URL_DEFAULT + "/home/news/newsList";//发现-资讯列表
    public static final String newsDetail = LIVE_URL_DEFAULT + "/home/news/newsDetail";//资讯详情
    public static final String liveList = LIVE_URL_DEFAULT + "/live/live/liveList";//直播列表
    public static final String search = LIVE_URL_DEFAULT + "/live/index/search";//搜索

    /**
     * 礼包数量
     */
    public static final String giftNumber = GAME_URL_DEFAULT +"/game/game_gift_card/myGiftCardCount";
    /**
     * 我的游戏
     */
    public static final String myGame = GAME_URL_DEFAULT +"/game/game/myGame";
    /**
     * 游戏列表
     */
    public static final String gameList = GAME_URL_DEFAULT +"/game/game/gameList";
    /**
     * 区服列表
     */
    public static final String serviceList = GAME_URL_DEFAULT +"/game/game_server/serverList";
    /**
     * 角色列表
     */
    public static final String roleList = GAME_URL_DEFAULT +"/game/game_user_role/roleSelect";



    //家族相关
    /**
     * 绑定角色信息
     */
    public static final String currentUserRole = FAMILY_URL_DEFAULT +"/family/family_user_role/currentUserRole";
    /**
     * 绑定角色
     */
    public static final String toCurrentRole = FAMILY_URL_DEFAULT +"/family/family_user_role/bindUserRole";
    /**
     * 绑定角色列表
     */
    public static final String bindRoleList = FAMILY_URL_DEFAULT +"/family/family_user_role/userRoleList";
    /**
     * 切换绑定角色
     */
    public static final String switchRole = FAMILY_URL_DEFAULT +"/family/family_user_role/toggleUserRole";
}
