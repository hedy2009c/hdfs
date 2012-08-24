package com.hdfsTools.action;

import java.io.File;

import org.apache.hadoop.conf.Configuration;

import com.hdfsTools.dao.upLoadDao;
import com.hdfsTools.impl.upLoadImpl;


public class upLoadAction extends BaseAction{

	public upLoadAction(){		
	}	
	/**
	 * Configuration
	 * @param fsUrl url,example: hdfs://localhost:8889
	 */
	/*public upLoadAction(String fsUrl){
		super(fsUrl);
	}*/

	public upLoadAction(Configuration conf){
		super(conf);
	}
	/**
	 * 
	 * @param localFile
	 * @param dst ַ,example /user
	 * @param deleteSource 
	 * @return true,false
	 */
	public  boolean copyFiletoFs(String fsUrl,
			String localFile, String dst, boolean deleteSource){
		upLoadDao upload=new upLoadImpl();
		getConf().getStrings("dfs.replication");
		System.out.println(getConf().toString());
		System.out.print("dfs.replication:"+getConf().get("dfs.replication").toString());
		return upload.copyFiletoFs(getConf(), localFile, dst, deleteSource);
	}
	
	/**
	 * 上传文件，参数是一个文件对象
	 * @param localFile
	 * @param dst
	 * @param deleteSource
	 * @param safelevel 
	 * @param safelevel 
	 * @return
	 */
	public boolean copytoDFS(File localFile,String dst,boolean deleteSource,String filename, int safelevel){
		upLoadDao upload=new upLoadImpl();
		return upload.copytoDfs(getConf(), localFile, dst, deleteSource,filename,safelevel);
		
	}
	
	public static void main(String args[]){
		
		String fsUrl="hdfs://localhost:8889";
	String localFile="c:/1.txt";
		String dst="/qqw";
	boolean deleteSource=false;
	new upLoadAction().copyFiletoFs(fsUrl, localFile, dst, deleteSource);
		//new upLoadAction(fsUrl);
	}
	public boolean fileExists(String filePath) {
		upLoadDao upload=new upLoadImpl();
		return upload.fileExists(getConf(),filePath);
		
	}
}
