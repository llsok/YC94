package com.yc.api.d1112.cmd;

import java.util.*;
import java.io.*;

// 主类

/**
 * 	1. 实现 dir 后面带指定路径，例如： dir window 或 dir d:\a
	2. 实现 type 命令，显示问及那内容， 例如： type a.txt
	3. 实现 del 命令， 删除指定文件
	4. 实现 copy 命令， 复制文件
 */

public class CmdMain {

	// 命令集合： 存放所有可以执行的命令对象
	private Set<ICommand> commandSet = new HashSet<>();

	private Scanner scanner = new Scanner(System.in);

	// 当前路径
	private String curPath = System.getProperty("user.home");
	
	// 设置当前路径
	public void setCurPath(String curPath) {
		this.curPath = curPath;
	}
	
	public String getCurPath() {
		return curPath;
	}

	public void start() {
		a: while (true) {
			System.out.print(curPath + ">");
			// 读取屏幕输入的命令
			String cmd = scanner.nextLine();
			if(cmd.trim().isEmpty()){
				continue;
			}
			// 依次调用命令集合的判断方法，判断是否可以执行该命令
			for (ICommand command : commandSet) {
				if (command.canExecute(cmd)) {
					// 如果允许执行则执行
					command.execute(cmd);
					continue a;
				}
			}
			// 如果循环完成没有对象可以执行，则提示用户错误命令
			System.out.println(cmd + " 不是内部或外部命令，也不是可运行的程序");
		}
	}

	/**
	 * 重新定义的初始化方法
	 * ICommand...commands 可变参数数组 ==》 等效于 ICommand[] 可变参数数组只能放在方法的最后一个参数位置上
	 */
	public void init(ICommand... commands) {
		// 将传入的命令对象添加到 set 集合中
		for (ICommand cmd : commands) {
			commandSet.add(cmd);
		}
	}

	public static void main(String[] args) {
		// 单独创建cm对象
		CmdMain cm = new CmdMain();
		// 初始化cm对象
		cm.init(new CdCommand(cm), // 加入 cd 命令
				new DirCommand(cm), // 加入 dir 命令
				new TypeCommand(cm),// 加入 type 命令
				new CopyCommand(cm)); // 加入 copy 命令
		cm.start();
	}

}
