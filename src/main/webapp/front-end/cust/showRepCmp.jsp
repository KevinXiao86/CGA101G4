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
	</div>
	<div style="display: inline-block; vertical-align: middle">
	<table>
		<thead>
			<tr>
				<td>商家名稱</td>
				<td>檢舉房型</td>
				<td>檢舉原因</td>
				<td>檢舉時間</td>
				<td>狀態</td>
				<td>結果</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="repCmpVO" items="${list}">
				<tr>
					<td>${repCmpVO.getCmpName()}</td>
					<td>${repCmpVO.getRoomtypeName()}</td>
					<td>${repCmpVO.reason}</td>
					<td>${repCmpVO.repCmpDate}</td>
					<td>${repCmpVO.status}</td>
					<td>${repCmpVO.result}</td>
					<td>
						<form method="post" action="cust/DeleteRepCmp">
							<input type="submit" value="刪除"
								${(repCmpVO.status=='未處理')?'':'disabled'}> <input
								type="hidden" value="${repCmpVO.repCmpId}" name="repCmpId">
							<input type="hidden" value="${repCmpVO.custId}" name="custId">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>