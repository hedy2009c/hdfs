package com.hdfs.user.serviceImpl;

import java.util.List;

import com.hdfs.user.bean.Users;
import com.hdfs.user.dao.userDao;
import com.hdfs.user.service.userService;

public class userServiceImpl implements userService{
	
	public userDao userdao;


	@Override
	public void updateUser(Users user) {
		this.userdao.updateUser(user);
		
	}

	public userDao getUserdao() {
		return userdao;
	}

	public void setUserdao(userDao userdao) {
		this.userdao = userdao;
	}
	
	@Override
	public Users find(Users user)
	{
		return this.userdao.find(user);
	}

	@Override
	public Users findByName(Users user)
	{
		return this.userdao.findByName(user);
	}


	@Override
	public void delete(Users users) {

		this.userdao.removeUsers(users);
	}
	
	@Override
	public List<Users> findAll() {
		return this.userdao.findAllUsers();
	}

	@Override
	public Users findById(Users user) {
		return this.userdao.findUsersById(user);
	}

	@Override
	public Users save(Users users) {

		return this.userdao.saveUsers(users);
	}

	@Override
	public Users getcheckuserById(Users user) {
		return this.userdao.getcheckuserById(user);
	}


}
