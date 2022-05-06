<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="com.taiwan.beans.RoomOrder"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
List<RoomOrder> list = (List<RoomOrder>) request.getAttribute("roomOrders");
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
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


	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">搜尋全部訂房訂單</h1>
				</div>
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

			<table>
				<tr>
					<th>訂房訂單編號</th>
					<th>訂房會員編號</th>
					<th>訂單成立日期</th>
					<th>入住時間</th>
					<th>退房時間</th>
					<th>總金額</th>
					<th>訂單狀態</th>
					<th>詳情</th>
					<th>預訂表</th>

				</tr>
				<c:forEach items="${list}" var="roomOrder">
					<tr>
						<td>${roomOrder.roomOrderId}</td>
						<td>${roomOrder.custId}</td>
						<td><fmt:formatDate value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd" /></td>
						<td>${roomOrder.totalPrice}</td>
						<td>${roomOrder.roomOrderStatus}</td>
						<td>
							<FORM METHOD="post" ACTION="roomOrder/cmpFindAllInfo" style="margin-bottom: 0px;">
								<input type="submit" value="詳情"> 
								<input type="hidden" name="roomOrderId" value="${roomOrder.roomOrderId}">
							</FORM>
						</td>
						<td><a href="#">預定表</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>



</body>
</html>