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
@Table(name = "t_table_columns", catalog = "store_2")
public class TableColumns implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer tablecolumnsId;
	private Privilege privilege;
	private String tableCode;
	private String tableName;
	private String columnCode;
	private String columnClass;
	private String columnName;
	private Integer isPublic;

	public TableColumns() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tablecolumns_id", unique = true, nullable = false)
	public Integer getTablecolumnsId() {
		return this.tablecolumnsId;
	}

	public void setTablecolumnsId(Integer tablecolumnsId) {
		this.tablecolumnsId = tablecolumnsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "privilege_id")
	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Column(name = "table_code", length = 1024)
	public String getTableCode() {
		return this.tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	
	@Column(name = "table_name", length = 1024)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	
	@Column(name = "column_code", length = 1024)
	public String getColumnCode() {
		return this.columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	@Column(name = "column_class", length = 1024)
	public String getColumnClass() {
		return this.columnClass;
	}

	public void setColumnClass(String columnClass) {
		this.columnClass = columnClass;
	}
	
	@Column(name = "column_name", length = 1024)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "is_public")
	public Integer getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

}
