/**
 * 
 */
package ramp.sample.hibernatejpaspring.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ramp.sample.hibernatejpaspring.entity.Employee;
import ramp.sample.hibernatejpaspring.entity.Gender;

/**
 * @author Rama Palaniappan
 * @since Mar 30, 2014
 */
public class TestEmployeeDAO extends BaseDAOTest {

	@Autowired
	EmployeeDAO employeeDAO;

	@Test
	public void testFindAll() {
		List<Employee> employees = employeeDAO.findAll();
		Assert.assertTrue(employees.size() > 0);
	}

	@DataProvider
	public Object[][] dpFindById() {
		return new Object[][] { { 10001 }, { 10001 }, { 10001 }, { 10002 },
				{ 10003 }, };
	}

	@Test(dataProvider = "dpFindById")
	public void testFindById(Integer employeeId) {
		Employee actual = employeeDAO.find(employeeId);
		Assert.assertEquals(actual.getEmpNo(), employeeId);
	}

	@DataProvider
	public Object[][] dpFindByGender() {
		return new Object[][] { { Gender.M }, { Gender.F }, };
	}

	@Test(dataProvider = "dpFindByGender")
	public void testFindByGender(Gender gender) {
		TypedQuery<Employee> query = employeeDAO.createQuery(
				"SELECT e FROM Employee as e WHERE e.gender = :gender",
				Employee.class);
		query.setParameter("gender", gender);
		List<Employee> employees = query.getResultList();
		for (Employee employee : employees) {
			Assert.assertEquals(employee.getGender(), gender);
		}
	}
}
