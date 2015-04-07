package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SuggestDAO;
import entity.Suggest;

public class SuggestServlet extends HttpServlet {
	
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
		this.addSuggest(staff_id,url,serverPath,detail);
		request.getRequestDispatcher("../suggestions/suggestUploaded.jsp").forward(request, response);
	}

	public void init() throws ServletException {
	}
	
	public void addSuggest(String staff_id,String url,String serverPath,String detail){
		Suggest suggest = new Suggest();
		suggest.setStaff_id(staff_id);
		suggest.setUrl(url);
		suggest.setServer_path(serverPath);
		suggest.setDetail(detail);
		try {
			SuggestDAO.addSuggest(suggest);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
