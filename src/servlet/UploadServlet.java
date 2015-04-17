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
import javax.servlet.http.HttpSession;

import applicationDAO.DocumentBackupDAO;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 4689917345506137384L;

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
		switch (uploadKey){
		case ("documentBackupAttachmentUpload"):
			int target = Integer.parseInt(request.getParameter("target"));
			int targetYear = Integer.parseInt(request.getParameter("targetYear"));
			int targetSeq = Integer.parseInt(request.getParameter("targetSeq"));
			filePath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/documentBackup";
//			filePath = "E:/talent_path/Myeclipse/DataSelfAccessSystem/WebRoot/files/documentBackup";
//			filePath = "D:/WEBROOT/DataSelfAccessSystem/WebRoot/files/documentBackup";
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
	}

	public void init() throws ServletException {
	}
	
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
		//设置保存上传文件的路径
		File fileupload = new File(filePath);
		if (!fileupload.exists()){
			fileupload.mkdir();
		}
		//获取上传文件名称
		randomFile.seek(0);
		randomFile.readLine();
		String str = randomFile.readLine();
		//webkit
//		int beginIndex = str.lastIndexOf("filename=\"") + 10;
		//IE
//		int beginIndex = str.lastIndexOf("\\") + 1;
		//后缀
		int beginIndex = str.lastIndexOf(".");
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
	
	public String generateFilename (HttpServletRequest request, String uploadKey, String fileNameSuffix) {
		switch (uploadKey){
		case ("documentBackupAttachmentUpload"):
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String docDepartment = request.getParameter("docDepartment");
			String docStaff = request.getParameter("docStaff");
			String filename = docDepartment + "-" + docStaff + "-" + sdf.format(new Date()) + fileNameSuffix;
			return filename;
		default :
		return "none";
		}
	}
}
