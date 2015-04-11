package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.SessionLog;
import util.DBConnect;

public class SessionLogDAO {
//	RequestListener.java
	public static void addSessionLog(String sessionId,String ipAddress,String staffId) {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "insert into nxy_dsas_session_log ("
				+"id,session_id,ip_address,staff_id,eff_date"
				+") values (nxy_dafs_session_log_id.nextval,?,?,?,sysdate)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sessionId);
			ps.setString(2, ipAddress);
			ps.setString(3, staffId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	SessionListener
	public static void invalidateSessionLog(String sessionId) {
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "update nxy_dsas_session_log set exp_date = sysdate "
				+ "where exp_date is null and session_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sessionId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	
//	sessionLog.jsp
	public ArrayList<SessionLog> showSessionLog() throws SQLException{
		ArrayList<SessionLog> sessionLogList = new ArrayList<SessionLog>();
		SessionLog sessionLog = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_session_log order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			sessionLog = new SessionLog();
			sessionLog.setId(rs.getLong("id"));
			sessionLog.setSessionId(rs.getString("session_id"));
			sessionLog.setIpAddress(rs.getString("ip_address"));
			sessionLog.setStaffId(rs.getString("staff_id"));
			sessionLog.setEffDate(rs.getDate("eff_date"));
			sessionLog.setExpDate(rs.getDate("exp_date"));
			sessionLogList.add(sessionLog);
		}
		rs.close();
		st.close();
		return sessionLogList;
	}
}
