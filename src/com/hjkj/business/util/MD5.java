package com.hjkj.business.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	public static String getMD5ofStr(String input) {
		String jms="";
		try {
			jms=code(input, 16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jms;
	}

	public static String code(String input, int bit) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance(System.getProperty(
					"MD5.algorithm", "MD5"));
			if (bit == 16)
				return bytesToHex(md.digest(input.getBytes("utf-8")))
						.substring(8, 24);
			return bytesToHex(md.digest(input.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}

	public static void main(String args[]) {

		//MD5 m = new MD5();
		System.out.println(MD5.getMD5ofStr("123456"));
		try {
			System.out.println(MD5.getMD5Str("123456").toLowerCase());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * if (Array.getLength(args) == 0) { //如果没有参数，执行标准的Test Suite
		 * 
		 * System.out.println("MD5 Test suite:");
		 * System.out.println("MD5(\"\"):" + m.getMD5ofStr(""));
		 * System.out.println("MD5(\"a\"):" + m.getMD5ofStr("a"));
		 * System.out.println("MD5(\"abc\"):" + m.getMD5ofStr("abc"));
		 * System.out.println("MD5(\"message digest\"):" +
		 * m.getMD5ofStr("message digest"));
		 * System.out.println("MD5(\"abcdefghijklmnopqrstuvwxyz\"):" +
		 * m.getMD5ofStr("abcdefghijkqlmnopqrstuvwxyz")); System.out.println(
		 * "MD5(\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\"):"
		 * + m.getMD5ofStr(
		 * "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")); }
		 * else { System.out.println("MD5(" + args[0] + ")=" +
		 * m.getMD5ofStr(args[0]));
		 * 
		 * }
		 */

	}
	
	public final static String getMD5Str(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法�? MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘�?
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
