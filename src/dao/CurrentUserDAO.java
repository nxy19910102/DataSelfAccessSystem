package dao;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

import entity.CurrentUser;

public class CurrentUserDAO {
	
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
	
	@SuppressWarnings("unchecked")
	public static void updateCurrentUser (HttpSessionBindingEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		String sessionId = session.getId();
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList) {
			if (sessionId.equals(c.getSessionId())){
				if (arg0.getName().equals("staff_id")) {
					c.setStaffId((String) session.getAttribute(arg0.getName()));
					c.setEffDate(new Date());
				}
				currentUserList.remove(c);
				currentUserList.add(c);
			}
		}
		context.setAttribute("currentUserList",currentUserList);
		System.out.println("updateCurrentUserFinish");
		//显示用户
		ArrayList<CurrentUser> currentUserList1 = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList1) {
			System.out.println(c.getSessionId());
			System.out.println(c.getIpAddress());
			System.out.println(c.getStaffId());
			System.out.println(c.getEffDate());
		}
	}
	
	public static void removeCurrentUser (HttpSessionEvent arg0) {
		System.out.println("removeCurrentUser");
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		String sessionId = session.getId();
		@SuppressWarnings("unchecked")
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList) {
			if (sessionId.equals(c.getSessionId())){
				currentUserList.remove(c);
			}
		}
		context.setAttribute("currentUserList", currentUserList);
		context.setAttribute("currentUserNumber", currentUserList.size());
		System.out.println("removeCurrentUserFinish");
		//显示用户
		@SuppressWarnings("unchecked")
		ArrayList<CurrentUser> currentUserList1 = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList1) {
			System.out.println(c.getSessionId());
			System.out.println(c.getIpAddress());
			System.out.println(c.getStaffId());
			System.out.println(c.getEffDate());
		}
	}
	public static ArrayList<CurrentUser> showCurrentUser(HttpServletRequest request) {
		System.out.println("showCurrentUser");
		@SuppressWarnings("unchecked")
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) request.getServletContext().getAttribute("currentUserList");
		System.out.println("showCurrentUserFinish");
		//显示用户
		@SuppressWarnings("unchecked")
		ArrayList<CurrentUser> currentUserList1 = (ArrayList<CurrentUser>) request.getServletContext().getAttribute("currentUserList");
		for (CurrentUser c:currentUserList1) {
			System.out.println(c.getSessionId());
			System.out.println(c.getIpAddress());
			System.out.println(c.getStaffId());
			System.out.println(c.getEffDate());
		}
		return currentUserList;
	}
	
	//用于RequestListener
	public static boolean judgeCurrentUser (ServletContext context,String sessionId) {
		@SuppressWarnings("unchecked")
		ArrayList<CurrentUser> currentUserList = (ArrayList<CurrentUser>) context.getAttribute("currentUserList");
		for (CurrentUser c:currentUserList) {
			if (sessionId.equals(c.getSessionId())){
				return true;
			}
		}
		return false;
	}
}
