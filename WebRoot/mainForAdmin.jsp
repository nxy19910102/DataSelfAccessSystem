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
<%System.out.println("jspStarted"); %>
  	<div class="main">
  		<h3>当前在线人数：<%=request.getSession().getServletContext().getAttribute("currentUserNumber")%></h3>
    	<a href="staff/staffManage.jsp">员工管理</a><br>
    	<a href="errorPages/errorManage.jsp">错误管理</a><br>
    	<a href="logs/requestLog.jsp">访问日志</a><br>
    	<a href="staff/currentUser.jsp">当前用户</a><br>
    	<p>SessionId:  <%=request.getSession().getId() %></p><br>
    	<p>Session:  <%=request.getSession().getAttribute("staff_id") %></p><br>
    	<p>Session:  <%=request.getSession().getAttribute("is_admin") %></p><br>
    	<a href="errorPages/error500.jsp">error500</a>
	</div>
<%System.out.println("jspFinished"); %>
  </body>
</html>
