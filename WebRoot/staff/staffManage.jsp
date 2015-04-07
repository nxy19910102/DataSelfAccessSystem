<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
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
    <style></style>
  </head>
  <body>
    <h1 align="center">员工查询</h1>
    <table width="1200px" border="1" align="center">
    	<tr>
    		<th height="30px">工号</th>
    		<th height="30px">姓名</th>
    		<th height="30px">部门</th>
    		<th height="30px">查看详情</th>
    	</tr>
<%
StaffDAO staffDAO = new StaffDAO();
ArrayList<Staff> staffList = staffDAO.showStaff();
if (staffList!=null&&staffList.size()>0){
	for (int i=0;i<staffList.size();i++){
		Staff staff = staffList.get(i);
 %>
    	<tr>
    		<td height="30px" align="center"><%=staff.getStaff_id() %></td>
    		<td height="30px" align="center"><%=staff.getStaff_name() %></td>
    		<td height="30px" align="center"><%=staff.getDepartment() %></td>
    		<td height="30px" align="center"><a href="staff/staffDetail.jsp?staff_id=<%=staff.getStaff_id() %>">查看详情</a></td>
    	</tr>
<%
	}
}	
 %>
     </table>
  </body>
</html>
