package com.yc.api.d1107;

public class Demo1 {
	
	public static void main(String[] args) {
		/*test();
		test1();*/
		
		String name = "";
		String pwd = "123";
		try{
			login(name,pwd);
			System.out.println("欢迎：" + name);
		} catch (LoginException e){
			// message ==> 异常信息
			System.out.println(e.getMessage());
		}

		name = "张三";
		try{
			login(name,pwd);
			System.out.println("欢迎：" + name);
		} catch (LoginException e){
			// message ==> 异常信息
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * 编译期异常
	 */
	public static void test1(){
		// 抛出一个编译期异常
		int a=1;
		try{
			// A
			throw new Exception();
		} catch (Exception e){
			// B
			// 打印异常
			e.printStackTrace();
		} finally {
			// C
		}
		
		// A C 没有异常 
		// A B C 有异常
		
		int b=2;
		
	}
	
	/**
	 * 运行期异常
	 */
	public static void test(){
		int i = 1 / 0;		
	}
	
	public static String[][] users = { { "张三", "123" }, { "李四", "456" } };
	
	public static boolean login(String name, String pwd) throws LoginException{
		
		if(name==null || name.isEmpty()){
			throw new LoginException("用户名不能为空！");
		}
		if(pwd==null || pwd.isEmpty()){
			throw new LoginException("密码不能为空！");
		}
		for (int i = 0; i < users.length; i++) {
			if(users[i][0].equals(name) && users[i][1].equals(pwd)){
				return true;
			}
		}
		
		throw new LoginException("用户名或密码错误！");
		// 代码不可达
		// return false;
	}
}

class LoginException extends Exception {
	/**
	 * 生产序列化版本编号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 重写所有父类的构造方法
	 */
	public LoginException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LoginException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LoginException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}





