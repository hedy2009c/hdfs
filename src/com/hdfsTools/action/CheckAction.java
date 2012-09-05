/**
 * 
 */
package com.hdfsTools.action;

import java.io.IOException;
import java.util.List;

import com.hdfsTools.dao.checkDao;
import com.hdfsTools.impl.checkImpl;



/**
 * @author rongjianping
 * @data 2011-2-10
 *
 */
public class CheckAction extends BaseAction{
	
	public CheckAction(){		
	}	
	/**
	 * 根据文件系统的url来初始化文件系统
	 * @param fsUrl 文件系统的id,fo example "hdfs://localhost:8889"
	 */
	/*public CheckAction(String fsUrl){
		super(fsUrl);
	}*/

	public CheckAction(Configuration conf){
		super(conf);
	}

	

	/**
	 * 查询目录列表和文件的总列表
	 * @param directory example /user
	 * @return List<FileStatus>
	 * @throws IOException
	 */
	public   List<FileStatus> doList(String directory )throws IOException{
	
		checkDao check=new checkImpl();
		List<FileStatus> list= check.doList(directory, getConf());
		for(FileStatus file:list){
			
			System.out.println("id:"+file.getPath().hashCode()+"----path:"+file.getPath()+"-----name:"+file.getPath().getName());
		}
		return list;
	}
	
	
	/**
	 * 获得文件列表
	 * @param directory example /user
	 * @return
	 * @throws IOException
	 */
	public  List<FileStatus> doListFile(String directory )throws IOException{
	
		checkDao check=new checkImpl();
		List<FileStatus> list= check.doListFile(directory, getConf());
		for(FileStatus file:list){
			System.out.println("id:"+file.getPath().hashCode()+"path:"+file.getPath()+"name:"+file.getPath().getName());
		}
		return list;
	}
	
	/**
	 * 
	 * @param directory
	 * @return
	 * @throws IOException
	 */
	public   List<FileStatus> doListDirectory(String directory )throws IOException{
	
		checkDao check=new checkImpl();
		return check.doListDirectory(directory, getConf());
	}
	
	
	/**
	 * 
	 * @param path  example /user  or /user/gg.txt
	 * @return 
	 * @throws IOException 
	 */
	
      public long getfileSize(String path) throws IOException{
    	  
    	  checkDao check=new checkImpl();
    	  long size=check.getfileSize(path, getConf());
    	  System.out.println("the size is:"+size);
    	  return size;
      }
	
	public static void main(String args[]){
	
		CheckAction check=	new CheckAction() ;
		try{
//			check.doList("/");
//			check.doListFile("/user");
//			check.doListDirectory("/");
			check.doList("/");
			check.getfileSize("/");
		}catch(IOException s){
			
		}
		
	}
}
