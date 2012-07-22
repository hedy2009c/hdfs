<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>网盘</title>
		<link style="text/css" rel="stylesheet"
			href="/hdfs/webDisk/css/webDisk.css" />
		<script type="text/javascript"><%--

	function toDisk(){
		var userId=document.getElementById("userId").value;
		var rootId=document.getElementById("rootId").value;
		var url="/hdfs/file/listFile?userId="+userId+"&currentId="+rootId;
	    document.getElementById("ifr").src=url;
	}
	--%></script>
		<script type="text/javascript" src="dtree.js"></script>
		<script type="text/javascript" src="jquery.js"></script>
		<link rel="stylesheet" href="/hdfs/webDisk/dtree.css" type="text/css"></link>
		<%--<%response.setContentType("text/xml;charset=UTF-8"); %>
--%>
	</head>
	<body>
		<%if (session.getAttribute("userName")==null||session.getAttribute("userName").equals("")) response.sendRedirect("hdfs/admin/login.jsp");%>
		<input type="hidden" id="userId" value="2" />
		<input type="hidden" id="rootId" value="47" />
		
		<div id="WDheader">
			<div style="text-align: right; margin-top: 55px; color: black;">你好,<%=session.getAttribute("userName")%>！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/hdfs/admin/logout.jsp">退出&nbsp;&nbsp;&nbsp;</a></div>
		</div>
		<div id="WDbody">
			<div id="WDcatalogList">
				<div class="WDlist"></div>
			
				
				
		
				<script>
				      tree1 = new dTree('tree1');
				      tree1.add(0,-1,"管理功能","","","ifr","","",false);
				      tree1.add(1,0,"系统监控","/hdfs/file/Monitor","","ifr","","",false);
				      tree1.add(2,0,"datanode监控","/hdfs/file/datanode","","ifr","","",false);
				      tree1.add(3,0,"用户管理","/hdfs/user/listuser?currentPage=1","","ifr","","",false);
				      document.write(tree1);
			    </script>
			</div>


			<div id="WDcatalogs">
				<iframe name="ifr" id="ifr" scrolling="no"
					style="width: 760px; height: 600px; margin-top: -2px; margin-left: -2px; border: 0px; overflow: hidden; float: left;"
					src="/hdfs/file/Monitor"></iframe>
			</div>
		</div>
		<div id="WDfooter"></div>
	</body>
	
</html>

