package com.hdfs.file.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.file.bean.dillResult;

public interface fileService {

	/**
	 * 显示当前目录文件和目录列表
	 * @param currentId 当前目录id
	 * @param userId 用户id
	 * @return json格式的结果字符串
	 */

	String listFile(long currentId, long userId);
	
	/**
	 * 根据当前目录id，列出父目录的文件列表
	 * @param currentId
	 * @param userId
	 * @return
	 */
	dillResult listParentFile(long currentId, long userId);
	
	/**
	 * 
	 * @param parentId 父目录的id
	 * @param name 要创建的目录的名字
	 * @return
	 * @throws IOException 
	 */
	Boolean mkdir(long parentId,String name ,long userId) throws IOException;
	Boolean rootmkdir(long parentId, String name ,long userId, long rootid) throws IOException;
	/**
	 * 根据文件Id删除文件，并且返回当前文件所在文件夹的文件列表
	 * @param fileId
	 * @return
	 */
	HdfsMemory insertMemory(int userId,int type);
	
	public List<HdfsFile> listAllFile();
	
	void deleteMemory(int memoryId);
	
	void changeMemory(int memoryId,int type);
	
	void updateMemory(HdfsMemory memory);
	
	public HdfsMemory getMemory(int memoryId);
	
	String deleteFile(long fileId, HdfsMemory memory);
	
	/**
	 * 根据文件id 和 名字重命名文件
	 * @param fileId name,并不是完整的url
	 * @return
	 */
	dillResult renameFile(long fileId ,String name );
	
	/**
	 * 
	 * @param currentId 当前文件夹的id
	 * @param file
	 * @return
	 */

	
	/**
	 * 根据文件的ID下载文件
	 * @param fileId
	 * @return
	 * @throws IOException
	 */
	InputStream downLoad(long fileId) throws IOException;

	dillResult uploadFile(long currentId, File file, String filename,HdfsMemory memory, int safelevel,String deadline);
}
