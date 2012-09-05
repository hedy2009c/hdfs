package com.hdfsTools.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.hdfsTools.dao.checkDao;

public class checkImpl implements checkDao {

	/**
	 * 获得文件和目录的列表
	 */
	@Override
	public List<FileStatus> doList(String directory, Configuration conf)
			throws IOException {
		List<FileStatus> list = new ArrayList<FileStatus>();
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(directory);
		FileStatus fileList[] = fs.listStatus(path);
		for (FileStatus fileStatues : fileList) {
			list.add(fileStatues);
		}

		return list;
	}

	/**
	 * 获得文件列表
	 */
	@Override
	public List<FileStatus> doListFile(String directory, Configuration conf)
			throws IOException {
		List<FileStatus> list = new ArrayList<FileStatus>();
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(directory);
		FileStatus fileList[] = fs.listStatus(path);
		for (FileStatus fileStatues : fileList) {
			if (!fileStatues.isDir()) {// 文件列表
				list.add(fileStatues);
				System.out.println("the uri is:"
						+ fileStatues.getPath().toUri());
			}
		}
		return list;
	}

	/**
	 * 获得目录列表
	 */
	@Override
	public List<FileStatus> doListDirectory(String directory, Configuration conf)
			throws IOException {
		List<FileStatus> list = new ArrayList<FileStatus>();
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(directory);
		FileStatus fileList[] = fs.listStatus(path);
		for (FileStatus fileStatues : fileList) {
			if (fileStatues.isDir()) {
				list.add(fileStatues);
				System.out.println("the uri is:"
						+ fileStatues.getPath().toUri());
			}
		}
		return list;
	}

	@Override
	public long getfileSize(String path, Configuration conf) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		Path filepath = new Path(path);
		ContentSummary contentSumary = fs.getContentSummary(filepath);
		System.out.println("getSpaceConsumed:"
				+ contentSumary.getSpaceConsumed());
		System.out.println("getDirectoryCount:"
				+ contentSumary.getDirectoryCount());
		System.out.println("getFileCount:" + contentSumary.getFileCount());
		System.out.println("getSpaceQuota:" + contentSumary.getSpaceQuota());
		System.out.println("getQuota:" + contentSumary.getQuota());

		return contentSumary.getLength();
	}

}
