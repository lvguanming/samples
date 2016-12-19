package ramp.sample.hibernatejpaspring.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author Rama Palaniappan
 * @since Jan 16, 2013
 */
@ContextConfiguration(locations = { "classpath:entity-manager.xml",
		"classpath:hsql-data-source.xml" })
@TransactionConfiguration(defaultRollback = true)
public class BaseDAOTest extends AbstractTransactionalTestNGSpringContextTests {
}