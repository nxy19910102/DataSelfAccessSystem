<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="util.DBConnect" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<html>
<head>
<title>查询原始档案</title>
</head>
<body>
<CENTER>
<FONT SIZE=5 COLOR=blue>查询原始档案</FONT>
</CENTER>
<BR>
<HR>
<BR>

</label>
  <p>&nbsp;</p>
    <form name="form1" method="post" action="">
      <label>
        <div align="center">输入号码
         <input type="text" name="text_acc_nbr" size="25" value="">
        </div>
     <label></label>

     <div align="center"> 
     <input type="submit" name="Submit" value="提交">
      <label>
      <input type="submit" name="Submit2" value="撤销">
      </label>
      </div>
    </form>
 <p align="center">&nbsp;</p>
<table border=1 align="center">
<tr>
	<td>录入工号</td>
	<td>号码</td>
	<td>业务</td>
	<td>柜号</td>
	<td>册号</td>
	<td>册内编号</td>
	<td>录入日期</td>
	<td>备注</td>
</tr>
<%
String my_sql = "select c.staff_name,a.tel,b.code_name,a.cup_no,a.pack_no,a.no,a.in_da_ti,a.memo1 from archives.tel a,archives.code_name b,archives.staff c where a.tel_code=b.tel_code and a.staff_id=c.staff_id and a.tel like '%"+request.getParameter("text_acc_nbr")+"%'";
String text_v = request.getParameter("text_acc_nbr")+"";
int changdu_u = 5;
changdu_u = text_v.length();

Connection con = new DBConnect().getConnection();
//创建一个JDBC声明
Statement stmt = con.createStatement();

if (changdu_u==0)
  {
  my_sql = my_sql + " and 1=2";
  }
//查询记录
ResultSet rs = stmt.executeQuery(my_sql);
//输出查询结果
while(rs.next()) 
{
%>
<tr>
		<td><%=rs.getString(1)%></td>
		<td><%=rs.getString(2)%></td>
		<td><%=rs.getString(3)%></td>
		<td><%=rs.getInt(4)%></td>
		<td><%=rs.getInt(5)%></td>
		<td><%=rs.getInt(6)%></td>
		<td><%=rs.getString(7)%></td>
		<td><%=rs.getString(8)%></td>
  </tr>
<%
}
// 关闭数据库连接
rs.close();
%>
</table>
</body>
</html>
