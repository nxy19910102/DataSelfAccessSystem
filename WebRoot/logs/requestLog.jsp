<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="administratorDAO.RequestLogDAO" %>
<%@ page import="administratorEntity.RequestLog" %>
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
  		<h1>访问日志</h1>
  		<h4><a href="<%=path%>/logs/sessionLog.jsp">会话日志</a></h4>
	    <table width="1800px" border="1" align="center">
	    	<tr>
	    		<th height="30px">序号</th>
	    		<th height="30px">会话ID</th>
	    		<th height="30px">访问地址</th>
	    		<th height="30px">目标地址</th>
	    		<th height="30px">登入工号</th>
	    		<th height="30px">访问时间</th>
	    		<th height="30px">访问参数</th>
	    	</tr>
<%
RequestLogDAO requestLogDAO = new RequestLogDAO();
ArrayList<RequestLog> requestLogList = requestLogDAO.showRequestLog();
if (requestLogList!=null&&requestLogList.size()>0){
	for (int i=0;i<requestLogList.size();i++){
		RequestLog requestLog = requestLogList.get(i);
 %>
	    	<tr>
	    		<td height="60px" width="80px" align="center"><%=requestLog.getId() %></td>
	    		<td height="60px" width="280px" align="center"><%=requestLog.getSessionId() %></td>
	    		<td height="60px" width="180px" align="center"><%=requestLog.getIpAddress() %></td>
	    		<td height="60px" width="500px" align="center"><%=requestLog.getServerPath() %></td>
	    		<td height="60px" width="200px" align="center"><%=requestLog.getStaffId() %></td>
	    		<td height="60px" width="130px" align="center"><%=requestLog.getEffDateString() %></td>
	    		<td height="60px" align="center"><%=requestLog.getParameters() %></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>