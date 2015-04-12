package applicationEntity;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelToDatabase {
	
	public void getFile(){
		//读取文件
		File file = new File("D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/testExcel.xls");
		try {
			//创建excel，读取文件内容
			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			//获取工作表（workbook.getSheetAt("sheet1")）
			HSSFSheet sheet = workbook.getSheetAt(0);
			//获取行号
			int firstRowNum = 0;
			int lastRowNum = sheet.getLastRowNum();
			for (int i=firstRowNum;i<lastRowNum;i++){
				HSSFRow row = sheet.getRow(i);
				int lastCellNum =row.getLastCellNum();
				for (int j=0;j<lastCellNum;j++){
					HSSFCell cell =row.getCell(j);
					String value =cell.getStringCellValue();
					System.out.println(value + " ");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}
