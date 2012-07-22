<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Register User</title>
	</head>
	<body>
	<center>
		
			<h1>
				<font color=red>用户注册</font>
			</h1>

			<s:form action="webregister" namespace="/userweb">
				<s:textfield name="users.userName" id="name" label="%{getText('name')}"
					required="true"></s:textfield>
				<s:fielderror>
					<s:param value="users.userName"></s:param>
				</s:fielderror>

				<s:password name="users.passWord" required="true" id="password"
					label="%{getText('password')}"></s:password>
				<s:password required="true" id="rpassword" key="rpassword"></s:password>
				<s:textfield name="users.email" id="email" key="email"></s:textfield>
				<s:textfield name="users.phone" id="phone" key="phone"></s:textfield>
				<s:textfield name="validationCode" key="validatecode">
				</s:textfield>
				<s:fielderror>
					<s:param value="validationCode"></s:param>
				</s:fielderror>
				<s:submit key="register"></s:submit>
				<s:reset key="reset">
				</s:reset>
			</s:form>
			用户验证码：
			<img id="img" src="userweb/validate_code.action" />
			<a href="#" onclick="refresh()">重新生成验证码</a>
	</center>
	</body>
	<script type="text/javascript">
  function refresh()
  {
   document.getElementById("img").src="userweb/validate_code.action?"+Math.random();
  }
  <s:if> test="result!=null">alert('<s:property value="result" escape="false"/>');</s:if>
  </script>
</html>
