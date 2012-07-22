package com.hdfsTools.dao;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public interface upLoadDao {

    /**
     * Copy local files to a FileSystem.	
     * @param fsUrl
     * @param localFile
     * @param dst
     * @param deleteSource
     * @return
     * @throws IOException
     */
	public boolean copyFiletoFs(Configuration conf,
			String localFile, String dst, boolean deleteSource);
	
	/**
	 * 
	 * @param conf
	 * @param localFile 类型是File
	 * @param dst
	 * @param deleteSource
	 * @param safelevel 
	 * @param  
	 * @return
	 */
	public boolean copytoDfs(Configuration conf,
			File localFile, String dst, boolean deleteSource,String filename, int safelevel);
}
