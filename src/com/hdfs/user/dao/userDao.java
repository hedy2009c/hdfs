package com.hdfs.user.dao;

import java.util.List;

import com.hdfs.user.bean.Users;

public interface userDao {

	/**
	 * �����û���Ϣ
	 * @param user
	 */
	public void updateUser(Users user);
	
	public Users saveUsers(Users users);
	
	public void removeUsers(Users users);
	
	public Users findUsersById(Users user);
	
	public List<Users> findAllUsers();

	public Users find(Users user);
	
	public Users findByName(Users user);
	
	public Users findid(int userid);

}
