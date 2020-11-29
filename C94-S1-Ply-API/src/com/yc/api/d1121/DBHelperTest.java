package com.yc.api.d1121;

import java.sql.SQLException;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class DBHelperTest {

	@Test // ע�� ==�� ����������ע�� Junit �ǵ�Ԫ���Կ�ܣ��׺в��ԣ�
	public void test() throws SQLException {
		DBHelper dbh = new DBHelper();
		String sql = "update dept set dname=? where deptno=?";
		int ret = dbh.update(sql, "����", 10);
		// �Խ�����ж�
		Assert.assertEquals(1, ret); // ���� : �����ڽ�����������쳣
	}

	@Test // ������������
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
	 * ע�⣺ ���²��Ի��� SCOTT �û���ԭʼ��¼���ݣ�������޸����ݣ����ܻ���ɲ���ʧ�ܣ�
	 * 
	 * 		���Է����������޸Ĵ���
	 */

	/**
	 * ���Բ�ѯ������������� ==�� �Ӳ�ѯ
	 */
	@Test
	public void testCount() throws SQLException {
		String sql = "select * from emp where deptno=?";
		DBHelper dbh = new DBHelper();
		long count = dbh.count(sql, 10);
		Assert.assertEquals(3, count);
	}

	/**
	 * ���Բ�ѯ��ֵ���������
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

		// ��ѯ����ʣ� �����û�и����ݣ���߹�����KING �� 5000Ԫ
		sql = "select max(sal) maxsal from emp";
		int maxsal = Integer.parseInt("" + dbh.selectValue(sql));
		Assert.assertEquals(5000, maxsal);
	}

	/**
	 * ���Բ�ѯ�������ݽ��������
	 */
	@Test
	public void testSelectOne() throws SQLException {
		// ����������ѯ KING
		String sql = "select * from emp where empno=?";
		DBHelper dbh = new DBHelper();
		Map<String, Object> row = dbh.selectOne(sql, 7839);
		Assert.assertEquals("KING", row.get("ENAME"));

		// ���ݲ����ڵ�������ѯ null ���
		sql = "select * from emp where empno=?";
		row = dbh.selectOne(sql, 9999);
		Assert.assertNull(row); // ���� row Ϊ null �� û�б��Ϊ 9999 ��Ա��

		// ��ѯ���м�¼�����쳣
		try {
			sql = "select * from emp where deptno=?";
			row = dbh.selectOne(sql, 10);
		} catch (SQLException e) { 
			// throw new SQLException("���������������1");
			Assert.assertEquals("���������������1", e.getMessage());
		}
	}

}
