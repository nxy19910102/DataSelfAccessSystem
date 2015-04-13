<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="administrationDAO.AuthorityDAO" %>
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
  		<p>你好，<%=request.getSession().getAttribute("staffId")%></p>
  		<div class="<%=checkAdministrator%>">
  			<form name="appToAdmin" action="<%=path%>/servlet.do" method="post">
  				<label for="appToAdmin">管理界面</label>
  				<input id="appToAdmin" type="submit" value="进入">
  				<input type="hidden" name="operate" value="appToAdmin">
  			</form>
  		</div>
  		<div>
  			<form name="documentBackup" action="<%=path%>/servlet.do" method="post">
  				<label for="documentBackup">签报备份</label>
  				<input id="documentBackup" type="submit" value="进入">
  				<input type="hidden" name="operate" value="documentBackup">
  			</form>
  		</div>
  		<div>
  			<form name="upload" action="<%=path%>/servlet.do" method="post">
  				<label for="upload">上传测试</label>
  				<input id="upload" type="submit" value="进入">
  				<input type="hidden" name="operate" value="upload">
  			</form>
  		</div>
  		<div>
  			<form name="download" action="<%=path%>/servlet.do" method="post">
  				<label for="download">下载测试</label>
  				<input id="download" type="submit" value="进入">
  				<input type="hidden" name="operate" value="download">
  			</form>
  		</div>
    </div>
  </body>
</html>