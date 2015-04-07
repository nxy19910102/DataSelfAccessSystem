<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="dao.ErrorDAO" %>
<%@ page import="entity.Error500" %>
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
  	<div id="table1">
	    <h1 align=center id="table">网站错误记录</h1>
	    <table width="1200px" border="1" align="center">
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
	    		<td height="30px" align="center"><%=error.getStaff_id() %></td>
	    		<td height="30px" align="center"><%=error.getUrl() %></td>
	    		<td height="30px" align="center"><%=error.getDetail() %></td>
	    		<td height="30px" align="center"><a href="#"><%=error.getState() %></a></td>
	    		<td height="30px" align="center"><%=error.getEff_dateString() %></td>
	    		<td height="30px" align="center"><%=error.getExp_dateString() %></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>
