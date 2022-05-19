<%@page import="ch.qos.logback.core.recovery.ResilientSyslogOutputStream"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.roomOrder.RoomOrderServicePlus"%>
<%@page import="com.taiwan.beans.Company"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="com.taiwan.beans.RoomOrder"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
RoomOrderServicePlus roomOrderServicePlus = ControllerUtil.getBean(RoomOrderServicePlus.class);
String custName = request.getParameter("name");
request.setAttribute("custName", custName);

// String roomOrderId = request.getParameter("roomOrderId");
// String status = request.getParameter("status");
// System.out.println("roomOrderId:" + roomOrderId + ", status: " + status);

Company loginCompany = (Company) session.getAttribute("loginCompany");
Integer cmpId = loginCompany.getCmpId();

if(custName == null){
	List<RoomOrder> list = (List<RoomOrder>) request.getAttribute("roomOrders");
	pageContext.setAttribute("list", list);	
	
}else{
	List<RoomOrder> list = roomOrderServicePlus.getCustomersByName(custName);
	pageContext.setAttribute("list", list);	
}
%>


<%
// 創建解析器
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
// 設置時區
dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
Date date = new Date();
String dateString = dateFormat.format(date);
String year = dateString.substring(0, 4);
pageContext.setAttribute("year", year);
String month = dateString.substring(5, 7);
pageContext.setAttribute("month", month);

// Company loginCompany = (Company) session.getAttribute("loginCompany");
// Integer cmpId = loginCompany.getCmpId();
Integer totalPrice = roomOrderServicePlus.getTotalPriceByCmpId(cmpId, year, month);
pageContext.setAttribute("totalPrice", totalPrice);
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
				<a class="navbar-brand" href="#" style="font-weight: 900;">台玩 - 旅遊平台</a>
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
					
					
						<li><a
							href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i> 房型管理
						</a></li>

						<li><a
							href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i> 廠商資料
						</a></li>

						<li><a
							href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
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
						<h1 class="page-header">搜尋全部訂房訂單</h1>
					</div>
				</div>
				<div >
                    <FORM METHOD="post" ACTION="roomOrder/cmpSelectByDate">
                        <b style="font-weight: 900; ">根據訂單成立日期來做查詢</b> <br>
                        <label for="from">From</label>
						<input type="text"  name="startdate" id="start_date">
						<label for="to">to</label>
						<input type="text"  name="enddate" id="end_date">
                        <input type="submit" value="送出">
                    </FORM>
                </div>

				<div style="margin-top: 10px;">
                    <FORM METHOD="post" ACTION="front-end/cmpRoomOrder/cmp_findOrder.jsp">
                        <b style="font-weight: 900; ">請輸入會員姓名查詢訂單</b> <br>
                        <input name="name" id="name" type="text" style="width:90px;" value="${requestScope.custName}">
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
				<div class="row" style="margin-top: 20px;">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="font-weight: 900;">已有的訂單如下</div>
								
							<div class="form-group" style="margin-left: 20px">
								<p style="font-weight: 900; font-size: 20px">
									${pageScope.year}-${pageScope.month} 目前的收入是: 
									<span style="color: red;">${pageScope.totalPrice}</span>
									元
								</p>
							</div>
							
<!-- 							<div class="col-lg-12"> -->
							<span style="color: red; font-size: 20px; font-weight: 900; margin-left: 20px">
								${requestScope.error}
							</span>
<!-- 							</div> -->
							
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">

										<tr>
											<th style="font-weight: 900;">訂房訂單編號</th>
											<th style="font-weight: 900;">訂房會員編號</th>
											<th style="font-weight: 900;">訂單成立日期</th>
											<th style="font-weight: 900;">入住時間</th>
											<th style="font-weight: 900;">退房時間</th>
											<th style="font-weight: 900;">總金額</th>
											<th style="font-weight: 900;">訂單狀態</th>
											<th style="font-weight: 900;">詳情</th>
											<th style="font-weight: 900;">取消訂單</th>

										</tr>
										<c:forEach items="${list}" var="roomOrder">
											<tr>
												<td style="font-weight: 400;">${roomOrder.roomOrderId}</td>
												<td style="font-weight: 400;">${roomOrder.custId}</td>
												<td style="font-weight: 400;"><fmt:formatDate value="${roomOrder.roomOrderDate}"
														pattern="yyyy-MM-dd" /></td>
												<td style="font-weight: 400;"><fmt:formatDate value="${roomOrder.checkinDate}"
														pattern="yyyy-MM-dd" /></td>
												<td style="font-weight: 400;"><fmt:formatDate value="${roomOrder.checkoutDate}"
														pattern="yyyy-MM-dd" /></td>
												<td style="font-weight: 400;">${roomOrder.totalPrice}</td>
												<td style="font-weight: 400;">${roomOrder.roomOrderStatus}</td>
												<td style="font-weight: 400;">
													<FORM METHOD="post" ACTION="roomOrder/cmpFindAllInfo"
														style="margin-bottom: 0px;">
														<input type="submit" value="詳情"> <input
															 type="hidden" name="roomOrderId"
															value="${roomOrder.roomOrderId}">
													</FORM>
												</td>
												
												<td>
												
												
													<c:choose>
														<c:when test="${roomOrder.roomOrderStatus == '已實現'}">
															<FORM METHOD="post" ACTION="#" style="margin-bottom: 0px;">
																<input type="submit" value="不可取消" disabled="disabled" style="cursor: not-allowed;">
															</FORM>
														</c:when>
														
														<c:when test="${roomOrder.roomOrderStatus == '已取消'}">
															<FORM METHOD="post" ACTION="#" style="margin-bottom: 0px;">
																<input type="submit" value="不可取消" disabled="disabled" style="cursor: not-allowed;">
															</FORM>
														</c:when>

														<c:when test="${roomOrder.roomOrderStatus == '正常'}">
															<FORM METHOD="post" ACTION="roomOrder/updateStatus" style="margin-bottom: 0px;">
																<input type="hidden" name="roomOrderId"	value="${roomOrder.roomOrderId}">
																<input type="hidden" name="status"	value="已取消">
																<input type="hidden" name="custName" value="${requestScope.custName}">
																<input type="submit" value="取消">
															</FORM>
														</c:when>
													</c:choose>
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