<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="applicationDAO.BackupDAO" %>
<%@ page import="applicationEntity.Backup" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int target = 0;
String target0 = "";
String target1 = "";
String target2 = "";
String targetYear = "";
String targetSeq = "";
String applyStaff = "";
String approveStaff = "";
String accNbr = "";
String effYear = "";
String effMonth = "";
String effDay = "";
String expYear = "";
String expMonth = "";
String expDay = "";
String attachmentAddress = "";
if (request.getParameter("target") != null){
	target = Integer.parseInt(request.getParameter("target"));
}
switch (target){
case 0:{
	target0 = " selected=\"selected\"";
	break;
}
case 1:{
	target1 = " selected=\"selected\"";
	break;
}
case 2:{
	target2 = " selected=\"selected\"";
	break;
}
}
if (request.getParameter("targetYear") != null){
	targetYear = request.getParameter("targetYear");
}
if (request.getParameter("targetSeq") != null){
	targetSeq = request.getParameter("targetSeq");
}
if (request.getParameter("applyStaff") != null){
	applyStaff = request.getParameter("applyStaff");
}
if (request.getParameter("approveStaff") != null){
	approveStaff = request.getParameter("approveStaff");
}
if (request.getParameter("accNbr") != null){
	accNbr = request.getParameter("accNbr");
}
if (request.getParameter("effYear") != null){
	effYear = request.getParameter("effYear");
}
if (request.getParameter("effMonth") != null){
	effMonth = request.getParameter("effMonth");
}
if (request.getParameter("effDay") != null){
	effDay = request.getParameter("effDay");
}
if (request.getParameter("expYear") != null){
	expYear = request.getParameter("expYear");
}
if (request.getParameter("expMonth") != null){
	expMonth = request.getParameter("expMonth");
}
if (request.getParameter("expDay") != null){
	expDay = request.getParameter("expDay");
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
  	<div align="center">
  		<h1>备案查询</h1>
  		<h3>请输入模糊查询条件</h3>
  		<form name="backupQuery" action="<%=path%>/servlet.do" method="post">
	    <table>
	    	<tr>
	    		<td><label for="target">类别</label></td>
    			<td><select name="target" id="target">
    				<option value="0"<%=target0%>>所有</option>
    				<option value="1"<%=target1%>>签报</option>
    				<option value="2"<%=target2%>>内部联系单</option>
    				</select></td>
	    		<td><label for="targetYear">序列号</label></td>
	    		<td><input type="text" name="targetYear" id="targetYear" value="<%=targetYear%>"></td>
	    		<td><label for="targetYear">年</label></td>
	    		<td><input type="text" name="targetSeq" id="targetSeq" value="<%=targetSeq%>"></td>
	    		<td><label for="targetSeq">号</label></td>
	    	</tr>
	    	<tr>
	    		<td><label for="applyStaff">申请责任人</label></td>
	    		<td><input type="text" name="applyStaff" id="applyStaff" value="<%=applyStaff%>"></td>
	    		<td><label for="approveStaff">审批责任人</label></td>
	    		<td><input type="text" name="approveStaff" id="approveStaff" value="<%=approveStaff%>"></td>
	    		<td><label for="accNbr">业务号码</label></td>
	    		<td><input type="text" name="accNbr" id="accNbr" value="<%=accNbr%>"></td>
	    	</tr>
	    	<tr>
	    		<td><label for="effYear">生效时间</label></td>
	    		<td><input type="text" name="effYear" id="effYear" value="<%=effYear%>"></td>
	    		<td><label for="effYear">年</label></td>
	    		<td><input type="text" name="effMonth" id="effMonth" value="<%=effMonth%>"></td>
	    		<td><label for="effMonth">月</label></td>
	    		<td><input type="text" name="effDay" id="effDay" value="<%=effDay%>"></td>
	    		<td><label for="effDay">日</label></td>
	    	</tr>
	    	<tr>
	    		<td><label for="expYear">失效时间</label></td>
	    		<td><input type="text" name="expYear" id="expYear" value="<%=expYear%>"></td>
	    		<td><label for="expYear">年</label></td>
	    		<td><input type="text" name="expMonth" id="expMonth" value="<%=expMonth%>"></td>
	    		<td><label for="expMonth">月</label></td>
	    		<td><input type="text" name="expDay" id="expDay" value="<%=expDay%>"></td>
	    		<td><label for="expDay">日</label></td>
	    	</tr>
	    </table>
	    <input type="submit" value="开始查询">
	    <input type="hidden" name="operate" value="backupQuery">
	    </form>
	    
	    <form name="backupDownload" action="<%=path%>/servlet.do?target=<%=target%>&targetYear=<%=targetYear%>&targetSeq=<%=targetSeq%>&applyStaff=<%=applyStaff%>&approveStaff=<%=approveStaff%>&accNbr=<%=accNbr%>&effYear=<%=effYear%>&effMonth=<%=effMonth%>&effDay=<%=effDay%>&expYear=<%=expYear%>&expMonth=<%=expMonth%>&expDay=<%=expDay%>" method="post">
		    <label>导出选项</label>
		    <select name="downloadPage">
			<%-- <option value="now">当前页</option>--%>
			<option value="all">所有页</option>
			</select>
			<label>导出格式</label>
			<select name="downloadFormat">
			<%-- <option value="EXCEL07">EXCEL07及以上</option>--%>
			<option value="EXCEL03">EXCEL03</option>
			</select>
			<input type="submit" value="点击导出">
		    <input type="hidden" name="operate" value="backupDownload">
	    </form>
	    <table width="3000px" border="1" align="center">
	    	<tr height="30px">
	    		<th>系统ID</th>
	    		<th>序列号</th>
	    		<th>申请责任人</th>
	    		<th>审批责任人</th>
	    		<th>业务号码</th>
	    		<th>原套餐</th>
	    		<th>新套餐</th>
	    		<th>优惠金额/月</th>
	    		<th>生效时间</th>
	    		<th>失效时间</th>
	    		<th>备注事由</th>
	    		<th>附件</th>
	    	</tr>
<%
BackupDAO backupDAO = new BackupDAO();
ArrayList<Backup> backupList = backupDAO.showBackup(target, targetYear, targetSeq, applyStaff, approveStaff, accNbr, effYear, effMonth, effDay, expYear, expMonth, expDay);
if (backupList!=null&&backupList.size()>0){
	for (int i=0;i<backupList.size();i++){
		Backup backup = backupList.get(i);
		if (backup.getAttachmentAddress() != null) {
			attachmentAddress = "下载附件";
		}
 %>
	    	<tr height="30px" align="center">
	    		<td width="200px"><%=backup.getId()%></td>
	    		<td width="200px"><%=backup.getTargetString()%>(<%=backup.getTargetYear()%>)第<%=backup.getTargetSeq()%>号</td>
	    		<td width="200px"><%=backup.getApplyStaff()%></td>
	    		<td width="200px"><%=backup.getApproveStaff()%></td>
	    		<td width="500px"><%=backup.getAccNbr()%></td>
	    		<td width="250px"><%=backup.getOfferOld()%></td>
	    		<td width="250px"><%=backup.getOfferNew()%></td>
	    		<td width="150px"><%=backup.getPromotion()%></td>
	    		<td width="100px"><%=backup.getEffDate()%></td>
	    		<td width="100px"><%=backup.getExpDate()%></td>
	    		<td><%=backup.getNote()%></td>
	    		<td width="90px"><a href="<%=path%>/servlet.download?realPath=<%=backup.getAttachmentAddress()%>" target="_blank"><%=attachmentAddress%></a></td>
	    	</tr>
<%
	}
}	
 %>
        </table>
  	</div>
  </body>
</html>