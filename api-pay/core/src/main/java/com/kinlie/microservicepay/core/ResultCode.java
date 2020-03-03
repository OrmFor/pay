package com.kinlie.microservicepay.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

//@JsonInclude(Include.NON_NULL)
public class ResultCode implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(ResultCode.class);

	private String code;
	private String message;
	private Object data;

	public ResultCode() {
	}

	public ResultCode(String code, String msg) {
		this.code = code;
		this.message = msg;
//		logger.info("返回数据，code:" + code + ",msg:" + msg);
	}

	public ResultCode(String code, String msg, Object data) {
		this.code = code;
		this.message = msg;
		if (data == null) {
			data = new Object();
		}
		this.data = data;
//		logger.info("返回数据，code:" + code + ",msg:" + msg + ",data:" + JSON.toJSONString(data));
	}

	public ResultCode(StatusCode statusCode) {
		this.code = statusCode.getCode();
		this.message = statusCode.getMsg();
//		logger.info("返回数据，code:" + code + ",msg:" + msg);
	}

	public ResultCode(StatusCode statusCode, Object o) {
		this.code = statusCode.getCode();
		this.message = statusCode.getMsg();
		if (o == null) {
			o = new Object();
		}
		this.data = o;
//		logger.info("返回数据，code:" + code + ",msg:" + msg + ",data:" + JSON.toJSONString(data));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
