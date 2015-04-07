package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private FilterConfig config;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		
		//过滤例外
		String filterException = config.getInitParameter("filterException");
		if (filterException!=null){
			String[] filterExceptions = filterException.split(";");
			for (String Exception : filterExceptions){
				if (Exception==null || Exception.equals("")){
					continue;
				}
				if (request.getRequestURI().indexOf(Exception)!=-1){
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
			if (session.getAttribute("staff_id")!=null){
				arg2.doFilter(arg0, arg1);
				return;
			}else{
				response.sendRedirect(path+"/index.jsp");
			}
		}else{
			arg2.doFilter(arg0, arg1);
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}
}
