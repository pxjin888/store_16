package cn.btttech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.DependsOn;

@Entity
@DependsOn("menu")
@Table(name = "t_searchbar", catalog = "store_2")
public class SearchBar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer searchbarId;
	private String searchName;
	private String searchUrl;

	public SearchBar() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "searchbar_id", unique = true, nullable = false)
	public Integer getSearchbarId() {
		return searchbarId;
	}

	public void setSearchbarId(Integer searchbarId) {
		this.searchbarId = searchbarId;
	}

	@Column(name = "searchbar_name", length = 1024)
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	@Column(name = "searchbar_url", length = 1024)
	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}


}
