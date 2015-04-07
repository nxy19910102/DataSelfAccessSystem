package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ErrorDAO;
import entity.Error500;

public class ErrorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2L;

	public ErrorServlet() {
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
		String staff_id = "none";
		String detail = "none";
		String url = request.getRequestURL().toString();
		String serverPath = request.getServletPath();
		if (session.getAttribute("staff_id")!=null){
			staff_id = (String) session.getAttribute("staff_id");
		}
		if (request.getParameter("detail")!=""){
			detail = request.getParameter("detail");
		}
		this.addError(staff_id,url,serverPath,detail);
		request.getRequestDispatcher("../errorPages/errorUploaded.jsp").forward(request, response);
	}

	public void init() throws ServletException {
	}
	
	public void addError(String staff_id,String url,String serverPath,String detail){
		Error500 error = new Error500();
		error.setStaff_id(staff_id);
		error.setUrl(url);
		error.setServer_path(serverPath);
		error.setDetail(detail);
		try {
			ErrorDAO.addError(error);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
