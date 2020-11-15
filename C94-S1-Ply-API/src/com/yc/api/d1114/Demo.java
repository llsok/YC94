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
		
		// Integer.MAX_VALUE; ���ͻ�����Ϣ
		
		// Integer.valueOf() �����������ͺ��ַ���ת�ɰ�װ������
		//Integer a = 1;
		//a.intValue(); // ����ת��
		//����.parseXXX() ; ==> ת�ɻ�������
		
		Integer i = new Integer(100);
		Integer a = 100; // ����ֵ��ֵ������ģ������Զ�װ��
		int b = a;		 // ��֮�����Զ�����  JDK 1.5 ����
		
		/**
		 *  �ַ����ص���ʲô?...
		 *  �ֽ����ص���ʲô?...   ==�� ͼƬ ��Ƶ
		 *  
		 *  �ַ� �� �ֽڣ�8λ byte 256��
		 *  unicode ���� ӳ�� �ַ�   65==��A
		 *  
		 *  �ַ������룺 GBK�� UTF-8
		 *  
		 */
		
		
		/**
		 * 	 String�г��õķ�������Щ(�Ƚ�...	2.61
		 		Object���е�toString��equals..
		 		
		 	equals ���ݱȽ�
		 	��ȡ�� substring( ��ʼ������ ���������� )  ==�� oracle substr����ʼ������ ���ȣ�
		 	���ң� indexOf��"a"��;
		 		indexOf("a", beginIndex)
		 		lastIndexOf();
		 		
		 		toLowerCase
		 		toUpperCase
		 		
		 		replaceAll("������ʽ")
		 		
		 */
		
		// a  == b ==�� �ڴ��ַ ==�� ͬһ������ 
		
		/**
		 * ���䣺 
		 */
		
		Object o = new Person("��ͨ",27,'��');
		test(o);
		
		
		
	}
	
	private static void test(Object o) throws Exception {
		/**
		 * �����Ƕ�̬������ʱ����ȡ�࣬���ԣ����������췽��java�ṩ����
		 * 
		 * ��ȡ�����
		 * 1. ����.getClass();
		 * 2. ����.class
		 * 3. ��̬���� Class.forName()
		 */
		
		Class<?> cls = o.getClass();
		
		// Field ����
		Field f = cls.getDeclaredField("name");  // Declared ��ȡ��ǰ�ඨ�������
		cls.getField("age");		   // û��Declared ��ȡ�������� public
		
		System.out.println(f.getName());
		System.out.println(f.getType());
		
		// Method ����
		cls.getMethod("getName");
		Method m = cls.getMethod("setName", String.class);
		
		m.invoke(o, "����");  
		System.out.println("==="+  ((Person)o).getName()  );
		
		// newInstance �����޲������캯��
		// Person newo = (Person) cls.newInstance();
		//newo.setName("����");
		// Constructor ���캯����
		
		Constructor ctt = cls.getConstructor(String.class, int.class, char.class);
		
		Person newo = (Person) ctt.newInstance("����",35, '��');
		
		System.out.println("==="+  newo.getName()  );
		
		/**
		 * ʲô�����л�/�����л�?���ʵ��?...
		 * 
		 * ���л�:����������ת���ֽ����Ĺ���
		 * �����л�:���ֽ���ת�ɶ������ݵĹ���
		 * 
		 * ʵ�ַ�ʽ�� ����ʵ�����л��ӿ� java.io.Serializable
		 * 			���Ǳ�־�ӿ�
		 * 
		 * ���� ��һ��Person���󱣴浽�ļ���
		 * 
		 * 	����������� ObjectOutputStream ==> ������
		 * 
		 * ���л��汾���
		 * ����1����ĳЩ���ϣ�ϣ����Ĳ�ͬ�汾�����л����ݣ������Ҫȷ����Ĳ�ͬ�汾������ͬ��serialVersionUID��
		 * 	      ��ĳЩ���ϣ���ϣ����Ĳ�ͬ�汾�����л����ݣ������Ҫȷ����Ĳ�ͬ�汾���в�ͬ��serialVersionUID�� 

����			2���������л���һ����ʵ����ϣ������һ���ֶλ����һ���ֶΣ�������serialVersionUID��
				�������κθ��Ķ��������޷����򻯾���ʵ�������ڷ����л�ʱ�׳�һ���쳣������������serialVersionUID��
				�ڷ����о���ʵ��ʱ������ӻ���ĵ��ֶ�ֵ����Ϊ��ʼ��ֵ������Ϊnull����������Ϊ��Ӧ�ĳ�ʼĬ��ֵ����
				�ֶα�ɾ���������á�
		 */
		
		//newo = new SubPerson("�ֳ�",32,'��');
		//((SubPerson)newo).setType("test");
		// �����ļ������ ==�� �Զ������ļ�
		Child c = new Child();
		//c.setName("test");
		newo.setChild(c);
		FileOutputStream fos = new FileOutputStream("d:/person.dat");
		
		
		// ���������������������ļ���  ��װ�����ģʽ
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(newo);
		
		oos.close();
		
		//  ��ȡ �ļ��ж���
		
		FileInputStream fis = new FileInputStream("d:/person.dat");
		// ���������������������ļ���  ��װ�����ģʽ
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




