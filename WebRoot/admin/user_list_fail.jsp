<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<%if (session.getAttribute("userName")==null||session.getAttribute("userName").equals("")) response.sendRedirect("hdfs/admin/login.jsp");%>
	<center>
		 <p>获取列表失败，<font id="second" color="red">5</font>秒后返回主界面</p>
  <br>
  <a href = "./index.jsp" >点此直接返回</a>
        <% response.setHeader("refresh","5;url='./index.jsp'");%>
	</center>
</body>
<script>
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