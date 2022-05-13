<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="com.taiwan.beans.RoomOrder"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
List<RoomOrder> list = (List<RoomOrder>) request.getAttribute("roomOrders");
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商訂單頁面</title>

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

<link rel="stylesheet" type="text/css" href="common/datetimepicker/jquery.datetimepicker.css" />
<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	/*     height: 600px;   */
}

table {
	width: 1100px;
	background-color: rgb(221, 221, 241) !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 3px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
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
						<li>
							<a href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商首頁
							</a>
						</li>					
					
					
						<li><a
							href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 房型管理
						</a></li>

						<li><a
							href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 廠商資料
						</a></li>

						<li><a
							href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 訂單管理
						</a></li>

						<li><a href="company/logout"> <i
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
						<h1 class="page-header">搜尋全部訂房訂單</h1>
					</div>
				</div>
				<div>
                    <FORM METHOD="post" ACTION="roomOrder/cmpSelectByDate">
                        <b>根據日期來做查詢來做查詢:</b> <br>
                        <label for="from">From</label>
						<input type="text"  name="startdate" id="start_date">
						<label for="to">to</label>
						<input type="text"  name="enddate" id="end_date">
                        <input type="submit" value="送出">
                    </FORM>
                </div>

				<%--錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">已有的房型如下:</div>
								
							<div class="form-group">
							
							</div>
							
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">

										<tr>
											<th>訂房訂單編號</th>
											<th>訂房會員編號</th>
											<th>訂單成立日期</th>
											<th>入住時間</th>
											<th>退房時間</th>
											<th>總金額</th>
											<th>訂單狀態</th>
											<th>詳情</th>

										</tr>
										<c:forEach items="${list}" var="roomOrder">
											<tr>
												<td>${roomOrder.roomOrderId}</td>
												<td>${roomOrder.custId}</td>
												<td><fmt:formatDate value="${roomOrder.roomOrderDate}"
														pattern="yyyy-MM-dd" /></td>
												<td><fmt:formatDate value="${roomOrder.checkinDate}"
														pattern="yyyy-MM-dd" /></td>
												<td><fmt:formatDate value="${roomOrder.checkoutDate}"
														pattern="yyyy-MM-dd" /></td>
												<td>${roomOrder.totalPrice}</td>
												<td>${roomOrder.roomOrderStatus}</td>
												<td>
													<FORM METHOD="post" ACTION="roomOrder/cmpFindAllInfo"
														style="margin-bottom: 0px;">
														<input type="submit" value="詳情"> <input
															type="hidden" name="roomOrderId"
															value="${roomOrder.roomOrderId}">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</table>
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

<script src="common/datetimepicker/jquery.js"></script>
<script src="common/datetimepicker/jquery.datetimepicker.full.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>

    $.datetimepicker.setLocale('zh');
    $('#start_date').datetimepicker({
       theme: '',              //theme: 'dark',
       timepicker:false,       //timepicker:true,
       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: new Date()       // value:   new Date(),
       //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
       //startDate:	            '2017/07/10',  // 起始日
       //minDate:               '-1970-01-01', // 去除今日(不含)之前
       //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
    });
    
    $('#end_date').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker:false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format:'Y-m-d',         //format:'Y-m-d H:i:s',
 	    value: new Date()       // value:   new Date(),
        //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        //startDate:	            '2017/07/10',  // 起始日
        //minDate:               '-1970-01-01', // 去除今日(不含)之前
        //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
     });

</script>
</body>
</html>