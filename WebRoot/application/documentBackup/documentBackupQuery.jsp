<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="applicationDAO.DocumentBackupDAO" %>
<%@ page import="applicationEntity.DocumentBackup" %>
<%
String targetYear = "";
String targetSeq = "";
String docTitle = "";
String docStaff = "";
String docDepartment = "";
String startYear = "";
String startMonth = "";
String startDay = "";
String dealYear = "";
String dealMonth = "";
String dealDay = "";
if (request.getParameter("targetYear") != null){
	targetYear = request.getParameter("targetYear");
}
if (request.getParameter("targetSeq") != null){
	targetSeq = request.getParameter("targetSeq");
}
if (request.getParameter("docTitle") != null){
	docTitle = request.getParameter("docTitle");
}
if (request.getParameter("docStaff") != null){
	docStaff = request.getParameter("docStaff");
}
if (request.getParameter("docDepartment") != null){
	docDepartment = request.getParameter("docDepartment");
}
if (request.getParameter("startYear") != null){
	startYear = request.getParameter("startYear");
}
if (request.getParameter("startMonth") != null){
	startMonth = request.getParameter("startMonth");
}
if (request.getParameter("startDay") != null){
	startDay = request.getParameter("startDay");
}
if (request.getParameter("dealYear") != null){
	dealYear = request.getParameter("dealYear");
}
if (request.getParameter("dealMonth") != null){
	dealMonth = request.getParameter("dealMonth");
}
if (request.getParameter("dealDay") != null){
	dealDay = request.getParameter("dealDay");
}

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String attachmentAddress = "";
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
  		<h3>请输入模糊查询条件</h3>
  		<form name="documentBackupQuery" action="<%=path%>/servlet.do" method="post">
	    <table>
	    	<tr>
	    		<td><label for="targetYear">序列号</label></td>
	    		<td><input type="text" name="targetYear" id="targetYear" value="<%=targetYear%>"></td>
	    		<td><label for="targetYear">年</label></td>
	    		<td><input type="text" name="targetSeq" id="targetSeq" value="<%=targetSeq%>"></td>
	    		<td><label for="targetSeq">号</label></td>
	    	</tr>
	    	<tr>
	    		<td><label for="docTitle">标题</label></td>
	    		<td><input type="text" name="docTitle" id="docTitle" value="<%=docTitle%>"></td>
	    		<td><label for="docStaff">申请人</label></td>
	    		<td><input type="text" name="docStaff" id="docStaff" value="<%=docStaff%>"></td>
	    		<td><label for="docDepartment">部门</label></td>
	    		<td><input type="text" name="docDepartment" id="docDepartment"></td>
	    	</tr>
	    	<tr>
	    		<td><label for="startYear">发起日期</label></td>
	    		<td><input type="text" name="startYear" id="startYear" value="<%=startYear%>"></td>
	    		<td><label for="startYear">年</label></td>
	    		<td><input type="text" name="startMonth" id="startMonth" value="<%=startMonth%>"></td>
	    		<td><label for="startMonth">月</label></td>
	    		<td><input type="text" name="startDay" id="startDay" value="<%=startDay%>"></td>
	    		<td><label for="startDay">日</label></td>
	    	</tr>
	    	<tr>
	    		<td><label for="dealYear">处理日期</label></td>
	    		<td><input type="text" name="dealYear" id="dealYear" value="<%=dealYear%>"></td>
	    		<td><label for="dealYear">年</label></td>
	    		<td><input type="text" name="dealMonth" id="dealMonth" value="<%=dealMonth%>"></td>
	    		<td><label for="dealMonth">月</label></td>
	    		<td><input type="text" name="dealDay" id="dealDay" value="<%=dealDay%>"></td>
	    		<td><label for="dealDay">日</label></td>
	    	</tr>
	    </table>
	    <input type="submit" value="开始查询">
	    <input type="hidden" name="operate" value="documentBackupQuery">
	    	
	    <table width="1800px" border="1" align="center">
	    	<tr height="30px">
	    		<th>序列号</th>
	    		<th>申请人</th>
	    		<th>发起日期</th>
	    		<th>处理日期</th>
	    		<th>标题</th>
	    		<th>附件</th>
	    	</tr>
<%
DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
ArrayList<DocumentBackup> documentBackupList = documentBackupDAO.showDocumentBackup(targetYear, targetSeq, docTitle, docStaff, docDepartment, startYear, startMonth, startDay, dealYear, dealMonth, dealDay);
if (documentBackupList!=null&&documentBackupList.size()>0){
	for (int i=0;i<documentBackupList.size();i++){
		DocumentBackup documentBackup = documentBackupList.get(i);
		if (documentBackup.getAttachmentAddress() != null) {
			attachmentAddress = "下载附件";
		}
 %>
	    	<tr height="30px" align="center">
	    		<td width="250px"><%=documentBackup.getTargetString()%>(<%=documentBackup.getTargetYear()%>)第<%=documentBackup.getTargetSeq()%>号</td>
	    		<td width="250px"><%=documentBackup.getDocDepartment()%>-<%=documentBackup.getDocStaff()%></td>
	    		<td width="150px"><%=documentBackup.getStartYear()%>年<%=documentBackup.getStartMonth()%>月<%=documentBackup.getStartDay()%>日</td>
	    		<td width="150px"><%=documentBackup.getDealYear()%>年<%=documentBackup.getDealMonth()%>月<%=documentBackup.getDealDay()%>日</td>
	    		<td><%=documentBackup.getDocTitle()%></td>
	    		<td width="100px"><a href="<%=path%>/servlet.download?realPath=<%=documentBackup.getAttachmentAddress()%>" target="_blank"><%=attachmentAddress%></a></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>