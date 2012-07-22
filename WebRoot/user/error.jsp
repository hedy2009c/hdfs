<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>无法访问的页面</title>
  </head>
  
  <body>
     访问的页面不存在<font id="second" color="red">5</font>秒后程序跳往主页面 
        <% response.setHeader("refresh","5;url='/SSH'");%>
       
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
