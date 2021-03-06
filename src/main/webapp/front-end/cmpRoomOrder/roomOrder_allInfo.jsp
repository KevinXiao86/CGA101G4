<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>TYPE
html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商訂單詳情頁面</title>

<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="common/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="common/css/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="common/css/dataTables/dataTables.responsive.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="common/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="common/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style type="text/css">
#page-wrapper {
/* 	background-color: rgb(221, 221, 241) !important; */
	height: 900px;
}

span {
	font-size: 18px;
}

#back_index {
	position: fixed;
	right: 10%;
	bottom: 10%;
}

#back_index a {
	font-size: 20px;
	color: blue;
}

#back_index a:hover {
	color: red;
	text-decoration: none;
}

#pre {
	margin-left: 50%;
}
</style>

</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">台玩 - 旅遊平台</a>
			</div>




			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a style="font-weight: 900;"
							href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&">
								<i class="fa fa-dashboard fa-fw"></i> 廠商首頁
						</a></li>


						<li><a style="font-weight: 900;"
							href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 房型管理
						</a></li>

						<li><a style="font-weight: 900;"
							href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 廠商資料
						</a></li>

						<li><a style="font-weight: 900;"
							href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 訂單管理
						</a></li>

						<li>
							<a href="<%=request.getContextPath()%>/front-end/repCust12/seeRepCust.jsp" style="font-weight: 900;">
        						<i class="fa fa-dashboard fa-fw"></i> 
        						檢舉管理
      						</a>
      					</li>

						<li><a href="company/logout" style="font-weight: 900;"> <i
								class="fa fa-dashboard fa-fw"></i> 登出
						</a></li>
					</ul>
				</div>
			</div>
		</nav>




		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header" style="font-weight: 900;">訂單詳情</h1>
					</div>
				</div>

				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="font-weight: 900;">訂單詳情如下</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<div>
										<div class="form-group">
											<label for="cmpName">訂單編號 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.roomOrderId}" />
										</div>
<!-- 										<div> -->
<%-- 											<span>訂單編號 :</span> <span>${roomOrder.roomOrderId}</span> --%>
<!-- 										</div> -->


										<div class="form-group">
											<label for="cmpName">會員編號 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.custId}" />
										</div>
<!-- 										<div> -->
<%-- 											<span>會員編號 :</span> <span>${roomOrder.custId}</span> --%>
<!-- 										</div> -->
										
										

										<div class="form-group">
											<label for="cmpName">會員姓名 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.customer.name}" />
										</div>										
<!-- 										<div> -->
<%-- 											<span>會員姓名 :</span> <span>${roomOrder.customer.name}</span> --%>
<!-- 										</div> -->
										

										<div class="form-group">
											<label for="cmpName">廠商編號 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.cmpId}" />
										</div>											
<!-- 										<div> -->
<%-- 											<span>廠商編號 :</span> <span>${roomOrder.cmpId}</span> --%>
<!-- 										</div> -->
										
										

										<div class="form-group">
											<label for="cmpName">訂單成立日期 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<br>
											<span>
												<fmt:formatDate value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd"/>
											</span>
										</div>										
										
										
										
										<div class="form-group">
											<label for="cmpName">入住日期 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<br>
											<span>
												<fmt:formatDate value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd"/>
											</span>
										</div>											
										
<!-- 										<div> -->
<%-- 											<span>入住日期 :</span> <span><fmt:formatDate --%>
<%-- 													value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd " /></span> --%>
<!-- 										</div> -->


										<div class="form-group">
											<label for="cmpName">退房日期 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<br>
											<span>
												<fmt:formatDate value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd"/>
											</span>
										</div>	

<!-- 										<div> -->
<%-- 											<span>退房日期 :</span> <span><fmt:formatDate --%>
<%-- 													value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd " /></span> --%>
<!-- 										</div> -->


										<div class="form-group">
											<label for="cmpName">訂單狀態 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.roomOrderStatus}" />
										</div>		
<!-- 										<div> -->
<%-- 											<span>訂單狀態 :</span> <span>${roomOrder.roomOrderStatus}</span> --%>
<!-- 										</div> -->
										
										
										<div class="form-group">
											<label for="roomtypeIntroduce">取消原因 :</label><br>
											<textarea rows="6" cols="40" name="canx" id="canx" required>${roomOrder.cancel}</textarea>
											<br>
										</div>										
										
<!-- 										<div> -->
<!-- 											<span>取消原因 :</span> -->
<!-- 											<textarea rows="6" cols="40" name="canx" id="canx" -->
<%-- 												readonly="readonly" style="resize: none;">${roomOrder.cancel}</textarea> --%>
<!-- 											<br> -->
<!-- 										</div> -->
										


										<div class="form-group">
											<label for="cmpName">訂單狀態 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.roomOrderPrice}" />
										</div>											
										
<!-- 										<div> -->
<%-- 											<span>原始金額 :</span> <span>${roomOrder.roomOrderPrice}</span> --%>
<!-- 										</div> -->



										<div class="form-group">
											<label for="cmpName">總金額 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomOrder.totalPrice}" />
										</div>	
<!-- 										<div> -->
<%-- 											<span>總金額 :</span> <span>${roomOrder.totalPrice}</span> --%>
<!-- 										</div> -->

										<div class="form-group">
											<label for="cmpName">會員優惠券編號 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${(roomOrder.custCopId== null)? "未使用優惠券" : roomOrder.custCopId}" />
										</div>
<!-- 										<div> -->
<%-- 											<span>會員優惠券編號 :</span> <span>${(roomOrder.custCopId== null)? "未使用優惠券" : roomOrder.custCopId}</span> --%>
<!-- 										</div> -->
<!-- 										========================================================================== -->
									
									
										<div class="form-group">
											<label for="cmpName">房型編號 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomItemVO.roomId}" />
										</div>
										
										<div class="form-group">
											<label for="cmpName">房型名稱 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomtype.roomtypeName}" />
										</div>
										
										<div class="form-group">
											<label for="cmpName">訂購數量 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomItemVO.roomItemAmount}" />
										</div>
										
										<div class="form-group">
											<label for="cmpName">評價分數 :</label> <span>${requestScope.errorInfo.cmpName}</span>
											<input class="form-control" type="text" id="cmpName"
												name="cmpName" required="required"
												value="${roomItemVO.roomItemEvaluateScore}" />
										</div>
										
										
										<div class="form-group">
											<label for="roomtypeIntroduce">評價內文 :</label><br>
											<textarea rows="6" cols="40" name="canx" id="canx" required>${roomItemVO.roomItemEvaluateMsg}</textarea>
											<br>
										</div>	
										
										
<!-- 										<div> -->
<!-- 											<div> -->
<%-- 												<span>房型編號 :</span> <span>${roomItemVO.roomId}</span> --%>
<!-- 											</div> -->
<!-- 											<div> -->
<%-- 												<span>房型名稱 :</span> <span>${roomtype.roomtypeName}</span> --%>
<!-- 											</div> -->

<!-- 											<div> -->
<%-- 												<span>訂購數量 :</span> <span>${roomItemVO.roomItemAmount}</span> --%>
<!-- 											</div> -->
<!-- 											<div> -->
<%-- 												<span>評價分數 :</span> <span>${roomItemVO.roomItemEvaluateScore}</span> --%>
<!-- 											</div> -->
<!-- 											<div> -->
<!-- 												<span>評價內文 :</span> -->
<!-- 												<textarea rows="6" cols="40" name="canx" id="canx" -->
<%-- 													readonly="readonly" style="resize: none;">${roomItemVO.roomItemEvaluateMsg}</textarea> --%>
<!-- 												<br> -->
<!-- 											</div> -->
<!-- 										</div> -->
									</div>

									<button id="pre">上一頁</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- jQuery -->
	<script src="common/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="common/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="common/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="common/js/dataTables/jquery.dataTables.min.js"></script>
	<script
		src="common/js/dataTables/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="common/js/startmin.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script type="text/javascript">
		   const button1=document.querySelector('#pre');
		   button1.addEventListener('click',e=>{history.back();});
	</script>
</body>
</html>