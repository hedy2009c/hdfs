<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    


  </head>
  
  <body>
		<input type="hidden" id="fileId"
		value="<s:property value="fileId" />"/>
		<input type="hidden" id="filename"
		value="<s:property value="filename" />"/>
		
		<p>该文件为加密文件<br>
		请上传privateKey文件，用于解密操作</p>
		
		<form id ="uploadPrivateKey" action="decryptFile" method="post" enctype="multipart/form-data" >
			
			<input type="file" name="privateKey" />
			<input type="hidden" name="fileId"  value=${requestScope.fileId} />
			<input type="hidden" name="filename"  value =${requestScope.filename} />
			<input type="submit" value="submit">
		
		</form>
										
  </body>
</html>
