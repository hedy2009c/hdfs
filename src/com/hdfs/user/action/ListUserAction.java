package com.hdfs.user.action;

import java.util.ArrayList;
import java.util.List;

import com.hdfs.user.bean.Users;
import com.hdfs.user.service.userService;
import com.opensymphony.xwork2.ActionSupport;

public class ListUserAction extends ActionSupport {
	private String entity;
	private String fields;
	private int rows;
	private int currentPage;
	private List records = new ArrayList();
	public userService userservice;

	public userService getUserservice() {
		return userservice;
	}

	public void setUserservice(userService userservice) {
		this.userservice = userservice;
	}

	@Override
	public String execute() throws Exception {
		// Session session=HibernateSessionFactory.getSession();
		// Query query=session.createQuery("from "+entity);
		// query.setFirstResult((currentPage-1)*rows);
		// query.setMaxResults(rows);
		List<Users> list = userservice.findAll();
		int first = (currentPage - 1) * rows;
		int last = first + rows - 1;
		int i;
		if (first > list.size()) {
			currentPage--;
			first = (currentPage - 1) * rows;
		}
		for (i = first; i <= last; i++) {
			// if(list.get(i)==null)
			// break;
			if (i >= list.size())
				break;
			records.add(list.get(i));
		}
		/*
		 * for(Object obj:list) { //Users user=(Users)obj;
		 * //System.out.println(user
		 * .getUserId()+user.getPassWord()+user.getPhone()); records.add(obj); }
		 */
		// session.close();
		return SUCCESS;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentpage) {
		this.currentPage = currentpage;
	}

	public List<List<String>> getRecords() {
		return records;
	}

	public String[] getfieldList() {
		return fields.split(",");
	}

}
