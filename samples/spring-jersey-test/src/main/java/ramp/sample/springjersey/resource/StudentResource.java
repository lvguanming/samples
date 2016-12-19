/**
 * 
 */
package ramp.sample.springjersey.resource;

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
import javax.ws.rs.core.Response;

import ramp.sample.springjersey.types.Student;

/**
 * 
 * @author Rama Palaniappan
 * @since Mar 24, 2014
 */
@Path("/students")
//@Consumes({APPLICATION_XML, APPLICATION_JSON })
//@Produces({APPLICATION_XML, APPLICATION_JSON })
public interface StudentResource {
	@GET
	public Response getAll();
	
	@GET
	@Path("{id}")
	public Response getById(@PathParam("id") String id);
	
	@POST
	public Response save(Student student);
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") String id, Student student);
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") String id);
}
