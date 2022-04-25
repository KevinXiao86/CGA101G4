<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<h1>這是廠商首頁</h1>
	<span id="error_msg">${requestScope.editCompany.message}</span>
	<a href="company/logout">登出</a><br>
	<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">廠商資料</a>${requestScope.errorInfo}<br><br>
	
	<a href="roomtype/getAllRoomtypes?cmpId=${sessionScope.loginCompany.cmpId}">房型管理</a>
</body>
</html>