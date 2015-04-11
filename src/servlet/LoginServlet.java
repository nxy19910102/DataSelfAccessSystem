package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StaffDAO;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private StaffDAO staffDAO = new StaffDAO();

	public LoginServlet() {
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
		String path = request.getContextPath();
		String staffId = null;
		String password = null;
		if (request.getParameter("staffId")!=null){
			staffId = (String) request.getParameter("staffId");
		}
		if (request.getParameter("password")!=null){
			password = (String) request.getParameter("password");
		}
		try {
			if (staffDAO.judgeLogin(staffId,password)){
				HttpSession session = request.getSession();
				session.setAttribute("staffId", staffId);
					request.getRequestDispatcher("../mainForUser.jsp").forward(request, response);
			}else{
				response.sendRedirect(path+"/loginFailure.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws ServletException {
	}
}
