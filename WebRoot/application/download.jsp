<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String download = "";
if (request.getAttribute("download") != null){
	download = (String) request.getAttribute("download");
}
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Data Self-Access System</title>
  </head>
  
  <body>
  	<p>下载测试</p>
  	<a href="<%=path%>/servlet/DownloadServlet?filename=test1.txt">下载测试</a><%=download%>
  </body>
</html>
