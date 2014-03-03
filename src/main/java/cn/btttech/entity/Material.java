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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "store_material", catalog = "store_2")
public class Material implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer materialId;
	private MaterialFormula materialFormula;
	private String materialName;
	private String materialUnit;
	private Float materialNum;
	private String materialCode;
	private String materialType;
	private Float materialMinNum;
	private Float materialBatchNum;
	private Float materialBatchPrice;
	private Long materialSequence;
	private Integer materialPassNum;
	private Integer materialPassNo;
	private String materialVersion;
	private String materialPosition;
	private String materialUse;
	private Set<MaterialFormula> materialFormulasForMaterialId = new HashSet<MaterialFormula>(
			0);
	private Set<MaterialLog> materialLogs = new HashSet<MaterialLog>(0);
	private Set<MaterialFormula> materialFormulasForMaterialParentid = new HashSet<MaterialFormula>(
			0);
	private Set<MaterialPrice> materialPrices = new HashSet<MaterialPrice>(0);

	public Material() {
	}

	public Material(MaterialFormula materialFormula, String materialName,
			String materialUnit, Float materialNum, String materialCode,
			String materialType, Float materialMinNum, Float materialBatchNum,
			Float materialBatchPrice, Long materialSequence,
			Integer materialPassNum, Integer materialPassNo,
			String materialVersion, String materialPosition,
			String materialUse,
			Set<MaterialFormula> materialFormulasForMaterialId,
			Set<MaterialLog> materialLogs,
			Set<MaterialFormula> materialFormulasForMaterialParentid,
			Set<MaterialPrice> materialPrices) {
		this.materialFormula = materialFormula;
		this.materialName = materialName;
		this.materialUnit = materialUnit;
		this.materialNum = materialNum;
		this.materialCode = materialCode;
		this.materialType = materialType;
		this.materialMinNum = materialMinNum;
		this.materialBatchNum = materialBatchNum;
		this.materialBatchPrice = materialBatchPrice;
		this.materialSequence = materialSequence;
		this.materialPassNum = materialPassNum;
		this.materialPassNo = materialPassNo;
		this.materialVersion = materialVersion;
		this.materialPosition = materialPosition;
		this.materialUse = materialUse;
		this.materialFormulasForMaterialId = materialFormulasForMaterialId;
		this.materialLogs = materialLogs;
		this.materialFormulasForMaterialParentid = materialFormulasForMaterialParentid;
		this.materialPrices = materialPrices;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "material_id", unique = true, nullable = false)
	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_formula_id")
	public MaterialFormula getMaterialFormula() {
		return this.materialFormula;
	}

	public void setMaterialFormula(MaterialFormula materialFormula) {
		this.materialFormula = materialFormula;
	}

	@Column(name = "material_name", length = 1024)
	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "material_unit", length = 1024)
	public String getMaterialUnit() {
		return this.materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	@Column(name = "material_num", precision = 12, scale = 0)
	public Float getMaterialNum() {
		return this.materialNum;
	}

	public void setMaterialNum(Float materialNum) {
		this.materialNum = materialNum;
	}

	@Column(name = "material_code", length = 1024)
	public String getMaterialCode() {
		return this.materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Column(name = "material_type", length = 1024)
	public String getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	@Column(name = "material_min_num", precision = 12, scale = 0)
	public Float getMaterialMinNum() {
		return this.materialMinNum;
	}

	public void setMaterialMinNum(Float materialMinNum) {
		this.materialMinNum = materialMinNum;
	}

	@Column(name = "material_batch_num", precision = 12, scale = 0)
	public Float getMaterialBatchNum() {
		return this.materialBatchNum;
	}

	public void setMaterialBatchNum(Float materialBatchNum) {
		this.materialBatchNum = materialBatchNum;
	}

	@Column(name = "material_batch_price", precision = 12, scale = 0)
	public Float getMaterialBatchPrice() {
		return this.materialBatchPrice;
	}

	public void setMaterialBatchPrice(Float materialBatchPrice) {
		this.materialBatchPrice = materialBatchPrice;
	}

	@Column(name = "material_sequence")
	public Long getMaterialSequence() {
		return this.materialSequence;
	}

	public void setMaterialSequence(Long materialSequence) {
		this.materialSequence = materialSequence;
	}

	@Column(name = "material_pass_num")
	public Integer getMaterialPassNum() {
		return this.materialPassNum;
	}

	public void setMaterialPassNum(Integer materialPassNum) {
		this.materialPassNum = materialPassNum;
	}

	@Column(name = "material_pass_no")
	public Integer getMaterialPassNo() {
		return this.materialPassNo;
	}

	public void setMaterialPassNo(Integer materialPassNo) {
		this.materialPassNo = materialPassNo;
	}

	@Column(name = "material_version", length = 1024)
	public String getMaterialVersion() {
		return this.materialVersion;
	}

	public void setMaterialVersion(String materialVersion) {
		this.materialVersion = materialVersion;
	}

	@Column(name = "material_position", length = 1024)
	public String getMaterialPosition() {
		return this.materialPosition;
	}

	public void setMaterialPosition(String materialPosition) {
		this.materialPosition = materialPosition;
	}

	@Column(name = "material_use", length = 1024)
	public String getMaterialUse() {
		return this.materialUse;
	}

	public void setMaterialUse(String materialUse) {
		this.materialUse = materialUse;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialByMaterialId")
	public Set<MaterialFormula> getMaterialFormulasForMaterialId() {
		return this.materialFormulasForMaterialId;
	}

	public void setMaterialFormulasForMaterialId(
			Set<MaterialFormula> materialFormulasForMaterialId) {
		this.materialFormulasForMaterialId = materialFormulasForMaterialId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	public Set<MaterialLog> getMaterialLogs() {
		return this.materialLogs;
	}

	public void setMaterialLogs(Set<MaterialLog> materialLogs) {
		this.materialLogs = materialLogs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialByMaterialParentid")
	public Set<MaterialFormula> getMaterialFormulasForMaterialParentid() {
		return this.materialFormulasForMaterialParentid;
	}

	public void setMaterialFormulasForMaterialParentid(
			Set<MaterialFormula> materialFormulasForMaterialParentid) {
		this.materialFormulasForMaterialParentid = materialFormulasForMaterialParentid;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	public Set<MaterialPrice> getMaterialPrices() {
		return this.materialPrices;
	}

	public void setMaterialPrices(Set<MaterialPrice> materialPrices) {
		this.materialPrices = materialPrices;
	}

}
