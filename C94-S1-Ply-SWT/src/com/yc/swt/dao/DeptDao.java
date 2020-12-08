package com.yc.swt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public List<Map<String,Object>> select(String dname, String loc){
		DBHelper dbh = new DBHelper();
		String sql = "select * from dept where 1=1";
		List<Object> list = new ArrayList<>();
		
		// Null pointer access: The variable dname can only
		// be null at this location
		if(dname!=null && dname.trim().isEmpty() == false){
			sql += " and dname like ?"; // 模糊查询
			list.add("%" + dname.trim() + "%");
		}
		if(loc!=null && loc.trim().isEmpty() == false){
			sql += " and loc like ?"; // 模糊查询
			list.add("%" + loc.trim() + "%");
		}
		try {
			return dbh.selectList(sql, list.toArray());
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
