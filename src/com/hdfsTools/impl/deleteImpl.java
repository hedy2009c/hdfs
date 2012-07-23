package com.hdfsTools.impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.hdfsTools.dao.deleteDao;

public class deleteImpl implements deleteDao{

	@Override
	public boolean deleteFile(Configuration conf, String fileUrl) throws IOException {
		FileSystem fs = FileSystem.get(conf);//获得文件系统实例
		Path srcPath = new Path(fileUrl);
		System.out.println("deleteing the file:"+fileUrl);		
		//FsPermission f=new FsPermission((short) (4));
		return  fs.delete(srcPath, false);
		
		
	}

	@Override
	public boolean deleteDirector(Configuration conf, String directorUrl)
			throws IOException {
	    FileSystem fs = FileSystem.get(conf);//获得文件系统实例
		Path srcPath = new Path(directorUrl);
		System.out.println("deleteing the file:"+directorUrl);
		return  fs.delete(srcPath, true);
	}
	

}
