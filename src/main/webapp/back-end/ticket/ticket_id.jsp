<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>單筆票券資料 TicketFindById</title>
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

	<h1>根據票券編號搜尋票券</h1>

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
			<th>票券編號</th>
			<th>票券名稱</th>
			<th>票券總量</th>
			<th>價格</th>
			<th>開始日期</th>
			<th>結束日期</th>
			<th>地點</th>
			<th>狀態</th>
			<th>詳情</th>
			<th>刪除</th>
		</tr>
<%-- 		<%@ include file="coupon/page1.jsp" %>  --%>
			<tr>
				<td>${ticketVO.tktId}</td>
				<td>${ticketVO.tktName}</td>
				<td>${ticketVO.originalAmount}</td>
				<td>${ticketVO.price}</td>
				<td><fmt:formatDate value="${ticketVO.startdate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${ticketVO.enddate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>${ticketVO.location}</td>
				<td>
					<FORM METHOD="post"	ACTION="ticket/tktStatusChange" style="margin-bottom: 0px;">
						<input type="submit" name="status" value="${ticketVO.status}">
						<input type="hidden" name="tktId" value="${ticketVO.tktId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"	ACTION="ticket/tkt2Update" style="margin-bottom: 0px;">
						<input type="submit" value="修改票券">
						<input type="hidden" name="tktId" value="${ticketVO.tktId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="ticket/tktDelete" style="margin-bottom: 0px;">
						<input type="submit" value="刪除">
						 <input type="hidden" name="tktId" value="${ticketVO.tktId}">
					</FORM>
				</td>
			</tr>
	</table>
<%-- 	<%@ include file="coupon/page2.jsp" %> --%>

</body>
</html>