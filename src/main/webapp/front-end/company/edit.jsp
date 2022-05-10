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
<link href="front-end/company/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="front-end/company/css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="front-end/company/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="front-end/company/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
	
<script src="front-end/company/js/edit.js"></script>
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
						<h1 class="page-header">廠商修改頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">基本資料</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6">
										<span class="errorMsg" style="color: red; font-size: 20px">${sessionScope.editCompany.message}</span>
										<form role="form" method="post" action="company/editCompany">
											<!-- 因為修改操作都需要 id, 所以使用隱藏域直接帶上 id -->
											<input type="hidden" name="cmpId" id="cmpId"
												value="${sessionScope.editCompany.cmpId}">

											<!-- 隐藏域保存服务端token -->
											<input type="hidden" id="token" name="token" ${sessionScope.token}/>

											<div class="form-group">
												<label for="cmpName">廠商名稱</label> <span>${requestScope.errorInfo.cmpName}</span>
												<input class="form-control" type="text" id="cmpName"
													name="cmpName" required="required"
													value="${sessionScope.editCompany.cmpName}" />
											</div>
											<div class="form-group">
												<label for="cmpTel">廠商電話</label> <span>${requestScope.errorInfo.cmpTel}</span>
												<input class="form-control" type="text" id="cmpTel"
													name="cmpTel" required="required"
													value="${sessionScope.editCompany.cmpTel}" />
											</div>

											<div class="form-group">
												<label for="bankAccount">銀行帳號</label> <span>${requestScope.errorInfo.headBank}${requestScope.errorInfo.endBank}</span><br>
												<input type="text" id="headBank" name="headBank"
													placeholder="銀行代號" required style="margin-bottom: 10px;"
													value="${sessionScope.editCompany.bankAccount.substring(0, 3)}" /><br>
												<input type="text" name="endBank" id="endBank"
													placeholder="銀行帳號" required
													value="${sessionScope.editCompany.bankAccount.substring(4)}" /><br>
											</div>

											<div class="form-group">
												<label for="cmpMail">信箱</label> <span>${requestScope.errorInfo.cmpMail}</span><br>
												<input type="text" id="cmpMail" name="cmpMail"
													required="required"
													value="${sessionScope.editCompany.cmpMail}" />
											</div>

											<div class="form-group">
												<label for="cmper">廠商負責人</label> <span>${requestScope.errorInfo.cmper}</span><br>
												<input type="text" id="cmper" name="cmper"
													required="required"
													value="${sessionScope.editCompany.cmper}" />
											</div>


											<div class="form-group">
												<label for="cmperTel">廠商負責人電話</label> <span>${requestScope.errorInfo.cmperTel}</span><br>
												<input type="text" id="cmperTel" name="cmperTel"
													required="required"
													value="${sessionScope.editCompany.cmperTel}" />
											</div>


											<div class="form-group">
												<label for="serialNo">旅宿登記證</label><br> <img
													src="${sessionScope.editCompany.serialNo}" width="120"
													height="120">
											</div>


											<div class="form-group">
												<label for="cmpIntroduce">飯店介紹</label> <span>${requestScope.errorInfo.cmpIntroduce}</span><br>
												<textarea rows="6" cols="40" name="cmpIntroduce"
													id="cmpIntroduce" required>${sessionScope.editCompany.cmpIntroduce}</textarea>
											</div>

											<div class="form-group">
												<label for="checkinTime">入住時間</label> <span>${requestScope.errorInfo.checkinTime}</span><br>
												<input type="time" id="checkinTime" name="checkinTime"
													min="00:00" max="24:00" required="required"
													value="${sessionScope.editCompany.checkinTime}" />
											</div>

											<div class="form-group">
												<label for="checkoutTime">退房時間</label> <span>${requestScope.errorInfo.checkoutTime}</span><br>
												<input type="time" id="checkoutTime" name="checkoutTime"
													min="00:00" max="24:00" required="required"
													value="${sessionScope.editCompany.checkoutTime}" />
											</div>

											<div class="form-group">
												<label>地點</label> <span>${requestScope.errorInfo.city}${requestScope.errorInfo.town}${requestScope.errorInfo.road}</span><br>
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
												<label for="notice">購買須知</label> <span>${requestScope.errorInfo.notice}</span><br>
												<textarea rows="6" cols="40" name="notice" id="notice"
													required>${sessionScope.editCompany.notice}</textarea>
												<br>
											</div>

											<div class="form-group">
												<label for="canx">取消政策</label><br> <span>${requestScope.errorInfo.canx}</span>
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
	<script src="front-end/company/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="front-end/company/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="front-end/company/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="front-end/company/js/startmin.js"></script>
</body>
</html>