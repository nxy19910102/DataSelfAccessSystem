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
		String staff_id = "none";
		String password = "none";
		if (request.getParameter("staff_id")!=null){
			staff_id = (String) request.getParameter("staff_id");
		}
		if (request.getParameter("password")!=null){
			password = (String) request.getParameter("password");
		}
		if (this.judgeLogin(staff_id,password)){
			HttpSession session = request.getSession();
			session.setAttribute("staff_id", staff_id);
				request.getRequestDispatcher("../mainForUser.jsp").forward(request, response);
		}else{
			response.sendRedirect(path+"/loginFailure.jsp");
		}
	}

	public boolean judgeLogin(String staff_id, String password){
		try {
			return staffDAO.judgeLogin(staff_id, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	public void init() throws ServletException {
	}
}
