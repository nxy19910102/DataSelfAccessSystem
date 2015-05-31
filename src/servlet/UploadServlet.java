package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationDAO.BackupDAO;
import applicationDAO.DocumentBackupDAO;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	public void destroy() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = null;
		String uploadKey = request.getParameter("uploadKey");
		switch (uploadKey) {
		case ("documentBackupAttachmentUpload"):{
			int target = Integer.parseInt(request.getParameter("target"));
			int targetYear = Integer.parseInt(request.getParameter("targetYear"));
			int targetSeq = Integer.parseInt(request.getParameter("targetSeq"));
			filePath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/documentBackup";
//			filePath = "E:/talent_path/Myeclipse/DataSelfAccessSystem/WebRoot/files/documentBackup";
//			filePath = "D:/WEBROOT/DataSelfAccessSystem/WebRoot/files/documentBackup";
			File file = new File(filePath);
			if (!file.exists()){
				file.mkdir();
			}
			String saveFile = this.doUpload (request, uploadKey, filePath);
			DocumentBackupDAO documentBackupDAO = new DocumentBackupDAO();
			try {
				documentBackupDAO.addDocumentBackupAttachment(target, targetYear, targetSeq, saveFile);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//回到原文件
			request.getRequestDispatcher("servlet.do?operate=" + uploadKey).forward(request, response);
			break;
		}
		case ("backupAttachmentUpload"):{
			int target = Integer.parseInt(request.getParameter("target"));
			int targetYear = Integer.parseInt(request.getParameter("targetYear"));
			int targetSeq = Integer.parseInt(request.getParameter("targetSeq"));
			filePath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/backup";
//			filePath = "E:/talent_path/Myeclipse/DataSelfAccessSystem/WebRoot/files/backup";
//			filePath = "D:/WEBROOT/DataSelfAccessSystem/WebRoot/files/backup";
			//设置保存上传文件的路径
			File file = new File(filePath);
			if (!file.exists()){
				file.mkdir();
			}
			String saveFile = this.doUpload (request, uploadKey, filePath);
			BackupDAO backupDAO = new BackupDAO();
			try {
				backupDAO.addBackupAttachment(target, targetYear, targetSeq, saveFile);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//回到原文件
			request.getRequestDispatcher("servlet.do?operate=" + uploadKey).forward(request, response);
			break;
		}
		}
	}

	public void init() throws ServletException {
	}
	
	//用于将文件上传到临时文件，再写入目标文件
	public String doUpload (HttpServletRequest request, String uploadKey, String filePath) throws IOException {
		Date date = new Date();
		//从request获得流信息
		InputStream fileSource = request.getInputStream();
		String tempFileName = filePath + "/temp" + date.getTime();
		//tempFile指向临时文件
		File tempFile = new File(tempFileName);
		//outputStream输出流指向临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[1024];
		int n;
		while ((n = fileSource.read(b)) != -1){
			outputStream.write(b, 0, n);
		}
		//关闭输出流、输入流
		outputStream.close();
		fileSource.close();
		
		RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
		//重新定位文件指针到文件头
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;
		//获取文件内容开始位置
		while ((n = randomFile.readByte()) != -1 && i <= 4){
			if (n == '\n'){
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		//获取文件内容结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while (endPosition >= 0 && j <= 2){
			endPosition--;
			randomFile.seek(endPosition);
			if (randomFile.readByte() == '\n'){
				j++;
			}
		}
		endPosition--;
		//获取上传文件名称
		randomFile.seek(0);
		randomFile.readLine();
		String str = randomFile.readLine();
//		String header = request.getHeader("user-agent");
//		String brower = "";
//		if (header.indexOf("Chrome") != -1){
//			brower = "Chrome";
//		} else if (header.indexOf("IE") != -1){
//			//IE6
//			brower = "IE";
//		}
		//前缀
//		int beginIndex = 0;
//		switch (brower){
//		case ("Chrome"):{
//			beginIndex = str.lastIndexOf("filename=\"") + 10;
//			break;
//		}
//		case ("IE"):{
//			beginIndex = str.lastIndexOf("\\") + 1;
//			break;
//		}
//		case (""):{
//			beginIndex = str.lastIndexOf(".");
//			break;
//		}
//		}
		int beginIndex = str.lastIndexOf(".");
		//后缀
		int endIndex = str.lastIndexOf("\"");
		
		String fileNameSuffix = str.substring(beginIndex, endIndex);
		String filename = this.generateFilename(request, uploadKey, fileNameSuffix);
				
		File saveFile = new File(filePath, filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
		//从临时文件中读取文件内容
		randomFile.seek(startPosition);
		while (startPosition < endPosition){
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		
		//关闭输入输出流，删除临时文件
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		return saveFile.toString();
	}
	
	//使用相应规则生成文件名
	public String generateFilename (HttpServletRequest request, String uploadKey, String fileNameSuffix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		switch (uploadKey) {
		case ("documentBackupAttachmentUpload"):{
			String docDepartment = request.getParameter("docDepartment");
			String docStaff = request.getParameter("docStaff");
			String filename = docDepartment + "-" + docStaff + "-" + sdf.format(new Date()) + fileNameSuffix;
			return filename;
		}
		case ("backupAttachmentUpload"):{
			String accNbr = request.getParameter("accNbr");
			String filename = sdf.format(new Date()) + "-" + accNbr + fileNameSuffix;
			return filename;
		}
		default :
		return "none";
		}
	}
}
