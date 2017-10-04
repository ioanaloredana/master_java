package ro.tirzuman.ioana.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class Lab1Servlet
 */
public class Lab1Servlet extends HttpServlet {
	
	private DataStore dataStore = new DataStore(); 
	private static final String LOG_DELIMITER = " | ";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lab1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logRequestDetails(request);
		
		response.setContentType("text/html;charset=UTF-8");
		//response.getWriter().println(dataStore.getData());
		
		Map<String, String> data = dataStore.getData(); 
		for (Map.Entry<String, String> entry : data.entrySet()) {
			PrintWriter writer = response.getWriter();
			writer.println(entry.getKey() + " " + entry.getValue());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logRequestDetails(request);
		
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		dataStore.put(key, value);
		
		response.setContentType("text/html");
		
		PrintWriter out = new PrintWriter(response.getWriter());
		
		out.println("<html><head><title>Response</title></head>");
		out.println("<body>The list of key-values is: " + dataStore.getData());
		out.println("</body></html>");
		out.close();
		
	}
	

	private void logRequestDetails(HttpServletRequest req) {
		 
		 String  temp = req.getMethod() + LOG_DELIMITER + req.getRemoteAddr() + LOG_DELIMITER + req.getRemotePort()
			+ LOG_DELIMITER + req.getHeader("User-Agent") + LOG_DELIMITER + req.getHeader("Accept-Language ")
			+ LOG_DELIMITER + req.getParameterMap() + LOG_DELIMITER + req.getHeader("geckotrail");
		 getServletContext().log(temp);
		 System.out.println(temp);
	}

}
