<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.StaffDAO" %>
<%@ page import="entity.Staff" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Data Self-Access System</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<%
//request.setCharacterEncoding("utf-8"); 
//response.setContentType("text/html; charset=utf-8");
 %>
    <h1 align="center">员工查询</h1>
    <table width="1200" height="100" border="1" align="center">
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
    		<th>权限</th>
    	</tr>
<%
StaffDAO staffDAO = new StaffDAO();
ArrayList<Staff> StaffList = staffDAO.getStaffByStaff_id(request.getParameter("staff_id"));
if (StaffList!=null&&StaffList.size()>0){
	for (int i=0;i<StaffList.size();i++){
		Staff staff = StaffList.get(i);
 %>
    	<tr>
    		<td height="30px" align="center"><%=staff.getId() %></td>
    		<td height="30px" align="center"><%=staff.getStaff_id() %></td>
    		<td height="30px" align="center"><%=staff.getStaff_name() %></td>
    		<td height="30px" align="center"><%=staff.getDepartment() %></td>
    		<td height="30px" align="center"><%=staff.getState() %></td>
    		<td height="30px" align="center"><%=staff.getUpdate_user() %></td>
    		<td height="30px" align="center"><%=staff.getUpdate_date() %></td>
    		<td height="30px" align="center"><%=staff.getEff_date() %></td>
    		<td height="30px" align="center"><%=staff.getExp_date() %></td>
    		<td height="30px" align="center"><%=staff.getIs_admin() %></td>
    	</tr>
<%
	}
}	
 %>
     </table>
  </body>
</html>
