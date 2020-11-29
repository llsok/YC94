package com.yc.api.d1121;

import java.sql.SQLException;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class DBHelperTest {

	@Test // 注解 ==》 给机器看的注释 Junit 是单元测试框架（白盒测试）
	public void test() throws SQLException {
		DBHelper dbh = new DBHelper();
		String sql = "update dept set dname=? where deptno=?";
		int ret = dbh.update(sql, "财务部", 10);
		// 对结果的判断
		Assert.assertEquals(1, ret); // 断言 : 不等于将会产生断言异常
	}

	@Test // 测试驱动开发
	public void test1() throws SQLException {

		String sql1 = "select * from emp";
		DBHelper dbh = new DBHelper();
		List<Map<String, Object>> list1 = dbh.selectList(sql1);
		Assert.assertEquals(14, list1.size());

		String sql2 = "select * from emp where deptno=10";
		List<Map<String, Object>> list2 = dbh.selectList(sql2);
		Assert.assertEquals(3, list2.size());
		Assert.assertEquals("CLARK", list2.get(0).get("ENAME"));

		System.out.println(list2);

	}
	
	/**
	 * 注意： 以下测试基于 SCOTT 用户的原始记录数据，如果有修改数据，可能会造成测试失败！
	 * 
	 * 		测试方法不允许修改代码
	 */

	/**
	 * 测试查询结果集总数方法 ==》 子查询
	 */
	@Test
	public void testCount() throws SQLException {
		String sql = "select * from emp where deptno=?";
		DBHelper dbh = new DBHelper();
		long count = dbh.count(sql, 10);
		Assert.assertEquals(3, count);
	}

	/**
	 * 测试查询单值结果集方法
	 */
	@Test
	public void testSelectValue() throws SQLException {
		String sql = "select ename from emp where empno=?";
		DBHelper dbh = new DBHelper();
		Object ename = dbh.selectValue(sql, 7839);
		Assert.assertEquals("KING", ename);

		sql = "select job from emp where empno=?";
		Object job = dbh.selectValue(sql, 7939);
		Assert.assertEquals("PRESIDENT", job);

		// 查询最大工资， 如果你没有改数据，最高工资是KING ， 5000元
		sql = "select max(sal) maxsal from emp";
		int maxsal = Integer.parseInt("" + dbh.selectValue(sql));
		Assert.assertEquals(5000, maxsal);
	}

	/**
	 * 测试查询单行数据结果集方法
	 */
	@Test
	public void testSelectOne() throws SQLException {
		// 根据主键查询 KING
		String sql = "select * from emp where empno=?";
		DBHelper dbh = new DBHelper();
		Map<String, Object> row = dbh.selectOne(sql, 7839);
		Assert.assertEquals("KING", row.get("ENAME"));

		// 根据不存在的主键查询 null 结果
		sql = "select * from emp where empno=?";
		row = dbh.selectOne(sql, 9999);
		Assert.assertNull(row); // 断言 row 为 null ， 没有编号为 9999 的员工

		// 查询多行记录产生异常
		try {
			sql = "select * from emp where deptno=?";
			row = dbh.selectOne(sql, 10);
		} catch (SQLException e) { 
			// throw new SQLException("结果集的行数大于1");
			Assert.assertEquals("结果集的行数大于1", e.getMessage());
		}
	}

}
