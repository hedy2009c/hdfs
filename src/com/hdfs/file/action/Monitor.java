package com.hdfs.file.action;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.*;
import com.hdfsTools.action.BaseAction;

public class Monitor extends BaseAction{
	private URI url;
	private long badblock;
	private long defaultblocksize;
	private long replinum;
	private long lossblock;
	private long totalspace;
	private long spaceused;
	private long blockcopy;
	private long percent;
	private long remain;
	private String datanode;
	private String data[];
	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	private int k;
	public Monitor() {
		super();
	}

	public URI getUrl() {
		return url;
	}
	public void setUrl(URI url) {
		this.url = url;
	}
	public long getBadblock() {
		return badblock;
	}
	public void setBadblock(long badblock) {
		this.badblock = badblock;
	}
	public long getDefaultblocksize() {
		return defaultblocksize;
	}
	public void setDefaultblocksize(long defaultblocksize) {
		this.defaultblocksize = defaultblocksize;
	}
	public long getReplinum() {
		return replinum;
	}
	public void setReplinum(long replinum) {
		this.replinum = replinum;
	}
	public long getLossblock() {
		return lossblock;
	}
	public void setLossblock(long lossblock) {
		this.lossblock = lossblock;
	}
	public long getTotalspace() {
		return totalspace;
	}
	public void setTotalspace(long totalspace) {
		this.totalspace = totalspace;
	}
	public long getSpaceused() {
		return spaceused;
	}
	public void setSpaceused(long spaceused) {
		this.spaceused = spaceused;
	}
	public long getBlockcopy() {
		return blockcopy;
	}
	public void setBlockcopy(long blockcopy) {
		this.blockcopy = blockcopy;
	}
	public long getPercent() {
		return percent;
	}
	public void setPercent(long percent) {
		this.percent = percent;
	}
	public long getRemain() {
		return remain;
	}
	public void setRemain(long remain) {
		this.remain = remain;
	}
	
	public String nameNode(){
		FileSystem fs;
		try {
			fs = FileSystem.get(getConf());
		
	//	fs.
		DistributedFileSystem dfs = (DistributedFileSystem) fs;
		dfs.getCorruptBlocksCount();
		url = dfs.getUri();
		badblock = dfs.getCorruptBlocksCount();
		defaultblocksize = dfs.getDefaultBlockSize();
		replinum = dfs.getDefaultReplication();
		lossblock = dfs.getMissingBlocksCount();
		totalspace = dfs.getDiskStatus().getCapacity()/1024/1024/1024;
		spaceused = dfs.getDiskStatus().getDfsUsed()/1024/1024;
		blockcopy = dfs.getUnderReplicatedBlocksCount();
		percent = spaceused/totalspace;
		remain =dfs.getDiskStatus().getRemaining()/1024/1024/1024;
		System.out.println(url+"   1     "+replinum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
			return "fail";
		}
		return "success";
	}
	public String dataNode() throws IOException{
		FileSystem fs = FileSystem.get(getConf());
		DistributedFileSystem dfs = (DistributedFileSystem) fs;
		DatanodeInfo[] di = dfs.getDataNodeStats();
		for (int i = 0; i < di.length ; i++){
			datanode=di[i].getDatanodeReport();
			data = datanode.split("\n");
			k = data.length;
			for (int j = 0; j <data.length; j++)
				System.out.println(data[j]);
			
		}
		return "success";
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}

	public String getDatanode() {
		return datanode;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getK() {
		return k;
	}
	
	/*public static void main (String args[]){
		Monitor ma = new Monitor("hdfs://localhost:8889");
		try {
			ma.nameNode();
			System.out.println("---------------------------------------------------------");
			ma.dataNode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}