/**
 * 
 */
package ramp.sample.springjersey.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ramp.sample.springjersey.resource.api.UsersResource;
import ramp.sample.springjersey.service.UsersService;
import ramp.sample.springjersey.types.User;
import ramp.sample.springjersey.types.Users;

/**
 * @author Rama Palaniappan
 * @since Mar 24, 2014
 */
//@Component
public class UsersResourceImpl implements UsersResource {

	@Autowired
	private UsersService usersService;
	
	@Override
	public Users getAll() {
		//System.out.println(userService.getAll().getUsers().size());
		return usersService.getAll();
	}

	@Override
	public User getById(String id) {
		return usersService.get(id);
	}

	@Override
	public User save(User user) {
		return usersService.save(user);
	}

	@Override
	public User update(String id, User user) {
		return usersService.update(id, user);
	}

	@Override
	public void delete(String id) {
		usersService.delete(id);
	}

	public UsersService getUserService() {
		return usersService;
	}

	public void setUserService(UsersService userService) {
		this.usersService = userService;
	}

}
