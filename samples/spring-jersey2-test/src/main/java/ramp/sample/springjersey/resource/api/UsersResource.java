/**
 * 
 */
package ramp.sample.springjersey.resource.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ramp.sample.springjersey.types.User;
import ramp.sample.springjersey.types.Users;

/**
 * 
 * @author Rama Palaniappan
 * @since Mar 24, 2014
 */
@Path("/users")
// @Consumes({APPLICATION_XML, APPLICATION_JSON })
// @Produces({APPLICATION_XML, APPLICATION_JSON })
@Consumes({ APPLICATION_XML })
@Produces({ APPLICATION_XML })
public interface UsersResource {
	@GET
	public Users getAll();

	@GET
	@Path("{id}")
	public User getById(@PathParam("id") String id);

	@POST
	public User save(User user);

	@PUT
	@Path("{id}")
	public User update(@PathParam("id") String id, User user);

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") String id);
}
