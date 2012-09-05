package com.hdfsTools.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public interface renameDao {

	public boolean rename(Configuration conf, String src, String dst)
			throws IOException;
}
