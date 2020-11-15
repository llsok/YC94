package com.yc.api.d1115.序列化版本;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *	三个类的关系： 
 *	Child 是 Person 的属性 		==> Person has Child
 *	SubPerson 是 Person 的子类	==> SubPerson is Person
 *
 *	序列化版本编号
 *	Person、SubPerson、Child 三个类的初始序列化版本编号都是 100 
 *
 */

public class Test {

	public static void main(String[] args) throws Exception {
		
		/**
		 * 	测试内容：	序列化版本编号对类存储的影响
		 * 	初始化：		保存 Person
		 */
		//savePerson();
		/**
		 *	测试1 ： 修改Person的序列化版本编号后，再读取 Person
		 *	测试2 ： 还原Person的序列化版本编号，修改Child的序列化版本编号后，再读取 Person 
		 */
		// loadPerson();  
		 
		 
		/**
		 *	测试前，		必须屏蔽前面的代码！
		 *	测试内容：	序列化版本编号对继承的类存储的影响
		 *	初始化：		保存 SubPerson
		 */
		// saveSubPerson();  // 
		
		/**
		 *	测试3 ： 修改Person的序列化版本编号后，再读取 SubPerson
		 *	测试4 ： 还原Person的序列化版本编号，修改Child的序列化版本编号后，再读取 SubPerson 
		 */
		// loadSubPerson();
		 
		 
		 /**
		 *	测试前，		必须屏蔽前面的代码！
		 *	测试内容：	不写序列化版本编号类存储的影响
		 *	初始化：		先删除 Person 的序列化版本编号， 保存 Person
		 */
		 savePerson();
		 
		 /**
		 *	测试5 ： 去掉 Person.name 的 private ， 再加载 Person
		 *	测试6：  还原 Person.name 的 private ，去掉 Child.name 的 private ，再加载 Person
		 */
		 loadPerson();
		
		/**
		 * 	总结：
		 1）在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
		 	在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。 

　　		 2）当你序列化了一个类实例后，希望更改一个字段或添加一个字段，不设置serialVersionUID，
			所做的任何更改都将导致无法反序化旧有实例，并在反序列化时抛出一个异常。如果你添加了serialVersionUID，
			在反序列旧有实例时，新添加或更改的字段值将设为初始化值（对象为null，基本类型为相应的初始默认值），
			字段被删除将不设置。
		 */
	}
	
	/**
	 * 	测试1 ： 保存并读取 Person
	 */
	static void savePerson() throws IOException, ClassNotFoundException {
		// 创建并保存对象 Person
		Person p = new Person("高俅", 35, '男');
		p.setChild(new Child("高衙内"));
		save(p, "e:/person.dat");
		// 读取对象 Person
		p = (Person) load(p, "e:/person.dat");
		System.out.println(p);
	}
	
	/**
	 * 	测试2 ： 修改Person的序列化版本编号后，再读取 Person
	 */
	static void loadPerson() throws IOException, ClassNotFoundException {
		Person p = null;
		// 读取对象 Person
		p = (Person) load(p, "e:/person.dat");
		System.out.println(p);
	}
	
	static void saveSubPerson() throws IOException, ClassNotFoundException {
		// 创建并保存对象 Person
		SubPerson p = new SubPerson("高俅", 35, '男',"朝廷命官");
		p.setChild(new Child("高衙内"));
		save(p, "e:/subperson.dat");
		// 读取对象 Person
		p = (SubPerson) load(p, "e:/subperson.dat");
		System.out.println(p);
	}
	
	static void loadSubPerson() throws IOException, ClassNotFoundException {
		SubPerson p = null;
		// 读取对象 SubPerson
		p = (SubPerson) load(p, "e:/subperson.dat");
		System.out.println(p);
	}

	/**
	 * 	保存对象
	 */
	static void save(Object o, String filepath) throws IOException {
		// 定义文件输出流
		FileOutputStream fos = new FileOutputStream(filepath);
		// 创建对象输出流，输出到文件中  包装器设计模式
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 写入对象
		oos.writeObject(o);
		// 关闭流
		oos.close();
	}

	/**
	 * 	读取对象
	 */
	static Object load(Object o, String filepath) throws IOException, ClassNotFoundException {
		// 定义文件输入流
		FileInputStream fis = new FileInputStream(filepath);
		// 创建对象输出流，输出到文件中 => 包装器设计模式
		ObjectInputStream ois = new ObjectInputStream(fis);
		// 读取对象
		o = ois.readObject();
		// 关闭流
		ois.close();
		// 将读取的对象返回出去
		return o;
	}

}
