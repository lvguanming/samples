/**
 * 
 */
package ramp.sample.mysql.loadbalance;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ramp.sample.mysql.loadbalance.entity.Users;

/**
 * @author Rama Palaniappan
 * @since Aug 5, 2013
 */
public class Main {

	private ClassPathXmlApplicationContext context = null;
	protected static final String TEST_EMAIL = "test-email-id-" + System.currentTimeMillis();
	protected static final String FIRST_NAME = "fname";
	protected static final String LAST_NAME = "lname";
	protected static final String EXISTING_EMAIL = "first1_last1@i.com";
	protected static final Date now = new Date();

	public Main(String beanConfigFile) {
		context = new ClassPathXmlApplicationContext(beanConfigFile);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main("entity-manager.xml");
		BaseSpring baseSpring = main.context.getBean(BaseSpring.class);
		baseSpring.readMethod1();
		baseSpring.readMethod2(EXISTING_EMAIL);
		Users user = new Users(TEST_EMAIL, FIRST_NAME, LAST_NAME, now, now);
		baseSpring.writeMethod1(user);
		baseSpring.readMethod2(user.getEmailId());
		baseSpring.writeMethod2(user.getEmailId());
		baseSpring.readMethod2(user.getEmailId());
		baseSpring.readMethod1();
	}

}
