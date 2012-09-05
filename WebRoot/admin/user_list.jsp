<%@ page pageEncoding="UTF-8" import="java.lang.*" import="java.sql.*"
	import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	int pagenum = 1;
	String strpagenum = request.getParameter("currentPage");
	if (strpagenum == null)
		pagenum = 1;
	else
		pagenum = java.lang.Integer.parseInt(strpagenum);
	if (pagenum < 1)
		pagenum = 1;
%>
<html>
	<head>
		<title>分页</title>
	</head>
	<body>
	<%if (session.getAttribute("userName")==null||session.getAttribute("userName").equals("")) response.sendRedirect("hdfs/admin/login.jsp");%>
		<center>
			<table border="1px" bordercolor="#000000" cellspacing="0px"
				style="border-collapse: collapse; width:600px;"
				style="text-align :center">
				<thead>
					<tr>
					<td>
						用户ID
					</td>
					<td>
						用户名
					</td>
					<td>
						密码
					</td>
					<td>
						电子邮件
					</td>
					<td>
						联系电话
					</td>
					<td>
						容量ID
					</td>
					
					<td>
						是否被审核
					</td>
					<td>
						删除用户
					</td>
					<td>
						审核
					</td>
					</tr>
				</thead>
				<s:iterator id="row" value="records">
					<tr>
						<s:iterator id="field" value="fieldList">
							<td>
								<s:property value="#row[#field]" />
							</td>

						</s:iterator>
						
						<td>
							<a href="userdelete?user.userId=<s:property value="#row.userId"/>">删除</a>
						</td>
						<td>
							<a href="userchangecheck?user.userId=<s:property value="#row.userId"/>">通过</a>
						</td>
					</tr>
				</s:iterator>

			</table>
			<p />



				<%
					if (pagenum > 1) {
				%><a href="listuser.action?currentPage=<%=pagenum - 1%>">上一页</a>
				<%
					}
				%>
				<a href="listuser.action?currentPage=<%=pagenum + 1%>">下一页</a>
		</center>
	</body>
</html>