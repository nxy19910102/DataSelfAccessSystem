<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="applicationDAO.DocumentBackupDAO" %>
<%@ page import="applicationEntity.DocumentBackup" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String attachmentAddress = "";
Date dateNew = new Date();
int year = dateNew.getYear() + 1900;
int yearLast = dateNew.getYear() + 1900 - 1;
String month[] = new String[12];
String date[] = new String[31];
for (int i=0;i<12;i++){
	if (dateNew.getMonth() == i){
		month[i] = "selected=\"selected\"";
	} else {
		month[i] = "";
	}
}
for (int i=0;i<31;i++){
	if (dateNew.getDate() == i){
		date[i] = "selected=\"selected\"";
	} else {
		date[i] = "";
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
			<input id="logout" type="submit" value="进入" style="display:none;">
			<input type="hidden" name="operate" value="logout">
		</form>
		<form name="backToApp" action="<%=path%>/servlet.do" method="post">
			<label for="backToApp">返回主菜单</label>
			<input id="backToApp" type="submit" value="进入" style="display:none;">
			<input type="hidden" name="operate" value="backToApp">
		</form>
	</div>
  	<div align="center">
  		<h1>报备查询</h1>
  		<h3>请输入以下条件匹配查询</h3>
  		<form name="documentBackupQueryPart" action="<%=path%>/servlet.do" method="post">
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
	    				<option value="<%=year%>" selected="selected"><%=year%></option>
	    				<option value="<%=yearLast%>"><%=yearLast%></option>
	    			</select></td>
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
	    		<td><select name="startYear" id="startYear">
	    				<option value="<%=year%>" selected="selected"><%=year%></option>
	    				<option value="<%=yearLast%>"><%=yearLast%></option>
	    			</select></td>
	    		<td><label for="startYear">年</label></td>
	    		<td><select name="startMonth" id="startMonth">
	    				<option value="1"<%=month[0]%>>1</option>
	    				<option value="2"<%=month[1]%>>2</option>
	    				<option value="3"<%=month[2]%>>3</option>
	    				<option value="4"<%=month[3]%>>4</option>
	    				<option value="5"<%=month[4]%>>5</option>
	    				<option value="6"<%=month[5]%>>6</option>
	    				<option value="7"<%=month[6]%>>7</option>
	    				<option value="8"<%=month[7]%>>8</option>
	    				<option value="9"<%=month[8]%>>9</option>
	    				<option value="10"<%=month[9]%>>10</option>
	    				<option value="11"<%=month[10]%>>11</option>
	    				<option value="12"<%=month[11]%>>12</option>
	    			</select></td>
	    		<td><label for="startMonth">月</label></td>
	    		<td><select name="startDay" id="startDay">
	    				<option value="1"<%=date[0]%>>1</option>
	    				<option value="2"<%=date[1]%>>2</option>
	    				<option value="3"<%=date[2]%>>3</option>
	    				<option value="4"<%=date[3]%>>4</option>
	    				<option value="5"<%=date[4]%>>5</option>
	    				<option value="6"<%=date[5]%>>6</option>
	    				<option value="7"<%=date[6]%>>7</option>
	    				<option value="8"<%=date[7]%>>8</option>
	    				<option value="9"<%=date[8]%>>9</option>
	    				<option value="10"<%=date[9]%>>10</option>
	    				<option value="11"<%=date[10]%>>11</option>
	    				<option value="12"<%=date[11]%>>12</option>
	    				<option value="13"<%=date[12]%>>13</option>
	    				<option value="14"<%=date[13]%>>14</option>
	    				<option value="15"<%=date[14]%>>15</option>
	    				<option value="16"<%=date[15]%>>16</option>
	    				<option value="17"<%=date[16]%>>17</option>
	    				<option value="18"<%=date[17]%>>18</option>
	    				<option value="19"<%=date[18]%>>19</option>
	    				<option value="20"<%=date[19]%>>20</option>
	    				<option value="21"<%=date[20]%>>21</option>
	    				<option value="22"<%=date[21]%>>22</option>
	    				<option value="23"<%=date[22]%>>23</option>
	    				<option value="24"<%=date[23]%>>24</option>
	    				<option value="25"<%=date[24]%>>25</option>
	    				<option value="26"<%=date[25]%>>26</option>
	    				<option value="27"<%=date[26]%>>27</option>
	    				<option value="28"<%=date[27]%>>28</option>
	    				<option value="29"<%=date[28]%>>29</option>
	    				<option value="30"<%=date[29]%>>30</option>
	    				<option value="31"<%=date[30]%>>31</option>
	    			</select></td>
	    		<td><label for="startDay">日</label></td>
	    	</tr>
	    	<tr>
	    		<td><label for="dealYear">处理日期</label></td>
	    		<td><select name="dealYear" id="dealYear">
	    				<option value="<%=year%>" selected="selected"><%=year%></option>
	    				<option value="<%=yearLast%>"><%=yearLast%></option>
	    			</select></td>
	    		<td><label for="dealYear">年</label></td>
	    		<td><select name="dealMonth" id="dealMonth">
	    				<option value="1"<%=month[0]%>>1</option>
	    				<option value="2"<%=month[1]%>>2</option>
	    				<option value="3"<%=month[2]%>>3</option>
	    				<option value="4"<%=month[3]%>>4</option>
	    				<option value="5"<%=month[4]%>>5</option>
	    				<option value="6"<%=month[5]%>>6</option>
	    				<option value="7"<%=month[6]%>>7</option>
	    				<option value="8"<%=month[7]%>>8</option>
	    				<option value="9"<%=month[8]%>>9</option>
	    				<option value="10"<%=month[9]%>>10</option>
	    				<option value="11"<%=month[10]%>>11</option>
	    				<option value="12"<%=month[11]%>>12</option>
	    			</select></td>
	    		<td><label for="dealMonth">月</label></td>
	    		<td><select name="dealDay" id="dealDay">
	    				<option value="1"<%=date[0]%>>1</option>
	    				<option value="2"<%=date[1]%>>2</option>
	    				<option value="3"<%=date[2]%>>3</option>
	    				<option value="4"<%=date[3]%>>4</option>
	    				<option value="5"<%=date[4]%>>5</option>
	    				<option value="6"<%=date[5]%>>6</option>
	    				<option value="7"<%=date[6]%>>7</option>
	    				<option value="8"<%=date[7]%>>8</option>
	    				<option value="9"<%=date[8]%>>9</option>
	    				<option value="10"<%=date[9]%>>10</option>
	    				<option value="11"<%=date[10]%>>11</option>
	    				<option value="12"<%=date[11]%>>12</option>
	    				<option value="13"<%=date[12]%>>13</option>
	    				<option value="14"<%=date[13]%>>14</option>
	    				<option value="15"<%=date[14]%>>15</option>
	    				<option value="16"<%=date[15]%>>16</option>
	    				<option value="17"<%=date[16]%>>17</option>
	    				<option value="18"<%=date[17]%>>18</option>
	    				<option value="19"<%=date[18]%>>19</option>
	    				<option value="20"<%=date[19]%>>20</option>
	    				<option value="21"<%=date[20]%>>21</option>
	    				<option value="22"<%=date[21]%>>22</option>
	    				<option value="23"<%=date[22]%>>23</option>
	    				<option value="24"<%=date[23]%>>24</option>
	    				<option value="25"<%=date[24]%>>25</option>
	    				<option value="26"<%=date[25]%>>26</option>
	    				<option value="27"<%=date[26]%>>27</option>
	    				<option value="28"<%=date[27]%>>28</option>
	    				<option value="29"<%=date[28]%>>29</option>
	    				<option value="30"<%=date[29]%>>30</option>
	    				<option value="31"<%=date[30]%>>31</option>
	    			</select></td>
	    		<td><label for="dealDay">日</label></td>
	    	</tr>
	    </table>
	    <input type="submit" value="开始查询">
	    <input type="hidden" name="operate" value="documentBackupQueryPart">
	    	
	    <table width="1800px" border="1" align="center">
	    	<tr height="30px">
	    		<th>序列号</th>
	    		<th>申请人</th>
	    		<th>发起日期</th>
	    		<th>标题</th>
	    		<th>附件</th>
	    	</tr>
<%
DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
ArrayList<DocumentBackup> documentBackupList = documentBackupDAO.showDocumentBackup();
if (documentBackupList!=null&&documentBackupList.size()>0){
	for (int i=0;i<documentBackupList.size();i++){
		DocumentBackup documentBackup = documentBackupList.get(i);
		if (documentBackup.getAttachmentAddress() != null) {
			attachmentAddress = "下载附件";
		}
 %>
	    	<tr height="30px" align="center">
	    		<td width="250px"><%=documentBackup.getTargetString()%>(<%=documentBackup.getTargetYear()%>)第<%=documentBackup.getTargetSeq()%>号</td>
	    		<td width="250px"><%=documentBackup.getDocDepartment()%>-<%=documentBackup.getDocStaff()%></td>
	    		<td width="150px"><%=documentBackup.getStartYear()%>年<%=documentBackup.getStartMonth()%>月<%=documentBackup.getStartDay()%>日</td>
	    		<td><%=documentBackup.getDocTitle()%></td>
	    		<td width="100px"><a href="<%=path%>/servlet.download?realPath=<%=documentBackup.getAttachmentAddress()%>" target="_blank"><%=attachmentAddress%></a></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>