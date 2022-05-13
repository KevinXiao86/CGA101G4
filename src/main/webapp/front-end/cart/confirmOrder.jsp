<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>台玩</title>
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/carstyle.css" />
<link href="https://fonts.googleapis.com/css?family=Source+Sans+3:200,300,regular,500,600,700,800,900,200italic,300italic,italic,500italic,600italic,700italic,800italic,900italic"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/c95ced1229.js"	crossorigin="anonymous"></script>

<style>
.order-detail{
    width: 600px;
    margin: -80px auto 50px;
}
.your-order {
    background: #f2f2f2 none repeat scroll 0 0;
    padding: 30px 40px 45px;
}

.your-order h3 {
    border-bottom: 1px solid #d8d8d8;
    font-size: 30px;
    margin: 0 0 20px;
    padding-bottom: 10px;
    text-transform: uppercase;
    width: 100%;
}

.your-order-table table {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
    border: medium none;
    width: 100%;
}

.your-order-table table th,
.your-order-table table td {
    border-bottom: 1px solid #d8d8d8;
    border-right: medium none;
    font-size: 14px;
    padding: 15px 0;
    text-align: center;
}

.your-order-table table th {
    border-top: medium none;
    font-family: Montserrat, Arial, Helvetica, sans-serif;
    font-weight: normal;
    text-align: center;
    text-transform: uppercase;
    vertical-align: middle;
    white-space: nowrap;
    width: 250px;
}

.your-order-table table .shipping ul li input {
    position: relative;
    top: 2px;
}

.your-order-table table .shipping th {
    vertical-align: top;
}

.your-order-table table .order-total th {
    border-bottom: medium none;
    font-size: 18px;
}

.your-order-table table .order-total td {
    border-bottom: medium none;
}

.your-order-table table tr.cart_item:hover {
    background: #F9F9F9
}

.your-order-table table tr.order-total td span {
    color: #c06b81;
    font-size: 20px;
}
.order-button-payment input {
    background: #232323 none repeat scroll 0 0;
    border: medium none;
    color: #fff;
    font-size: 15px;
    font-weight: 700;
    height: 34px;
    margin: 20px 0 0;
    padding: 0;
    text-transform: uppercase;
    transition: all 0.3s ease 0s;
    width: 100%;
}

.order-button-payment input:hover {
    background: #c06b81
}
</style>

<body>
	
	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>

	<!-- 頁面 -->
	<form action="#" class="form" style="margin:70px 36px;">
		<h3 class="text-center">結帳</h3>
		<!-- Progress bar -->
		<div class="progressbar">
			<div class="progress" id="progress"></div>
			<div class="progress-step progress-step-active" data-title="商品確認"></div>
			<div class="progress-step progress-step-active" data-title="填寫資料"></div>
			<div class="progress-step progress-step-active" data-title="訂單確認"></div>
		</div>
		<div class="form-step">
			<div>
				<a href="#" class="btn btn-next width-50 ml-auto">Next</a>
			</div>
		</div>
	</form>

	<!-- ORDER DETAIL -->
	<div class="order-detail">
		<div class="your-order">
			<h3>訂單成立<i class="fa-solid fa-ballot-check"></i> <i style="font-size:14px;color:#c06b81;"> 請查看電子信箱 <i class="fa-solid fa-square-envelope"></i></i></h3>
			<div class="your-order-table table-responsive">
				<table>
				
					<thead>
						<tr>
							<th class="product-name">訂單編號</th>
							<th class="product-total">${newOrderId}</th>
						</tr>
					</thead>
					<tbody>
						<tr class="cart_item">
							<td class="product-name">訂購人</td>
							<td class="product-total">${tktOrder.orderName}</td>
						</tr>
						<tr class="cart_item">
							<td class="product-name">連絡電話</td>
							<td class="product-total">${tktOrder.orderMobile}</td>
						</tr>
						<tr class="cart_item">
							<td class="product-name">電子信箱</td>
							<td class="product-total">${tktOrder.orderEmail}</td>
						</tr>
					</tbody>
					<tfoot>
<!-- 						<tr class="cart-subtotal"> -->
<!-- 							<th>Cart Subtotal</th> -->
<!-- 							<td><span class="amount">£215.00</span></td> -->
<!-- 						</tr> -->
						<tr class="order-total">
							<th>總金額</th>
							<td><strong><span class="amount">$${tktOrder.ttlPrice}</span></strong></td>
						</tr>
					</tfoot>
				</table>
			</div>

			<div class="order-button-payment">
				<FORM METHOD="post" ACTION="tktOrder/getCustId" >
			        <input type="hidden" name="custId" value="${customer.custId}">
			        <input type="hidden" name="action" value="get_order_cust">
			        <input type="submit" value="查看訂單">
			    </FORM>
			</div>

		</div>
	</div>
	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>


	<script	src="<%=request.getContextPath()%>/static/js/front-main/jquery/jquery-2.2.4.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/script.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<!-- ionicon link -->
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>


	<script>
        $(function () {
            $("#sendOrder").click(function () {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '訂單已成立'
                })
                // Swal.fire({
                //     title: 'Custom width, padding, color, background.',
                //     width: 600,
                //     padding: '3em',
                //     color: '#716add',
                //     background: '#fff url(/images/trees.png)',
                //     backdrop: ` rgba(0,0,123,0.4)
                //                 url("/images/nyan-cat.gif")
                //                 left top
                //                 no-repeat
                //               `
                // })
            });
        });


     
    </script>

</body>

</html>


<!-- == -->
<!-- 
訂單編號
訂單明細

寄信 -->