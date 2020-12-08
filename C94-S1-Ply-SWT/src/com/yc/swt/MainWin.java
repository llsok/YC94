package com.yc.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWin {

	protected Shell shell;
	TabFolder tabFolder;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
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
		shell.setSize(694, 475);
		shell.setText("主窗口");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("系统管理");

		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 寻判断是否已经开启该标签页
				for(TabItem ti : tabFolder.getItems()){
					if(ti.getText().equals("部门管理")){
						// 如果已经开启，则直接选择
						tabFolder.setSelection(ti);
						return;
					}
				}
				TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
				tbtmNewItem.setText("部门管理");
				DeptComp dc = new DeptComp(tabFolder, SWT.NONE);
				// 将部门管理控件添加到当前标签页
				tbtmNewItem.setControl(dc);
				/**
				 * 自动切换当前打开控件
				 */
				tabFolder.setSelection(tbtmNewItem);
			}
		});
		menuItem_1.setText("部门管理");

		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.setText("员工管理");
		
		MenuItem menuItem_8 = new MenuItem(menu_1, SWT.NONE);
		/**
		 * 点击“员工调薪”菜单执行的方法
		 */
		menuItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new AddSalChangeWin(shell,SWT.NONE).open();
			}
		});
		menuItem_8.setText("员工调薪");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem menuItem_3 = new MenuItem(menu_1, SWT.NONE);
		menuItem_3.setText("工资体系");

		MenuItem menuItem_4 = new MenuItem(menu, SWT.CASCADE);
		menuItem_4.setText("报表系统");

		Menu menu_2 = new Menu(menuItem_4);
		menuItem_4.setMenu(menu_2);

		MenuItem menuItem_5 = new MenuItem(menu_2, SWT.NONE);
		menuItem_5.setText("人力成本统计表");

		MenuItem menuItem_6 = new MenuItem(menu_2, SWT.NONE);
		menuItem_6.setText("历史人力成本");

		MenuItem menuItem_7 = new MenuItem(menu, SWT.NONE);
		menuItem_7.setText("退出");

		// 标签页容器控件
		tabFolder = new TabFolder(shell, SWT.NONE);

		// 子标签页
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("首页");

		Label lblNewLabel = new Label(tabFolder, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage(MainWin.class, "/com/yc/swt/06.jpg"));
		tbtmNewItem.setControl(lblNewLabel);

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("员工管理");

	}
}
