package com.hdfs.comm.util;

/**
 * 根据文件或者目录的路径哈希获得文件或者目录的id
 * 
 * @author Administrator
 * 
 */
public class pathToId {

	public static long ParsepathToId(String path) {

		return path.hashCode();
	}

	public static void main(String path[]) {

		String k = "/jianping/mark.excel";
		long hc = k.hashCode();
		System.out.println(hc);

	}

}
