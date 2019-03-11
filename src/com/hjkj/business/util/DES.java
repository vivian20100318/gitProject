package com.hjkj.business.util;

import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.Cipher;

@SuppressWarnings("serial")
public class DES
    implements java.io.Serializable {
	
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

  SecretKey key;

  public DES() {
    try {
      String skey = "7x8u2q9a";
      byte rawKeyData[] = skey.getBytes();
      DESKeySpec dks = new DESKeySpec(rawKeyData);
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      key = keyFactory.generateSecret(dks);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
	 * 解密方法输入String输出String
	 */
	public String createDecryptor(String decryptString) throws Exception {
		String ss = new String(decryptString.getBytes("UTF-8"), "UTF-8");
		byte[] byteMi = Base64.decode(ss);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec("7x8u2q9a".getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.PRIVATE_KEY, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		// byte decryptedData[] = cipher.doFinal(decryptString.getBytes());

		return new String(decryptedData, "UTF-8");
	}

	public String createEncryptor(String in)throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec("7x8u2q9a".getBytes(), "DES");
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher  
        enCipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);// 设置工作模式为加密模式，给出密钥和向�?  
        byte[] pasByte = enCipher.doFinal(in.getBytes("utf-8"));  
        return Base64.encode(pasByte); 
	}

 public static void main(String s[]) {
    DES p = new DES();
    String se;
	try {
		se = p.createEncryptor("123456");
		 System.out.println(se.length() + " " + se);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   
    String sd;
	try {
		sd = p.createDecryptor("EZOsMPcDeq8==");
		System.out.println("sd:"+sd);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

  }

}
