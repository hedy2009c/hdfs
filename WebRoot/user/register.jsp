<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Register User</title>
	</head>
	<body>
	<center>
		
			<h1>
				<font color=b>用户注册</font>
			</h1>
--------------------------------------------------------------------------------------------------------
<br>
			<s:form action="register" namespace="/user">
				<s:textfield name="user.username" id="name" label="用户名"
					required="true"></s:textfield>
				<s:password name="user.password" required="true" id="password"
					label="密  码"></s:password>
				<s:password required="true" id="rpassword"  label="重复密码"></s:password>
				<s:textfield name="user.email" id="email" label="email"></s:textfield>
				<s:textfield name="user.phone" id="phone" label="联系电话"></s:textfield>
				<s:submit value="提交" key="register"></s:submit>
				<s:reset value="重置" key="reset">
				</s:reset>
			</s:form>
	</center>
	</body>
</html>
