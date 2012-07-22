package com.hdfsTools.dao;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public interface downLoadDao {

	/**
	 * Copy FileSystem files to local files.
	 * @param src
	 * @param dst
	 * @param deleteSource
	 * @return
	 * @throws IOException
	 */
	public 	boolean  copyFileFromFs(Configuration conf,
			String src, File dst, boolean deleteSource)throws IOException;
}
