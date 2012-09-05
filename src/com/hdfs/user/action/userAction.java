package com.hdfs.user.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.hdfs.comm.util.BaseAction;
import com.hdfs.comm.util.JsonUtil;
import com.hdfs.comm.util.pathToId;
import com.hdfs.file.bean.HdfsFile;
import com.hdfs.file.bean.HdfsMemory;
import com.hdfs.file.service.fileService;
import com.hdfs.user.bean.Users;
import com.hdfs.user.service.userService;
import com.opensymphony.xwork2.ActionContext;

public class userAction extends BaseAction implements ServletRequestAware,
		SessionAware {
	public fileService fileservice;
	public userService userservice;
	private List<HdfsFile> filelist;
	public Users user = new Users();
	private HdfsMemory memory = new HdfsMemory();
	private HttpServletRequest request;
	public String initJson = null;/*
								 * "{'catalogs':[{'parentId':0,'fileId': 2001,'fileName':'����','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'false','children':[{'parentId':2001,'fileId': 20011,'fileName':'����','size':120000,'fileType':'5','createTime':'2011-2-22','isLeaf':'true'},{'parentId':2001,'fileId': 20012,'fileName':'������','size':120000,'fileType':'5','createTime':'2011-2-22','isLeaf':'true'}]},"
								 * +
								 * "{'parentId':0,'fileId': 2002,'fileName':'ͼƬ','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'false','children':[{'parentId':2002,'fileId': 20012,'fileName':'����','size':120000,'fileType':'3','createTime':'2011-2-22','isLeaf':'true'},{'parentId':2002,'fileId': 20022,'fileName':'������','size':120000,'fileType':'3','createTime':'2011-2-22','isLeaf':'true'}]},"
								 * +
								 * "{'parentId':0,'fileId': 2003,'fileName':'�����ĵ�','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 2004,'fileName':'���','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 2005,'fileName':'��Ϸ','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'false','children':[{'parentId':2005,'fileId': 20051,'fileName':'�й���Ϸ','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'false','children':[{'parentId':20051,'fileId': 200511,'fileName':'ħ��','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'true'},{'parentId':20051,'fileId': 200512,'fileName':'������԰','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'true'},{'parentId':20051,'fileId': 200513,'fileName':'ֲ��԰','size':120000,'fileType':'0','createTime':'2011-2-22','isLeaf':'false','children':[{'parentId':200513,'fileId': 2005131,'fileName':'����','size':120000,'fileType':'6','createTime':'2011-2-22','isLeaf':'true'},{'parentId':200513,'fileId': 2005132,'fileName':'����2','size':120000,'fileType':'8','createTime':'2011-2-22','isLeaf':'true'},{'parentId':200513,'fileId': 2005133,'fileName':'����3','size':120000,'fileType':'7','createTime':'2011-2-22','isLeaf':'true'}]}]}]},"
								 * +
								 * "{'parentId':0,'fileId': 2006,'fileName':'������','size':120000,'fileType':'1','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 2007,'fileName':'�����ĵ�','size':120000,'fileType':'2','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 2008,'fileName':'�����ĵ�','size':120000,'fileType':'3','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 2009,'fileName':'�����ĵ�','size':120000,'fileType':'4','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20010,'fileName':'�����ĵ�','size':120000,'fileType':'5','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20011,'fileName':'�����ĵ�','size':120000,'fileType':'6','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20012,'fileName':'�����ĵ�','size':120000,'fileType':'7','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20013,'fileName':'�����ĵ�','size':120000,'fileType':'8','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20014,'fileName':'�����ĵ�','size':120000,'fileType':'9','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20015,'fileName':'�����ĵ�','size':120000,'fileType':'10','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20016,'fileName':'�����ĵ�','size':120000,'fileType':'11','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20013,'fileName':'�����ĵ�','size':120000,'fileType':'8','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20014,'fileName':'�����ĵ�','size':120000,'fileType':'9','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20015,'fileName':'�����ĵ�','size':120000,'fileType':'10','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20016,'fileName':'�����ĵ�','size':120000,'fileType':'11','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20013,'fileName':'�����ĵ�','size':120000,'fileType':'8','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20014,'fileName':'�����ĵ�','size':120000,'fileType':'9','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20015,'fileName':'�����ĵ�','size':120000,'fileType':'10','createTime':'2011-2-22','isLeaf':'true'},"
								 * +
								 * "{'parentId':0,'fileId': 20016,'fileName':'�����ĵ�','size':120000,'fileType':'11','createTime':'2011-2-22','isLeaf':'true'}"
								 * + "]}";
								 */
	public JSONObject jsonresult = new JSONObject();

	public String getInitJson() {
		return initJson;
	}

	public void setInitJson(String initJson) {
		this.initJson = initJson;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public userService getUserservice() {
		return userservice;
	}

	public void setUserservice(userService userservice) {
		this.userservice = userservice;
	}

	public fileService getFileservice() {
		return fileservice;
	}

	public void setFileservice(fileService fileservice) {
		this.fileservice = fileservice;
	}

	/**
	 * �����û�����Ϣ
	 * 
	 * @return
	 */
	public String updateUser() {
		ArrayList<Users> list = new ArrayList();
		list.add(user);
		list.add(user);

		JSONArray json = JsonUtil.toJSONArray(list);
		JSONUtils.valueToString(json);
		System.out.println(JSONUtils.valueToString(json));
		this.userservice.updateUser(user);
		return SUCCESS;
	}

	public String login() throws Exception {

		user = this.userservice.findByName(user);
		// System.out.println("thisuser"+user);
		System.out.println("checkuser="
				+ this.userservice.getcheckuserById(this.user));
		System.out.println("111111111111111111111111");
		// System.out.println("phone="+user.getPhone());
		// System.out.println("username="+user.getUsername());
		if (this.userservice.findById(this.user) == null) {

			this.addFieldError("users.userName", "用户名或者密码错误！");
			this.user.setPassword(null);
			return INPUT;
		}

		else if (this.userservice.getcheckuserById(this.user) == null) {

			System.out.println("resultcheck=" + getUser().getCheckuser());
			this.addFieldError("users.checkuser", "该帐号未通过验证");
			return INPUT;
		}

		else {
			filelist = fileservice.listAllFile();
			{
				for (HdfsFile hdfsfile : filelist) {
					if (hdfsfile.getDeadline() == null
							|| hdfsfile.getDeadline().equals("")) {
						continue;
					} else {
						System.out.println(hdfsfile.getDeadline());
						System.out.println(new Date());
						if (hdfsfile.getDeadline().before(new Date())) {
							user.setUserId(new Long(hdfsfile.getUserId())
									.intValue());
							user = this.userservice.findById(user);
							fileservice.deleteFile(hdfsfile.getFileId(),
									fileservice.getMemory(user.getMemoryId()));
						}
					}
				}
			}
			user = this.userservice.findById(this.user);
			memory = fileservice.getMemory(user.getMemoryId());
			System.out.println(memory.getMemoryused());
			ActionContext ac = ActionContext.getContext();// 获得ActionContext
			Map app = ac.getApplication();
			ac.getSession().put("userid", user.getUserId());// 把登录用户放入session中
			ac.getSession().put("userName", user.getUsername());
			double used = memory.getMemoryused();
			int i = 0;
			while (used >= 1000) {
				used = used / 1000;
				i++;
			}
			if (i == 0)
				ac.getSession().put("used", used + "K");
			if (i == 1)
				ac.getSession().put("used", used + "M");
			if (i == 2)
				ac.getSession().put("used", used + "G");

			int total = memory.getTotalmemory();
			int j = 0;
			while (total >= 1000) {
				total = total / 1000;
				j++;
			}
			if (j == 0)
				ac.getSession().put("total", total + "K");
			if (j == 1)
				ac.getSession().put("total", total + "M");
			if (j == 2)
				ac.getSession().put("total", total + "G");

			ac.getSession().put("root", user.getRootDirectory());
			return SUCCESS;
		}
	}

	public String adminlogin() throws Exception {

		if (this.userservice.findById(this.user) == null) {
			this.addFieldError("users.userName", "用户名或者密码错误！");
			this.user.setPassword(null);
			return INPUT;
		}

		else {
			user = this.userservice.findById(this.user);
			if (user.getRole() != 2) {
				this.addFieldError("users.userName", "您不是管理员身份！");
				return INPUT;
			}
			ActionContext ac = ActionContext.getContext();// 获得ActionContext
			Map app = ac.getApplication();
			ac.getSession().put("userid", user.getUserId());// 把登录用户放入session中
			ac.getSession().put("userName", user.getUsername());
			return SUCCESS;
		}
	}

	public String register() throws Exception {
		// 根据用户名的哈希码设定rootId
		String rootId = String.valueOf(pathToId.ParsepathToId(user
				.getUsername()));
		user.setRootDirectory(rootId);
		this.user = this.userservice.save(user);
		System.out.println(user.getUsername() + "    " + user.getUserId());

		// 在数据库中为该注册用户创建一个HdfsFile记录，用来表示该用户的根目录
		// 在hdfs文件系统中为该用户创建一个根目录
		fileservice.rootmkdir(47, user.getUsername(), user.getUserId()
				.longValue(), Long.parseLong(rootId));

		// 创建该用户对应的存储空间
		HdfsMemory memory = fileservice.insertMemory(user.getUserId(), 2);

		// 设置用户关于存储空间Id字段
		user.setMemoryId(memory.getMemoryId());
		userservice.updateUser(user);
		if (this.user == null) {
			return INPUT;
		}

		return SUCCESS;
	}

	public void upgrade(int memoryId) {
		fileservice.changeMemory(memoryId, 1);
	}

	public String update() throws Exception {
		ActionContext ac = ActionContext.getContext();// 获得ActionContext
		Map app = ac.getApplication();
		this.user.setUserId(ac.getSession().get("userid"));
		this.user.setUsername(ac.getSession().get("userName"));
		this.user.setRole(1);
		if (this.user.getPassword() == null) {
			this.user.setPassword(this.userservice.findByName(user)
					.getPassword());
		}
		if (this.user.getRootDirectory() == null) {
			this.user.setRootDirectory(this.userservice.findByName(user)
					.getRootDirectory());
		}
		if (this.user.getMemoryId() == null) {
			this.user.setMemoryId(this.userservice.findByName(user)
					.getMemoryId());
		}
		if (this.user.getEmail() == null) {
			this.user.setEmail(this.userservice.findByName(user).getEmail());
		}
		if (this.user.getPhone() == null) {
			this.user.setPhone(this.userservice.findByName(user).getPhone());
		}
		this.userservice.updateUser(this.user);
		return SUCCESS;
	}

	public String userprofile() throws Exception {

		ActionContext ac = ActionContext.getContext();// 获得ActionContext
		Map app = ac.getApplication();

		String username = (String) ac.getSession().get("userName");

		user = userservice.findByName(new Users(username));
		return SUCCESS;

	}

	public String deleteuser() throws Exception {
		Users u = userservice.find(user);
		if (u == null)
			return ERROR;
		userservice.delete(u);
		fileservice.deleteFile(Long.parseLong(u.getRootDirectory()),
				fileservice.getMemory(u.getMemoryId()));
		fileservice.deleteMemory(u.getMemoryId());
		return SUCCESS;
	}

	public String modifyuser() throws Exception {
		this.user.setRole(1);
		if (this.user.getPassword() == null) {
			this.user.setPassword(this.userservice.findByName(user)
					.getPassword());
		}
		if (this.user.getRootDirectory() == null) {
			this.user.setRootDirectory(this.userservice.findByName(user)
					.getRootDirectory());
		}
		if (this.user.getEmail() == null) {
			this.user.setEmail(this.userservice.findByName(user).getEmail());
		}
		if (this.user.getMemoryId() == null) {
			this.user.setMemoryId(this.userservice.findByName(user)
					.getMemoryId());
		}
		if (this.user.getPhone() == null) {
			this.user.setPhone(this.userservice.findByName(user).getPhone());
		}
		Users u = userservice.find(user);
		if (u == null)
			return ERROR;
		else {
			userservice.updateUser(user);
			return SUCCESS;
		}
	}

	public String changeusercheck() throws Exception {

		user = this.userservice.find(user);
		System.out.println("username=" + user.getUsername());
		System.out.println("checkuser=" + user.getCheckuser());
		this.user.setRole(1);
		if (this.user.getCheckuser() == null) {
			this.user.setCheckuser(1);
		}

		Users u = userservice.find(user);
		System.out.println("my test=" + user);
		if (u == null)
			return ERROR;
		else {
			userservice.updateUser(user);
			return SUCCESS;
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public void setMemory(HdfsMemory memory) {
		this.memory = memory;
	}

	public HdfsMemory getMemory() {
		return memory;
	}

	public void setFilelist(List<HdfsFile> filelist) {
		this.filelist = filelist;
	}

	public List<HdfsFile> getFilelist() {
		return filelist;
	}
}
