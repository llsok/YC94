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
		File dir;
		if ("dir".equalsIgnoreCase(cmd)) {
			dir = new File(cmdMain.getCurPath());
		} else {
			String[] items = cmd.split(" ");
			String path = items[1];
			if(path.contains(":")) {
				// 绝对路径查询  例如 c:\a
				dir = new File(path);
			} else {
				// 相对路径， 在当前目录中查询指定目录
				dir = new File(cmdMain.getCurPath() + "\\" + path) ;
			}
		}
		
		// 判断目录中是否由文件
		if(dir.listFiles()!=null) {
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
