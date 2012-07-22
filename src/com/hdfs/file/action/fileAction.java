package com.hdfs.file.action;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hdfs.comm.util.BaseAction;
import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.user.bean.Users;
import com.hdfs.file.bean.dillResult;
import com.hdfs.file.service.fileService;
import com.hdfs.user.service.userService;

public class fileAction extends BaseAction {
	
	private long currentId;// 当前文件夹的ID
	private String wddescjson;// json格式的字符串
	private long userId;// 用户ID
	
	private userService userservice; //在spring-file.xml配置依赖注入
	private fileService fileservice; //依赖注入
	
	private String file_url;
	private String filename;// 新增文件夹的名字
	private String deadline;
	private int safelevel;
	private long fileId;// 文件目录或者id
	private int type;// 文件类型
	File uploadFile;// 上传的文件
	private String contentType;// 上传的文件类型
	InputStream inputStream;

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
		System.out.println("安全等级："+getSafelevel());
		Users user = new Users();
		user.setUserId((int) userId);
		System.out.println(user.getUserId());
		user = userservice.find(user);
		long memoryId = user.getMemoryId();
		HdfsMemory memory = fileservice.getMemory((int) memoryId);
		int free = memory.getTotalmemory() - memory.getMemoryused();
		if (free <= uploadFile.length() / 1024)
			return FAIL;
		else {

			//File dst = new File(ServletActionContext.getServletContext().getContextPath()+ "/" + this.getFilename());
			//System.out.println("the context path of the web application is : "+ServletActionContext.getServletContext().getContextPath());
			// this.copy(uploadFile, dst);
			System.out.println("currentId:"+ currentId);
			System.out.println("uploadFile:"+ uploadFile);
			System.out.println("filename:"+ this.getFilename());
			System.out.println("memory:"+ memory);
			System.out.println("safelevel:"+ getSafelevel());
			System.out.println("deadline:"+ deadline);
			dillResult result = fileservice.uploadFile(currentId, uploadFile,this.getFilename(),memory, getSafelevel(), deadline);
			if (result != null) {


				this.setWddescjson(result.getWddescjson());
				this.setCurrentId(result.getParentid());
				this.setUserId(result.getUserId());
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
		InputStream in = fileservice.downLoad(this.getFileId());
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

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileFileName(String fileName) {
		this.filename = fileName;
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



}
