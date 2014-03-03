package cn.btttech.service.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.TableColumns;

@SuppressWarnings({"hiding","unchecked"})
@Transactional(readOnly = true)
public abstract class AbstractService<T> implements BaseService {

	@Autowired
    private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<Map<String, String>> listByPage(Set<TableColumns> tableColumnsSet, int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return listByPage(tableColumnsSet, new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	
	@Override
	public List<Map<String, String>> listByPage(Set<TableColumns> tableColumnsSet, Object condition, Object param,
			int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return listByPage(tableColumnsSet, new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<Map<String, String>> listByPage(Set<TableColumns> tableColumnsSet, Object[] conditions, Object[] params,
			int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		String cond = "";
		if(conditions.length != 0){
			cond = " where";
		}
		for(int i=0;i<conditions.length;i++){
			cond+=" "+conditions[i]+" and";
		}
		if(cond.endsWith("and")){
			cond = cond.substring(0, cond.length()-3);
		}
		String totalColumnClass = " ";
		List<Map<String, String>> titleList = listColumns(tableColumnsSet);
		
		for (Map<String, String> title : titleList) {
			totalColumnClass += title.get("columnClass")+" as "+title.get("columnName")+",";
		}
		totalColumnClass = totalColumnClass.substring(0, totalColumnClass.length()-1);
		totalColumnClass = " new map("+totalColumnClass + ") ";
		List<Map<String, String>> list = null;
		if(tableColumnsSet.size() != 0){
			list = (List<Map<String, String>>) baseDao.findByPage(
					"select"+totalColumnClass+"from "+((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName()+" "+cond, (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0], params,firstResult,maxResult);
		}
		
		return list;
	}

	public List<Map<String, String>> listColumns(Set<TableColumns> tableColumnsSet){
		List<Map<String, String>> titleList = new ArrayList<Map<String, String>>(0);
		
		for (TableColumns tableColumns : tableColumnsSet) {
			if(tableColumns.getIsPublic() == 1){
				Map<String, String> map = new HashMap<String, String>(0);
				map.put("columnClass", tableColumns.getColumnClass());
				map.put("columnName", tableColumns.getColumnName());
				titleList.add(map);
			}
		}
		return titleList;
	}
	
	@Override
	public int listByPageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int listByPageCount(Object condition, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int listByPageCount(Object[] conditions, Object[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T getById(int id) {
		// TODO Auto-generated method stub
		return baseDao.get((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0], id);
	}

	@Override
	@Transactional(readOnly = false)
	public <T> void save(T T) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	
}
