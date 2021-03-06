package com.gotkx.utils.dataBases.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库连接池
 *
 * @author hd
 * @date 2017-4-26
 */
public class ConnectionPool {
	private String testTable = ""; // 测试连接是否可用的测试表名，默认没有测试表
	private int initialConnections = 10; // 连接池的初始大小
	private int incrementalConnections = 5; // 连接池自动增加的大小
	private int maxConnections = 50; // 连接池最大的大小
	private List<PooledConnection> connections = null;

	private static String DRIVER_CLASS;
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;

	private static Properties p = new Properties();
	static {
		System.out.println("oracle static块开始执行....");
		try {
			FileInputStream fis = new FileInputStream(
					"G:\\Normal_WorkSpace\\Java_Project\\for_idea\\HKUtils\\mavenExcel\\src\\main\\resources\\oracle.properties");
			p.load(fis);
			DRIVER_CLASS = p.getProperty("driver");
			URL = p.getProperty("url");
			USERNAME = p.getProperty("user");
			PASSWORD = p.getProperty("passwd");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConnectionPool() {
		try {
			createPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void createPool() throws Exception {
		// 确保连接池没有创建
		// 假如连接池己经创建了，保存连接的向量 connections 不会为空
		if (connections != null) {
			return; // 假如己经创建，则返回
		}
		// 实例化 JDBC Driver 中指定的驱动类实例
		Driver driver = (Driver) (Class.forName(DRIVER_CLASS).newInstance());
		DriverManager.registerDriver(driver); // 注册 JDBC 驱动程序
		// 创建保存连接的向量 , 初始时有 0 个元素
		connections = new LinkedList<PooledConnection>();
		// 根据 initialConnections 中设置的值，创建连接。
		createConnections(this.initialConnections);
		System.out.println(" 数据库连接池创建成功！ ");
	}

	private void createConnections(int numConnections) throws SQLException {
		// 循环创建指定数目的数据库连接
		for (int i = 0; i < numConnections; i++) {
			// 是否连接池中的数据库连接的数量己经达到最大？最大值由类成员 maxConnections
			// 指出，假如 maxConnections 为 0 或负数，表示连接数量没有限制。
			// 假如连接数己经达到最大，即退出。
			if (this.maxConnections > 0
					&& this.connections.size() >= this.maxConnections) {
				break;
			}
			// add a new PooledConnection object to connections vector
			// 增加一个连接到连接池中（向量 connections 中）
			try {
				connections.add(new PooledConnection(newConnection()));
			} catch (SQLException e) {
				System.out.println(" 创建数据库连接失败！ " + e.getMessage());
				throw new SQLException();
			}
			System.out.println(" 数据库连接" + i + "己创建 ......");
		}
	}

	private Connection newConnection() throws SQLException {
		// 创建一个数据库连接
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		// 假如这是第一次创建数据库连接，即检查数据库，获得此数据库答应支持的
		// 最大客户连接数目
		// connections.size()==0 表示目前没有连接己被创建
		if (connections.size() == 0) {
			DatabaseMetaData metaData = conn.getMetaData();
			int driverMaxConnections = metaData.getMaxConnections();
			// 数据库返回的 driverMaxConnections 若为 0 ，表示此数据库没有最大
			// 连接限制，或数据库的最大连接限制不知道
			// driverMaxConnections 为返回的一个整数，表示此数据库答应客户连接的数目
			// 假如连接池中设置的最大连接数量大于数据库答应的连接数目 , 则置连接池的最大
			// 连接数目为数据库答应的最大数目
			if (driverMaxConnections > 0
					&& this.maxConnections > driverMaxConnections) {
				this.maxConnections = driverMaxConnections;
			}
		}
		return conn; // 返回创建的新的数据库连接
	}

	public synchronized Connection getConnection() throws SQLException {
		// 确保连接池己被创建
		if (connections == null) {
			return null; // 连接池还没创建，则返回 null
		}
		Connection conn = getFreeConnection(); // 获得一个可用的数据库连接
		// 假如目前没有可以使用的连接，即所有的连接都在使用中
		while (conn == null) {
			// 等一会再试
			wait(250);
			conn = getFreeConnection(); // 重新再试，直到获得可用的连接，假如
			// getFreeConnection() 返回的为 null
			// 则表明创建一批连接后也不可获得可用连接
		}
		return conn; // 返回获得的可用的连接
	}

	private Connection getFreeConnection() throws SQLException {
		// 从连接池中获得一个可用的数据库连接
		Connection conn = findFreeConnection();
		if (conn == null) {
			// 假如目前连接池中没有可用的连接
			// 创建一些连接
			createConnections(incrementalConnections);
			// 重新从池中查找是否有可用连接
			conn = findFreeConnection();
			if (conn == null) {
				// 假如创建连接后仍获得不到可用的连接，则返回 null
				return null;
			}
		}
		return conn;
	}

	private Connection findFreeConnection() throws SQLException {
		Connection conn = null;
		// 获得连接池向量中所有的对象
		// 遍历所有的对象，看是否有可用的连接
		for (PooledConnection pConn : connections) {
			if (!pConn.isBusy()) {
				// 假如此对象不忙，则获得它的数据库连接并把它设为忙
				conn = pConn.getConnection();
				pConn.setBusy(true);
				// 测试此连接是否可用
				if (!testConnection(conn)) {
					// 假如此连接不可再用了，则创建一个新的连接，
					// 并替换此不可用的连接对象，假如创建失败，返回 null
					try {
						conn = newConnection();
					} catch (SQLException e) {
						System.out.println(" 创建数据库连接失败！ " + e.getMessage());
						return null;
					}
					pConn.setConnection(conn);
				}
				break; // 己经找到一个可用的连接，退出
			}
		}
		return conn; // 返回找到到的可用连接
	}

	private boolean testConnection(Connection conn) {
		try {
			// 判定测试表是否存在
			if (testTable.equals("")) {
				// 假如测试表为空，试着使用此连接的 setAutoCommit() 方法
				// 来判定连接否可用（此方法只在部分数据库可用，假如不可用 ,
				// 抛出异常）。注重：使用测试表的方法更可靠
				conn.setAutoCommit(true);
			} else { // 有测试表的时候使用测试表测试
				// check if this connection is valid
				Statement stmt = conn.createStatement();
				stmt.execute("select count(*) from " + testTable);
			}
		} catch (SQLException e) {
			// 上面抛出异常，此连接己不可用，关闭它，并返回 false;
			closeConnection(conn);
			return false;
		}
		// 连接可用，返回 true
		return true;
	}

	public void returnConnection(Connection conn) {
		// 确保连接池存在，假如连接没有创建（不存在），直接返回
		if (connections == null) {
			System.out.println(" 连接池不存在，无法返回此连接到连接池中 !");
			return;
		}
		// 遍历连接池中的所有连接，找到这个要返回的连接对象
		for (PooledConnection pConn : connections) {
			// 先找到连接池中的要返回的连接对象
			if (conn == pConn.getConnection()) {
				// 找到了 , 设置此连接为空闲状态
				pConn.setBusy(false);
				break;
			}
		}
	}

	// public synchronized void refreshConnections() throws SQLException {
	// // 确保连接池己创新存在
	// if (connections == null) {
	// System.out.println(" 连接池不存在，无法刷新 !");
	// return;
	// }
	//
	// for (PooledConnection pConn : connections) {
	// // 获得一个连接对象
	// // 假如对象忙则等 5 秒 ,5 秒后直接刷新
	// if (pConn.isBusy()) {
	// wait(5000); // 等 5 秒
	// }
	// // 关闭此连接，用一个新的连接代替它。
	// closeConnection(pConn.getConnection());
	// pConn.setConnection(newConnection());
	// pConn.setBusy(false);
	// }
	// }

	public synchronized void closeConnectionPool() throws SQLException {
		// 确保连接池存在，假如不存在，返回
		if (connections == null) {
			System.out.println(" 连接池不存在，无法关闭 !");
			return;
		}

		Iterator<PooledConnection> connIter = connections.iterator();

		int i = 0;
		while (connIter.hasNext()) {
			PooledConnection pConn = connIter.next();
			// 假如忙，等 5 秒
			if (pConn.isBusy()) {
				wait(5000); // 等 5 秒
			}
			// 5 秒后直接关闭它
			closeConnection(pConn.getConnection());
			// 从连接池向量中删除它
			connIter.remove();
			i++;
			System.out.println("删除第" + i + "个连接");
		}

		// 置连接池为空
		connections = null;
	}

	/**
	 * 释放连接
	 *
	 * @author hd
	 * @date 2017-4-26
	 * @param conn
	 */
	public synchronized void releaseConnection(Connection conn) {
		connections.add(new PooledConnection(conn));
		notifyAll();
	}

	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(" 关闭数据库连接出错： " + e.getMessage());
		}
	}

	private void wait(int mSeconds) {
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
		}
	}

	/*----------------get/set method start -------------------*/
	public int getInitialConnections() {
		return this.initialConnections;
	}

	public void setInitialConnections(int initialConnections) {
		this.initialConnections = initialConnections;
	}

	public int getIncrementalConnections() {
		return this.incrementalConnections;
	}

	public void setIncrementalConnections(int incrementalConnections) {
		this.incrementalConnections = incrementalConnections;
	}

	public int getMaxConnections() {
		return this.maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public String getTestTable() {
		return this.testTable;
	}

	public void setTestTable(String testTable) {
		this.testTable = testTable;
	}
	/*----------------get/set method end -------------------*/

}
