package cn.btttech.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_department", catalog = "store_2")
public class Department implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer departmentId;
	private Company company;
	private Department department;
	private String departmentName;
	private Set<Privilege> privileges = new HashSet<Privilege>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<Department> departments = new HashSet<Department>(0);

	public Department() {
	}

	public Department(Company company, Department department,
			String departmentName, Set<Privilege> privileges, Set<User> users,
			Set<Department> departments) {
		this.company = company;
		this.department = department;
		this.departmentName = departmentName;
		this.privileges = privileges;
		this.users = users;
		this.departments = departments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "department_id", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_parentid")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "department_name", length = 1024)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "department_privilege_relationship", catalog = "store_2", joinColumns = { @JoinColumn(name = "department_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "privilege_id", nullable = false, updatable = false) })
	public Set<Privilege> getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "department_user_relationship", catalog = "store_2", joinColumns = { @JoinColumn(name = "department_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
