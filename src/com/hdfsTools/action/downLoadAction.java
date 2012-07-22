package com.hdfsTools.action;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import com.hdfsTools.dao.downLoadDao;
import com.hdfsTools.impl.downLoadImpl;


public class downLoadAction extends BaseAction{
	
	public downLoadAction(){		
	}	
	/**
	 *Configuration
	 * @param fsUrl url,example: hdfs://localhost:8889
	 */
	/*public downLoadAction(String fsUrl){
		super(fsUrl);
	}*/

	public downLoadAction(Configuration conf){
		super(conf);
	}
	
	
	/**
	 * Copy FileSystem files to local files.
	 * @param src , example: /user/test.txt
	 * @param dst ,example: d:
	 * @param deleteSource  ,example: false
	 * @return
	 */
	public 	boolean  copyFileFromFs(
			String src, File dst, boolean deleteSource){
		
		downLoadDao download=new downLoadImpl();
		try{
		 return	download.copyFileFromFs(getConf(), src, dst, deleteSource);
		}catch (IOException e){
			System.out.println("error...�");
			return false;
		}
	}
	public static void main (String args[]){
		downLoadAction down =new downLoadAction();
		File f=new File("c:/test.txt");
		down.copyFileFromFs("/qqw/1.txt", f, false);		
}
}
