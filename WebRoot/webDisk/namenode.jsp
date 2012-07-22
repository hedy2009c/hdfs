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
	</br>
	</br>
	</br>
		<table border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">
			<tr>
				<td>
					系统url
				</td>
				<td>
					<s:property value="url"/>
				</td>
				<td>
					总空间
				</td>
				<td>
					<s:property value="totalspace"/>G
				</td>
			</tr>
			<tr>
				<td>
					已使用空间
				</td>
				<td>
					<s:property value="spaceused"/>M
				</td>
				<td>
					剩余空间
				</td>
				<td>
					<s:property value="remain"/>G
				</td>
			</tr>
			<tr>
				<td>
					默认块大小
				</td>
				<td>
					<s:property value="defaultblocksize"/>
				</td>
				<td>
					损坏块数目
				</td>
				<td>
					<s:property value="badblock"/>
				</td>
			</tr>
			<tr>
				<td>
					已复制块数目
				</td>
				<td>
					<s:property value="blockcopy"/>
				</td>
				<td>
					丢失块数目
				</td>
				<td>
					<s:property value="lossblock"/>
				</td>
			</tr>
			<tr>
				<td>
					备份数
				</td>
				<td>
					<s:property value="replinum"/>
				</td>
				<td>
					使用百分比
				</td>
				<td>
					<s:property value="percent"/>
				</td>
			</tr>
		</table>
		</center>
	</body>
</html>
