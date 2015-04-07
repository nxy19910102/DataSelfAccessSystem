package listener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
		try {
			DBConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<CurrentUser> currentUserList = new ArrayList<CurrentUser>();
		arg0.getServletContext().setAttribute("currentUserList", currentUserList);
		arg0.getServletContext().setAttribute("currentUserNumber", currentUserList.size());
	}
}
