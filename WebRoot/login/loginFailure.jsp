<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
	<link rel="stylesheet" type="text/css" href="styles/index.css">
  </head>
  
  <body>
  	<div class="main">用户名或密码错误！<br>
  		<a href="index.jsp">返回</a>
  	</div>
  </body>
</html>
