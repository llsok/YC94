package com.yc.api.d1112;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * DataInputStream--->����������
 * 
 * DataOutputStream--->���������
 * 
 * ��������Ż����޹صķ���ȡjavaԭʼ����
 * ��һ��˵�����ǣ�
 * �ܹ������ݴ����������͵Ĵ�źͶ�ȡ
 * ���ع��������ֵӦ���Ƕ����ֽ�
 * 
 * @author me
 */
public class DateStreamDemo {

	public static void main(String[] args) {
		
		File file = new File("d:/mytxt.txt");  //����ļ�����   
		
		//DataOutputStreamд�����
		try {
			if(file==null) {//û�и��ļ��ʹ������ļ�
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			//���췽����DataOutputStream(OutStream out)
			DataOutputStream dos = new DataOutputStream(fos);
			//DataOutputStream������write+Java������������+(Java������������  ������);
			dos.writeInt(10);     //д��int����
			dos.writeDouble(1314.1234); //д��double����
			dos.writeFloat(3.1415926f);
			dos.writeBoolean(true);
			dos.writeChars(" Yes you so handsome. ");
			//�رղ���
			dos.flush();  //������棬������ļ���ȥ
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//DataInputStream��ȡ����
		try {
			FileInputStream fis = new FileInputStream(file);
			//���췽����FileInputStream(inputStream in)
			DataInputStream dis = new DataInputStream(fis);
			//DataInputStream������read+Java������������+(Java������������  ������);
			//ע�⣺��ȡ˳�������д��˳����ͬ
			System.out.println(dis.readInt());  //��ȡint����
			System.out.println(dis.readDouble());
			System.out.println(dis.readFloat());
			System.out.println(dis.readBoolean());
			//��ֻ��readChar()��һ���ַ�
			char c = '\0';  //'\0'��ʾ���ַ�
			while((c=dis.readChar())!='\0') {
				System.out.print(c);//ע����û����
			}
			//�رղ���
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/*
 * 
 * ���г�һ������
 * java.io.EOFException
 * �������������ĵ����ļ�β����β���źţ�����������������
 * */
