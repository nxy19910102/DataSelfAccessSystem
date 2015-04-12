package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UploadServlet extends HttpServlet {
//	tempFileName,realPath
	private static final long serialVersionUID = 4689917345506137384L;

	public void destroy() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//从request获得流信息
		InputStream fileSource = request.getInputStream();
		String tempFileName = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/tempFile";
//		String tempFileName = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/tempFile";
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
		
		//获取上传文件名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("filename=\"") + 10;
		int endIndex = str.lastIndexOf("\"");
//		String filename = str.substring(beginIndex, endIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = session.getAttribute("staffId") 
			+ "-" + sdf.format(new Date()) 
			+ "-" + str.substring(beginIndex, endIndex);
		
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

		//设置保存上传文件的路径
		String realPath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files";
//		String realPath = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files";
		File fileupload = new File(realPath);
		if (!fileupload.exists()){
			fileupload.mkdir();
		}
		File saveFile = new File(realPath, filename);
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
//		tempFile.delete();
		
		request.setAttribute("upload", "上传成功");
		request.getRequestDispatcher("../functions/upload.jsp").forward(request, response);
	}

	public void init() throws ServletException {
	}
}
