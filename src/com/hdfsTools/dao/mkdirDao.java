package com.hdfsTools.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public interface mkdirDao {

	/**
	 * 
	 * @param fsUrl
	 * @param dirUrl
	 * @return
	 * @throws IOException
	 */
	public boolean createDir(Configuration conf, String dirUrl)
			throws IOException;

}
