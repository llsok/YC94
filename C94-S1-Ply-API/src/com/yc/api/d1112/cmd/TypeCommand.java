package com.yc.api.d1112.cmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TypeCommand implements ICommand{
	// ��������������е��������
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
			// �ļ�������
			FileReader fr = new FileReader(filepath);
			// ������������ ���԰��ж�ȡ
			BufferedReader br = new BufferedReader(fr);
			
			// line ��ʾ�����һ������
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
