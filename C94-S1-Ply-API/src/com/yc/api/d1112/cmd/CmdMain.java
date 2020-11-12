package com.yc.api.d1112.cmd;

import java.util.*;
import java.io.*;

// ����

public class CmdMain {

	// ����ϣ� ������п���ִ�е��������
	private Set<ICommand> commandSet = new HashSet<>();

	private Scanner scanner = new Scanner(System.in);

	// ��ǰ·��
	private String curPath = System.getProperty("user.home");
	
	// ���õ�ǰ·��
	public void setCurPath(String curPath) {
		this.curPath = curPath;
	}
	
	public String getCurPath() {
		return curPath;
	}

	public void start() {
		a: while (true) {
			System.out.print(curPath + ">");
			// ��ȡ��Ļ���������
			String cmd = scanner.nextLine();
			if(cmd.trim().isEmpty()){
				continue;
			}
			// ���ε�������ϵ��жϷ������ж��Ƿ����ִ�и�����
			for (ICommand command : commandSet) {
				if (command.canExecute(cmd)) {
					// �������ִ����ִ��
					command.execute(cmd);
					continue a;
				}
			}
			// ���ѭ�����û�ж������ִ�У�����ʾ�û���������
			System.out.println(cmd + " �����ڲ����ⲿ���Ҳ���ǿ����еĳ���");
		}
	}

	/**
	 * ���¶���ĳ�ʼ������
	 * ICommand...commands �ɱ�������� ==�� ��Ч�� ICommand[] �ɱ��������ֻ�ܷ��ڷ��������һ������λ����
	 */
	public void init(ICommand... commands) {
		// ����������������ӵ� set ������
		for (ICommand cmd : commands) {
			commandSet.add(cmd);
		}
	}

	public static void main(String[] args) {
		// ��������cm����
		CmdMain cm = new CmdMain();
		// ��ʼ��cm����
		cm.init(new CdCommand(cm), // ���� cd ����
				new DirCommand(cm)); // ���� dir ����
		cm.start();
	}

}
