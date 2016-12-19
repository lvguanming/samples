package ramp.sample.log4jmdc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rama Palaniappan
 * @since Oct 11, 2013
 */
public class HelloWorldServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(HelloWorldServlet.class);

	public HelloWorldServlet() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logSomeMessages(request, response);
	}

	protected void logSomeMessages(HttpServletRequest request,
			HttpServletResponse response) {
		LOG.info("Info message");
		LOG.warn("Warning Message");
		sleep(1000);
		LOG.error("Error Message");
		LOG.debug("Debug Message");
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			LOG.error("Sleep interrupted", e);
		}
	}
}
