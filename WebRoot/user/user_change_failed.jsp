<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath(); 
// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
pageContext.setAttribute("basePath",basePath); 
%> 
<html>
  <head>
    
    <title>My JSP 'user_del.jsp' starting page</title>
   
    <meta http-equiv='refresh' content='2; url=user_profile.jsp'>
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
     <p>修改失败，<font id="second" color="red">5</font>秒后返回您的用户资料</p>
     <% response.setHeader("refresh","5;url='../user_profile.jsp'");%>
  <br>
  <a href = "${pageScope.basePath}userweb/user_profile.jsp" >点此直接返回</a>   
  
   </center>    
  </body>
  <script type="text/javascript">
var second=5;
var timer;
function change()
{
    second--;

 if(second>-1)
 {
     document.getElementById("second").innerHTML=second;
  timer = setTimeout('change()',1000);
 }
 else
 {
     clearTimeout(timer);
 }
}
timer = setTimeout('change()',1000);
</script>
    
  
</html>
