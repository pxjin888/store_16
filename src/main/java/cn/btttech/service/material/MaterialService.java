package cn.btttech.service.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFormula;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.service.base.AbstractService;

@Service("materialService")
@Transactional(readOnly = true)
public class MaterialService extends AbstractService<Material> {  
    
	public List<MaterialPrice> getMaterialFactories(Material material) {
		
		//先进先出
		List<MaterialPrice> prices = getBaseDao().findAll("from MaterialPrice price where price.material.materialId = ? order by price.materialPriceTime asc ", MaterialPrice.class, 
				material.getMaterialId());
//		for (MaterialPrice materialPrice : prices) {
//			MaterialFactory factory = materialPrice.getMaterialFactory();
//			map.put(factory, materialPrice);
//		}
		return prices;
	}
	
	
	
	/**
	 * 二级物料，即物料拼合
	 * @param material 父物料
	 * @param materialChildIds 子物料 
	 * @param materialChildNums
	 */
	@Transactional(readOnly = false)
	public void materialMix(Material material, Integer [] materialChildIds, Float [] materialChildNums){
		
		Set<MaterialFormula> materialFormulas = new HashSet<MaterialFormula>(0);
		System.out.println(materialChildIds.toString()+"|"+materialChildNums.toString());
		for (int i = 0; i < materialChildIds.length; i++) {
			MaterialFormula formula = new MaterialFormula();
			Material materialChild = getBaseDao().get(Material.class, materialChildIds[i]);
			//子物料总量减少
//			materialChild.setMaterialNum(materialChild.getMaterialNum() - materialChildNums[i]);
			
//			List<MaterialPrice> materialPrices = baseDao.findAll("from MaterialPrice price where price.material.materialId = ? order by price.materialPriceTime asc ", MaterialPrice.class, 
//					materialChild.getMaterialId());
			
			
			//对应时价物料量减少（现在使用的方案是先进先出）
//			materialPrices = MaterialPriceUtil.firstInFirstOut(materialPrices, materialChildNums[i]);
//			materialChild.setMaterialPrices(new TreeSet<MaterialPrice>(materialPrices));
			
			formula.setMaterialByMaterialId(materialChild);
			formula.setMaterialByMaterialParentid(material);
			formula.setMaterialFormulaNum(materialChildNums[i]);
			materialFormulas.add(formula);
		}
		material.setMaterialFormulasForMaterialId(materialFormulas);
		material.setMaterialNum(0F);
		getBaseDao().save(material);
		
	}


	public List<Map<String, Object>> getMaterialChildren(Integer materialId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0);
		
		Material material = getBaseDao().get(Material.class, materialId);
		Set<MaterialFormula> materialFormulas = material.getMaterialFormulasForMaterialParentid();
		for (MaterialFormula materialFormula : materialFormulas) {
			Material materialChild = materialFormula.getMaterialByMaterialId();
			Float materialChildFormulaNum = materialFormula.getMaterialFormulaNum();
			Map<String, Object> childMap = new HashMap<String, Object>();
			childMap.put("id", materialChild.getMaterialId());
			childMap.put("name", materialChild.getMaterialName());
			childMap.put("materialNum", materialChild.getMaterialNum());
			childMap.put("materialFormulaNum", materialChildFormulaNum);
			list.add(childMap);
		}
		return list;
	}


}  