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
	private static final long serialVersionUID = 3L;

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
		String realPath = request.getParameter("realPath");
		int beginIndex = realPath.lastIndexOf("\\") + 1;
		String filename = realPath.substring(beginIndex);
		String filePath = realPath.replace("\\", "/");
		File file = new File(filePath);
		if (file.exists()){
			//设置响应类型(或application/octet-stream)
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
			//关闭流，释放资源
			outputStream.close();
			inputStream.close();
		} else {
			response.sendRedirect("login/downloadFailure.jsp");
		}
	}

	public void init() throws ServletException {
	}

}
