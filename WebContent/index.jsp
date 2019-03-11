<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hjkj.business.common.WebConstants" %>
<%@page import="com.hjkj.business.util.WebUtils"%>
<%@ page import="com.hjkj.common.MctsSessionKeys" %>
<%@ page import="com.aspire.util.GenApi" %>
<%@ page import="com.hjkj.business.util.MctsUtils"%>
<!DOCTYPE HTML >
<html lang="zh-CN">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>病例邮寄管理系统</title>
	<script type="text/javascript" src="<%=WebConstants.WEB_ROOT%>/scripts/jquery/jquery-1.7.1.js"></script>
	<script src="<%=WebConstants.WEB_ROOT%>/scripts/md5.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="<%=WebConstants.WEB_ROOT%>/style/login.css"/>
  </head>
  <%
	String sPwdChallenge = WebUtils.generateChallenge(GenApi.stringRandom(16));
	session.setAttribute(MctsSessionKeys.PWD_CHALLENGE, sPwdChallenge);
  %>
  <script language="javascript">
	
	function submitLogin(){
		var user_id = $("#user_id").val();
		if(user_id==""){
			alert("用户ID不能为空");
			return;
		}
		
		var user_pwd = $("#user_pwd").val();
		if(user_pwd==""){
			alert("密码不能为空");
			return;
		}
		
		var challenge = $.md5($.md5(user_pwd)+"<%=sPwdChallenge%>");
		$("#challenge").val(challenge);
		document.fmLoginForm.submit();
	}
	
  </script>
 

  <body background="<%=WebConstants.WEB_ROOT%>/img/login/bg.jpg" style="background:#2066CE; color:#2066CE  background-position: -0px -60px"  class="boby">
  
    <form  name="fmLoginForm" method="post" action="<%=WebConstants.WEB_ROOT%>/userLogin.do" id="fmLoginForm" onsubmit="return false;">
		<input type="hidden" id="challenge" name="challenge" />
		<div id="login_all" class="login_all">
				<table width="420px" height="450px" border="0" class="login_table">
				<tr height="48" style="">
						<td class="login_title">
							<span id="" style="font-size:26px;color:#87898d;font-family: '微软雅黑';">
								后台登录
							</span>
						</td>
					</tr>
					
					<tr height="76">
						<td>
							<div class="table_box">
								<div class="table_all">
									<div class="table_name" >
										<span id="" class="table_namespan" >
											
										</span>
									</div>
									<div id="" class="table_inputall" >
										<input type="text" name="user_id" id="user_id" value="" placeholder="请输入用户名" class="table_input" />
									</div>
								</div> 
								<div class="table_all">
									<div class="table_name" >
										<span id="" class="table_pwdspan" >										
										</span>
									</div>
									<div id="" class="table_inputall" >
										<input type="password" name="user_pwd" id="user_pwd" value="" placeholder="请输入密码" class="table_input" />
									</div>
								</div> 
							</div>
						</td>
					</tr>
				
					<tr height="0">
						<td >
							<span style="color:red;">
									<%
										String error = (String)request.getSession().getAttribute(MctsSessionKeys.ERR_LOGIN_INFO);
										if(error != null && !("".equals(error)) &&!("null".equals(error))){
											out.write(error);
										}else{
											out.write("");
										}
									%>
							</span>
						</td>
					</tr>
					<tr height="100">
						<td>
							<div id="" style="width: 300px;  height:50px;">
								<!-- <div id="" style="width: 150px;height: 25px; float: left; margin-top: 20px;  margin-left: 17px;">
									<input type="checkbox" name="" id="" value="" style="width: 20px; height: 20px;"/>
									<span style="font-size: 20px; font-family: '微软雅黑';">记住密码</span>
								</div>  -->
								<div id="" >
									<button  class="buttondiv" onclick="submitLogin();">登录</button>
									
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
  	</body>
</html>
