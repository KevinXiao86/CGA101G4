<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>優惠券編號資料 CouponFindById</title>
<style>


table {
	width: 1100px;
	background-color: #ced7e8 !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
/* 	border: 3px solid #CCCCFF; */
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}

input[type="submit"] {
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
	background-color: #ffffff;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	padding: 6px 24px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

input[type="submit"]:hover {
	background: linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
}
</style>


</head>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">藉由編號搜尋優惠券</h1>
				</div>
			</div>
			<%--錯誤列表 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<table border="2px" class="table table-striped table-hover">
			<thead>
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
					<th>發放</th>
<!-- 					<th>刪除</th> -->
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>${couponVO.copId}</td>
					<td>${couponVO.copName}</td>
					<td>${couponVO.introduce}</td>
					<td>${couponVO.discount}</td>
					<td><fmt:formatDate value="${couponVO.startdate}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><fmt:formatDate value="${couponVO.enddate}"	pattern="yyyy-MM-dd HH:mm" /></td>
					<td><img src="${couponVO.img}" /></td>
					<td>
						<FORM METHOD="post" ACTION="coupon/copStatusChange"	style="margin-bottom: 0px;">
							<input type="submit" name="status" value="${couponVO.status}" ${(couponVO.status == "已過期")? "disabled='disabled'":"" }>
							<input type="hidden" name="copId" value="${couponVO.copId}">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="coupon/cop2Update" style="margin-bottom: 0px;">
							<input type="submit" value="修改" ${(couponVO.status == "已過期")? "disabled='disabled'":"" }> 
							<input type="hidden"	name="copId" value="${couponVO.copId}">
						</FORM>
					</td>
					<td>
							<FORM METHOD="post" ACTION="coupon/get" style="margin-bottom: 0px;">
								<input type="submit" value="發放" class="send" ${(couponVO.status == "已過期")? "disabled='disabled'":"" } ${(couponVO.status == "下架")? "disabled='disabled'":"" }> 
								<input type="hidden" name="copId" value="${couponVO.copId}">
							</FORM>
						</td>
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" ACTION="coupon/copDelete" style="margin-bottom: 0px;"> -->
<!-- 							<input type="submit" value="刪除">  -->
<%-- 							<input type="hidden"	name="copId" value="${couponVO.copId}"> --%>
<!-- 						</FORM> -->
<!-- 					</td> -->
				</tr>
				</tbody>
			</table>
			<div>
				<a href='back-end/coupon/cop_index.jsp'>回到優惠券首頁</a>
			</div>
		</div>
	</div>




</body>
</html>