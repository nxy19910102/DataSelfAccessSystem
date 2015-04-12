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
  	<div class="main">
  		<h3>当前在线人数：<%=request.getSession().getServletContext().getAttribute("currentUserNumber")%></h3>
    	<a href="staff/staffManage.jsp">员工管理</a><br>
    	<a href="errorPages/errorManage.jsp">错误管理</a><br>
    	<a href="suggestions/suggestionManage.jsp">建议管理</a><br>
    	<a href="logs/requestLog.jsp">日志管理</a><br>
    	<a href="staff/currentUser.jsp">当前用户</a><br>
    	<a href="errorPages/error500.jsp">error500测试</a>
	</div>
  </body>
</html>
