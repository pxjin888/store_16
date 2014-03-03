package cn.btttech.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;

import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.entity.Menu;
import cn.btttech.entity.Operation;
import cn.btttech.entity.SearchBar;
import cn.btttech.entity.TableColumns;
import cn.btttech.entity.User;
import cn.btttech.entity.view.LastStore;
import cn.btttech.service.comm.MenuService;
import cn.btttech.service.comm.OperationService;
import cn.btttech.service.comm.SearchBarService;
import cn.btttech.service.comm.TableColumnsService;
import cn.btttech.service.material.MaterialFactoryService;
import cn.btttech.service.material.MaterialService;

public class MaterialAction extends ActionBase {

	private static final long serialVersionUID = -3302139967485953797L;
	private String firstResult;
    private String maxResult;
    private String materialId;
    private String materialName;
    private String materialCode;
    private String materialType;
    private String materialUnit;
    private Float materialMinNum;
    private String materialBatchNum;
    private String materialBatchPrice;
    private String materialSequence;
    private String materialPassNum;
    private String materialPassNo;
    private String materialVersion;
    private String materialUse;
    private String materialPosition;
    private InputStream inputStream;
    
    private String menuId;
    
    private String logDo;
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
	
    @Resource(name="materialService")
    private MaterialService materialService;
    
    @Resource(name="materialFactoryService")
    private MaterialFactoryService materialFactoryService;
    @Resource(name="tableColumnsService")
    private TableColumnsService tableColumnsService;
    @Resource(name="menuService")
    private MenuService menuService;
    @Resource(name="operationService")
	private OperationService operationService;
    @Resource(name="searchBarService")
    private SearchBarService searchBarService;
    
	public String list(){
    	int firstResultInt = 1;
    	if(firstResult != null){
    		firstResultInt = Integer.parseInt(firstResult);
    	}
    	int maxResultInt = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("maxPageResult"));
    	if(maxResult != null){
    		maxResultInt = Integer.parseInt(maxResult);
    	}
    
    	Menu menu = menuService.getById(Integer.parseInt(menuId));
    	
    	User user = (User)session.get("user");
    	
    	Set<TableColumns> tableColumnsSet = tableColumnsService.list(user, menu);
    	
    	List<Map<String, String>> list = materialService.listByPage(tableColumnsSet, firstResultInt, maxResultInt);
    	
    	Set<Operation> operationSet = operationService.list(user, menu);
    	
    	SearchBar searchBar = searchBarService.getSearchBarByMenu(menu);

    	request.put("searchBar", searchBar);
    	request.put("list", list);
    	request.put("operations", operationSet);
    	
    	return SUCCESS;
    }
	
	
	public String listAndFactory(){
		
    	int firstResultInt = 1;
    	if(firstResult != null){
    		firstResultInt = Integer.parseInt(firstResult);
    	}
    	int maxResultInt = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("maxPageResult"));
    	if(maxResult != null){
    		maxResultInt = Integer.parseInt(maxResult);
    	}
    	//以后修改2.18
    	List<Material> list = null;
//    			materialService.listByPage(firstResultInt, maxResultInt);
    	
    	//获取该物料的供应商
    	HashMap<Material, List<LastStore>> materialMap = new HashMap<Material, List<LastStore>>();
    	if(logDo.equals("out")){
    		for (Material material : list) {
    			List<MaterialPrice> materialPrices = materialService.getMaterialFactories(material);
    			List<LastStore> lastStores = new ArrayList<LastStore>();
    			for (MaterialPrice materialPrice : materialPrices) {
					
					Set<MaterialFactory> materialFactories = materialPrice.getMaterialFactories();
					String materialFactoryId = null;
					String materialFactoryName = null;
					
					
					for (MaterialFactory materialFactory : materialFactories) {
						 
						materialFactoryName = materialFactory.getMaterialFactoryName()+"^";
						materialFactoryId = materialFactory.getMaterialFactoryId() + "^";
					}
					
					materialFactoryId = materialFactoryId.substring(0, materialFactoryId.length()-2);
					materialFactoryName = materialFactoryName.substring(0, materialFactoryName.length()-2);
					
					Float materialPartNum = materialPrice.getMaterialPartNum();
					int materialPriceId = materialPrice.getMaterialPriceId();
					Float inputPrice = materialPrice.getMaterialPriceInputprice();
					LastStore lastStore = new LastStore(materialPriceId, materialFactoryId, materialFactoryName, inputPrice, materialPartNum);
					lastStores.add(lastStore);
					
				}
    			materialMap.put(material, lastStores);
			}
    		request.put("materialMap", materialMap);
    	}
    	
    	
    	//获取所有的供应商
    	else if(logDo.equals("in")){
    		List<MaterialFactory> materialFactories = materialFactoryService.list();
    		request.put("materialFactories", materialFactories);
    		request.put("material_list", list);
    	}
    	
    	//获取物料的基本信息
    	else if(logDo.equals("mix")){
    		
//    		ArrayList<MaterialPlusChildren> materialPlusChildrens = new ArrayList<MaterialPlusChildren>();
//    		for (Material material : list) {
//    			String materialChildrenId = "";
//    			String materialChildrenName = "";
//    			String materialChildrenNum = "";
//    			
//    			Set<Material> materialChildren = material.getMaterialsForMaterialchildId();
//    			for (Material materialChild : materialChildren) {
//    				materialChildrenId = materialChild.getMaterialId()+",";
//    				materialChildrenName = materialChild.getMaterialName()+",";
//    				materialChildrenNum = materialChild.getMaterialNum()+",";
//				}
//    			
//				MaterialPlusChildren materialPlusChildren = 
//						new MaterialPlusChildren(material.getMaterialId(), material.getMaterialName(), material.getMaterialUnit(), material.getMaterialNum(), materialChildrenId, materialChildrenName, materialChildrenNum);
//				
//				materialPlusChildrens.add(materialPlusChildren);
//			}
//    		request.put("material_children", materialPlusChildrens);
    	}
    	
    	
    	System.out.println("logDo:"+logDo);
    	return logDo;
    }
	
	public String add(){
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		String materialCombineMaterialId = request.getParameter("materialCombine.materialId");
		System.out.println("materialCombineMaterialId:"+materialCombineMaterialId);

		Material material = new Material();
		if (materialBatchNum!=null&&materialBatchNum.length()>0) {
			material.setMaterialBatchNum(Float.parseFloat(materialBatchNum));
		}
		if (materialBatchPrice!=null&&materialBatchPrice.length()>0) {
			material.setMaterialBatchPrice(Float.valueOf(materialBatchPrice));
		}
		material.setMaterialCode(materialCode);
		if (materialMinNum!=null&&materialMinNum>0) {
			material.setMaterialMinNum(Float.valueOf(materialMinNum));
		}
		material.setMaterialName(materialName);
		if (materialPassNo!=null&&materialPassNo.length()>0) {
			material.setMaterialPassNo(Integer.parseInt(materialPassNo));
		}
		material.setMaterialPosition(materialPosition);
		material.setMaterialType(materialType);
		material.setMaterialUnit(materialUnit);
		material.setMaterialUse(materialUse);
		material.setMaterialVersion(materialVersion);
		material.setMaterialNum(0F);
		materialService.save(material);
		String result = "{\"statusCode\":\"200\",\"message\":\"物料添加成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String mix(){
		ActionContext context = ActionContext.getContext();  
		
		System.out.println("materialBatchNum:"+materialBatchNum);
		
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
//		String materialCombineMaterialName = request.getParameter("materialCombine.materialName");
		String [] materialChildIdsStr = request.getParameterValues("material_child_id");
		Integer [] materialChildIds = new Integer[materialChildIdsStr.length];
		for(int i = 0;i < materialChildIdsStr.length; i++){
			materialChildIds[i] = Integer.parseInt(materialChildIdsStr[i]);
			System.out.println("materialChildIds："+materialChildIds[i]);
		}
		
	    String [] materialChildNumsStr = request.getParameterValues("material_child_num");
	    Float [] materialChildNums = new Float[materialChildIdsStr.length];
		for(int i = 0;i < materialChildNumsStr.length; i++){
			materialChildNums[i] = Float.parseFloat(materialChildNumsStr[i]);
			System.out.println("materialChildNums:"+materialChildNums[i]);
		}

		Material material = new Material();
		if (materialBatchNum!=null&&materialBatchNum.length()>0) {
			material.setMaterialBatchNum(Float.parseFloat(materialBatchNum));
		}
		if (materialBatchPrice!=null&&materialBatchPrice.length()>0) {
			material.setMaterialBatchPrice(Float.valueOf(materialBatchPrice));
		}
		material.setMaterialCode(materialCode);
		if (materialMinNum!=null&&materialMinNum>0) {
			material.setMaterialMinNum(Float.valueOf(materialMinNum));
		}
		material.setMaterialName(materialName);
		if (materialPassNo!=null&&materialPassNo.length()>0) {
			material.setMaterialPassNo(Integer.parseInt(materialPassNo));
		}
		material.setMaterialPosition(materialPosition);
		material.setMaterialType(materialType);
		material.setMaterialUnit(materialUnit);
		material.setMaterialUse(materialUse);
		material.setMaterialVersion(materialVersion);
		
		materialService.materialMix(material, materialChildIds, materialChildNums);
		
		String result = "{\"statusCode\":\"200\",\"message\":\"物料添加成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String getChildren(){
		
		List<Map<String, Object>> materialChildren = materialService.getMaterialChildren(Integer.parseInt(materialId));
		
		String result = "";
		
		String json = JSONArray.toJSONString(materialChildren);
		
		System.out.println("json: "+json);
		result = json;
		
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public Float getMaterialMinNum() {
		return materialMinNum;
	}

	public void setMaterialMinNum(Float materialMinNum) {
		this.materialMinNum = materialMinNum;
	}

	public String getMaterialBatchNum() {
		return materialBatchNum;
	}

	public void setMaterialBatchNum(String materialBatchNum) {
		this.materialBatchNum = materialBatchNum;
	}

	public String getMaterialBatchPrice() {
		return materialBatchPrice;
	}

	public void setMaterialBatchPrice(String materialBatchPrice) {
		this.materialBatchPrice = materialBatchPrice;
	}

	public String getMaterialSequence() {
		return materialSequence;
	}

	public void setMaterialSequence(String materialSequence) {
		this.materialSequence = materialSequence;
	}

	public String getMaterialPassNum() {
		return materialPassNum;
	}

	public void setMaterialPassNum(String materialPassNum) {
		this.materialPassNum = materialPassNum;
	}

	public String getMaterialPassNo() {
		return materialPassNo;
	}

	public void setMaterialPassNo(String materialPassNo) {
		this.materialPassNo = materialPassNo;
	}

	public String getMaterialVersion() {
		return materialVersion;
	}

	public void setMaterialVersion(String materialVersion) {
		this.materialVersion = materialVersion;
	}

	public String getMaterialUse() {
		return materialUse;
	}

	public void setMaterialUse(String materialUse) {
		this.materialUse = materialUse;
	}

	public String getMaterialPosition() {
		return materialPosition;
	}

	public void setMaterialPosition(String materialPosition) {
		this.materialPosition = materialPosition;
	}

	public MaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}


	public String getLogDo() {
		return logDo;
	}


	public void setLogDo(String logDo) {
		this.logDo = logDo;
	}


	public String getMaterialId() {
		return materialId;
	}


	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


}
