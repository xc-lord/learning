package com.lord.learn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.lord.utils.PropertiesUtil;


public final class TestJdbcUtils {
	private static String url = "jdbc:mysql://localhost:3306/lord";
	private static String user = "root";
	private static String password = "root";
	private static String driver;
	
	private TestJdbcUtils() {
	}
	
	static {
		try {
			Properties properties = PropertiesUtil.readConfigFile("db_config.properties");
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("username");
			password = properties.getProperty("password");
			//注册jdbc驱动
			Class.forName(driver);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * 获得数据库连接
	 * @return
	 * @throws java.sql.SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * 释放连接对象
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
