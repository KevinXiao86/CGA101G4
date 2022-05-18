<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商修改頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>台玩旅遊平台 - 廠商修改頁面</title>

<!-- Bootstrap Core CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="common/css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="common/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="common/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
	
<script src="common/js/edit.js"></script>
<style>
span{
	color: red;
	font-size: 16px;
}
</style>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html" style="font-weight: 900;">台玩 - 旅遊平台</a>
			</div>


			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
					
						<li>
							<a href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&"
								style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商首頁
							</a>
						</li>					
					
					
						<li>
							<a href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}"
								style="font-weight: 900;">
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
							<a href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}"
								style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								訂單管理
							</a>
						</li>
						
<!-- 						<li> -->
<!-- 							<a href="javascript:history.go(-1)" -->
<!-- 								style="font-weight: 900;"> -->
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
							<a href="company/logout"
								style="font-weight: 900;">
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
						<h1 class="page-header" style="font-weight: 900;">廠商修改頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="font-weight: 900;">基本資料</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6">
										<span class="errorMsg" style="color: red; font-size: 20px">${sessionScope.editCompany.message}</span>
										<form role="form" method="post" action="company/editCompany"  enctype="multipart/form-data">
										
											<div class="form-group">
												<label for="cmpAccount" style="font-weight: 900;">帳號</label> <br>
												<input type="text" id="cmpAccount" name="cmpAccount" required="required" value="${sessionScope.editCompany.cmpAccount}" disabled="disabled"/>
											</div>
											<!-- 因為修改操作都需要 id, 所以使用隱藏域直接帶上 id -->
											<input type="hidden" name="cmpId" id="cmpId"
												value="${sessionScope.editCompany.cmpId}">

											<!-- 隐藏域保存服务端token -->
<%-- 											<input type="hidden" id="token" name="token" ${sessionScope.token}/> --%>

											<div class="form-group">
												<label for="cmpName" style="font-weight: 900;">廠商名稱</label> <span>${requestScope.errorInfo.cmpName}</span>
												<input class="form-control" type="text" id="cmpName"
													name="cmpName" required="required"
													value="${sessionScope.editCompany.cmpName}" />
											</div>
											
											<div class="form-group">
												<label for="cmpTel" style="font-weight: 900;">廠商電話</label> <span id="cmpTelSpan">${requestScope.errorInfo.cmpTel}</span>
												<input class="form-control" type="text" id="cmpTel"
													name="cmpTel" required="required"
													value="${sessionScope.editCompany.cmpTel}" />
											</div>

											<div class="form-group">
												<label for="bankAccount" style="font-weight: 900;">銀行帳號</label> <span id="bankAccountSpan">${requestScope.errorInfo.headBank}${requestScope.errorInfo.endBank}</span><br>
												<input type="text" id="headBank" name="headBank"
													placeholder="銀行代號" required style="margin-bottom: 10px;"
													value="${sessionScope.editCompany.bankAccount.substring(0, 3)}" /><br>
												<input type="text" name="endBank" id="endBank"
													placeholder="銀行帳號" required
													value="${sessionScope.editCompany.bankAccount.substring(4)}" /><br>
											</div>

											<div class="form-group">
												<label for="cmpMail" style="font-weight: 900;">信箱</label> <span>${requestScope.errorInfo.cmpMail}</span><br>
												<input type="text" id="cmpMail" name="cmpMail"
													required="required"
													value="${sessionScope.editCompany.cmpMail}" />
											</div>

											<div class="form-group">
												<label for="cmper" style="font-weight: 900;">廠商負責人</label> <span>${requestScope.errorInfo.cmper}</span><br>
												<input type="text" id="cmper" name="cmper"
													required="required"
													value="${sessionScope.editCompany.cmper}" />
											</div>


											<div class="form-group">
												<label for="cmperTel" style="font-weight: 900;">廠商負責人電話</label> <span>${requestScope.errorInfo.cmperTel}</span><br>
												<input type="text" id="cmperTel" name="cmperTel"
													required="required"
													value="${sessionScope.editCompany.cmperTel}" />
											</div>


											<div class="form-group">
												<c:choose>
														<c:when test="${sessionScope.editCompany.auditStatus == '審核通過'}">
															<label for="serialNo" style="font-weight: 900;">旅宿登記證</label><br> 
															<img src="${sessionScope.editCompany.serialNo}" width="120"	height="120">
														</c:when>

														<c:when test="${sessionScope.editCompany.auditStatus == '審核未通過'}">
															<label for="serialNo" style="font-weight: 900;">旅宿登記證</label><br>
															<span id="serialNoSpan">${requestScope.serialNo}</span>
															<input type="file" name="serialNo" id="serialNo">
														</c:when>
												</c:choose>
<!-- 												<label for="serialNo" style="font-weight: 900;">旅宿登記證</label><br> <img -->
<%-- 													src="${sessionScope.editCompany.serialNo}" width="120" --%>
<!-- 													height="120"> -->
											</div>


											<div class="form-group">
												<label for="cmpIntroduce" style="font-weight: 900;">飯店介紹</label> <span>${requestScope.errorInfo.cmpIntroduce}</span><br>
												<textarea rows="6" cols="40" name="cmpIntroduce"
													id="cmpIntroduce" required>${sessionScope.editCompany.cmpIntroduce}</textarea>
											</div>

											<div class="form-group">
												<label for="checkinTime" style="font-weight: 900;">入住時間</label> <span>${requestScope.errorInfo.checkinTime}</span><br>
												<input type="time" id="checkinTime" name="checkinTime"
													min="00:00" max="24:00" required="required"
													value="${sessionScope.editCompany.checkinTime}" />
											</div>

											<div class="form-group">
												<label for="checkoutTime" style="font-weight: 900;">退房時間</label> <span>${requestScope.errorInfo.checkoutTime}</span><br>
												<input type="time" id="checkoutTime" name="checkoutTime"
													min="00:00" max="24:00" required="required"
													value="${sessionScope.editCompany.checkoutTime}" />
											</div>

											<div class="form-group">
												<label style="font-weight: 900;">地點</label> <span>${requestScope.errorInfo.city}${requestScope.errorInfo.town}${requestScope.errorInfo.road}</span><br>
												<input type="text" id="city" name="city" placeholder="縣市"
													required="required" style="margin-bottom: 10px;"
													value="${sessionScope.editCompany.location.substring(0, 3)}" /><br>
												<input type="text" id="town" name="town" placeholder="城鎮"
													required="required" style="margin-bottom: 10px;"
													value="${sessionScope.editCompany.location.substring(3, 6)}" /><br>
												<input type="text" id="road" name="road" placeholder="街道"
													required="required" style="margin-bottom: 10px;"
													value="${sessionScope.editCompany.location.substring(6)}" />
											</div>

											<div class="form-group">
												<label for="notice" style="font-weight: 900;">購買須知</label> <span>${requestScope.errorInfo.notice}</span><br>
												<textarea rows="6" cols="40" name="notice" id="notice"
													required>${sessionScope.editCompany.notice}</textarea>
												<br>
											</div>

											<div class="form-group">
												<label for="canx" style="font-weight: 900;">取消政策</label><br> <span>${requestScope.errorInfo.canx}</span>
												<textarea rows="6" cols="40" name="canx" id="canx" required>${sessionScope.editCompany.canx}</textarea>
												<br>
											</div>

											<button type="submit" class="btn btn-default" id="btnEdit">提交修改</button>
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