package com.yc.api.d1112.cmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TypeCommand implements ICommand{
	// 保存在命令对象中的主类对象
	private CmdMain cmdMain;

	public TypeCommand(CmdMain cmdMain) {
		this.cmdMain = cmdMain;
	}
	
	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("type");
	}

	@Override
	public void execute(String cmd) {
		String[] items = cmd.split(" ");
		String path = items[1];
		
		String filepath = cmdMain.getCurPath() + "\\" + path;
		
		try {
			// 文件读入流
			FileReader fr = new FileReader(filepath);
			// 缓冲输入流， 可以按行读取
			BufferedReader br = new BufferedReader(fr);
			
			// line 表示读入的一行数据
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
