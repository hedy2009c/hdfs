<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>初次使用加密文件上传功能</title>
    
  </head>
  
  <body>
  	userId: ${requestScope.userId}<br>
    <p style="color:red">
    初次使用加密功能，需要生成密钥对，并保存私钥到本机
    </p>
    <form action = "generateKeyPair">
    		<input type="hidden"  name="userId"  value="<%=session.getAttribute("userid")%>" />
    		<input type="submit" value="生成密钥对" >
    </form>
  </body>
</html>
