<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
  <head>
    
    <title>My JSP 'user_del.jsp' starting page</title>
    
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
    <s:form action="updateuser" namespace="/user">
    <s:textfield label="新用户密码" name="user.password" cssClass="input_list"/>
    <s:textfield label="新用户email" name="user.email" cssClass="input_list"/>
    <s:textfield label="新用户phone" name="user.phone" cssClass="input_list"/>
   
    <s:submit value="提交"/>
    </s:form>
  </body>
</html>
