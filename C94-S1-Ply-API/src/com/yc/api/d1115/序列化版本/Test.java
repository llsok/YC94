package com.yc.api.d1115.���л��汾;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *	������Ĺ�ϵ�� 
 *	Child �� Person ������ 		==> Person has Child
 *	SubPerson �� Person ������	==> SubPerson is Person
 *
 *	���л��汾���
 *	Person��SubPerson��Child ������ĳ�ʼ���л��汾��Ŷ��� 100 
 *
 */

public class Test {

	public static void main(String[] args) throws Exception {
		
		/**
		 * 	�������ݣ�	���л��汾��Ŷ���洢��Ӱ��
		 * 	��ʼ����		���� Person
		 */
		//savePerson();
		/**
		 *	����1 �� �޸�Person�����л��汾��ź��ٶ�ȡ Person
		 *	����2 �� ��ԭPerson�����л��汾��ţ��޸�Child�����л��汾��ź��ٶ�ȡ Person 
		 */
		// loadPerson();  
		 
		 
		/**
		 *	����ǰ��		��������ǰ��Ĵ��룡
		 *	�������ݣ�	���л��汾��ŶԼ̳е���洢��Ӱ��
		 *	��ʼ����		���� SubPerson
		 */
		// saveSubPerson();  // 
		
		/**
		 *	����3 �� �޸�Person�����л��汾��ź��ٶ�ȡ SubPerson
		 *	����4 �� ��ԭPerson�����л��汾��ţ��޸�Child�����л��汾��ź��ٶ�ȡ SubPerson 
		 */
		// loadSubPerson();
		 
		 
		 /**
		 *	����ǰ��		��������ǰ��Ĵ��룡
		 *	�������ݣ�	��д���л��汾�����洢��Ӱ��
		 *	��ʼ����		��ɾ�� Person �����л��汾��ţ� ���� Person
		 */
		 savePerson();
		 
		 /**
		 *	����5 �� ȥ�� Person.name �� private �� �ټ��� Person
		 *	����6��  ��ԭ Person.name �� private ��ȥ�� Child.name �� private ���ټ��� Person
		 */
		 loadPerson();
		
		/**
		 * 	�ܽ᣺
		 1����ĳЩ���ϣ�ϣ����Ĳ�ͬ�汾�����л����ݣ������Ҫȷ����Ĳ�ͬ�汾������ͬ��serialVersionUID��
		 	��ĳЩ���ϣ���ϣ����Ĳ�ͬ�汾�����л����ݣ������Ҫȷ����Ĳ�ͬ�汾���в�ͬ��serialVersionUID�� 

����		 2���������л���һ����ʵ����ϣ������һ���ֶλ����һ���ֶΣ�������serialVersionUID��
			�������κθ��Ķ��������޷����򻯾���ʵ�������ڷ����л�ʱ�׳�һ���쳣������������serialVersionUID��
			�ڷ����о���ʵ��ʱ������ӻ���ĵ��ֶ�ֵ����Ϊ��ʼ��ֵ������Ϊnull����������Ϊ��Ӧ�ĳ�ʼĬ��ֵ����
			�ֶα�ɾ���������á�
		 */
	}
	
	/**
	 * 	����1 �� ���沢��ȡ Person
	 */
	static void savePerson() throws IOException, ClassNotFoundException {
		// ������������� Person
		Person p = new Person("��ٴ", 35, '��');
		p.setChild(new Child("������"));
		save(p, "e:/person.dat");
		// ��ȡ���� Person
		p = (Person) load(p, "e:/person.dat");
		System.out.println(p);
	}
	
	/**
	 * 	����2 �� �޸�Person�����л��汾��ź��ٶ�ȡ Person
	 */
	static void loadPerson() throws IOException, ClassNotFoundException {
		Person p = null;
		// ��ȡ���� Person
		p = (Person) load(p, "e:/person.dat");
		System.out.println(p);
	}
	
	static void saveSubPerson() throws IOException, ClassNotFoundException {
		// ������������� Person
		SubPerson p = new SubPerson("��ٴ", 35, '��',"��͢����");
		p.setChild(new Child("������"));
		save(p, "e:/subperson.dat");
		// ��ȡ���� Person
		p = (SubPerson) load(p, "e:/subperson.dat");
		System.out.println(p);
	}
	
	static void loadSubPerson() throws IOException, ClassNotFoundException {
		SubPerson p = null;
		// ��ȡ���� SubPerson
		p = (SubPerson) load(p, "e:/subperson.dat");
		System.out.println(p);
	}

	/**
	 * 	�������
	 */
	static void save(Object o, String filepath) throws IOException {
		// �����ļ������
		FileOutputStream fos = new FileOutputStream(filepath);
		// ���������������������ļ���  ��װ�����ģʽ
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// д�����
		oos.writeObject(o);
		// �ر���
		oos.close();
	}

	/**
	 * 	��ȡ����
	 */
	static Object load(Object o, String filepath) throws IOException, ClassNotFoundException {
		// �����ļ�������
		FileInputStream fis = new FileInputStream(filepath);
		// ���������������������ļ��� => ��װ�����ģʽ
		ObjectInputStream ois = new ObjectInputStream(fis);
		// ��ȡ����
		o = ois.readObject();
		// �ر���
		ois.close();
		// ����ȡ�Ķ��󷵻س�ȥ
		return o;
	}

}
