package com.yc.api.d1112.cmd;

import java.io.File;

public class CdCommand implements ICommand {
	
	// 保存在命令对象中的主类对象
	private CmdMain cmdMain;
	
	// 在构造方法中传入主类对象
	public CdCommand(CmdMain cmdMain) {
		// 构建命令时出入主类对象
		this.cmdMain = cmdMain;
	}

	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("cd");
	}

	/**
	 * cd 子目录
	 * cd ..
	 * 
	 * 扩展  cd
	 */
	
	@Override
	public void execute(String cmd) {
		
		// 单独输入 cd 只显示当前路径就退出
		if(cmd.length()==2){
			System.out.println(cmdMain.getCurPath());
			System.out.println();
			return;
		}
		
		// 获取 cd空格 后面的字符串（有待优化）
		String subDir = cmd.substring(3);
		// 获取主类对象中的当前路径
		String curPath = cmdMain.getCurPath();
		
		if("..".equals(subDir)){
			/**
			 * 回到上级目录
			 */
			// 获取最后一个 \
			int index = curPath.lastIndexOf("\\");
			// -1 表示没找到
			if(index > -1) {
				// 截取父目录路径
				curPath = curPath.substring(0, index);
				// 更新新的当前路径
				cmdMain.setCurPath(curPath);
			}
		} else {
			/**
			 * 进入子目录
			 */
			// 构建新的路径
			String newDir = curPath + "\\" + subDir;
			// 判断目录是否存在
			File dir = new File(newDir);
			if(dir.exists()){
				// 路径追加子目录
				cmdMain.setCurPath(newDir);
			} else {
				System.out.println("系统找不到指定的路径。");
			}
		}
	}
}
