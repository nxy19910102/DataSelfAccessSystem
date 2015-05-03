package util;

import java.sql.SQLException;
import java.util.Date;

import applicationDAO.DocumentBackupDAO;
import applicationEntity.DatabaseToExcel;

public class Test {

	public static void main(String[] args) {
		DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
		try {
			documentBackupDAO.showDocumentBackup("1", null, "3", "4", "5", "6", "7", "8", "9", "10", "11");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
