package com.hdfsTools.test;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class fileUtilTest {

	public static void main(String[] args)throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://localhost:8889");
		
		try {
		FileSystem fs = FileSystem.get(conf); 
		Path src = new Path("/user1/dvd2.txt"); 
		File dst=new File("d:/test.txt");
		System.out.println(FileUtil.copy(fs, src, dst, false, conf));
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
	
}
