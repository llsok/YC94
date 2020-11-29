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
		// ��̬�� �� ����ص�ִ��һ��, �鲻�����׳��������쳣
		// 1.��������
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// �쳣ת�ͣ� �쳣��
			throw new RuntimeException("���ݿ���������ʧ�ܣ�", e);
		}
	}

	public Connection getConnection() throws SQLException {
		// 2.��ȡ����
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * ִ���޸����ݵ�SQL���
	 * 
	 * @param sql
	 * @param params
	 *            �ɱ�������� �� ���ֲ�������д�ڷ������������һ��
	 * @throws SQLException
	 */
	public int update(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("����: " + Arrays.toString(params));
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
	 * List<Map<String,Object>> ������ظ�
	 * 
	 * ��ѯ0~N�н����
	 * 
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectList(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("����: " + Arrays.toString(params));
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			List<Map<String, Object>> ret = new ArrayList<>();
			ResultSet rs = ps.executeQuery();

			// �����Ԫ���ݣ��������ݵ����ݣ�����
			ResultSetMetaData md = rs.getMetaData();
			// md.getColumnCount(); // ��ȡ�����������
			// md.getColumnName(column); // ��ȡָ����ŵ�����
			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				/**
				 * ȡ���������е���ֵ����� row ������
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
	 * ��ѯ select �������������
	 * 
	 * @param sql
	 * @return
	 */
	public long count(String sql, Object... params) throws SQLException {
		sql = "select count(*) from (" + sql + ")"; // �Ӳ�ѯ
		Object obj = selectValue(sql,params); //  BigDecimal  Long Double  ==> long
		return Long.parseLong("" + obj);  // �� obj ת�� �ַ���
	}

	/**
	 * ��ѯ select ����һ�е�һ���ֶ�ֵ����ѯ��ֵ��
	 * 
	 * @param sql
	 * @return
	 */
	public Object selectValue(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("����: " + Arrays.toString(params));
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
	 * ��ѯ select ���Ψһ�Ľ�������磺��������ֵ��ѯ����������ش���1�����ݣ����׳�SQL�쳣
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, Object> selectOne(String sql, Object... params) throws SQLException {
		Connection conn = getConnection();
		try {
			System.out.println("SQL: " + sql);
			System.out.println("����: " + Arrays.toString(params));
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			// �����Ԫ���ݣ��������ݵ����ݣ�����
			ResultSetMetaData md = rs.getMetaData();
			// md.getColumnCount(); // ��ȡ�����������
			// md.getColumnName(column); // ��ȡָ����ŵ�����
			if(rs.next() == false) {
				// 0 ��
				return null;
			} else {
				// 1~N ��
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);
					row.put(columnName, value);
				}
				if(rs.next()){
					// ���� 1��
					throw new SQLException("���������������1"); 
				} else {
					return row;
				}
			}
		} finally {
			conn.close();
		}
	}

}
