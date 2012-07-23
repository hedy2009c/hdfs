package com.hdfs.file.serviceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.hdfs.comm.util.fileUtil;
import com.hdfs.comm.util.pathToId;
import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.file.bean.checkFile;
import com.hdfs.file.bean.dillResult;
import com.hdfs.file.dao.fileDao;
import com.hdfs.file.service.fileService;
import com.hdfs.user.dao.userDao;
import com.hdfsTools.action.createAction;
import com.hdfsTools.action.deleteAction;
import com.hdfsTools.action.downLoadAction;
import com.hdfsTools.action.renameAction;
import com.hdfsTools.action.upLoadAction;


public class fileServiceImpl implements fileService{
	public fileDao filedao;
	public userDao userdao;
	public userDao getUserdao() {
		return userdao;
	}

	public void setUserdao(userDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public String listFile(long currentId, long userId) {
		
		List<HdfsFile> fileList=filedao.listFile(currentId, userId);//获得文件列表
		//转化为json格式
		ArrayList<checkFile> list=new ArrayList();
		for(HdfsFile hdfsfile : fileList){
			checkFile file=new checkFile();
			if(null==hdfsfile.getCreateTime()){
				file.setCreateTime("");
			}else{
				file.setCreateTime(hdfsfile.getCreateTime().toString());
			}	
			if(null==hdfsfile.getDeadline()){
				file.setTime("");
			}else{
				file.setTime(hdfsfile.getDeadline().toString());
			}	
			file.setId(hdfsfile.getFileId());
			if(null==hdfsfile.getModifiedTime()){
				file.setModifiedTime("");
			}else{
				file.setModifiedTime(hdfsfile.getModifiedTime().toString());
			}
			file.setName(hdfsfile.getFileName());
			file.setParentId(hdfsfile.getParentid());
			if(null==hdfsfile.getSafeLevel()){
				file.setSaveLevel(0);
			}else{
				file.setSaveLevel(hdfsfile.getSafeLevel());
			}
			if(null==hdfsfile.getSize()){
				file.setSize(-1);
			}else{
				file.setSize(hdfsfile.getSize());
			}
			file.setType(hdfsfile.getType());
			file.setUrl(hdfsfile.getFileUrl());
			

			list.add(file);
			file=null;
		}
		JSONObject jsonresult=new JSONObject();
		jsonresult.accumulate("list", list);
	    String jsonList=JSONUtils.valueToString(jsonresult);
	    System.out.println(jsonList);
		return jsonList;
		
	}
	
	@Override
	public List<HdfsFile> listAllFile() {
		List<HdfsFile> fileList=filedao.listAllFile();
		return fileList;
	}

	public fileDao getFiledao() {
		return filedao;
	}

	public void setFiledao(fileDao filedao) {
		this.filedao = filedao;
	}

	@Override
	public Boolean mkdir(long parentId, String name ,long userId) throws IOException {
		
		String parentPath=filedao.getFileUrl(parentId);//获取父目录的id
//		System.out.println("the filepath is:"+parentPath);
//		String filePath=parentPath.substring(1, parentPath.length());
		System.out.println("the filepath is:"+parentPath);
		String newfile = null;
		System.out.println(parentPath.length());
		System.out.println(parentPath.lastIndexOf("/"));
		if ((parentPath.length()-1) == parentPath.lastIndexOf("/")){
			newfile=parentPath+name;
		}//新建文件的详细路径
		else{
			newfile=parentPath+"/"+name;
		}
		long fileId=pathToId.ParsepathToId(newfile);//hash得出fileid
		HdfsFile hdfsfile=new HdfsFile(fileId, name, parentId,newfile, 0);
		hdfsfile.setUserId(userId);
		hdfsfile.setCreateTime(new Date());
		hdfsfile.setModifiedTime(new Date());
		
			System.out.println(newfile);
			boolean result=filedao.insertFile(hdfsfile);//保存文件
			 createAction caction=new  createAction();
			 caction.createDir(newfile);//操作文件系统
			 return result;
		
			
		
		
		
	}
	
@Override
public Boolean rootmkdir(long parentId, String name ,long userId, long rootid) throws IOException {
		
		String parentPath=filedao.getFileUrl(parentId);//获取父目录的id
//		System.out.println("the filepath is:"+parentPath);
//		String filePath=parentPath.substring(1, parentPath.length());
		System.out.println("the filepath is:"+parentPath);
		String newfile = null;
		System.out.println(parentPath.length());
		System.out.println(parentPath.lastIndexOf("/"));
		if ((parentPath.length()-1) == parentPath.lastIndexOf("/")){
			newfile=parentPath+name;
		}//新建文件的详细路径
		else{
			newfile=parentPath+"/"+name;
		}
		long fileId=rootid;//hash得出fileid
		HdfsFile hdfsfile=new HdfsFile(fileId, name, parentId,newfile, 0);
		hdfsfile.setUserId(userId);
		hdfsfile.setCreateTime(new Date());
		hdfsfile.setModifiedTime(new Date());
		
			System.out.println(newfile);
			boolean result=filedao.insertFile(hdfsfile);//保存文件
			 createAction caction=new  createAction();
			 caction.createDir(newfile);//操作文件系统
			 return result;
		
		
		
	}
	@Override
	public String deleteFile(long fileId,HdfsMemory memory) {
		// TODO Auto-generated method stub
		boolean deletedb=false;
		String result="";
		HdfsFile file= filedao.findFile(fileId);
		if(filedao.findFile(fileId)==null) System.out.println("kongduixiang");//获得文件的url
		System.out.println("url is :"+file.getFileUrl());
		deleteAction delete=new deleteAction();
		boolean deletehdfs=delete.deleteDirector(file.getFileUrl());
		if(deletehdfs){
			int delmem = filedao.deleteFilesmem(file.getFileUrl());
			deletedb= filedao.deleteFiles(file.getFileUrl());//删除数据库中该文件和子文件的记
			//Users user = new Users();
		    //System.out.println(hfile.getFileId());
		    //int id = (int) file.getUserId();    
		    //user.setUserId(id);
		   // user = userdao.findid(id);
		    //HdfsMemory memory = filedao.find(user.getMemoryId());
		    //HdfsMemory memory = filedao.getMemory(userdao.find(user).getMemoryId());
		    memory.setMemoryused(memory.getMemoryused()-delmem);
		    filedao.updateMemory(memory);
		}else {
			return null;
		}
		if(deletedb){
			System.out.println("the result is "+deletedb);
			result=listFile(file.getParentid(), file.getUserId());
		}
	    return result;
		
		
	}

	@Override
	public dillResult renameFile(long fileId ,String name) {
		boolean dohdfs=false;
		boolean dodelete=false;
		boolean doinsert=false;
		dillResult result=new dillResult();
		HdfsFile file=filedao.findFile(fileId);
		String src=file.getFileUrl();//源url
		String dst=src.substring(0, src.lastIndexOf("/"))+"/"+name;//新的url
		System.out.println("the new name is:"+dst);
		renameAction rename=new renameAction();
		try {
			dohdfs=rename.rename(src, dst);//操作文件系统
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		if(dohdfs){
			System.out.println("do hdfs success!");
			filedao.deleteFiles(file.getFileUrl());//删除原来的记录
			dodelete=true;
		}
		if(dodelete){
			
			System.out.println("do delete success!");
			long newid=pathToId.ParsepathToId(dst);//获得新的fileid
			file.setFileId(newid);
			file.setFileName(name);
			file.setFileUrl(dst);
			doinsert=filedao.insertFile(file);//新增记录			
		}
		if(doinsert){
			System.out.println("do insert success");
			String listfile=listFile(file.getParentid(), file.getUserId());
			result.setWddescjson(listfile);
			result.setUserId(file.getUserId());
			result.setParentid(file.getParentid());
			//result.setFileName(name);
			result.setFileUrl(dst);
			System.out.println("the new name is:"+dst);
		}
		
		return result;
	}

	@Override
	public dillResult listParentFile(long currentId, long userId) {
		HdfsFile file=filedao.findFile(currentId);
		System.out.println();
		if(null==file){
			System.out.println("是空的！");
			return null;
		}
		dillResult result=new dillResult();
		String listfile=listFile(file.getParentid(), file.getUserId());
		result.setWddescjson(listfile);
		result.setUserId(file.getUserId());
		result.setParentid(file.getParentid());
		result.setFileId(file.getFileId());		
		return result;
		
		
	}
	

	
	@Override
	/**
	 * 上传文件
	 * currentId：当前文件夹的Id
	 * file：上传的文件
	 * filename:
	 * memory:
	 * safelevel:
	 * deadline:
	 */
	public dillResult uploadFile(long currentId, File file ,String filename,HdfsMemory memory, int safelevel,String deadline) {
		
		/*  
		 *获取所上传的文件的大小，在数据库dao层更新该用户已使用的存储空间
		 */
		long size = file.length()/1024; 	//获取所上传的文件的大小（kb)
		memory.setMemoryused((int) (memory.getMemoryused()+size));	//更新已使用的空间
		filedao.updateMemory(memory);	//实际更新已使用的空间
		System.out.println("文件大小："+size);
		
		HdfsFile newfile=new HdfsFile(); 	//建立HdfsFile对象，用于写入数据库
		dillResult resultReturn=new dillResult(); 	//建立dillResult不知有何用
		
		/* 
		 * 获取上传到所在目录对应的url，也就是要上传文件到hdfs文件系统的目标目录 
		 */
		HdfsFile dfsfile=filedao.findFile(currentId);
		String dst=dfsfile.getFileUrl();	//获得当前目录的url
		System.out.println("目标地址："+dst);
		
		upLoadAction upAction=new upLoadAction();
		System.out.println("源文件地址"+file);
		System.out.println("目标文件名字"+ filename);
		boolean result=upAction.copytoDFS(file, dst,true,filename,safelevel);
		
		if(result){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date newDate;
			try {
				newDate = new Date(dateFormat.parse(deadline).getTime());
				newfile.setDeadline(newDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			newfile.setSize(size);
			newfile.setType(fileUtil.checkType(filename));
			newfile.setFileName(filename);
			newfile.setFileUrl(dfsfile.getFileUrl()+"/"+filename);
			newfile.setParentid(dfsfile.getFileId());
			newfile.setFileId(pathToId.ParsepathToId(newfile.getFileUrl()));
			newfile.setUserId(dfsfile.getUserId());
			newfile.setCreateTime(new Date());
			newfile.setModifiedTime(new Date());
			newfile.setSafeLevel(safelevel);
			result=filedao.insertFile(newfile);
		}
		System.out.println("result------>"+result);
		if(result){
			System.out.println("fileId:"+ newfile.getFileId());
			System.out.println("fileName:"+ newfile.getFileName());
			System.out.println("parentid:" + newfile.getParentid());
			System.out.println("size:" + newfile.getSize());
			System.out.println("fileUrl:" + newfile.getFileUrl());
			System.out.println("createTime:" + newfile.getCreateTime());
			System.out.println("modifiedTime:" + newfile.getModifiedTime());
			System.out.println("deadline:"+ newfile.getDeadline());
			System.out.println("safeLevel:" + newfile.getSafeLevel());
			System.out.println("type:" + newfile.getType());
			System.out.println("userId:" + newfile.getUserId());
			String listfile=listFile(newfile.getParentid(), newfile.getUserId());
			resultReturn.setWddescjson(listfile);
			resultReturn.setUserId(newfile.getUserId());
			resultReturn.setParentid(newfile.getParentid());

		}
		return resultReturn;
	}

    private  void copy(File src, File dst)  {
        try  {
           InputStream in = null ;
           OutputStream out = null ;
           BufferedInputStream l;
            try  {                
               in = new BufferedInputStream( new FileInputStream(src),64);
               out = new BufferedOutputStream( new FileOutputStream(dst), 64);
                byte [] buffer = new byte [64];
                while (in.read(buffer) > 0 )  {
                   out.write(buffer);
               } 
            } finally  {
                if ( null != in)  {
                   in.close();
               } 
                 if ( null != out)  {
                   out.close();
               } 
           } 
        } catch (Exception e)  {
           e.printStackTrace();
       } 
   }

	@Override
	public InputStream downLoad(long fileId) throws IOException {
		HdfsFile dfsfile=filedao.findFile(fileId);
		String tmpFile="tmp"+fileId;//构造临时文件夹
		File dst =new File("E:/hadoop/temp"+tmpFile);//建立临时的文件
		downLoadAction down =new downLoadAction();
		down.copyFileFromFs(dfsfile.getFileUrl(), dst, false);
		FileInputStream returnIn=new FileInputStream(dst);
		dst.deleteOnExit();
		return returnIn;
	}

	@Override
	public HdfsMemory insertMemory(int userId,int type) {
		// TODO Auto-generated method stub
		int total = 0;
		if (type == 2) total = 1000000;
		if (type == 1) total = 10000000;
		HdfsMemory memory = new HdfsMemory(type,total,0);
		memory.setMemoryId(userId);
		memory = filedao.saveMemory(memory);
		return memory;
		
	} 
	
	@Override
	public void changeMemory(int memoryId,int type){
		int total = 0;
		if (type == 2) total = 1000000;
		if (type == 1) total = 10000000;
		HdfsMemory memory = new HdfsMemory(type,total,0);
		filedao.updateMemory(memory);
	}
	
	@Override
	public void updateMemory(HdfsMemory memory){
		filedao.updateMemory(memory);
	}
	
	
	@Override
	public void deleteMemory(int memoryId){
		filedao.deleteMemory(memoryId);
	}
	
	@Override
	public HdfsMemory getMemory(int memoryId){
		return filedao.getMemory(memoryId);
	}
}
