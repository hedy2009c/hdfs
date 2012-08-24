<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网盘</title>
<link style="text/css" rel="stylesheet"
	href="../webDisk/css/webDisk.css" />
<link id="artDialogSkin" href="../webDisk/skin/aero/aero.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
#createDirForm,#uploadForm {
	width: 300px;
	height: 30px;
	padding: 20px;
	text-align: left;
}

#createDirForm input {
	width: 250px;
}

#uploadForm {
	width: 250px;
}
</style>
</head>


<body>
	<input type="hidden" id="userId"
		value="<%=session.getAttribute("userid")%>" />
	<input type="hidden" id="wddescjson"
		value="<s:property value="wddescjson"/>" /> <!--问Action值栈中的普通属性  -->
	<input type="hidden" id="currentId"
		value="<s:property value="currentId"/>" />
	<input type="hidden" id="rootid"
		value="<%=session.getAttribute("root")%>" />
	
	<div id="catalogs">

		<ul id="WDtoolBar">
			<li id="reBack"><a href="#" parentId="0">返回上一层</a>
			</li>
			<li><a href="#" command="makeDir" id="newDir">新建文件夹</a>
			</li>
			<li><a href="#" command="upload" id="upload">上传</a>
			</li>
			<!-- <li>排序</li> -->
		</ul>
		
		<ul id="catalogContent"> <!-- 用来显示网盘页面内容，由/hdfs/webDisk/js/wd.js插入html代码 -->

		</ul>
	</div>


	<div id="detailWin">
		<div class="data">
			<ul>
				<li class="name"><label>文件名：</label><span id="dataName"></span>
				</li>
				<li class="size"><label>文件大小：</label><span>kb</span>
				</li>
				<li class="time"><label>创建时间：</label><span></span>
				</li>
				<li class="modifiedTime"><label>修改时间：</label><span></span>
				</li>
				<li class="saveTime"><label>截止时间：</label><span></span>
				</li>
				<li class="savePath"><label>保存路径：</label><span></span>
				</li>
				<li class="saveLevel"><label>存储等级：</label><span></span>
				</li>
			</ul>
		</div>
		<div id="dirType" class="type10"></div>
	</div>
	
	<div id="menu">
		<ul>
			<li><a href="#" command="openFile">打开</a>
			</li>
			<li><a href="#" command="downloadFile">下载</a>
			</li>
			<li><a href="#" command="reNameFile">重命名</a>
			</li>
			<li><a href="#" command="removeFile">删除</a>
			</li>
			<li id="hideMenu"><a href="#" command="cancel">取消</a>
			</li>
		</ul>
	</div>
	
	<script type="text/javascript"
		src="/hdfs/webDisk/js/jquery-1.3.1.min.js"></script>
	
	<script type="text/javascript" src="/hdfs/webDisk/js/wd.js"></script>
	
	<script type="text/javascript" src="/hdfs/webDisk/js/artDialog.min.js"></script>
	
	<script>
		$(function() {

			function getParentId() {
				var id = 0
				var last = $('#WDpath').find('li.filePath:last-child');
				if (last.length == 1) {
					id = last.attr('fileId');
				} else {
					id = $('#currentId').val();
				}
				return id;
			}
			$('#newDir')
					.bind(
							'click',
							function(event) {

								//获取对象的坐标，让对话框在按钮附近弹出
								var parentId = getParentId();
								var _html = '<form id ="createDirForm" action="" method="post">'
										+ '<label>目录名:</label><input type="text" id="dirName" name="dirName"></input>'
										+ '<input type="hidden" name="parentID" id="parentID" value="'+parentId+'"></form>';
								var _x = event.clientX;
								var _y = event.clientY;
								var _this = this;
								artDialog({
									id : 'menu_4834783',
									content : _html,
									left : _x + 20,
									top : _y + 20,
									title : '创建目录'
								}, function() {
									var currentId = $('#parentID').val();
									var filename = $('#dirName').val();
									var userId = $('#userId').val();
									var url = "mkDir?currentId=" + currentId
											+ "&filename=" + filename
											+ "&userId=" + userId;
									window.location.href = url;

								})
								return false;
							});
			$('#upload')
					.bind(
							'click',
							function(event) {
								//获取对象的坐标，让对话框在按钮附近弹出
								var parentId = getParentId();
								var _x = event.clientX;
								var _y = event.clientY;
								var _this = this;
								var userId = $('#userId').val();
								var currentId = getParentId();
								var wddescjson = $('#wddescjson').val();
								var _html = '<form id ="uploadForm" action="uploadFile" method="post" enctype="multipart/form-data" style="height:80px">'
										+ '<input type="file" name="uploadFile" />'
										+ '</br>'
										+ '上传方式：'
										+ '<input type="radio" name="uploadType" checked value="0">普通上传</>'
										+ '<input type="radio" name="uploadType" value="1">加密上传</>'
										+ '</br>'
										+ '安全等级：'
										+ '<input type="radio" name="safelevel" checked value="1" >低</>'
										+ '<input type="radio" name="safelevel" value="2">中</>'
										+ '<input type="radio" name="safelevel" value="3">高</>'
										+ '</br>'
										+ '截止日期（YYYY-MM—DD）<input type="text" name="deadline" value="2014-01-01" onClick="jQuery(this).val(&quot;&quot;);"></>'
										+ '<input type="hidden" name="currentId" value="'+currentId+'"/>'
										+ '<input type="hidden" name="userId" value="'+userId+'" />'
										+ '</form>';

								artDialog({
									id : 'menu_4834783',
									content : _html,
									left : _x + 200,
									top : _y + 20,
									title : '上传文件'

								}, function() {
									$('#uploadForm')[0].submit();
								})
								return false;
							});

		});
	</script>

</body>
</html>