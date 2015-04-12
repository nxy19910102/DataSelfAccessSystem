package util;

import applicationEntity.DatabaseToExcel;
import applicationEntity.ExcelToDatabase;


public class Test {

	public static void main(String[] args) {
		DatabaseToExcel databaseToExcel = new DatabaseToExcel();
		databaseToExcel.xlsx();
		databaseToExcel.xls();
	}

}
