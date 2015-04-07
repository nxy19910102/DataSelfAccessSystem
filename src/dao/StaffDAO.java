package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Staff;
import util.DBConnect;

public class StaffDAO {
	
	public void addStaff(Staff staff) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into nxy_dsas_staff ("
			+"id,staff_id,password,staff_name,department,state,update_user,update_date,eff_date,exp_date,is_admin,password_tip"
			+") values (nxy_dafs_staff_id.nextVal,?,?,?,?,'正常',?,sysdate,sysdate,sysdate+100,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staff.getStaff_id());
		ps.setString(2, staff.getPassword());
		ps.setString(3, staff.getStaff_name());
		ps.setString(4, staff.getDepartment());
		ps.setString(5, staff.getUpdate_user());
		ps.setString(6, staff.getIs_admin());
		ps.setString(7, staff.getPassword_tip());
		ps.execute();
		ps.close();
	}

	public void delStaff(Staff staff) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "update nxy_dsas_staff set state='注销' "
			+"where state='正常' and staff_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staff.getStaff_id());
		ps.execute();
		ps.close();
	}

	public void updateStaff(Staff staff) throws SQLException{
		delStaff(staff);
		addStaff(staff);
	}

	public ArrayList<Staff> showStaff() throws SQLException{
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		Staff staff = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_staff "
			+"where state='正常' order by staff_id";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			staff = new Staff();
			staff.setStaff_id(rs.getString("staff_id"));
			staff.setStaff_name(rs.getString("staff_name"));
			staff.setDepartment(rs.getString("department"));
			staffList.add(staff);
		}
		rs.close();
		st.close();
		return staffList;
	}

	public ArrayList<Staff> getStaffByStaff_id(String staff_id) throws SQLException{
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		Staff staff = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_staff "
			+"where staff_id=? order by id desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staff_id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			staff = new Staff();
			staff.setId(rs.getInt("id"));
			staff.setStaff_id(rs.getString("staff_id"));
			staff.setStaff_name(rs.getString("staff_name"));
			staff.setDepartment(rs.getString("department"));
			staff.setState(rs.getString("state"));
			staff.setUpdate_user(rs.getString("update_user"));
			staff.setUpdate_date(rs.getDate("update_date"));
			staff.setEff_date(rs.getDate("eff_date"));
			staff.setExp_date(rs.getDate("exp_date"));
			staff.setIs_admin(rs.getString("is_admin"));
			staffList.add(staff);
		}
		rs.close();
		ps.close();
		return staffList;
	}

	public boolean judgeLogin(String staff_id,String password) throws SQLException{
		ArrayList<Staff> StaffList = new ArrayList<Staff>();
		Staff staff = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_staff "
			+"where state='正常' order by staff_id";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			staff = new Staff();
			staff.setStaff_id(rs.getString("staff_id"));
			staff.setPassword(rs.getString("password"));
			StaffList.add(staff);
		}
		rs.close();
		st.close();
		
		Staff staff2 = new Staff();
		staff2.setStaff_id(staff_id);
		staff2.setPassword(password);
		for (Staff staff1:StaffList){
			if (staff2.equals(staff1)){
				return true;
			}
		}
		return false;
	}
}
