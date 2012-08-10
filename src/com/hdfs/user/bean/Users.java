package com.hdfs.user.bean;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private String phone;
	private Integer memoryId;
	private Integer role;
	private String rootDirectory;
	private String publicKey;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	

	
	public Users(String username){
		this.username = username;
	}
	
	public Users(String username, String rootDirectory) {
		this.username = username;
		this.rootDirectory = rootDirectory;
	}

	/** full constructor */
	public Users(String username, String password, String email, String phone,
			Integer memoryId, Integer role, String rootDirectory) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.memoryId = memoryId;
		this.role = role;
		this.rootDirectory = rootDirectory;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userid){
		this.userId = userid;
	}

	public void setUserId(Object object) {
		this.userId = (Integer) object;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public void setUsername(Object object) {
		this.username = (String) object;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getMemoryId() {
		return this.memoryId;
	}

	public void setMemoryId(Integer memoryId) {
		this.memoryId = memoryId;
	}

	public Integer getrole() {
		return this.role;
	}

	public void setrole(Integer role) {
		this.role = role;
	}

	public String getRootDirectory() {
		return this.rootDirectory;
	}

	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}