package com.hdfsTools.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import com.hdfsTools.dao.upLoadFile;



/**
 * 文件上传的实现类
 * @author rongjianping
 *
 */
@SuppressWarnings("deprecation")
public class upLoadFileImpl implements upLoadFile{

	/**
	 * @param localFile example /docs/1400-8.txt
	 * @param hdfsFile  example hdfs://localhost/user/tom/1400-8.txt
	 */
	public int simpleUpLoad(String localFile, String hdfsFile) {

		try{	
		InputStream in = new BufferedInputStream(new FileInputStream(localFile));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf);
		OutputStream out = fs.create(new Path(hdfsFile), new Progressable() {
		public void progress() {
		System.out.print(".");
		}
		});		
		IOUtils.copyBytes(in, out, 4096, true);
		IOUtils.closeStream(in);
		return 1;
		}catch (IOException e){
			System.out.println("发生了IO异常");
			return 0;
		}finally {
			
		}
		}
		
	}



