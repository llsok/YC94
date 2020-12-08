package com.yc.swt;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.swt.biz.BizException;
import com.yc.swt.biz.SalChangeBiz;
import com.yc.swt.util.SWTHelper;

public class AddSalChangeWin extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddSalChangeWin(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(380, 344);
		shell.setText(getText());
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(67, 72, 54, 12);
		label.setText("员工编号：");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(146, 69, 161, 18);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("原工资：");
		label_1.setBounds(67, 115, 54, 12);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(146, 112, 161, 18);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("新工资：");
		label_2.setBounds(67, 159, 54, 12);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(146, 156, 161, 18);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("调薪原因：");
		label_3.setBounds(67, 202, 54, 12);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(146, 199, 161, 18);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SalChangeBiz biz = new SalChangeBiz();
				String empno = text.getText();
				double oldSal = Double.valueOf(text_1.getText());
				double newSal = Double.valueOf(text_2.getText());
				String cause = text_3.getText();
				try {
					biz.create(empno, oldSal, newSal, cause);
					SWTHelper.msg(shell, "调薪单提交成功！");
				} catch (BizException e1) {
					SWTHelper.msg(shell, e1.getMessage());
				}
				
			}
		});
		button.setBounds(60, 259, 72, 22);
		button.setText("确定");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("取消");
		button_1.setBounds(235, 259, 72, 22);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.BOLD));
		lblNewLabel.setBounds(146, 20, 161, 43);
		lblNewLabel.setText("员工调薪");

	}
}
