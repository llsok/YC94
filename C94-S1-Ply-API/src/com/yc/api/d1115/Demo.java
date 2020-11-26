package com.yc.api.d1115;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Demo {

	public static void main(String[] args) throws Exception {
		test2();
	}

	public static void test2() throws Exception {
		// ��ѯ���ű� dept
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stat = conn.createStatement();

		// ��ѯ�����ؽ����
		String sql = "select * from dept";
		ResultSet rs = stat.executeQuery(sql);
		
		// ��������������
		// rs.next() ���� boolean �����αָ꣨�룩ָ����һ�м�¼��
		// �������һ�м�¼����ô���� true, ���û�У��򷵻� false
		// rs.getXXX�������� ��ȡ�α�ָ��ļ�¼�� ָ���ֶε�ֵ�� XXX ��ʾ���� int String������
		
		while(rs.next()){
			// jdbc �ֶα�Ŵ� 1 ��ʼ����
			System.out.print("��ţ�" + rs.getInt(1));
			System.out.print("\t���ƣ�" + rs.getString(2));
			// oracle�ֶ������Դ�д����ʽ����
			System.out.print("\t��ַ��" + rs.getString("LOC"));
			System.out.println();
		}
		// �رս����
		rs.close();
		// �ر����
		stat.close();
		// �ر�����
		conn.close();
		
	}

	public static void test1() throws ClassNotFoundException, SQLException {
		/*
		 * ִ��һ�� SQL ��䣬�� Dept ���һ������
		 */

		// 1. ��������
		// ����� �� class ����.getClass() ��.class Class.forName("��·��")
		// oracle ������·�� �쳣 ClassNotFoundException
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. ��ȡ����
		// ���������� DriverManager
		// localhost �������� ==�� IP ��127.0.0.1 ==> 192.168.20.59
		// 1521 oracle Ĭ�϶˿�
		// orcl ���ݿ�ʵ���� ��Сд����
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //
		String user = "scott";
		String password = "a";
		// �쳣 SQLException
		Connection conn = DriverManager.getConnection(url, user, password);

		// 3. �������
		Statement stat = conn.createStatement();

		// 4. ִ�����
		String sql = "insert into dept values(50,'����','����')";
		// executeUpdate ִ�����з� ��ѯ��select�������
		int cnt = stat.executeUpdate(sql);

		System.out.println("������" + cnt + "������");

		// 5. �ر���Դ
		stat.close();
		conn.close();
	}
	
/**
 * ��ҵ
 * 1. ������ְһ������������Ŀ��������3000������ 300�� ��ְʱ�� 2019.1.1�� �쵼��KING
 * 2. �������ǹ��� 300
 * 3. ���������һ������Ա�����ģ�����2000������ 500�� ��ְʱ�� 2019.5.1
 * 4. ��ѯ��������Ա�����ڿ���̨��ʾ ��ţ��������������ƣ����ʣ���ְʱ�䣨��ʽ��2019/02/02��
 * 5. ���ı��ֲ��ѣ�������
 * 6. ��ѯ���й��ʴ���3000��Ա������ʾ�� ��ţ��������������ƣ����ʣ�����û�н������ʾ���ޡ������쵼������ 
 * 7. �´ο�Ĭд test2()
 */

}
