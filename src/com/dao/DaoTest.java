package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoTest {
	Connection con = null;

	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/hdfs?useUnicode=true&amp;characterEncoding=gbk";
		String user = "root";
		String password = "root";
		try {
			if (conn == null) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, user, password);
			}
			System.out.println("初始化成功");
		} catch (Exception e) {
			System.out.println("连接失败");
			return null;
		} finally {
			url = null;
			user = null;
			password = null;
		}
		return conn;
	}

	public ArrayList<Nodes> getNodeInfo() {
		String sql = "select  file_id,parentid ,hrefAddress ,file_name ,user_id from hdfs_file";
		PreparedStatement pre = null;
		Connection conn = null;
		System.out.println("初始化开始");
		conn = getConnection();
		ResultSet rs = null;
		ArrayList<Nodes> list = new ArrayList<Nodes>();
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				Nodes node = new Nodes();
				node.setHrefAddress(rs.getString("hrefAddress"));
				node.setFile_id(rs.getString("file_id"));
				node.setParentid(rs.getString("parentid"));
				node.setFile_name(rs.getString("file_name"));
				node.setUser_id(rs.getString("user_id"));
				list.add(node);
			}
			System.out.println("连接成功");
			rs.close();
			pre.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pre = null;
			conn = null;
			rs = null;
		}
		return list;
	}

}
