package cn.btttech.service.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.btttech.entity.TableColumns;


public interface BaseService {

	public List<Map<String, String>> listByPage(Set<TableColumns> tableColumnsSet,final int firstResult, final int maxResult);
    
    public List<Map<String, String>> listByPage(Set<TableColumns> tableColumnsSet,Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<Map<String, String>> listByPage(Set<TableColumns> tableColumnsSet, Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public List<Map<String, String>> listColumns(Set<TableColumns> tableColumnsSet);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public <T> T getById(int id);
    
    public <T> void save(T T);
    
    public <T> List<T> list();

	
}
