/**
 * 
 */
package ramp.sample.springjersey.resource;

import java.util.List;

import ramp.sample.springjersey.service.UsersService;
import ramp.sample.springjersey.types.User;
import ramp.sample.springjersey.types.Users;

/**
 * @author Rama Palaniappan
 * @since Mar 25, 2014
 */
public class UsersServiceMock implements UsersService{
	public Users getAll() {
		return getUsers();
	}
	
	public User get(String id) {
		if ("abc@xyz.com".equalsIgnoreCase(id)) {
			return getUser();
		} else if ("sample1@xyz.com".equalsIgnoreCase(id)) {
			return getSampleUser1();
		} else if ("sample2@xyz.com".equalsIgnoreCase(id)) {
			return getSampleUser2();
		}
		return null;
	}
	
	public User update(String id, User user) {
		if (user !=null && id !=null) {
			user.setId(id);
		}
		return user;
	}
	
	public User save(User user) {
		//set some id
		if (user != null) {
			user.setId("random@email.id");
		}
		return user;
	}
	
	public void delete(String id) {
		
	}
	
	private Users getUsers() {
		Users users = new Users();
		List<User> list = users.getUsers();
		list.add(getUser());
		list.add(getSampleUser1());
		list.add(getSampleUser2());
		return users;
	}
	
	private User getSampleUser1() {
		return getUser("sample1@xyz.com", "Sample1", "User");
	}
	
	private User getSampleUser2() {
		return getUser("sample2@xyz.com", "Sample2", "User");
	}
	
	private User getUser() {
		return getUser("abc@xyz.com", "First", "Last");
	}
	
	private User getUser(String id, String firstName, String lastName) {
		User user = new User();
		user.setId("mock-" + id);
		user.setFirstName("mock-" + firstName);
		user.setLastName("mock-" + lastName);
		return user;
	}

}
