package applicationEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseToExcel {
	private String[] title = {"NO","ACC_NBR","KHXZ","WANGGE"};
	public void xls (){
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet = workbook.createSheet();
		//创建第一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		//插入第一行
		for (int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//追加数据
		for (int i=1;i<10;i++){
			HSSFRow nextRow = sheet.createRow(i);
			HSSFCell cell2 = nextRow.createCell(0);
			cell2.setCellValue("a" + i);
		}
		//创建一个文件
		File file = new File("D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/testExcel.xls");
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void xlsx (){
		//创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建工作表
		Sheet sheet = workbook.createSheet();
		//创建第一行
		Row row = sheet.createRow(0);
		Cell cell = null;
		//插入第一行
		for (int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//追加数据
		for (int i=1;i<10;i++){
			Row nextRow = sheet.createRow(i);
			Cell cell2 = nextRow.createCell(0);
			cell2.setCellValue("a");
		}
		//创建一个文件
		File file = new File("D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/testExcelx.xlsx");
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
