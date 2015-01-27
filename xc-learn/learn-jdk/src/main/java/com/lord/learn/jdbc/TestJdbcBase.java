package com.lord.learn.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TestJdbcBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			//createBatch();
			//read("卢俊");
			//read();
//			String sql = "select * from user where name=? and birthday<?";
//			Object[] params = new Object[] {"卢俊", new java.sql.Date(System.currentTimeMillis())};
//			read(sql, params);
			//System.out.println(find("select id as Id, name as Name, birthday as Birthday, money as Money from user where id=1", User.class));
			
			for (int i = 0; i < 11; i++) {
				Connection conn = TestJdbcUtils.getConnection();
				System.out.println(conn);
				TestJdbcUtils.free(null, null, conn);//必须放回去
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 批处理方式
	 */
	private static void createBatch() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO USER(NAME,birthday,money) VALUES(?,?,?)";
		try {
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			ps = conn.prepareStatement(sql);
			for(int i=0; i<100; i++) {
				ps.setString(1, "name" + i);
				ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				ps.setFloat(3, 100f + i);
				//批处理,打包sql语句
				ps.addBatch();
			}
			//4.执行语句
			int[] i = ps.executeBatch();
			
			System.out.println(i + " row affect");
		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, ps, conn);
		}
	}
	/**
	 * 基本的更新步骤
	 */
	private static void create() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "INSERT INTO USER(NAME,birthday,money) VALUES('萧铖','1989-04-02',897)";
		try {
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			st = conn.createStatement();
			//4.执行语句
			int i = st.executeUpdate(sql);
			
			System.out.println(i + " row affect");
		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, st, conn);
		}
	}
	
	/**
	 * 结果集的动态赋值
	 */
	private static Map<String, Object> find(String sql) throws Exception {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> data = null;
		try {
			//2.建立连接
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			ps = conn.prepareStatement(sql);
			//4.执行语句
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();//列个数
			
			if(rs.next()) {
				data = new HashMap<String, Object>();
				for(int i=1; i<=count; i++){
					//获得列名
					String columnName = rsmd.getColumnLabel(i);
					data.put(columnName, rs.getObject(columnName));
				}
			}

		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, st, conn);
		}
		return data;
	}
	/**
	 * 结果集的动态赋值
	 */
	private static Object find(String sql, Class clazz) throws Exception {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object data = null;
		try {
			//2.建立连接
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			ps = conn.prepareStatement(sql);
			//4.执行语句
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();//列个数
			
			Method[] mes = clazz.getMethods();
			if(rs.next()) {
				data = clazz.newInstance();
				for(int i=1; i<=count; i++){
					//获得列名
					String columnName = rsmd.getColumnLabel(i);
					//System.out.println(columnName);
					String methodName = "set" + columnName;
					for (Method method : mes) {
						if(method.getName().equals(methodName)) {
							method.invoke(data, rs.getObject(columnName));
						}
					}					
				}
			}

		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, st, conn);
		}
		return data;
	}
	/**
	 * 参数动态的查询
	 */
	private static void read(String sql, Object[] params) throws Exception {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			ps = conn.prepareStatement(sql);
			ParameterMetaData pmd = ps.getParameterMetaData();
			//获得参数个数
			int count = pmd.getParameterCount();
			if(params == null || params.length < count) {
				throw new RuntimeException("提供的参数与sql语句不对应");
			}
			for(int i=1; i<=count; i++) {
				ps.setObject(i, params[i-1]);
			}
			//4.执行语句
			rs = ps.executeQuery();
			
			//5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3));
			}
		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, st, conn);
		}
	}
	/**
	 * 基本的查询
	 */
	private static void read(String name) throws Exception {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where name=?";
		try {
			//2.建立连接
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			//4.执行语句
			rs = ps.executeQuery();
			
			//5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3));
			}
		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, st, conn);
		}
	}
	
	/**
	 * 基本的查询步骤
	 */
	private static void read() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = TestJdbcUtils.getConnection();
			//3.创建语句
			st = conn.createStatement();
			//4.执行语句
			rs = st.executeQuery("select * from user");
			//5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3));
			}
		} finally {		
			//6.释放资源
			TestJdbcUtils.free(rs, st, conn);
		}
	}

	/**
	 * 基本的jdbc步骤
	 */
	private static void test() throws SQLException, ClassNotFoundException {
		//1.注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());	//驱动中已实现
		System.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		
		//2.建立连接
		String url = "jdbc:mysql://localhost:3306/lord";
		String user = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, user, password);
		//3.创建语句
		Statement st = conn.createStatement();
		//4.执行语句
		ResultSet rs = st.executeQuery("select * from user");
		//5.处理结果
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t" + rs.getObject(3));
		}
		//6.释放资源
		rs.close();
		st.close();
		conn.close();
	}
}
