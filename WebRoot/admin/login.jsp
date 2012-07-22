<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=us-ascii" />
	<title>Hello! Admin-www.mianfeimoban.com</title>
	
	<link type="text/css" href="style.css" rel="stylesheet" />
	<link type="text/css" href="css/login.css" rel="stylesheet" />
	
	<script type='text/javascript' src='js/jquery-1.4.2.min.js'></script>	<!-- jquery library -->
	<script type='text/javascript' src='js/iphone-style-checkboxes.js'></script> <!-- iphone like checkboxes -->

	<script type='text/javascript'>
		jQuery(document).ready(function() {
			jQuery('.iphone').iphoneStyle();
		});
	</script>
	
	<!--[if IE 8]>
		<script type='text/javascript' src='js/excanvas.js'></script>
		<link rel="stylesheet" href="css/loginIEfix.css" type="text/css" media="screen" />
	<![endif]--> 
 
	<!--[if IE 7]>
		<script type='text/javascript' src='js/excanvas.js'></script>
		<link rel="stylesheet" href="css/loginIEfix.css" type="text/css" media="screen" />
	<![endif]--> 
	
</head>
<body  >

	<div id="background" >
		<div id="container">
		
			<div id="logo">
				<img src="assets/hadoop.png" alt="Logo" />
			</div>
			<div id="box"> 
				<s:form action="adminlogin" namespace="/user">
					<div class="one_half">
						<p><input name="user.username" value="用户名" class="field" onBlur="if (jQuery(this).val() == '') { jQuery(this).val('username'); }" onClick="jQuery(this).val('');" /></p>
						<font color="red">
		<s:fielderror>
		<s:param value="users.userName"></s:param>
		</s:fielderror>
		</font>
					</div>
					<div class="one_half last">
						<p><input type="password" name="user.password" value="password" class="field" onBlur="if (jQuery(this).val() == '') { jQuery(this).val('asdf1234'); }" onClick="jQuery(this).val('');">	</p>
						<p><input type="submit" value="Login" class="login" /></p>
					</div>
				</s:form>
		</div> 
		
		</div>
	</div>
</body>
</html>