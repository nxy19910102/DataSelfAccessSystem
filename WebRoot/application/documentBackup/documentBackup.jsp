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
    <form name="documentBackupUpload" action="<%=path%>/servlet.do" method="post">
    <table>
    	<tr>
    		<td><label for="target">类别</label></td>
    		<td><input type="text" name="target" id="target"></td>
    		<td><label for="targetYear">序列号</label></td>
    		<td><input type="text" name="targetYear" id="targetYear"></td>
    		<td><label for="targetYear">年</label></td>
    		<td><input type="text" name="targetSeq" id="targetSeq"></td>
    		<td><label for="targetSeq">号</label></td>
    	</tr>
    	<tr>
    		<td><label for="docTitle">标题</label></td>
    		<td><input type="text" name="docTitle" id="docTitle"></td>
    		<td><label for="docStaff">申请人</label></td>
    		<td><input type="text" name="docStaff" id="docStaff"></td>
    		<td><label for="docDepartment">部门</label></td>
    		<td><input type="text" name="docDepartment" id="docDepartment"></td>
    	</tr>
    	<tr>
    		<td><label for="startYear">发起日期</label></td>
    		<td><input type="text" name="startYear" id="startYear"></td>
    		<td><label for="startYear">年</label></td>
    		<td><input type="text" name="startMonth" id="startMonth"></td>
    		<td><label for="startMonth">月</label></td>
    		<td><input type="text" name="startDay" id="startDay"></td>
    		<td><label for="startDay">日</label></td>
    	</tr>
    	<tr>
    		<td><label for="dealYear">处理日期</label></td>
    		<td><input type="text" name="dealYear" id="dealYear"></td>
    		<td><label for="dealYear">年</label></td>
    		<td><input type="text" name="dealMonth" id="dealMonth"></td>
    		<td><label for="dealMonth">月</label></td>
    		<td><input type="text" name="dealDay" id="dealDay"></td>
    		<td><label for="dealDay">日</label></td>
    	</tr>
    </table>
    	<label for="docDetail">正文</label>
    	<textarea name="docDetail" id="docDetail" rows="10" cols="80"></textarea><br>
    	<label>是否含有附件:</label>
    	<label for="attachment1">是</label>
    	<input type="radio" name="attachment" id="attachment1" value="1">
    	<label for="attachment0">否</label>
    	<input type="radio" name="attachment" id="attachment0" value="0" checked="checked">
    	<input type="hidden" name="operate" value="documentBackupUpload"></br>
    	<label>**附件在提交后选择</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
    	<input type="submit" value="提交">
    </form>
  </body>
</html>