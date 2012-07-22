<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>

    
    <title>邮箱激活注册</title>
  </head>
  
  <body>
     激活邮件正在发送中....请前往邮箱进行激活<font id="second" color="red">10</font>秒后程序跳往注册页面
        <% response.setHeader("refresh","10;url='/SSH'");%>
       
  </body>
  <script type="text/javascript">
var second=10;
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
