<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<li>用户编号：<s:property value="user.userId"/></li>
	<li>用 户 名：<s:property value="user.username"/></li>
	<li>用户邮箱：<s:property value="user.email"/></li>
	<li>联系电话：<s:property value="user.phone"/></li>
	<s:debug></s:debug>
		
</body>
</html>