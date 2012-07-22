/**
 * 
 */
package com.hdfsTools.dao;

/**
 * 定义上传文件到hdfs的相关接口
 * @rongjianping
 *
 */
public interface upLoadFile {
	/**
	 * 上传单个文件
	 * @param localFile 要上传的文件
	 * @param hdfsFile  上传到hdfs的路径
	 * @return 1 上传成功，0 上传失败
	 */
	public int simpleUpLoad(String localFile,String hdfsFile);

}
