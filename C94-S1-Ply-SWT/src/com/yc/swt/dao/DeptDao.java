package com.yc.swt.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.swt.util.DBHelper;

/**
 * DAO ==> 负责执行SQL语句的类
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
	
	public void delete(String deptno) throws SQLException{
		String sql = "delete from dept where deptno = ?";
		DBHelper dbh = new DBHelper();
		dbh.update(sql, deptno);
	}
	
	/**
	 * 查询部门下的员工数量
	 * @param deptno
	 * @return
	 * @throws SQLException
	 */
	public long countEmp(String deptno) throws SQLException{
		String sql = "select * from emp where deptno = ?";
		DBHelper dbh = new DBHelper();
		return dbh.count(sql, deptno);
	}
	
	public void update(int deptno, String dname, String loc) throws SQLException{
		String sql = "update dept set dname=? ,loc=? where deptno=?";
		DBHelper dbh = new DBHelper();
		dbh.update(sql, dname, loc, deptno);
	}

}
