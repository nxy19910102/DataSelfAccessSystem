<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
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
  				<input id="logout" type="submit" value="进入"<%-- style="display:none;"--%>>
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入"<%-- style="display:none;"--%>>
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
	<label>上传成功!</label>
  </body>
</html>