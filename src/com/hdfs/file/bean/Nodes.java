package com.hdfs.file.bean;

public class Nodes {
	private int id;
	private String file_id;
	private String parentid;
	private String hrefAddress;
	private String file_name;
	private String user_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String fileId) {
		file_id = fileId;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getHrefAddress() {
		return hrefAddress;
	}
	public void setHrefAddress(String hrefAddress) {
		this.hrefAddress = hrefAddress;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String fileName) {
		file_name = fileName;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return user_id;
	}

	

}
