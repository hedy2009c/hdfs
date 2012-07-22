package com.hdfs.user.serviceImpl;

import java.util.List;


import com.hdfs.user.bean.Users;
import com.hdfs.user.dao.userDao;
import com.hdfs.user.service.userService;

public class userServiceImpl implements userService{
	
	public userDao userdao;


	public void updateUser(Users user) {
		this.userdao.updateUser(user);
		
	}

	public userDao getUserdao() {
		return userdao;
	}

	public void setUserdao(userDao userdao) {
		this.userdao = userdao;
	}
	
	public Users find(Users user)
	{
		return this.userdao.find(user);
	}

	public Users findByName(Users user)
	{
		return this.userdao.findByName(user);
	}


	public void delete(Users users) {

		this.userdao.removeUsers(users);
	}
	
	public List<Users> findAll() {
		return this.userdao.findAllUsers();
	}

	public Users findById(Users user) {
		return this.userdao.findUsersById(user);
	}

	public Users save(Users users) {

		return this.userdao.saveUsers(users);
	}



}
