<%@page import="com.taiwan.service.impl.CompanyServiceImpl12"%>
<%@page import="java.util.List"%>
<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.service.coupon.CouponService"%>
<%@page import="com.taiwan.service.roomtype.RoomtypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.roomtype.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/head.jsp"%>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" -->
<!-- 	crossorigin="anonymous"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/selectRoom.css">

<meta charset="UTF-8">
<title>台玩</title>
<!--  Favicon title 小圖 -->
<link rel ="icon" href="<%=request.getContextPath() %>/static/img/core-img/favicon.ico">
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%--@ include file="/common/head.jsp"--%>
<style type="text/css">
.ratings {
	position: relative;
	vertical-align: middle;
	display: inline-block;
	color: #b1b1b1;
	overflow: hidden;
}

.full-stars {
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #fde16d;
}

.empty-stars:before, .full-stars:before {
	content: "\2605\2605\2605\2605\2605";
	font-size: 14pt;
}

.empty-stars:before {
	-webkit-text-stroke: 1px #848484;
}

.full-stars:before {
	-webkit-text-stroke: 1px orange;
}
</style>
</head>
<body>
	 	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>

	<%
	CouponService couponService = ControllerUtil.getBean(CouponService.class);
	request.setAttribute("couponService", couponService);
	System.out.println(couponService);

	RoomtypeService roomtypeService = ControllerUtil.getBean(RoomtypeService.class);
	%>
	<%

	//假設廠商20011
	int cmpId = Integer.valueOf(session.getAttribute("cmpId").toString());
	List<Roomtype> roomtypeVOs = roomtypeService.getAllRoomtypes(cmpId);
	request.setAttribute("roomtypeVOs", roomtypeVOs);

	CompanyServiceImpl12 cmpSvc12 = new CompanyServiceImpl12();
	Company cmpVO = cmpSvc12.searchCmpByCmpId(cmpId);
	request.setAttribute("cmpVO", cmpVO);
	%>
	<jsp:useBean id="RTSvc" scope="page"
		class="com.taiwan.service.impl.ReservationServiceImpl12" />
	<jsp:useBean id="CustCouponSvc12" scope="page"
		class="com.taiwan.service.impl.CustCouponServiceImpl12" />
	<jsp:useBean id="roomImgSvc12" scope="page"
		class="com.taiwan.service.impl.roomImgServiceImpl12" />
	<jsp:useBean id="RoomItemSvc" scope="page"
		class="com.taiwan.service.roomItem.impl.RoomItemServiceImpl" />

	 
	 

	<main class="result-main d-flex flex-d-col jc-center ai-center w-100p">
		<!--Hotel-->

		<section class="result-sel-room mb-m w-100p"></section>
		<section class="result-sel-room mb-m w-100p"></section>
		<section class="result-sel-room mb-m w-100p"></section>
		<!-- Select your date -->
		<section class="result-sel-room mb-m w-100p">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 col-md-7 result-room-info">
						<div class="d-flex ai-center result-room-info-top">
							<h3 class="fz-m fw-bold color-basic-b mr-1 mb-tiny">${cmpVO.cmpName}</h3>

						</div>
						<p class="color-primary fz-xxs mb-xs">${cmpVO.location}</p>
						<p class="fz-xxs">${cmpVO.cmpIntroduce}</p>
						<h5 class="fz-m fw-bold color-basic-b mr-1 mb-tiny">訂房須知</h5>
						<p class="fz-xxs">${cmpVO.notice}</p>
						<h5 class="fz-m fw-bold color-basic-b mr-1 mb-tiny">取消政策</h5>
						<p class="fz-xxs">${cmpVO.canx}</p>
					</div>
				</div>
				<div class="row">
					<h2 class="col-12 seb-tit mb-xs">Select Your Date</h2>
					<ul class="col-12 d-flex ai-center result-sel-room-list">
						<li class="select-tab mr-tiny">開始日期:<input type="date"
							id="ckin"  value="${dateMap.ckin}">${errorMsgs.ckin}</li>
						<li class="select-tab mr-tiny">結束日期:<input type="date"
							id="ckout"  value="${dateMap.ckout}">${errorMsgs.ckout}</li>
						<li class="select-tab mr-tiny">優惠券:<br><select id="custCopId">
								<option value="0">請選擇優惠券</option>
								<c:if test="${not empty customer}">
								<c:forEach var="CustCouponVO"
									items="${CustCouponSvc12.searchCustCoupon(customer.custId)}">
									<option value="${CustCouponVO.custCopId }"
										${(param.copId==CustCouponVO.custCopId)? 'selected':'' }>${couponService.findById(CustCouponVO.copId).copName}$${couponService.findById(CustCouponVO.copId).discount}</option>
								</c:forEach>
								</c:if>
						</select></li>
						<li class="color-primary fw-bold">EDIT DETAIL</li>
					</ul>
				</div>
			</div>
		</section>
		<!-- room type -->
		<section class="result-room-type mb-lg">
			<div class="container">
				<!--/******************************/-->
							<p></p>
						<h5 class="fz-m fw-bold color-basic-b mr-1 mb-tiny text-danger">${errorMsgs.amount}</h5>

				<div class="row result-b-btm-sec pb-tiny result-room-type-tit">
					<div class="col-lg-6 col-md-7">
						<h5 class="fw-bold">房型</h5>
					</div>
					<div class="col-lg-2 col-md-1 p-0">
						<h5 class="ta-center fw-bold">人數</h5>
					</div>
					<div class="col-lg-2 col-md-2 p-0">
						<h5 class="ta-center fw-bold">價錢</h5>
					</div>
					<div class="col-lg-2 col-md-2 p-0">
						<h5 class="ta-center fw-bold">房數</h5>
					</div>
				</div>
				<!--/******************************/-->
				<c:forEach var="roomtypeVO" items="${roomtypeVOs}">
				<c:if test="${roomtypeVO.roomtypeStatus eq '上架'}">
				<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/roomOrder12/roomOrder">
					<div class="row pt-xs pb-xs result-b-btm-sec">
						<div class="col-lg-6 col-md-7">
							<div class="type-list d-flex">
								<div class="type-list-img">
									<div id="${roomtypeVO.roomtypeName}" class="carousel slide"
										data-ride="carousel">
										<div class="carousel-inner">
											<c:forEach var="imgs"
												items="${roomImgSvc12.searchImgByRoomId(roomtypeVO.roomtypeId) }">
												<c:choose>
													<c:when
														test="${imgs.roomImg eq roomImgSvc12.searchImgByRoomId(roomtypeVO.roomtypeId)[1].roomImg}">
														<div class="carousel-item active">
															<img
																src="<%=request.getScheme()
        + "://"
        + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath()+"/"%>${imgs.roomImg}"
																class="d-block w-100" alt="">
														</div>
													</c:when>
													<c:otherwise>
														<div class="carousel-item">
															<img
																src="<%=request.getScheme()
        + "://"
        + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath()+"/"%>${imgs.roomImg}"
																class="d-block w-100" alt="">
														</div>

													</c:otherwise>
												</c:choose>
											</c:forEach>
											<a class="carousel-control-prev"
												href="#${roomtypeVO.roomtypeName}" role="button"
												data-slide="prev"> <span
												class="carousel-control-prev-icon" aria-hidden="true"></span>
												<span class="sr-only">Previous</span>
											</a> <a class="carousel-control-next"
												href="#${roomtypeVO.roomtypeName}" role="button"
												data-slide="next"> <span
												class="carousel-control-next-icon" aria-hidden="true"></span>
												<span class="sr-only">Next</span>
											</a>
										</div>
									</div>
								</div>
								<div class="type-list-info">
									<div class="mb-1">
										<p class="fw-bold">${roomtypeVO.roomtypeName}</p>
										<p class="fz-ets">
										<div class="ratings">
											<div class="empty-stars"></div>
											<div class="full-stars"
												style="width:${roomtypeVO.totalScore/roomtypeVO.totalPeople*20}%"></div>
										</div>
										共${roomtypeVO.totalPeople}人評分
										</p>
									</div>
									<ul
										class="d-flex flex-wrap-w fz-xxs color-tertiary room-fea-list">
										<li class="d-flex ai-center w-50p mb-tiny">
											<p class="d-md-none d-lg-block">平方公尺:${roomtypeVO.roomtypeArea}</p></li>
										
										</li>
										<li class="d-flex ai-center w-50p d-md-none d-lg-block">
											<p class="d-md-none d-lg-block">房型介紹:${roomtypeVO.roomtypeIntroduce}</p>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div
							class="col-lg-2 col-md-2 d-flex jc-center ai-center flex-d-col">
													<p> ${roomtypeVO.roomtypePeople}人</p>

						</div>
						<div
							class="col-lg-2 col-md-2 d-flex jc-center ai-center flex-d-col">
													<p>TWD ${roomtypeVO.roomtypePrice}</p>
							<p class="fz-ets">每晚</p>
						</div>
						<div
							class="col-lg-2 col-md-2 d-flex jc-center ai-center color-basic-b">
							<select name="amount">
									<c:forEach var="amount" begin="1"
										end="${roomtypeVO.roomtypeAmount }">
										<option value="${amount}"
											${(param.amount==amount)? 'selected':'' }>${amount}</option>
									</c:forEach>
							</select>
							
		<input type="hidden" name="roomId" value="${roomtypeVO.roomtypeId}" >
		<input type="hidden" name="ckin" class="ckin" value="${dateMap.ckin}" >
		<input type="hidden" name="ckout" class="ckout" value="${dateMap.ckout}" >
		<input type="hidden" name="custCopId" class="custCopId" value="0">
		<input type="hidden" name="custId" value="${customer.custId}" />
		<input type="submit" value="送出預定">
							
						</div>
					</div>
				</FORM>
				</c:if>
				</c:forEach>
				

			</div>
		</section>




	</main>

</body>

<%--"/CGA101G4/front-end/addOrder" --%>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>



<script>
amount=new Array();
<%--amount[0]=[1, 2, 3];	
amount[1]=[1, 2, 3];	
amount[2]=[1, 2];			
amount[3]=[1,2,3,4];	--%>			
var cmpRoomtype =0;
<c:forEach var="roomtypeVO" items="${roomtypeVOs}">
amount[cmpRoomtype]=[1];

	for(i=2;i<="${roomtypeVO.roomtypeAmount }";i++){
		amount[cmpRoomtype].push(i);
	}
	<%--
	<c:forEach var="i" begin="1" end="${roomtypeVO.roomtypeAmount }">
	amount[cmpRoomtype].push(${i});
	console.log(${i});
	console.log(amount[cmpRoomtype]);
	</c:forEach>
	
	--%>
cmpRoomtype=cmpRoomtype+1;

</c:forEach> 




function renew(index){
	console.log(index);
	console.log(amount[0][1]);

	console.log(amount[1]);

	console.log(amount[index]);
	for(var i=0;i<amount[index].length;i++)
		document.form1.amount.options[i]=new Option(amount[index][i], amount[index][i]);	<%--// 設定新選項--%>
	document.form1.amount.length=amount[index].length;	<%--// 刪除多餘的選項--%>
}
</script>
<script>
		$(function(){
	        let date = new Date();
	        let day = date.getDate();
	        let month = date.getMonth() + 1;
	        let month2 = date.getMonth() + 2;
	        let year = date.getFullYear();
	        if (month < 10)
	            month = "0" + month;
	        if (month2 < 10)
	            month2 = "0" + month2;
	        if (day < 10)
	            day = "0" + day;
	        let today = year + "-" + month + "-" + day; 
	        let lastmonth = year + "-" + month2 + "-" + day;  

            $('#ckin').attr('min', today);
            $('#ckin').attr('max', lastmonth);
            
            let start;
            let startMonth;
            let startval;
            let startDay;
            
           

            $('#ckout').attr('min', '${dateMap.ckin}');
                    
               
            $('#ckin').change(function(){
            start = new Date(Date.parse($('#ckin').val()) + 24*60*60*1000);
            startMonth = start.getMonth() +1;
            startDay=start.getDate();
            if (startMonth < 10)
            	startMonth = "0" + startMonth;
            if (startDay < 10)
            	startDay = "0" + startDay;
            startval = start.getFullYear() + "-" + startMonth + "-" + startDay;
            $('#ckout').attr('min', startval);
            $('#ckout').attr('max', lastmonth);
            $('.ckin').val($('#ckin').val());
            })
            
            $('#ckout').change(function(){
            start = new Date(Date.parse($('#ckout').val()) - 24*60*60*1000);
            startMonth = start.getMonth() +1;
            startDay=start.getDate();
            if (startMonth < 10)
            	startMonth = "0" + startMonth;
            if (startDay < 10)
            	startDay = "0" + startDay;
            startval = start.getFullYear() + "-" + startMonth + "-" + startDay;
            $('#ckin').attr('max', startval);

            $('.ckout').val($('#ckout').val());
            })
             $('#custCopId').change(function(){
           

            $('.custCopId').val($('#custCopId').val());
            })
         
        });
    </script>
</html>