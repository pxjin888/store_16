package cn.btttech.service.material;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.service.base.AbstractService;

@Service("materialPriceService")
@Transactional(readOnly = true)
public class MaterialPriceService extends AbstractService<MaterialPrice> {  

	public MaterialPrice combineMaterialItem(Material material,
			MaterialFactory materialFactory, float inputPrice) {
		
		List<MaterialPrice> list = getBaseDao().findAll("from MaterialPrice materialPrice where materialPrice.material = ? and materialPrice.materialFactory = ? and materialPrice.materialPriceInputprice = ?", MaterialPrice.class,
				new Object[]{material, materialFactory, inputPrice});
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

}  