<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="administratorDAO.AuthorityDAO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String staffId = (String) request.getSession().getAttribute("staffId");
//判断用户是否有管理员权限
String checkAdministrator = null;
AuthorityDAO authorityDAO = new AuthorityDAO();
if (authorityDAO.checkAdministrator(request,staffId)){
	checkAdministrator = "normal";
} else {
	checkAdministrator = "hide";
}
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
	<link href="styles/mainForUser.css" rel="stylesheet" type="text/css">
  </head>

  <body>
  	<div class="main">
  		<p>你好，<%=request.getSession().getAttribute("staffId") %></p>
    	<div class="<%=checkAdministrator %>"><a href="<%=path%>/servlet/AdministratorServlet" >管理界面</a></div>
    	<a href="<%=path%>/functions/upload.jsp">上传测试</a><br>
    	<a href="<%=path%>/functions/download.jsp">下载测试</a>
    </div>
  </body>
</html>
