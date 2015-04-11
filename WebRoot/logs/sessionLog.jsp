<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="dao.SessionLogDAO" %>
<%@ page import="entity.SessionLog" %>
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
  	<div align="center"><h1>会话日志</h1>
	    <table width="1200px" border="1" align="center">
	    	<tr>
	    		<th height="30px">序号</th>
	    		<th height="30px">会话ID</th>
	    		<th height="30px">IP地址</th>
	    		<th height="30px">登入时间</th>
	    		<th height="30px">登入工号</th>
	    	</tr>
<%
SessionLogDAO sessionLogDAO = new SessionLogDAO();
ArrayList<SessionLog> sessionLogList = sessionLogDAO.showSessionLog();
if (sessionLogList!=null&&sessionLogList.size()>0){
	for (int i=0;i<sessionLogList.size();i++){
		SessionLog sessionLog = sessionLogList.get(i);
 %>
	    	<tr>
	    		<td height="30px" align="center"><%=sessionLog.getId() %></td>
	    		<td height="30px" align="center"><%=sessionLog.getSessionId() %></td>
	    		<td height="30px" align="center"><%=sessionLog.getIpAddress() %></td>
	    		<td height="30px" align="center"><%=sessionLog.getEffDate() %></td>
	    		<td height="30px" align="center"><%=sessionLog.getStaffId() %></td>
	    	</tr>
<%
	}
}	
%>
     
        </table>
  	</div>
  </body>
</html>
