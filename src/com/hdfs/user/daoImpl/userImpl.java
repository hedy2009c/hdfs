package com.hdfs.user.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hdfs.comm.util.BaseDao;
import com.hdfs.comm.util.pathToId;
import com.hdfs.user.bean.Users;
import com.hdfs.user.dao.userDao;


public class userImpl extends BaseDao implements userDao{
	

	public void updateUser(Users user) {
		this.getHibernateTemplate().update(user);
		System.out.println("ok");
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> findAllUsers() {
		String hql = "from Users";
		//users order by users.id desc";
		return (List<Users>)this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据用户的Id来查询用户的信息。
	 * 返回符合要求的具有完整信息的用户
	 */
	public Users find(Users user)
	{
		 Session session=this.getSession();
		  Criteria crit=session.createCriteria(Users.class);
		  crit.add(Restrictions.eq("userId", user.getUserId()));
		  Users newuser=null;
		  try
		  {
		   newuser= (Users)crit.uniqueResult();
		  }
		 catch(Exception e)
		 {
		  return null;
		 }
		 finally
		 {
			 session.close();
		 }
		  
		  return newuser;
	}
	
	public Users findid(int userid)
	{
		 Session session=this.getSession();
		  Criteria crit=session.createCriteria(Users.class);
		  crit.add(Restrictions.eq("userId", userid));
		  Users newuser=null;
		  try
		  {
		   newuser= (Users)crit.uniqueResult();
		  }
		 catch(Exception e)
		 {
		  return null;
		 }
		 finally
		 {
			 session.close();
		 }
		  
		  return newuser;
	}
	public Users findByName(Users user)
	{
		 Session session=this.getSession();
		  Criteria crit=session.createCriteria(Users.class);
		  crit.add(Restrictions.eq("username", user.getUsername()));
		  Users newuser=null;
		  try
		  {
		   newuser= (Users)crit.uniqueResult();
		  }
		 catch(Exception e)
		 {
		 return null;
		 }
		 finally
		 {
			 session.close();
		 }
		  
		 return newuser;
	}

	public Users findUsersById(Users user) {

		  Session session=this.getSession();
		  Criteria crit=session.createCriteria(Users.class);
		  crit.add(Restrictions.eq("username", user.getUsername()));
		  crit.add(Restrictions.eq("password", user.getPassword()));
//		  crit.add(Restrictions.eq("active", 1));//是否激活
		  Users newuser = null;
		  try
		  {
		   newuser= (Users)crit.uniqueResult();
		  }
		 catch(Exception e)
		 {
		  return null;
		 }
		 finally
		 {
			 session.close();
		 }
		  
		  return newuser;
		  
		  
		}
	public void removeUsers(Users users) {

		this.getHibernateTemplate().delete(users);
	}

	public Users saveUsers(Users user) {
		Session session=this.getSession();
		String Hql="select count(*) from Users user where user.username=:username";
        Query query=session.createQuery(Hql);
        query.setString("username", user.getUsername());
        long result=(Long)query.uniqueResult();
     try
        {
           if(result==0)
           {
        	   
        	  this.getHibernateTemplate().save(user);
        	  return user;
        }
           else
           {
        	 return null;  
           }
        	  
        	   
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        session.close();
        }
      
		return null;
	}




}
