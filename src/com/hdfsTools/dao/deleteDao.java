package com.hdfsTools.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public interface deleteDao {
	
	/**
	 * É¾³ýÎÄ¼þ
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	public boolean deleteFile(Configuration conf,String fileUrl)throws IOException;;
	
	/**
	 * É¾³ýÄ¿Â¼
	 * @param directorUrl
	 * @return
	 * @throws IOException
	 */
	public boolean deleteDirector(Configuration conf, String directorUrl)throws IOException;;

}
