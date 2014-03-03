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
import javax.persistence.Table;

@Entity
@Table(name = "t_project", catalog = "store_2")
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer projectId;
	private String projectName;
	private Set<User> users = new HashSet<User>(0);
	private Set<Privilege> privileges = new HashSet<Privilege>(0);

	public Project() {
	}

	public Project(String projectName, Set<User> users,
			Set<Privilege> privileges) {
		this.projectName = projectName;
		this.users = users;
		this.privileges = privileges;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "project_name", length = 1024)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "project_privilege_relationship", catalog = "store_2", joinColumns = { @JoinColumn(name = "project_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "privilege_id", nullable = false, updatable = false) })
	public Set<Privilege> getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
