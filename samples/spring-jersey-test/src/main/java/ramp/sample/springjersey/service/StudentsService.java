/**
 * 
 */
package ramp.sample.springjersey.service;

import ramp.sample.springjersey.types.Student;
import ramp.sample.springjersey.types.Students;

/**
 * @author Rama Palaniappan
 * @since Mar 26, 2014
 */
public interface StudentsService {
	public Students getAll();
	
	public Student get(String id);
	
	public Student update(String id, Student student);
	
	public Student save(Student student);
	
	public void delete(String id);

}
