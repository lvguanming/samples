package ramp.sample.hibernatejpaspring.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
//@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="emp_no", updatable=false, unique=true, nullable=false)
	private Integer empNo;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date", nullable=false)
	private Date birthDate;

	@Column(name="first_name", nullable=false, length=14)
	private String firstName;

	@Column(nullable=false, length=1)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Temporal(TemporalType.DATE)
	@Column(name="hire_date", nullable=false)
	private Date hireDate;

	@Column(name="last_name", nullable=false, length=16)
	private String lastName;

	//bi-directional many-to-one association to DeptEmp
	@OneToMany(mappedBy="employee")
	private List<DeptEmp> deptEmps;

	//bi-directional many-to-one association to DeptManager
	@OneToMany(mappedBy="employee")
	private List<DeptManager> deptManagers;

	//bi-directional many-to-one association to Salary
	@OneToMany(mappedBy="employee")
	private List<Salary> salaries;

	//bi-directional many-to-one association to Title
	@OneToMany(mappedBy="employee")
	private List<Title> titles;

	public Employee() {
	}

	public Integer getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<DeptEmp> getDeptEmps() {
		return this.deptEmps;
	}

	public void setDeptEmps(List<DeptEmp> deptEmps) {
		this.deptEmps = deptEmps;
	}

	public DeptEmp addDeptEmp(DeptEmp deptEmp) {
		getDeptEmps().add(deptEmp);
		deptEmp.setEmployee(this);

		return deptEmp;
	}

	public DeptEmp removeDeptEmp(DeptEmp deptEmp) {
		getDeptEmps().remove(deptEmp);
		deptEmp.setEmployee(null);

		return deptEmp;
	}

	public List<DeptManager> getDeptManagers() {
		return this.deptManagers;
	}

	public void setDeptManagers(List<DeptManager> deptManagers) {
		this.deptManagers = deptManagers;
	}

	public DeptManager addDeptManager(DeptManager deptManager) {
		getDeptManagers().add(deptManager);
		deptManager.setEmployee(this);

		return deptManager;
	}

	public DeptManager removeDeptManager(DeptManager deptManager) {
		getDeptManagers().remove(deptManager);
		deptManager.setEmployee(null);

		return deptManager;
	}

	public List<Salary> getSalaries() {
		return this.salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	public Salary addSalary(Salary salary) {
		getSalaries().add(salary);
		salary.setEmployee(this);

		return salary;
	}

	public Salary removeSalary(Salary salary) {
		getSalaries().remove(salary);
		salary.setEmployee(null);

		return salary;
	}

	public List<Title> getTitles() {
		return this.titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public Title addTitle(Title title) {
		getTitles().add(title);
		title.setEmployee(this);

		return title;
	}

	public Title removeTitle(Title title) {
		getTitles().remove(title);
		title.setEmployee(null);

		return title;
	}

}