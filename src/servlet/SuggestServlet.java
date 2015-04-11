package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SuggestDAO;

public class SuggestServlet extends HttpServlet {
	private SuggestDAO suggestDAO = new SuggestDAO();
	private static final long serialVersionUID = 3L;

	public SuggestServlet() {
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
		String staffId = "none";
		String detail = "none";
		String url = request.getRequestURL().toString();
		String serverPath = request.getServletPath();
		if (session.getAttribute("staffId")!=null){
			staffId = (String) session.getAttribute("staffId");
		}
		if (request.getParameter("detail")!=""){
			detail = request.getParameter("detail");
		}
		try {
			suggestDAO.addSuggest(staffId, url, serverPath, detail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("../suggestions/suggestUploaded.jsp").forward(request, response);
	}

	public void init() throws ServletException {
	}
}
