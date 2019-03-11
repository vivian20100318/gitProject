<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hjkj.business.common.WebConstants" %>
<%@page import="com.hjkj.business.util.MctsUtils"%>
<%@page import="com.hjkj.common.MctsSessionKeys"%> 
<html>
	<head>
		<meta charset="utf-8">
		<title>登陆超时</title>
		
		<style type="text/css">
			.div_all{width: 700px; height: 350px;box-shadow: 0px 0px 15px orangered; border-radius: 35px;margin: auto; margin-top: 100px;}
			.button_all{width: 120px;height: 50px; background: red; border: 0; border-radius: 25px; box-shadow:0px 0px 10px #D43F3A; outline: white; font-size: 20px; color: white;}
		</style>
	</head>
	<script language="javascript">
		function relogin(){
			window.top.location.href="<%=WebConstants.WEB_ROOT%>/index.jsp";
		}
	</script>
	<body >
		<div id="" class="div_all" style="">
			
		
		<table border="0" width="600px" height="200px" style="text-align: center; margin: auto;padding-top: 50px;">
			<tr>
				<td rowspan="2" width="220px">
					
				</td>
				<td height="120px" >
					<span style="font-family: '微软雅黑'; font-size: 30px; color: red;">登录超时了，请重新登录！</span>
					
				</td>
			</tr>
			<tr>
				<td>
					
					<button class="button_all" onclick="relogin();">登录</button>
				</td>
				
			</tr>
			
		</table>
		
		</div>
	</body>
</html>