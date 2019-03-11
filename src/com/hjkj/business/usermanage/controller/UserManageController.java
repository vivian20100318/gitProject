package com.hjkj.business.usermanage.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspire.boc.util.ResourceManager;
import com.hjkj.business.common.WebConstants;
import com.hjkj.business.usermanage.po.CaseMailMenuInfo;
import com.hjkj.business.usermanage.po.HospitalUserInfo;
import com.hjkj.business.util.JsonWrapper;
import com.hjkj.business.util.WebUtils;
import com.hjkj.common.MctsSessionKeys;
import com.hjkj.business.usermanage.controller.UserManageController;
import com.hjkj.business.usermanage.service.IUserManageService;
import com.hjkj.web.controller.BaseController;

import net.sf.json.JSONArray;

@Controller
public class UserManageController extends BaseController{
	private static Log LOG = LogFactory.getLog(UserManageController.class);
	
	@Resource(name = "userManageService")
	private IUserManageService userManageService;

	private static ResourceManager rm = ResourceManager.getInstance();
	
	String endpoint = rm.getValue("endpoint");
	String accessKeyId = rm.getValue("accessKeyId");
	String accessKeySecret = rm.getValue("accessKeySecret");
	String urlpre = rm.getValue("urlpre");
	
	
	@RequestMapping(value = "/userLogin.do")
	public void userLogin(HttpServletRequest request,
			HttpServletResponse response, String user_id, String challenge) {
		HttpSession session = request.getSession();
		try {
			// 用户登录
			String sessionChallenge = WebUtils.object2String(session
					.getAttribute(MctsSessionKeys.PWD_CHALLENGE));

			HospitalUserInfo user = userManageService.userLogin(user_id, challenge,
					sessionChallenge);
			if (user != null) {
				if ("2".equals(user.getUser_status())) {
					// 保存错误信息
					session.setAttribute(MctsSessionKeys.ERR_LOGIN_INFO,
							"您的账户已经注销，请联系管理人员！");
					session.setAttribute("oldUserId", user_id);
					response.sendRedirect(WebConstants.WEB_ROOT + "/index.jsp");
					return;
				}
				session.removeAttribute(MctsSessionKeys.PWD_CHALLENGE);
				session.removeAttribute(MctsSessionKeys.ERR_LOGIN_INFO);
				// 在Session中保存用户信息
				session.setAttribute(MctsSessionKeys.LOGIN_USER, user_id);
				session.setAttribute(MctsSessionKeys.SESSION_LOGIN_USER, user);
				response.sendRedirect(WebConstants.WEB_ROOT + "/goToMain.do");
				return;
			} else {
				// 保存错误信息
				session.setAttribute("oldUserId", user_id);
				session.setAttribute(MctsSessionKeys.ERR_LOGIN_INFO,
						"用户名或密码不正确");
				response.sendRedirect(WebConstants.WEB_ROOT + "/index.jsp");
				return;
			}
		} catch (IOException ioex) {
			Logger log = Logger.getLogger(getClass());
			log.error("Sends redirect response error.", ioex);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).error("login.do", ex);
			// 保存错误信息
			session.setAttribute(MctsSessionKeys.ERR_LOGIN_INFO,
					getMessage("login.fail.title", request));
			try {
				response.sendRedirect(WebConstants.JSP_ROOT + "/index.jsp");
			} catch (IOException ioex) {
				Logger log = Logger.getLogger(getClass());
				log.error("Sends redirect response error.", ioex);
			}
		}
	}
	
	
	@RequestMapping(value = "/goToMain.do")
	public String goToMain(HttpServletRequest request, final ModelMap context) {
		try {
			HospitalUserInfo user = getUserOnSession(request);
			context.addAttribute("user", user);
			return "common/main";
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("queryUser.do", e);
			return "/error";
		}
	}
	

	@RequestMapping(value = "/logout.do")
	public void logout(final HttpServletRequest req,
			HttpServletResponse response, final ModelMap context) {
		HospitalUserInfo user = getUserOnSession(req);
		if (user != null) {
			clearSession(req);
		}
		try {
			response.sendRedirect(WebConstants.WEB_ROOT + "/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getMenuListByType.rpc")
	public void getMenuListByType(HttpServletRequest request,
			HttpServletResponse response, String resource_type) {
		
		HttpSession session = request.getSession();
		try {
			HospitalUserInfo user = getUserOnSession(request);
			HashMap menuSearchMap = new HashMap();
			menuSearchMap.put("resource_type", resource_type);
			List<CaseMailMenuInfo> menuList = userManageService.getCaseMailMenuListForShow(menuSearchMap);
			String userRoleJson = user.getRole_json() ;
			List<String> userHasMenuList = new ArrayList<String>();
			String[] roleArray = userRoleJson.split(",");
			for (int j = 0; j < roleArray.length; j++) {
				if (!userHasMenuList.contains(roleArray[j])) {
					userHasMenuList.add(roleArray[j]);
				}
			}
			List<HashMap> trueMenuList = new ArrayList<HashMap>();
			
			
			for (int i = 0; i < menuList.size(); i++) {
				CaseMailMenuInfo subMenu = menuList.get(i);
				if(userHasMenuList.contains(String.valueOf(subMenu.getResource_id()))){
					int resource_grade = subMenu.getResource_grade();
					String access_path = subMenu.getAccess_path() == null ? ""
							: subMenu.getAccess_path();
					HashMap menuMap = new HashMap();
					menuMap.put("resource_id", subMenu.getResource_id());
					menuMap.put("parent_id", subMenu.getParent_id());
					menuMap.put("resource_name", subMenu.getResource_name());
					menuMap.put("accessPath", access_path);
					trueMenuList.add(menuMap);
				}
			}
			
			session.removeAttribute(MctsSessionKeys.SESSION_LOGIN_USER);
			session.setAttribute(MctsSessionKeys.SESSION_LOGIN_USER, user);

			JSONArray jsonArray2 = JSONArray.fromObject(trueMenuList);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonArray2);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("getTwoFenleiByOne.rpc", e);
			try {
				WebUtils.sendHttpResponse(request, response, JsonWrapper
						.wrapRpcResp(WebConstants.RPC_RESP_STATUS_ERR, ""));
			} catch (IOException e1) {
				Logger.getLogger(getClass()).error("WebUtils.sendHttpResponse",
						e1);
			}
		}
	}
	

	
	
	
	
	
}
