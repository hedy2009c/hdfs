package com.hdfsTools.action;

import org.apache.hadoop.conf.Configuration;

public class BaseAction {

	protected Configuration conf = new Configuration();

	public BaseAction() {
		this.conf.addResource("hdfs-site.xml");
		System.out.println("初始化父类");
	}

	/**
	 * url,example: hdfs://localhost:8889
	 */
	/*
	 * public BaseAction(String fsUrl){ this.conf.addResource("hdfs-site.xml");
	 * this.conf.set("fs.default.name", fsUrl);//
	 * this.conf.set("dfs.replication.min", "3");//
	 * this.conf.set("dfs.replication", "4");//
	 * 
	 * System.out.println("初始化开始"); }
	 */

	public BaseAction(Configuration conf) {
		this.setConf(conf);
		System.out.println("Configuration初始化");
	}

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	public void setDefaultName(String fsUrl) {
		this.conf.set("fs.default.name", fsUrl);//

	}

}
