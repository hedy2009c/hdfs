<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<center>
		 <p>指定的用户不存在，删除失败<font id="second" color="red">5</font>秒后返回删除用户界面</p>
  <br>
  <a href = "/hdfs/admin/user_del.jsp" >点此直接返回</a>
        <% response.setHeader("refresh","5;url='/hdfs/admin/user_del.jsp'");%>
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