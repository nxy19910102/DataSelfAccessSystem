<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
	<link href="styles/index.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  	<div class="main">
    	<h1>地址输错啦！</h1>
    	<h2>本网站主页为</h2>
    	<h2>http://135.224.10.4:8080/DataSelfAccessSystem/index.jsp</h2>
    	<h2><a href="<%=path %>/index.jsp">点击进入</a></h2>
    </div>
  </body>
</html>
