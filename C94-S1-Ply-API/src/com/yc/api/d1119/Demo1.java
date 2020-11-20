package com.yc.api.d1119;

import java.sql.*;

public class Demo1 {
	
	public static void main(String[] args) throws Exception {
		
		// 新增2个部门， 总仓库， 下属仓库

//		1.加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.获取连接
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url,user,password);
		// 关闭自动提交
		conn.setAutoCommit(false);
		
		String sql1 = "insert into dept values(50,'总仓库','hy')";
		String sql2 = "insert into dept values(51,'下属仓库','cs')";
		
		try{
			PreparedStatement ps1 = conn.prepareCall(sql1);
			PreparedStatement ps2 = conn.prepareCall(sql2);
			ps1.executeUpdate();
			int i = 1/0; // 除0异常
			ps2.executeUpdate();
			// 没有异常要提交
			conn.commit();
		} catch (Exception e){
			e.printStackTrace(); // 打印或者抛出异常
			// 出现异常要回滚
			conn.rollback();
		} finally{
			// 关闭连接可以附带关闭其创建语句对象和结果集
			// 不管有没有异常， 都要关闭连接
			conn.close();
		}
		
	}

}
