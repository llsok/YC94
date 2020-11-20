package com.yc.api.d1119;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo2 {
	
	public static void main(String[] args) throws Exception {
		
		query("S","S","S");
		
		query(null,"S","S");
		
		query("S","S",null);
		
		query(null,null,null);
		
	}
	
	/**
	 * 查询员工： 姓名，职位，所在部门名称
	 * 必须使用 预编译语句对象
	 * @throws Exception 
	 */
	public static void query(String ename, String job, String dname) throws Exception{
		
		/**
		 * 姓名 + 岗位
		 * 	select * from emp where ename=? and job=?
		 * 岗位
		 * 	select * from emp where job=?
		 * 无
		 * 	select * from emp
		 * 
		 */
		// 定义存放参数数组
		List<Object> paramList = new ArrayList<>() ;// 有序可重复
		String sql = "select * from emp a where 1=1";
		
		if(ename!=null){
			// 注意： 拼接sql 要注意空格
			sql += " and ename like ?";
			paramList.add("%" + ename + "%");
		}
		
		if(job!=null){
			sql += " and job like ?";
			paramList.add("%" + job + "%");
		}
		
		if(dname!=null){
			sql += " and exists(select * from dept b "
					+ " where a.deptno=b.deptno "
					+ " and b.dname like ?)";
			paramList.add("%" + dname + "%");
		}
		
		System.out.println("sql: "+sql);
		System.out.println("参数： " + paramList);
		
//		1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < paramList.size(); i++) {
			ps.setObject(i+1, paramList.get(i));
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("ENAME"));
		}
		conn.close();
		
	}

}
