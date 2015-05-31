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
  		<div>
  			<form name="logout" action="<%=path%>/servlet.do" method="post">
  				<label for="logout">登出</label>
  				<input id="logout" type="submit" value="进入"<%-- style="display:none;"--%>>
  				<input type="hidden" name="operate" value="logout">
  			</form>
  		</div>
  		<div>
  			<form name="documentBackup" action="<%=path%>/servlet.do" method="post">
				<label for="suggestion">提建议</label>
				<input id="suggestion" type="submit" value="进入">
				<input type="hidden" name="operate" value="suggestion">
			</form>
  		</div>
  		<div class="<%=checkAdministrator%>">
  			<form name="appToAdmin" action="<%=path%>/servlet.do" method="post">
  				<label for="appToAdmin">管理界面</label>
  				<input id="appToAdmin" type="submit" value="进入">
  				<input type="hidden" name="operate" value="appToAdmin">
  			</form>
  		</div>
  		<%-- <div>
  			<form name="documentBackup" action="<%=path%>/servlet.do" method="post">
  				<label for="documentBackup">业务报备</label>
  				<input id="documentBackup" type="submit" value="进入">
  				<input type="hidden" name="operate" value="documentBackup">
  			</form>
  		</div>
  		<div>
  			<form name="documentBackupQuery" action="<%=path%>/servlet.do" method="post">
  				<label for="documentBackupQuery">报备查询</label>
  				<input id="documentBackupQuery" type="submit" value="进入">
  				<input type="hidden" name="operate" value="documentBackupQuery">
  			</form>
  		</div> --%>
  		<div>
  			<form name="backup" action="<%=path%>/servlet.do" method="post">
  				<label for="backup">备案管理</label>
  				<input id="backup" type="submit" value="进入">
  				<input type="hidden" name="operate" value="backup">
  			</form>
  		</div>
  		<div>
  			<form name="backupQuery" action="<%=path%>/servlet.do" method="post">
  				<label for="backupQuery">备案查询</label>
  				<input id="backupQuery" type="submit" value="进入">
  				<input type="hidden" name="operate" value="backupQuery">
  			</form>
  		</div>
    </div>
  </body>
</html>
