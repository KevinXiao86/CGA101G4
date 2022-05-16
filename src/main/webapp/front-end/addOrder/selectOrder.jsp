<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.service.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.taiwan.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>台玩</title>
<!--  Favicon title 小圖 -->
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<style>
table {
	text-align: center;
}
</style>

</head>
<body>
	<jsp:useBean id="orderSvc" scope="page"
		class="com.taiwan.service.impl.RoomOrderServiceImpl" />
	<jsp:useBean id="itemSvc" scope="page"
		class="com.taiwan.service.roomItem.impl.RoomItemServiceImpl" />
	<jsp:useBean id="roomtypeSvc" scope="page"
		class="com.taiwan.service.roomtype.impl.RoomtypeService12" />
	<%
	//假設會員10000已登入
	// 	CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
	// 	CustomerServiceImpl custSvc = new CustomerServiceImpl();
	// 	CustomerVO custVO = custSvc.getAll(customerVO.getCustId());
	// 	request.setAttribute("custVO", custVO);
	%>
	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
	<jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>



	<main id="main" class="main"
		style="padding-left: 70px; height: 1200px; padding-top: 40px;">

		<div class="pagetitle">
			<h1>訂房訂單</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">首頁</li>
					<li class="breadcrumb-item">瀏覽訂單</li>
					<li class="breadcrumb-item active">訂房訂單</li>
				</ol>
			</nav>
		</div>

		<div class="w-80 p-3 "
			style="background-color: #eee; margin: 50px auto 30px;">

			<table border="2" class="table table-hover">
				<thead>
					<tr>
						<th>訂單編號</th>
						<th>房型</th>
						<th>預定日期</th>
						<th>入住日期</th>
						<th>退房日期</th>
						<th>訂單金額</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="orderVO"
						items="${orderSvc.searchRoomOrderbyCustId(customer.custId)}">

						<c:if
							test="${not empty orderVO}">
							<tr
								onclick="window.document.location='<%=request.getContextPath()%>/roomOrder12/seeItemByOrder?order=${orderVO.roomOrderId}';">
								<!--      orderId是從req.getParameter("orderId"); 來的-->
								<th scope="row">${orderVO.roomOrderId}</th>
								<td>${roomtypeSvc.searchRoomtype(itemSvc.findByOrderId(orderVO.roomOrderId).roomId).roomtypeName}</td>
								<td>${orderVO.roomOrderDate}</td>
								<td><fmt:formatDate
										value="${orderVO.roomOrderCheckinDate}" pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate
										value="${orderVO.roomOrderCheckoutDate}" pattern="yyyy-MM-dd" /></td>
								<td>TWD ${orderVO.roomOrderTotalPrice}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>




	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>

</body>
</html>