package applicationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import applicationEntity.Backup;
import util.DBConnect;

public class BackupDAO {
//	DoServlet.java
	public boolean addBackup (int target, int targetYear, int targetSeq, String applyStaff, String approveStaff, String accNbr, String offerOld, String offerNew, String promotion, String effYear, String effMonth, String effDay, String expYear, String expMonth, String expDay, String note) throws SQLException{
		Connection conn = DBConnect.getConnection();
		String sql = "insert into NXY_DSAS_BACKUP ("
			+ "id,target,target_year,target_seq,apply_staff,approve_staff,"
			+ "acc_nbr,offer_old,offer_new,promotion,eff_date,exp_date,note,"
			+ "state"
			+ ") values (NXY_DSAS_BACKUP_id.nextVal,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, target);
		ps.setInt(2, targetYear);
		ps.setInt(3, targetSeq);
		ps.setString(4, applyStaff);
		ps.setString(5, approveStaff);
		ps.setString(6, accNbr);
		ps.setString(7, offerOld);
		ps.setString(8, offerNew);
		ps.setString(9, promotion);
		ps.setString(10, effYear + effMonth + effDay);
		ps.setString(11, expYear + expMonth + expDay);
		ps.setString(12, note);
		ps.execute();
		ps.close();
		return true;
	}
	
//	DoServlet.java
	public boolean addBackups (int target, int targetYear, int targetSeq, String applyStaff, String approveStaff, String accNbr, String offerOld, String offerNew, String promotion, String effYear, String effMonth, String effDay, String expYear, String expMonth, String expDay, String note) throws SQLException{
		String accNbrs[] = accNbr.replace("，", ",").split(",");
		Connection conn = DBConnect.getConnection();
		for (int i=0;i<accNbrs.length;i++){
			if (!accNbrs[i].equals("")){
				String sql = "insert into NXY_DSAS_BACKUP ("
					+ "id,target,target_year,target_seq,apply_staff,"
					+ "approve_staff,acc_nbr,offer_old,offer_new,promotion,"
					+ "eff_date,exp_date,note,state"
					+ ") values (NXY_DSAS_BACKUP_id.nextVal,?,?,?,?,?,?,?,?,?,?,?,?,0)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, target);
				ps.setInt(2, targetYear);
				ps.setInt(3, targetSeq);
				ps.setString(4, applyStaff);
				ps.setString(5, approveStaff);
				ps.setString(6, accNbrs[i]);
				ps.setString(7, offerOld);
				ps.setString(8, offerNew);
				ps.setString(9, promotion);
				ps.setString(10, effYear + effMonth + effDay);
				ps.setString(11, expYear + expMonth + expDay);
				ps.setString(12, note);
				ps.execute();
				ps.close();
			}
		}
		return true;
	}

//	UploadServlet.java
	public boolean addBackupAttachment (int target, int targetYear, int targetSeq, String attachmentAddress) throws SQLException{
			Connection conn = DBConnect.getConnection();
			String sql = "update NXY_DSAS_BACKUP set attachment_address = ? "
				+ "where state = 0 and target = ? and target_year = ? and target_seq = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, attachmentAddress);
			ps.setInt(2, target);
			ps.setInt(3, targetYear);
			ps.setInt(4, targetSeq);
			ps.execute();
			ps.close();
			return true;
		}
	
//	backupQuery
	public ArrayList<Backup> showBackup(int target, String targetYear, String targetSeq, String applyStaff, String approveStaff, String accNbr, String effYear, String effMonth, String effDay, String expYear, String expMonth, String expDay) throws SQLException{
		StringBuffer stringBufferBuilder = new StringBuffer();
		stringBufferBuilder.append("select * from nxy_dsas_backup where state=0");
		if (target != 0){
			stringBufferBuilder.append(" and target=" + target);
		}
		if (targetYear != null && targetYear != ""){
			stringBufferBuilder.append(" and target_year=" + targetYear);
		}
		if (targetSeq != null && targetSeq != ""){
			stringBufferBuilder.append(" and target_seq=" + targetSeq);
		}
		if (applyStaff != null && applyStaff != ""){
			stringBufferBuilder.append(" and apply_staff like '%" + applyStaff + "%'");
		}
		if (approveStaff != null && approveStaff != ""){
			stringBufferBuilder.append(" and approve_staff like '%" + approveStaff + "%'");
		}
		if (accNbr != null && accNbr != ""){
			stringBufferBuilder.append(" and acc_nbr like '%" + accNbr + "%'");
		}
		if (effYear != null && effYear != ""){
			stringBufferBuilder.append(" and substr(eff_date,1,4) like '%" + effYear + "%'");
		}
		if (effMonth != null && effMonth != ""){
			stringBufferBuilder.append(" and substr(eff_date,5,2) like '%" + effMonth + "%'");
		}
		if (effDay != null && effDay != ""){
			stringBufferBuilder.append(" and substr(eff_date,7,2) like '%" + effDay + "%'");
		}
		if (expYear != null && expYear != ""){
			stringBufferBuilder.append(" and substr(exp_date,1,4) like '%" + expYear + "%'");
		}
		if (expMonth != null && expMonth != ""){
			stringBufferBuilder.append(" and substr(exp_date,5.2) like '%" + expMonth + "%'");
		}
		if (expDay != null && expDay != ""){
			stringBufferBuilder.append(" and substr(exp_date,7,2) like '%" + expDay + "%'");
		}
		stringBufferBuilder.append(" order by id desc");
		ArrayList<Backup> backupList = new ArrayList<Backup>();
		Backup backup = null;
		Connection conn = DBConnect.getConnection();
		Statement st = conn.createStatement();
		String sql = stringBufferBuilder.toString();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			backup = new Backup();
			backup.setId(rs.getLong("id"));
			backup.setTargetString(rs.getInt("target"));
			backup.setTargetYear(rs.getInt("target_year"));
			backup.setTargetSeq(rs.getInt("target_seq"));
			backup.setApplyStaff(rs.getString("apply_staff"));
			backup.setApproveStaff(rs.getString("approve_staff"));
			backup.setAccNbr(rs.getString("acc_nbr"));
			backup.setOfferOld(rs.getString("offer_old"));
			backup.setOfferNew(rs.getString("offer_new"));
			backup.setPromotion(rs.getString("promotion"));
			backup.setEffDate(rs.getString("eff_date"));
			backup.setExpDate(rs.getString("exp_date"));
			backup.setNote(rs.getString("note"));
			backup.setAttachmentAddress(rs.getString("attachment_address"));
			backupList.add(backup);
		}
		rs.close();
		st.close();
		return backupList;
	}
}
