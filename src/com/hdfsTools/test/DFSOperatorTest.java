package com.hdfsTools.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DFSOperatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testReadFile();
	}
	
	public static void testReadFile() {
		try {
			Configuration config = new Configuration();
			config.set("fs.default.name", "localhost:8889");
			FileSystem hdfs = FileSystem.get(config);
			Path path = new Path("/user");
			boolean isExists = hdfs.exists(path);
			System.out.println("isExists:" + isExists);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
