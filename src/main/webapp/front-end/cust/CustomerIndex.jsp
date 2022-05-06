<%@page import="com.taiwan.beans.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<form method="post" action="cust/CustomerInformation">
		<input type="submit" value="會員基本資料"> 
		<input type="hidden" name="customer" value="${customer.custId}">
	</form>

	<form method="post" action="cust/ShowFollow">	
		<input type="submit" value="關注廠商">
		<input type="hidden" name="customer" value="${customer.custId}">
	</form>
	<form method="post" action="cust/ShowCustCoupon">
		<input type="submit" value="管理優惠劵">
	</form>
	<form method="post" action="cust/showRepCmp">
		<input type="submit" value="瀏覽檢舉">
		<input type="hidden" name="customer" value="${customer.custId}">
	</form>
	<form method="post" action="cust/ShowEmail">
		<input type="submit" value="訊息管理">
	</form>
	<form method="post">
		<input type="submit" value="瀏覽訂單">
	</form>
</body>
</html>