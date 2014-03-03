package cn.btttech.util;

import java.util.List;

import cn.btttech.entity.MaterialPrice;

public class MaterialPriceUtil {

	//先进先出
	public static List<MaterialPrice> firstInFirstOut(List<MaterialPrice> materialPrices, Float num){
		for (MaterialPrice materialPrice : materialPrices) {
			if(num > 0){
				float partNum = materialPrice.getMaterialPartNum();
				if(num >= partNum){
					materialPrice.setMaterialPartNum(0f);
				}else {
					materialPrice.setMaterialPartNum(materialPrice.getMaterialPartNum() - num);
				}
			}
		}
		return materialPrices;
	}
}
