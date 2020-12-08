package com.yc.swt.biz;

import java.sql.SQLException;
import java.util.Map;

import com.yc.swt.dao.SalChangeDao;

public class SalChangeBiz {
	
	private SalChangeDao dao = new SalChangeDao();
	
	public void create(String empno, double oldSal, double newSal, String couse)
			throws BizException{
		/**
		 * 判断员工编号，原有工资，新的工资，原因都不能为空
		 * 判断员工是否存在
		 * 
		 *  请完成代码
		 */
		try {
			dao.insert(empno, oldSal, newSal, couse);
		} catch (SQLException e) {
			throw new BizException("调薪失败，请联系管理员",e);
		}
	}
	
	
	/**
	 * 同意调薪
	 * salChangeId : 调薪记录 的 id
	 * verify  ： 审核人的员工编号
	 * @throws BizException 
	 */
	public void agree (String salChangeId, String verify) throws BizException{
		// salChangeId 调薪单的 id
		// verify 审核人
		
		try{
			// 更新 调薪审核人字段 ==》 sal_change.verify
			dao.updateVerify(salChangeId, verify);
			// 根据 调薪单id 获取到调薪单的记录
			Map<String,Object> salChangeMap = dao.selectById(salChangeId);
			double newSal = Double.parseDouble(""+ salChangeMap.get("NEW_SAL"));
			// 使用调薪表的new_sal更新被调薪人的工资 ==》 sal_change.new_sal ===> emp.sal
			
			// empDao.updateSal(); 请完成代码：创建 EmpDao
			/**
			 * 请完成业务代码
			 */
			
			/**
			 * 窗体请自行完成
			 */
			
		}catch(SQLException e){
			throw new BizException("调薪失败，请联系管理员",e);
		}
		
	}

}
