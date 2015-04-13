package administrationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import administrationEntity.RequestLog;
import util.DBConnect;

public class RequestLogDAO {
//	RequestListener.java
	public static void addRequestLog(String sessionId,String ipAddress,String serverPath,String staffId,String parameters){
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "insert into nxy_dsas_request_log ("
				+"id,session_id,ip_address,server_path,staff_id,parameters,eff_date"
				+") values (nxy_dsas_request_log_id.nextval,?,?,?,?,?,sysdate)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sessionId);
			ps.setString(2, ipAddress);
			ps.setString(3, serverPath);
			ps.setString(4, staffId);
			ps.setString(5, parameters);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	requestLog.jsp
	public ArrayList<RequestLog> showRequestLog() throws SQLException{
		ArrayList<RequestLog> requestLogList = new ArrayList<RequestLog>();
		RequestLog requestLog = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_request_log order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			requestLog = new RequestLog();
			requestLog.setId(rs.getLong("id"));
			requestLog.setSessionId(rs.getString("session_id"));
			requestLog.setIpAddress(rs.getString("ip_address"));
			requestLog.setServerPath(rs.getString("server_path"));
			requestLog.setStaffId(rs.getString("staff_id"));
			requestLog.setParameters(rs.getString("parameters"));
			requestLog.setEffDateString(rs.getString("eff_date").substring(0, 19));
			requestLogList.add(requestLog);
		}
		rs.close();
		st.close();
		return requestLogList;
	}
}
