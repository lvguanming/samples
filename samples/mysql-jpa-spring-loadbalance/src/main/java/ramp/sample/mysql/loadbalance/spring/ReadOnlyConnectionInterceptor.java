package ramp.sample.mysql.loadbalance.spring;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Copied from http://www.dragishak.com/?p=307
 * @author Rama Palaniappan
 * @since Aug 6, 2013
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

	private int order;
	private EntityManager entityManager;

	@Value("20")
	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint pjp,
			ReadOnlyConnection readOnlyConnection) throws Throwable {

		//Connection connection = entityManager.unwrap(java.sql.Connection.class);
		//Hibernate specific hack to get connection object
		Session session = entityManager.unwrap(Session.class);
		Connection connection = ((SessionImpl)session).connection();

		boolean autoCommit = connection.getAutoCommit();
		boolean readOnly = connection.isReadOnly();

		try {
			connection.setAutoCommit(false);
			connection.setReadOnly(true);

			return pjp.proceed();

		} finally {
			// restore state
			connection.setReadOnly(readOnly);
			connection.setAutoCommit(autoCommit);
		}
	}
}
