<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
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
	<div>
    <h1 align="center">员工查询</h1>
    <table width="1800px" border="1" align="center">
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
    		<td height="30px" align="center"><%=staff.getStaffId() %></td>
    		<td height="30px" align="center"><%=staff.getStaffName() %></td>
    		<td height="30px" align="center"><%=staff.getDepartment() %></td>
    		<td height="30px" align="center">
    			<form name="staffDetail" action="<%=path%>/servlet.do" method="post">
  					<input type="submit" value="查看详情">
  					<input type="hidden" name="operate" value="staffDetail">
  					<input type="hidden" name="staffId" value="<%=staff.getStaffId()%>">
  				</form>
  			</td>
    	</tr>
<%
	}
}	
 %>
     </table>
     </div>
  </body>
</html>
