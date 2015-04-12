package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
//	path
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取文件下载路径
		String path = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/";
//		String path = "D:/talent_path/myeclipse/DataSelfAccessSystem/WebRoot/files/";
		String filename = request.getParameter("filename");
		File file = new File(path + filename);
		if (file.exists()){
			//设置响应类型(application/octet-stream)
			response.setContentType("application/x-msdownload");
			//设置头信息
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			InputStream inputStream = new FileInputStream(file);
			ServletOutputStream outputStream = response.getOutputStream();
			byte b[] = new byte[1024];
			int n;
			while ((n = inputStream.read(b)) != -1){
				outputStream.write(b, 0, n);
			}
			request.setAttribute("download", "文件下载成功");
			//关闭流，释放资源
			outputStream.close();
			inputStream.close();
		} else {
			request.setAttribute("download", "文件不存在下载失败");
			request.getRequestDispatcher("../functions/download.jsp").forward(request, response);
		}
	}

	public void init() throws ServletException {
	}

}
