package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import administrationDAO.ErrorDAO;
import administrationDAO.StaffDAO;
import administrationDAO.SuggestDAO;
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
					response.sendRedirect("login/application.jsp");
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
		case("upload"):{
			request.getRequestDispatcher("application/upload.jsp").forward(request, response);
			break;
		}
		case("download"):{
			request.getRequestDispatcher("application/download.jsp").forward(request, response);
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
			String url = request.getRequestURL().toString();
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
				errorDAO.addError(staffId,url,serverPath,detail);
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
				request.getRequestDispatcher("application/documentBackup/documentBackupAttachment.jsp?uploadKey=documentBackupAttachmentUpload&target=" + target + "&targetYear=" + targetYear + "&targetSeq=" + targetSeq).forward(request, response);
			} else {
				request.getRequestDispatcher("application/documentBackup/documentBackupSuccess.jsp").forward(request, response);
			}
			break;
		}
		case("documentBackupAttachmentUpload"):{
			request.getRequestDispatcher("application/documentBackup/documentBackupSuccess.jsp").forward(request, response);
			break;
		}
		case("DocumentBackupQuery"):{
			request.getRequestDispatcher("application/documentBackup/documentBackupQuery.jsp").forward(request, response);
			break;
		}
		}
		
	}
	
	public void init() throws ServletException {
	}
}
