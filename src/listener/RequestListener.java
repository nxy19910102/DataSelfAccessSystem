package listener;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.CurrentUserDAO;
import dao.RequestLogDAO;
import dao.SessionLogDAO;

@WebListener
public class RequestListener implements ServletRequestListener {
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String sessionId = session.getId();
		String ipAddress = request.getRemoteAddr();
		String serverPath = request.getServletPath();
		String staffId;
//		设置session属性staffId
		if (session.getAttribute("staffId")!=null){
			staffId = (String) session.getAttribute("staffId");
		} else {
			staffId = "nologin";
		}
		Enumeration<String> enumer =request.getParameterNames();
		
		StringBuffer parameter = new StringBuffer();
		String param;
		while (enumer.hasMoreElements()){
			param = enumer.nextElement();
			System.out.println(param);
			if (param.equals("password")){
				continue;
			}
			parameter.append(param+":"+request.getParameter(param)+";"); 
		}
		String parameters = parameter.toString();
		
		RequestLogDAO.addRequestLog(sessionId, ipAddress, serverPath, staffId, parameters);
		if (!CurrentUserDAO.judgeCurrentUser(context, sessionId, ipAddress, staffId)) {
			SessionLogDAO.addSessionLog(sessionId, ipAddress, staffId);
			CurrentUserDAO.addCurrentUser(context, sessionId, ipAddress, staffId);
		}
	}
}
