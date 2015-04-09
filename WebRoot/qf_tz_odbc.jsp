<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="util.DBConnect" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<html>
<head>
<title>查询欠费信息生成调账表</title>
</head>
<body>
<CENTER>
<FONT SIZE=5 COLOR=blue>查询欠费信息生成调账表</FONT>
</CENTER>
<BR>
<HR>
<BR>

</label>
  <p>&nbsp;</p>
    <form name="form1" method="post" action="">
      <label>
        <div align="center">输入用户实例ID
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
	<td>区号</td>
	<td>号码</td>
	<td>合同号</td>
	<td>账目ID</td>
	<td>账务周期</td>
	<td>调整金额</td>
	<td>产品ID</td>
	<td>欠费分类</td>
	<td>用户名称</td>
	<td>账目名称</td>
</tr>
<%
String my_sql1 = "select decode(substr(b.area_id,6,1),'2','0992','3','0992','0990'),a.acc_nbr,b.acct_id,b.acct_item_type_id,'20'||substr(billing_cycle_id,3,4),";
String my_sql2 = "      -sum(amount-balance_paid),b.product_id,decode(nvl(pseudo_flag,0),1,'内拆','正常'),a.user_name,c.name";
String my_sql3 = " from (select serv_id,max(acc_nbr) acc_nbr,max(user_name) user_name from qhb_gdk_auto1 where serv_id="+request.getParameter("text_acc_nbr")+" group by serv_id) a,";
String my_sql4 = "     v_nbil_acct_owe_day@odscpy b,v_nbil_acctitem_type_day@odscpy c";
String my_sql5 = " where a.serv_id=b.serv_id and b.state='5JA' and nvl(b.bill_id,0)<>-1000 and b.acct_item_type_id=c.acct_item_type_id and b.serv_id="+request.getParameter("text_acc_nbr");
String my_sql6 = " group by decode(substr(b.area_id,6,1),'2','0992','3','0992','0990'),a.acc_nbr,b.acct_id,b.acct_item_type_id,";
String my_sql7 = "        '20'||substr(billing_cycle_id,3,4),b.product_id,decode(nvl(pseudo_flag,0),1,'内拆','正常'),a.user_name,c.name";
String my_sql8 = " having sum(amount-balance_paid)<>0 order by 5";

String my_sql = my_sql1 + my_sql2 + my_sql3 + my_sql4 + my_sql5 + my_sql6 + my_sql7 + my_sql8;
String text_v = request.getParameter("text_acc_nbr")+"";
int changdu_u = 5;
changdu_u = text_v.length();

Connection con = new DBConnect().getConnection();
//创建一个JDBC声明
Statement stmt = con.createStatement();

if (changdu_u==12)
  {
  my_sql = my_sql1 + my_sql2 + my_sql3 + my_sql4 + my_sql5 + my_sql6 + my_sql7 + my_sql8;
  }
if (changdu_u==0)
  {
  my_sql = my_sql1 + my_sql2 + my_sql3 + my_sql4 + my_sql5 + " and 1=2" + my_sql6 + my_sql7 + my_sql8;
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
		<td><%=rs.getString(4)%></td>
		<td><%=rs.getString(5)%></td>
		<td><%=rs.getString(6)%></td>
		<td><%=rs.getString(7)%></td>
		<td><%=rs.getString(8)%></td>
		<td><%=rs.getString(9)%></td>
		<td><%=rs.getString(10)%></td>
  </tr>
<%
}
// 关闭数据库连接
rs.close();
%>
</table>
</body>
</html>
