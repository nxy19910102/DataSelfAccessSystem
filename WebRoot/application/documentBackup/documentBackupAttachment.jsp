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
    <form name="logout" action="<%=path%>/servlet.do" method="post">
		<label for="logout">登出</label>
		<input id="logout" type="submit" value="进入" style="display:none;">
		<input type="hidden" name="operate" value="logout">
	</form>
    <form name="backToApp" action="<%=path%>/servlet.do" method="post">
		<label for="backToApp">返回主目录</label>
		<input id="backToApp" type="submit" value="进入" style="display:none;">
		<input type="hidden" name="operate" value="backToApp">
	</form>
    <form name="documentBackupAttachmentUpload" action="<%=path%>/servlet.do" method="post" enctype="multipart/form-data">
    	<label for="target">选择上传文件：</label>
		<input type="submit" value="提交">
		<input type="hidden" name="operate" value="documentBackupAttachmentUpload">
    </form>
  </body>
</html>