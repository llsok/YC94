package com.yc.api.d1112.cmd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyCommand implements ICommand{
	// ��������������е��������
	private CmdMain cmdMain;

	public CopyCommand(CmdMain cmdMain) {
		this.cmdMain = cmdMain;
	}
	
	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("copy");
	}

	@Override
	public void execute(String cmd) {
		String[] items = cmd.split(" ");
		String path1 = items[1];
		String path2 = items[2];
		
		try {
			// �ļ�������
			FileInputStream fis = new FileInputStream(path1);
			// �ļ������
			FileOutputStream fos = new FileOutputStream(path2);

			// ʵ�ֿ���
			byte[] buffer = new byte[1024];
			int count;
			while ((count = fis.read(buffer)) >-1) {
				fos.write(buffer, 0, count);
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
