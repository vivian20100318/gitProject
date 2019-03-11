package com.hjkj.business.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.aspire.boc.util.ResourceManager;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GeTuiUtil {

	public static boolean pushContentToApp(String news_id, String title,
			String news_type) throws Exception {
		ResourceManager rm = ResourceManager.getInstance();
		String appId = rm.getValue("getui_appid");
		String appkey = rm.getValue("getui_appkey");
		String master = rm.getValue("getui_master");
		String host = rm.getValue("getui_host");
		IGtPush push = new IGtPush(host, appkey, master);

		// 透传模板
		TransmissionTemplate template = TransmissionTemplateDemo(appId, appkey,
				news_id, title, news_type);
		AppMessage message = new AppMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可�?
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 设置推�?�目标条件过�?
		List appIdList = new ArrayList();
		List phoneTypeList = new ArrayList();
		List provinceList = new ArrayList();
		List tagList = new ArrayList();
		appIdList.add(appId);
		// 设置机型
		// phoneTypeList.add("ANDROID");
		// //设置省份
		// provinceList.add("浙江");
		// //设置标签内容
		// tagList.add("�?�?");
		message.setAppIdList(appIdList);
		message.setPhoneTypeList(phoneTypeList);
		message.setProvinceList(provinceList);
		message.setTagList(tagList);
		IPushResult ret = push.pushMessageToApp(message);
		String resultCode = (String) ret.getResponse().get("result");
		if ("ok".equals(resultCode)) {
			return true;
		} else {
			return false;
		}
	}

	public static TransmissionTemplate TransmissionTemplateDemo(String appId,
			String appkey, String news_id, String title, String news_type) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 透传消息设置�?1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动�??
		template.setTransmissionType(2);
		JSONObject messageObj = new JSONObject();
		messageObj.element("newsTitle", title);
		messageObj.element("newsType", news_type);
		messageObj.element("newsId", news_id);
		template.setTransmissionContent(messageObj.toString());
		//
		APNPayload payload = new APNPayload();
		payload.setBadge(1);
		payload.setContentAvailable(1);
		payload.setSound("default");
		// payload.setCategory("qq");
		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(title));
		payload.addCustomMsg("type", news_type);
		payload.addCustomMsg("id", news_id);
		// 字典模式使用下�??
		// payload.setAlertMsg(getDictionaryAlertMsg());
		template.setAPNInfo(payload);
		return template;
	}
}
