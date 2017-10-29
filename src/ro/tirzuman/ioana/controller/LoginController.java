package ro.tirzuman.ioana.controller;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.tirzuman.ioana.dao.AuthLogic;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 7524137982779010514L;
	private static final String SESSION_LOGIN = "loggedIn";
	private static final String COOKIE_LOGIN = "java-lab2.login";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkCookies(request);
		getServletContext().getRequestDispatcher("/view/input.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getAttribute("username");
		String password = (String) request.getAttribute("password");
		if (!isValidCredentials(username, password)) {
			String errorMessage = "The provided credentials are invalid";
			request.setAttribute("errorMessage", errorMessage);
			getServletContext().getRequestDispatcher("/view/login.jsp").forward(request, response);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute(SESSION_LOGIN, Boolean.TRUE);
		addCookie(response, username);

	}

	private void addCookie(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(COOKIE_LOGIN, username);
		cookie.setMaxAge(60 * 60 * 24 * 365 * 10); // in seconds, 10 years
		response.addCookie(cookie);
	}

	private boolean isValidCredentials(String username, String password) {
		validateInput(username);
		validateInput(password);
		return AuthLogic.isValidCredential(username, password);
	}

	private void validateInput(String input) {
		if (input == null || input.isEmpty()) {
			throw new InvalidParameterException("Invalid input.");
		}
	}

	private void checkCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_LOGIN)) {
					String username = cookie.getValue();
					request.setAttribute("username", username);
					break;
				}
			}
		}
	}
}
