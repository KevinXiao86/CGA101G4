<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.service.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.taiwan.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>

	<!-- 	<FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/roomOrder12/seeItemByOrder" -->
	<!-- 		name="form1"> -->
	<!-- 		<table> -->
	<jsp:useBean id="orderSvc" scope="page"
		class="com.taiwan.service.impl.RoomOrderServiceImpl" />
	<jsp:useBean id="itemSvc" scope="page"
		class="com.taiwan.service.roomItem.impl.RoomItemServiceImpl" />
	<jsp:useBean id="roomtypeSvc" scope="page"
		class="com.taiwan.service.roomtype.impl.RoomtypeService12" />
	<%
	//假設會員10000已登入
	int i = 10000;
	CustomerServiceImpl custSvc = new CustomerServiceImpl();
	CustomerVO custVO = custSvc.getAll(i);
	request.setAttribute("custVO", custVO);
	%>

<div class="w-75 p-3 " style="background-color: #eee;">

	<table border="2" class="table table-hover">
		<thead>
			<tr>
				<th scope="col">住房訂單編號</th>
				<th scope="col">房型</th>
				<th scope="col">預定日期</th>
				<th scope="col">入住日期</th>
				<th scope="col">退房日期</th>
				<th scope="col">訂單金額</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="orderVO"
				items="${orderSvc.searchRoomOrderbyCustId(custVO.custId)}">
				<tr
					onclick="window.document.location='<%=request.getContextPath()%>/roomOrder12/seeItemByOrder?order=${orderVO.roomOrderId}';">
					<!--      orderId是從req.getParameter("orderId"); 來的-->
					<th scope="row">${orderVO.roomOrderId}</th>
					<td >${roomtypeSvc.searchRoomtype(itemSvc.findByOrderId(orderVO.roomOrderId).roomId).roomtypeName}</td>				
					<td >${orderVO.roomOrderDate}</td>
					
					<c:set value="${orderVO.roomOrderCheckinDate}" var="ckinString" />
					<fmt:parseDate value="${ckinString}" var="ckin"
                pattern="yyyy-MM-dd HH:mm:ss" />
            <td ><fmt:formatDate value="${ckin }" pattern="yyyy-MM-dd" /></td>
            
            <c:set value="${orderVO.roomOrderCheckoutDate}" var="ckoutString" />
					<fmt:parseDate value="${ckoutString}" var="ckout"
                pattern="yyyy-MM-dd HH:mm:ss" />
					<td ><fmt:formatDate value="${ckout }" pattern="yyyy-MM-dd" /></td>
					<td >TWD ${orderVO.roomOrderTotalPrice}</td>
				</tr>
			</c:forEach>



		</tbody>
	</table>
	</div>
	 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>