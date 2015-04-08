package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Administrator extends HttpServlet {

	public Administrator() {
		super();
	}

	public void destroy() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//此处写入权限验证，或在MainForUser里加入权限才有超链接
		if ()){
			request.getRequestDispatcher("../mainForUser.jsp").forward(request, response);
		}else{
			response.sendRedirect(path+"/loginFailure.jsp");
		}
	}

	public void init() throws ServletException {
	}
}
