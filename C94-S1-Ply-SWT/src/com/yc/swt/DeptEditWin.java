package com.yc.swt;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.yc.swt.dao.DeptDao;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Dialog 对话框（实现模态窗口操作）
 */
public class DeptEditWin extends Dialog {

	/**
	 * result 表示关闭当前对话框，返回给主窗口的返回值
	 */
	protected Object result = false;
	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DeptEditWin(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(354, 235);
		shell.setText("部门信息编辑窗口");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(29, 47, 74, 12);
		label.setText("编号：");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(109, 44, 201, 21);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("部门名称：");
		label_1.setBounds(29, 80, 74, 12);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(109, 77, 201, 18);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("地址：");
		label_2.setBounds(29, 114, 74, 12);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(109, 111, 201, 18);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DeptEditWin.this.shell.close();
			}
		});
		button.setBounds(238, 154, 72, 22);
		button.setText("取消");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DeptDao dao = new DeptDao();
				int deptno = Integer.parseInt(spinner.getText());
				String dname = text.getText();
				String loc = text_1.getText();
				String msg = null;
				try {
					dao.insert(deptno, dname, loc);
					msg = "部门添加成功！";
					MessageBox mb = new MessageBox(shell);
					mb.setText("系统提示");
					mb.setMessage(msg);
					mb.open();
					// 设置对话框的返回结果为 true
					DeptEditWin.this.result = true;
					DeptEditWin.this.shell.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					msg = "部门添加失败！";
					MessageBox mb = new MessageBox(shell);
					mb.setText("系统提示");
					mb.setMessage(msg);
					mb.open();
					
				}
			
			}
		});
		button_1.setBounds(142, 154, 72, 22);
		button_1.setText("保存");

	}
}
