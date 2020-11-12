package com.yc.api.d1112.cmd;

import java.io.File;

public class CdCommand implements ICommand {
	
	// ��������������е��������
	private CmdMain cmdMain;
	
	// �ڹ��췽���д����������
	public CdCommand(CmdMain cmdMain) {
		// ��������ʱ�����������
		this.cmdMain = cmdMain;
	}

	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("cd");
	}

	/**
	 * cd ��Ŀ¼
	 * cd ..
	 * 
	 * ��չ  cd
	 */
	
	@Override
	public void execute(String cmd) {
		
		// �������� cd ֻ��ʾ��ǰ·�����˳�
		if(cmd.length()==2){
			System.out.println(cmdMain.getCurPath());
			System.out.println();
			return;
		}
		
		// ��ȡ cd�ո� ������ַ������д��Ż���
		String subDir = cmd.substring(3);
		// ��ȡ��������еĵ�ǰ·��
		String curPath = cmdMain.getCurPath();
		
		if("..".equals(subDir)){
			/**
			 * �ص��ϼ�Ŀ¼
			 */
			// ��ȡ���һ�� \
			int index = curPath.lastIndexOf("\\");
			// -1 ��ʾû�ҵ�
			if(index > -1) {
				// ��ȡ��Ŀ¼·��
				curPath = curPath.substring(0, index);
				// �����µĵ�ǰ·��
				cmdMain.setCurPath(curPath);
			}
		} else {
			/**
			 * ������Ŀ¼
			 */
			// �����µ�·��
			String newDir = curPath + "\\" + subDir;
			// �ж�Ŀ¼�Ƿ����
			File dir = new File(newDir);
			if(dir.exists()){
				// ·��׷����Ŀ¼
				cmdMain.setCurPath(newDir);
			} else {
				System.out.println("ϵͳ�Ҳ���ָ����·����");
			}
		}
	}
}
