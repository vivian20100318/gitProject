/**
 * @(#) WebConstants.java Created on 2010-5-12
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.business.common;

import com.aspire.boc.util.ResourceManager;
import com.hjkj.web.servlet.WebInitializer;

/**
 * <code>WebConstants</code>用来定义网页常用变量
 * 
 * @author xuyong
 * @version 1.0
 */
public class WebConstants {
    // 路径常量定义
    public static final String WEB_ROOT = WebInitializer.getContextPath();
    public static final String IMG_ROOT = WEB_ROOT + "/image";
    public static final String JSP_ROOT = WEB_ROOT + "/jsp";
    public static final String JS_ROOT = WEB_ROOT + "/js";
    public static final String THEME_FOLDER = "theme";
    public static final String THEME_ROOT = WEB_ROOT + "/" + THEME_FOLDER;
    public static final String SPACE_IMG = IMG_ROOT + "/space.gif";
    public static final String CLIENT_VERSION_ROOT="/upgrade";
    public static final String pc_view="http://112.74.77.72:8081/excellSource_pic/";
    // 页面编码方式
    public static final String PAGE_ENCODING = "UTF-8";


    // Constants for ajax function name
    public static final String RPC_JSON_RESP_VAR = "oJsonResp";
    public static final String RPC_RESP_STATUS_VAR = "s";
    public static final String RPC_RESP_REASON_VAR = "r";
    public static final String RPC_RESP_STATUS_SUCCESS = "0";
    public static final String RPC_RESP_STATUS_ERR = "1";

    // �?��密码输入错误次数
    public static final int PWD_ERR_MAX_TIMES = 3;
    
    //OSID集常�?
    public static final String OSID_SET_OMS = "3";
    public static final String OSID_SET_ANDROID = "9";
    public static final String OSID_SET_WM = "2";
    public static final String OSID_SET_SYMBIAN = "1";
    
    //分页器显示的页面个数
    public static final int PAGINATOR_PAGE_COUNT = 8;
    
   
    

    //Paginator 在request 中的名称
    public static final String VIEW_ATTR_NAME_PAGINATOR = "paginator";
    
    public static final String RESPONSE_FAIL = "fail";
    
    //空串
    public static final String EMPTY = "";    
    
    //统计用颜�?
    public static final String STAT_COLORS[] = new String[]{
        "#ff0000", "#00aa00", "#0000ff", "#ff9900", "#ff00ff", "#00ff00", 
        "#ffff00", "#00ffff", "#aa0000", "#0000aa", "#0099ff", "#9900ff"
    };
    
    public static final String JS_RANDOM = "\"nnn=\" + new Date().getTime()";
    
    //定义图片地址
    public static String getImgRootPath1() {
    	ResourceManager rm = ResourceManager.getInstance();
    	String serverSitePath = rm.getValue("server_site_path");
    	return serverSitePath;
    }
    public static String getImgRootPath() {
    	ResourceManager rm = ResourceManager.getInstance();
    	String serverSitePath = rm.getValue("server_site_path");
    	return serverSitePath;
    }
}
