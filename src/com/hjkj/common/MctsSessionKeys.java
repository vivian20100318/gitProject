package com.hjkj.common;

public class MctsSessionKeys {

    // 登录用户
    public static final String LOGIN_USER = "__login_user__";
 
    // 加密随机
    public static final String PWD_CHALLENGE = "pwd_challenge";

    // 成功信息(success.jsp)
    public static final String SUCCESS_DLG_TITLE = "success_dlg_title";
    public static final String SUCCESS_INFO_TITLE = "success_info_title";
    public static final String SUCCESS_INFO_CONTENT = "success_info_content";

    // 错误信息(error.jsp)
    public static final String ERR_DLG_TITLE = "err_dlg_title";
    public static final String ERR_INFO_TITLE = "err_info_title";
    public static final String ERR_INFO_CONTENT = "err_info_content";
    public static final String ERR_RETURN_URL = "err_return_url";

    // 错误信息(登录)
    public static final String ERR_LOGIN_INFO = "err_login_info";

    // Cookie的名
    public static final String COOKIE_LOGIN_REM_ME = "login_rem_me";
    public static final String COOKIE_LOGIN_NAME = "login_name";
    
    //重复提交的校验串
    public static final String SUBMIT_TOKEN = "submitToken"; //下次提交可用的令
    public static final String SUBMITED_TOKEN = "submitedToken"; //上次提交的令
    
    // Session中登录用户ID
    public static final String SESSION_LOGIN_USER = "session_login_user";
    public static final String SESSION_LOGIN_USER_ACTION = "session_login_user_action";
}
