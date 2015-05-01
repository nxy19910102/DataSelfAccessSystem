package applicationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import administrationEntity.Staff;
import applicationEntity.DocumentBackup;
import util.DBConnect;

public class DocumentBackupDAO {
//	DoServlet.java
	public boolean addDocumentBackup (int target, int targetYear, int targetSeq, String docTitle, String docStaff, String docDepartment, int startYear, int startMonth, int startDay, int dealYear, int dealMonth, int dealDay, String docDetail, String dealStaff) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into NXY_DSAS_DOCUMENT_BACKUP ("
			+ "id,target,target_year,target_seq,doc_title,doc_staff,"
			+ "doc_department,start_year,start_month,start_day,deal_year,"
			+ "deal_month,deal_day,doc_detail,deal_staff,doc_state"
			+ ") values (NXY_DSAS_DOCUMENT_BACKUP_id.nextVal,?,?,?,?,?,?,?,"
			+ "?,?,?,?,?,?,?,0)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, target);
		ps.setInt(2, targetYear);
		ps.setInt(3, targetSeq);
		ps.setString(4, docTitle);
		ps.setString(5, docStaff);
		ps.setString(6, docDepartment);
		ps.setInt(7, startYear);
		ps.setInt(8, startMonth);
		ps.setInt(9, startDay);
		ps.setInt(10, dealYear);
		ps.setInt(11, dealMonth);
		ps.setInt(12, dealDay);
		ps.setString(13, docDetail);
		ps.setString(14, dealStaff);
		ps.execute();
		ps.close();
		return true;
	}
	
//	DoServlet.java
	public boolean addDocumentBackupAttachment (int target, int targetYear, int targetSeq, String attachmentAddress) throws SQLException{
			Connection conn = DBConnect.getConnection();
			String sql = "update NXY_DSAS_DOCUMENT_BACKUP set attachment_address = ? "
				+ "where doc_state = 0 and target = ? and target_year = ? and target_seq = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, attachmentAddress);
			ps.setInt(2, target);
			ps.setInt(3, targetYear);
			ps.setInt(4, targetSeq);
			ps.execute();
			ps.close();
			return true;
		}
	
	public ArrayList<DocumentBackup> showDocumentBackup() throws SQLException{
		ArrayList<DocumentBackup> documentBackupList = new ArrayList<DocumentBackup>();
		DocumentBackup documentBackup = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_dsas_document_backup "
			+"where doc_state=0 order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			documentBackup = new DocumentBackup();
			documentBackup.setTargetString(rs.getInt("target"));
			documentBackup.setTargetYear(rs.getInt("target_year"));
			documentBackup.setTargetSeq(rs.getInt("target_seq"));
			documentBackup.setDocTitle(rs.getString("doc_title"));
			documentBackup.setDocStaff(rs.getString("doc_staff"));
			documentBackup.setDocDepartment(rs.getString("doc_department"));
			documentBackup.setStartYear(rs.getInt("start_year"));
			documentBackup.setStartMonth(rs.getInt("start_month"));
			documentBackup.setStartDay(rs.getInt("start_day"));
			documentBackup.setDealYear(rs.getInt("deal_year"));
			documentBackup.setDealMonth(rs.getInt("deal_month"));
			documentBackup.setDealDay(rs.getInt("deal_day"));
			documentBackup.setDocDetail(rs.getString("doc_detail"));
			documentBackup.setAttachmentAddress(rs.getString("attachment_address"));
			documentBackup.setDealStaff(rs.getString("deal_staff"));
			documentBackupList.add(documentBackup);
		}
		rs.close();
		st.close();
		return documentBackupList;
	}
}
