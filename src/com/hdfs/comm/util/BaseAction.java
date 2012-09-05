package com.hdfs.comm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	public static final String SUCCESS = "success";// ִ�гɹ�
	public static final String FAIL = "fail";// ִ��ʧ��
	public String result = "success";// �ɸı�

	/**
	 * @author
	 * @date
	 * @description ���request
	 * @param null
	 * @return HttpServletRequest
	 * @exception null
	 */
	public final HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * @author
	 * @date 2008-8-15
	 * @description ���response
	 * @param null
	 * @return HttpServletResponse
	 * @exception null
	 */
	public final HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}
