<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function auto(){
var user=document.getElementById("ss").value;
ff(user);
alert(user);
}
</script>
</head>
<body onload="auto()">

<s:property value="user.phone"/><br/> 

<input type="hidden" value="<s:property value="user.phone"/>" id="ss"/>
<form action="updateUser" >
用户ID：<input name="user.userId" value="16"/><br/>
电话号码:<input name="user.phone" value="13751795888"/><br/>
根目录id：<input name="user.rootDirectory" value="1"/>

<input type="submit"  value="提交"/>
</form>
</body>
</html>