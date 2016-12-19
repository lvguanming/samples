/**
 * 
 */
package ramp.sample.springjersey.resource.impl;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ramp.sample.springjersey.resource.api.StudentResource;
import ramp.sample.springjersey.service.StudentsServiceImpl;
import ramp.sample.springjersey.types.Student;
import ramp.sample.springjersey.types.Students;

/**
 * @author Rama Palaniappan
 * @since Mar 24, 2014
 */
@Path("/students")
//@Component
public class StudentsResourceImpl implements StudentResource {

	@Autowired
	private StudentsServiceImpl studentsService;

	@Context
	private UriInfo uriInfo;

	@Override
	@GET
	public Response getAll() {
		Students students = studentsService.getAll();
		return Response.ok().entity(students).build();
	}

	@Override
	@GET
	@Path("{id}")
	public Response getById(@PathParam("id") String id) {
		Student student = studentsService.get(id);
		return Response.ok().entity(student).build();
	}

	@Override
	@POST
	public Response save(Student student) {
		student = studentsService.save(student);
		// Return 201 - Created and set created url as location header
		URI uri = uriInfo.getAbsolutePathBuilder().path(student.getId())
				.build();
		return Response.created(uri).entity(student).build();
	}

	@Override
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") String id, Student student) {
		student = studentsService.update(id, student);
		return Response.ok().entity(student).build();
	}

	@Override
	@DELETE
	@Path("{id}")
	public void delete(String id) {
		studentsService.delete(id);
	}

}
