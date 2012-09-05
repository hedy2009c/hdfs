<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>网盘</title>

<link type="text/css" rel="stylesheet" href="../webDisk/css/webDisk.css" />
<link id="artDialogSkin" href="../webDisk/skin/aero/aero.css" rel="stylesheet" type="text/css" />

</head>
  
  <body>
<input type="hidden" id="userId"
		value="<s:property value="userId"/>" />
	<input type="hidden" id="wddescjson"
		value="<s:property value="wddescjson"/>" />
	<!--问Action值栈中的普通属性  -->
	<input type="hidden" id="currentId"
		value="<s:property value="currentId"/>" />
	<input type="hidden" id="rootid"
		value="<%=session.getAttribute("root")%>" />
	  
		<input type ="hidden" id="fileId" value="<s:property value="fileId" />"/>
		<input type="hidden" id="filename" value="<s:property value="filename" />"/>
		
		<div id="catalogs">
		<ul id="WDtoolBar">
			<li id="reBack"><a href="#" parentId="0">返回上一层</a></li>
		</ul>
			<p>该文件为加密文件<br>
		请上传privateKey文件，用于解密操作</p>
		
		<form id ="uploadPrivateKey" action="decryptFile" method="post" enctype="multipart/form-data" >
			
			<input type="file" name="privateKey" />
			<input type="hidden" name="fileId"  value=${requestScope.fileId} />
			<input type="hidden" name="filename"  value =${requestScope.filename} />
			<input type="hidden" name="userId"  value ="<s:property value="userId"/>" />
			<input type="hidden" name="wddescjson"  value ="<s:property value="wddescjson"/>" />
			<input type="hidden" name="currentId"  value ="<s:property value="currentId"/>" />
			<input type="hidden" name="rootid"  value ="<s:property value="rootid"/>" />
			<input type="submit" value="确定">
		
		</form>
		</div>
		
	

		<script type="text/javascript"
		src="/hdfs/webDisk/js/jquery-1.3.1.min.js"></script>

	<script type="text/javascript" src="/hdfs/webDisk/js/wd.js"></script>
						
  </body>
</html>


