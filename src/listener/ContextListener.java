package listener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.AuthorityDAO;
import entity.Authority;
import entity.CurrentUser;
import util.DBConnect;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			DBConnect.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		try {
			DBConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AuthorityDAO authorityDAO = new AuthorityDAO();
		try {
			ArrayList<Authority> authorityList = authorityDAO.getAuthority();
			context.setAttribute("authorityList", authorityList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<CurrentUser> currentUserList = new ArrayList<CurrentUser>();
		context.setAttribute("currentUserList", currentUserList);
		context.setAttribute("currentUserNumber", currentUserList.size());
	}
}
