<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約表</title>
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

<script src="common/datetimepicker/jquery.js"></script>
<script src="common/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">
$(() => {
	
	$("#btn").click(() => {		
		var startDate = $("#start_date").val();
		if (startDate.length == 0) {
			 //4. 提示用戶結果
        	$("#errorMsg").html("請輸入有效的日期");
        	//5. 阻止提交 
        	return false;
		}
	
		var endDate = $("#end_date").val();
		if (endDate.length == 0) {
			 //4. 提示用戶結果
        	$("#errorMsg").html("請輸入有效的日期");
        	//5. 阻止提交 
        	return false;
		}
	
		// 去掉錯誤訊息
    	$("#errorMsg").html("");
	});
});
</script>
<style type="text/css">
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
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

						<li><a href="javascript:history.go(-1)" style="font-weight: 900;"> <i
								class="fa fa-dashboard fa-fw"></i> 返回列表
						</a></li>

						<li><a href="company/logout" style="font-weight: 900;"> <i
								class="fa fa-dashboard fa-fw"></i> 登出
						</a></li>
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
						<h1 class="page-header">房型預約表</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>


				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="font-weight: 900;">${requestScope.roomtype.roomtypeName} 
								的預約表如下
							<div>
<!-- 							<br> -->


							<div class="panel-heading">
								<!-- 錯誤訊息回顯 -->
								<span id="errorMsg"
									style="color: red; margin-left: 10px; font-size: 20px;">${requestScope.errorMsg}</span><br>
								<br>
								<form action="reservation/getReservationByDate" style="font-weight: 900;">
									<input type="hidden" name="roomtypeId"
										value="${requestScope.reservations[0].roomtypeId}"> <input
										type="hidden" name="roomtypeAmount"
										value="${requestScope.reservations[0].roomtypeAmount}">
									開始日期: <input name="start_date" id="start_date" type="text"
										value="${requestScope.startDate}"> <br>
									<br> 結束日期: <input name="end_date" id="end_date"
										type="text" value="${requestScope.endDate}">

									<button id="btn">查詢</button>
								</form>
							</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">

										<c:forEach items="${requestScope.reservations}"
											var="Reservations">
											<div
												style="width: 13.5%; display: inline-block; border-width: 1px; border-style: solid; border-radius: 5px; margin: 5px 0 5px 5px">

												<div style="margin-bottom: 5px; margin-left: 5px">${Reservations.dateString.substring(5)}</div>

												<div style="margin-bottom: 10px; margin-left: 15px;">
													<a href="#"
														style="background-color: lightskyblue; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; border-radius: 12px;">
														有效數量:${Reservations.roomtypeAmount - Reservations.reserveAmount}
													</a>
												</div>
											</div>
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
    
    //      1.以下為某一天之前的日期無法選擇
//     	 var endDate =  $('#end_date').val();
//          var somedate1 = new Date(endDate);
//          $('#start_date').datetimepicker({
//              beforeShowDay: function(date) {
//            	  if (  date.getYear() <  somedate1.getYear() || 
//     		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//     		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//                  ) {
//                       return [false, ""]
//                  }
//                  return [true, ""];
//          }});
    
</script>


	<!-- jQuery -->
	<script src="common/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="common/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="common/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="common/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="common/js/dataTables/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="common/js/startmin.js"></script>
</body>
</html>