package cn.btttech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_operation", catalog = "store_2")
public class Operation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer operationId;
	private Privilege privilege;
	private String operationName;
	private String operationUrl;
	private String operationIconClass;
	private Integer operationOpenWidth;
	private Integer operationOpenHeight;
	private String operationTarget;
	private String operationOpenRel;
	private Integer operationOrder;

	public Operation() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "operation_id", unique = true, nullable = false)
	public Integer getOperationId() {
		return this.operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "operation")
	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Column(name = "operation_name", length = 1024)
	public String getOperationName() {
		return this.operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	@Column(name = "operation_url", length = 1024)
	public String getOperationUrl() {
		return this.operationUrl;
	}

	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}

	@Column(name = "operation_icon_class", length = 1024)
	public String getOperationIconClass() {
		return this.operationIconClass;
	}

	public void setOperationIconClass(String operationIconClass) {
		this.operationIconClass = operationIconClass;
	}

	@Column(name = "operation_open_width")
	public Integer getOperationOpenWidth() {
		return this.operationOpenWidth;
	}

	public void setOperationOpenWidth(Integer operationOpenWidth) {
		this.operationOpenWidth = operationOpenWidth;
	}

	@Column(name = "operation_open_height")
	public Integer getOperationOpenHeight() {
		return this.operationOpenHeight;
	}

	public void setOperationOpenHeight(Integer operationOpenHeight) {
		this.operationOpenHeight = operationOpenHeight;
	}

	@Column(name = "operation_target", length = 1024)
	public String getOperationTarget() {
		return this.operationTarget;
	}

	public void setOperationTarget(String operationTarget) {
		this.operationTarget = operationTarget;
	}

	@Column(name = "operation_open_rel", length = 1024)
	public String getOperationOpenRel() {
		return this.operationOpenRel;
	}

	public void setOperationOpenRel(String operationOpenRel) {
		this.operationOpenRel = operationOpenRel;
	}

	@Column(name = "operation_order")
	public Integer getOperationOrder() {
		return this.operationOrder;
	}

	public void setOperationOrder(Integer operationOrder) {
		this.operationOrder = operationOrder;
	}
}
