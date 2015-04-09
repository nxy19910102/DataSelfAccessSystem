package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//从request获得流信息
		InputStream fileSource = request.getInputStream();
		String tempFileName = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/tempFile";
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
	}

	public void init() throws ServletException {
	}

}
