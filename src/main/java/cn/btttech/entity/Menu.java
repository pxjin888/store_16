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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_menu", catalog = "store_2")
public class Menu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer menuId;
	private Privilege privilege;
	private Menu menu;
	private String menuName;
	private String menuUrl;
	private Integer menuOrder;
	private String menuView;
	private String menuClass;
	
	private Set<Operation> operations = new HashSet<Operation>(0);
	private Set<TableColumns> tableColumnses = new HashSet<TableColumns>(0);
	private Set<Menu> menus = new HashSet<Menu>(0);
	
	private SearchBar searchBar;

	public Menu() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "menu_id", unique = true, nullable = false)
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "menu")
	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_parentid")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "menu_name", length = 1024)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "menu_url", length = 1024)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "menu_order")
	public Integer getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name = "menu_view")
	public String getMenuView() {
		return this.menuView;
	}

	public void setMenuView(String menuView) {
		this.menuView = menuView;
	}

	@Column(name = "menu_class")
	public String getMenuClass() {
		return this.menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="menu_tablecolumns_relationship",
	joinColumns=@JoinColumn(name="menu_id"),
	inverseJoinColumns=@JoinColumn(name="tablecolumns_id")
			   )
	public Set<TableColumns> getTableColumnses() {
		return this.tableColumnses;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name="menu_searchbar_relationship",
	joinColumns=@JoinColumn(name="menu_id"),
	inverseJoinColumns=@JoinColumn(name="searchbar_id")
			   )
	public SearchBar getSearchBar() {
		return searchBar;
	}

	public void setSearchBar(SearchBar searchBar) {
		this.searchBar = searchBar;
	}

	public void setTableColumnses(Set<TableColumns> tableColumnses) {
		this.tableColumnses = tableColumnses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="menu_operation_relationship",
	joinColumns=@JoinColumn(name="menu_id"),
	inverseJoinColumns=@JoinColumn(name="operation_id")
			   )
	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

}
