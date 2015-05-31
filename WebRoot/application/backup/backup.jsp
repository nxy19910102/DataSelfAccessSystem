<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Calendar calendar = Calendar.getInstance();
Date date = calendar.getTime();
int yearLast = date.getYear() + 1900 - 1;
int year = date.getYear() + 1900;
int yearNext = date.getYear() + 1900 + 1;
int yearNext2 = date.getYear() + 1900 + 2;
int yearNext3 = date.getYear() + 1900 + 3;
int yearNext4 = date.getYear() + 1900 + 4;
int yearNext5 = date.getYear() + 1900 + 5;
String month[] = new String[12];
String day[] = new String[31];
for (int i=0;i<12;i++){
	if (date.getMonth() == i){
		month[i] = " selected=\"selected\"";
	} else {
		month[i] = "";
	}
}
for (int i=0;i<31;i++){
	if (date.getDate() == i){
		day[i] = "selected=\"selected\"";
	} else {
		day[i] = "";
	}
}
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
  				<input id="logout" type="submit" value="进入"<%-- style="display:none;"--%>>
  				<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入"<%-- style="display:none;"--%>>
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
    <h1>备案管理</h1>
    <form name="documentBackupUpload" action="<%=path%>/servlet.do" method="post">
    <table>
    	<tr>
    		<td><label for="target">类别</label></td>
    		<td><select name="target" id="target">
    				<option value="0" selected="selected">请选择</option>
    				<option value="1">签报</option>
    				<option value="2">内部联系单</option>
    			</select></td>
    		<td><label for="targetYear">序列号</label></td>
    		<td><select name="targetYear" id="targetYear">
    				<option value="<%=yearLast%>"><%=yearLast%></option>
    				<option value="<%=year%>" selected="selected"><%=year%></option>
    			</select></td>
    		<td><label for="targetYear">年</label></td>
    		<td><input type="text" name="targetSeq" id="targetSeq"></td>
    		<td><label for="targetSeq">号</label></td>
    	</tr>
    	<tr>
    		<td><label for=applyStaff>申请责任人</label></td>
    		<td><input type="text" name="applyStaff" id="applyStaff"></td>
    		<td><label for="approveStaff">审批责任人</label></td>
    		<td><input type="text" name="approveStaff" id="approveStaff"></td></td>
    		<td><label for="accNbr">业务号码</label></td>
    		<td><input type="text" name="accNbr" id="accNbr"></td>
    		<td></td>
    	</tr>
    	<tr>
    		<td><label for="offerOld">原套餐</label></td>
    		<td><input type="text" name="offerOld" id="offerOld"></td>
    		<td><label for="offerNew">新套餐</label></td>
    		<td><input type="text" name=""offerNew"" id=""offerNew""></td>
    		<td><label for="promotion">优惠金额</label></td>
    		<td><input type="text" name="promotion" id="promotion"></td>
    		<td><label for="promotion">/月</label></td>
    	</tr>
    	<tr>
    		<td><label for="effYear">生效时间</label></td>
    		<td><select name="effYear" id="effYear">
    				<option value="<%=yearLast%>"><%=yearLast%></option>
    				<option value="<%=year%>" selected="selected"><%=year%></option>
    				<option value="<%=yearNext%>"><%=yearNext%></option>
    			</select></td>
    		<td><label for="effYear">年</label></td>
    		<td><select name="effMonth" id="effMonth">
    				<option value="01"<%=month[0]%>>1</option>
    				<option value="02"<%=month[1]%>>2</option>
    				<option value="03"<%=month[2]%>>3</option>
    				<option value="04"<%=month[3]%>>4</option>
    				<option value="05"<%=month[4]%>>5</option>
    				<option value="06"<%=month[5]%>>6</option>
    				<option value="07"<%=month[6]%>>7</option>
    				<option value="08"<%=month[7]%>>8</option>
    				<option value="09"<%=month[8]%>>9</option>
    				<option value="10"<%=month[9]%>>10</option>
    				<option value="11"<%=month[10]%>>11</option>
    				<option value="12"<%=month[11]%>>12</option>
    			</select></td>
    		<td><label for="effMonth">月</label></td>
    		<td><select name="effDay" id="effDay">
    				<option value="01"<%=day[0]%>>1</option>
    				<option value="02"<%=day[1]%>>2</option>
    				<option value="03"<%=day[2]%>>3</option>
    				<option value="04"<%=day[3]%>>4</option>
    				<option value="05"<%=day[4]%>>5</option>
    				<option value="06"<%=day[5]%>>6</option>
    				<option value="07"<%=day[6]%>>7</option>
    				<option value="08"<%=day[7]%>>8</option>
    				<option value="09"<%=day[8]%>>9</option>
    				<option value="10"<%=day[9]%>>10</option>
    				<option value="11"<%=day[10]%>>11</option>
    				<option value="12"<%=day[11]%>>12</option>
    				<option value="13"<%=day[12]%>>13</option>
    				<option value="14"<%=day[13]%>>14</option>
    				<option value="15"<%=day[14]%>>15</option>
    				<option value="16"<%=day[15]%>>16</option>
    				<option value="17"<%=day[16]%>>17</option>
    				<option value="18"<%=day[17]%>>18</option>
    				<option value="19"<%=day[18]%>>19</option>
    				<option value="20"<%=day[19]%>>20</option>
    				<option value="21"<%=day[20]%>>21</option>
    				<option value="22"<%=day[21]%>>22</option>
    				<option value="23"<%=day[22]%>>23</option>
    				<option value="24"<%=day[23]%>>24</option>
    				<option value="25"<%=day[24]%>>25</option>
    				<option value="26"<%=day[25]%>>26</option>
    				<option value="27"<%=day[26]%>>27</option>
    				<option value="28"<%=day[27]%>>28</option>
    				<option value="29"<%=day[28]%>>29</option>
    				<option value="30"<%=day[29]%>>30</option>
    				<option value="31"<%=day[30]%>>31</option>
    			</select></td>
    		<td><label for="effDay">日</label></td>
    	</tr>
    	<tr>
    		<td><label for="expYear">失效时间</label></td>
    		<td><select name="expYear" id="expYear">
    				<option value="<%=year%>" selected="selected"><%=year%></option>
    				<option value="<%=yearNext%>"><%=yearNext%></option>
    				<option value="<%=yearNext2%>"><%=yearNext2%></option>
    				<option value="<%=yearNext3%>"><%=yearNext3%></option>
    				<option value="<%=yearNext4%>"><%=yearNext4%></option>
    				<option value="<%=yearNext5%>"><%=yearNext5%></option>
    			</select></td>
    		<td><label for="expYear">年</label></td>
    		<td><select name="expMonth" id="expMonth">
    				<option value="01"<%=month[0]%>>1</option>
    				<option value="02"<%=month[1]%>>2</option>
    				<option value="03"<%=month[2]%>>3</option>
    				<option value="04"<%=month[3]%>>4</option>
    				<option value="05"<%=month[4]%>>5</option>
    				<option value="06"<%=month[5]%>>6</option>
    				<option value="07"<%=month[6]%>>7</option>
    				<option value="08"<%=month[7]%>>8</option>
    				<option value="09"<%=month[8]%>>9</option>
    				<option value="10"<%=month[9]%>>10</option>
    				<option value="11"<%=month[10]%>>11</option>
    				<option value="12"<%=month[11]%>>12</option>
    			</select></td>
    		<td><label for="expMonth">月</label></td>
    		<td><select name="expDay" id="expDay">
    				<option value="01"<%=day[0]%>>1</option>
    				<option value="02"<%=day[1]%>>2</option>
    				<option value="03"<%=day[2]%>>3</option>
    				<option value="04"<%=day[3]%>>4</option>
    				<option value="05"<%=day[4]%>>5</option>
    				<option value="06"<%=day[5]%>>6</option>
    				<option value="07"<%=day[6]%>>7</option>
    				<option value="08"<%=day[7]%>>8</option>
    				<option value="09"<%=day[8]%>>9</option>
    				<option value="10"<%=day[9]%>>10</option>
    				<option value="11"<%=day[10]%>>11</option>
    				<option value="12"<%=day[11]%>>12</option>
    				<option value="13"<%=day[12]%>>13</option>
    				<option value="14"<%=day[13]%>>14</option>
    				<option value="15"<%=day[14]%>>15</option>
    				<option value="16"<%=day[15]%>>16</option>
    				<option value="17"<%=day[16]%>>17</option>
    				<option value="18"<%=day[17]%>>18</option>
    				<option value="19"<%=day[18]%>>19</option>
    				<option value="20"<%=day[19]%>>20</option>
    				<option value="21"<%=day[20]%>>21</option>
    				<option value="22"<%=day[21]%>>22</option>
    				<option value="23"<%=day[22]%>>23</option>
    				<option value="24"<%=day[23]%>>24</option>
    				<option value="25"<%=day[24]%>>25</option>
    				<option value="26"<%=day[25]%>>26</option>
    				<option value="27"<%=day[26]%>>27</option>
    				<option value="28"<%=day[27]%>>28</option>
    				<option value="29"<%=day[28]%>>29</option>
    				<option value="30"<%=day[29]%>>30</option>
    				<option value="31"<%=day[30]%>>31</option>
    			</select></td>
    		<td><label for="expDay">日</label></td>
    	</tr>
    </table>
    	<label for="note">备注事由</label>
    	<textarea name="note" id="note" rows="10" cols="80"></textarea><br>
    	<label>是否含有附件:</label>
    	<label for="attachment1">是</label>
    	<input type="radio" name="attachment" id="attachment1" value="1">
    	<label for="attachment0">否</label>
    	<input type="radio" name="attachment" id="attachment0" value="0" checked="checked">
    	<input type="hidden" name="operate" value="backupUpload"><br>
    	<label>**附件在提交后选择</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="submit" value="提交">
    </form>
  </body>
</html>