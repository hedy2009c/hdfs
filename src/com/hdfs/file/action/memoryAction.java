package com.hdfs.file.action;

import com.hdfs.file.service.fileService;
import com.hdfs.user.bean.Users;

public class memoryAction {
	private Users user;
	private int memoryId;
	private int priority;
	private int totalmemory;
	private int memoryused;
	private fileService fileservice;

	public int getMemoryId() {
		return memoryId;
	}

	public void setMemoryId(int memoryId) {
		this.memoryId = memoryId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getTotalmemory() {
		return totalmemory;
	}

	public void setTotalmemory(int totalmemory) {
		this.totalmemory = totalmemory;
	}

	public int getMemoryused() {
		return memoryused;
	}

	public void setMemoryused(int memoryused) {
		this.memoryused = memoryused;
	}

	public fileService getFileservice() {
		return fileservice;
	}

	public void setFileservice(fileService fileservice) {
		this.fileservice = fileservice;
	}

	public boolean insertMemory(int userId, int type) {

		fileservice.insertMemory(userId, type);
		return false;
	}

}
