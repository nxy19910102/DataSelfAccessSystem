<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.AuthorityDAO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String staffId = (String) request.getSession().getAttribute("staffId");
String checkAdministrator = null;
if (AuthorityDAO.checkAdministrator(request,staffId)){
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
    	<a href="<%=path%>/functions/upload.jsp">上传测试</a>
    </div>
  </body>
</html>
