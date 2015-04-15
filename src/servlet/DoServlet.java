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

public class DoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String operate = null;
	private StaffDAO staffDAO = new StaffDAO();
	private SuggestDAO suggestDAO = new SuggestDAO();
	private ErrorDAO errorDAO = new ErrorDAO();
	private String staffId = "none";
	private String password = "none";
	private String detail = "none";

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
			if (request.getParameter("staffId")!=null){
				staffId = (String) request.getParameter("staffId");
			}
			if (request.getParameter("password")!=null){
				password = (String) request.getParameter("password");
			}
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
			String url = request.getRequestURL().toString();
			if (session.getAttribute("staffId")!=null){
				staffId = (String) session.getAttribute("staffId");
			}
			if (request.getParameter("detail")!=""){
				detail = request.getParameter("detail");
			}
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
			String url = request.getRequestURL().toString();
			String serverPath = request.getServletPath();
			if (session.getAttribute("staffId")!=null){
				staffId = (String) session.getAttribute("staffId");
			}
			if (request.getParameter("detail")!=""){
				detail = request.getParameter("detail");
			}

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
			if (request.getParameter("attachment")=="1"){
				request.getRequestDispatcher("application/documentBackup/documentBackupAttachment.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("application/documentBackup/documentBackupSuccess.jsp").forward(request, response);
			}
			break;
		}
		case("documentBackupAttachmentUpload"):{
			request.getRequestDispatcher("application/documentBackup/documentBackupSuccess.jsp").forward(request, response);
			break;
		}
		}
		
	}
	
	public void init() throws ServletException {
	}
}
