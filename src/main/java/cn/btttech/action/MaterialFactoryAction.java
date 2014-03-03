package cn.btttech.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.Menu;
import cn.btttech.entity.Operation;
import cn.btttech.entity.SearchBar;
import cn.btttech.entity.TableColumns;
import cn.btttech.entity.User;
import cn.btttech.service.comm.MenuService;
import cn.btttech.service.comm.OperationService;
import cn.btttech.service.comm.SearchBarService;
import cn.btttech.service.comm.TableColumnsService;
import cn.btttech.service.comm.UserService;
import cn.btttech.service.material.MaterialFactoryService;

public class MaterialFactoryAction extends ActionBase {
	
	private static final long serialVersionUID = 64601525869588514L;
	private String firstResult;
    private String maxResult;
    
    private String materialFactoryCode;
    private String materialFactoryName;
    private String materialFactoryAddress;
    private String materialFactoryContactName;
    private String materialFactoryTitle;
    private String materialFactoryContactphone;
    private String materialFactoryContactOther;
    private String materialFactoryMail;
    private String materialFactoryWeb;
    private InputStream inputStream;
    
    private String menuId;
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
    @Resource(name="menuService")
    private MenuService menuService;
	@Resource(name="materialFactoryService")
    private MaterialFactoryService materialFactoryService;
	@Resource(name="tableColumnsService")
    private TableColumnsService tableColumnsService;
	@Resource(name="operationService")
	private OperationService operationService;
	@Resource(name="userService")
	private UserService userService;
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
    	
    	List<Map<String, String>> list = materialFactoryService.listByPage(tableColumnsSet, firstResultInt, maxResultInt);
    	
    	Set<Operation> operationSet = operationService.list(user, menu);
    	
    	SearchBar searchBar = searchBarService.getSearchBarByMenu(menu);

    	request.put("searchBar", searchBar);
    	request.put("list", list);
    	request.put("operations", operationSet);
    	
    	return SUCCESS;
    }
	
	public String add(){
		MaterialFactory factory = new MaterialFactory();
		factory.setMaterialFactoryCode(materialFactoryCode);
		factory.setMaterialFactoryAddress(materialFactoryAddress);
		factory.setMaterialFactoryContactName(materialFactoryContactName);
		factory.setMaterialFactoryContactOther(materialFactoryContactOther);
		factory.setMaterialFactoryContactphone(Long.parseLong(materialFactoryContactphone));
		factory.setMaterialFactoryMail(materialFactoryMail);
		factory.setMaterialFactoryName(materialFactoryName);
		factory.setMaterialFactoryTitle(materialFactoryTitle);
		factory.setMaterialFactoryWeb(materialFactoryWeb);
		
		materialFactoryService.save(factory);
		
		String result = "{\"statusCode\":\"200\",\"message\":\"供应商添加成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
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

	public String getMaterialFactoryCode() {
		return materialFactoryCode;
	}

	public void setMaterialFactoryCode(String materialFactoryCode) {
		this.materialFactoryCode = materialFactoryCode;
	}

	public String getMaterialFactoryName() {
		return materialFactoryName;
	}

	public void setMaterialFactoryName(String materialFactoryName) {
		this.materialFactoryName = materialFactoryName;
	}

	public String getMaterialFactoryAddress() {
		return materialFactoryAddress;
	}

	public void setMaterialFactoryAddress(String materialFactoryAddress) {
		this.materialFactoryAddress = materialFactoryAddress;
	}

	public String getMaterialFactoryContactName() {
		return materialFactoryContactName;
	}

	public void setMaterialFactoryContactName(String materialFactoryContactName) {
		this.materialFactoryContactName = materialFactoryContactName;
	}

	public String getMaterialFactoryTitle() {
		return materialFactoryTitle;
	}

	public void setMaterialFactoryTitle(String materialFactoryTitle) {
		this.materialFactoryTitle = materialFactoryTitle;
	}

	public String getMaterialFactoryContactphone() {
		return materialFactoryContactphone;
	}

	public void setMaterialFactoryContactphone(String materialFactoryContactphone) {
		this.materialFactoryContactphone = materialFactoryContactphone;
	}

	public String getMaterialFactoryContactOther() {
		return materialFactoryContactOther;
	}

	public void setMaterialFactoryContactOther(String materialFactoryContactOther) {
		this.materialFactoryContactOther = materialFactoryContactOther;
	}

	public String getMaterialFactoryMail() {
		return materialFactoryMail;
	}

	public void setMaterialFactoryMail(String materialFactoryMail) {
		this.materialFactoryMail = materialFactoryMail;
	}

	public String getMaterialFactoryWeb() {
		return materialFactoryWeb;
	}

	public void setMaterialFactoryWeb(String materialFactoryWeb) {
		this.materialFactoryWeb = materialFactoryWeb;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
