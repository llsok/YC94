package com.yc.api.d1114;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
		// int ==> Integer
		
		// Integer.MAX_VALUE; 类型基本信息
		
		// Integer.valueOf() 基本数据类型和字符串转成包装器类型
		//Integer a = 1;
		//a.intValue(); // 类型转换
		//类型.parseXXX() ; ==> 转成基本类型
		
		Integer i = new Integer(100);
		Integer a = 100; // 字面值赋值给对象的，就是自动装箱
		int b = a;		 // 反之就是自动拆箱  JDK 1.5 才有
		
		/**
		 *  字符流特点是什么?...
		 *  字节流特点是什么?...   ==》 图片 视频
		 *  
		 *  字符 和 字节（8位 byte 256）
		 *  unicode 数字 映射 字符   65==》A
		 *  
		 *  字符集编码： GBK， UTF-8
		 *  
		 */
		
		
		/**
		 * 	 String中常用的方法有哪些(比较...	2.61
		 		Object类中的toString、equals..
		 		
		 	equals 内容比较
		 	截取： substring( 开始索引， 结束的索引 )  ==》 oracle substr（开始索引， 长度）
		 	查找： indexOf（"a"）;
		 		indexOf("a", beginIndex)
		 		lastIndexOf();
		 		
		 		toLowerCase
		 		toUpperCase
		 		
		 		replaceAll("正则表达式")
		 		
		 */
		
		// a  == b ==》 内存地址 ==》 同一个对象 
		
		/**
		 * 反射： 
		 */
		
		Object o = new Person("周通",27,'男');
		test(o);
		
		
		
	}
	
	private static void test(Object o) throws Exception {
		/**
		 * 反射是动态（运行时）获取类，属性，方法，构造方的java提供技术
		 * 
		 * 获取类对象
		 * 1. 对象.getClass();
		 * 2. 类名.class
		 * 3. 静态方法 Class.forName()
		 */
		
		Class<?> cls = o.getClass();
		
		// Field 属性
		Field f = cls.getDeclaredField("name");  // Declared 获取当前类定义的属性
		cls.getField("age");		   // 没有Declared 获取共有属性 public
		
		System.out.println(f.getName());
		System.out.println(f.getType());
		
		// Method 方法
		cls.getMethod("getName");
		Method m = cls.getMethod("setName", String.class);
		
		m.invoke(o, "武松");  
		System.out.println("==="+  ((Person)o).getName()  );
		
		// newInstance 调用无参数构造函数
		// Person newo = (Person) cls.newInstance();
		//newo.setName("李逵");
		// Constructor 构造函数类
		
		Constructor ctt = cls.getConstructor(String.class, int.class, char.class);
		
		Person newo = (Person) ctt.newInstance("李逵",35, '男');
		
		System.out.println("==="+  newo.getName()  );
		
		/**
		 * 什么是序列化/反序列化?如何实现?...
		 * 
		 * 序列化:将对象内容转成字节流的过程
		 * 反序列化:将字节流转成对象内容的过程
		 * 
		 * 实现方式： 给类实现序列化接口 java.io.Serializable
		 * 			它是标志接口
		 * 
		 * 需求， 将一个Person对象保存到文件中
		 * 
		 * 	对象输出流： ObjectOutputStream ==> 处理流
		 * 
		 * 序列化版本编号
		 * 　　1）在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
		 * 	      在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。 

　　			2）当你序列化了一个类实例后，希望更改一个字段或添加一个字段，不设置serialVersionUID，
				所做的任何更改都将导致无法反序化旧有实例，并在反序列化时抛出一个异常。如果你添加了serialVersionUID，
				在反序列旧有实例时，新添加或更改的字段值将设为初始化值（对象为null，基本类型为相应的初始默认值），
				字段被删除将不设置。
		 */
		
		//newo = new SubPerson("林冲",32,'男');
		//((SubPerson)newo).setType("test");
		// 创建文件输出流 ==》 自动创建文件
		Child c = new Child();
		//c.setName("test");
		newo.setChild(c);
		FileOutputStream fos = new FileOutputStream("d:/person.dat");
		
		
		// 创建对象输出流，输出到文件中  包装器设计模式
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(newo);
		
		oos.close();
		
		//  读取 文件中对象
		
		FileInputStream fis = new FileInputStream("d:/person.dat");
		// 创建对象输出流，输出到文件中  包装器设计模式
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person filep = (Person) ois.readObject();
		
		ois.close();
		
		System.out.println(filep);
		
		
		
	}
	
}

class SubPerson extends  Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;

	public SubPerson(String name, int age, char sex) {
		super(name, age, sex);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SubPerson [type=" + type + "]";
	}
	
}

class Child {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Person implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Person(String name, int age, char sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	private String name;
	
	public int age;
	
	public char sex;
	
	private Child child;
	
	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char isSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}




