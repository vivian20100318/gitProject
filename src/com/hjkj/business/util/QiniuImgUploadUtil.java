package com.hjkj.business.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import com.aspire.boc.util.ResourceManager;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
/**
 * 
 * 
 * @类编�?:
 * @类名�?:QiniuImgUploadUtil
 * @内容摘要: 上传七牛服务�?
 * @author:luweiwei
 * @创建日期:2015�?8�?15�? 上午9:21:09
 * @修改�?:
 * @修改日期:
 * @修改描述:�?单描述修改的内容
 * @version 1.0.0
 *
 */
public class QiniuImgUploadUtil {
	private static ResourceManager rm = ResourceManager.getInstance();
	public static String uploadImg(byte[] imgData){
		String img_url="";
		String fileName = new Random().nextInt(99999999)+".jpg";
		try {
			Auth auth = Auth.create(
					rm.getValue("qiniu_access_key"),
					rm.getValue("qiniu_secret_key"));
			String timeTemp = String.valueOf(new Date().getTime());
			UploadManager uploadManager = new UploadManager();
			Response res= uploadManager.put(imgData, timeTemp + "_"
					+ fileName, auth.uploadToken("qlydimg"));
			if (res.isOK()) {
				String resultStr = res.bodyString();
				JSONObject messageObj = JSONObject.fromObject(resultStr);
				String key = messageObj.getString("key");
				String qiniuImgPre = rm.getValue("qiniu_img_pre");
				img_url = qiniuImgPre + key;
			}
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img_url;
	}
	/**
	 * 
	 * @方法名称:create2QRCode
	 * @内容摘要: ＜生成二维码�?
	 * @param contentStr
	 * @return 
	 * String 内容
	 * @exception 
	 * @author:luweiwei
	 * @since  1.0.0
	 */
	public static String createQRCode(String id,String type){
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		Map<String,Object> map= new HashMap<String,Object>();
		String url="";
		try {
			if((!"".equals(id)||id !=null)&&(!"".equals(type)||type !=null)){
				map.put("id", id);
				map.put("type", type);
				MatrixToLogoImageWriter.encode(JsonUtils.convertToString(map).toString(),baos);
//			MatrixToLogoImageWriter.encode(contentStr, "C:/Users/Administrator/Desktop/test2D/logo.png",baos , true);
				byte[] bytess=baos.toByteArray();
				url=uploadImg(bytess);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	/**
	 * 
	 * @方法名称:createQRCode
	 * @内容摘要: ＜活动定制生成url�?
	 * @param id
	 * @param type
	 * @param activeUrl
	 * @return 
	 * String
	 * @exception 
	 * @author:luweiwei
	 * @since  1.0.0
	 */
	public static String createQRCode(String id,String type,String activeUrl){
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		Map<String,Object> map= new HashMap<String,Object>();
		String url="";
		try {
			map.put("id", id);
			map.put("type", type);
			map.put("activeUrl", activeUrl);
			MatrixToLogoImageWriter.encode(JsonUtils.convertToString(map).toString(),baos);
//			MatrixToLogoImageWriter.encode(contentStr, "C:/Users/Administrator/Desktop/test2D/logo.png",baos , true);
			byte[] bytess=baos.toByteArray();
			url=uploadImg(bytess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	public static void main(String[] args) throws Exception {
		String url=createQRCode("121432","1");
		System.out.println(url);
	}
}
