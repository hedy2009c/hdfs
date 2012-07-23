package com.hdfsTools.impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.hdfsTools.dao.mkdirDao;

public class mkdirImpl implements mkdirDao{

	@Override
	public  boolean createDir(Configuration conf,String dirUrl)throws IOException{

		FileSystem fs = FileSystem.get(conf);//����ļ�ϵͳʵ��
		Path dirPath = new Path(dirUrl);
		return fs.mkdirs(dirPath);
		
	}
}
