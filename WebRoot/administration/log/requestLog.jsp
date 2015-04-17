<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="administrationDAO.RequestLogDAO" %>
<%@ page import="administrationEntity.RequestLog" %>
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
  				<input id="logout" type="submit" value="进入" style="display:none;">
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入" style="display:none;">
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
  	<div align="center">
  		<h1>访问日志</h1>
  		<div>
  			<form name="sessionLog" action="<%=path%>/servlet.do" method="post">
  				<label for="sessionLog">会话日志</label>
  				<input id="sessionLog" type="submit" value="切换">
  				<input type="hidden" name="operate" value="sessionLog">
  			</form>
  		</div>
	    <table width="2500px" border="1" align="center">
	    	<tr height="45px">
	    		<th>序号</th>
	    		<th>会话ID</th>
	    		<th>访问地址</th>
	    		<th>目标地址</th>
	    		<th>登入工号</th>
	    		<th>访问时间</th>
	    		<th>访问参数</th>
	    	</tr>
<%
RequestLogDAO requestLogDAO = new RequestLogDAO();
ArrayList<RequestLog> requestLogList = requestLogDAO.showRequestLog();
if (requestLogList!=null&&requestLogList.size()>0){
	for (int i=0;i<requestLogList.size();i++){
		RequestLog requestLog = requestLogList.get(i);
 %>
	    	<tr height="60px">
	    		<td width="80px" align="center"><%=requestLog.getId()%></td>
	    		<td width="260px" align="center"><%=requestLog.getSessionId()%></td>
	    		<td width="150px" align="center"><%=requestLog.getIpAddress()%></td>
	    		<td width="150px" align="center"><%=requestLog.getServerPath()%></td>
	    		<td width="150px" align="center"><%=requestLog.getStaffId()%></td>
	    		<td width="150px" align="center"><%=requestLog.getEffDateString()%></td>
	    		<td align="center"><%=requestLog.getParameters()%></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>