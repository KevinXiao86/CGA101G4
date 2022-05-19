<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Dashboard - NiceAdmin Bootstrap Template</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

<style type="text/css">
.sidebar-nav .nav-content a {
    display: flex;
    align-items: center;
    font-size: 14px;
    font-weight: 600;
    color: #012970;
    padding: 10px 0 10px 40px;
    transition: 0.3s;
}
.sidebar-nav .nav-content a i {
    font-size: 6px;
    margin-right: 8px;
    line-height: 0;
    border-radius: 50%;
}
</style>

</head>
<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center" style="width: 0px; padding: 0px; top: 70px;">
		<i class="bi bi-list toggle-sidebar-btn" style="background-color: #fff; padding: 0 10px; border-radius: 10px;"></i>
	</header>

	<!-- ======= Sidebar ======= -->
	<aside id="sidebar" class="sidebar" style="margin-top: 10px;">

		<ul class="sidebar-nav" id="sidebar-nav">
			<li class="nav-item" style="margin-top: 40px;">
				<a class="nav-link collapsed" href="cust/CustomerInformation">
					<i class="bi bi-person"></i> 
					<span>基本資料</span>
				</a>
			</li>
			<!-- End Profile Page Nav -->

			<li class="nav-item">
				<a class="nav-link collapsed" href="cust/ShowFollow">
					<i class="bi bi-grid"></i>
					<span>關注廠商</span>
				</a>
			</li>
			<!-- End Dashboard Nav -->

			<li class="nav-item">
				<a class="nav-link collapsed" href="cust/ShowCustCoupon">
				<i class="bx bxs-coupon"></i>
				<span>管理優惠劵</span>
				</a>
			</li>
			<!-- End Coupon Page Nav -->

			<li class="nav-item">
				<a class="nav-link collapsed" href="cust/showRepCmp">
					<i class="bx bxs-error-circle"></i>
					<span>瀏覽檢舉</span>
				</a>
			</li>
			<!-- End Report Page Nav -->

			<li class="nav-item">
				<a class="nav-link collapsed" data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-envelope"></i><span>訊息管理</span><i class="bi bi-chevron-down ms-auto"></i>
				</a>
				<ul id="tables-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
					<li>
						<a href="cust/ShowEmail"> 
							<i class="bi bi-circle"></i><span>與平台聯絡</span>
						</a>
					</li>
					<li>
						<a href="cust/ShowRoomMail">
							<i class="bi bi-circle"></i><span>住宿信箱</span>
						</a>
					</li>
				</ul>
			</li>
			<!-- End Mail Nav -->

			<li class="nav-item">
				<a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
					<i class="bi bi-journal-text"></i><span>瀏覽訂單</span><i class="bi bi-chevron-down ms-auto"></i>
				</a>
				<ul id="forms-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
					<li>
			            <a href="<%=request.getContextPath()%>/front-end/addOrder/selectOrder.jsp">
			               <i class="bi bi-circle"></i><span>訂房訂單</span>
			            </a>
<!-- 						<FORM METHOD="post" ACTION=""> -->
<!-- 							<input type="hidden" name="action" value=""> -->
<!-- 							<input type="hidden" name="custId" value="">  -->
<!-- 							<input class="allType" type="submit" value="訂房訂單"> -->
<!-- 						</FORM> -->
					</li>
					<li>
						<FORM METHOD="post" ACTION="tktOrder/getCustId">
							<input type="hidden" name="action" value="get_order_cust">
							<input type="hidden" name="custId" value="${customer.custId}"> 
							<input class="allType" type="submit" value="◯ 票券訂單">
						</FORM>
					</li>
				</ul>
			</li>
			<!-- End Order Nav -->

		</ul>

	</aside>
	<!-- End Sidebar-->

	<!-- JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/main.js"></script>

</body>

</html>