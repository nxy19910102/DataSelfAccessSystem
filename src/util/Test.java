package util;

import java.sql.SQLException;

import applicationDAO.DocumentBackupDAO;

public class Test {

	public static void main(String[] args) {
		DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
		try {
			documentBackupDAO.addDocumentBackupDAO(0, 2015, 1, "docTitle", "docStaff", "docDepartment", 2015, 04, 14, 2015, 04, 15, "docDetail", "attachmentAddress", "dealStaff");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
