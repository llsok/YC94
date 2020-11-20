package com.yc.api.d1119;

import java.sql.*;

public class Demo1 {
	
	public static void main(String[] args) throws Exception {
		
		// ����2�����ţ� �ֿܲ⣬ �����ֿ�

//		1.��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.��ȡ����
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
		// �ر��Զ��ύ
		conn.setAutoCommit(false);
		
		String sql1 = "insert into dept values(50,'�ֿܲ�','hy')";
		String sql2 = "insert into dept values(51,'�����ֿ�','cs')";
		
		try{
			PreparedStatement ps1 = conn.prepareCall(sql1);
			PreparedStatement ps2 = conn.prepareCall(sql2);
			ps1.executeUpdate();
			int i = 1/0; // ��0�쳣
			ps2.executeUpdate();
			// û���쳣Ҫ�ύ
			conn.commit();
		} catch (Exception e){
			e.printStackTrace(); // ��ӡ�����׳��쳣
			// �����쳣Ҫ�ع�
			conn.rollback();
		} finally{
			// �ر����ӿ��Ը����ر��䴴��������ͽ����
			// ������û���쳣�� ��Ҫ�ر�����
			conn.close();
		}
		
	}

}
