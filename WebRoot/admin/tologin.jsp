<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>管理员登陆</title>
    

  </head>
  
  <body>
	<h1>管理员登录</h1>
	<hr>
	<center>
	<s:form action="adminlogin" namespace="/user">
		<s:textfield name="user.username"  label="用户名"></s:textfield>
		<s:textfield name="user.password" label="密  码"></s:textfield>
		<font color="red">
		<s:fielderror>
		<s:param value="users.userName"></s:param>
		</s:fielderror>
		</font>
		<s:submit type="button" value="登陆" ></s:submit>
		<s:reset type="button" value="重置"></s:reset>
	</s:form>

  </center>
  </body>

</html>
