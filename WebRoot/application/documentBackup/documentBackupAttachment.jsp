<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uploadKey = request.getParameter("uploadKey");
String docDepartment = request.getParameter("docDepartment");
String docStaff = request.getParameter("docStaff");
String target = request.getParameter("target");
String targetYear = request.getParameter("targetYear");
String targetSeq = request.getParameter("targetSeq");
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
  				<input id="logout" type="submit" value="进入" style="display:none;">
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入" style="display:none;">
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
    <h1>业务报备</h1>
    <form name="documentBackupAttachmentUpload" action="<%=path%>/servlet.upload?uploadKey=<%=uploadKey%>&docDepartment=<%=docDepartment%>&docStaff=<%=docStaff%>&target=<%=target%>&targetYear=<%=targetYear%>&targetSeq=<%=targetSeq%>" method="post" enctype="multipart/form-data">
    	<label for="target">选择上传文件：</label>
    	<input name="uploadFile" type="file">
		<input type="submit" value="提交">
    </form>
  </body>
</html>