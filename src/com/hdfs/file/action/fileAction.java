package com.hdfs.file.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.encryption.RSAEncrypter;
import com.hdfs.comm.util.BaseAction;
import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.file.bean.dillResult;
import com.hdfs.file.service.fileService;
import com.hdfs.user.bean.Users;
import com.hdfs.user.service.userService;

public class fileAction extends BaseAction {
	private String wddescjson;// json格式的字符串

	private userService userservice; //在spring-file.xml配置依赖注入
	private fileService fileservice; //依赖注入
	
	private String file_url;

	private long fileId;// 文件目录或者id
	
	File uploadFile;// 上传的文件
	File privateKey; //上传的privateKey
	
	private String filename;// 上传的文件名
	private String privateKeyName; //上传的私钥名
	
	private String contentType;// 上传的文件类型
	private int uploadType; //是否加密上传，1为加密上传，0为普通上传
	private int safelevel;		//安全等级，即备份数
	private String deadline;	//文件的有效日期
	private long currentId;	// 当前文件夹的ID
	private long userId;	// 用户ID
	

	InputStream inputStream; //用于下载时的输入流 

	

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 根据用户ID和当前目录ID查询当前目录的文件列表（包括文件夹）
	 * 
	 * @param currentId
	 *            , userId
	 * @return
	 */
	public String dolistFile() {
		wddescjson = fileservice.listFile(currentId, userId);
		return SUCCESS;

	}

	/**
	 * 参数是当前目录的id和userid
	 * 
	 * @return
	 */
	public String listParentFile() {
		dillResult result = fileservice.listParentFile(currentId, userId);
		this.setCurrentId(result.getParentid());
		this.setUserId(result.getUserId());
		this.setWddescjson(result.getWddescjson());
		return SUCCESS;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param currentId
	 *            , filename, userId
	 * @return
	 * @throws IOException 
	 */
	public String domkDir() throws IOException {
		if (null == filename || "".equals(filename)) {
			return FAIL;
		}
		if (fileservice.mkdir(currentId, filename, userId)) {
			wddescjson = fileservice.listFile(currentId, userId);
			return SUCCESS;
		} else {
			return FAIL;
		}

	}

	/**
	 * 删除文件或者文件夹
	 * 
	 * @param fileId
	 *            currentId userId
	 * @return
	 */
	public String deleteFile() {
		if (fileId == 0) {
			return FAIL;
		} else {
			System.out.println("安全等级："+getSafelevel());
			Users user = new Users();
			user.setUserId((int) userId);
			System.out.println(user.getUserId());
			user = userservice.find(user);
			long memoryId = user.getMemoryId();
			HdfsMemory memory = fileservice.getMemory((int) memoryId);
			wddescjson = fileservice.deleteFile(fileId,memory);
			System.out.println("result is :" + wddescjson);
		}
		return SUCCESS;
	}

	/**
	 * @param fileId
	 *            , filename
	 * @return
	 */
	public String renameFile() {

		dillResult result = fileservice.renameFile(fileId, filename);
		if (result != null) {
			this.setWddescjson(result.getWddescjson());
			this.setCurrentId(result.getParentid());
			this.setUserId(result.getUserId());
			this.setFile_url(result.getFileUrl() + "1");
			System.out.println("the userid :" + this.getUserId());
			return SUCCESS;
		} else {
			return FAIL;
		}

	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String fileUrl) {
		file_url = fileUrl;
	}

	/**
	 * 上传文件
	 * 
	 * @return
	 */

	public String uploadFile() {
		byte[] encryptedDataSecretKey = null; //用来保存加密后的DES密钥
		
		Users user = new Users();
		user.setUserId((int) userId);

		user = userservice.find(user);
		
		long memoryId = user.getMemoryId();
		HdfsMemory memory = fileservice.getMemory((int) memoryId);
		
		int free = memory.getTotalmemory() - memory.getMemoryused();
		
		if (getUploadType()!=0) { //加密上传
			/*判断是否第一次使用加密上传的功能
			 * 可以提取users表的public_key字段判断
			 * 若publicKey字段为空，说明还没使用过加密上传的功能
			 */
			if(fileservice.isPublicKeyEmpty(user)) {	//查找该用户的publicKey是否为空
				/*
				 * 第一次使用加密上传功能
				 */
				return "generateKeyPair";
				
			}
			else { //进入加密模块

				/*
				 * 生成DES密钥用来加密文件
				 */
				
				encryptedDataSecretKey = fileservice.encryptFile(userId, uploadFile);
				
				uploadFile = new File(uploadFile.getAbsolutePath()+".des");
				
			}
		}
		if (free <= uploadFile.length() / 1024)
			return FAIL;
		else {

			//File dst = new File(ServletActionContext.getServletContext().getContextPath()+ "/" + this.getFilename());
			//System.out.println("the context path of the web application is : "+ServletActionContext.getServletContext().getContextPath());
			// this.copy(uploadFile, dst);
	
			/*
			 * 上传文件到hdfs
			 */
			dillResult result = fileservice.uploadFile(currentId, uploadFile,this.getFilename(),memory, getSafelevel(), deadline);

			
			if (result != null) {


				this.setWddescjson(result.getWddescjson());
				this.setCurrentId(result.getParentid());
				this.setUserId(result.getUserId());
				this.setFileId(result.getFileId());
				if (getUploadType()!=0) { //加密上传的后续处理
			        /*
			         * 将加密后的数据密钥保存到数据库中 encryptedDataSecretKey
			         * 即更新对应文件下的encrypt_DataKey属性
			         */
					HdfsFile hdfsFile = new HdfsFile();
					hdfsFile.setFileId(fileId);

					fileservice.storeEncryptDataKey(fileId, encryptedDataSecretKey );
					
				}
				return SUCCESS;
			} else {
				return FAIL;
			}
		}
	}

	public String downLoad() throws IOException {
		if (this.getFileId() == 0) {
			return FAIL;
		}
		if (this.getFilename() == null || "".equals(this.getFilename())) {
			this.setFilename("未知名字");
		}

		/*
		 * 若为普通文件，直接设置inputStream为该输入流
		 * 否则，需要用户上传privateKey来辅助解密该文件
		 */
		if (fileservice.isEncryptFile(this.getFileId())) {
			return "uploadPrivateKey";
		}
		else {
			/*
			 * 获取存储在hdfs文件系统上的文件（普通文件或加密文件）
			 */
			File file = fileservice.downLoad(this.getFileId());
			InputStream in = new FileInputStream(file);
			this.setInputStream(in);
			return SUCCESS;
		}
	}
	
	public String decryptFile() throws IOException {
		
		if (this.getFileId() == 0) {
			return FAIL;
		}
		if (this.getFilename() == null || "".equals(this.getFilename())) {
			this.setFilename("未知名字");
		}
		File file = fileservice.downLoad(this.getFileId());
		/*
		 * 利用privateKey文件解密“加密后的数据密钥”
		 * 用数据密钥解密文件
		 */
		if (privateKey==null) {
			return FAIL;
		}
		InputStream in = fileservice.decryptFile(privateKey, fileId, file);
		
		this.setInputStream(in);
		return SUCCESS;

	}

	private void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			BufferedInputStream l;
			try {
				in = new BufferedInputStream(new FileInputStream(src), 64);
				out = new BufferedOutputStream(new FileOutputStream(dst), 64);
				byte[] buffer = new byte[64];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 生成RSA密钥对，并保存公钥到数据库中
	 */
	public String generateKeyPair() {
		
		Users user = new Users();
		user.setUserId((int) userId);
		user = userservice.find(user);
		
		try {
			fileservice.generatePublicKey(user);
			
		}catch (Exception e) {   
            e.printStackTrace();   
        }  
		return "downLoadPrivateKey";
	}

	/**
	 * 下载privateKey文件到客户端
	 * @return
	 * @throws IOException
	 */
	public String downLoadPrivateKey() throws IOException {

		String privateKeyPath = "D:/hdfs/"+userId+"/privateKey";
		InputStream in = new FileInputStream(privateKeyPath);
		this.setInputStream(in);
		return SUCCESS;
	}
	
	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public fileService getFileservice() {
		return fileservice;
	}

	public void setFileservice(fileService fileservice) {
		this.fileservice = fileservice;
	}

	public userService getUserservice() {
		return userservice;
	}

	public void setUserservice(userService userservice) {
		this.userservice = userservice;
	}

	public long getCurrentId() {
		return currentId;
	}

	public void setCurrentId(long currentId) {
		this.currentId = currentId;
	}

	public String getWddescjson() {
		return wddescjson;
	}

	public void setWddescjson(String wddescjson) {
		this.wddescjson = wddescjson;

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	/**
	 * 以struts2要求的格式设置所要上传的文件
	 * @param uploadFile 应用服务器下的临时文件
	 */
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	/**
	 * 以struts2要求的格式设置所要上传的文件
	 * @param privateKey 要上传的rsa私钥
	 */
	public void setPrivateKey(File privateKey) {
		this.privateKey = privateKey;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	/**
	 *以struts2规定的格式设置上传的文件的类型
	 * setXContentType
	 * @param contentType 上传文件的类型
	 */
	public void setUploadFileContentType(String contentType) { 
		this.contentType = contentType;
	}

	/**
	 * 以struts2规定的格式设置上传的文件的类型
	 * setXContentType
	 * @param contentType 上传文件的类型
	 */
	public void setPrivateKeyContentType(String contentType) { 
		this.contentType = contentType;
	}
	
	/**
	 * 以struts2规定的格式设置上传文件的实际名字
	 * setXFileName
	 * @param fileName 实际名字
	 */
	public void setUploadFileFileName(String fileName) {
		this.filename = fileName;
	}

	/**
	 * 以struts2规定的格式设置上传文件的实际名字
	 * setXFileName
	 * @param fileName 实际名字
	 */
	public void setPrivateKeyFileName(String fileName) {
		this.setPrivateKeyName(fileName);
	}
	
	public void setSafelevel(int safelevel) {
		this.safelevel = safelevel;
	}

	public int getSafelevel() {
		return safelevel;
	}
	
	
		
		public void nodeprint() throws IOException{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/xml;charset=utf-8");   
	        response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			System.out.println("调用了.........");
			ArrayList<HdfsFile> list=  (ArrayList<HdfsFile>)fileservice.listAllFile();
		    if(list!=null&&list.size()>0){
		    	out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		        out.println("<nodes>");
		    	for(int i=0;i<list.size();i++){
		    		HdfsFile file = list.get(i);
		    		out.println("<node file_id='"+file.getFileId()+"' parentid='"+file.getParentid()+"' hrefAddress='"+file.getFileId()+"' userid='"+file.getUserId()+"'>"+file.getFileName()+"</node>");
		    		System.out.println(file.getFileName());
		    	}
		    	out.println("</nodes>");
		    }
		    System.out.println("调用完成");
			
	}

		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}

		public String getDeadline() {
			return deadline;
		}

		public int getUploadType() {
			return uploadType;
		}

		public void setUploadType(int uploadType) {
			this.uploadType = uploadType;
		}

		public String getPrivateKeyName() {
			return privateKeyName;
		}

		public void setPrivateKeyName(String privateKeyName) {
			this.privateKeyName = privateKeyName;
		}



}
