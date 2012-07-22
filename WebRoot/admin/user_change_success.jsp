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
   
   <center>
     <p>用户信息修改成功，<font id="second" color="red">5</font>秒后返回用户列表</p>
  <br>
  <a href = "/hdfs/admin/ListUser?currentPage=1" >点此直接返回</a>
        <% response.setHeader("refresh","5;url='/hdfs/admin/ListUser?currentPage=1'");%>
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
