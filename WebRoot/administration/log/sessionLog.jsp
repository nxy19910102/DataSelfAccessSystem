<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="administrationDAO.SessionLogDAO" %>
<%@ page import="administrationEntity.SessionLog" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
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
  	<div align="center">
  		<h1>会话日志</h1>
  		<div>
  			<form name="requestLog" action="<%=path%>/servlet.do" method="post">
  				<label for="requestLog">访问日志</label>
  				<input id="requestLog" type="submit" value="切换">
  				<input type="hidden" name="operate" value="requestLog">
  			</form>
  		</div>
	    <table width="1800px" border="1" align="center">
	    	<tr height="45px">
	    		<th>序号</th>
	    		<th>会话ID</th>
	    		<th>IP地址</th>
	    		<th>登入工号</th>
	    		<th>登入时间</th>
	    		<th>登出时间</th>
	    	</tr>
<%
SessionLogDAO sessionLogDAO = new SessionLogDAO();
ArrayList<SessionLog> sessionLogList = sessionLogDAO.showSessionLog();
if (sessionLogList!=null&&sessionLogList.size()>0){
	for (int i=0;i<sessionLogList.size();i++){
		SessionLog sessionLog = sessionLogList.get(i);
 %>
	    	<tr height="45px">
	    		<td align="center"><%=sessionLog.getId()%></td>
	    		<td align="center"><%=sessionLog.getSessionId()%></td>
	    		<td align="center"><%=sessionLog.getIpAddress()%></td>
	    		<td align="center"><%=sessionLog.getStaffId()%></td>
	    		<td align="center"><%=sessionLog.getEffDateString()%></td>
	    		<td align="center"><%=sessionLog.getExpDateString()%></td>
	    	</tr>
<%
	}
}	
%>
     
        </table>
  	</div>
  </body>
</html>
