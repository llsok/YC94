package com.yc.api.d1108;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileDemo {
	
	public static void main(String[] args) throws IOException {
		// ctrl + 1
		File file = new File("readme.txt"); // 绝对路径 d:/xx.text   相对路径 xx.text
		
		System.out.println(file.getAbsolutePath());  // 绝对路径
		System.out.println(file.length());  // 文件大小
		System.out.println(file.getParent());  // 父目录
		System.out.println(file.lastModified());  // 最后的修改时间
		System.out.println(file.exists());  // 判断文件是否存在
		
		// Unhandled exception type IOException
		System.out.println(file.createNewFile());  // 创建文件
		
		System.out.println("====================");
		
		System.out.println(file.getAbsolutePath());  // 绝对路径
		System.out.println(file.length());  // 文件大小
		System.out.println(file.getParent());  // 父目录
		System.out.println(new Date(file.lastModified()));  // 最后的修改时间
		System.out.println(file.exists());  // 判断文件是否存在
		
		
		/*
		 * 将该文件删除
		 */
		
	}

}
