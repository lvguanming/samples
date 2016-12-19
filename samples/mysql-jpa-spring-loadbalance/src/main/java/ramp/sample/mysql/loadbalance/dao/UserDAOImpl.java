/**
 * 
 */
package ramp.sample.mysql.loadbalance.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ramp.sample.mysql.loadbalance.entity.Users;

/**
 * @author Rama Palaniappan
 * @since Aug 1, 2013
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<Users, String> implements UserDAO {

	@Override
	public List<Users> findUser(String firstName, String lastName) {
		if (firstName != null && lastName != null) {
			TypedQuery<Users> query = getEntityManager().createNamedQuery(Users.FIND_USER_FIRST_AND_LAST_NAME, klass);
			query.setParameter(Users.USER_PARAM_FIRST_NAME, firstName);
			query.setParameter(Users.USER_PARAM_LAST_NAME, lastName);
			return query.getResultList();
		} else if (firstName != null) {
			return findUserByFirstName(firstName);
		} else {
			return findUserByLastName(lastName);
		}
	}
	
	private List<Users> findUserByFirstName(String firstName) {
		TypedQuery<Users> query = getEntityManager().createNamedQuery(Users.FIND_USER_FIRST_NAME, klass);
		query.setParameter(Users.USER_PARAM_FIRST_NAME, firstName);
		return query.getResultList();
	}

	private List<Users> findUserByLastName(String lastName) {
		TypedQuery<Users> query = getEntityManager().createNamedQuery(Users.FIND_USER_LAST_NAME, klass);
		query.setParameter(Users.USER_PARAM_LAST_NAME, lastName);
		return query.getResultList();
	}	

}
