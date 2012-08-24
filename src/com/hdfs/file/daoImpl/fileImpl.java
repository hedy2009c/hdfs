package com.hdfs.file.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hdfs.comm.util.BaseDao;
import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.file.dao.fileDao;

public class fileImpl extends BaseDao implements fileDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<HdfsFile> listFile(long currentId, long userId) {

		String hql = "from HdfsFile where userId=:userId and parentid=:currentId";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("currentId", currentId);
		query.setParameter("userId", userId);
		List<HdfsFile> fileList = query.list();
		System.out.println("第一个list"+fileList.toString());
		if (fileList.size()==0) {
			hql = "select parentid from HdfsFile where fileId=:currentId";
			query = session.createQuery(hql);
			query.setParameter("currentId", currentId);
			String pId = query.uniqueResult().toString();
			
			hql = "from HdfsFile where userId=:userId and parentid=:currentId";
			session = this.getSession();
			Query query1 = session.createQuery(hql);
			query1.setParameter("currentId", Long.parseLong(pId));
			query1.setParameter("userId", userId);
			System.out.println(Long.parseLong(pId)+"   " +userId);
			fileList = query1.list();
			System.out.println("第二个list:"+fileList.toString()+fileList.size()+"-----------------------------------");
			
		}
		return fileList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<HdfsFile> listAllFile() {

		String hql = "from HdfsFile";
		//users order by users.id desc";
		return this.getHibernateTemplate().find(hql);
		
	}
	@Override
	public String getFileUrl(long fileId) {
		// TODO Auto-generated method stub
		String hql = "select fileUrl from HdfsFile where fileId=:fileId";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("fileId", fileId);
		String fileUrl = (String) query.uniqueResult();
		System.out.println(fileUrl);
		return fileUrl;
	}

	@Override
	public boolean insertFile(HdfsFile file) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		try {
			Transaction trans = session.beginTransaction();
			session.save(file);
			trans.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean insertMemory(HdfsMemory memory) {
		Session session = this.getSession();
		try {
			Transaction trans = session.beginTransaction();
			session.save(memory);
			trans.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public HdfsMemory saveMemory(HdfsMemory memory) {
		/*
		 * Session session=this.getSession(); StringHql=
		 * "select count(*) from HdfsMemory memory where memory.memoryId=:memoryId"
		 * ; Query query=session.createQuery(Hql); query.setString("memoryId",
		 * memory.getMemoryId().toString()); long
		 * result=(Long)query.uniqueResult(); try { if(result==0) {
		 */
		this.getHibernateTemplate().save(memory);
		return memory;

		/*
		 * } else { System.out.println("mem error~"); }
		 * 
		 * 
		 * } catch(Exception e) { e.printStackTrace(); } finally {
		 * session.close(); }
		 */
	}

	@Override
	public boolean deleteFile(long fileId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		try {
			Transaction trans = session.beginTransaction();
			HdfsFile file = new HdfsFile();
			file.setFileId(fileId);
			session.delete(findFile(fileId));

			trans.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public HdfsMemory findMemory(int memoryId) {
		HdfsMemory memory = new HdfsMemory();
		Session session = this.getSession();
		memory = (HdfsMemory) session.get(HdfsMemory.class, memoryId);
		return memory;
	}
	
	
	
	@Override
	public boolean deleteMemory(int memoryId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		try {
			Transaction trans = session.beginTransaction();
			HdfsMemory memory = new HdfsMemory();
			memory.setMemoryId(memoryId);
			session.delete(findMemory(memoryId));

			trans.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteFiles(String startpath) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		try {
			String hql = "delete HdfsFile where fileUrl like :startpath";
			Query query = session.createQuery(hql);
			query.setParameter("startpath", startpath + "%");
			int ref = query.executeUpdate();
			session.beginTransaction().commit();
			session.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
		
	}
	
	@Override
	public int deleteFilesmem(String startpath){
		Session session = this.getSession();
		try {
			String hql = "select sum(size) from HdfsFile where fileUrl like :startpath";
			Query query = session.createQuery(hql);
			query.setParameter("startpath", startpath + "%");
			int ref = Integer.parseInt(query.uniqueResult().toString());
			
			System.out.println("111111111111111111111111111"+ref);

			return ref;
		} catch (Exception ex) {
			return 0;
		}
	}
	/*public Users findUser(int userId)
	{
		 Session session=this.getSession();
		  Criteria crit=session.createCriteria(Users.class);
		  crit.add(Restrictions.eq("userId", userId));
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
	}*/
	@Override
	public HdfsFile findFile(long fileId) {
		// TODO Auto-generated method stub
		/*Session session = this.getSession();
		HdfsFile file = (HdfsFile) session.get(HdfsFile.class, fileId);
		return file;*/
		 Session session=this.getSession();
		  Criteria crit=session.createCriteria(HdfsFile.class);
		  crit.add(Restrictions.eq("fileId", fileId));
		  HdfsFile file=null;
		  try
		  {
		   file= (HdfsFile)crit.uniqueResult();
		  }
		 catch(Exception e)
		 {
		  return null;
		 }
		 finally
		 {
			 session.close();
		 }
		  
		  return file;
	
	}
	
/*
	public long findUserIdByFileId(int currentId) {
		Session session = this.getSession();
		Criteria crit = session.createCriteria(HdfsFile.class);
		crit.add(Restrictions.eq("fileId", currentId));
		HdfsFile file = null;
		try {
			file = (HdfsFile) crit.uniqueResult();
		} catch (Exception e) {
			return 0;
		} finally {
			session.close();
		}

		return file.getUserId();
	}*/

	@Override
	public HdfsMemory getMemory(int memoryId) {
		Session session = this.getSession();
		HdfsMemory memory = (HdfsMemory) session.get(HdfsMemory.class, memoryId);
		return memory;
	}
	
	@Override
	public HdfsMemory find(long memoryId)
	{
		  Session session=this.getSession();
		  Criteria crit=session.createCriteria(HdfsMemory.class);
		  crit.add(Restrictions.eq("memoryId", memoryId));
		  HdfsMemory memory=null;
		  try
		  {
		   memory= (HdfsMemory)crit.uniqueResult();
		  }
		 catch(Exception e)
		 {
		  return null;
		 }
		 finally
		 {
			 session.close();
		 }
		  
		  return memory;
	}
	
	@Override
	public void updateMemory(HdfsMemory memory) {
		this.getHibernateTemplate().update(memory);
	}

	@Override
	public boolean setSafeLevel(short level,String filePath){
		boolean result = false;
		
		return result;
	}

	@Override
	public void updateEncrypt_DataKey(HdfsFile hdfsFile,
			byte[] encryptedDataSecretKey) {
		
		Session session=this.getSession();
		Transaction trans=session.beginTransaction();
		String hql="update HdfsFile hdfsFile set hdfsFile.encryptDataKey=:encryptDataKey where hdfsFile.fileId=:fileId";
		Query queryupdate=session.createQuery(hql);
		queryupdate.setBinary("encryptDataKey", encryptedDataSecretKey);
		queryupdate.setLong("fileId", hdfsFile.getFileId());
		int ret=queryupdate.executeUpdate();
		trans.commit();
		
	}

	@Override
	public long getFileId(String fileUrl) {
		String hql = "select fileId from HdfsFile where fileUrl=:fileUrl";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("fileUrl", fileUrl);
		System.out.println(fileUrl);
		long fileId =  (Long) query.uniqueResult();
		System.out.println(fileId);
		return fileId;
	}
	
	
}
