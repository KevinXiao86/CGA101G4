<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>台玩</title>
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link href="<%=request.getContextPath()%>/static/css/front-main/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/static/css/front-main/bootstrap.min2.css">
<script src="https://kit.fontawesome.com/c95ced1229.js" crossorigin="anonymous"></script>

<style>
.classynav ul li a {
    padding: 0 12px;
    display: block;
    height: 35px;
    font-size: 14px;
    line-height: 34px;
}
.classynav ul li {
    display: inline-block;
    clear: both;
    position: inherit;
    z-index: 10;
}
</style>
<body>
	<!-- Preloader -->
	<div id="preloader">
		<div class="preload-content">
			<div id="original-load"></div>
		</div>
	</div>

	<!-- ##### Header Area Start ##### -->

	<header class="header-area">

		<!-- Top Header Area -->
		<div class="top-header" id="headerFixed">
			<div class="container h-100">
				<div class="h-100 align-items-center" style="display:flex;">
					<a href="<%=request.getContextPath() %>/front-end/homepage/index.jsp"><img src="<%=request.getContextPath()%>/static/img/ticket-img/logo.jpg"
						id="bear" style="height: 65px;"></a>

					<!-- Top Social Area -->
					<!-- Nav Start -->
					<div class="classynav" id="clas">
						<ul style="display: flex;">
							<li><a href="<%=request.getContextPath() %>/cmpList.jsp">住宿</a></li>
							<li><a href="<%=request.getContextPath() %>/front-end/ticket/ticketList.jsp">票券</a></li>
						</ul>

						<!-- Search Form  -->
<!-- 						<div id="search-wrapper"> -->
<!-- 							<form action="#"> -->
<!-- 								<input type="text" id="search" placeholder="Search something..."> -->
<!-- 								<div id="close-icon"></div> -->
<!-- 								<input class="d-none" type="submit" value=""> -->
<!-- 							</form> -->
<!-- 						</div> -->
					</div>
					<div class="col-sm-2" style="position:absolute;right:90px" >
						<div class="top-social-area">
							<a href="<%=request.getContextPath()%>/front-end/cart/cartList.jsp" data-toggle="tooltip" data-placement="bottom"
								title="購物車"> <i class="fa-solid fa-cart-shopping"
								aria-hidden="true"></i></a> 
							<a href="<%=request.getContextPath()%>/front-end/cust/CustomerLogin.jsp" data-toggle="tooltip"
								data-placement="bottom" title="登入"> <i
								class="fa-regular fa-user" aria-hidden="true"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script	src="<%=request.getContextPath()%>/static/js/front-main/jquery/jquery-2.2.4.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>


</body>

</html>