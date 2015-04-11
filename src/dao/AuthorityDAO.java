package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import entity.Authority;
import util.DBConnect;

public class AuthorityDAO {
//	ContextListener.java
	public ArrayList<Authority> getAuthority () throws SQLException{
		ArrayList<Authority> authorityList = new ArrayList<Authority>();
		Authority authority = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from NXY_DSAS_AUTHORITY order by id";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			authority = new Authority();
			authority.setId(rs.getLong("id"));
			authority.setStaffId(rs.getString("staff_id"));
			authority.setAdministrator(rs.getInt("administrator"));
			authorityList.add(authority);
		}
		rs.close();
		st.close();
		return authorityList;
	}
	
//	mainForUser.jsp
	@SuppressWarnings("unchecked")
	public boolean checkAdministrator (HttpServletRequest request, String staffId) {
		ServletContext context = request.getSession().getServletContext();
		ArrayList<Authority> authorityList = (ArrayList<Authority>) context.getAttribute("authorityList");
		for (Authority authority : authorityList){
			if (authority.getStaffId().equals(staffId)){
				if (authority.getAdministrator()==1){
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
