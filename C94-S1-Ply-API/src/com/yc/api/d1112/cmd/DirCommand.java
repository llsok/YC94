package com.yc.api.d1112.cmd;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirCommand implements ICommand {

	// ��������������е��������
	private CmdMain cmdMain;

	public DirCommand(CmdMain cmdMain) {
		super();
		this.cmdMain = cmdMain;
	}

	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("dir");
	}

	@Override
	public void execute(String cmd) {
		File dir;
		if ("dir".equalsIgnoreCase(cmd)) {
			dir = new File(cmdMain.getCurPath());
		} else {
			String[] items = cmd.split(" ");
			String path = items[1];
			if(path.contains(":")) {
				// ����·����ѯ  ���� c:\a
				dir = new File(path);
			} else {
				// ���·���� �ڵ�ǰĿ¼�в�ѯָ��Ŀ¼
				dir = new File(cmdMain.getCurPath() + "\\" + path) ;
			}
		}
		
		// �ж�Ŀ¼���Ƿ����ļ�
		if(dir.listFiles()!=null) {
			for (File f : dir.listFiles()) {
				Date d = new Date(f.lastModified());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = sdf.format(d);
				String type = f.isDirectory() ? "<DIR>" : "";
				String size = f.isDirectory() ? "" : ("" + f.length());
				String name = f.getName();
				System.out.printf("%s %5s %10s %s\r\n", date, type, size, name);// ��ʽ�����
			}
		}
		
	}

}
