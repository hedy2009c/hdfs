package com.hdfsTools.action;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import com.hdfsTools.dao.renameDao;
import com.hdfsTools.impl.renameImpl;

public class renameAction extends BaseAction {

	public renameAction() {
	}

	/**
	 * urlConfiguration
	 * 
	 * @param fsUrl
	 *            url,example: hdfs://localhost:8889
	 */
	/*
	 * public renameAction(String fsUrl){ super(fsUrl); }
	 */

	public renameAction(Configuration conf) {
		super(conf);
	}

	/**
	 * 
	 * @param src
	 *            ,,url
	 * @param dst
	 *            , ,url
	 * @return
	 * 
	 * @throws IOException
	 */
	public boolean rename(String src, String dst) throws IOException {

		renameDao rename = new renameImpl();

		return rename.rename(getConf(), src, dst);
	}

	public static void main(String args[]) throws IOException {
		renameAction rename = new renameAction();
		String src = "/user/gg.txt1";
		String dst = "/gg.txt1";
		rename.rename(src, dst);
	}
}
