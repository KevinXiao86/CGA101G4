<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增房型</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<link rel="stylesheet" href="common/css/add.css" />
<script src="common/js/add.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700i"
	rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="common/css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="common/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="common/css/font-awesome.min.css" rel="stylesheet"
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
// 	$.ajax({
// 		url: "http://localhost:8081/CGA101G4/roomtype/getToken",
// 		type: "get",
// 		//如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
// 		dataType: "json",
// 		success: function(resp) {
// 			$("#token").val(resp);
// 		}
// 	});
	
	
	$("#btn").click(() => {
		//1. 獲取房型名稱
		var roomtypeName = $("#roomtypeName").val();
        //2. 檢查數據
        if (roomtypeName.length == 0) {
            //3. 提示用戶結果
            $("#errorMsg").html("請輸入房型名稱");
            //5. 阻止提交
            return false;
        }
        
        
        //1. 獲取房型數量
        var roomtypeAmount = $("#roomtypeAmount").val();
        //2. 檢查數據
        var numbertPatt = /^[0-9]*$/;
        if (roomtypeAmount.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的房型數量");
            //5. 阻止提交 
            return false;
        }
        if (!numbertPatt.test(roomtypeAmount)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的房型數量");
            //5. 阻止提交 
            return false;
        }
        
        
        //1. 獲取入住人數
        var roomtypePeople = $("#roomtypePeople").val();
        //2. 檢查數據
        if (roomtypePeople.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的入住人數");
            //5. 阻止提交 
            return false;
        }
        if (!numbertPatt.test(roomtypePeople)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的入住人數");
            //5. 阻止提交 
            return false;
        }
        
        
        //1. 獲取價格
        var roomtypePrice = $("#roomtypePrice").val();
        //2. 檢查數據
        if (roomtypePrice.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的價格");
            //5. 阻止提交 
            return false;
        }
        if (!numbertPatt.test(roomtypePrice)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的價格");
            //5. 阻止提交 
            return false;
        }
        
        
        //1. 獲取平方公尺
        var roomtypeArea = $("#roomtypeArea").val();
        //2. 檢查數據
        if (roomtypeArea.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的平方公尺");
            //5. 阻止提交 
            return false;
        }
        if (!numbertPatt.test(roomtypeArea)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的平方公尺");
            //5. 阻止提交 
            return false;
        }
        
        
        //1. 獲取房型介紹
        var roomtypeIntroduce = $("#roomtypeIntroduce").val();
        //2. 檢查數據
        if (roomtypeIntroduce.length == 0) {
            //3. 提示用戶結果
            $("#errorMsg").html("請輸入房型介紹");
            //5. 阻止提交
            return false;
        }
        
        
        //1. 獲取房型圖片
        var serialNo = document.querySelector('#file');
        if(serialNo.files[0] == null){
           //4. 提示用戶結果
           $("#errorMsg").html("請上傳房型圖片");
           //5. 阻止提交 
           return false;
        }
        
        
        // 去掉錯誤訊息
        $("#errorMsg").html("");
	});
});
</script>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">台玩 - 旅遊平台</a>
			</div>

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">

						<li>
							<a href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商首頁
							</a>
						</li>
												
						<li>
							<a href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								房型管理
							</a>
						</li>
						
						<li>
							<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商資料
							</a>
						</li>
						
						<li>
							<a href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								訂單管理
							</a>
						</li>
					
						<li>
							<a href="javascript:history.go(-1)" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								返回列表
							</a>
						</li>
						
						<li>
							<a href="company/logout" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								登出
							</a>
						</li>						
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
						<h1 class="page-header" style="font-weight: 900;">新增房型頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<div class="panel-heading" style="font-weight: 900;">房間資料</div>

							<div class="panel-body">
								<div class="row">
									<!-- 錯誤訊息回顯 -->
									<span id="errorMsg" style="color: red; margin-left: 10px; font-size: 20px;"></span><br><br>
									<div class="col-lg-6">
										<form action="roomtype/insertRoomtype"
											enctype="multipart/form-data" method="post">
											
											<!-- 隐藏域保存服务端token -->
											<input type="hidden" id="token" name="token" ${sessionScope.token}/>
											
											<!-- param:可以獲取請求參數的值, 這邊獲取到的就是從 room_list.jsp 傳遞過來的總頁碼 -->
											<input type="hidden" name="pageTotal" value="${param.pageTotal}">	
											<input type="hidden" name="pageTotalCount" value="${param.pageTotalCount}">
											
											<div class="form-group">
												<label for="roomtypeName">房型名稱:</label> <input type="text"
													name="roomtypeName" id="roomtypeName"
													value="${requestScope.roomtype.roomtypeName}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypeAmount">房型數量:</label> <input type="text"
													name="roomtypeAmount" id="roomtypeAmount"
													value="${requestScope.roomtype.roomtypeAmount}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypePeople">入住人數:</label> <input type="text"
													name="roomtypePeople" id="roomtypePeople"
													value="${requestScope.roomtype.roomtypePeople}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypePrice">價格:</label> <input type="text"
													name="roomtypePrice" id="roomtypePrice"
													value="${requestScope.roomtype.roomtypePrice}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypeStatus">狀態:</label> <input
													type="checkbox" name="status" value="上架" checked="checked">上架
												<input type="checkbox" name="status" value="下架">下架 <br>
											</div>

											<div class="form-group">
												<label for="roomtypeArea">平方公尺:</label> <input type="text"
													name="roomtypeArea" id="roomtypeArea"
													value="${requestScope.roomtype.roomtypeArea}"><br>
											</div>

											<div class="form-group">
												<label for="roomtypeIntroduce">房型介紹</label><br>
												<textarea rows="6" cols="40" name="roomtypeIntroduce"
													id="roomtypeIntroduce">${requestScope.roomtype.roomtypeIntroduce}</textarea>
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

											<button id="btn">新增</button>
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
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="common/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="common/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="common/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="common/js/startmin.js"></script>
</body>
</html>