package administrationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import administrationEntity.Staff;
import util.DBConnect;

public class StaffDAO {
	
	public void addStaff(Staff staff) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into nxy_dsas_staff ("
			+"id,staff_id,password,staff_name,department,state,update_user,update_date,eff_date,exp_date,password_tip"
			+") values (nxy_dsas_staff_id.nextVal,?,?,?,?,0,?,sysdate,sysdate,sysdate+100,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staff.getStaffId());
		ps.setString(2, staff.getPassword());
		ps.setString(3, staff.getStaffName());
		ps.setString(4, staff.getDepartment());
		ps.setString(5, staff.getUpdateUser());
		ps.setString(6, staff.getPasswordTip());
		ps.execute();
		ps.close();
	}

	public void delStaff(Staff staff) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "update nxy_dsas_staff set state=2 "
			+"where state=0 and staff_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staff.getStaffId());
		ps.execute();
		ps.close();
	}

	public void updateStaff(Staff staff) throws SQLException{
		delStaff(staff);
		addStaff(staff);
	}

//	staffManage.jsp
	public ArrayList<Staff> showStaff() throws SQLException{
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		Staff staff = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_staff "
			+"where state=0 order by staff_id";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			staff = new Staff();
			staff.setStaffId(rs.getString("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setDepartment(rs.getString("department"));
			staffList.add(staff);
		}
		rs.close();
		st.close();
		return staffList;
	}

//	staffDetail.jsp
	public ArrayList<Staff> getStaffByStaffId(String staffId) throws SQLException{
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		Staff staff = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_staff "
			+"where staff_id=? order by id desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, staffId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			staff = new Staff();
			staff.setId(rs.getLong("id"));
			staff.setStaffId(rs.getString("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setDepartment(rs.getString("department"));
			staff.setStateString(rs.getInt("state"));
			staff.setUpdateUser(rs.getString("update_user"));
			staff.setUpdateDateString(rs.getString("update_date").substring(0, 19));
			staff.setEffDateString(rs.getString("eff_date").substring(0, 19));
			staff.setExpDateString(rs.getString("exp_date").substring(0, 19));
			staffList.add(staff);
		}
		rs.close();
		ps.close();
		return staffList;
	}

//	LoginServlet.java
	public boolean judgeLogin(String staffId,String password) throws SQLException{
		ArrayList<Staff> StaffList = new ArrayList<Staff>();
		Staff staff = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_staff "
			+"where state=0 order by id";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			staff = new Staff();
			staff.setStaffId(rs.getString("staff_id"));
			staff.setPassword(rs.getString("password"));
			StaffList.add(staff);
		}
		rs.close();
		st.close();
		
		Staff staff2 = new Staff();
		staff2.setStaffId(staffId);
		staff2.setPassword(password);
		for (Staff staff1:StaffList){
			if (staff2.equals(staff1)){
				return true;
			}
		}
		return false;
	}
}
