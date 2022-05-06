<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.service.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

	<!-- 			<tr> -->
	<!-- 				<td>訂單:(已會員10000作範例)</td> -->
	<!-- 				<td><select name="order"> -->
	<%-- 						<c:forEach var="orderVO" items="${orderSvc.searchRoomOrderbyCustId(custVO.custId)}"> --%>
	<%-- 							<option value="${orderVO.roomOrderId }" --%>
	<%-- 								>訂單:${orderVO.roomOrderId}</option> --%>
	<%-- 						</c:forEach> --%>
	<!-- 				</select></td> -->
	<%-- 				<td>${errorMsgs.order}</td> --%>
	<!-- 			</tr> -->
	<!-- 			<br> -->

	<!-- 		</table> -->
	<!-- 		 <input type="submit" value="送出"> -->
	<!-- 	</FORM> -->
	<table border="2">
		<thead>
			<tr>
				<th>住房訂單編號</th>
				<th>房型</th>
				<th>預定日期</th>
				<th>入住日期</th>
				<th>退房日期</th>
				<th>訂單金額</th>
				<th></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="orderVO"
				items="${orderSvc.searchRoomOrderbyCustId(custVO.custId)}">
				<tr
					onclick="window.document.location='<%=request.getContextPath()%>/roomOrder12/seeItemByOrder?order=${orderVO.roomOrderId}';">
					<!--      orderId是從req.getParameter("orderId"); 來的-->
					<td>${orderVO.roomOrderId}</td>
					<td>${roomtypeSvc.searchRoomtype(itemSvc.findByOrderId(orderVO.roomOrderId).roomId).roomtypeName}</td>				
					<td>${orderVO.roomOrderDate}</td>
					<td>${orderVO.roomOrderCheckinDate}</td>
					<td>${orderVO.roomOrderCheckoutDate}</td>
					<th>${orderVO.roomOrderTotalPrice}</th>
				</tr>
			</c:forEach>



		</tbody>
	</table>
</body>
</html>