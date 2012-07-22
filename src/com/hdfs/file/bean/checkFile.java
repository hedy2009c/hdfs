package com.hdfs.file.bean;

public class checkFile {

	private String name;//�ļ�����Ŀ¼������
	private long id;//�ļ�����Ŀ¼��ID
	private long parentId;//��Ŀ¼��ID
	private long size;//�ļ�����Ŀ¼�Ĵ�С��kb
	private String url;//�ļ�����Ŀ¼��·��
	private String createTime;//����ʱ��
	private String modifiedTime;//�޸�ʱ��
	private int saveLevel;//���漶��
	private String time;//����ʱ��
	private int type;//�ļ����ͣ�0���Ŀ¼
    private int day;//ʣ������
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public int getSaveLevel() {
		return saveLevel;
	}
	public void setSaveLevel(int saveLevel) {
		this.saveLevel = saveLevel;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}

    }
