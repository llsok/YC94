package com.yc.api.d1112.cmd;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirCommand implements ICommand {

	// 保存在命令对象中的主类对象
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
		if ("dir".equalsIgnoreCase(cmd)) {
			File dir = new File(cmdMain.getCurPath());
			for (File f : dir.listFiles()) {
				Date d = new Date(f.lastModified());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = sdf.format(d);
				String type = f.isDirectory() ? "<DIR>" : "";
				String size = f.isDirectory() ? "" : ("" + f.length());
				String name = f.getName();
				System.out.printf("%s %5s %10s %s\r\n", date, type, size, name);// 格式化输出
			}
		}
	}

}
