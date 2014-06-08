package demo.hr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the DEPARTMENTS database table.
 * 
 */
@Entity
@Table(name = "DEPARTMENTS")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "departmentId")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	private long departmentId;
	private String departmentName;
	private Employee manager;
	private Location location;

	public Department() {
	}

	@Id
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false, precision = 4)
	public long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	// uni-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}