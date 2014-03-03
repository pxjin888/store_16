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
@Table(name = "store_material_formula", catalog = "store_2")
public class MaterialFormula implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer materialFormulaId;
	private Material materialByMaterialId;
	private Material materialByMaterialParentid;
	private Float materialFormulaNum;
	private Set<Material> materials = new HashSet<Material>(0);

	public MaterialFormula() {
	}

	public MaterialFormula(Material materialByMaterialId,
			Material materialByMaterialParentid, Float materialFormulaNum,
			Set<Material> materials) {
		this.materialByMaterialId = materialByMaterialId;
		this.materialByMaterialParentid = materialByMaterialParentid;
		this.materialFormulaNum = materialFormulaNum;
		this.materials = materials;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "material_formula_id", unique = true, nullable = false)
	public Integer getMaterialFormulaId() {
		return this.materialFormulaId;
	}

	public void setMaterialFormulaId(Integer materialFormulaId) {
		this.materialFormulaId = materialFormulaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	public Material getMaterialByMaterialId() {
		return this.materialByMaterialId;
	}

	public void setMaterialByMaterialId(Material materialByMaterialId) {
		this.materialByMaterialId = materialByMaterialId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_parentid")
	public Material getMaterialByMaterialParentid() {
		return this.materialByMaterialParentid;
	}

	public void setMaterialByMaterialParentid(
			Material materialByMaterialParentid) {
		this.materialByMaterialParentid = materialByMaterialParentid;
	}

	@Column(name = "material_formula_num", precision = 12, scale = 0)
	public Float getMaterialFormulaNum() {
		return this.materialFormulaNum;
	}

	public void setMaterialFormulaNum(Float materialFormulaNum) {
		this.materialFormulaNum = materialFormulaNum;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialFormula")
	public Set<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

}
