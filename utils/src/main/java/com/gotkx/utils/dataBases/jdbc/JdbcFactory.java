package com.gotkx.utils.dataBases.jdbc;

import java.sql.*;


public class JdbcFactory {

	public static String URL;
	public static String USERRNAME;
	public static String PASSWORD;

	public static Connection getConection() {
		Connection conn = null;
		try {
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
	 *@date 2017-3-24
	 *@return void
	 *@exception
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
		}
	}

	/**
	 * 获取数据库连接地址
	 *
	 * @author hd
	 *@date 2017-3-24
	 *@return String
	 *@exception
	 */
	public static String getUrl(int dbType, String ip, String dbName) {
		StringBuilder url = new StringBuilder();
		if (ComDef.SQL_SERVER == dbType) {
			url.append("jdbc:sqlserver://").append(ip).append(":1433;").append(
					"DatabaseName=").append(dbName).append(";");
		} else if (ComDef.ORACLE == dbType) {
			url.append("jdbc:oracle:thin:@").append(ip).append(":1521:")
					.append(dbName);
		}
		return url.toString();

	}

	/**
	 * 获取数据库连接驱动
	 *
	 * @author hd
	 *@date 2017-3-24
	 *@return String
	 *@exception
	 */
	public static String getClassDriver(int dbType) {
		String classDriver = "";
		if (ComDef.SQL_SERVER == dbType) {
			classDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		} else if (ComDef.ORACLE == dbType) {
			classDriver = "oracle.jdbc.driver.OracleDriver";
		}
		return classDriver;
	}
}
