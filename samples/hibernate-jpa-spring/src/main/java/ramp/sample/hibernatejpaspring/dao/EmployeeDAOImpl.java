/**
 * 
 */
package ramp.sample.hibernatejpaspring.dao;

import java.util.List;

import javax.persistence.Cacheable;

import org.springframework.stereotype.Repository;

import ramp.sample.hibernatejpaspring.entity.Employee;

/**
 * @author Rama Palaniappan
 * @since Mar 30, 2014
 */
@Repository
public class EmployeeDAOImpl extends BaseDAOImpl<Employee, Integer> implements
		EmployeeDAO {

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
}
