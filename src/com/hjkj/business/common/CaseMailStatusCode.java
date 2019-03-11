package com.hjkj.business.common;

public class CaseMailStatusCode {

	/**
	 * 病案新申请待审核
	 */
	public static final int CASE_TO_AUDIT = 1;
	
	/**
	 * 病案已审核待导出
	 */
	public static final int CASE_TO_EXPORT = 2;
	
	/**
	 * 病案已导出待打印
	 */
	public static final int CASE_TO_PRINT = 3;
	
	/**
	 * 病案已打印待发货
	 */
	public static final int CASE_TO_RECEIVE = 4;
	
	/**
	 * 病案已发货待收货
	 */
	public static final int CASE_TO_DELIVER = 5;
	/**
	 * 病案已收货
	 */
	public static final int CASE_TO_FINISH = 6;
	/**
	 * 病案审核拒绝
	 */
	public static final int CASE_TO_REFUSE = 7;

	
	
	
}

