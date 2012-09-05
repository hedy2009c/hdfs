package com.hdfsTools.dao;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;

public interface checkDao {
	/**
	 * 列出指定目录的所有文件和文件夹
	 * 
	 * @param directory
	 *            指定的目录
	 * @param conf
	 *            指定的文件系统的配置
	 * @return
	 */
	public List<FileStatus> doList(String directory, Configuration conf)
			throws IOException;

	/**
	 * 列出指定目录的所有文件
	 * 
	 * @param directory
	 *            指定的目录
	 * @param conf
	 *            指定的文件系统的配置
	 * @return
	 */
	public List<FileStatus> doListFile(String directory, Configuration conf)
			throws IOException;

	/**
	 * 列出指定目录的所有目录
	 * 
	 * @param directory
	 *            指定的目录
	 * @param conf
	 *            指定的文件系统的配置
	 * @return
	 */
	public List<FileStatus> doListDirectory(String directory, Configuration conf)
			throws IOException;

	/**
	 * 
	 * @param path
	 *            指定的文件的路径
	 * @param conf
	 *            指定的文件系统的配置
	 * @return
	 * @throws IOException
	 */
	public long getfileSize(String path, Configuration conf) throws IOException;

}
