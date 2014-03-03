package cn.btttech.service.material;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialLog;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.service.base.AbstractService;
import cn.btttech.util.MaterialPriceUtil;

@Service("materialLogService")
@Transactional(readOnly = true)
public class MaterialLogService extends AbstractService<MaterialLog> {  
	
	//一级物料入库
	@Transactional(readOnly = false)
	public void firstMaterialInput(MaterialLog logMaterial, int materialId, int materialFactoryId, float materialNum, float price){
		
		Material material = getBaseDao().get(Material.class, materialId);
		Float preNum = material.getMaterialNum();
		Set<MaterialLog> logMaterials = new HashSet<MaterialLog>();
		logMaterials.add(logMaterial);
		
		MaterialPrice materialPrice = new MaterialPrice();
		materialPrice.setMaterial(material);
		
		MaterialFactory materialFactory = getBaseDao().get(MaterialFactory.class, materialFactoryId); 
		
		materialPrice.getMaterialFactories().add(materialFactory);
		materialPrice.setMaterialPartNum(materialNum);
		materialPrice.setMaterialPriceInputprice(price);
		materialPrice.setMaterialPriceTime(new Date());
		
		material.getMaterialPrices().add(materialPrice);
		material.setMaterialNum(preNum+materialNum);
		material.setMaterialLogs(logMaterials);
		
		logMaterial.setMaterialFactory(materialFactory);
		logMaterial.setMaterial(material);
		
		materialFactory.getMaterialPrices().add(materialPrice);
		
		getBaseDao().update(material);
	
	}
	
	
	//物料出库
	@Transactional(readOnly = false)
	public void materialOutput(MaterialLog logMaterial, int materialId, String []materialFactoryIds,  String [] materialPriceIds, String [] materialNums, Float totalMaterialNum){
		
		Material material = getBaseDao().get(Material.class, materialId);
		HashSet<MaterialPrice> materialPrices = new HashSet<MaterialPrice>();
		for (int i = 0; i < materialPriceIds.length; i++) {
			MaterialPrice materialPrice = getBaseDao().get(MaterialPrice.class, Integer.parseInt(materialPriceIds[i]));
			materialPrice.setMaterialPartNum(materialPrice.getMaterialPartNum() - Float.parseFloat(materialNums[i]));
			materialPrices.add(materialPrice);
		}
		
		material.setMaterialPrices(materialPrices);
		material.setMaterialNum(material.getMaterialNum() - totalMaterialNum);
		Set<MaterialLog> logMaterials = new HashSet<MaterialLog>();
		logMaterials.add(logMaterial);
		material.setMaterialLogs(logMaterials);
		
		logMaterial.setMaterial(material);
		
		getBaseDao().update(material);
		
	}

	//成品入库
	@Transactional(readOnly = false)
	public void mixMaterialInput(MaterialLog logMaterial, int materialId, Integer [] materialChildIds, Float [] materialChildNums,
			Float totalMaterialNum) {
		Material material = getBaseDao().get(Material.class, materialId);
		
		for (int i = 0; i < materialChildIds.length; i++) {
			Material materialChild = getBaseDao().get(Material.class, materialChildIds[i]);
			//子物料总量减少
			materialChild.setMaterialNum(materialChild.getMaterialNum() - materialChildNums[i]);
			
			List<MaterialPrice> materialPrices = getBaseDao().findAll("from MaterialPrice price where price.material.materialId = ? order by price.materialPriceTime asc ", MaterialPrice.class, 
					materialChild.getMaterialId());
			
			//对应时价物料量减少（现在使用的方案是先进先出）
			materialPrices = MaterialPriceUtil.firstInFirstOut(materialPrices, materialChildNums[i]);
			materialChild.setMaterialPrices(new TreeSet<MaterialPrice>(materialPrices));
			getBaseDao().update(materialChild);
		}
		material.setMaterialNum(material.getMaterialNum() + totalMaterialNum);
		Set<MaterialLog> logMaterials = new HashSet<MaterialLog>();
		logMaterials.add(logMaterial);
		material.setMaterialLogs(logMaterials);
		
		logMaterial.setMaterial(material);
		
		getBaseDao().update(material);
	}
		

}  