package com.yc.swt.biz;
import java.sql.SQLException;
import com.yc.swt.dao.DeptDao;
public class DeptBiz {
	private DeptDao dao = new DeptDao();
	/**
	 * 删除部门 
	 *  remove 删除 ==》 delete
	 *  create 新增  ==》 insert
	 *  modify 修改   ==》  update
	 * @param deptno
	 * @throws BizException 
	 */
	public void remove(String deptno) throws BizException{
		// 判断该部门编号是否正确
		// 判断部门是否存在
		
		try{
			// 判断部门是否有员工
			if(dao.countEmp(deptno) > 0){
				throw new BizException("该部门下有员工，不能删除");
			}
			// 删除部门
			dao.delete(deptno);
		} catch (SQLException e){
			// 异常转型 ==> 异常链  e.getCause()
			throw new BizException("删除部门失败，请联系管理员", e);
		}
	}
	
	public void create(int deptno, String dname, String loc) throws BizException{
		/**
		 *  扩展： 部门名称不能为空， 部门名称不能重复（同名验证）， 部门编号不能为0， 不能重复
		 */
		try {
			dao.insert(deptno, dname, loc);
		} catch (SQLException e) {
			throw new BizException("新增部门失败，请联系管理员", e);
		}
	}
	
	public void modify(int deptno, String dname, String loc) throws BizException{
		/**
		 *  扩展： 部门名称不能为空， 部门名称不能重复（同名验证）
		 */
		try {
			dao.update(deptno, dname, loc);
		} catch (SQLException e) {
			throw new BizException("修改部门失败，请联系管理员", e);
		}
	}

}
