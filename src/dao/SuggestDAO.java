package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Suggest;
import util.DBConnect;

public class SuggestDAO {
	
	public static void addSuggest(Suggest sugggest) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into nxy_dsas_suggest ("
			+"id,staff_id,url,server_path,detail,state,eff_date"
			+") values (nxy_dafs_error_id.nextval,?,?,?,?,'未解决',sysdate)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sugggest.getStaff_id());
		ps.setString(2, sugggest.getUrl());
		ps.setString(3, sugggest.getServer_path());
		ps.setString(4, sugggest.getDetail());
		ps.execute();
		ps.close();
	}
	
	public static void addSuggest(String staff_id,String url,String serverPath,String detail) throws SQLException{
		Suggest sugggest = new Suggest();
		sugggest.setStaff_id(staff_id);
		sugggest.setUrl(url);
		sugggest.setServer_path(serverPath);
		sugggest.setDetail(detail);
		SuggestDAO.addSuggest(sugggest);
	}
	
	public ArrayList<Suggest> showSuggest() throws SQLException{
		ArrayList<Suggest> suggestList = new ArrayList<Suggest>();
		Suggest sugggest = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_suggest order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			sugggest = new Suggest();
			sugggest.setId(rs.getInt("id"));
			sugggest.setStaff_id(rs.getString("staff_id"));
			sugggest.setUrl(rs.getString("url"));
			sugggest.setDetail(rs.getString("detail"));
			sugggest.setState(rs.getString("state"));
			sugggest.setEff_dateString(rs.getString("eff_date").substring(0, 19));
			if (rs.getString("exp_date")!=null){
				sugggest.setExp_dateString(rs.getString("exp_date").substring(0, 19));
			}
			suggestList.add(sugggest);
		}
		rs.close();
		st.close();
		return suggestList;
	}
}
