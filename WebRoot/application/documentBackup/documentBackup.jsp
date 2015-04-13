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
    <form>
    <table>
    	<tr>
    		<td><label for="number1">属性1</label></td>
    		<td><input type="text" name="number1" id="number1"></td>
    		<td><label for="number2">属性2</label></td>
    		<td><input type="text" name="number2" id="number2"></td>
    		<td><label for="number3">属性3</label></td>
    		<td><input type="text" name="number3" id="number3"></td>
    	</tr>
    	<tr>
    		<td><label for="docName">签报名</label></td>
    		<td><input type="text" name="docName" id="docName"></td>
    		<td><label for="docStaff">签报人</label></td>
    		<td><input type="text" name="docStaff" id="docStaff"></td>
    		<td><label for="docDepartment">部门</label></td>
    		<td><input type="text" name="docDepartment" id="docDepartment"></td>
    	</tr>
    	<tr>
    		<td><label for="docStartYear">发起日期</label></td>
    		<td><input type="text" name="docStartYear" id="docStartYear"></td>
    		<td><label for="docStartYear">年</label></td>
    		<td><input type="text" name="docStartMonth" id="docStartMonth"></td>
    		<td><label for="docStartMonth">月</label></td>
    		<td><input type="text" name="docStartDay" id="docStartDay"></td>
    		<td><label for="docStartDay">日</label></td>
    	</tr>
    	<tr>
    		<td><label for="docDealtYear">处理日期</label></td>
    		<td><input type="text" name="docDealtYear" id="docDealtYear"></td>
    		<td><label for="docDealtYear">年</label></td>
    		<td><input type="text" name="docDealMonth" id="docDealMonth"></td>
    		<td><label for="docDealMonth">月</label></td>
    		<td><input type="text" name="docDealDay" id="docDealDay"></td>
    		<td><label for="docDealDay">日</label></td>
    	</tr>
    </table>
    	<label for="detail">签报正文	</label>
    	<textarea name="detail" id="detail" rows="10" cols="80"></textarea><br>
    	<input type="hidden" name="operate" value="documentBackupUpload">
    </form>
  </body>
</html>