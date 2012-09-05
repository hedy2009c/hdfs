package com.hdfs.user.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hdfs.comm.util.BaseDao;
import com.hdfs.user.bean.Users;
import com.hdfs.user.dao.userDao;

public class userImpl extends BaseDao implements userDao {

	@Override
	public void updateUser(Users user) {
		this.getHibernateTemplate().update(user);
		System.out.println("ok");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Users> findAllUsers() {
		String hql = "from Users";
		// users order by users.id desc";
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据用户的Id来查询用户的信息。 返回符合要求的具有完整信息的用户
	 */
	@Override
	public Users find(Users user) {
		Session session = this.getSession();
		Criteria crit = session.createCriteria(Users.class);
		crit.add(Restrictions.eq("userId", user.getUserId()));
		Users newuser = null;
		try {
			newuser = (Users) crit.uniqueResult();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

		return newuser;
	}

	@Override
	public Users findid(int userid) {
		Session session = this.getSession();
		Criteria crit = session.createCriteria(Users.class);
		crit.add(Restrictions.eq("userId", userid));
		Users newuser = null;
		try {
			newuser = (Users) crit.uniqueResult();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

		return newuser;
	}

	@Override
	public Users findByName(Users user) {
		Session session = this.getSession();
		Criteria crit = session.createCriteria(Users.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		Users newuser = null;
		try {
			newuser = (Users) crit.uniqueResult();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

		return newuser;
	}

	@Override
	public Users findUsersById(Users user) {

		Session session = this.getSession();
		Criteria crit = session.createCriteria(Users.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		crit.add(Restrictions.eq("password", user.getPassword()));
		// crit.add(Restrictions.eq("active", 1));//是否激活
		Users newuser = null;
		try {
			newuser = (Users) crit.uniqueResult();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

		return newuser;

	}

	@Override
	public void removeUsers(Users users) {

		this.getHibernateTemplate().delete(users);
	}

	@Override
	public Users saveUsers(Users user) {
		Session session = this.getSession();
		String Hql = "select count(*) from Users user where user.username=:username";
		Query query = session.createQuery(Hql);
		query.setString("username", user.getUsername());
		// 查询是否存在username的用户
		long result = (Long) query.uniqueResult();
		try {
			if (result == 0) {

				// 利用模板对用户表的记录进行添加
				this.getHibernateTemplate().save(user);
				return user;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isPublicKeyEmpty(Users user) {

		System.out.println("11");
		Session session = this.getSession();
		String Hql = "select publicKey from Users user where user.userId=:userId";
		Query query = session.createQuery(Hql);
		query.setInteger("userId", user.getUserId());

		System.out.println("11");

		List<String> list = query.list();
		System.out.println("11");
		try {
			for (String str : list) {
				if (str != null) {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("11");
		return true;
	}

	@Override
	public void updateUserPublicKey(Users user, String publicKeyUrl) {
		Session session = this.getSession();
		Transaction trans = session.beginTransaction();
		String hql = "update Users user set user.publicKey=:publicKey where user.userId=:userId";
		Query queryupdate = session.createQuery(hql);
		queryupdate.setString("publicKey", publicKeyUrl);
		queryupdate.setInteger("userId", user.getUserId());
		int ret = queryupdate.executeUpdate();
		trans.commit();

	}

	@Override
	public Users getcheckuserById(Users user) {

		Session session = this.getSession();
		Criteria crit = session.createCriteria(Users.class);
		user = this.findByName(user);
		String s = "";
		s = user.getCheckuser() + "";
		// crit.add(Restrictions.eq("username", user.getUsername()));
		crit.add(Restrictions.eq("s", 1));// 是否激活
		// System.out.println("username in the function = "+user.getUsername());
		System.out.println("checknumber in the function = "
				+ user.getCheckuser());
		Users newuser = null;
		try {
			// newuser= (Users)crit.uniqueResult();
			if (user.getCheckuser() == 1) {
				newuser = user;
			} else {
				newuser = null;
			}

		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
		System.out.println("newuser" + newuser);
		return newuser;

	}
}
