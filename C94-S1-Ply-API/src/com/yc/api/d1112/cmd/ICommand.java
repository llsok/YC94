package com.yc.api.d1112.cmd;

/**
 * �������Ϊ��
 * 	1. �жϵ�ǰ����������Ƿ����ִ�и�����
 * 	2. ִ�и�����
 */
public interface ICommand {
	// 1. �жϵ�ǰ����������Ƿ����ִ�и�����
	boolean canExecute(String cmd);
	
	// 2. ִ�и�����
	void execute(String cmd);
}
