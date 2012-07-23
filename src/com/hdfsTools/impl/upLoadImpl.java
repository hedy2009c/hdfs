package com.hdfsTools.impl;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import com.hdfsTools.dao.upLoadDao;

public class upLoadImpl implements upLoadDao {

    /**
     * Copy local files to a FileSystem.
     */
	@Override
	@SuppressWarnings("finally")
	public boolean copyFiletoFs(Configuration conf, String localFile, String dst,
			boolean deleteSource){
		File src = new File(localFile); 
		String fileName=src.getName();
		Path dstFile=new Path(dst+"/"+fileName);
		boolean result=false;
		try{
			FileSystem fs = FileSystem.get(conf);//����ļ�ϵͳʵ��
			result=FileUtil.copy(src, fs, dstFile, deleteSource, conf);
		}catch(IOException e){
			System.out.println("成功");
		}finally{
			return result;
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	public boolean copytoDfs(Configuration conf, File localFile, String dst, boolean deleteSource,String filename,int safelevel) {
		
		Path dstFile=new Path(dst+"/"+filename);
		boolean result=false;
		try{
			FileSystem fs = FileSystem.get(conf);
			fs.setReplication(dstFile, (short) safelevel);
			result=FileUtil.copy(localFile, fs, dstFile, deleteSource, conf);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			return result;
		}
		
	}
	

}
