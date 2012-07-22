package com.hdfsTools.impl;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import com.hdfsTools.dao.downLoadDao;

public class downLoadImpl implements downLoadDao{

	/**
	 *  Copy FileSystem files to local files.
	 */
	public boolean copyFileFromFs(Configuration conf, String src, File dst,
			boolean deleteSource) throws IOException {
		FileSystem fs = FileSystem.get(conf);//初始化文件系统
		Path srcPath = new Path(src); 
//		String fileName=srcPath.getName().substring(srcPath.getName().lastIndexOf("/")+1);
//		File dstFile=new File(dst);
//		System.out.println("the file is :"+dstFile.toString());
//		if(!fs.exists(srcPath)){
//			System.out.println("检查是否存在");
//			return false;
//		}
		return FileUtil.copy(fs, srcPath, dst, deleteSource, conf);
	}
	
	
	

}
