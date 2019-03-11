/**
 * @(#) CommonFilter.java Created on 2010-5-21
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hjkj.business.common.WebConstants;
import com.hjkj.business.usermanage.po.HospitalUserInfo;
import com.hjkj.business.util.WebUtils;
import com.hjkj.web.controller.BaseController;


/**
 * The class <code>CommonFilter</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public class CommonFilter implements Filter {

	/**
	 * The page encoding
	 */
	public static String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		if (encoding == null || encoding.isEmpty()) {
			encoding = WebConstants.PAGE_ENCODING;
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean needSignOn = (session == null);
		String url = req.getRequestURI();
		if (!url.endsWith("/userLogin.do")
				&& !url.endsWith("/preInsertUserInfo.do")
				&& !url.endsWith("/insertUserInfo.do")
				&& !url.endsWith("/queryUserInfo.do")
				&& !url.endsWith("/getMsgs.rpc")
				&& !url.endsWith("/readMsg.rpc")
				) {
			if (!needSignOn) {
				HospitalUserInfo user = BaseController.getUserOnSession(req);
				needSignOn = (user == null);
			}
			if (needSignOn) {
				if (url.indexOf(".rpc") > 0) {
					WebUtils.sendHttpJsonResponse(req, resp, "2", "");
				} else {
					resp.sendRedirect(WebConstants.JSP_ROOT + "/logout.jsp");
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
		// chain.doFilter(request, response);
	}

	// TODO
	private static int index = 0;

	private static synchronized String getSubmitToken() {
		index++;
		return String.valueOf(index);
	}
}
