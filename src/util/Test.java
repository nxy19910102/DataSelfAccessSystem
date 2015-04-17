package util;

import java.sql.SQLException;
import java.util.Date;

import applicationDAO.DocumentBackupDAO;

public class Test {

	public static void main(String[] args) {
		String saveFile = "E://ok";
		DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
		try {
			documentBackupDAO.addDocumentBackupAttachment(1, 1, 1, saveFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
