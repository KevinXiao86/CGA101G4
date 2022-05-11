<%@page import="com.taiwan.beans.TktOrder"%>
<%@page import="com.taiwan.service.TktOrderService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
List<TktOrder> list = (List<TktOrder>) request.getAttribute("tktOrders");
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>多筆票券訂單資料 - tktOrders</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>

<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 1000px;
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
					<h1 class="page-header">多筆票券訂單搜尋</h1>
				</div>
			</div>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message.key}:${message.value}</li>
					</c:forEach>
				</ul>
			</c:if>

			<table>
				<tr>
					<th>票券訂單編號</th>
					<th>訂購人姓名</th>
					<th>訂購人信箱</th>
					<th>訂購人手機號碼</th>
					<th>原始金額</th>
					<th>訂購日期</th>
					<th>總金額</th>
					<th>會員優惠券編號</th>
					<th>訂單詳情</th>
				</tr>
				<c:forEach var="tktOrder" items="${list}">
					<tr>
						<td>${tktOrder.tktOrderId}</td>
						<td>${tktOrder.orderName}</td>
						<td>${tktOrder.orderEmail}</td>
						<td>${tktOrder.orderMobile}</td>
						<td>${tktOrder.originalPrice}</td>
						<td><fmt:formatDate value="${tktOrder.orderdate}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${tktOrder.ttlPrice}</td>
						<td>${(tktOrder.custCopId == 0)? "未使用優惠券" : tktOrder.custCopId}</td>
						<td>
							<FORM METHOD="post" ACTION="tktOrder/findAllInfo" style="margin-bottom: 0px;">
								<input type="submit" value="詳情"> 
								<input type="hidden" name="tktOrderId" value="${tktOrder.tktOrderId}">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>





</body>
</html>