package com.yc.swt.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SWTHelper {
	
	/**
	 * 提示信息的消息框
	 * @param shell
	 * @param msg
	 */
	public static void msg(Shell shell, String msg){
		MessageBox mb = new MessageBox(shell,SWT.NONE);
		mb.setText("系统提示");
		mb.setMessage(msg);
		mb.open();
	}
	
	/**
	 * 提问消息框,如果按yes 则返回true， 否则false
	 * 样式的设置，实际是控制一些开关
	 * @param shell
	 * @param msg
	 */
	public static boolean confirm(Shell shell, String msg){
		MessageBox mb = new MessageBox(shell,SWT.YES | SWT.NO);
		mb.setText("系统提示");
		mb.setMessage(msg);
		int ret = mb.open();
		return ret == SWT.YES;
	}

}
