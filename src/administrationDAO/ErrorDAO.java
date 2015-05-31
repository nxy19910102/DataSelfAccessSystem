package administrationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import administrationEntity.Error500;
import util.DBConnect;

public class ErrorDAO {
	
//	DoServlet.java
	public void addError(String staffId,String serverPath,String detail) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into nxy_dsas_error ("
			+"id,staff_id,server_path,detail,state,eff_date"
			+") values (nxy_dsas_error_id.nextval,?,?,?,1,sysdate)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staffId);
		ps.setString(2, serverPath);
		ps.setString(3, detail);
		ps.execute();
		ps.close();
	}
	
//	errorManege.jsp
	public ArrayList<Error500> showError() throws SQLException{
		ArrayList<Error500> errorList = new ArrayList<Error500>();
		Error500 error = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_error order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			error = new Error500();
			error.setId(rs.getLong("id"));
			error.setStaffId(rs.getString("staff_id"));
			error.setDetail(rs.getString("detail"));
			error.setStateString(rs.getInt("state"));
			error.setEffDateString(rs.getString("eff_date").substring(0, 19));
			if (rs.getString("exp_date")!=null){
				error.setExpDateString(rs.getString("exp_date").substring(0, 19));
			} else {
				error.setExpDateString("");
			}
			errorList.add(error);
		}
		rs.close();
		st.close();
		return errorList;
	}
}
