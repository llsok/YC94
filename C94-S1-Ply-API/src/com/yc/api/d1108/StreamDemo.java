package com.yc.api.d1108;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 流的命名规则 1. 方向： 输入， 输出 Input Output Reader Writer
 * 
 * 2. 格式： 字节， 字符 Stream Writer，Reader
 * 
 * 流的类名格式： 资源 + 方向 + 格式
 * 
 * 读入 图片文件 FileInputStream 保存 图片文件 FileOutputStream 读入txt文档 FileReader
 *
 */

public class StreamDemo {

	// IOException 是 FileNotFoundException 的父类
	public static void main(String[] args) {

		File file = new File("d:/readme.txt");

		System.out.println("=====================字符流=============================");

		FileReader fr = null;

		try {
			fr = new FileReader(file);
			// 定义读取数据缓冲区 ==》 勺子
			char[] buffer = new char[100];
			int count; // 记录缓冲区内数据的大小
			while ((count = fr.read(buffer)) > -1) {
				String string = new String(buffer, 0, count);
				System.out.print(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// DataInputStream 与 DataOutputStream 的使用
		
		System.out.println("\r\n=====================字节流=============================");

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			byte[] buffer1 = new byte[100];
			int count;
			while ((count = fis.read(buffer1)) > -1) {
				String string = new String(buffer1, 0, count);
				System.out.print(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
