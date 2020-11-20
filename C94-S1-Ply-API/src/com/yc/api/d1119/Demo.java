package com.yc.api.d1119;

import java.sql.*;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
		// ����Ա��������ѯԱ����Ϣ
		//showEmp("SCOTT");
		// SQLע�빥��
		//showEmp("' or '1'='1");
		
		inserDept();
		inserDept1();
		deleteDept();
	}
	
	public static void showEmp(String ename) throws Exception{
		
//		1.��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.��ȡ����
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
//		3.�������
		// Statement stat = conn.createStatement();
		// Ԥ���������������е� ? �ǲ���ռλ��
		String sql = "select * from emp where ename=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
//		4.ִ�����
		//String sql = "select * from emp where ename='"+ename+"'";
		System.out.println(sql);
		// ���ò���==�� �滻ռλ��
		ps.setString(1, ename); // ������Ŵ�1 ��ʼ
		ResultSet rs = ps.executeQuery();  // ע�⣺��ִ��ʱ��Ҫ���� SQL ���
		
		while(rs.next()){
			System.out.print(rs.getString("ENAME") + "\t");
			System.out.print(rs.getString("DEPTNO") + "\t");
			System.out.println();
		}
//		5.�ر���Դ
		rs.close();
		ps.close();
		conn.close();
	}
	
	public static void inserDept() throws Exception{
		long begin = System.currentTimeMillis(); // ��ȡ��ǰʱ���
//		1.��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.��ȡ����
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement stat = conn.createStatement();
		for(int i=0; i<10000; i++){
//			3.�������
			String sql = "insert into dept values("+(100+i)+",'test','test')";
//			4.ִ�����
			stat.executeUpdate(sql);
		}
//		5.�ر���Դ
		conn.close();
		long time = System.currentTimeMillis() - begin; // ʱ�����ֵ
		System.out.printf("һ����ʱ%s��\n" , time);
		
		/**
		 * java.sql.SQLException: ORA-01438: ֵ����Ϊ����ָ����������
		 * �ֶεĳ��Ȳ���
		 * 
		 * java.sql.SQLException: ORA-00001: Υ��ΨһԼ������ (SCOTT.PK_DEPT)
		 * ����ֵ��ͻ
		 * 
		 */
	}
	
	public static void inserDept1() throws Exception{
		long begin = System.currentTimeMillis(); // ��ȡ��ǰʱ���
//		1.��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.��ȡ����
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
//		3.�������
		String sql = "insert into dept values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=0; i<10000; i++){
			ps.setInt(1, 60000+i);
			ps.setString(2, "test");
			ps.setString(3, "test");
//			4.ִ�����
			ps.executeUpdate();
		}
//		5.�ر���Դ
		conn.close();
		long time = System.currentTimeMillis() - begin; // ʱ�����ֵ
		System.out.printf("һ����ʱ%s��\n" , time);
	}
	
	public static void deleteDept() throws Exception{
		long begin = System.currentTimeMillis(); // ��ȡ��ǰʱ���
//		1.��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.��ȡ����
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
//		3.�������
		String sql = "delete from DEPT t where t.deptno>99";
		conn.prepareStatement(sql).executeUpdate();
//		5.�ر���Դ
		conn.close();
		long time = System.currentTimeMillis() - begin; // ʱ�����ֵ
		System.out.printf("һ����ʱ%s��\n" , time);
	}

}

