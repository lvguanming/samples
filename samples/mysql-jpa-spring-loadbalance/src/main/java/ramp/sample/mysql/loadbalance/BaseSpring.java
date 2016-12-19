/**
 * 
 */
package ramp.sample.mysql.loadbalance;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ramp.sample.mysql.loadbalance.dao.UserDAO;
import ramp.sample.mysql.loadbalance.entity.Users;
import ramp.sample.mysql.loadbalance.spring.ReadOnlyConnection;

/**
 * @author Rama Palaniappan
 * @since Aug 5, 2013
 */
@Component
public class BaseSpring {

	private static final Log LOG = LogFactory.getLog(BaseSpring.class);
	
	protected static final String TEST_EMAIL = "test-email-id-" + System.currentTimeMillis();
	protected static final String FIRST_NAME = "fname";
	protected static final String LAST_NAME = "lname";
	protected static final String EXISTING_EMAIL = "first1_last1@i.com";
	protected static final Date now = new Date();
	
	@Autowired
	private UserDAO userDAO = null;

	@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
	@ReadOnlyConnection
	//Propagation is new because, we want to simulate four different transaction for these methods
	public void readMethod1() {
		LOG.info(userDAO.getAll());
	}
	

	@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
	@ReadOnlyConnection
	public void readMethod2(String emailId) {
		LOG.info(userDAO.find(emailId));
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void writeMethod1(Users user) {
		userDAO.save(user);
		LOG.info(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void writeMethod2(String emailId) {
		Users user = userDAO.find(emailId);
		user.setLastUpdatedTimestamp(new Date());
		userDAO.merge(user);
		LOG.info(user);
	}
	
}
