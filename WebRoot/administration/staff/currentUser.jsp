<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="administrationDAO.CurrentUserDAO" %>
<%@ page import="administrationEntity.CurrentUser" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
    <style></style>
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
    <h1 align="center">当前用户</h1>
    <table width="1800px" border="1" align="center">
    	<tr>
    		<th height="30px">会话ID</th>
    		<th height="30px">IP地址</th>
    		<th height="30px">员工工号</th>
    		<th height="30px">登入时间</th>
    	</tr>
<%
CurrentUserDAO currentUserDAO = new CurrentUserDAO();
ArrayList<CurrentUser> currentUserList = currentUserDAO.showCurrentUser(request);
if (currentUserList!=null&&currentUserList.size()>0){
	for (int i=0;i<currentUserList.size();i++){
		CurrentUser currentUser = currentUserList.get(i);
 %>
    	<tr>
    		<td height="30px" align="center"><%=currentUser.getSessionId() %></td>
    		<td height="30px" align="center"><%=currentUser.getIpAddress() %></td>
    		<td height="30px" align="center"><%=currentUser.getStaffId() %></td>
    		<td height="30px" align="center"><%=sdf.format(currentUser.getEffDate()) %></td>
    	</tr>
<%
	}
}
 %>
     </table>
  </body>
</html>
