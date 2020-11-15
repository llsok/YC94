package com.yc.api.d1115.序列化版本;

public class Person implements java.io.Serializable{
	
	/**
	 *	序列化版本编号 
	 */
	private static final long serialVersionUID = 100L;
	private String name;
	private int age;
	private char sex;
	
	/**
	 * 	引用的对象也必须序列化，否则在IO操作时会有异常
	 */
	private Child child;
	
	public Person(String name, int age, char sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
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

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + ", child=" + child + "]";
	}
	
}
