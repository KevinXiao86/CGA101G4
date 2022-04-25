<%@page import="com.taiwan.beans.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
CustomerVO customerVO = (CustomerVO) request.getAttribute("customer");
request.setAttribute("customer", customerVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="CustomerInformation">

		<input type="submit" value="會員基本資料"> <input type="hidden"
			name="customer" value="${customer.custId}">
	</form>

	<form method="post">
		<input type="submit" value="關注廠商">
	</form>
	<form method="post">
		<input type="submit" value="管理優惠劵">
	</form>
	<form method="post">
		<input type="submit" value="瀏覽檢舉">
	</form>
	<form method="post">
		<input type="submit" value="常用旅伴">
	</form>
	<form method="post">
		<input type="submit" value="瀏覽訂單">
	</form>
</body>
</html>