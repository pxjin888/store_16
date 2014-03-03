package cn.btttech.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.filechooser.FileSystemView;
/**
 * 动态生成权限表中的sql语句
 * @author HAHA
 *
 */
public class DBUtil {
	
	/**
	 * 这里是Oracle连接方法
	 *private static final String driver = "oracle.jdbc.driver.OracleDriver";
	 *private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	 *private static final String uid = "system";
	 *private static final String pwd = "sys";
	 *这里是SQL Server连接方法
	 *private static final String url = "jdbc:sqlserver://localhost:1433;DateBaseName=数据库名";
	 *private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	 *private static final String uid = "sa";
	 *private static final String pwd = "sa";
	 *
	 *
	 * 这里是MySQL连接方法
	 */
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String pwd="root";
	private static final String user="root";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/store_1"
			+ "?user=" + user + "&password=" + pwd
			+ "&useUnicode=true&characterEncoding=UTF-8";
	private static Connection getConnection=null;

	public static void main(String[] args) {
		FileSystemView fsv=FileSystemView.getFileSystemView();
		String path=fsv.getHomeDirectory().toString();//获取当前用户桌面路径
		System.out.println(path);
		getConnection=getConnections();
		try {
			DatabaseMetaData dbmd=getConnection.getMetaData();
		    ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
		    while (resultSet.next()) {
		    	String tableName=resultSet.getString("TABLE_NAME");
//		    	System.out.println(tableName);
		    	if(tableName.equals("store_log")){
		    		//ResultSet rs =getConnection.getMetaData().getColumns(null, getXMLConfig.getSchema(),tableName.toUpperCase(), "%");//其他数据库不需要这个方法的，直接传null，这个是oracle和db2这么用
		    		ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
		    		File directory = new File(path);
					FileWriter fw = new FileWriter(directory+ "\\"+tableName.toUpperCase()+".xml");
					PrintWriter pw = new PrintWriter(fw);
		    		System.out.println("表名："+tableName+"\t\n表字段信息：");
		    		pw.write("<p filid=\"xx\" table=\""+tableName.toUpperCase()+"\" zj=\"xx\"/>\n");
		    		while(rs.next()){
//		    			pw.write("\t<p code=\""+rs.getString("COLUMN_NAME").toUpperCase()+"\" name=\""+rs.getString("REMARKS")+"\"/>\n");
//		    			System.out.println("字段名："+rs.getString("COLUMN_NAME")+"\t字段注释："+rs.getString("REMARKS")+"\t字段数据类型："+rs.getString("TYPE_NAME"));
		    			System.out.println("INSERT INTO T_TABLE_COLUMNS ("+tableName+",日志表,"+rs.getString("COLUMN_NAME")+","+rs.getString("REMARKS")+",1"+");");
		    		}
		    		pw.write("</p>");
	    			pw.flush();
					pw.close();
					System.out.println("生成成功！");
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnections(){
		try {
			//Properties props =new Properties();
			//props.put("remarksReporting","true");
			Class.forName(driver);
			getConnection=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getConnection;
	}
	////其他数据库不需要这个方法 oracle和db2需要
	 public static String getSchema() throws Exception {
			String schema;
			schema =getConnection.getMetaData().getUserName();
			if ((schema == null) || (schema.length() == 0)) {
			    throw new Exception("ORACLE数据库模式不允许为空");
			}
			return schema.toUpperCase().toString();

	}

}
