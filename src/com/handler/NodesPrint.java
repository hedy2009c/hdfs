package com.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dao.DaoTest;
import com.dao.Nodes;
public class NodesPrint{
	
	public void execute() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");   
        response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		System.out.println("调用了.........");
		DaoTest test = new DaoTest();
		ArrayList<Nodes> list=  test.getNodeInfo();
	    if(list!=null&&list.size()>0){
	    	out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        out.println("<nodes>");
	    	for(int i=0;i<list.size();i++){
	    		Nodes node = list.get(i);
	    		out.println("<node file_id='"+node.getFile_id()+"' parentid='"+node.getParentid()+"' hrefAddress='"+node.getHrefAddress()+"' userid='"+node.getUser_id()+"'>"+node.getFile_name()+"</node>");
	    		System.out.println(node.getFile_name());
	    	}
	    	out.println("</nodes>");
	    }
	    System.out.println("调用完成");
		
}



}
