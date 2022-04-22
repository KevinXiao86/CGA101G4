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
	<a href="company/logout">登出</a><br>
	<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">廠商資料</a><br>
	
	<c:forEach begin="1" end="10" var="i">
		${i}
	</c:forEach><br>
	
	${sessionScope.loginCompany.cmpName}
</body>
</html>