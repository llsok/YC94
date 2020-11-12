package com.yc.api.d1112;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * DataInputStream--->数据输入流
 * 
 * DataOutputStream--->数据输出流
 * 
 * 允许程序按着机器无关的风格读取java原始数据
 * 换一种说法就是：
 * 能够将数据带有数据类型的存放和读取
 * 不必关心这个数值应当是多少字节
 * 
 * @author me
 */
public class DateStreamDemo {

	public static void main(String[] args) {
		
		File file = new File("d:/mytxt.txt");  //获得文件对象   
		
		//DataOutputStream写入操作
		try {
			if(file==null) {//没有该文件就创造新文件
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			//构造方法：DataOutputStream(OutStream out)
			DataOutputStream dos = new DataOutputStream(fos);
			//DataOutputStream方法：write+Java基本类型数据+(Java基本类型数据  对象名);
			dos.writeInt(10);     //写入int数据
			dos.writeDouble(1314.1234); //写入double数据
			dos.writeFloat(3.1415926f);
			dos.writeBoolean(true);
			dos.writeChars(" Yes you so handsome. ");
			//关闭操作
			dos.flush();  //清除缓存，保存进文件里去
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//DataInputStream读取操作
		try {
			FileInputStream fis = new FileInputStream(file);
			//构造方法：FileInputStream(inputStream in)
			DataInputStream dis = new DataInputStream(fis);
			//DataInputStream方法：read+Java基本类型数据+(Java基本类型数据  对象名);
			//注意：读取顺序必须与写入顺序相同
			System.out.println(dis.readInt());  //读取int数据
			System.out.println(dis.readDouble());
			System.out.println(dis.readFloat());
			System.out.println(dis.readBoolean());
			//读只有readChar()读一个字符
			char c = '\0';  //'\0'表示空字符
			while((c=dis.readChar())!='\0') {
				System.out.print(c);//注意这没换行
			}
			//关闭操作
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/*
 * 
 * 运行出一个错误
 * java.io.EOFException
 * 输入过程中意外的到达文件尾或流尾的信号？？？？？？？？？
 * */
