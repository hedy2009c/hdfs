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
	
	/**
	 * 根据用户的Id来查询用户的信息。
	 * 返回符合要求的具有完整信息的用户
	 * @param user
	 * @return
	 */
	public Users find(Users user);
	
	public Users findByName(Users user);
	
	public Users getcheckuserById(Users user);

}
