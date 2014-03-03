package cn.btttech.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "t_privilege", catalog = "store_2")
public class Privilege implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer privilegeId;
	private Operation operation;
	private TableColumns tableColumns;
	private ContentElement contentElement;
	private Menu menu;
	private String privilegeName;
	private String privilegeTypeName;
	private Set<Department> departments = new HashSet<Department>(0);
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<Role> roles = new HashSet<Role>(0);

	public Privilege() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "privilege_id", unique = true, nullable = false)
	public Integer getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public TableColumns getTableColumns() {
		return this.tableColumns;
	}

	public void setTableColumns(TableColumns tableColumns) {
		this.tableColumns = tableColumns;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public ContentElement getContentElement() {
		return this.contentElement;
	}

	public void setContentElement(ContentElement contentElement) {
		this.contentElement = contentElement;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "privilege_name", length = 1024)
	public String getPrivilegeName() {
		return this.privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	@Column(name = "privilege_type_name", length = 1024)
	public String getPrivilegeTypeName() {
		return this.privilegeTypeName;
	}

	public void setPrivilegeTypeName(String privilegeTypeName) {
		this.privilegeTypeName = privilegeTypeName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "privileges")
	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "privileges")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "privileges")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
