package applicationDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import util.DBConnect;
import applicationEntity.BusinessReport;

public class BusinessReportDAO {
	
	public ArrayList<BusinessReport> showBusinessReport () throws SQLException{
		ArrayList<BusinessReport> businessReportList = new ArrayList<BusinessReport>();
		BusinessReport businessReport = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from nxy_auto_report_day order by khxz1";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			businessReport = new BusinessReport();
			businessReport.setKhxz1(rs.getInt("khxz1"));
			businessReport.setKhxz(rs.getString("khxz"));
			businessReport.setYidongMubiao(rs.getInt("YIDONG_MB"));
			businessReport.setYidongXinzengLeiji(rs.getInt("YIDONG_XZ_LJ"));
			businessReport.setYidongXinzeng89(rs.getInt("YIDONG_XZ_89"));
			businessReport.setYidongXinzeng4zhe(rs.getInt("YIDONG_XZ_4Z"));
			businessReport.setYidongXinzeng01(rs.getInt("YIDONG_XZ_01"));
			businessReport.setYidongXinzeng02(rs.getInt("YIDONG_XZ_02"));
			businessReport.setYidongChaijiLeiji(rs.getInt("YIDONG_CJ_LJ"));
			businessReport.setYidongChaijiChuzhang(rs.getInt("YIDONG_CJ_CZ"));
			businessReport.setYidongChaiji89(rs.getInt("YIDONG_CJ_89"));
			businessReport.setYidongChaiji01(rs.getInt("YIDONG_CJ_01"));
			businessReport.setYidongChaiji02(rs.getInt("YIDONG_CJ_02"));
			businessReport.setYidongX(rs.getInt("YIDONG_X"));
			businessReport.setYidongWanchenglv(rs.getFloat("YIDONG_WCL"));
			businessReport.setKuandaiMubiao(rs.getInt("KUANDAI_MB"));
			businessReport.setKuandaiXinzengLeiji(rs.getInt("KUANDAI_XZ_LJ"));
			businessReport.setKuandaiXinzeng01(rs.getInt("KUANDAI_XZ_01"));
			businessReport.setKuandaiXinzeng02(rs.getInt("KUANDAI_XZ_02"));
			businessReport.setKuandaiChaijiLeiji(rs.getInt("KUANDAI_CJ_LJ"));
			businessReport.setKuandaiChaiji01(rs.getInt("KUANDAI_CJ_01"));
			businessReport.setKuandaiChaiji02(rs.getInt("KUANDAI_CJ_02"));
			businessReport.setKuandaiX(rs.getInt("KUANDAI_X"));
			businessReport.setKuandaiWanchenglv(rs.getFloat("KUANDAI_WCL"));
			businessReport.setItvMubiao(rs.getInt("ITV_MB"));
			businessReport.setItvXinzengLeiji(rs.getInt("ITV_XZ_LJ"));
			businessReport.setItvXinzeng01(rs.getInt("ITV_XZ_01"));
			businessReport.setItvXinzeng02(rs.getInt("ITV_XZ_02"));
			businessReport.setItvChaijiLeiji(rs.getInt("ITV_CJ_LJ"));
			businessReport.setItvChaiji01(rs.getInt("ITV_CJ_01"));
			businessReport.setItvChaiji02(rs.getInt("ITV_CJ_02"));
			businessReport.setItvX(rs.getInt("ITV_X"));
			businessReport.setItvWanchenglv(rs.getFloat("ITV_WCL"));
			businessReport.setLiuliangbaoMubiao(rs.getInt("LLB_MB"));
			businessReport.setLiuliangbaoLeiji(rs.getInt("LLB_LJ"));
			businessReport.setLiuliangkaLeiji(rs.getInt("LLK_LJ"));
			businessReport.setLiuliangbaoLeiji01(rs.getInt("LLB_LJ_01"));
			businessReport.setLiuliangbaoLeiji02(rs.getInt("LLB_LJ_02"));
			businessReport.setLiuliangbaoWanchenglv(rs.getFloat("LLB_WCL"));
			businessReport.setQianfei(rs.getInt("QF"));
			businessReport.setQianfeilv(rs.getFloat("QFL"));
			businessReport.setYusuan(rs.getInt("YUSUAN"));
			businessReport.setIphone60300(rs.getInt("IPHONE6_0300"));
			businessReport.setIphone60200(rs.getInt("IPHONE6_0200"));
			businessReport.setIphone60100(rs.getInt("IPHONE6_0100"));
			businessReport.setIphone6Mubiao(rs.getInt("IPHONE6_MB"));
			businessReport.setIphone60000(rs.getInt("IPHONE6_0000"));
			businessReport.setIphone601(rs.getInt("IPHONE6_01"));
			businessReport.setLexiang4g(rs.getInt("LEXIANG4G"));
			businessReport.setLexiang4g1(rs.getInt("LEXIANG4G_1"));
			businessReport.setLexiang3g(rs.getInt("LEXIANG3G"));
			businessReport.setLexiang3g1(rs.getInt("LEXIANG3G_1"));
			businessReport.setHuimingka(rs.getInt("HUIMINGKA"));
			businessReport.setHuimingka1(rs.getInt("HUIMINGKA_1"));
			businessReport.setXinfeiyoung(rs.getInt("XINFEIYOUNG"));
			businessReport.setXinfeiyoung1(rs.getInt("XINFEIYOUNG_1"));
			businessReport.setQita(rs.getInt("QITA"));
			businessReport.setQita1(rs.getInt("QITA_1"));
			businessReport.setChangdabao(rs.getInt("CHANGDABAO"));
			businessReport.setChangdabao1(rs.getInt("CHANGDABAO_1"));
			businessReportList.add(businessReport);
		}
		rs.close();
		st.close();
		return businessReportList;
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	public void exportXLS(String workbookName,String sheetName) throws SQLException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow[] row = null;
//		for (int i=0;i<10;i++){
//			row[i] = sheet.createRow(i);
//		}
		
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell cell00 = row0.createCell(0);
		cell00.setCellValue("移动业务");
		HSSFCell cell10 = row0.createCell(1);
		cell10.setCellValue("单位");
		row0 = sheet.createRow(1);
		HSSFCell cell11 = row0.createCell(1);
		cell11.setCellValue("当月净增目标");
		HSSFCell cell12 = row0.createCell(2);
		cell12.setCellValue("移动本月累计新增");
//		row = sheet.createRow(2);
//		cell = row.createCell(3);
//		cell.setCellValue("其中:89元以上");
//		row = sheet.createRow(2);
//		cell = row.createCell(4);
//		cell.setCellValue("其中:4折卡");
//		row = sheet.createRow(1);
//		cell = row.createCell(5);
//		cell.setCellValue("昨天新增");
//		row = sheet.createRow(1);
//		cell = row.createCell(6);
//		cell.setCellValue("前天新增");
//		row = sheet.createRow(1);
//		cell = row.createCell(7);
//		cell.setCellValue("移动本月累计拆机");
//		row = sheet.createRow(2);
//		cell = row.createCell(8);
//		cell.setCellValue("其中:上月出账");
//		row = sheet.createRow(2);
//		cell = row.createCell(9);
//		cell.setCellValue("其中:89元以上");
//		row = sheet.createRow(1);
//		cell = row.createCell(10);
//		cell.setCellValue("昨天拆机");
//		row = sheet.createRow(1);
//		cell = row.createCell(11);
//		cell.setCellValue("前天拆机");
//		row = sheet.createRow(1);
//		cell = row.createCell(12);
//		cell.setCellValue("移动当月累计净增");
//		row = sheet.createRow(1);
//		cell = row.createCell(13);
//		cell.setCellValue("完成率");
		
//		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 13));
//		sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0));
//		sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1));
//		sheet.addMergedRegion(new Region(1, (short) 2, 1, (short) 4));
//		sheet.addMergedRegion(new Region(1, (short) 5, 2, (short) 5));
//		sheet.addMergedRegion(new Region(1, (short) 6, 2, (short) 6));
//		sheet.addMergedRegion(new Region(1, (short) 7, 1, (short) 9));
//		sheet.addMergedRegion(new Region(1, (short) 10, 2, (short) 10));
//		sheet.addMergedRegion(new Region(1, (short) 11, 2, (short) 11));
//		sheet.addMergedRegion(new Region(1, (short) 12, 2, (short) 12));
//		sheet.addMergedRegion(new Region(1, (short) 13, 2, (short) 13));
		
		
//		ArrayList<BusinessReport> businessReportList = this.showBusinessReport();
//		BusinessReport businessReport = null;
//		for (int i=0;i<businessReportList.size();i++){
//			businessReport = businessReportList.get(i);
//			row = sheet.createRow(i+1);
//			for (int j=0;j<title.length;j++){
//				cell = row.createCell(j);
//				switch (j){
//				case 0:{
//					cell.setCellValue(businessReport.getKhxz1());
//					break;
//				}
//				case 1:{
//					cell.setCellValue(businessReport.getKhxz());
//					break;
//				}
//				}
//			}
//		}
//		String filePath = "E:/talent_path/Myeclipse/DataSelfAccessSystem/WebRoot/files/download/" + workbookName + ".xls";
		String filePath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/download/" + workbookName + ".xls";
		File file = new File(filePath);
		
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void exportXLSOrignal(String workbookName,String sheetName) throws SQLException{
//		此方法待续
		String[] title = {"KHXZ1", "KHXZ", "YIDONG_MB", "YIDONG_XZ_LJ", "YIDONG_XZ_89", "YIDONG_XZ_4Z", "YIDONG_XZ_01", "YIDONG_XZ_02", "YIDONG_CJ_LJ", "YIDONG_CJ_CZ", "YIDONG_CJ_89", "YIDONG_CJ_01", "YIDONG_CJ_02", "YIDONG_X", "YIDONG_WCL", "KUANDAI_MB", "KUANDAI_XZ_LJ", "KUANDAI_XZ_01", "KUANDAI_XZ_02", "KUANDAI_CJ_LJ", "KUANDAI_CJ_01", "KUANDAI_CJ_02", "KUANDAI_X", "KUANDAI_WCL", "ITV_MB", "ITV_XZ_LJ", "ITV_XZ_01", "ITV_XZ_02", "ITV_CJ_LJ", "ITV_CJ_01", "ITV_CJ_02", "ITV_X", "ITV_WCL", "LLB_MB", "LLB_LJ", "LLK_LJ", "LLB_LJ_01", "LLB_LJ_02", "LLB_WCL", "QF", "QFL", "YUSUAN", "IPHONE6_0300", "IPHONE6_0200", "IPHONE6_0100", "IPHONE6_MB", "IPHONE6_0000", "IPHONE6_01", "LEXIANG4G", "LEXIANG4G_1", "LEXIANG3G", "LEXIANG3G_1", "HUIMINGKA", "HUIMINGKA_1", "XINFEIYOUNG", "XINFEIYOUNG_1", "QITA", "QITA_1", "CHANGDABAO", "CHANGDABAO_1"};
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		for (int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		ArrayList<BusinessReport> businessReportList = this.showBusinessReport();
		BusinessReport businessReport = null;
		for (int i=0;i<businessReportList.size();i++){
			businessReport = businessReportList.get(i);
			row = sheet.createRow(i+1);
			for (int j=0;j<title.length;j++){
				cell = row.createCell(j);
				switch (j){
				case 0:{
					cell.setCellValue(businessReport.getKhxz1());
					break;
				}
				case 1:{
					cell.setCellValue(businessReport.getKhxz());
					break;
				}
				}
			}
		}
		String filePath = "E:/talent_path/Myeclipse/DataSelfAccessSystem/WebRoot/files/download/" + workbookName + ".xls";
		File file = new File(filePath);
		
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
