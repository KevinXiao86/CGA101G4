<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	margin: 20px auto;
}

table td {
	padding: 10px;
	height: 30px;
}

table tbody tr:nth-child(odd) {
	background-color: #999;
}
</style>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<div
		style="display: inline-block; border: 2px solid blue; vertical-align: middle">
		<form method="post" action="cust/CustomerInformation">
			<input type="submit" value="會員基本資料"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowFollow">
			<input type="submit" value="關注廠商"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowCustCoupon">
			<input type="submit" value="管理優惠劵">
		</form>

		<form method="post" action="cust/showRepCmp">
			<input type="submit" value="瀏覽檢舉"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowEmail">
			<input type="submit" value="訊息管理">
		</form>
		<form method="post">
			<input type="submit" value="瀏覽訂單">
		</form>
	</div>
	<div style="display: inline-block; vertical-align: middle">
		<table>
			<thead>
				<tr>
					<td>商家名稱</td>
					<td>取消關注</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="follow" items="${list}">
					<tr>
						<td>${follow.cmpId}</td>
						<td>
							<form method="post" action="cust/DeleteFollow">
								<input type="submit" value="取消關注">
								<input type="hidden" name="custId" value="${follow.custId}"> 
								<input type="hidden" name="cmpId" value="${follow.cmpId}">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>