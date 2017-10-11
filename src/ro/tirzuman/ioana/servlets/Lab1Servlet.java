package ro.tirzuman.ioana.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lab1Servlet
 */
public class Lab1Servlet extends HttpServlet {

	private DataStore dataStore = new DataStore();
	private static final String LOG_DELIMITER = " | ";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		logRequestDetails(request);

		response.setContentType("text;charset=UTF-8");

		ArrayList<Element> data = dataStore.getData();
		for (Element entry : data) {
			PrintWriter writer = response.getWriter();
			writer.println(entry.getKey() + " " + entry.getValue());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logRequestDetails(request);

		String key = request.getParameter("key");
		String value = request.getParameter("value");
		dataStore.put(key, value);

		String userAgent = request.getHeader("User-Agent");
		if (userAgent == null || userAgent.toLowerCase().contains("java")
				|| !userAgent.toLowerCase().contains("webkit")) {
			doGet(request, response);
		} else {
			response.setContentType("text/html");

			PrintWriter out = new PrintWriter(response.getWriter());

			out.println("<html><head><title>Response</title></head>");
			out.println("<body>The list of key-values is: " + dataStore.getData());
			out.println("</body></html>");
			out.close();
		}
	}

	private void logRequestDetails(HttpServletRequest req) {

		String temp = req.getMethod() + LOG_DELIMITER + req.getRemoteAddr() + LOG_DELIMITER + req.getRemotePort()
				+ LOG_DELIMITER + req.getHeader("User-Agent") + LOG_DELIMITER + req.getHeader("Accept-Language ")
				+ LOG_DELIMITER + req.getParameterMap();
		getServletContext().log(temp);
		//System.out.println(temp);
	}

}
