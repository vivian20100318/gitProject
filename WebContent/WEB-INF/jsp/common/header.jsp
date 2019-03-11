<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.hjkj.business.common.WebConstants" %>
<%@page import="com.hjkj.business.util.MctsUtils"%>
<%@page import="com.hjkj.business.usermanage.po.HospitalUserInfo"%>  
<%@page import="com.hjkj.common.MctsSessionKeys"%>
<%@page import="com.aspire.boc.util.ResourceManager"%>  
<c:set var="ctx" value="<%=WebConstants.WEB_ROOT%>"/>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=WebConstants.WEB_ROOT%>/style/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="<%=WebConstants.WEB_ROOT%>/style/common_style.css" rel="stylesheet" type="text/css">
	
	<link href="<%=WebConstants.WEB_ROOT%>/style/selected.css" rel="stylesheet" type="text/css">
	<link href="<%=WebConstants.WEB_ROOT%>/style/dialog.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=WebConstants.WEB_ROOT%>/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
	<link href="<%=WebConstants.WEB_ROOT%>/style/upload.css" rel="stylesheet" type="text/css">
	<link href="<%=WebConstants.WEB_ROOT%>/scripts/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
	<link href="<%=WebConstants.WEB_ROOT%>/style/zTreeStyle.css" rel="stylesheet" type="text/css">
	
	<script src="<%=WebConstants.WEB_ROOT%>/scripts/My97DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/jquery/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/authority/commonAll.js"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/artDialog/artDialog.js?skin=aero"></script>
	<script src="<%=WebConstants.WEB_ROOT%>/scripts/utils.js" type="text/javascript"></script>
	<script src="<%=WebConstants.WEB_ROOT%>/scripts/jquery.form.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/scripts/jqueryValidate/js/jquery.validate.min.js" ></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/jquery-ui/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/zTree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/zTree/jquery.ztree.excheck-3.5.js"></script>
	
	<c:set var="user" value="<%=(HospitalUserInfo) (session.getAttribute(MctsSessionKeys.SESSION_LOGIN_USER))%>"/>
	<title>信息管理系统</title>
	<script language="javascript">      
	var js_web_root = '<%=WebConstants.WEB_ROOT %>';
		/** 输入页跳转 **/
	function jumpInputPage(totalPage){
		// 如果“跳转页数”不为空
		if($("#jumpNumTxt").val() != ''){
			var pageNum = parseInt($("#jumpNumTxt").val());
			// 如果跳转页数在不合理范围内，则置为1
			if(pageNum<1 | pageNum>totalPage){

				alert("请输入合适的页数");
				return;
			}else{
				doQuery(pageNum);
			}
		}else{
			alert("请输入合适的页数");
			return;
		}
	}
	
	</script>
	<Script language="javascript">
		function GetRequest() {
		   var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		      strs = str.split("&");
		      for(var i = 0; i < strs.length; i ++) {
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
		      }
		   }
		   return theRequest;
		}
</Script>
	<script src="<%=WebConstants.WEB_ROOT%>/scripts/ajax_sub.js" type="text/javascript"></script>
</head>	
<form name="innerform" id="innerform" method="post" style="margin: 0px;">
</form>
<iframe id='target_upload' name='target_upload' src=''
			style='display: none'></iframe>