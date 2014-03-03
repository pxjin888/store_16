package cn.btttech.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_company", catalog = "store_2")
public class Company implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer companyId;
	private String companyName;
	private Set<Department> departments = new HashSet<Department>(0);

	public Company() {
	}

	public Company(String companyName, Set<Department> departments) {
		this.companyName = companyName;
		this.departments = departments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "company_id", unique = true, nullable = false)
	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "company_name", length = 1024)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
