package ro.tirzuman.ioana.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CopyrightFilter implements Filter {

	private static final String COPYRIGHT_TEXT = "Copyright Infoiasi 2017";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) res);
		// Send the decorated object as a replacement for the original response
		chain.doFilter(req, wrapper);

		// Get the dynamically generated content from the decorator
		String content = wrapper.toString();
		// Modify the content
		StringWriter sw = new StringWriter();
		sw.write(content);
		sw.write("<br>" + COPYRIGHT_TEXT + "<br>");
		// Send the modified content using the original response
		PrintWriter out = res.getWriter();
		out.write(sw.toString());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
