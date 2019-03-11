/**
 * @(#) JsonWrapper.java Created on 2010-5-17
 * 
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.hjkj.business.util;

import com.hjkj.business.common.WebConstants;

/**
 * The class <code>JsonWrapper</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public class JsonWrapper {

	/**
	 * 创建JSON响应消息
	 * 
	 * @param status
	 *            响应状�?�??
	 * @param reason
	 *            响应状�?信息
	 * @return 返回JSON响应消息
	 */
	public static String wrapRpcResp(String status, String reason) {
		StringBuilder sbResp = new StringBuilder();
		// sbResp.append("var ")
		// .append(WebConstants.RPC_JSON_RESP_VAR)
		// .append(" = {")
		sbResp.append("{")
				.append(wrapVariable(WebConstants.RPC_RESP_STATUS_VAR, status))
				.append(", ")
				.append(wrapVariable(WebConstants.RPC_RESP_REASON_VAR, reason))
				.append("};");
		return sbResp.toString();
	}

	/**
	 * 创建JSON属�?的名�??值对
	 * 
	 * @param varName
	 *            JSON属�?�??
	 * @param varValue
	 *            JSON属�?的�?
	 * @return 返回JSON属�?的名�??值对
	 */
	private static String wrapVariable(String varName, String varValue) {
		StringBuilder sbResp = new StringBuilder();
		sbResp.append("'").append(varName).append("':'").append(varValue)
				.append("'");
		return sbResp.toString();
	}
}
