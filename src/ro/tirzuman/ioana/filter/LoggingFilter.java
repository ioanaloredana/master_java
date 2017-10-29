package ro.tirzuman.ioana.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter {

	private static final String LOG_DELIMITER = " | ";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		logRequestDetails(request);
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private void logRequestDetails(HttpServletRequest req) {

		String temp = req.getMethod() + LOG_DELIMITER + req.getRemoteAddr() + LOG_DELIMITER + req.getRemotePort()
				+ LOG_DELIMITER + req.getHeader("User-Agent") + LOG_DELIMITER + req.getHeader("Accept-Language ")
				+ LOG_DELIMITER + req.getParameterMap();
		req.getServletContext().log(temp);
		// System.out.println(temp);
	}

}
