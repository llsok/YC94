package com.yc.api.d1119;

import java.sql.*;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
		// 根据员工姓名查询员工信息
		//showEmp("SCOTT");
		// SQL注入攻击
		//showEmp("' or '1'='1");
		
		inserDept();
		inserDept1();
		deleteDept();
	}
	
	public static void showEmp(String ename) throws Exception{
		
//		1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
//		3.创建语句
		// Statement stat = conn.createStatement();
		// 预编译的语句对象，语句中的 ? 是参数占位符
		String sql = "select * from emp where ename=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
//		4.执行语句
		//String sql = "select * from emp where ename='"+ename+"'";
		System.out.println(sql);
		// 设置参数==》 替换占位符
		ps.setString(1, ename); // 参数编号从1 开始
		ResultSet rs = ps.executeQuery();  // 注意：在执行时不要传入 SQL 语句
		
		while(rs.next()){
			System.out.print(rs.getString("ENAME") + "\t");
			System.out.print(rs.getString("DEPTNO") + "\t");
			System.out.println();
		}
//		5.关闭资源
		rs.close();
		ps.close();
		conn.close();
	}
	
	public static void inserDept() throws Exception{
		long begin = System.currentTimeMillis(); // 获取当前时间戳
//		1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement stat = conn.createStatement();
		for(int i=0; i<10000; i++){
//			3.创建语句
			String sql = "insert into dept values("+(100+i)+",'test','test')";
//			4.执行语句
			stat.executeUpdate(sql);
		}
//		5.关闭资源
		conn.close();
		long time = System.currentTimeMillis() - begin; // 时间毫秒值
		System.out.printf("一共耗时%s秒\n" , time);
		
		/**
		 * java.sql.SQLException: ORA-01438: 值大于为此列指定的允许精度
		 * 字段的长度不足
		 * 
		 * java.sql.SQLException: ORA-00001: 违反唯一约束条件 (SCOTT.PK_DEPT)
		 * 主键值冲突
		 * 
		 */
	}
	
	public static void inserDept1() throws Exception{
		long begin = System.currentTimeMillis(); // 获取当前时间戳
//		1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
//		3.创建语句
		String sql = "insert into dept values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=0; i<10000; i++){
			ps.setInt(1, 60000+i);
			ps.setString(2, "test");
			ps.setString(3, "test");
//			4.执行语句
			ps.executeUpdate();
		}
//		5.关闭资源
		conn.close();
		long time = System.currentTimeMillis() - begin; // 时间毫秒值
		System.out.printf("一共耗时%s秒\n" , time);
	}
	
	public static void deleteDept() throws Exception{
		long begin = System.currentTimeMillis(); // 获取当前时间戳
//		1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
//		3.创建语句
		String sql = "delete from DEPT t where t.deptno>99";
		conn.prepareStatement(sql).executeUpdate();
//		5.关闭资源
		conn.close();
		long time = System.currentTimeMillis() - begin; // 时间毫秒值
		System.out.printf("一共耗时%s秒\n" , time);
	}

}

