<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="administrationDAO.SuggestDAO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
	<link href="styles/index.css" rel="stylesheet" type="text/css">
  </head>
<%
String staffId = "none";
String detail = "none";
String remoteAddr = request.getRemoteAddr();
if (session.getAttribute("staffId")!=null){
	staffId = (String) session.getAttribute("staffId");
}
if (request.getParameter("detail")!=""){
	detail = request.getParameter("detail");
}
SuggestDAO suggestDAO = new SuggestDAO();
suggestDAO.addSuggest(staffId, remoteAddr, detail);
 %>
  <body>
  	<div class="main">
    	<h1></h1>
    	<h2>请在下方写上您的建议，并附上工号，联系电话等信息以便沟通（500字以内）</h2>
    	<form name="suggestionUpload" action="<%=path%>/servlet.do" method="post">
    		<textarea name="detail" rows="10" cols="60" placeholder="您的建议"></textarea><br>
    		<input type="submit" value="提交">
    		<input type="hidden" name="operate" value="suggestionUpload">
    	</form>
    </div>
  </body>
</html>