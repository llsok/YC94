package com.yc.api.d1108;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFileDemo {

	public static void main(String[] args) {

		FileWriter fw = null;

		try {
			// 默认情况下， 写文件是覆盖操作,
			// true 表示追加内容
			fw = new FileWriter("d:/readme.txt", true);
			fw.write("\r\n\r\n\r\nJava IO 操作！！！！");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
