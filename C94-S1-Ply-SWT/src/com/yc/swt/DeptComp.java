package com.yc.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.yc.swt.dao.DeptDao;

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
				DeptEditWin dew = new DeptEditWin(getShell(), SWT.NONE); // NONE 没有样式，即使用默认样式
				Object result = dew.open();
				// 返回 true 表示新增部门成功！
				if(result.equals(true)){
					query();
				}
			}
		});
		button.setText("新建");

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
		List<Map<String, Object>> list = dao.selectAll();
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
