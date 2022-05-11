<%@page import="com.taiwan.beans.CouponVO"%>
<%@page import="com.taiwan.service.customer.CustCouponService"%>
<%@page import="com.taiwan.beans.CustCoupon"%>
<%@page import="com.taiwan.beans.TicketVO"%>
<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>台玩</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/carstyle.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/carstyle2.css" />
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+3:200,300,regular,500,600,700,800,900,200italic,300italic,italic,500italic,600italic,700italic,800italic,900italic"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/c95ced1229.js"
	crossorigin="anonymous"></script>
<style>
	#scrollUp {
	    text-decoration: none;
	    bottom: 60px;
	    right: 60px;
	    font-size: 13px;
	    text-align: center;
	    color: #878787;
	    letter-spacing: 2px;
	    border-bottom: 2px solid #878787;
	    -webkit-transition-duration: 500ms;
	    transition-duration: 500ms;
	    text-transform: uppercase;
	    padding: 5px 10px;
	    line-height: 1;
	}
</style>
<body>
	<%
	List<TicketVO> cartlist = (List<TicketVO>) session.getAttribute("tktlist");
	List<Integer> amountList = (List<Integer>) session.getAttribute("amountList");
	List<CouponVO> couponList = (List<CouponVO>) request.getAttribute("couponList");
	%>
	
	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>

	<!-- 頁面 -->
	<form action="cart/do" method="post" style="margin-top: 90px">
		<h3 class="text-center">結帳</h3>
		<!-- Progress bar -->
		<div class="progressbar">
			<div class="progress" id="progress"></div>
			<div class="progress-step progress-step-active" data-title="商品確認"></div>
			<div class="progress-step" data-title="填寫資料"></div>
			<div class="progress-step" data-title="訂單確認"></div>
		</div>

		<!-- Steps -->
		<%
		if (cartlist == null || cartlist.size() == 0) {
		%>
		<div class="form-step form-step-active">
			<div style="margin-left: 18%;">
				<h2 class="section-heading">購物車</h2>
				<div class="product-card">
					<div class="card">
						<h4 style="margin-bottom: 100px; margin-left:150px">
						您的購物車目前無商品 𓆡𓆝𓆟𓆜 𓆞𓆝𓆟 𓆜𓆞</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="order-button-payment" style="width:30%;display:inline-block;margin:-20px auto 50px 560px;">
			<a href="<%=request.getContextPath()%>/front-end/homepage/index.jsp"><input type="submit" value="繼續購物" /></a>
		</div>
	
		<%
		}
		if (cartlist != null && (cartlist.size() > 0)) {
		%>
		<div class="form-step form-step-active">
			<div style="margin-left: 18%;">
				<h2 class="section-heading">購物車</h2>
				<div class="product-card">
					<div class="card">
						<table>
							<tr class="product-name">
								<th></th>
								<th></th>
								<th>商品編號</th>
								<th>商品名稱</th>
								<th>價格</th>
								<th>數量</th>
								<th>小計</th>
							</tr>
							<%
							for (int index = 0; index < cartlist.size(); index++) {
								TicketVO order = cartlist.get(index);
							%>
							<tr>
								<td><%=index + 1%></td>
								<td><img class="product-img"
									src="<%=request.getContextPath()%>/static/img/cart/green-tomatoes.jpg">
								</td>
								<td><%=order.getTktId()%></td>
								<td><%=order.getTktName()%></td>
								<td>NT$ <%=order.getPrice()%></td>
								<td><%=amountList.get(index)%></td>
								<td>NT$ <%=order.getPrice()*amountList.get(index)%></td>
								<td>
									<button class="product-close-btn">
										<ion-icon name="close-outline"></ion-icon>
									</button> 
									<input type="hidden" name="action" value="delete"> 
									<input type="hidden" name="del" value="<%=order.getTktId()%>">
								</td>
							</tr>
							<%}	%>
						</table>
					</div>
				</div>
			</div>

			<div class="cpnline">
				<div class="discount-token">
					<label for="discount-token" class="label-default">優惠券代號</label>
					<div class="wrapper-flex">
<!-- 						<input type="text" name="discount-token" id="discount-token" -->
<!-- 							class="input-default"> -->
						<select name="discount" id="discount">
							<option>請選擇您的優惠券</option>
							<c:forEach var="couponVO" items="${couponList}">
								<option value="${couponVO.discount}">${couponVO.copName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="amount">
					<div class="total">
						<span style="margin-right: 50px;">小計</span><span>$</span><span id="total">${total}</span>
					</div>
					<div class="tax">
						<span style="margin-right: 30px;">折扣金額</span><span>-$</span><span id="tax"></span>
					</div>
					<div class="subtotal">
						<span style="margin-right: 30px;">總金額</span><span>$</span><span id="subtotal"></span>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="<%=request.getContextPath()%>/front-end/homepage/index.jsp" method="get"  style="width:30%;display:inline-block;margin-left:200px; margin-bottom:50px;">
		<div class="order-button-payment">
			<input type="submit" value="繼續購物" />
		</div>
	</form>
	<form action="cart/do" method="post" style="width:30%;float:right;">
		<div class="order-button-payment">
			<input type="hidden" name="action" value="checkout">
			<input type="submit" value="下一步" />
		</div>
	</form>
	<%
	}
	%>
	
	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>

	<script
		src="<%=request.getContextPath()%>/static/js/front-main/jquery/jquery-2.2.4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/front-main/script.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<!-- ionicon link -->
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>
	
<script>
	  $('#discount').change( function() {  
	  	  $('#tax').text($('#discount').val())
	  });
	  
	  
	 
</script>

	
</body>

</html>
