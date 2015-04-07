package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
//		String remStaff = "none";
//		String remAdmin = "none";
		String is_admin = request.getParameterValues("is_admin")[0];
		if (request.getParameter("staff_id")!=null){
			staff_id = (String) request.getParameter("staff_id");
		}
		if (request.getParameter("password")!=null){
			password = (String) request.getParameter("password");
		}
//		if (request.getParameterValues("remStaff")!=null){
//			remStaff = request.getParameterValues("remStaff")[0];
//		}
//		if (request.getParameterValues("remAdmin")!=null){
//			remAdmin = request.getParameterValues("remAdmin")[0];
//		}
		if (this.judgeLogin(staff_id,password)){
//			this.doCookie(request, response, staff_id, is_admin, remStaff, remAdmin);
			HttpSession session = request.getSession();
			session.setAttribute("staff_id", staff_id);
			session.setAttribute("is_admin", is_admin);
			if (is_admin.equals("normal")){
				request.getRequestDispatcher("../mainForUser.jsp").forward(request, response);
			}else if (is_admin.equals("admin")){
				request.getRequestDispatcher("../mainForAdmin.jsp").forward(request, response);
			}
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
	
	public void doCookie(HttpServletRequest request, HttpServletResponse response,String staff_id,String is_admin,String remStaff,String remAdmin){
		Cookie cookie = null;
		cookie = new Cookie("remStaff",remStaff);
		response.addCookie(cookie);
		if (remStaff.equals("remStaff")){
			cookie = new Cookie("staff_id",staff_id);
		}else{
			cookie = new Cookie("staff_id",null);
		}
		response.addCookie(cookie);
		cookie = new Cookie("remAdmin",remAdmin);
		response.addCookie(cookie);
		if (remAdmin.equals("remAdmin")){
			cookie = new Cookie("is_admin",is_admin);
		}else{
			cookie = new Cookie("is_admin","normal");
		}
		response.addCookie(cookie);
	}

	public void init() throws ServletException {
	}

}
