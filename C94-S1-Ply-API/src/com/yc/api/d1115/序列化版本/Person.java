package com.yc.api.d1115.���л��汾;

public class Person implements java.io.Serializable{
	
	/**
	 *	���л��汾��� 
	 */
	private static final long serialVersionUID = 100L;
	private String name;
	private int age;
	private char sex;
	
	/**
	 * 	���õĶ���Ҳ�������л���������IO����ʱ�����쳣
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
