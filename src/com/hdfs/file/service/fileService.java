package com.hdfs.file.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.file.bean.dillResult;
import com.hdfs.user.bean.Users;

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
	File downLoad(long fileId) throws IOException;

	/**
	 * 上传文件到hdfs
	 * @param currentId hdfs上的当前目录，即上传file文件到该目录下
	 * @param file	要上传的文件，服务器临时文件
	 * @param filename	要上传文件的实际文件名
	 * @param memory	用户的存储类对象
	 * @param safelevel	安全等级
	 * @param deadline	文件有效日期
	 * @return
	 */
	dillResult uploadFile(long currentId, File file, String filename,HdfsMemory memory, int safelevel,String deadline);

	boolean isPublicKeyEmpty(Users user);

	/**
	 * 第一次使用加密上传文件功能时：
	 * 生成rsa密钥对，并保存公钥的内容到数据库中。
	 * @param user
	 */
	void generatePublicKey(Users user);

	/**
	 * 用DES密钥加密文件
	 * @param userId	用来加密DES密钥的公钥对应的用户
	 * @param uploadFile	被加密的文件
	 * @return	返回加密后的文件
	 */
	byte[] encryptFile(long userId, File uploadFile);

	/**
	 * 将加密后的数据密钥保存到数据库中
	 * @param fileId	用来找到加密后的数据密钥对应的文件
	 * @param encryptedDataSecretKey	加密后的密钥的内容
	 */
	void storeEncryptDataKey(long fileId, byte[] encryptedDataSecretKey);

	/**
	 * 判断fileId对应的文件是否加密
	 * @param fileId 被判断的文件Id
	 * @return
	 */
	boolean isEncryptFile(long fileId);

	/**
	 * 解密文件
	 * @param privateKey 用来解密已加密的数据密钥
	 * @param fileId	加密文件对应的id
	 * @param in	封装了加密文件的输入流
	 * @return
	 */
	InputStream decryptFile(File privateKey, long fileId, File file);

	/**
	 * 判断当前文件夹currentId下，文件filename是否存在。
	 * @param currentId	当前文件夹
	 * @param filename 文件名
	 * @return
	 */
	boolean exists(long currentId, String filename);

	/**
	 * 获取将要删除的文件的ID
	 * @param currentId
	 * @param filename
	 * @return
	 */
	long getDeletedFileId(long currentId, String filename);


}
