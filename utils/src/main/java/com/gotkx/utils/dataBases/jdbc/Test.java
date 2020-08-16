package com.gotkx.utils.dataBases.jdbc;

import java.sql.*;

public class Test {
	public static void main(String[] args) {
		linkOracle();
	}

	/**
	 * 连接SQL SERVER示例
	 *
	 * @author hd
	 *@date 2017-3-24
	 *@return void
	 *@exception
	 */
	public static void linkSQLServer() {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=TEST;";
		String sql = "select * from student_info";
		try {
			// 连接数据库
			conn = DriverManager.getConnection(url, "sa", "sa");
			// 建立Statement对象
			stmt = conn.createStatement();
			/**
			 * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
			 */
			// 执行数据库查询语句
			rs = stmt.executeQuery(sql);
			/**
			 * ResultSet executeQuery(String sql) throws SQLException 执行给定的 SQL
			 * 语句，该语句返回单个 ResultSet 对象
			 */
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String name = rs.getString("student_name");
				int age = rs.getInt("student_age");
				System.out.println("student_id:" + id + "\tstudent_name:"
						+ name + "\tstudent_age:" + age);
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}

	public static void linkOracle() {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:HK";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "arch_app";// 用户名,系统默认的账户名
			String password = "11";// 你安装时选设置的密码
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");
			String sql = "select * from doc_card where doc_seq=?";// 预编译语句，“？”代表参数
			pre = con.prepareStatement(sql);// 实例化预编译语句
			//pre.setString(1, "刘显安");// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			pre.setLong(1, 20170100019091L);
			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			while (result.next())
				// 当结果集不为空时
				System.out.println("流水号:" + result.getLong("doc_seq") + "题名:"
						+ result.getString("doc_title"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
