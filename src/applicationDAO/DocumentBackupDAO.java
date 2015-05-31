package applicationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
//	UploadServlet.java
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
	
//	documentBackupQuery
	public ArrayList<DocumentBackup> showDocumentBackup(String targetYear,String targetSeq,String docTitle,String docStaff,String docDepartment,String startYear,String startMonth,String startDay,String dealYear,String dealMonth,String dealDay) throws SQLException{
		StringBuffer stringBufferBuilder = new StringBuffer();
		stringBufferBuilder.append("select * from nxy_dsas_document_backup where doc_state=0 ");
		if (targetYear != null && targetYear != ""){
			stringBufferBuilder.append("and target_year=" + targetYear + " ");
		}
		if (targetSeq != null && targetSeq != ""){
			stringBufferBuilder.append("and target_seq=" + targetSeq + " ");
		}
		if (docTitle != null && docTitle != ""){
			stringBufferBuilder.append("and doc_title like '%" + docTitle + "%' ");
		}
		if (docStaff != null && docStaff != ""){
			stringBufferBuilder.append("and doc_staff like '%" + docStaff + "%' ");
		}
		if (docDepartment != null && docDepartment != ""){
			stringBufferBuilder.append("and doc_department like '%" + docDepartment + "%' ");
		}
		if (startYear != null && startYear != ""){
			stringBufferBuilder.append("and start_year=" + startYear + " ");
		}
		if (startMonth != null && startMonth != ""){
			stringBufferBuilder.append("and start_month=" + startMonth + " ");
		}
		if (startDay != null && startDay != ""){
			stringBufferBuilder.append("and start_day=" + startDay + " ");
		}
		if (dealYear != null && dealYear != ""){
			stringBufferBuilder.append("and deal_year=" + dealYear + " ");
		}
		if (dealMonth != null && dealMonth != ""){
			stringBufferBuilder.append("and deal_month=" + dealMonth + " ");
		}
		if (dealDay != null && dealDay != ""){
			stringBufferBuilder.append("and deal_day=" + dealDay + " ");
		}
		ArrayList<DocumentBackup> documentBackupList = new ArrayList<DocumentBackup>();
		DocumentBackup documentBackup = null;
		Connection conn = DBConnect.getConnection();
		Statement st = conn.createStatement();
		String sql = stringBufferBuilder.toString();
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
