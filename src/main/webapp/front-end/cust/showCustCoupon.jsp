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
	<div style="display: inline-block; border: 2px solid blue; vertical-align: middle">
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
	</div>
	
	<div style="display: inline-block; vertical-align: middle">
		<form method="post" action="cust/CustCouponCompositeQuery">
			<label for="copId">優惠券名稱: </label>
			<select id="copId" name="COP_ID">
					<option value=""></option>  
				<c:forEach var="coupon" items="${couponList}">
					<option value="${coupon.copId}" ${(param.COP_ID==coupon.copId)?'selected':''}>${coupon.copName}</option>
				</c:forEach>
			</select>
			
			<label for="discount">優惠券金額: </label><input type="text" name="DISCOUNT" id="discount" value="${param.DISCOUNT}">
			
			<label for="status">狀態: </label>
			<select id="status" name="STATUS">
				<option value=""></option>
				<option value="已使用" ${(param.STATUS=="已使用")?'selected':''}>已使用</option>
				<option value="未使用" ${(param.STATUS=="未使用")?'selected':''}>未使用</option>
				<option value="已過期" ${(param.STATUS=="已過期")?'selected':''}>已過期</option>
			</select>
			
			<input type="submit" value="查詢">
			<input type="hidden" name="CUST_ID" value="${customer.custId}">
		</form>
		
		<table>
			<thead>
				<tr>
					<td>票券名稱</td>
					<td>領取日期</td>
					<td>使用日期</td>
					<td>搭配使用</td>
					<td>優惠券金額</td>
					<td>狀態</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="custCoupon" items="${custCouponList}">
					<tr>
						<td>${custCoupon.copId}</td>
						<td>${custCoupon.getdate}</td>
						<td>${custCoupon.usedate}</td>
						<td>${(custCoupon.roomOrderId==0)?((custCoupon.tktOrderId==0)?'':custCoupon.tktOrderId):custCoupon.roomOrderId}</td>
						<td>${custCoupon.discount}</td>
						<td>${custCoupon.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>