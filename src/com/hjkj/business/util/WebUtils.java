/**
 * @(#) WebUtils.java Created on 2010-5-17
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.business.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.aspire.util.GenApi;
import com.hjkj.business.common.WebConstants;

/**
 * The class <code>WebUtils</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public final class WebUtils {

    /**
     * Base64 Encoder
     */
    private final static BASE64Encoder base64encoder = new BASE64Encoder();

    /**
     * Base64 Decoder
     */
    private final static BASE64Decoder base64decoder = new BASE64Decoder();

    /**
     * 登录password是否�??��随机密钥�??
     */
    private final static boolean bUseRandomLoginKey = true;

    /**
     * 加解密密�??
     */
    private final static byte[] aesKeyBytes = {
        (byte)0xbb, (byte)0xbb, (byte)0xbb, (byte)0xbb,
        (byte)0xbb, (byte)0xbb, (byte)0xbb, (byte)0xbb,
        (byte)0xbb, (byte)0xbb, (byte)0xbb, (byte)0xbb,
        (byte)0xbb, (byte)0xbb, (byte)0xbb, (byte)0xbb
    };

    /**
     * AES加解密key
     */
    private final static SecretKeySpec aesKeySpec = new SecretKeySpec(
            aesKeyBytes, "AES");

    /**
     * 产生验证�??
     * @param minNum 验证码最小长�??
     * @param maxNum 验证码最大长�??
     * @return 返回验证�??
     */
    public final static String generateYzm(int minNum, int maxNum) {
        Random oRand = new Random();
        int nRandNum = minNum + oRand.nextInt(maxNum - minNum);

        String nums = "2345678abdefhjnqrtyABDEFGHJLNQRTY";
        StringBuffer sResult = new StringBuffer();
        Random ran = new Random();

        for (int i = 0; i < nRandNum; i++) {
            int iRan = ran.nextInt(nums.length());
            sResult.append(nums.charAt(iRan));
        }

        return sResult.toString();
    }

    /**
     * 以UTF-8编码方式，将HTTP响应消息发�?到浏览器(�??��用于ajax的远程调用的响应消息)
     * @param request HTTP请求
     * @param response HTTP响应
     * @param sResp 响应消息
     * @throws IOException 异常
     */
    public static void sendHttpResponse(HttpServletRequest request,
            HttpServletResponse response, String sResp) throws IOException {
    	response.setContentType("application/json");
        response.setCharacterEncoding(WebConstants.PAGE_ENCODING);
        PrintWriter wr = response.getWriter();
        wr.append(sResp);
        wr.flush();
    }

    /**
     * 验证Email的格式是否正�??
     * @param sEmail Email地址
     * @return 若格式正确，返回true，否则，返回false
     */
    public static boolean validateEmail(String sEmail) {
        Pattern p = Pattern.compile("\\w+@(\\w+\\.)+[a-z]{2,3}");
        Matcher m = p.matcher(sEmail);
        return m.matches();
    }

    /**
     * 将对象转换成字符�??
     * @param o 对象
     * @return 返回转换的字符串，若对象为null，则返回空字符串
     */
    public static String object2String(Object o) {
        return o == null ? "" : o.toString();
    }

//    /**
//     * 产生AES加密/解密密钥
//     * @param key 加密/解密密码
//     * @return 返回AES加密/解密密钥
//     * @throws Exception
//     */
//    public static SecretKeySpec generateAesKey(String key) throws Exception {
//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//        kgen.init(128, new SecureRandom(key.getBytes(
//                WebConstants.PAGE_ENCODING)));
//        SecretKey secretKey = kgen.generateKey();
//        byte[] enCodeFormat = secretKey.getEncoded();
//        return new SecretKeySpec(enCodeFormat, "AES");
//    }

    /**
     * 采用AES算法对内容进行加�??
     * @param key 加密密码
     * @param content 要加密的内容
     * @return 返回加密信息
     */
    public static byte[] aesEncrypt(String key,
            String content) throws Exception {
        // 创建密码器并初始�??
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec);
        
        // 加密
        return cipher.doFinal(base64encoder.encode(content.getBytes(
                WebConstants.PAGE_ENCODING)).getBytes());
    }

    /**
     * 采用AES算法对内容进行解�??
     * @param key 解密密码
     * @param content 要解密的内容
     * @return 返回解密信息
     */
    public static byte[] aesDecrypt(String key, byte[] content) throws Exception {
        // 创建密码器并初始�??
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKeySpec);
       
        // 解密
        return base64decoder.decodeBuffer(new String(cipher.doFinal(content),
                WebConstants.PAGE_ENCODING));
    }

    /**
     * sha1加密
     * @param content 要加密的内容
     * @return 返回加密后的字符�??
     */
    public static String sha1(String content) throws Exception {
        MessageDigest oMD = MessageDigest.getInstance("sha1");
        oMD.update(content.getBytes());
        return GenApi.bytesToHexStr(oMD.digest());
    }

    /**
     * 产生随机加密�??
     * @param content 要加密的内容
     * @return 返回随机加密�??
     */
    public static String generateChallenge(String content) {
        if (bUseRandomLoginKey) {
            try {
                return sha1(content);
            } catch (Exception ex) {
                return GenApi.stringRandom(32);
            }
        } else {
            return "00000000000000000000000000000000";
        }
    }

    /**
     * 取出cookie�??
     * @param request HTTP请求
     * @param name cookie的名�??
     * @return 返回cookie的�?
     */
    public static String getCookie(HttpServletRequest request,
            String name) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
        	for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return URLDecoder.decode(cookie.getValue(),
                            WebConstants.PAGE_ENCODING);
                }
            }
        }        
        return null;
    }

    /**
     * 保存cookie
     * @param response HTTP响应
     * @param name cookie名称
     * @param value cookie�??
     * @param expiry Cookie的超时时间，以秒为单位，expiry=0可以删除cookie
     * @param cookiePath Cookie的路�??
     */
    public static void saveCookie(HttpServletResponse response, String name,
            String value, int expiry, String cookiePath) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(name, URLEncoder.encode(value,
                WebConstants.PAGE_ENCODING));
        cookie.setPath(cookiePath);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }
    
    /**
     * 判断字符串是否为�??
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
    	return value == null || value.isEmpty();
    }
    
    /**
     * 转换成int，如果格式错误，返回defaultValue
     * @param value
     * @param defaultValue
     * @return
     */
    public static int toInt(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
    }

    public static void main(String[] args) throws Exception {

//        String a = "1234";
//        MessageDigest oMD = MessageDigest.getInstance("sha1");
//        oMD.update(a.getBytes());
//        byte[] by = oMD.digest();
//        System.out.println(by.length);
//        System.out.println(GenApi.bytesToHexStr(by));
//        
//        String userName = "徐勇2徐勇2徐勇2";
//        String userPwd = "1234567及非师范fff徐勇2徐勇2徐勇2";
//        int aa = (int)(userPwd.getBytes(WebConstants.PAGE_ENCODING).length/16.0 + 0.5);
//        System.out.println(aa);
//        System.out.println(aa*32);
//        byte[] byPwd = aesEncrypt(userName, userPwd);
//        System.out.println(GenApi.bytesToHexStr(byPwd));
//        System.out.println(GenApi.bytesToHexStr(byPwd).length());
//        //System.out.println(new String(byPwd));
//        byPwd = aesDecrypt(userName, byPwd);
//        System.out.println(new String(byPwd, WebConstants.PAGE_ENCODING));
    	String  aa = sha1(sha1("123456") + "00000000000000000000000000000000");
    	 System.out.println("aa==="+aa);
    }
    
    /**
     * 以UTF-8编码方式，将HTTP响应消息发�?�到浏览�?(�?般用于ajax的远程调用的响应消息)
     * @param request HTTP请求
     * @param response HTTP响应
     * @param sResp 响应消息
     * @throws IOException 异常
     */
    public static void sendHtmlResponse(HttpServletRequest request,
            HttpServletResponse response, String sResp) throws IOException {
    	response.setContentType("text/html");
        response.setCharacterEncoding(WebConstants.PAGE_ENCODING);
        PrintWriter wr = response.getWriter();
        wr.append(sResp);
        wr.flush();
    }
    
    /**
     * 以UTF-8编码方式，将HTTP响应消息发�?到浏览器(�??��用于ajax的远程调用的响应消息)
     * @param request HTTP请求
     * @param response HTTP响应
     * @param sResp 响应消息
     * @throws IOException 异常
     */
    public static void sendHttpJsonResponse(HttpServletRequest request,
            HttpServletResponse response, String code, String reason) throws IOException {
    	//response.setContentType("application/json");
    	HashMap<String, String> rtnMap = new HashMap<String, String>();
		rtnMap.put("s", code);
		rtnMap.put("r", reason);
		JSONObject jsonobj = JSONObject.fromObject(rtnMap);
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter wr = response.getWriter();
        wr.print(jsonobj);
        //wr.flush();
    }
}
