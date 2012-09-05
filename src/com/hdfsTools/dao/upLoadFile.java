/**
 * 
 */
package com.hdfsTools.dao;

/**
 * �����ϴ��ļ���hdfs����ؽӿ�
 * 
 * @rongjianping
 * 
 */
public interface upLoadFile {
	/**
	 * �ϴ������ļ�
	 * 
	 * @param localFile
	 *            Ҫ�ϴ����ļ�
	 * @param hdfsFile
	 *            �ϴ���hdfs��·��
	 * @return 1 �ϴ��ɹ���0 �ϴ�ʧ��
	 */
	public int simpleUpLoad(String localFile, String hdfsFile);

}
