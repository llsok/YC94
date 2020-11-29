package com.yc.swt.biz;

/**
 * 自定义异常，不用写一行代码
 * @author Administrator
 *
 */
public class BizException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * 重写所有的构造函数
	 * source ==》
	 */
	
	public BizException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
