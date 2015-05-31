<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="administrationDAO.ErrorDAO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String staffId = "none";
String detail = "none";
String serverPath = request.getServletPath();
if (session.getAttribute("staffId")!=null){
	staffId = (String) session.getAttribute("staffId");
}
if (request.getParameter("detail")!=""){
	detail = request.getParameter("detail");
}
ErrorDAO errorDAO =new ErrorDAO();
errorDAO.addError(staffId, serverPath, detail);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
	<link href="styles/index.css" rel="stylesheet" type="text/css">
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
  	<div class="main">
    	<h1>系统出错啦！</h1>
    	<h2>请联系管理员处理该问题或在下方留言，请写明工号，操作步骤等，尽量具体，以便管理员查错（500字以内）</h2>
    	<form name="error" method="post" action="<%=path%>/servlet.do">
    		<textarea name="detail" rows="10" cols="60" placeholder="请具体描述错误"></textarea><br>
    		<input type="submit" value="提交">
    		<input type="hidden" name="operate" value="error">
    	</form>
    </div>
  </body>
</html>