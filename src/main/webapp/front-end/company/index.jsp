<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商首頁</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="front-end/company/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="front-end/company/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="front-end/company/css/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="front-end/company/css/dataTables/dataTables.responsive.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="front-end/company/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="front-end/company/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">台玩 - 旅遊平台</a>
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
<!-- 				<li class="dropdown navbar-inverse"> -->
				
<!-- 				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i> -->
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
					
					
<!-- 				<li class="dropdown"> -->
<!-- 					<a class="dropdown-toggle" data-toggle="dropdown" href="#">  -->
<!-- 						<i class="fa fa-user fa-fw"></i> -->
<!-- 						登出 -->
<!-- 						<b class="caret"></b> -->
<!-- 					</a> -->
					
<!-- 					<ul class="dropdown-menu dropdown-user"> -->
<!-- 						<li><a href="#"><i class="fa fa-user fa-fw"></i> User -->
<!-- 								Profile</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a> -->
<!-- 						</li> -->
<!-- 						<li class="divider"></li> -->
<!-- 						<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> -->
<!-- 								Logout</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 			</ul> -->
			<!-- /.navbar-top-links -->


			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
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
							<a href="company/logout">
								<i class="fa fa-dashboard fa-fw"></i>
								登出
							</a>
						</li>
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
						<h1 class="page-header">廠商首頁</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>


				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">已有的房型如下:</div>
							

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">
										
										<tr>
											<td>房型圖片</td>
											<td>房型名稱</td>
											<td>房型數量</td>
											<td>入住人數</td>
											<td>價格</td>
											<td>狀態</td>
										</tr>
										
										<c:forEach items="${sessionScope.page.items}" var="Roomtype">
											<tr>
												<td>
													<div id="myModal" class="modal">
  														<span class="close">×</span>
  														<img class="modal-content" id="img01">
  														<div id="caption"></div>
													</div>
												
													<c:forEach items="${Roomtype.roomImgs}" end="0"
														var="RoomImg">
														<img height="128px" width="128px" src="${RoomImg.roomImg}">
													</c:forEach>
												</td>
												
												<td>${Roomtype.roomtypeName}</td>
                								<td>${Roomtype.roomtypeAmount}</td>
                								<td>${Roomtype.roomtypePeople}</td>
                								<td>${Roomtype.roomtypePrice}</td>
												
												<td>
													<a href="reservation/getReservation?roomtypeId=${Roomtype.roomtypeId}&roomtypeAmount=${Roomtype.roomtypeAmount}"
                                                    		   style="background-color: lightskyblue; color: white; padding: 5px 5px; 
                                                        	   text-decoration: none; display: inline-block; border-radius: 12px;">
                                                    	預約表
                                                	</a>
												</td>
											</tr>
										</c:forEach>
									</table>
									
									<%--靜態包含分頁條 --%>
									<%@include file="/front-end/company/page_nav.jsp"%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="front-end/company/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="front-end/company/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="front-end/company/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="front-end/company/js/dataTables/jquery.dataTables.min.js"></script>
	<script
		src="front-end/company/js/dataTables/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="front-end/company/js/startmin.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true
			});
		});
	</script>
</body>
</html>