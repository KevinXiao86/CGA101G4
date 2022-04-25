<%@page import="com.taiwan.beans.CouponVO"%>
<%@page import="java.util.List"%>
<%@page import="com.taiwan.service.impl.CouponServiceImpl"%>
<%@page import="com.taiwan.service.CouponService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

List<CouponVO> list =(List<CouponVO>)request.getAttribute("list");
pageContext.setAttribute("list", list);

%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>所有優惠券資料 CouponFindAll</title>
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

	<h1>所有優惠券</h1>

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
			<th>優惠券編號</th>
			<th>優惠券名稱</th>
			<th>優惠券介紹</th>
			<th>折扣</th>
			<th>開始日期</th>
			<th>結束日期</th>
			<th>圖片</th>
			<th>狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
<%-- 		<%@ include file="page1.file" %>  --%>
		<c:forEach items="${list}" var="couponVO" >
			<tr>
				<td>${couponVO.copId}</td>
				<td>${couponVO.copName}</td>
				<td>${couponVO.introduce}</td>
				<td>${couponVO.discount}</td>
				<td><fmt:formatDate value="${couponVO.startdate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${couponVO.enddate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><img src="${couponVO.img}"/></td>
				<td>
					<FORM METHOD="post"	ACTION="coupon/copStatusChange" style="margin-bottom: 0px;">
						<input type="submit" name="status" value="${couponVO.status}">
						<input type="hidden" name="copId" value="${couponVO.copId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"	ACTION="coupon/cop2Update" style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						 <input type="hidden" name="copId" value="${couponVO.copId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="coupon/copDelete" style="margin-bottom: 0px;">
						<input type="submit" value="刪除">
						 <input type="hidden" name="copId" value="${couponVO.copId}">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="page2.file" %> --%>

</body>
</html>