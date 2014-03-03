package cn.btttech.install.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateData {

	private static String DriverName = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://127.0.0.1:3306/store_1";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	private static String COLUMN_NAME = "COLUMN_NAME";
	private static String REMARKS = "REMARKS";
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
	//t_menu 插入数据 ----》t_privilege 插入------》t_menu补全privilege_id
    //---》插入t_table_columns----》插入t_privilege---》补全t_table_columns
    //---》插入t_operation---》插入t_privilege---》补全t_operation
	public static void main(String[] args) {
		
		int menuIdGen = 1;
		int privilegeIdGen = 1;
		int tableColumnsIdGen = 1;
		int operationIdGen = 1;
		
        CreateData createData = new CreateData();
        List<Map> listMaterialFactoryColumn = createData.getColumn("store_material_factory");
        List<Map> listMaterialColumn = createData.getColumn("store_material");
        List<Map> listMaterialLogColumn = createData.getColumn("store_log");
        System.out.println();
        //物料组件
        createData.menuInsert(menuIdGen++, 0, "'物料组件'", null, 1, null, null);
        
        createData.privilegeInsert(privilegeIdGen++, "'物料组件'", "'menu'");
        
        createData.menuUpdate(menuIdGen-1, privilegeIdGen-1);
        
        
        //物料信息
        createData.menuInsert(menuIdGen++, 1, "'物料信息'", "'material_list.action'", 2, null, "'Material'");
        
        createData.privilegeInsert(privilegeIdGen++, "'物料信息'", "'menu'");
        
        createData.menuUpdate(menuIdGen-1, privilegeIdGen-1);
        
        
        
        for (Map map : listMaterialColumn) {
			String column_code = "'"+(String) map.get(createData.COLUMN_NAME)+"'";
			String column_class = createData.columnsNameToClassName(column_code);
			String column_name = "'"+(String) map.get(createData.REMARKS)+"'";
        	createData.tableColumnsInsert(tableColumnsIdGen++, menuIdGen-1, "'store_material'", "'物料表'", column_code, column_class, column_name, 1);
        	createData.privilegeInsert(privilegeIdGen++, "'物料信息_"+column_name.substring(1,column_name.length()-1)+"'", "'table_columns'");
        	createData.tableColumnsUpdate(tableColumnsIdGen-1, privilegeIdGen-1);
		}
        
        createData.operationInsert(operationIdGen++, menuIdGen-1, "'一级物料添加'", "'/btt/material/material_addDisplay.jsp'", "'add'", 300, 300, "'dialog'", "'material_addDisplay'", 1);
        createData.privilegeInsert(privilegeIdGen++, "'一级物料添加'", "'operation'");
        createData.operationUpdate(operationIdGen-1, privilegeIdGen-1);
        createData.operationInsert(operationIdGen++, menuIdGen-1, "'物料拼合'", "'/btt/material/material_mixDisplay.jsp'", "'add'", 850, 500, "'dialog'", "'material_mixDisplay'", 2);
        createData.privilegeInsert(privilegeIdGen++, "'物料拼合'", "'operation'");
        createData.operationUpdate(operationIdGen-1, privilegeIdGen-1);
        
        //物料供应商
        createData.menuInsert(menuIdGen++, 1, "'物料供应商'", "'materialFactory_list.action'", 3, null, "'MaterialFactory'");
        
        createData.privilegeInsert(privilegeIdGen++, "'物料供应商'", "'menu'");
        
        createData.menuUpdate(menuIdGen-1, privilegeIdGen-1);
        
        for (Map map : listMaterialFactoryColumn) {
        	String column_code = "'"+(String) map.get(createData.COLUMN_NAME)+"'";
			String column_class = createData.columnsNameToClassName(column_code);
			String column_name = "'"+(String) map.get(createData.REMARKS)+"'";
        	createData.tableColumnsInsert(tableColumnsIdGen++, menuIdGen-1, "'store_material_factory'", "'物料供应商表'", column_code, column_class, column_name, 1);
        	createData.privilegeInsert(privilegeIdGen++, "'物料供应商_"+column_name.substring(1,column_name.length()-1)+"'", "'table_columns'");
        	createData.tableColumnsUpdate(tableColumnsIdGen-1, privilegeIdGen-1);
		}
        
        createData.operationInsert(operationIdGen++, menuIdGen-1, "'添加'", "'/btt/material/materialFactory_addDisplay.jsp'", "'add'", 300, 300, "'dialog'", "'material_addDisplay'", 1);
        createData.privilegeInsert(privilegeIdGen++, "'添加'", "'operation'");
        createData.operationUpdate(operationIdGen-1, privilegeIdGen-1);
        
        
        //物料出入库记录
        createData.menuInsert(menuIdGen++, 1, "'物料出入库记录'", "'materialLog_list.action'", 4, null, "'MaterialLog'");
        
        createData.privilegeInsert(privilegeIdGen++, "'物料出入库记录'", "'menu'");
        
        createData.menuUpdate(menuIdGen-1, privilegeIdGen-1);
        
        for (Map map : listMaterialLogColumn) {
        	String column_code = "'"+(String) map.get(createData.COLUMN_NAME)+"'";
			String column_class = createData.columnsNameToClassName(column_code);
			String column_name = "'"+(String) map.get(createData.REMARKS)+"'";
        	createData.tableColumnsInsert(tableColumnsIdGen++, menuIdGen-1, "'store_log'", "'物料出入库记录表'", column_code, column_class, column_name, 1);
        	createData.privilegeInsert(privilegeIdGen++, "'物料出入库记录_"+column_name.substring(1,column_name.length()-1)+"'", "'table_columns'");
        	createData.tableColumnsUpdate(tableColumnsIdGen-1, privilegeIdGen-1);
		}
        
        createData.operationInsert(operationIdGen++, menuIdGen-1, "'一级物料入库'", "'/btt/material/materialLog_firstin.jsp'", "'add'", 0, 0, "'dialog'", "'material_addDisplay'", 1);
        createData.privilegeInsert(privilegeIdGen++, "'一级物料入库'", "'operation'");
        createData.operationUpdate(operationIdGen-1, privilegeIdGen-1);
        createData.operationInsert(operationIdGen++, menuIdGen-1, "'物料出库'", "'/btt/material/materialLog_out.jsp'", "'add'", 0, 0, "'dialog'", "'material_addDisplay'", 2);
        createData.privilegeInsert(privilegeIdGen++, "'物料出库'", "'operation'");
        createData.operationUpdate(operationIdGen-1, privilegeIdGen-1);
        createData.operationInsert(operationIdGen++, menuIdGen-1, "'成品入库'", "'/btt/material/materialLog_mix.jsp'", "'add'", 0, 0, "'dialog'", "'material_addDisplay'", 3);
        createData.privilegeInsert(privilegeIdGen++, "'成品入库'", "'operation'");
        createData.operationUpdate(operationIdGen-1, privilegeIdGen-1);
        
	}

	private List<Map> getColumn(String tableName) {
		
        List<Map> list = new ArrayList<Map>(0);
        try {
			Class.forName(DriverName);
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			DatabaseMetaData dbMetaData = conn.getMetaData();
			rs = dbMetaData.getColumns(null, "%", tableName, "%");
			 
			while (rs.next()) {
				String columnName = rs.getString(COLUMN_NAME);
				String columnRemark = rs.getString(REMARKS);
				HashMap<String, String> map = new HashMap<String, String>(0);
				map.put(COLUMN_NAME, columnName);
				map.put(REMARKS, columnRemark);
				list.add(map);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return list;
	}
	
	private void menuInsert(int menu_id, int menu_parentid, String menu_name, String menu_url, 
			int menu_order, String menu_view, String menu_class) {
		String sql = "insert into t_menu(menu_id, menu_parentid, menu_name,"
				+ " menu_url, menu_order, menu_view, menu_class) "
				+ "values ("+menu_id+","+menu_parentid+","+menu_name+","
				+menu_url+","+menu_order+","+menu_view+","+menu_class+");";
		
		System.out.println(sql);
//		try {
//			ps = conn.prepareStatement(sql);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	private void privilegeInsert(int privilege_id, String privilege_name, String privilege_type_name){
		String sql = "insert into t_privilege(privilege_id, privilege_name, privilege_type_name) "
				+ "values ("+privilege_id+","+privilege_name+","+privilege_type_name+");"; 
		System.out.println(sql);
//		try {
//			ps = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private void menuUpdate(int menu_id, int privilege_id){
		String sql = "update t_menu set privilege_id = "+privilege_id+" where menu_id = "+menu_id+";";
		System.out.println(sql);
//		try {
//			ps = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private void tableColumnsInsert(int tablecolumns_id, int menu_id, String table_code, String table_name, 
			String column_code, String column_class, String column_name, int is_public){
		
		String sql = "insert into t_table_columns(tablecolumns_id, table_code,table_name,"
				+ " column_code, column_class, column_name, is_public) "
				+ "values ("+tablecolumns_id+","+table_code+","+table_name+","
				+column_code+","+column_class+","+column_name+","+is_public+");";
		
		String relationsql = "insert into menu_tablecolumns_relationship values("+menu_id+","+tablecolumns_id+");";
		
		System.out.println(sql);
		System.out.println(relationsql);
		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps = conn.prepareStatement(relationsql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	private void tableColumnsUpdate(int tablecolumns_id, int privilege_id){
		String sql = "update t_table_columns set privilege_id ="+privilege_id+" where tablecolumns_id ="+tablecolumns_id+";";
		System.out.println(sql);
//		try {
//			ps = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private void operationInsert(int operation_id, int menu_id, String operation_name,String operation_url,  
			String operation_icon_class, int operation_open_width, int operation_open_height, 
			String operation_target, String operation_open_rel, int operation_order){
		
		String sql = "insert into t_operation(operation_id, operation_name,operation_url,"
				+ " operation_icon_class, operation_open_width, operation_open_height, "
				+ "operation_target, operation_open_rel, operation_order) "
				+ "values ("+operation_id+","+operation_name+","+operation_url+","
				+operation_icon_class+","+operation_open_width+","+operation_open_height+","
				+operation_target+","+operation_open_rel+","+operation_order+");";
		
		String relationsql = "insert into menu_operation_relationship values("+menu_id+","+operation_id+");";
		
		System.out.println(sql);
		System.out.println(relationsql);
		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps = conn.prepareStatement(relationsql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	private void operationUpdate(int operation_id, int privilege_id){
		String sql = "update t_operation set privilege_id ="+privilege_id+" where operation_id ="+operation_id+";";
		System.out.println(sql);
//		try {
//			ps = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}	
	
	private String columnsNameToClassName(String columnsName){
		String[] temp = columnsName.split("_");
		for (int i=1; i<temp.length; i++) {
			temp[i] = temp[i].substring(0,1).toUpperCase()+temp[i].substring(1);
		}
		StringBuffer result = new StringBuffer();
		for (int j = 0; j < temp.length; j++) {
			   result.append( temp[j] );
			}
		return result.toString();
	}
}
