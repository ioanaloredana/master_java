package ro.tirzuman.ioana.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.tirzuman.ioana.dao.CategoryRepository;

public class AddRecordController extends HttpServlet {

	private static final long serialVersionUID = 7524137982779010513L;
	private static final String COOKIE_CATEGORY = "java-lab2.category";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categories", CategoryRepository.getAll());
		checkCookies(request);
		getServletContext().getRequestDispatcher("/view/input.jsp").forward(request, response);
	}

	
	private void checkCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_CATEGORY)) {
					String value = cookie.getValue();
					request.setAttribute("selectedCategory", value);
					break;
				}
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
