<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型詳情</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>台玩旅遊平台 - 廠商修改頁面</title>

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
					
<!-- 						<li> -->
<!-- 							<a href="javascript:history.go(-1)" style="font-weight: 900;"> -->
<!-- 								<i class="fa fa-dashboard fa-fw"></i> -->
<!-- 								返回列表 -->
<!-- 							</a> -->
<!-- 						</li> -->
						<li>
<%-- 							<a href="<%=request.getContextPath()%>/front-end/repCust12/seeRepCust.jsp" style="font-weight: 900;"> --%>
							<a href="front-end/repCust12/seeRepCust.jsp" style="font-weight: 900;">
        						<i class="fa fa-dashboard fa-fw"></i> 
        						檢舉管理
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
						<h1 class="page-header" style="font-weight: 900;">房間詳情頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<div class="panel-heading" style="font-weight: 900;">房間資料</div>

							<div class="panel-heading">
								<c:forEach var="RoomImg" items="${requestScope.roomtype.roomImgs}">
        							<img src="${RoomImg.roomImg}" height="128px" width="128px" class="uploadedImg">
    							</c:forEach>
							</div>
								


								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form">
												<div class="form-group">
													<label for="roomtypeName">房型名稱:</label> <input type="text"
														name="roomtypeName" id="roomtypeName"
														value="${requestScope.roomtype.roomtypeName}"><br>
												</div>

												<div class="form-group">
													<label for="roomtpyeAmount">房型數量:</label> <input
														type="text" name="roomtypeAmount" id="roomtypeAmount"
														value="${requestScope.roomtype.roomtypeAmount}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypePeople">入住人數:</label> <input
														type="text" name="roomtypePeople" id="roomtypePeople"
														value="${requestScope.roomtype.roomtypePeople}"><br>
												</div>

												<div class="form-group">
													<label for="totalScope">總評分:</label> <input type="text"
														name="totalScore" id="totalScore"
														value="${requestScope.roomtype.totalScore}"><br>
												</div>

												<div class="form-group">
													<label for="totalPeople">評分總人數:</label> <input type="text"
														name="totalPeople" id="totalPeople"
														value="${requestScope.roomtype.totalPeople}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypePrice">價格:</label> <input type="text"
														name="roomtypePrice" id="roomtypePrice"
														value="${requestScope.roomtype.roomtypePrice}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypeStatus">狀態:</label> <input type="text"
														name="roomtypeStatus" id="roomtypeStatus"
														value="${requestScope.roomtype.roomtypeStatus}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypeArea">平方公尺:</label> <input type="text"
														name="roomtypeArea" id="roomtypeArea"
														value="${requestScope.roomtype.roomtypeArea}"><br>
												</div>


												<div class="form-group">
													<label for="roomtypeIntroduce">房型介紹</label><br>
													<textarea rows="6" cols="40" name="canx" id="canx" required>${requestScope.roomtype.roomtypeIntroduce}</textarea>
													<br>
												</div>
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