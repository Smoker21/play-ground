package demo.hr.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the EMPLOYEES database table.
 * 
 */
@Entity
@Table(name = "EMPLOYEES")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private long employeeId;
	private BigDecimal commissionPct;
	private String email;
	private String firstName;
	private Date hireDate;
	private String lastName;
	private String phoneNumber;
	private BigDecimal salary;
	private Department department;
	private Employee manager;
	private Job job;

	public Employee() {
	}

	@Id
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false, precision = 6)
	public long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "COMMISSION_PCT", precision = 2, scale = 2)
	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	@Column(nullable = false, length = 25)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FIRST_NAME", length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE", nullable = false)
	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 25)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "PHONE_NUMBER", length = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(precision = 8, scale = 2)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	// uni-directional many-to-one association to Department
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	// uni-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee employee) {
		this.manager = employee;
	}

	// uni-directional many-to-one association to Job
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_ID", nullable = false)
	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}