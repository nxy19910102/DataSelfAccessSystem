package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DatabaseToExcel;
import administrationDAO.ErrorDAO;
import administrationDAO.StaffDAO;
import administrationDAO.SuggestDAO;
import applicationDAO.BackupDAO;
import applicationDAO.DocumentBackupDAO;

public class DoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String operate = null;

	public DoServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		operate = request.getParameter("operate");
		switch (operate){
		case("logout"):{
			session.removeAttribute("staffId");
			response.sendRedirect("index.jsp");
			break;
		}
		case("backToApp"):{
			request.getRequestDispatcher("login/application.jsp").forward(request, response);
			break;
		}
		case("backToAdmin"):{
			request.getRequestDispatcher("login/administration.jsp").forward(request, response);
			break;
		}
		case("login"):{
			String password = "none";
			String staffId = "none";
			if (request.getParameter("staffId")!=null){
				staffId = request.getParameter("staffId");
			}
			if (request.getParameter("password")!=null){
				password = request.getParameter("password");
			}
			StaffDAO staffDAO = new StaffDAO();
			try {
				if (staffDAO.judgeLogin(staffId,password)){
					session.setAttribute("staffId", staffId);
					request.getRequestDispatcher("login/application.jsp").forward(request, response);;
				}else{
					response.sendRedirect("login/loginFailure.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case("appToAdmin"):{
			request.getRequestDispatcher("login/administration.jsp").forward(request, response);
			break;
		}
		case("staff"):{
			request.getRequestDispatcher("administration/staff/staffManage.jsp").forward(request, response);
			break;
		}
		case("staffDetail"):{
			request.getRequestDispatcher("administration/staff/staffDetail.jsp").forward(request, response);
			break;
		}
		case("currentUser"):{
			request.getRequestDispatcher("administration/staff/currentUser.jsp").forward(request, response);
			break;
		}
		case("suggestion"):{
			request.getRequestDispatcher("administration/suggestion/suggestion.jsp").forward(request, response);
			break;
		}
		case("suggestionUpload"):{
			String staffId = "none";
			String url = request.getRequestURL().toString();
			String detail = "none";
			if (session.getAttribute("staffId")!=null){
				staffId = (String) session.getAttribute("staffId");
			}
			if (request.getParameter("detail")!=""){
				detail = request.getParameter("detail");
			}
			SuggestDAO suggestDAO = new SuggestDAO();
			try {
				suggestDAO.addSuggest(staffId, url, detail);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("administration/suggestion/suggestionUpload.jsp").forward(request, response);
			break;
		}
		case("suggestionManage"):{
			request.getRequestDispatcher("administration/suggestion/suggestionManage.jsp").forward(request, response);
			break;
		}
		case("error"):{
			String staffId = "none";
			String serverPath = request.getServletPath();
			String detail = "none";
			if (session.getAttribute("staffId")!=null){
				staffId = (String) session.getAttribute("staffId");
			}
			if (request.getParameter("detail")!=""){
				detail = request.getParameter("detail");
			}
			ErrorDAO errorDAO = new ErrorDAO();
			try {
				errorDAO.addError(staffId,serverPath,detail);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("administration/error/errorUpload.jsp").forward(request, response);
			break;
		}
		case("errorManage"):{
			request.getRequestDispatcher("administration/error/errorManage.jsp").forward(request, response);
			break;
		}
		case("requestLog"):{
			request.getRequestDispatcher("administration/log/requestLog.jsp").forward(request, response);
			break;
		}
		case("sessionLog"):{
			request.getRequestDispatcher("administration/log/sessionLog.jsp").forward(request, response);
			break;
		}
		case("documentBackup"):{
			request.getRequestDispatcher("application/documentBackup/documentBackup.jsp").forward(request, response);
			break;
		}
		case("documentBackupUpload"):{
			int target = Integer.parseInt(request.getParameter("target"));
			int targetYear = Integer.parseInt(request.getParameter("targetYear"));
			int targetSeq;
			if (request.getParameter("targetSeq") == "") {
				targetSeq = 0;
			} else {
				targetSeq = Integer.parseInt(request.getParameter("targetSeq"));
			}
			String docTitle = request.getParameter("docTitle");
			String docStaff = request.getParameter("docStaff");
			String docDepartment = request.getParameter("docDepartment");
			int startYear = Integer.parseInt(request.getParameter("startYear"));
			int startMonth = Integer.parseInt(request.getParameter("startMonth"));
			int startDay = Integer.parseInt(request.getParameter("startDay"));
			int dealYear = Integer.parseInt(request.getParameter("dealYear"));
			int dealMonth = Integer.parseInt(request.getParameter("dealMonth"));
			int dealDay = Integer.parseInt(request.getParameter("dealDay"));
			String docDetail = request.getParameter("docDetail");
			int attachment = Integer.parseInt(request.getParameter("attachment"));
			String dealStaff = (String) session.getAttribute("staffId");
			DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
			try {
				documentBackupDAO.addDocumentBackup(target, targetYear, targetSeq, docTitle, docStaff, docDepartment, startYear, startMonth, startDay, dealYear, dealMonth, dealDay, docDetail, dealStaff);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (attachment == 1) {
				request.getRequestDispatcher("application/documentBackup/documentBackupAttachment.jsp?uploadKey=documentBackupAttachmentUpload&targetSeq=" + targetSeq).forward(request, response);
			} else {
				request.getRequestDispatcher("application/documentBackup/documentBackupSuccess.jsp").forward(request, response);
			}
			break;
		}
		case("documentBackupAttachmentUpload"):{
			request.getRequestDispatcher("application/documentBackup/documentBackupSuccess.jsp").forward(request, response);
			break;
		}
		case("documentBackupQuery"):{
			request.getRequestDispatcher("application/documentBackup/documentBackupQuery.jsp").forward(request, response);
			break;
		}
		case("documentBackupQueryPart"):{
			request.getRequestDispatcher("application/documentBackup/documentBackupQueryPart.jsp").forward(request, response);
			break;
		}
		case("backup"):{
			request.getRequestDispatcher("application/backup/backup.jsp").forward(request, response);
			break;
		}
		case("backupUpload"):{
			int target = Integer.parseInt(request.getParameter("target"));
			int targetYear;
			if (request.getParameter("targetYear") == "") {
				targetYear = 0;
			} else {
				targetYear = Integer.parseInt(request.getParameter("targetYear"));
			}
			int targetSeq;
			if (request.getParameter("targetSeq") == "") {
				targetSeq = 0;
			} else {
				targetSeq = Integer.parseInt(request.getParameter("targetSeq"));
			}
			String applyStaff = request.getParameter("applyStaff");
			String approveStaff = request.getParameter("approveStaff");
			String accNbr = request.getParameter("accNbr");
			String offerOld = request.getParameter("offerOld");
			String offerNew = request.getParameter("offerNew");
			String promotion = request.getParameter("promotion");
			String effYear = request.getParameter("effYear");
			String effMonth = request.getParameter("effMonth");
			String effDay = request.getParameter("effDay");
			String expYear = request.getParameter("expYear");
			String expMonth = request.getParameter("expMonth");
			String expDay = request.getParameter("expDay");
			String note = request.getParameter("note");
			int attachment = Integer.parseInt(request.getParameter("attachment"));
			BackupDAO backupDAO = new BackupDAO();
			try {
				backupDAO.addBackups(target, targetYear, targetSeq, applyStaff, approveStaff, accNbr, offerOld, offerNew, promotion, effYear, effMonth, effDay, expYear, expMonth, expDay, note);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (attachment == 1) {
				//此处不转发target因为target在原请求中已有
				request.getRequestDispatcher("application/backup/backupAttachment.jsp?uploadKey=backupAttachmentUpload&targetYear=" + targetYear + "&targetSeq=" + targetSeq).forward(request, response);
			} else {
				request.getRequestDispatcher("application/backup/backupSuccess.jsp").forward(request, response);
			}
			break;
		}
		case("backupAttachmentUpload"):{
			request.getRequestDispatcher("application/backup/backupSuccess.jsp").forward(request, response);
			break;
		}
		case("backupQuery"):{
			request.getRequestDispatcher("application/backup/backupQuery.jsp").forward(request, response);
			break;
		}
		case("backupDownload"):{
			String[] title = {"系统ID","序列号","申请责任人","审批责任人","业务号码","原套餐","新套餐","优惠金额/月","生效时间","失效时间","备注事由","附件"};
			String downloadFormat = request.getParameter("downloadFormat");
			DatabaseToExcel databaseToExcel = new DatabaseToExcel();
			String attachmentAddress = null;
			switch (downloadFormat){
				case ("EXCEL03"):{
					attachmentAddress = databaseToExcel.generateXls(request, title, operate);
					break;
				}
//				EXCEL07没做好
				case ("EXCEL07"):{
					attachmentAddress = databaseToExcel.generateXlsx(request, title, operate);
					break;
				}
			}
			String url = "servlet.download?realPath=" + attachmentAddress; 
			request.getRequestDispatcher(url).forward(request, response);
			break;
		}
		}
	}
	
	public void init() throws ServletException {
	}
}
