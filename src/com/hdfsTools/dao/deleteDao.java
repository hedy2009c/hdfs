package com.hdfsTools.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public interface deleteDao {

	/**
	 * 删除文件
	 * 
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	public boolean deleteFile(Configuration conf, String fileUrl)
			throws IOException;;

	/**
	 * 删除目录
	 * 
	 * @param directorUrl
	 * @return
	 * @throws IOException
	 */
	public boolean deleteDirector(Configuration conf, String directorUrl)
			throws IOException;;

}
