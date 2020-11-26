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
		// 查询部门表 dept
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stat = conn.createStatement();

		// 查询并返回结果集
		String sql = "select * from dept";
		ResultSet rs = stat.executeQuery(sql);
		
		// 输出结果集的内容
		// rs.next() 返回 boolean 控制游标（指针）指向下一行记录，
		// 如果有下一行记录，那么返回 true, 如果没有，则返回 false
		// rs.getXXX（参数） 获取游标指向的记录， 指定字段的值， XXX 表示类型 int String。。。
		
		while(rs.next()){
			// jdbc 字段编号从 1 开始计数
			System.out.print("编号：" + rs.getInt(1));
			System.out.print("\t名称：" + rs.getString(2));
			// oracle字段名会以大写的形式返回
			System.out.print("\t地址：" + rs.getString("LOC"));
			System.out.println();
		}
		// 关闭结果集
		rs.close();
		// 关闭语句
		stat.close();
		// 关闭连接
		conn.close();
		
	}

	public static void test1() throws ClassNotFoundException, SQLException {
		/*
		 * 执行一个 SQL 语句，向 Dept 添加一个部门
		 */

		// 1. 加载驱动
		// 类对象 ： class 对象.getClass() 类.class Class.forName("类路径")
		// oracle 驱动类路径 异常 ClassNotFoundException
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. 获取连接
		// 驱动管理器 DriverManager
		// localhost 本机域名 ==》 IP ：127.0.0.1 ==> 192.168.20.59
		// 1521 oracle 默认端口
		// orcl 数据库实例名 大小写忽略
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //
		String user = "scott";
		String password = "a";
		// 异常 SQLException
		Connection conn = DriverManager.getConnection(url, user, password);

		// 3. 创建语句
		Statement stat = conn.createStatement();

		// 4. 执行语句
		String sql = "insert into dept values(50,'财务部','衡阳')";
		// executeUpdate 执行所有非 查询（select）的语句
		int cnt = stat.executeUpdate(sql);

		System.out.println("新增了" + cnt + "个部门");

		// 5. 关闭资源
		stat.close();
		conn.close();
	}
	
/**
 * 作业
 * 1. 财务部入职一个叫张三的项目经理，工资3000，奖金 300， 入职时间 2019.1.1， 领导是KING
 * 2. 给张三涨工资 300
 * 3. 给张三添加一个办事员：李四：工资2000，奖金 500， 入职时间 2019.5.1
 * 4. 查询财务部所有员工，在控制台显示 编号，姓名，部门名称，工资，入职时间（格式：2019/02/02）
 * 5. 李四表现不佳，被开除
 * 6. 查询所有工资大于3000的员工，显示： 编号，姓名，部门名称，工资，奖金（没有奖金的显示“无”），领导的名字 
 * 7. 下次课默写 test2()
 */

}
