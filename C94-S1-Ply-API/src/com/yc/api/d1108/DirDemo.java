package com.yc.api.d1108;

import java.io.File;
import java.io.FileFilter;

/* 目录操作 */
public class DirDemo {

	public static void main(String[] args) {

		File dir = new File("d:/java");
		System.out.println(dir.mkdir()); // 创建一个目录

		dir = new File("d:/java/javaee/servlet/jsp");

		System.out.println(dir.mkdirs());// 创建一个多级目录

		dir = new File("d:/");

		for (File f : dir.listFiles()) {
			System.out.println(f.getName());
		}
		
		System.out.println("=======================");
		
		/**
		 * filter 是文件过滤器对象， 在调用的时候创建， 该对象负责对目录的文件进行筛选
		 */

		/**
		 * 创建匿名类
		 */
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// 判断当前是文件
				boolean ret = pathname.isFile();
				return ret;
			}
		};

		for (File f : dir.listFiles(filter)) {
			System.out.println(f.getName());
		}
		
		/**
		 	扩展练习： window目录下所有的 .exe
		 	String 函数
		*/System.out.println("=========== window目录下所有的 .exe============");
		
		dir = new File("C:/WINDOWS");
		
		filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// 判断当前是文件
				boolean ret = pathname.getName().toLowerCase().endsWith(".exe");
				return ret;
			}
		};

		for (File f : dir.listFiles(filter)) {
			System.out.println(f.getName());
		}
	}

}
