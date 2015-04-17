<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="administrationDAO.StaffDAO" %>
<%@ page import="administrationEntity.Staff" %>
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
    <h1 align="center">员工查询</h1>
    <table width="1800" height="100" border="1" align="center">
    	<tr>
    		<th>序列号</th>
    		<th>工号</th>
    		<th>姓名</th>
    		<th>部门</th>
    		<th>状态</th>
    		<th>添加人</th>
    		<th>添加日期</th>
    		<th>生效日期</th>
    		<th>失效日期</th>
    	</tr>
<%
StaffDAO staffDAO = new StaffDAO();
ArrayList<Staff> StaffList = staffDAO.getStaffByStaffId( (String) request.getParameter("staffId"));
if (StaffList!=null&&StaffList.size()>0){
	for (int i=0;i<StaffList.size();i++){
		Staff staff = StaffList.get(i);
 %>
    	<tr>
    		<td height="30px" align="center"><%=staff.getId() %></td>
    		<td height="30px" align="center"><%=staff.getStaffId() %></td>
    		<td height="30px" align="center"><%=staff.getStaffName() %></td>
    		<td height="30px" align="center"><%=staff.getDepartment() %></td>
    		<td height="30px" align="center"><%=staff.getStateString() %></td>
    		<td height="30px" align="center"><%=staff.getUpdateUser() %></td>
    		<td height="30px" align="center"><%=staff.getUpdateDateString() %></td>
    		<td height="30px" align="center"><%=staff.getEffDateString() %></td>
    		<td height="30px" align="center"><%=staff.getExpDateString() %></td>
    	</tr>
<%
	}
}	
 %>
     </table>
  </body>
</html>
