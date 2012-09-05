package com.hdfsTools.dao;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;

public interface checkDao {
    /**
     * �г�ָ��Ŀ¼�������ļ����ļ���
     * @param directory ָ����Ŀ¼
     * @param  conf ָ�����ļ�ϵͳ������
     * @return
     */
	public List<FileStatus> doList(String directory ,Configuration conf)throws IOException;
    /**
     * �г�ָ��Ŀ¼�������ļ�
     * @param directory ָ����Ŀ¼
     * @param conf ָ�����ļ�ϵͳ������
     * @return
     */
	public List<FileStatus> doListFile(String directory ,Configuration conf)throws IOException;
	
	 /**
     * �г�ָ��Ŀ¼������Ŀ¼
     * @param directory ָ����Ŀ¼
     * @param conf ָ�����ļ�ϵͳ������
     * @return
     */
	public List<FileStatus> doListDirectory(String directory ,Configuration conf)throws IOException;
	
	/**
	 * 
	 * @param path ָ�����ļ���·��
	 * @param conf ָ�����ļ�ϵͳ������
	 * @return
	 * @throws IOException
	 */
	public long getfileSize(String path, Configuration conf)throws IOException;	

}
