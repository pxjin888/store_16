package cn.btttech.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.btttech.entity.MaterialLog;
import cn.btttech.entity.Menu;
import cn.btttech.entity.Operation;
import cn.btttech.entity.SearchBar;
import cn.btttech.entity.TableColumns;
import cn.btttech.entity.User;
import cn.btttech.service.comm.MenuService;
import cn.btttech.service.comm.OperationService;
import cn.btttech.service.comm.SearchBarService;
import cn.btttech.service.comm.TableColumnsService;
import cn.btttech.service.material.MaterialFactoryService;
import cn.btttech.service.material.MaterialLogService;
import cn.btttech.service.material.MaterialPriceService;
import cn.btttech.service.material.MaterialService;
import cn.btttech.util.UuidGenerator;

public class MaterialLogAction extends ActionBase {

	private static final long serialVersionUID = 638241257683967654L;
	private String firstResult;
    private String maxResult;
    
    private String logDo;
    private String logMaterialUse;
    private String logMaterialProject;
    private String logBuyRequirecode;
    private String logBuyAgreementcode;
    private String logRemark;
    
    private String materialId;
	private String materialProviderName;
	private String materialProviderId;
	private String materialPartNum;
	private String materialNum;
	private String materialPriceId;
	private String materialInputPrice;
	
	private Integer [] materialChildId;
	private Float [] materialFormulaNum;
    
	private String menuId;
	
    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }
    
    @Resource(name="materialLogService")
	private MaterialLogService materialLogService;
    @Resource(name="materialService")
	private MaterialService materialService;
    @Resource(name="materialPriceService")
	private MaterialPriceService materialPriceService;
    @Resource(name="materialFactoryService")
	private MaterialFactoryService materialFactoryService;
    @Resource(name="operationService")
	private OperationService operationService;
    @Resource(name="tableColumnsService")
    private TableColumnsService tableColumnsService;
    @Resource(name="menuService")
    private MenuService menuService;
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
    	
    	SearchBar searchBar = searchBarService.getSearchBarByMenu(menu);
    	
    	List<Map<String, String>> list = materialLogService.listByPage(tableColumnsSet, firstResultInt, maxResultInt);
    	
    	Set<Operation> operationSet = operationService.list(user, menu);
    	request.put("list", list);
    	request.put("operations", operationSet);
    	request.put("searchBar", searchBar);
    	
    	return SUCCESS;
    }
    
    public String add(){
		
		String uuid = UuidGenerator.generate();
		
		if(logDo.equals("in")){
			
				MaterialLog logMaterial = new MaterialLog();
				logMaterial.setLogBuyAgreementcode(logBuyAgreementcode);
				logMaterial.setLogBuyRequirecode(logBuyRequirecode);
				logMaterial.setLogDo(logDo);
				logMaterial.setLogMaterialInputPrice(Float.parseFloat(materialInputPrice));
				logMaterial.setLogMaterialNum(Float.parseFloat(materialNum));
				logMaterial.setLogMaterialProject(logMaterialProject);
				logMaterial.setLogMaterialUse(logMaterialUse);
				logMaterial.setLogRemark(logRemark);
				logMaterial.setLogTime(new Date());
				logMaterial.setLogCode(uuid);
				
				materialLogService.firstMaterialInput(logMaterial, Integer.parseInt(materialId), Integer.parseInt(materialProviderId), Float.parseFloat(materialNum), Float.parseFloat(materialInputPrice));
				
			
		}else if(logDo.equals("out")){
			
			MaterialLog logMaterial = new MaterialLog();
			logMaterial.setLogDo(logDo);
			logMaterial.setLogMaterialNum(Float.parseFloat(materialNum));
			logMaterial.setLogMaterialProject(logMaterialProject);
			logMaterial.setLogMaterialUse(logMaterialUse);
			logMaterial.setLogRemark(logRemark+"{"+"价格："+materialInputPrice+",数量："+materialPartNum+",厂商："+materialProviderName+"}");
			logMaterial.setLogTime(new Date());
			logMaterial.setLogCode(uuid);
			
			String [] materialCombineMaterialProviderIdArray = materialProviderId.split("&");
			
			String [] materialCombineMaterialPartNumArray = materialPartNum.split("&");
			
			String [] materialCombineMaterialPriceIdArray = materialPriceId.split("&");
			materialLogService.materialOutput(logMaterial, Integer.parseInt(materialId), materialCombineMaterialProviderIdArray, materialCombineMaterialPriceIdArray, materialCombineMaterialPartNumArray, Float.parseFloat(materialNum));
			
			
		}else if(logDo.equals("mix")){
			MaterialLog logMaterial = new MaterialLog();
			logMaterial.setLogDo(logDo);
			logMaterial.setLogTime(new Date());
			logMaterial.setLogCode(uuid);
			
			materialLogService.mixMaterialInput(logMaterial, Integer.parseInt(materialId), materialChildId, materialFormulaNum, Float.parseFloat(materialNum));
		}
		
		String result = "{\"statusCode\":\"200\",\"message\":\"添加成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
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

	public String getLogDo() {
		return logDo;
	}

	public void setLogDo(String logDo) {
		this.logDo = logDo;
	}

	public String getLogMaterialUse() {
		return logMaterialUse;
	}

	public void setLogMaterialUse(String logMaterialUse) {
		this.logMaterialUse = logMaterialUse;
	}

	public String getLogMaterialProject() {
		return logMaterialProject;
	}

	public void setLogMaterialProject(String logMaterialProject) {
		this.logMaterialProject = logMaterialProject;
	}

	public String getLogBuyRequirecode() {
		return logBuyRequirecode;
	}

	public void setLogBuyRequirecode(String logBuyRequirecode) {
		this.logBuyRequirecode = logBuyRequirecode;
	}

	public String getLogBuyAgreementcode() {
		return logBuyAgreementcode;
	}

	public void setLogBuyAgreementcode(String logBuyAgreementcode) {
		this.logBuyAgreementcode = logBuyAgreementcode;
	}

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialProviderName() {
		return materialProviderName;
	}

	public void setMaterialProviderName(String materialProviderName) {
		this.materialProviderName = materialProviderName;
	}

	public String getMaterialProviderId() {
		return materialProviderId;
	}

	public void setMaterialProviderId(String materialProviderId) {
		this.materialProviderId = materialProviderId;
	}

	public String getMaterialPartNum() {
		return materialPartNum;
	}

	public void setMaterialPartNum(String materialPartNum) {
		this.materialPartNum = materialPartNum;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getMaterialPriceId() {
		return materialPriceId;
	}

	public void setMaterialPriceId(String materialPriceId) {
		this.materialPriceId = materialPriceId;
	}

	public String getMaterialInputPrice() {
		return materialInputPrice;
	}

	public void setMaterialInputPrice(String materialInputPrice) {
		this.materialInputPrice = materialInputPrice;
	}

	public Integer[] getMaterialChildId() {
		return materialChildId;
	}

	public void setMaterialChildId(Integer[] materialChildId) {
		this.materialChildId = materialChildId;
	}

	public Float[] getMaterialFormulaNum() {
		return materialFormulaNum;
	}

	public void setMaterialFormulaNum(Float[] materialFormulaNum) {
		this.materialFormulaNum = materialFormulaNum;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
