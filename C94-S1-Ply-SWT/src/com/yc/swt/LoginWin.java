package com.yc.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginWin {

	protected Shell shell;
	private Text text;
	private Label label;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginWin window = new LoginWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setText("登录窗口");
		shell.setSize(420, 280);
		
		text = new Text(shell, SWT.BORDER);
		
		// 设置大小和位置
		text.setBounds(138, 34, 210, 27);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setBounds(57, 38, 75, 23);
		lblNewLabel.setText("用户名：");
		
		label = new Label(shell, SWT.NONE);
		label.setText("密码：");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(57, 100, 75, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(138, 96, 210, 27);
		
		Button button = new Button(shell, SWT.NONE);
		
		/**
		 * 事件处理代码： 添加一个Selection监听器
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 只要用户点击该按钮， SWT 就会执行当前方法，该方法就叫事件监听方法
				
				// 获取用户输入的账号和密码
				String name = text.getText(); // 获取用户的输入值
				String pwd = text_1.getText();
				MessageBox mb = new MessageBox(shell);
				mb.setText("系统提示");
				if("yc".equals(name) && "123".equals(pwd)){
					// 弹窗消息框
					mb.setMessage("登录成功！");
					mb.open();
					shell.dispose();
					new MainWin().open();
				} else {
					mb.setMessage("用户名或密码错误！");
					mb.open();
				}
				/**
				 * 请实现退出按钮功能
				 * shell.dispose(); 关闭窗口的代码
				 */
				
			}
		});
		
		button.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		button.setBounds(73, 176, 88, 34);
		button.setText("登录");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button_1.setText("退出");
		button_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		button_1.setBounds(235, 176, 88, 34);

	}
}
