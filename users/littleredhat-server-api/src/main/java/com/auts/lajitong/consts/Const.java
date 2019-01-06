package com.auts.lajitong.consts;

public interface Const {

    interface ThirdPart {
        String PHICOMM_ACCOUNT_URL = "https://accountsym.phicomm.com/v1/accountDetail";
    }

    String DEFAULT_CHARSET = "utf-8";

    String TOKEN_PREFIX = "Bearer";

    /**
     * header头部.
     */
    String AUTHORIZATION = "Authorization";

    //产品类参数
    String RECOMMEND_TYPE = "recommendType";
    String PAGE_NO = "pageNo";
    String PAGE_SIZE = "pageSize";
    String TYPE = "type";
    String P_CODE= "pCode";


    String X_APP_INFO = "x-app-info";

    int STATUS_OK = 200;
    int STATUS_ERROR = 1;

    // 错误码定义， 10000以上定义为错误码
    // 请求参数类错误
    interface ErrorCode {

        int COMMON_ERROR = 10001; //请求失败
        int REQUEST_NO_PARAS = 10002; //请求中参数不足
        int ERROR_NO_TOKEN = 10003;// 请求中缺少Token
        int ERROR_TOKEN_INVILD = 10004;//token失效
        int ERROR_USER_NO_DETAIL = 10007;// 用户还未设置账号详情
        int ERROR_DATABASE_FATAL = 10008;//操作数据库失败
        int REQUEST_PARA_ERROR = 10009;// 请求参数不合法
        int ERROR_NO_UID = 10010;// 请求中缺少uid
        int ERROR_JSON = 10011;//获取json数据出错
        int ERROR_NO_DEVICEID = 10012;// 请求中缺少deviceId
        int ERROR_NO_DEVICE_FOUND = 10013;// 数据库中未查找到对应的设备信息
        int ERROR_SMS_SEND_TOO_OFFEN = 10014;// 注册短信发送太过于频繁
        int ERROR_SMS_CAPTCHA = 10015;// 验证码错误
        int STAUS_WITH_DRAW_NO_BALANCE = 10016;//提现余额不足
        int STAUS_PROCESSING_WITHDRAW = 10017;//正在提现中
        int STAUS_UID_NOT_BIND_OPEN_ID = 10018;//还没有绑定微信OPENID
        int STAUS_MORE_THAN_1_PROCESSING_WITHDRAW_RECORD = 10019;//提现出现问题，请联系客服
        int STAUS_ORDER_FAILED = 10020;//提现失败
        
        int STATUS_WITHDRAW_FAILED_NO_BANKNAME = 10021; //提现缺少银行卡名称
        int STATUS_WITHDRAW_FAILED_NO_BANKNO = 10022; //提现缺少银行卡号码
        int STATUS_WITHDRAW_FAILED_NO_USERNAME = 10023;//提现缺少用户名称
        int STATUS_WITHDRAW_FAILED_NO_AMOUNT = 10024;// 请输入提现金额
        int STATUS_WITHDRAW_FAILED_WRONG_AMOUNT = 10025;// 请输入提现金额
        int STATUS_WITHDRAW_FAILED_NO_BALANCE = 10026;// 请输入提现金额
        int STATUS_WITHDRAW_FAILED_NO_BANK = 10027;// 请输入提现金额

        interface Account {
            int OK = 0;
            //注册类错误码
            int REGIST_VERCODE_ERROR = 1; //验证码错误
            int REGIST_VERCODE_OVERDUE = 2; //验证码过期
            int REGIST_GRANTCODE_ERROR = 11; //授权码错误
            int REGIST_ACCOUNT_EXISTS = 14;//账户已经存在，可以直接登录
            int REGIST_VERCODE_USED = 23;//验证码已使用
            int REGIST_PHONE_ERROR = 34; //手机号格式错误
            int REGIST_ERROR = 50; //系统错误

            //登录类错误码
            int LOGIN_USER_NOT_EXIST = 7;
            int LOGIN_PASSWORD_ERROR = 8;
            int LOGIN_GRANTCODE_ERROR = 11;
            int LOGIN_PARA_ERROR = 12;
            int LOGIN_PASSWORD_NOT_SET = 15;
            int LOGIN_KICK_MULTI_LOGIN = 30;
            int LOGIN_PHONE_ERROR = 34;
            int LOGIN_ERROR = 50;

            //token
            int TOKEN_OK = 0;
            int TOKEN_NEED_REFRESH = 1;
            int TOKEN_INVILID = 2;
            String STR_TOKEN_INVILID = "TOKEN INVILID";
        }
    }

    interface SexType{
        int SEX_UNKNOWN = 0;
        int SEX_MAIL = 1; //男
        String SEX_MAIL_STR = "1"; //男
        int SEX_FEMAIL = 2; //女
        String SEX_FEMAIL_STR = "2"; //女
    }
    
    public interface DistributedLockParams {
        //锁的超时时间，单位毫秒
        int EXPIRED_TIME = 60000;
        //提现分布式锁名称前缀
        String ROOT_PATH = "/WITHDRAW_DISTRIBUTED_LOCK";

        //设置打赏参数分布式锁名称前缀
        String SETTING_PARAMS_ROOT_PATH = "/SET_PARA_DISTRIBUTED_LOCK";

        //提现操作类型，原单号操作类型
        int WITHDRAW_TYPE_ORI = 0;
        //提现操作类型，新单号操作类型
        int WITHDRAW_TYPE_NEW = 1;
    }
}
