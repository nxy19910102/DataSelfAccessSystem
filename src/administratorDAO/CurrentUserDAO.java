package administratorDAO;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import administratorEntity.CurrentUser;

public class CurrentUserDAO {
//	RequestListener.java
	@SuppressWarnings("unchecked")
	public static void addCurrentUser (ServletContext context,String sessionId,String ipAddress,String staffId){
		CurrentUser currentUser = new CurrentUser();
		currentUser.setSessionId(sessionId);
		currentUser.setIpAddress(ipAddress);
		currentUser.setStaffId(staffId);
		currentUser.setEffDate(new Date());
		
		ArrayList<CurrentUser> currentUserList;
		currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		currentUserList.add(currentUser);
		context.setAttribute("currentUserList", currentUserList);
		context.setAttribute("currentUserNumber", currentUserList.size());
	}
	
//	SessionListener
	@SuppressWarnings("unchecked")
	public static void removeCurrentUser (HttpSession session) {
		ServletContext context = session.getServletContext();
		String sessionId = session.getId();
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList) {
			if (sessionId.equals(c.getSessionId())){
				currentUserList.remove(c);
			}
		}
		context.setAttribute("currentUserList", currentUserList);
		context.setAttribute("currentUserNumber", currentUserList.size());
	}
	
//	CurrentUser.jsp
	@SuppressWarnings("unchecked")
	public ArrayList<CurrentUser> showCurrentUser(HttpServletRequest request) {
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) request.getServletContext().getAttribute("currentUserList");
		return currentUserList;
	}
	
//	RequestListener.java,添加currentUser和sessionLog
	@SuppressWarnings("unchecked")
	public static boolean judgeCurrentUser (ServletContext context, String sessionId, String ipAddress, String staffId) {
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList) {
			if (sessionId.equals(c.getSessionId())){
				if (!staffId.equals(c.getStaffId())){
					currentUserList.remove(c);
					c.setStaffId(staffId);
					currentUserList.add(c);
					SessionLogDAO.invalidateSessionLog(sessionId);
					SessionLogDAO.addSessionLog(sessionId, ipAddress, staffId);
					context.setAttribute("currentUserList", currentUserList);
					context.setAttribute("currentUserNumber", currentUserList.size());
				}
				return true;
			}
		}
		return false;
	}
}
