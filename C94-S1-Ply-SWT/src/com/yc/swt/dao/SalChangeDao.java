package com.yc.swt.dao;

import java.sql.SQLException;
import java.util.Map;

public class SalChangeDao {
	
	/**
	 * 创建调薪记录
	 * @param empno
	 * @param oldSal
	 * @param newSal
	 * @param couse
	 */
	public void insert(String empno, double oldSal, double newSal, String couse)
			throws SQLException {
		/**
		 * id 使用序列生成
		 * create_time 使用 sysdate
		 * verify 初始状态是 null
		 * 
		 * 请完成代码
		 */
	}
	
	/**
	 * 更新审核字段，填写审核人的 empno
	 * @param verify
	 */
	public void updateVerify(String id, String verify) throws SQLException {
		/**
		 * update 只更新 verify 字段
		 * 请完成代码
		 */
	}

	/**
	 * 根据 调薪单id 获取到调薪单的记录
	 * @param salChangeId
	 * @return
	 */
	public Map<String, Object> selectById(String salChangeId) {
		/**
		 * 请完成代码
		 */
		return null;
	}
	
	
	
	

}
