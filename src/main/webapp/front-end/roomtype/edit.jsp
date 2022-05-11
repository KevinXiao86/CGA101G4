<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型修改頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<link rel="stylesheet" href="front-end/roomtype/css/add.css" />
<script src="front-end/roomtype/js/add.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link
	href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700i"
	rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="front-end/company/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="front-end/company/css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="front-end/company/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="front-end/company/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style>
input[type=text] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	box-sizing: border-box;
	border: 3px solid #ccc;
	-webkit-transition: 0.5s;
	transition: 0.5s;
	outline: none;
}

input[type=text]:focus {
	border: 3px solid #555;
}
</style>

<script type="text/javascript">
	$(() => {
		
		//獲取令牌
		$.ajax({
			url: "http://localhost:8081/CGA101G4/roomtype/getToken",
			type: "get",
			//如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
			dataType: "json",
			success: function(resp) {
				$("#token").val(resp);
			}
		});
		
		
		$("#deleteBtn").click(() => {
			//1. 獲取勾選框的溝想的數量
			var delete_checkbox = $(".delete_checkbox:checked");
			//2. 檢查
			if (delete_checkbox.length == 0) {
	            //3. 提示用戶結果
	            $("#deleteMsg").html("請勾選要刪除的圖片");
	            //5. 阻止提交
	            return false;
			}
		});
		
		 // 去掉錯誤訊息
        $("#deleteMsg").html("");
	})
</script>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">台玩 - 旅遊平台</a>
			</div>

<!-- 			<button type="button" class="navbar-toggle" data-toggle="collapse" -->
<!-- 				data-target=".navbar-collapse"> -->
<!-- 				<span class="sr-only">Toggle navigation</span> <span -->
<!-- 					class="icon-bar"></span> <span class="icon-bar"></span> <span -->
<!-- 					class="icon-bar"></span> -->
<!-- 			</button> -->

<!-- 			<ul class="nav navbar-nav navbar-left navbar-top-links"> -->
<!-- 				<li><a href="#"><i class="fa fa-home fa-fw"></i> Website</a></li> -->
<!-- 			</ul> -->

<!-- 			<ul class="nav navbar-right navbar-top-links"> -->
<!-- 				<li class="dropdown navbar-inverse"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i> -->
<!-- 						<b class="caret"></b> -->
<!-- 				</a> -->
<!-- 					<ul class="dropdown-menu dropdown-alerts"> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-comment fa-fw"></i> New Comment <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span -->
<!-- 										class="pull-right text-muted small">12 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-envelope fa-fw"></i> Message Sent <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-tasks fa-fw"></i> New Task <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-upload fa-fw"></i> Server Rebooted <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li class="divider"></li> -->
<!-- 						<li><a class="text-center" href="#"> <strong>See -->
<!-- 									All Alerts</strong> <i class="fa fa-angle-right"></i> -->
<!-- 						</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i> -->
<!-- 						secondtruth <b class="caret"></b> -->
<!-- 				</a> -->
<!-- 					<ul class="dropdown-menu dropdown-user"> -->
<!-- 						<li><a href="#"><i class="fa fa-user fa-fw"></i> User -->
<!-- 								Profile</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a> -->
<!-- 						</li> -->
<!-- 						<li class="divider"></li> -->
<!-- 						<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> -->
<!-- 								Logout</a></li> -->
<!-- 					</ul></li> -->
<!-- 			</ul> -->
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
					
						<li>
							<a href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商首頁
							</a>
						</li>						
					
						<li>
							<a href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i>
								房型管理
							</a>
						</li>
						
						<li>
							<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商資料
							</a>
						</li>
						
						<li>
							<a href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i>
								訂單管理
							</a>
						</li>
						
						<li>
							<a href="javascript:history.go(-1)">
								<i class="fa fa-dashboard fa-fw"></i>
								返回列表
							</a>
						</li>						
						
						<li>
							<a href="company/logout">
								<i class="fa fa-dashboard fa-fw"></i>
								登出
							</a>
						</li>
<!-- 						<li class="sidebar-search"> -->
<!-- 							<div class="input-group custom-search-form"> -->
<!-- 								<input type="text" class="form-control" placeholder="Search..."> -->
<!-- 								<span class="input-group-btn"> -->
<!-- 									<button class="btn btn-primary" type="button"> -->
<!-- 										<i class="fa fa-search"></i> -->
<!-- 									</button> -->
<!-- 								</span> -->
<!-- 							</div> /input-group -->
<!-- 						</li> -->
<!-- 						<li><a href="index.html"><i class="fa fa-dashboard fa-fw"></i> -->
<!-- 								Dashboard</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> -->
<!-- 								Charts<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="flot.html">Flot Charts</a></li> -->
<!-- 								<li><a href="morris.html">Morris.js Charts</a></li> -->
<!-- 							</ul> /.nav-second-level</li> -->
<!-- 						<li><a href="tables.html"><i class="fa fa-table fa-fw"></i> -->
<!-- 								Tables</a></li> -->
<!-- 						<li><a href="forms.html"><i class="fa fa-edit fa-fw"></i> -->
<!-- 								Forms</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-wrench fa-fw"></i> UI -->
<!-- 								Elements<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="panels-wells.html">Panels and Wells</a></li> -->
<!-- 								<li><a href="buttons.html">Buttons</a></li> -->
<!-- 								<li><a href="notifications.html">Notifications</a></li> -->
<!-- 								<li><a href="typography.html">Typography</a></li> -->
<!-- 								<li><a href="icons.html"> Icons</a></li> -->
<!-- 								<li><a href="grid.html">Grid</a></li> -->
<!-- 							</ul> /.nav-second-level</li> -->
<!-- 						<li><a href="#"><i class="fa fa-sitemap fa-fw"></i> -->
<!-- 								Multi-Level Dropdown<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="#">Second Level Item</a></li> -->
<!-- 								<li><a href="#">Second Level Item</a></li> -->
<!-- 								<li><a href="#">Third Level <span class="fa arrow"></span></a> -->
<!-- 									<ul class="nav nav-third-level"> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 									</ul> /.nav-third-level</li> -->
<!-- 							</ul> /.nav-second-level</li> -->
<!-- 						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> -->
<!-- 								Sample Pages<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="blank.html">Blank Page</a></li> -->
<!-- 								<li><a href="login.html">Login Page</a></li> -->
<!-- 							</ul> /.nav-second-level</li> -->
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">房間詳情頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<div class="panel-heading">房間資料</div>

							<div class="panel-heading">
								<!-- 錯誤訊息回顯 -->
								<span id="deleteMsg" style="color: red; margin-left: 10px; font-size: 20px;"></span><br><br>
								<form action="roomtype/deleteRoomImg" method="post">
									<input type="hidden" id="cmpId" name="cmpId"
										value="${requestScope.editRoomtype.cmpId}">
										 
									<input
										type="hidden" id="roomtypeId" name="roomtypeId"
										value="${requestScope.editRoomtype.roomtypeId}">
									
									<!-- 隐藏域保存服务端token -->
									<input type="hidden"  name="token" id="token"
												${sessionScope.token} /> 	

									<c:forEach var="RoomImg"
										items="${requestScope.editRoomtype.roomImgs}">
										<c:choose>
											<c:when test="${RoomImg.roomImgId == requestScope.editRoomtype.roomImgs[0].roomImgId}">
												<img src="${RoomImg.roomImg}" height="128px" width="128px"
													class="uploadedImg" style="margin-right: 10px">
											</c:when>

											<c:otherwise>
												<img src="${RoomImg.roomImg}" height="128px" width="128px"
													class="uploadedImg">
													
												<input type="checkbox" name="roomImgId"
													value="${RoomImg.roomImgId}" class="delete_checkbox">
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<button id="deleteBtn"
										style="position: absolute; right: 40px; top: 160px">點擊刪除</button>
								</form>
							</div>



							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6">
										<form role="form" action="roomtype/editRoomtypeInEditPage"
											enctype="multipart/form-data" method="post">

											<!-- 隐藏域保存服务端token -->
<!-- 											<input type="hidden" name="token" id="token" -->
<%-- 												${sessionScope.token} />  --%>
											<!-- 錯誤訊息回顯 -->
											<span id="editMsg" style="color: red; margin-left: 10px; font-size: 20px;">${requestScope.editRoomtype.message}</span><br><br>
												
											<input type="hidden" id="cmpId"
												name="cmpId" value="${requestScope.editRoomtype.cmpId}">

											<input type="hidden" id="roomtypeId" name="roomtypeId"
												value="${requestScope.editRoomtype.roomtypeId}">

											<div class="form-group">

												<label for="roomtypeName">房型名稱:</label> <input type="text"
													name="roomtypeName" id="roomtypeName"
													value="${requestScope.editRoomtype.roomtypeName}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypeAmount">房型數量:</label> <input type="text"
													name="roomtypeAmount" id="roomtypeAmount"
													value="${requestScope.editRoomtype.roomtypeAmount}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypePeople">入住人數:</label> <input type="text"
													name="roomtypePeople" id="roomtypePeople"
													value="${requestScope.editRoomtype.roomtypePeople}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypePrice">價格:</label> <input type="text"
													name="roomtypePrice" id="roomtypePrice"
													value="${requestScope.editRoomtype.roomtypePrice}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypeStatus">狀態:</label> <input
													type="checkbox" name="status" value="上架" checked="checked">上架
												<input type="checkbox" name="status" value="下架">下架 <br>
											</div>

											<div class="form-group">
												<label for="roomtypeArea">平方公尺:</label> <input type="text"
													name="roomtypeArea" id="roomtypeArea"
													value="${requestScope.editRoomtype.roomtypeArea}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypeIntroduce">房型介紹</label><br>
												<textarea rows="6" cols="40" name="roomtypeIntroduce"
													id="roomtypeIntroduce">${requestScope.editRoomtype.roomtypeIntroduce}</textarea>
												<br>
											</div>

											<div class="form-group">
												<div class="container">
													<h2>上傳圖片</h2>
													<ul class="list">
														<li class="file">
															<!-- multiple 這個屬性可以實現文件選擇框的多選 --> <input type="file"
															name="file" id="file" multiple />
														</li>
													</ul>
												</div>
											</div>
											<button id="btn">修改</button>
										</form>
									</div>
								</div>
								<!-- /.row (nested) -->
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>


	<script type="text/javascript">
	
	$(() => {
		$("#btn").click(() => {
			//1. 獲取房型名稱
			var roomtypeName = $("#roomtypeName").val();
	        //2. 檢查數據
	        if (roomtypeName.length == 0) {
	            //3. 提示用戶結果
	            $("#editMsg").html("請輸入房型名稱");
	            //5. 阻止提交
	            return false;
	        }
	        
	        
	        //1. 獲取房型數量
	        var roomtypeAmount = $("#roomtypeAmount").val();
	        //2. 檢查數據
	        var numbertPatt = /^[0-9]*$/;
	        if (roomtypeAmount.length == 0) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的房型數量");
	            //5. 阻止提交 
	            return false;
	        }
	        if (!numbertPatt.test(roomtypeAmount)) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的房型數量");
	            //5. 阻止提交 
	            return false;
	        }
	        
	        
	        //1. 獲取入住人數
	        var roomtypePeople = $("#roomtypePeople").val();
	        //2. 檢查數據
	        if (roomtypePeople.length == 0) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的入住人數");
	            //5. 阻止提交 
	            return false;
	        }
	        if (!numbertPatt.test(roomtypePeople)) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的入住人數");
	            //5. 阻止提交 
	            return false;
	        }
	        
	        
	        //1. 獲取價格
	        var roomtypePrice = $("#roomtypePrice").val();
	        //2. 檢查數據
	        if (roomtypePrice.length == 0) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的價格");
	            //5. 阻止提交 
	            return false;
	        }
	        if (!numbertPatt.test(roomtypePrice)) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的價格");
	            //5. 阻止提交 
	            return false;
	        }
	        
	        
	        //1. 獲取平方公尺
	        var roomtypeArea = $("#roomtypeArea").val();
	        //2. 檢查數據
	        if (roomtypeArea.length == 0) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的平方公尺");
	            //5. 阻止提交 
	            return false;
	        }
	        if (!numbertPatt.test(roomtypeArea)) {
	            //4. 提示用戶結果
	            $("#editMsg").html("請輸入有效的平方公尺");
	            //5. 阻止提交 
	            return false;
	        }
	        
	        
	        //1. 獲取房型介紹
	        var roomtypeIntroduce = $("#roomtypeIntroduce").val();
	        //2. 檢查數據
	        if (roomtypeIntroduce.length == 0) {
	            //3. 提示用戶結果
	            $("#editMsg").html("請輸入房型介紹");
	            //5. 阻止提交
	            return false;
	        }
	        
	        
// 	        //1. 獲取房型圖片
// 	        var serialNo = document.querySelector('#file');
// 	        if(serialNo.files[0] == null){
// 	           //4. 提示用戶結果
// 	           $("#editMsg").html("請上傳房型圖片");
// 	           //5. 阻止提交 
// 	           return false;
// 	        }
		});
	});	
	</script>

	<!-- jQuery -->
	<script src="front-end/company/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="front-end/company/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="front-end/company/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="front-end/company/js/startmin.js"></script>
</body>
</html>