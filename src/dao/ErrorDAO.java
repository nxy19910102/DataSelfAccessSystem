package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Error500;
import util.DBConnect;

public class ErrorDAO {
	
	public static void addError(Error500 error) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into nxy_dsas_error ("
			+"id,staff_id,url,server_path,detail,state,eff_date"
			+") values (nxy_dafs_error_id.nextval,?,?,?,?,'未解决',sysdate)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, error.getStaff_id());
		ps.setString(2, error.getUrl());
		ps.setString(3, error.getServer_path());
		ps.setString(4, error.getDetail());
		ps.execute();
		ps.close();
	}
	
	public static void addError(String staff_id,String url,String serverPath,String detail) throws SQLException{
		Error500 error = new Error500();
		error.setStaff_id(staff_id);
		error.setUrl(url);
		error.setServer_path(serverPath);
		error.setDetail(detail);
		ErrorDAO.addError(error);
	}
	
	public ArrayList<Error500> showError() throws SQLException{
		ArrayList<Error500> errorList = new ArrayList<Error500>();
		Error500 error = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_error order by id desc";
		System.out.println(sql);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			error = new Error500();
			error.setId(rs.getInt("id"));
			error.setStaff_id(rs.getString("staff_id"));
			error.setUrl(rs.getString("url"));
			error.setDetail(rs.getString("detail"));
			error.setState(rs.getString("state"));
			error.setEff_dateString(rs.getString("eff_date").substring(0, 19));
			if (rs.getString("exp_date")!=null){
				error.setExp_dateString(rs.getString("exp_date").substring(0, 19));
			}
			errorList.add(error);
		}
		rs.close();
		st.close();
		return errorList;
	}
}
