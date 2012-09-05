package com.hdfs.user.dao;

import java.util.List;

import com.hdfs.user.bean.Users;

public interface userDao {

	/**
	 * 更新users表
	 * 
	 * @param user
	 */
	public void updateUser(Users user);

	/**
	 * 保存users记录 若所要保存的users记录的username项在原users表出现过，即该用户名可用，就允许保存该记录 用在用户的注册过程
	 * 
	 * @param users
	 * @return
	 */
	public Users saveUsers(Users users);

	/**
	 * 删除users表
	 * 
	 * @param users
	 */
	public void removeUsers(Users users);

	/**
	 * 获取匹配用户名和密码的用户记录
	 * 
	 * @param user
	 * @return
	 */
	public Users findUsersById(Users user);

	/**
	 * 获取用户表里面的所有用户
	 * 
	 * @return
	 */
	public List<Users> findAllUsers();

	/**
	 * 
	 * 根据用户的Id来查询用户的信息。 返回符合要求的具有完整信息的用户
	 * 
	 * @param user
	 * @return
	 */
	public Users find(Users user);

	/**
	 * 根据用户名来查询用户的信息。 放回符合要求的具有完整信息的用户
	 * 
	 * @param user
	 * @return
	 */
	public Users findByName(Users user);

	/**
	 * 返回具有userid的用户信息
	 * 
	 * @param userid
	 * @return
	 */
	public Users findid(int userid);

	/**
	 * 通过id返回检验码
	 * 
	 * @param user
	 * @return
	 */
	public Users getcheckuserById(Users user);

	/**
	 * 判断用户的pulic_key属性是否为空
	 * 
	 * @param user
	 * @return
	 */
	public boolean isPublicKeyEmpty(Users user);

	public void updateUserPublicKey(Users user, String publicKeyUrl);

}
