<%@page import="com.taiwan.beans.RoomOrder"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
List<RoomOrder> list=(List<RoomOrder>)request.getAttribute("list");
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>所有訂房訂單資料 RoomOrderFindAll</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
		<h1>所有訂房訂單</h1>

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
		<%@ include file="page1.file" %> 
		<c:forEach items="${list}" var="roomOrder" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${roomOrder.roomOrderId}</td>
				<td>${roomOrder.custId}</td>
				<td>${roomOrder.cmpId}</td>
				<td><fmt:formatDate value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>${roomOrder.totalPrice}</td>
				<td>${roomOrder.roomOrderStatus}</td>
				<td>
					<FORM METHOD="post" ACTION="roomOrder/findAllInfo" style="margin-bottom: 0px;">
						<input type="submit" value="詳情">
						<input type="hidden" name="roomOrderId" value="${roomOrder.roomOrderId}">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file" %>
	
	<div>
		<a href='back-end/roomOrder/roomOrder_index.jsp'>回到優惠券首頁</a>
	</div>
		

</body>
</html>