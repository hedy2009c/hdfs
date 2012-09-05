package com.hdfsTools.action;

import java.io.IOException;

import com.hdfsTools.dao.deleteDao;
import com.hdfsTools.impl.deleteImpl;

public class deleteAction extends BaseAction{

	public deleteAction(){		
	}
	
	/**
	 * Configuration
	 * @param fsUr url,example: hdfs://localhost:8889
	 */
	/*public deleteAction(String fsUrl){
		super(fsUrl);
	}*/

	public deleteAction(Configuration conf){
		super(conf);
	}
	/**
	 * 
	 * @param fileUrl url example: /user/test.txt
	 * @return 
	 * @throws IOException 
	 */
	public  boolean deleteFile(String fileUrl) throws IOException{
		
		deleteDao delete=new deleteImpl();
		return delete.deleteFile(getConf(), fileUrl);
		
		
	}
	
	/**
	 * 
	 * @param directorUrl url ,example: /user
	 * @return 
	 */
	public  boolean deleteDirector( String directorUrl){
		deleteDao delete=new deleteImpl();
		try{
		return delete.deleteDirector(getConf(), directorUrl);
		}catch (IOException e){
			System.out.println("throw the IOEception...");
			return false;
		}
	}
	
	public static void main(String ars[]) throws IOException{
		
		deleteAction delete=new deleteAction();
		String dir="/user/ll";
		delete.deleteFile(dir);
		
	}
	
	
}
