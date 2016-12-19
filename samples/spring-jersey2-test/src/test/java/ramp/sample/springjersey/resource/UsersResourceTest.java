/**
 * 
 */
package ramp.sample.springjersey.resource;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import ramp.sample.springjersey.types.User;
import ramp.sample.springjersey.types.Users;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Rama Palaniappan
 * @since Mar 24, 2014
 */
public class UsersResourceTest extends BaseJerseyTest {

	@Test
	public void testGetAllByJaxbObject() {
		WebResource resource = resource();
		Users users = resource.path("users").get(Users.class);
		Assert.assertTrue(users.getUsers().size() > 0);
	}

	@Test
	public void testGetAllByStringObject() {
		WebResource resource = resource();
		String response = resource.path("users").get(String.class);
		Assert.assertTrue(response.length() > 0);
	}

	@Test
	public void testGetAllByClientResponse() {
		WebResource resource = resource();
		ClientResponse response = resource.path("users").get(
				ClientResponse.class);
		Assert.assertEquals(response.getStatus(),
				Response.Status.OK.getStatusCode());
		Users users = response.getEntity(Users.class);
		Assert.assertTrue(users.getUsers().size() > 0);
	}

	@Test
	public void testGetAllByClientResponseMockService() {
		WebResource resource = resource();
		ClientResponse response = resource.path("users").get(
				ClientResponse.class);
		Assert.assertEquals(response.getStatus(),
				Response.Status.OK.getStatusCode());
		Users users = response.getEntity(Users.class);
		Assert.assertTrue(users.getUsers().size() > 0);
	}

	public Users getUsers() {
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
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}
}
