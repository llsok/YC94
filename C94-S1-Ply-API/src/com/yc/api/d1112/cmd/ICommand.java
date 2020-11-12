package com.yc.api.d1112.cmd;

/**
 * 命令的行为：
 * 	1. 判断当前的命令对象是否可以执行该命令
 * 	2. 执行该命令
 */
public interface ICommand {
	// 1. 判断当前的命令对象是否可以执行该命令
	boolean canExecute(String cmd);
	
	// 2. 执行该命令
	void execute(String cmd);
}
