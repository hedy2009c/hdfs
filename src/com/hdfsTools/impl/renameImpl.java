package com.hdfsTools.impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.hdfsTools.dao.renameDao;

public class renameImpl implements renameDao {

	@Override
	public boolean rename(Configuration conf, String src, String dst)
			throws IOException {
		FileSystem fs = FileSystem.get(conf);
		Path srcPath = new Path(src);
		Path dstPath = new Path(dst);

		return fs.rename(srcPath, dstPath);
	}

}
