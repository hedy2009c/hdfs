/*
 * 该js文件是用来控制webDisk/disk.jsp页面里面的元素的触发事件等等。
 */

$(function(){
		var pathBox = $('#WDpath');
		var json = $('#wddescjson').val();
		var currentId = $('#currentId').val();
		var webDiskJson = eval('('+json+')');
		var filterDir = [];
		var directoryTpl = [
							'<li style="display:none;" fileId="{id}" fileName="{name}" time="{time}" day="{day}" saveLevel="{saveLevel}" modifiedTime="{modifiedTime}" parentId="{parentId}" fileType="{type}" >',
							'<div class="floderIcon"><a href="{url}" ><img src="./images/folder.gif" /></a><div>',
							'<div class="floderName" style="width:80px;height:30;word-wrap: break-word; overflow: hidden;"><a href="{url}" style="color:#000;">{name}</a></div>',
							'<div class="detail"><a href="#" fileName="{name}" fileType="{type}" createTime="{createTime}" saveTime="{time}" size="{size}kb" savePath="{url}" leaveDay="{day}" saveLevel="{saveLevel}" modifiedTime="{modifiedTime}">详情</a></div>',
							'</li>'
							].join("");
		var filePathTpl = [
							'<li class="filePath" fileName="{name}" fileId="{id}"><a href="#">{name}</a></li>'
		].join("");					
		var fileType = {
			directory: 0,
			doc: 1,
			txt: 2,
			image: 3,
			exe: 4,
			music: 5,
			rar: 6,
			zip: 7,
			html: 8,
			pdf: 9,
			undefined: 10,
			xls: 11
		
		}
		var fileIcon = {
			"0": '/hdfs/webDisk/images/folder.gif',
			"1": '/hdfs/webDisk/images/doc.gif',
			"2": '/hdfs/webDisk//images/txt.gif',
			"3": '/hdfs/webDisk/images/image.gif',
			"4": '/hdfs/webDisk/images/exe.gif',
			"5": '/hdfs/webDisk/images/music.gif',
			"6": '/hdfs/webDisk/images/rar.gif',
			"7": '/hdfs/webDisk/images/zip.gif',
			"8": '/hdfs/webDisk/images/html.gif',
			"9": '/hdfs/webDisk/images/pdf.gif',
			"10": '/hdfs/webDisk/images/undefined.gif',
			"11": '/hdfs/webDisk/images/xls.gif'
		}
		var menuCommand = {
			openFile: function(event){
				var fileName = $('.curFile').attr('fileName');
				$('.curFile').dblclick();
				//alert("您选择打开"+fileName);
			},
			reNameFile: function(event){
			//var fileName = $('.curFile').attr('fileName');
			var fileId= $('.curFile').attr('fileId');
			//获取对象的坐标，让对话框在按钮附近弹出
			var _html = '<form id ="renameForm" action="renameFile" method="post">'+
						'<label>重命名为:</label><input type="text" name="filename" ></input>'+
						'<input type="hidden" name="fileId" value="'+fileId+'" /></form>';

			var _x = event.clientX ;
			var _y =  event.clientY;
		
			artDialog({
				id:'menu_4834783',
				content:_html,
				left:_x+20,
				top:_y+20,
				title: '重命名'
				}, 
				function(){ 
					$('#renameForm')[0].submit();
						
						
					}
				);
			},
			removeFile: function(event){
			var fileId = $('.curFile').attr('fileId');
			var userId=document.getElementById("userId").value;
			var currentId = $('#currentId').val();
			var url="/hdfs/file/deleteFile?fileId="+fileId+"&userId="+userId+"&currentId="+currentId;
		    window.location.href=url;
			},
			downloadFile: function(){
				var fileId = $('.curFile').attr('fileId');
			var fileName = $('.curFile').attr('fileName');
				var url="/hdfs/file/downLoad?fileId="+fileId+"&filename="+fileName;
				window.location.href=url;
			},
			cancel: function(event){
				$('#menu').hide(300);
			}
			
			
		};
		function createTpl(str, config){
			var initStr = str;
			for (var key in config){
				var re = new RegExp("{"+key+"}","g");
				initStr = initStr.replace(re,config[key]);
			}
			return initStr;
		}

		function addListener(){
					$('#catalogContent').find('li').bind('dblclick',function(){
						$('#detailWin').hide(300);
						var curFileId = $(this).attr('fileId');
						var curFileName = $(this).attr('fileName');
						var curFileType = $(this).attr('fileType');
						var isLeaf = $(this).attr('isLeaf');
						var parentId = $(this).attr('parentId');
						if (curFileType != fileType.directory){
							alert("不是目录文件");
						}else{
							if (isLeaf == "true"){
								$('#catalogContent').html("<p class='notice'>当前目录为空</p>");
							}else {
								$('#catalogContent').html("");
								var userId=document.getElementById("userId").value;
								var url="/hdfs/file/listFile?userId="+userId+"&currentId="+curFileId;
							    window.location.href=url;
								
							}
														
							$('#reBack').find('a').attr('parentId',parentId).attr('curFileId',curFileId).attr('title',curFileName);
							var filePathTpl = '<li class="filePath" fileName="'+curFileName+'" parentId="'+parentId+'" fileId="'+curFileId+'"><a href="#">'+curFileName+'</a></li>'
							var pathNode = $(filePathTpl);
							$(pathNode).appendTo(pathBox);
						}
						return false;
					});
					$('#catalogContent').find('li').bind('click',function(){
						$('#detailWin').hide(300);
						//alert('click');
						//$('#menu').hide(300);
						if ($('.curFile').length > 0){
							$('.curFile').removeClass('curFile');
						}
						$(this).addClass('curFile');
						return false;
					});
					$('#catalogContent').find('li').bind('mousedown',function(){
				
						return false;
					});
					$('#catalogContent').find('li').bind('mousedown',function(e){
						var menu = {};
					
						var offset = $(this).offset();
						if (e.which === 3){
							
							document.oncontextmenu=function()
							{ 
								$('#menu').css({'top':e.clientY-10+'px','left':e.clientX-10+'px'});
								menuWin = $('#menu:visible');
								if (menuWin.length != 1){
									$('#menu').show(300);
								}
								return false;
							} 
							$('#detailWin').hide(300);
							if ($('.curFile').length == 1){
								$('.curFile').removeClass('curFile');
							}
						$(this).addClass('curFile');
						}
						return false;
					});
					$('#menu').bind('click',function(){
						return false;
					}); 
				
					$('.detail a').bind('click',function(){
							var offset = $(this).offset();
							var name = $(this).attr('fileName');
							var type = $(this).attr('fileType');
							var leaveDay = $(this).attr('leaveDay');
							var modifiedTime = $(this).attr('modifiedTime');
							var saveTime = $(this).attr('saveTime');
							var savePath = $(this).attr('savePath');
							var size = $(this).attr('size');
							if ($(this).attr('saveLevel')==1)
								var saveLevel = "低";
							if ($(this).attr('saveLevel')==2)
								saveLevel = "中";
							if ($(this).attr('saveLevel')==3)
								saveLevel = "高";
							var time = $(this).attr('createTime');
							$('#dirType').attr('class','type'+type);
							$('#detailWin').css({'top':offset.top-170+'px',
												 'left':offset.left+30+'px'
												 });
							$('<span>'+name+'</span>').replaceAll($('#detailWin').find('li.name span'));
							if (type!=0) {
								$('<span>'+size+'</span>').replaceAll($('#detailWin').find('li.size span'));
								$('<span>'+saveLevel+'</span>').replaceAll($('#detailWin').find('li.saveLevel span'));
								$('<span>'+saveTime+'</span>').replaceAll($('#detailWin').find('li.saveTime span'));
							} else {
								$('<span>无</span>').replaceAll($('#detailWin').find('li.size span'));
								$('<span>无</span>').replaceAll($('#detailWin').find('li.saveLevel span'));
								$('<span>无</span>').replaceAll($('#detailWin').find('li.saveTime span'));
							}
							$('<span>'+time+'</span>').replaceAll($('#detailWin').find('li.time span'));
							$('<span>'+leaveDay+'</span>').replaceAll($('#detailWin').find('li.leaveDay span'));
							$('<span>'+modifiedTime+'</span>').replaceAll($('#detailWin').find('li.modifiedTime span'));
							$('<span>'+savePath+'</span>').replaceAll($('#detailWin').find('li.savePath span'));	
							
							$('#detailWin').show(300);
							return false;
					});
					$(document).bind('click',function(){
						$('#detailWin').hide(300);
						//$('#menu').hide(300);
					});
		
		
		}
		function showFiles(files){
			$('#detailWin').hide(300);
			$('#menu').hide(300);
			for(var i = 0,len = files.length; i < len; i++ ){
				var html = createTpl(directoryTpl,files[i]);
				var type = files[i].type;
				var file = $(html);
				if (fileIcon[type]){
					$(file).find('img').attr('src',fileIcon[type]);
				
				}	
					$(file).appendTo($('#catalogContent'));
					$(file).fadeIn(500);
			}
			addListener();
		
		}
		function initCatalog(){
			var catalogs = webDiskJson.list;
			//webDiskJson = result;
			findFile(currentId);
			showFiles(filterDir);
			
		}
		$('#menu ul li a').bind('click',function(event){
			var cmd = $(this).attr('command');
			menuCommand[cmd](event);
		});
		$('#reBack').bind('click',function(){
	
			var userId=document.getElementById("userId").value;
			var currentId = $('#currentId').val();
			//当前目录的id			
			var rootid = $('#rootid').val();
			if (rootid != currentId){
			var url="/hdfs/file/listParentFile?userId="+userId+"&currentId="+currentId;
			window.location.href=url;
			}
			
		});
		

		function eachDirectory(dir,parentId){
	
			if (dir.parentId == parentId ){
					filterDir.push(dir);
					return;
				}else{
					if (dir.isLeaf == "false"){
						var children = dir.children;
						for (var j = 0,lens = children.length; j < lens; j++){
							eachDirectory(children[j],parentId);
						}
				}
			}	
			
		}
		function findFile(parentId){
			//webDiskJson = [];
			var curdir = webDiskJson.list;
			filterDir = [];
			 for (var i = 0,len = curdir.length; i < len; i++){
					eachDirectory(curdir[i],parentId);
				} 
		}

		initCatalog();
		
	
	});