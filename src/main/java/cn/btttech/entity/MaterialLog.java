package cn.btttech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("materialLog")
public class MaterialLog extends Log {

	private static final long serialVersionUID = 1L;
	private MaterialFactory materialFactory;
	private Material material;
	private Float logMaterialNum;
	private Float logMaterialInputPrice;
	private String logMaterialUse;
	private String logMaterialPosition;
	private String logMaterialProject;
	private String logBuyRequirecode;
	private String logBuyAgreementcode;

	public MaterialLog() {
	}


	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_factory_id")
	public MaterialFactory getMaterialFactory() {
		return this.materialFactory;
	}

	public void setMaterialFactory(MaterialFactory materialFactory) {
		this.materialFactory = materialFactory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}


	@Column(name = "material_log_num", precision = 12, scale = 0)
	public Float getLogMaterialNum() {
		return this.logMaterialNum;
	}

	public void setLogMaterialNum(Float logMaterialNum) {
		this.logMaterialNum = logMaterialNum;
	}

	@Column(name = "material_log_input_price", precision = 12, scale = 0)
	public Float getLogMaterialInputPrice() {
		return this.logMaterialInputPrice;
	}

	public void setLogMaterialInputPrice(Float logMaterialInputPrice) {
		this.logMaterialInputPrice = logMaterialInputPrice;
	}

	@Column(name = "material_log_use", length = 1024)
	public String getLogMaterialUse() {
		return this.logMaterialUse;
	}

	public void setLogMaterialUse(String logMaterialUse) {
		this.logMaterialUse = logMaterialUse;
	}

	@Column(name = "material_log_position", length = 1024)
	public String getLogMaterialPosition() {
		return this.logMaterialPosition;
	}

	public void setLogMaterialPosition(String logMaterialPosition) {
		this.logMaterialPosition = logMaterialPosition;
	}

	@Column(name = "material_log_project", length = 1024)
	public String getLogMaterialProject() {
		return this.logMaterialProject;
	}

	public void setLogMaterialProject(String logMaterialProject) {
		this.logMaterialProject = logMaterialProject;
	}

	@Column(name = "material_log_buy_requirecode", length = 1024)
	public String getLogBuyRequirecode() {
		return this.logBuyRequirecode;
	}

	public void setLogBuyRequirecode(String logBuyRequirecode) {
		this.logBuyRequirecode = logBuyRequirecode;
	}

	@Column(name = "log_buy_agreementcode", length = 1024)
	public String getLogBuyAgreementcode() {
		return this.logBuyAgreementcode;
	}

	public void setLogBuyAgreementcode(String logBuyAgreementcode) {
		this.logBuyAgreementcode = logBuyAgreementcode;
	}


}
