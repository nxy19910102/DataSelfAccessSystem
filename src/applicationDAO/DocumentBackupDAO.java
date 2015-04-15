package applicationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBConnect;

public class DocumentBackupDAO {
	public boolean addDocumentBackupDAO(int target, int targetYear, int targetSeq, String docTitle, String docStaff, String docDepartment, int startYear, int startMonth, int startDay, int dealYear, int dealMonth, int dealDay, String docDetail, String attachmentAddress, String dealStaff) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into NXY_DSAS_DOCUMENT_BACKUP ("
			+ "id,target,target_year,target_seq,doc_title,doc_staff,"
			+ "doc_department,start_year,start_month,start_day,deal_year,"
			+ "deal_month,deal_day,doc_detail,attachment_address,deal_staff"
			+ ") values (NXY_DSAS_DOCUMENT_BACKUP_id.nextVal,?,?,?,?,?,?,?,?,"
			+ "?,?,?,?,?,?,?)";
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
		ps.setString(14, attachmentAddress);
		ps.setString(15, dealStaff);
		ps.execute();
		ps.close();
		return true;
	}
}
