<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>网盘</title>
<link style="text/css" rel="stylesheet"
	href="/hdfs/webDisk/css/webDisk.css" />
<script type="text/javascript"><%--

	function toDisk(){
		var userId=document.getElementById("userId").value;
		var rootId=document.getElementById("rootId").value;
		var url="/hdfs/file/listFile?userId="+userId+"&currentId="+rootId;
	    document.getElementById("ifr").src=url;
	}
	--%></script>
<script type="text/javascript" src="dtree.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<link rel="stylesheet" href="/hdfs/webDisk/dtree.css" type="text/css"></link>
<%--<%response.setContentType("text/xml;charset=UTF-8"); %>
--%>
</head>
<body>
	<%
		if (session.getAttribute("userName") == null
				|| session.getAttribute("userName").equals(""))
			response.sendRedirect("/hdfs/user/login.jsp");
	%>
	<%--

		<input type="hidden" id="userId" value="2" />
		<input type="hidden" id="rootId" value="47" />
		--%>
	<%--<s:debug></s:debug>
		<%response.setContentType("text/xml;charset=UTF-8");%>
		--%>
	<div id="WDheader">
		<div style="text-align: right; margin-top: 55px; color: black;">
			你好,<%=session.getAttribute("userName")%>！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="logout.jsp">退出&nbsp;&nbsp;&nbsp;</a>
		</div>
	</div>
	<div id="WDbody">
		<div id="WDcatalogList">
			<div class="WDlist">
				容量：<%=session.getAttribute("used")%>/<%=session.getAttribute("total")%></div>
			<h3>
				<%=session.getAttribute("userName")%>的网盘
			</h3>

			<script type="text/javascript">
				 // alert(nodeId+"/"+parentId+"/"+nodeName+"/"+hrefAddress);
					tree = new dTree('tree');
					tree.add(47,-1,"文件系统","","","","","",false);//创建一个对象.
								$.ajax({ 
										url:'/hdfs/file/NodesPrint', 
										type:'post', //数据发送方式 
										dataType:'xml', //接受数据格式 
										error:function(json){
				   							     alert( "not lived!");
											  },
										async: false ,
										success: function(xml){
				 								    $(xml).find("node").each(function(){ 
														  var file_id=$(this).attr("file_id");  
					  									  var parentid=$(this).attr("parentid");  
														  var hrefAddress=$(this).attr("hrefAddress");  
														  var file_name=$(this).text(); 
														  var user_id = "<%=session.getAttribute("userid")%>"
														  var userid = $(this).attr("userid");
														  if (user_id == userid)
															
														 tree.add(file_id,parentid,file_name,
														  			"/hdfs/file/listFile?currentId="+file_id+"&userId=<%=session.getAttribute("userid")%>",
																		"",
																		"ifr",
																		"", "",
																		false);
												});
							}
						});

				document.write(tree);
			</script>
			</br> </br> </br>
			<script>
				tree1 = new dTree('tree1');
				tree1.add(0, -1, "用户功能", "", "", "ifr", "", "", false);
				tree1.add(1, 0, "用户资料", "/hdfs/user/userprofile", "", "ifr",
						"", "", false);
				tree1.add(2, 0, "修改资料", "/hdfs/user/user_change.jsp", "",
						"ifr", "", "", false);
				document.write(tree1);
			</script>
		</div>




		<div id="WDcatalogs">
			<iframe name="ifr" id="ifr" scrolling="no"
				style="width: 760px; height: 600px; margin-top: -2px; margin-left: -2px; border: 0px; overflow: hidden; float: left;"
				src="/hdfs/file/listFile?currentId=<%=session.getAttribute("root")%>&userId=<%=session.getAttribute("userid")%>"></iframe>
		</div>
	</div>
	<div id="WDfooter"></div>
</body>

</html>

