package com.gotkx.utils.dataBases.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SQLServerConnectionPool {
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

	public static ConnectionPool connPool = null;

	public SQLServerConnectionPool() {
		connPool = new ConnectionPool();
	}

	/**
	 * 获取连接
	 *
	 * @author hd
	 * @date 2017-5-17
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = connPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放连接
	 *
	 * @author hd
	 * @date 2017-5-17
	 * @param conn
	 */
	public void releaseConnection(Connection conn) {
		connPool.releaseConnection(conn);
	}
}
