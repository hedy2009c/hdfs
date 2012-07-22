package com.hdfs.user.service;

import java.util.List;

import com.hdfs.user.bean.Users;

public interface userService {
	/**
	 * �����û���Ϣ
	 * @param user
	 */
	public void updateUser(Users user);
	
	public List<Users> findAll();
	
	public Users save(Users users);
	
	public void delete(Users users);
	
	public Users findById(Users user);
	
	public Users find(Users user);
	
	public Users findByName(Users user);

}
