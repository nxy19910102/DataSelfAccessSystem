package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import dao.CurrentUserDAO;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		CurrentUserDAO.updateCurrentUser(arg0);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		CurrentUserDAO.updateCurrentUser(arg0);
	}
}
