<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="administrationDAO.ErrorDAO" %>
<%@ page import="administrationEntity.Error500" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
    <link href="<%=path%>/styles/errorManage.css" rel="stylesheet" type="text/css">
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
  	<div>
	    <h1 align=center id="table">网站错误记录</h1>
	    <table width="1800px" border="1" align="center">
	    	<tr>
	    		<th height="30px">序号</th>
	    		<th height="30px">工号</th>
	    		<th height="30px">路径</th>
	    		<th height="30px">错误描述</th>
	    		<th height="30px">状态</th>
	    		<th height="30px">出现时间</th>
	    		<th height="30px">解决时间</th>
	    	</tr>
<%
ErrorDAO errorDAO = new ErrorDAO();
ArrayList<Error500> errorList = errorDAO.showError();
if (errorList!=null&&errorList.size()>0){
	for (int i=0;i<errorList.size();i++){
		Error500 error = errorList.get(i);
 %>
	    	<tr>
	    		<td height="30px" align="center"><%=error.getId() %></td>
	    		<td height="30px" align="center"><%=error.getStaffId() %></td>
	    		<td height="30px" align="center"><%=error.getUrl() %></td>
	    		<td height="30px" align="center"><%=error.getDetail() %></td>
	    		<td height="30px" align="center"><%=error.getStateString() %></td>
	    		<td height="30px" align="center"><%=error.getEffDateString() %></td>
	    		<td height="30px" align="center"><%=error.getExpDateString() %></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>
