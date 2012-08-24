<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>覆盖已有文件</title>
  </head>
  
  <body>
    <p style="color:red">
    已存在该文件！是否覆盖？
    </p>
    <form action = "cover">
    		<input type="radio" name="coverFile" value="yes" /> 覆盖
			<input type="radio" name="coverFile" value="no" /> 取消
    		<input type="hidden" name="currentId" value="<s:property value="currentId"/>"/>
    		<input type="hidden" name="filename" value="<s:property value="filename"/>"/>
    		<input type="hidden" name="absoluteFilePath" value="<s:property value="absoluteFilePath"/>"/>
    		<input type="hidden" name="safelevel" value="<s:property value="safelevel"/>"/>
    		<input type="hidden" name="deadline " value="<s:property value="deadline "/>"/>
    		<input type="hidden" name="userId" value="<s:property value="userId"/>"/>
    		<input type="hidden" name="uploadType" value="<s:property value="uploadType"/>"/>
    		<input type="hidden" name="fileId" value="<s:property value="fileId"/>"/>
    		<input type="submit" value="确定" >
    </form>
  </body>
</html>
