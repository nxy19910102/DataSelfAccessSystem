<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8"); 
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
    	<h1>建议上传成功</h1>
    	<h2>您的留言是：</h2>
    	<h3><%=request.getParameter("detail") %></h3>
    	<h2><a href="<%=path %>/index.jsp">返回首页</a></h2>
    </div>
  </body>
</html>
