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
	<link rel="stylesheet" type="text/css" href="styles/administration.css">
  </head>
  <body>
  	<div class="main">
    	<label>你好，<%=request.getSession().getAttribute("staffId")%></label>
    	<form name="logout" action="<%=path%>/servlet.do" method="post">
  				<label for="logout">登出</label>
  				<input id="logout" type="submit" value="进入"<%-- style="display:none;"--%>>
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入"<%-- style="display:none;"--%>>
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
	<div class="main">
		<div>
			<form name="staff" action="<%=path%>/servlet.do" method="post">
				<label for="staff">员工管理</label>
				<input id="staff" type="submit" value="进入">
				<input type="hidden" name="operate" value="staff">
			</form>
		</div>
		<%-- <div>
			<form name="currentUser" action="<%=path%>/servlet.do" method="post">
				<label for="currentUser">当前用户</label>
				<input id="currentUser" type="submit" value="进入">
				<input type="hidden" name="operate" value="currentUser">
			</form>
		</div> --%>
		<div>
			<form name="suggestionManage" action="<%=path%>/servlet.do" method="post">
				<label for="suggestionManage">建议管理</label>
				<input id="suggestionManage" type="submit" value="进入">
				<input type="hidden" name="operate" value="suggestionManage">
			</form>
		</div>
		<div>
			<form name="errorManage" action="<%=path%>/servlet.do" method="post">
				<label for="errorManage">错误管理</label>
				<input id="errorManage" type="submit" value="进入">
				<input type="hidden" name="operate" value="errorManage">
			</form>
		</div>
		<div>
			<form name="requestLog" action="<%=path%>/servlet.do" method="post">
				<label for="requestLog">日志管理</label>
				<input id="requestLog" type="submit" value="进入">
				<input type="hidden" name="operate" value="requestLog">
			</form>
		</div>
	</div>
  </body>
</html>
