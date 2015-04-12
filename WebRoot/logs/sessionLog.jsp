<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="administratorDAO.SessionLogDAO" %>
<%@ page import="administratorEntity.SessionLog" %>
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
  	<div align="center">
  		<h1>会话日志</h1>
  		<h4><a href="<%=path%>/logs/requestLog.jsp">访问日志</a></h4>
	    <table width="1800px" border="1" align="center">
	    	<tr>
	    		<th height="30px">序号</th>
	    		<th height="30px">会话ID</th>
	    		<th height="30px">IP地址</th>
	    		<th height="30px">登入工号</th>
	    		<th height="30px">登入时间</th>
	    		<th height="30px">登出工号</th>
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
	    		<td height="30px" align="center"><%=sessionLog.getStaffId() %></td>
	    		<td height="30px" align="center"><%=sessionLog.getEffDateString() %></td>
	    		<td height="30px" align="center"><%=sessionLog.getExpDateString() %></td>
	    	</tr>
<%
	}
}	
%>
     
        </table>
  	</div>
  </body>
</html>
