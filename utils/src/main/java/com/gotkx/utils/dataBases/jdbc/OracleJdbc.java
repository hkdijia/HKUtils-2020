package com.gotkx.utils.dataBases.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleJdbc {

	public static ConnectionPool connPool = new ConnectionPool();

	public static Connection getConection() {
		Connection conn = null;
		try {
			conn = connPool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(conn);
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
			connPool.releaseConnection(conn);
		}
	}

}
