package listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import administratorDAO.CurrentUserDAO;
import administratorDAO.SessionLogDAO;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("sessionCreated");
		arg0.getSession().setMaxInactiveInterval(1);
		System.out.println(new Date());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("sessionDestroyed");
		HttpSession session = arg0.getSession();
		String sessionId = session.getId();
		SessionLogDAO.invalidateSessionLog(sessionId);
		CurrentUserDAO.removeCurrentUser(session);
		System.out.println(new Date());
	}

}
