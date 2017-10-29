package ro.tirzuman.ioana.filter;

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

public class CredentialsFilter implements Filter {

	private static final String SESSION_LOGIN = "loggedIn";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession();
		if (session != null && session.getAttribute(SESSION_LOGIN) != null) {
			boolean isUserLoggedIn = (boolean) session.getAttribute(SESSION_LOGIN);
			if (!isUserLoggedIn) {
				redirectToLogin(request, response);
			}
		} else {
			redirectToLogin(request, response);
		}

		chain.doFilter(req, res);
	}

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String errorMessage = "Please login before accessing protected resources";
			request.setAttribute("errorMessage", errorMessage);
			request.getServletContext().getRequestDispatcher("/view/login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
