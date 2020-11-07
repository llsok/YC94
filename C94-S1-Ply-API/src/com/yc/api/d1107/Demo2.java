package com.yc.api.d1107;

public class Demo2 {
	// alt + / ==> 代码提示
	public static void main(String[] args) throws ClassNotFoundException {
		
		Person p1 = new Person("武松", 28);
		Person p2 = new Person("武松", 28);
		
		System.out.println(p1 == p2);  // false
		
		System.out.println(p1.equals(p2));  // true  ctrl + 鼠标点击
		
		System.out.println(p1.getClass());
		
		System.out.println(Person.class);
		
		System.out.println(Class.forName("com.yc.api.d1107.Person"));
		
		/*获取类对象的方法
		 * 对象.getClass();
		 * 类.class
		 * Class.forName("类的完全限定名：包名+类名")
		 * 
		 * */
	}

}


class Person {
	
	String name;
	int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}