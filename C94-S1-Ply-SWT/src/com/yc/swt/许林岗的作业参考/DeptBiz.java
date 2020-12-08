package com.yc.swt.许林岗的作业参考;


import java.sql.SQLException;

import com.yc.swt.biz.BizException;
import com.yc.swt.dao.DeptDao;

public class DeptBiz {

	private DeptDao dao = new DeptDao();
	/**
	 * 删除部门
	 * @throws BizEception 
	 * 
	 * 
	 * 
	 */
	public void remove(String deptno) throws BizException {
		//
		//
		try {
			//判断部门是否有员工
			if(dao.countEmp(deptno) > 0) {
				throw new BizException("该部门下有员工，不能删除");
			}
			//删除部门
			dao.delete(deptno);
		} catch (SQLException e) {
			//异常转型==> 异常链
			throw new BizException("删除部门失败，请联系管理员",e);
		}
		
	}
	public void create(int deptno,String dname,String loc) throws BizException {
		/*
		 * 扩展：部门名称不能为空，部门名称不能重复（同名验证），部门编号不能为零，不能重复
		 */
		try {
			if(dao.countDeptname(dname)!=0) {
				throw new BizException("部门名称重复！");
			}
			if(dname==null || dname.trim().isEmpty() ) {
				throw new BizException("部门名称不能为空！");
			}
			if(deptno==0) {
				throw new BizException("部门编号不能为零！");
			}
			if(dao.countDeptno(deptno)!=0) {
				throw new BizException("部门编号重复！");
			}
			dao.insert(deptno, dname, loc);
		} catch (SQLException e) {
			throw new BizException("新增部门失败，请联系管理员",e);
		}
	}
	public void modify(int deptno,String dname,String loc) throws BizException{
		/*
		 * 扩展：部门名称不能为空，部门名称不能重复（同名验证）
		 */
		try {
			if(dao.countDeptname(dname)!=0) {
				throw new BizException("部门名称重复！");
			}
			if(dname==null || dname.trim().isEmpty() ) {
				throw new BizException("部门名称不能为空！");
			}
			dao.update(deptno,dname,loc);
		} catch (SQLException e) {
			throw new BizException("修改部门失败，请联系管理员",e);
		}
	}
}
