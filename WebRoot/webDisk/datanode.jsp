<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'datanode.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
	<center>
	</br></br></br>
		<table border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">
			<tr>
				<td>
				<center>节点 1 &nbsp;<s:property value="data[0]"/></center>
				</td>

			</tr>
			<tr>
				<td>
					<s:property value="data[0]"/></br>
					<s:property value="data[1]"/></br>
					<s:property value="data[2]"/></br>
					<s:property value="data[3]"/></br>
					<s:property value="data[4]"/></br>
					<s:property value="data[5]"/></br>
					<s:property value="data[6]"/></br>
					<s:property value="data[7]"/></br>
					<s:property value="data[8]"/></br>
				</td>

			</tr>
		</table>

		
		</center>

	</body>
</html>