package listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entity.CurrentUser;

@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		ServletContext context = arg0.getSession().getServletContext();
		@SuppressWarnings("unchecked")
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		context.setAttribute("currentUserNumber", currentUserList.size());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}
}
