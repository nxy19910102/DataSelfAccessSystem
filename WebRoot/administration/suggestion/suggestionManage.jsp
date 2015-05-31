<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="administrationDAO.SuggestDAO" %>
<%@ page import="administrationEntity.Suggest" %>
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
  				<input id="logout" type="submit" value="进入"<%-- style="display:none;"--%>>
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入"<%-- style="display:none;"--%>>
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
  	<div id="table1">
	    <h1 align=center id="table">网站建议记录</h1>
	    <table width="1800px" border="1" align="center">
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
	    		<td height="30px" align="center"><%=suggest.getStateString() %></td>
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