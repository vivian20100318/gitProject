/**
 * @(#) MctsUtils.java Created on 2010-5-19
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.business.util;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.aspire.util.GenApi;

/**
 * The class <code>MctsUtils</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public class MctsUtils {

//	/**
//	 * 将成功信息保存在session�?
//	 * @param session HTTP session对象
//	 * @param dlgTitle 成功信息框的标题
//	 * @param infoTitle 成功信息的标�?
//	 * @param info 成功信息内容
//	 */
//	public static void saveSuccessInfo(HttpSession session, String dlgTitle,
//			String infoTitle, String info) {
//		session.setAttribute(MctsSessionKeys.SUCCESS_DLG_TITLE, dlgTitle);
//		session.setAttribute(MctsSessionKeys.SUCCESS_INFO_TITLE, infoTitle);
//		session.setAttribute(MctsSessionKeys.SUCCESS_INFO_CONTENT, info);
//	}
//
//	/**
//	 * 将错误信息保存在session�?
//	 * @param session HTTP session对象
//	 * @param dlgTitle 错误信息框的标题
//	 * @param infoTitle 错误信息的标�?
//	 * @param info 错误信息内容
//	 */
//	public static void saveErrorInfo(HttpSession session, String dlgTitle,
//			String infoTitle, String info) {
//		session.setAttribute(MctsSessionKeys.ERR_DLG_TITLE, dlgTitle);
//		session.setAttribute(MctsSessionKeys.ERR_INFO_TITLE, infoTitle);
//		session.setAttribute(MctsSessionKeys.ERR_INFO_CONTENT, info);
//	}

	/**
	 * 产生保持到数据库中的密码
	 * @param userName 用户�?
	 * @param userPwd 用户原密�?
	 * @return 返回保持到数据库中的密码
	 * @throws Exception
	 */
	public static String encodePassword(String userName, String userPwd)
			throws Exception {
		return GenApi.bytesToHexStr(WebUtils.aesEncrypt(userName, userPwd));
	}

	/**
	 * 主函�?
	 * @param args
	 */
/*	public static void main(String[] args) {
		
		try {
			//System.out.println(MctsUtils.encodePassword("system", "111111"));
			String age = getAgeByBirthDay("1982-11-05");
			String nowStr = "2013-11-11 00:00:03";
			String minStr = "2013-11-11 00:00:00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowdate = sdf.parse(nowStr);
			Date mindate = sdf.parse(minStr);
			String result = "";
			if(nowdate.getTime()-mindate.getTime()<Integer.parseInt("2")*1000){
				result = minStr;
			}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(nowdate);
			cal.add(Calendar.SECOND, -Integer.parseInt("2"));
			result = sdf.format(cal.getTime());
			}
			System.out.println("1111===" + result);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		System.out.println(getNowStrBefore("yyyy-MM-dd", 1));
	}
	
	private static final Random random = new Random();

	public static String getURLRandom() {
		return "nnn=" + random.nextInt();
	}

	/**
	 * 将日期从yyyy-MM-dd 转换成yyyyMMdd
	 * @throws ParseException 
	 */
	public static String getFileFromDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String fileName = sdf1.format(date);
		return fileName;
	}
	
	/**
	 * 将日期从yyyy-MM-dd 转换成yyyyMMdd
	 * @throws ParseException 
	 */
	public static String getAgeByBirthDay(String birthDay) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beiginDate = sdf.parse(birthDay);
		Date nowDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = sdf.parse(sdf1.format(nowDate));
		long age=(endDate.getTime()-beiginDate.getTime())/(1000*60*60*24); 
		
		return String.valueOf(age/365);
	}

	/**
	 * 将日期从yyyyMMdd 转换成yyyy-MM-dd
	 * @throws ParseException 
	 */
	public static String getSearchFromDate(String dateStr)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(dateStr);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = sdf1.format(date);
		return fileName;
	}

	public static String getNoticeFromDate(String dateStr)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(dateStr);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
		String fileName = sdf1.format(date);
		return fileName;
	}

	public static String getDateInfo(String dateStr) throws ParseException {
		//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		//		Date date = sdf.parse(dateStr);
		//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = dateStr.substring(0, 4) + "-"
				+ dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8);
		return fileName;
	}

	public static String getVisitDateFromDate(String dateStr)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = sdf.parse(dateStr);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fileName = sdf1.format(date);
		return fileName;
	}

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;

	}

	public static int getIamgeWidth(String fileName) throws IOException {
		int width = 0;

		Image image;
		image = ImageIO.read(new File(fileName));
		width = image.getWidth(null);

		return width;
	}

	public static int getIamgeHeight(String fileName) throws IOException {
		int height = 0;
		Image image;
		image = ImageIO.read(new File(fileName));
		height = image.getHeight(null);
		return height;
	}

	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	public static String replaceAllTs(String strSource) {
		String rtn = "";
		rtn = strSource;
		rtn = replace(rtn, "&", "&amp;");
		rtn = replace(rtn, "\"", "&quot;");
		rtn = replace(rtn, "'", "&apos;");
		rtn = replace(rtn, "<", "&lt;");
		rtn = replace(rtn, ">", "&gt;");
		return rtn;
	}

	public static String getNowStr() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String noStr = sf.format(new Date());
		return noStr;
	}

	public static String getNowStr(String fmtStr) {
		SimpleDateFormat sf = new SimpleDateFormat(fmtStr);
		String noStr = sf.format(new Date());
		return noStr;
	}

	public static String getNowStrBefore(String fmtStr,int beforeStr) {
		SimpleDateFormat sf = new SimpleDateFormat(fmtStr);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, beforeStr);
		String noStr = sf.format(c.getTime());
		return noStr;
	}
	
	public static String splitString(String str, int len, String elide) {
		if (str == null) {
			return "";
		}
		byte[] strByte = str.getBytes();
		int strLen = strByte.length;
		if (len >= strLen || len < 1) {
			return str;
		}
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			len = (len == 1) ? len + 1 : len - 1;
		}
		return new String(strByte, 0, len) + elide.trim();
	}

	/**
	 * 将日期从yyyyMMdd 转换成yyyy-MM-dd
	 * @throws ParseException 
	 */
	public static String getVersionDateString(String dateStr)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf1.format(date);
		return fileName;
	}
	
	/**
	 * * 根据名字获取cookie *
	 * 
	 * @param request
	 *            *
	 * @param name
	 *            cookie名字 *
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * * 将cookie封装到Map里面 *
	 * 
	 * @param request
	 *            *
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	/**
	 * 将Blob字段转换成string
	 * 
	 * @param b
	 * @return
	 */
	public static String ConvertBLOBtoString(byte[] b) {
		try {
			ByteArrayInputStream msgContent = new ByteArrayInputStream(b);
			byte[] byte_data = new byte[msgContent.available()];
			msgContent.read(byte_data, 0, byte_data.length);
			return new String(byte_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 将日期从yyyy-MM-dd HH:mm:ss 转换成yyyy-MM-dd
	 * @throws ParseException 
	 */
	public static String getShowDateString(String dateStr,String fromFmt, String showFmt)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(fromFmt);
		Date date = sdf.parse(dateStr);
		SimpleDateFormat sdf1 = new SimpleDateFormat(showFmt);
		String fileName = sdf1.format(date);
		return fileName;
	}
}
