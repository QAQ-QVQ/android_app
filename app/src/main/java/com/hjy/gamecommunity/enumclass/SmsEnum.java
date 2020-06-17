package com.hjy.gamecommunity.enumclass;

import com.hjy.baseutil.enumclass.Enum;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 10:48
 * 描述: 短信验证码类型 Enum
 * 短信业务:app_quickLogin 快速登录，app_bindPhone 绑定手机号，app_forgetPassword  忘记密码，app_editpassword 修改密码
 */
public class SmsEnum extends Enum<String, String> {
    private static SmsEnum mInstance;
    public final static String VALUE1 = "快速登录";
    public final static String VALUE2 = "绑定手机号";
    public final static String VALUE3 = "忘记密码";
    public final static String VALUE4 = "修改密码";


    public static SmsEnum i() {
        if (mInstance == null) {
            synchronized (SmsEnum.class) {
                if (mInstance == null) {
                    mInstance = new SmsEnum();
                }
            }
        }
        return mInstance;
    }

    @Override
    protected Map<String, String> addEnum() {
        Map<String, String> kvMap = new LinkedHashMap<>();
        kvMap.put("app_quickLogin", VALUE1);
        kvMap.put("app_bindPhone", VALUE2);
        kvMap.put("app_forgetPassword", VALUE3);
        kvMap.put("app_editpassword", VALUE4);
        return kvMap;
    }


}
