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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_user", catalog = "store_2")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Set<Department> departments = new HashSet<Department>(0);
	private String userName;
	private String userPassword;
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Log> logs = new HashSet<Log>(0);

	public User() {
	}
	
	public User(String userName, String userPassword){
		this.userName = userName;
		this.userPassword = userPassword;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	@Column(name = "user_name", length = 1024)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_password", length = 1024)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_project_relationship", catalog = "store_2", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) })
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role_relationship", catalog = "store_2", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	
}
