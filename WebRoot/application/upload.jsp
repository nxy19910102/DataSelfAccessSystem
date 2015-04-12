<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String upload = "";
if (request.getAttribute("upload") != null){
	upload = (String) request.getAttribute("upload");
}
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
  </head>
  
  <body>
  	<p>上传测试</p>
  	<form action="<%=path%>/servlet/UploadServlet" method="post" enctype="multipart/form-data">
  		选择上传文件：<input id="uploadFile" name="uploadFile" type="file">
  		<input type="submit" value="提交"><%=upload %>
  	</form>
  </body>	
</html>
