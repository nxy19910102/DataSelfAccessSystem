<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="dao.SuggestDAO" %>
<%@ page import="entity.Suggest" %>
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
	    <h1 align=center id="table">网站建议记录</h1>
	    <table width="1200px" border="1" align="center">
	    	<tr>
	    		<th height="30px">序号</th>
	    		<th height="30px">工号</th>
	    		<th height="30px">路径</th>
	    		<th height="30px">建议描述</th>
	    		<th height="30px">状态</th>
	    		<th height="30px">提交时间</th>
	    		<th height="30px">审阅时间</th>
	    	</tr>
<%
SuggestDAO suggestDAO = new SuggestDAO();
ArrayList<Suggest> suggestList = suggestDAO.showSuggest();
if (suggestList!=null&&suggestList.size()>0){
	for (int i=0;i<suggestList.size();i++){
		Suggest suggest = suggestList.get(i);
 %>
	    	<tr>
	    		<td height="30px" align="center"><%=suggest.getId() %></td>
	    		<td height="30px" align="center"><%=suggest.getStaffId() %></td>
	    		<td height="30px" align="center"><%=suggest.getUrl() %></td>
	    		<td height="30px" align="center"><%=suggest.getDetail() %></td>
	    		<td height="30px" align="center"><%=suggest.getState() %></td>
	    		<td height="30px" align="center"><%=suggest.getEffDateString() %></td>
	    		<td height="30px" align="center"><%=suggest.getExpDateString() %></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>
