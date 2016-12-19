/**
 * 
 */
package ramp.sample.springjersey.service;

import ramp.sample.springjersey.types.User;
import ramp.sample.springjersey.types.Users;

/**
 * @author Rama Palaniappan
 * @since Mar 25, 2014
 */
public interface UsersService {
	public Users getAll();

	public User get(String id);

	public User update(String id, User user);

	public User save(User user);

	public void delete(String id);

}
