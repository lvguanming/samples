/**
 * 
 */
package ramp.sample.springjersey.service;

import java.util.List;

import org.springframework.stereotype.Component;

import ramp.sample.springjersey.types.Student;
import ramp.sample.springjersey.types.Students;

/**
 * @author Rama Palaniappan
 * @since Mar 24, 2014
 */
@Component
public class StudentsServiceImpl implements StudentsService{
	public Students getAll() {
		return getStudents();
	}
	
	public Student get(String id) {
		if ("abc@xyz.com".equalsIgnoreCase(id)) {
			return getStudent();
		} else if ("sample1@xyz.com".equalsIgnoreCase(id)) {
			return getSampleStudent1();
		} else if ("sample2@xyz.com".equalsIgnoreCase(id)) {
			return getSampleStudent2();
		}
		return null;
	}
	
	public Student update(String id, Student student) {
		if (student !=null && id !=null) {
			student.setId(id);
		}
		return student;
	}
	
	public Student save(Student student) {
		//set some id
		if (student != null) {
			student.setId("random@email.id");
		}
		return student;
	}
	
	public void delete(String id) {
		
	}
	
	private Students getStudents() {
		Students students = new Students();
		List<Student> list = students.getStudents();
		list.add(getStudent());
		list.add(getSampleStudent1());
		list.add(getSampleStudent2());
		return students;
	}
	
	private Student getSampleStudent1() {
		return getStudent("sample1@xyz.com", "Sample1", "Student", "Course1");
	}
	
	private Student getSampleStudent2() {
		return getStudent("sample2@xyz.com", "Sample2", "Student", "Course2");
	}
	
	private Student getStudent() {
		return getStudent("abc@xyz.com", "First", "Last", "Course");
	}
	
	private Student getStudent(String id, String firstName, String lastName, String course) {
		Student student = new Student();
		student.setId(id);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setCourse(course);
		return student;
	}

}
