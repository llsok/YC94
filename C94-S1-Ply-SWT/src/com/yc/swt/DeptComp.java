package com.yc.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.yc.swt.biz.BizException;
import com.yc.swt.biz.DeptBiz;
import com.yc.swt.dao.DeptDao;
import com.yc.swt.util.SWTHelper;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DeptComp extends Composite {
	private Table table;
	private Text text;
	private DeptBiz biz = new DeptBiz();
	private Text text_1; //新增地址的条件输入框
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DeptComp(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("部门编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("部门名称");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("地址");

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		RowLayout rl_composite = new RowLayout(SWT.HORIZONTAL);
		rl_composite.center = true;
		composite.setLayout(rl_composite);

		Label label = new Label(composite, SWT.NONE);
		label.setText("部门名称：");

		text = new Text(composite, SWT.BORDER);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("地址：");
		
		text_1 = new Text(composite, SWT.BORDER);

		Button btnNewButton = new Button(composite, SWT.NONE);
		/**
		 * 查询按钮事件
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				query();
			}
		});
		btnNewButton.setText("查询");

		Button button = new Button(composite, SWT.NONE);

		/**
		 * 新增按钮的点击事件
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DeptEditWin dew = new DeptEditWin(getShell(), SWT.NONE); // NONE
																			// 没有样式，即使用默认样式
				Object result = dew.open();
				// 返回 true 表示新增部门成功！
				if (result.equals(true)) {
					query();
				}
			}
		});
		button.setText("新建");

		Button button_1 = new Button(composite, SWT.NONE);
		/**
		 * 删除按钮的点击事件
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取当前表格中选中的行
				if( table.getSelectionCount() == 0){
					SWTHelper.msg(getShell(), "请选择要删除部门！");
					return;
				}
				
				// 提问用户，是否删除
				if(SWTHelper.confirm(getShell(), "请确认是否删除该部门？") == false){
					return;
				}
				
				// 执行删除 ==> 调用业务类方法
				// DeptBiz biz = new DeptBiz();
				TableItem tableItem = table.getSelection()[0];
				String deptno = tableItem.getText(0);
				try {
					biz.remove(deptno);
					SWTHelper.msg(getShell(), "删除部门成功！");
				} catch (BizException e1) {
					e1.printStackTrace();
					// getMessage() ==> throw new BizException("删除部门失败，请联系管理员", e);
					SWTHelper.msg(getShell(), e1.getMessage());
				}

				// 刷新表格
				query();
			}
		});
		button_1.setText("删除");
		
		Button button_2 = new Button(composite, SWT.NONE);
		/**
		 * 修改按钮的点击事件
		 */
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( table.getSelectionCount() == 0){
					SWTHelper.msg(getShell(), "请选择要修改部门！");
					return;
				}
				TableItem tableItem = table.getSelection()[0];
				DeptEditWin dew = new DeptEditWin(getShell(), SWT.NONE, tableItem); // NONE
				Object result = dew.open();
				// 返回 true 表示修改部门成功！
				if (result.equals(true)) {
					query();
				}
			}
		});
		button_2.setText("修改");

		/**
		 * 在构造函数最后执行查询
		 */
		query();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * 查询数据库， 将结果展现表格中
	 */
	public void query() {
		DeptDao dao = new DeptDao();
		String dname = text.getText();
		String loc = text_1.getText();
		List<Map<String, Object>> list = dao.select(dname,loc);
		// 清空表格的数据
		// table.clearAll();
		// 移除表格的数据
		table.removeAll();
		for (Map<String, Object> row : list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { "" + row.get("DEPTNO"), "" + row.get("DNAME"), "" + row.get("LOC") });
		}
	}
}
