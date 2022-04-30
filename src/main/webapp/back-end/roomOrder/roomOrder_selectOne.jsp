<%@page import="com.taiwan.beans.RoomOrder"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>所有訂房訂單資料 RoomOrderFindAll</title>
<style>
#page-wrapper {
    background-color: rgb(221, 221, 241) !important;
    height: 600px; 
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
					<h1 class="page-header">單筆訂房訂單查詢</h1>
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
					<th>廠商編號</th>
					<th>訂單成立日期</th>
					<th>入住時間</th>
					<th>退房時間</th>
					<th>總金額</th>
					<th>訂單狀態</th>
					<th>詳情</th>
				</tr>
				<tr>
					<td>${roomOrder.roomOrderId}</td>
					<td>
						<FORM METHOD="post" ACTION="roomOrder/selectCustById" style="margin-bottom: 0px;">
							<input type="submit" value="${roomOrder.custId}">
							<input type="hidden" name="custId" value="${roomOrder.custId}">
						</FORM>
					</td>
					<td>
					<FORM METHOD="post" ACTION="roomOrder/selectCmpById" style="margin-bottom: 0px;">
							<input type="submit" value="${roomOrder.cmpId}">
							<input type="hidden" name="cmpId" value="${roomOrder.cmpId}">
						</FORM>
					</td>
					<td><fmt:formatDate value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd"/></td>
					<td>${roomOrder.totalPrice}</td>
					<td>${roomOrder.roomOrderStatus}</td>
					<td>
						<FORM METHOD="post" ACTION="roomOrder/findAllInfo" style="margin-bottom: 0px;">
							<input type="submit" value="詳情">
							<input type="hidden" name="roomOrderId" value="${roomOrder.roomOrderId}">
						</FORM>
					</td>
				</tr>
			</table>


			<div>
				<a href='back-end/roomOrder/roomOrder_index.jsp'>回到訂房訂單首頁</a>
			</div>


		</div>
	</div>





</body>
</html>