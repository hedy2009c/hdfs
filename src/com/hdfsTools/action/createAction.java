package com.hdfsTools.action;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.DFSClient;

import com.hdfsTools.dao.mkdirDao;
import com.hdfsTools.impl.mkdirImpl;

public class createAction extends  BaseAction{

	public createAction(){		
	}
	
	/**
	 * 
	 * @param fsUrl ,example: hdfs://localhost:8889
	 */
	/*public createAction(String fsUrl){
		super(fsUrl);
	}*/

	public createAction(Configuration conf){
		super(conf);
	}
	
	
	
	/**
	 * 
	 * @param fsUrl url,example: hdfs://localhost:8889
	 * @param dirUrl ,example: /user
	 * @return
	 * @throws IOException
	 */
	
	public  boolean createDir (String dirUrl)
            throws IOException{
		mkdirDao mkdir=new mkdirImpl();
		return mkdir.createDir(getConf(), dirUrl);
		
	}
	
	
	
	
	
	
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		new createAction().createDir("/user/ltl");
		

	}

}
