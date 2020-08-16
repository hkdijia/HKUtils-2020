package com.gotkx.utils.dataBases.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SQLServerJdbc extends JdbcFactory {

	private static String DRIVER_CLASS;
	public static String URL;
	public static String USERRNAME;
	public static String PASSWORD;

	private static Properties p = new Properties();
	static {
		System.out.println("sqlserver static块开始执行....");
		try {
			FileInputStream fis = new FileInputStream(
					"resources/sqlserver.properties");
			p.load(fis);
			DRIVER_CLASS = p.getProperty("driver");
			URL = p.getProperty("url");
			USERRNAME = p.getProperty("user");
			PASSWORD = p.getProperty("passwd");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public static SQLServerConnectionPool connPool = new
	// SQLServerConnectionPool();

	public static Connection getConection() {
		Connection conn = null;
		try {
			// connPool.getConnection();
			conn = DriverManager.getConnection(URL, USERRNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 *
	 * @author hd
	 * @date 2017-3-24
	 * @return void
	 * @exception
	 */
	public static void closeConn(ResultSet rs, PreparedStatement stmt,
								 Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// connPool.releaseConnection(conn);
		}
	}

}
