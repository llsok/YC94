package com.yc.api.d1121;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {

	// OOP
	static {
		// 静态块 ： 类加载的执行一次, 块不允许抛出编译期异常
		// 1.加载驱动
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 异常转型， 异常链
			throw new RuntimeException("数据库驱动加载失败！", e);
		}
	}

	public Connection getConnection() throws SQLException {
		// 2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * 执行修改数据的SQL语句
	 * 
	 * @param sql
	 * @param params
	 *            可变参数数组 ， 这种参数必须写在方法参数的最后一个
	 * @throws SQLException
	 */
	public int update(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("参数: " + Arrays.toString(params));
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			return ps.executeUpdate();
		} finally {
			conn.close();
		}
	}

	/**
	 * List<Map<String,Object>> 有序可重复
	 * 
	 * 查询0~N行结果集
	 * 
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectList(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("参数: " + Arrays.toString(params));
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			List<Map<String, Object>> ret = new ArrayList<>();
			ResultSet rs = ps.executeQuery();

			// 结果集元数据（描述数据的数据）对象
			ResultSetMetaData md = rs.getMetaData();
			// md.getColumnCount(); // 获取结果集的列数
			// md.getColumnName(column); // 获取指定序号的列名
			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				/**
				 * 取出该行所有的列值，存放 row 集合中
				 */
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);
					row.put(columnName, value);
				}
				ret.add(row);
			}
			return ret;
		} finally {
			conn.close();
		}
	}

	/**
	 * 查询 select 语句结果集的行数
	 * 
	 * @param sql
	 * @return
	 */
	public long count(String sql, Object... params) throws SQLException {
		sql = "select count(*) from (" + sql + ")"; // 子查询
		Object obj = selectValue(sql,params); //  BigDecimal  Long Double  ==> long
		return Long.parseLong("" + obj);  // 将 obj 转成 字符串
	}

	/**
	 * 查询 select 语句第一行第一个字段值（查询单值）
	 * 
	 * @param sql
	 * @return
	 */
	public Object selectValue(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("参数: " + Arrays.toString(params));
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getObject(1) : null;
		} finally {
			conn.close();
		}
	}

	/**
	 * 查询 select 语句唯一的结果（例如：根据主键值查询），如果返回大于1行数据，则抛出SQL异常
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, Object> selectOne(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("参数: " + Arrays.toString(params));
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			// 结果集元数据（描述数据的数据）对象
			ResultSetMetaData md = rs.getMetaData();
			// md.getColumnCount(); // 获取结果集的列数
			// md.getColumnName(column); // 获取指定序号的列名
			if(rs.next() == false) {
				// 0 行
				return null;
			} else {
				// 1~N 行
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);
					row.put(columnName, value);
				}
				if(rs.next()){
					// 多于 1行
					throw new SQLException("结果集的行数大于1"); 
				} else {
					return row;
				}
			}
		} finally {
			conn.close();
		}
	}

}
