package ro.tirzuman.ioana.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.tirzuman.ioana.dao.RecordRepository;
import ro.tirzuman.ioana.model.Record;
import ro.tirzuman.ioana.util.Util;

/**
 * Servlet implementation class Lab1Servlet
 */
public class RecordController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6471791868015497423L;
	private RecordRepository dataStore = new RecordRepository();
	private static final String SESSION_OBJ_RECORDS = "sessionRecords";
	private static final String COOKIE_CATEGORY = "java-lab2.category";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text;charset=UTF-8");

		List<Record> data = dataStore.getData();
		for (Record entry : data) {
			PrintWriter writer = response.getWriter();
			writer.println(entry.getAsLine());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			throw new InvalidParameterException("No session exists.");
		}
		String captchaSession = (String) session.getAttribute("captcha");
		String captchaRequest = (String) request.getParameter("captcha");
		if (captchaRequest == null || !captchaRequest.equals(captchaSession)) {
			throw new InvalidParameterException("Captcha validation failed");
		}

		String category = request.getParameter("category");
		String key = request.getParameter("key");
		String name = request.getParameter("name");
		validateInput(category);
		validateInput(key);
		validateInput(name);

		dataStore.put(category, key, name);

		List<Record> sessionRecords = null;
		if (session.getAttribute(SESSION_OBJ_RECORDS) == null) {
			sessionRecords = new ArrayList<Record>();
			session.setAttribute(SESSION_OBJ_RECORDS, sessionRecords);
		} else {
			sessionRecords = (List<Record>) session.getAttribute(SESSION_OBJ_RECORDS);
		}
		sessionRecords.add(new Record(category, key, name));

		boolean showRecordsOnlyFromSession = Util.getBoolean(request.getParameter("recordsFromSession"));

		if (!showRecordsOnlyFromSession) {
			request.setAttribute("records", dataStore.getData());
		} else {
			request.setAttribute("records", sessionRecords);
		}

		String userAgent = request.getHeader("User-Agent");
		if (userAgent == null || userAgent.toLowerCase().contains("java")
				|| !userAgent.toLowerCase().contains("webkit")) {
			doGet(request, response);
		} else {
			addCategoryCookie(response, category);
			getServletContext().getRequestDispatcher("/view/result.jsp").forward(request, response);
		}
	}

	private void validateInput(String input) {
		if (input == null || input.isEmpty()) {
			throw new InvalidParameterException("Invalid input.");
		}
	}

	private void addCategoryCookie(HttpServletResponse response, String category) {
		Cookie cookie = new Cookie(COOKIE_CATEGORY, category);
		cookie.setMaxAge(60 * 60 * 24 * 365 * 10); // in seconds, 10 years
		response.addCookie(cookie);
	}
}
