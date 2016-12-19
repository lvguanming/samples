/**
 * 
 */
package ramp.sample.springjersey.resource;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;

/**
 * Adapted from http://blog.valotas.com/2012/04/quicker-jerseytest.html
 * 
 * @author rpalaniappan
 * 
 */
public class BaseJerseyTest extends JerseyTest {
	private static final AppDescriptor APP_DESCRIPTOR = new WebAppDescriptor.Builder()
			.servletClass(SpringServlet.class)
			.contextListenerClass(ContextLoaderListener.class)
			//Use actual applicationContext.xml which will call actual service implementation
//			.contextParam("contextConfigLocation",
//					"classpath:applicationContext.xml")
			//Use test application context.xml which will use actual resource but mock service
			.contextParam("contextConfigLocation",
					"classpath:testApplicationContext.xml")
			.requestListenerClass(RequestContextListener.class).build();

	public BaseJerseyTest() {
		super(APP_DESCRIPTOR);
	}

	public WebResource resource(MultivaluedMap<String, String> queryParams) {
		WebResource resource = super.resource();
		if (queryParams != null) {
			resource = resource.queryParams(queryParams);
		}
		return resource;
	}

	@BeforeSuite
	public void initTestContainer() throws Exception {
		setUp();
	}

	@Override
	protected TestContainerFactory getTestContainerFactory()
			throws TestContainerException {
		return new OnePerAppDescriptorTestContainerFactory(
				super.getTestContainerFactory());
	}

	@AfterSuite
	public void tearDownTestContainer() throws Exception {
		tearDown();
	}

}
