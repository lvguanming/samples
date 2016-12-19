/**
 * 
 */
package ramp.sample.log4jmdc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

/**
 * @author Rama Palaniappan
 * @since Oct 11, 2013
 */
public class AppFilter implements Filter {

	private static final Log LOG = LogFactory.getLog(AppFilter.class);


	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	private enum MdcKey {
		TID("tid", "intuit_tid"), 
		APP_NAME("appName", "intuit_app_name"),
		TRAFFIC_TYPE("trafficType","Traffic-Type");

		String mdcKey = null;
		String httpHeaderName = null;

		MdcKey(String key, String httpHeaderName) {
			this.mdcKey = key;
			this.httpHeaderName = httpHeaderName;
		}

		public String key() {
			return mdcKey;
		}
		public String getHttpHeader() {
			return httpHeaderName;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			doFilter((HttpServletRequest) request,
					(HttpServletResponse) response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	private void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			addMdc(request);
			long start = System.currentTimeMillis();
			chain.doFilter(request, response);
			LOG.info("processingTime=" + (System.currentTimeMillis() - start));
		} finally {
			clearMdc();
		}
	}

	// To log these values, add these in your appender's ConversionPattern
	// %X{tid} %X{appName} %X{trafficType}
	// Sample logs
	// 2013-10-15 16:37:32,216 +0530 level=INFO
	// class=ramp.sample.log4jmdc.HelloWorldServlet tid=sample-tid
	// appName=sample-app trafficType=SANDBOX Info message
	// 2013-10-15 16:37:32,217 +0530 level=WARN
	// class=ramp.sample.log4jmdc.HelloWorldServlet tid=sample-tid
	// appName=sample-app trafficType=SANDBOX Warning Message
	// 2013-10-15 16:37:33,218 +0530 level=ERROR
	// class=ramp.sample.log4jmdc.HelloWorldServlet tid=sample-tid
	// appName=sample-app trafficType=SANDBOX Error Message
	// 2013-10-15 16:37:33,219 +0530 level=INFO
	// class=ramp.sample.log4jmdc.AppFilter tid=sample-tid appName=sample-app
	// trafficType=SANDBOX processingTime=1004
	private void addMdc(HttpServletRequest request) {
		for (MdcKey mdcKey : MdcKey.values()) {
			String value = request.getHeader(mdcKey.getHttpHeader());
			if (mdcKey != null && mdcKey.key() != null && value != null) {
				MDC.put(mdcKey.key(), mdcKey.key() + "=" + value);
			}
		}
	}

	private void clearMdc() {
		// MDC.clear();
		// Older log4j jar don't have clear method, so remove one my one
		for (MdcKey mdcKey : MdcKey.values()) {
			MDC.remove(mdcKey.key());
		}
	}

}
