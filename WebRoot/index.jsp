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
	<link href="styles/index.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div class="main">
		<h1>Data Self-Access System</h1>
	</div>
	<div class="main">
		<h1>自助取数系统</h1>
	</div>
	<div class="main">
		<h3>当前在线人数：<%=request.getSession().getServletContext().getAttribute("currentUserNumber")%></h3>
	</div>
	<div class="main">
		<form name="login" action="<%=path%>/servlet.do" method="post">
    	<table>
    		<tr>
    			<td><label for="staffId">用户名：</label></td>
        		<td><input type="text" name="staffId" id="staffId" placeholder="请输入用户名"></td>
        		<td><input type="checkbox" name="remStaffId" id="remStaffId" value="remStaffId" checked="checked">
        			<label for="remStaffId">记住用户名</label></td>
        	</tr>
        	<tr>
        		<td><label for="password">密码：</label></td>
        		<td><input type="password" name="password" id="password" placeholder="请输入密码"></td>
        		<td>&nbsp;&nbsp;&nbsp;<a href="#">忘记密码</a></td>
        	</tr>
        	<tr>
        		<td><input type="submit" value="登录"></td>
        		<td><input type="reset" value="重置"></td>
        		<td><input type="hidden" name="operate" value="login"></td>
        	</tr>
        </table>
	    </form>
	</div>
	<div class="main">
		<h3>此系统为本地开发，用于一些简单的数据需求自助提取</h3><br>
		<h3>如有简单需求可在建议里写明</h3><br>
		<h4>本地人力财力有限，开发和更新系统较慢</h4><br>
		<h3>测试阶段请直接点击以下链接</h3><br>
		<h4><a href="<%=path%>/archive_odbc.jsp" target="_blank">查询原始档案</a><br><br>
		<a href="<%=path %>/qf_tz_odbc.jsp" target="_blank">查询欠费信息生成调账表</a></h4>
	</div>
  </body>
</html>
