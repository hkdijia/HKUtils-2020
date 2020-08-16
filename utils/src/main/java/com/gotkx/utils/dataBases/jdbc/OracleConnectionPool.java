package com.gotkx.utils.dataBases.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleConnectionPool {
	public static ConnectionPool connPool = null;

	public OracleConnectionPool() {
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
