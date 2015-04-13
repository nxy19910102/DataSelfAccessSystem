package administrationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import administrationEntity.Suggest;
import util.DBConnect;

public class SuggestDAO {
	
//	LoginServlet.java
	public void addSuggest(String staffId, String url, String detail) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into NXY_DSAS_SUGGESTION ("
			+"id,staff_id,url,detail,state,eff_date"
			+") values (NXY_DSAS_SUGGESTION_ID.nextval,?,?,?,1,sysdate)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staffId);
		ps.setString(2, url);
		ps.setString(3, detail);
		ps.execute();
		ps.close();
	}
	
//	suggestionManage.jsp
	public ArrayList<Suggest> showSuggest() throws SQLException{
		ArrayList<Suggest> suggestList = new ArrayList<Suggest>();
		Suggest sugggest = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_suggestion order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			sugggest = new Suggest();
			sugggest.setId(rs.getLong("id"));
			sugggest.setStaffId(rs.getString("staff_id"));
			sugggest.setUrl(rs.getString("url"));
			sugggest.setDetail(rs.getString("detail"));
			sugggest.setStateString(rs.getInt("state"));
			sugggest.setEffDateString(rs.getString("eff_date").substring(0, 19));
			if (rs.getString("exp_date")!=null){
				sugggest.setExpDateString(rs.getString("exp_date").substring(0, 19));
			} else {
				sugggest.setExpDateString("");
			}
			suggestList.add(sugggest);
		}
		rs.close();
		st.close();
		return suggestList;
	}
}
