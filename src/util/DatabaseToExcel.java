package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import applicationDAO.BackupDAO;
import applicationEntity.Backup;

//filePath
public class DatabaseToExcel {
	String filePath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/testExcel.xls";
//	String filePath = "E:/talent_path/Myeclipse/DataSelfAccessSystem/WebRoot/files/Excel";
//	合并单元格
//	sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 3));
	@SuppressWarnings("resource")
	public String generateXls (HttpServletRequest request, String[] title, String operate){
		
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet = workbook.createSheet();
		//创建第一行
		HSSFRow row = sheet.createRow(0);
		HSSFRow nextRow = null;
		HSSFCell cell = null;
		HSSFCell nextCell = null;
		//插入第一行
		for (int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//追加数据
		switch (operate){
		case ("backupDownload"):{
			ArrayList<Backup> backupList;
			int target = Integer.parseInt(request.getParameter("target"));
			String targetYear = request.getParameter("targetYear");
			String targetSeq = request.getParameter("targetSeq");
			String applyStaff = request.getParameter("applyStaff");
			String approveStaff = request.getParameter("approveStaff");
			String accNbr = request.getParameter("accNbr");
			String effYear = request.getParameter("effYear");
			String effMonth = request.getParameter("effMonth");
			String effDay = request.getParameter("effDay");
			String expYear = request.getParameter("expYear");
			String expMonth = request.getParameter("expMonth");
			String expDay = request.getParameter("expDay");
			BackupDAO backupDAO = new BackupDAO();
			Backup backup = new Backup();
			try {
				backupList = backupDAO.showBackup(target, targetYear, targetSeq, applyStaff, approveStaff, accNbr, effYear, effMonth, effDay, expYear, expMonth, expDay);
				for (int i=0;i<backupList.size();i++){
					nextRow = sheet.createRow(i+1);
					backup = backupList.get(i);
					for (int j=0;j<title.length;j++){
						nextCell = nextRow.createCell(j);
						switch (j){
						case 0:
							nextCell.setCellValue(backup.getId());
							break;
						case 1:
							nextCell.setCellValue(backup.getTargetString() + "(" + backup.getTargetYear() + ")第" +backup.getTargetSeq() + "号");
							break;
						case 2:
							nextCell.setCellValue(backup.getApplyStaff());
							break;
						case 3:
							nextCell.setCellValue(backup.getApproveStaff());
							break;
						case 4:
							nextCell.setCellValue(backup.getAccNbr());
							break;
						case 5:
							nextCell.setCellValue(backup.getOfferOld());
							break;
						case 6:
							nextCell.setCellValue(backup.getOfferNew());
							break;
						case 7:
							nextCell.setCellValue(backup.getPromotion());
							break;
						case 8:
							nextCell.setCellValue(backup.getEffDate());
							break;
						case 9:
							nextCell.setCellValue(backup.getExpDate());
							break;
						case 10:
							nextCell.setCellValue(backup.getNote());
							break;
						case 11:
							nextCell.setCellValue(backup.getAttachmentAddress());
							break;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		}
		
		//创建一个文件
		File file = new File(filePath);
		if (!file.exists()){
			file.mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date()) + ".xls";
		file = new File(filePath, fileName);
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String sysPath = filePath + "/" + fileName;
		String realPath = sysPath.replace("/", "\\");
		return realPath;
	}
	
	@SuppressWarnings("resource")
	public String generateXlsx (HttpServletRequest request, String[] title, String operate){
		//创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建工作表
		Sheet sheet = workbook.createSheet();
		//创建第一行
		Row row = sheet.createRow(0);
		Row nextRow = null;
		Cell cell = null;
		Cell nextCell = null;
		//插入第一行
		for (int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//追加数据
		switch (operate){
		case ("backupDownload"):{
			ArrayList<Backup> backupList;
			int target = Integer.parseInt(request.getParameter("target"));
			String targetYear = request.getParameter("targetYear");
			String targetSeq = request.getParameter("targetSeq");
			String applyStaff = request.getParameter("applyStaff");
			String approveStaff = request.getParameter("approveStaff");
			String accNbr = request.getParameter("accNbr");
			String effYear = request.getParameter("effYear");
			String effMonth = request.getParameter("effMonth");
			String effDay = request.getParameter("effDay");
			String expYear = request.getParameter("expYear");
			String expMonth = request.getParameter("expMonth");
			String expDay = request.getParameter("expDay");
			BackupDAO backupDAO = new BackupDAO();
			Backup backup = new Backup();
			try {
				backupList = backupDAO.showBackup(target, targetYear, targetSeq, applyStaff, approveStaff, accNbr, effYear, effMonth, effDay, expYear, expMonth, expDay);
				for (int i=0;i<backupList.size();i++){
					nextRow = sheet.createRow(i+1);
					backup = backupList.get(i);
					for (int j=0;j<title.length;j++){
						nextCell = nextRow.createCell(j);
						switch (j){
						case 0:
							nextCell.setCellValue(backup.getId());
							break;
						case 1:
							nextCell.setCellValue(backup.getTargetString() + "(" + backup.getTargetYear() + ")第" +backup.getTargetSeq() + "号");
							break;
						case 2:
							nextCell.setCellValue(backup.getApplyStaff());
							break;
						case 3:
							nextCell.setCellValue(backup.getApproveStaff());
							break;
						case 4:
							nextCell.setCellValue(backup.getAccNbr());
							break;
						case 5:
							nextCell.setCellValue(backup.getOfferOld());
							break;
						case 6:
							nextCell.setCellValue(backup.getOfferNew());
							break;
						case 7:
							nextCell.setCellValue(backup.getPromotion());
							break;
						case 8:
							nextCell.setCellValue(backup.getEffDate());
							break;
						case 9:
							nextCell.setCellValue(backup.getExpDate());
							break;
						case 10:
							nextCell.setCellValue(backup.getNote());
							break;
						case 11:
							nextCell.setCellValue(backup.getAttachmentAddress());
							break;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		}
		
		//创建一个文件
		File file = new File(filePath);
		if (!file.exists()){
			file.mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date()) + ".xls";
		file = new File(filePath, fileName);
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String sysPath = filePath + "/" + fileName;
		String realPath = sysPath.replace("/", "\\");
		return realPath;
	}
}
