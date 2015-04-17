<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="applicationDAO.DocumentBackupDAO" %>
<%@ page import="applicationEntity.DocumentBackup" %>
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
  				<input id="logout" type="submit" value="进入" style="display:none;">
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入" style="display:none;">
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
  	<div align="center">
  		<h1>报备查询</h1>
	    <table width="2500px" border="1" align="center">
	    	<tr height="45px">
	    		<th>序列号</th>
	    		<th>标题</th>
	    		<th>申请人</th>
	    		<th>发起日期</th>
	    		<th>正文</th>
	    		<th>附件</th>
	    	</tr>
<%
DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
ArrayList<DocumentBackup> documentBackupList = documentBackupDAO.showDocumentBackup();
if (documentBackupList!=null&&documentBackupList.size()>0){
	for (int i=0;i<documentBackupList.size();i++){
		DocumentBackup documentBackup = documentBackupList.get(i);
 %>
	    	<tr height="60px">
	    		<td width="80px" align="center"><%=documentBackup.getTargetString()%>(<%=documentBackup.getTargetYear()%>)</td>
	    		<td width="260px" align="center"><%=documentBackup.getTargetString()%></td>
	    		<td width="150px" align="center"><%=documentBackup.getTargetString()%></td>
	    		<td width="150px" align="center"><%=documentBackup.getTargetString()%></td>
	    		<td width="150px" align="center"><%=documentBackup.getTargetString()%></td>
	    		<td width="150px" align="center"><%=documentBackup.getTargetString()%></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>