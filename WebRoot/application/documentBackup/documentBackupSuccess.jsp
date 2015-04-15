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
    <h1>签报备份</h1>
   	<label>上传成功</label>
   	<form name="documentBackupUploadSuccess" action="<%=path%>/servlet.do" method="post" enctype="multipart/form-data">
    	<label for="target">选择上传文件：</label>
		<input type="submit" value="提交">
		<input type="hidden" name="operate" value="documentBackupUploadSuccess">
    </form>
  </body>
</html>