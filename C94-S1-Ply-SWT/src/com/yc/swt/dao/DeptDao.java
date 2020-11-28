package com.yc.swt.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.swt.util.DBHelper;

/**
 * DAO
 * Data Access Object 数据访问对象
 */
public class DeptDao {
	
	public List<Map<String,Object>> selectAll(){
		DBHelper dbh = new DBHelper();
		String sql = "select * from dept";
		try {
			return dbh.selectList(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public void insert(int deptno, String dname, String loc) throws SQLException{
		String sql = "insert into dept values (?,?,?)";
		DBHelper dbh = new DBHelper();
		dbh.update(sql, deptno, dname, loc);
	}

}
