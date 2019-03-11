/**
 * @(#) BaseController.java Created on 2010-5-17
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.web.controller;

import java.util.Locale;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.portlet.ModelAndView;

import com.hjkj.business.usermanage.po.HospitalUserInfo;
import com.hjkj.common.MctsSessionKeys;

/**
 * The class <code>BaseController</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public class BaseController {

	@Resource(name = "messageSource")
	private MessageSource i18nMsg;

	/**
	 * Gets i18n message from resource
	 * @param key The message resource key
	 * @param locale The locale of the HTTP request
	 * @return Returns the i18n message
	 */
	protected String getMessage(String key, Locale locale) {
		return getMessage(key, null, locale);
	}

	/**
	 * Gets i18n message from resource
	 * @param key The message resource key
	 * @param args The locale of the HTTP request
	 * @param locale The locale of the HTTP request
	 * @return Returns the i18n message
	 */
	protected String getMessage(String key, Object[] args, Locale locale) {
		return i18nMsg.getMessage(key, args, key, locale);
	}

	/**
	 * Gets i18n message from resource
	 * @param key The message resource key
	 * @param request The HTTP request
	 * @return Returns the i18n message
	 */
	public String getMessage(String key, HttpServletRequest request) {
		return getMessage(key, null, request.getLocale());
	}

	/**
	 * Gets i18n message from resource
	 * @param key The message resource key
	 * @param args The locale of the HTTP request
	 * @param request The HTTP request
	 * @return Returns the i18n message
	 */
	public String getMessage(String key, Object[] args,
			HttpServletRequest request) {
		return getMessage(key, args, request.getLocale());
	}

	public static final void clearSession(final HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 
	 * @param modelMap
	 * @param dlgTitleKey
	 * @param infoTitleKey
	 * @param message
	 * @param returnUrl
	 */
	protected void saveErrorInfo(ModelMap modelMap, String dlgTitleKey,
			String infoTitleKey, String message, String returnUrl) {
		if (modelMap == null) {
			return;
		}
	}

	/**
	 * 
	 * @param dlgTitleKey
	 * @param titleKey
	 * @param infoTitleKey
	 * @param message
	 */
	protected void saveErrorInfo(ModelMap modelMap, String dlgTitleKey,
			String infoTitleKey, String message) {
		saveErrorInfo(modelMap, dlgTitleKey, infoTitleKey, message, null);
	}

	/**
	 * 
	 * @param dlgTitleKey
	 * @param infoTitleKey
	 * @param message
	 * @param returnUrl
	 * @return
	 */
	protected ModelAndView saveErrorInfo(String dlgTitleKey,
			String infoTitleKey, String message, String returnUrl) {
		ModelAndView modelAndView = new ModelAndView("/error");
		saveErrorInfo(modelAndView.getModelMap(), dlgTitleKey, infoTitleKey,
				message, null);
		return modelAndView;
	}

	/**
	 * 鐢熸垚鎴愬姛淇℃伅
	 * @param dlgTitleKey
	 * @param titleKey
	 * @param infoTitleKey
	 * @param message
	 */
	protected void saveSuccessInfo(ModelMap modelMap, String dlgTitleKey,
			String infoTitleKey, String message) {

	}

	/**
	 * 
	 * @param dlgTitleKey
	 * @param infoTitleKey
	 * @param message
	 * @param returnUrl
	 * @return
	 */
	protected ModelAndView saveSuccessInfo(String dlgTitleKey,
			String infoTitleKey, String message, String returnUrl) {
		ModelAndView modelAndView = new ModelAndView("/success");
		saveErrorInfo(modelAndView.getModelMap(), dlgTitleKey, infoTitleKey,
				message, null);
		return modelAndView;
	}

	/**
	 * 
	 * @param req
	 * @param action
	 */
	public static void setUserAction(HttpServletRequest req, String action) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			//session.setAttribute(MctsSessionKeys.SESSION_LOGIN_USER_ACTION, action);   		
		}
	}

	/**
	 * 
	 * @param req
	 * @return
	 */
	public static final HospitalUserInfo getUserOnSession(final HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			HospitalUserInfo user = (HospitalUserInfo) (session
					.getAttribute(MctsSessionKeys.SESSION_LOGIN_USER));
			return user;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static String getRandomForURL() {
		return getRandomForURL(true);
	}

	public static String getRandomForURL(boolean hasOtherParam) {
		if (hasOtherParam) {
			return "&nnn=" + (new Random().nextInt());
		} else {
			return "?nnn=" + (new Random().nextInt());
		}

	}
}
