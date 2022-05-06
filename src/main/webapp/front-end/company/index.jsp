<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商首頁</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<h1>廠商首頁</h1>
	${sessionScope.loginCompany.cmpId}
	<a href="roomOrder/getAllRoomOrders?cmpId=${sessionScope.loginCompany.cmpId}">訂單管理</a>
	<a href="reservation/getAllRoomtypes">房數管理</a>
	<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">廠商資料</a>${requestScope.errorInfo}<br><br>
	<a href="roomtype/getAllRoomtypes">房型管理</a><br><br>
</body>
</html>