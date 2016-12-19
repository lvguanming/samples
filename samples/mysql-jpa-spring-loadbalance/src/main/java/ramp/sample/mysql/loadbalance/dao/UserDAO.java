package ramp.sample.mysql.loadbalance.dao;

import java.util.List;

import ramp.sample.mysql.loadbalance.entity.Users;

/**
 * 
 * @author Rama Palaniappan
 * @since Aug 1, 2013
 */
public interface UserDAO extends GenericDAO<Users, String> {
	public List<Users> findUser(String firstName, String lastName);
}
