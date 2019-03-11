package com.hjkj.business.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/******************************************************************************
 * @function�?
 * @author zhouych
 * @file_name ExcellCourController.java
 * @package_name：com.liumai.business.excelltcourse.controller
 * @project_name：liumai_manage
 * @department�?2015-4-14
 */
public class StringUtil {
	public static final int DEFAULT_LENGTH = 28;

	public static String splitString(String str, int len, String elide) {
		if (str == null) {
			return "";
		}
		len = 600;
		byte[] strByte = str.getBytes();
		int strLen = strByte.length;
		// int elideLen = (elide.trim().length() == 0) ? 0 :
		// elide.getBytes().length;
		if (len >= strLen || len < 1) {
			return str;
		}
		/*
		 * if (len - elideLen > 0) { len = len - elideLen; }
		 */
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

	public static String object2String(Object o) {
		return o == null ? "" : o.toString();
	}

	/**
	 * 
	 * @方法名称:splitString
	 * @内容摘要: ＜按照规定分割符将字符串分割�?
	 * @param str
	 * @param splitFlag
	 *            分割标记
	 * @return String[]
	 * @exception
	 * @author:luweiwei
	 * @since 1.0.0
	 */
	public static String[] splitString(String str, String splitFlag) {
		String[] strs = null;
		if ((str != null || !"".equals(str))
				&& (splitFlag != null || !"".equals(splitFlag))) {
			strs = str.split(splitFlag);
		}
		return strs;
	}

	public static String splitString(String[] strs) {
		String str = "";
		if (strs != null && !"".equals(strs) && strs.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < strs.length; i++) {
				sb.append(strs[i].trim() + ",");
			}
			String newStr = sb.toString();
			str = newStr.substring(0, newStr.length() - 1);
		}
		return str;
	}

	/**
	 * 
	 * @方法名称:assembleString
	 * @内容摘要: ＜将传入的数组拼装成以[1],[2],[3]等组成的字符串＞
	 * @param str
	 * @return String
	 * @exception
	 * @author:luweiwei
	 * @since 1.0.0
	 */
	public static String assembleString(String[] str) {
		StringBuffer cityList = new StringBuffer();
		if ("".equals(str) || str == null) {

		} else {
			for (int i = 0; i < str.length; i++) {
				cityList.append("[" + str[i] + "]");
				if (i < str.length - 1) {
					cityList.append(",");
				}
			}
		}
		return cityList.toString();
	}

	/**
	 * 
	 * @方法名称:assembleString
	 * @内容摘要: ＜传�?1,2,3类似字符串转换成[1],[2],[3]类似�?
	 * @param cityIds
	 *            城市ids
	 * @return String
	 * @exception
	 * @author:luweiwei
	 * @since 1.0.0
	 */
	public static String assembleString(String cityIds) {
		// 举办地市
		String citys = "";
		if ("".equals(cityIds) || cityIds == null) {

		} else {
			String[] city = StringUtil.splitString(cityIds, ",");
			citys = StringUtil.assembleString(city);
		}
		return citys;
	}

	// 求两个字符串数组的并集，利用set的元素唯�?�?
	private static String[] union(Object[] arr1, Object[] arr2) {
		Set<String> set = new HashSet<String>();
		for (Object str : arr1) {
			set.add(str.toString());
		}
		for (Object str : arr2) {
			set.add(str.toString());
		}
		String[] result = {};
		return set.toArray(result);
	}

	// 求两个数组的交集
	private static List<String> intersect(Object[] arr1, Object[] arr2) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		for (Object str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str.toString(), Boolean.FALSE);
			}
		}
		for (Object str : arr2) {
			if (map.containsKey(str)) {
				map.put(str.toString(), Boolean.TRUE);
			}
		}

		for (Entry<String, Boolean> e : map.entrySet()) {
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}

		return list;
	}

	// 求两个数组的差集
	private static String[] minus(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] longerArr = arr1;
		String[] shorterArr = arr2;
		// 找出较长的数组来减较短的数组
		if (arr1.length > arr2.length) {
			longerArr = arr2;
			shorterArr = arr1;
		}
		for (String str : longerArr) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : shorterArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			} else {
				if (!history.contains(str)) {
					list.add(str);
				}
			}
		}

		String[] result = {};
		return list.toArray(result);
	}
	/**
	 * 
	 * @方法名称:minusPlus
	 * @内容摘要: ＜数组差�?,第一个参数做为被减数�?
	 * @param arr1
	 * @param arr2
	 * @return 
	 * String[]
	 * @exception 
	 * @author:luweiwei
	 * @since  1.0.0
	 */
	public static String[] minusPlus(String[] arr1, String[] arr2){
		List<String> list=intersect(arr1,arr2);
		String[] strs=new String[list.size()];
		for(int i=0;i<list.size();i++){
			strs[i]=list.get(i);
		}
		return minus(strs,arr1);
	}
	public static void main(String[] args) {
		// //测试union
		String[] arr1 = { "1", "2", "3" };
		String[] arr2 = { "1", "4","5"};
		System.out.println("=======================");
		String[] ss2=minusPlus(arr1,arr2);
		System.out.println("结果3�?");
		for (String str : ss2) {
			System.out.println(str);
		}
		System.out.println("=======================");
		String[] ss3=minusPlus(arr2,arr1);
		System.out.println("结果4�?");
		for (String str : ss3) {
			System.out.println(str);
		}
	}
	
	public static List<String> sortString(List<String> list){
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA); 
		final int size = list.size();
		String[] arr = (String[])list.toArray(new String[size]);
		Arrays.sort(arr, cmp);  
		list = Arrays.asList(arr);
		return list;
	}
	
}
