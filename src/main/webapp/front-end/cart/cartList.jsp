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
<body>
	<%
	List<TicketVO> cartlist = (List<TicketVO>) session.getAttribute("tktlist");
	List<Integer> amountList = (List<Integer>) session.getAttribute("amountList");
	%>
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
				<div class="row h-100 align-items-center">
					<img
						src="<%=request.getContextPath()%>/static/img/ticket-img/logo.jpg"
						alt="" id="bear" style="height: 65px;">
					<div class="col-12 col-sm-5" style="margin-left: 480px">
						<div class="top-social-area">
							<a href="#" data-toggle="tooltip" data-placement="bottom"
								title="購物車"> <i class="fa-solid fa-cart-shopping"
								aria-hidden="true"></i></a> <a href="#" data-toggle="tooltip"
								data-placement="bottom" title="登入"> <i
								class="fa-regular fa-user" aria-hidden="true"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

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
		if (cartlist == null) {
		%>
		<div class="form-step form-step-active">
			<div style="margin-left: 18%;">
				<h2 class="section-heading">購物車</h2>
				<div class="product-card">
					<div class="card">
						<h4 style="margin-bottom: 100px; margin-left:150px">
						您的購物車目前無任何商品</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12">
			<a class="btn original-btn" href="<%=request.getContextPath()%>/front-end/homepage/index.jsp">繼續購物</a>
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
								<td>
									<%=amountList.get(index) %>
<!-- 									<div class="product-qty"> -->
<!-- 										<button id="decrement"> -->
<!-- 											<ion-icon name="remove-outline"></ion-icon> -->
<!-- 										</button> -->
<!-- 										<span id="quantity">1</span> -->
<!-- 										<button id="increment"> -->
<!-- 											<ion-icon name="add-outline"></ion-icon> -->
<!-- 										</button> -->
<!-- 									</div> -->
								</td>
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
						<input type="text" name="discount-token" id="discount-token"
							class="input-default">
						<button class="btn btn-outline">Apply</button>
					</div>
				</div>

				<div class="amount">
					<div class="total">
						<span>小計</span><span id="total"> $${total}</span>
					</div>
					<div class="tax">
						<span>折扣金額</span><span id="tax"> -$0</span>
					</div>
					<div class="subtotal">
						<span>總金額</span><span id="subtotal"> $2.05</span>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="<%=request.getContextPath()%>/front-end/homepage/index.jsp" method="get">
		<div class="col-12">
			<button type="submit" class="btn original-btn">繼續購物</button>
		</div>
	</form>
	<form  action="cart/do" method="post">
		<div class="col-12">
			<input type="hidden" name="action" value="checkout">
			<input type="hidden" name="custId" value="">
			<button type="submit" class="btn original-btn">下一步</button>
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
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


	<script>
        const prevBtns = document.querySelectorAll(".btn-prev");
        const nextBtns = document.querySelectorAll(".btn-next");
        const progress = document.getElementById("progress");
        const formSteps = document.querySelectorAll(".form-step");
        const progressSteps = document.querySelectorAll(".progress-step");

        let formStepsNum = 0;

        nextBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                formStepsNum++;
                updateFormSteps();
                updateProgressbar();
            });
        });

        prevBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                formStepsNum--;
                updateFormSteps();
                updateProgressbar();
            });
        });

        function updateFormSteps() {
            formSteps.forEach((formStep) => {
                formStep.classList.contains("form-step-active") &&
                    formStep.classList.remove("form-step-active");
            });

            formSteps[formStepsNum].classList.add("form-step-active");
        }

        function updateProgressbar() {
            progressSteps.forEach((progressStep, idx) => {
                if (idx < formStepsNum + 1) {
                    progressStep.classList.add("progress-step-active");
                } else {
                    progressStep.classList.remove("progress-step-active");
                }
            });

            const progressActive = document.querySelectorAll(".progress-step-active");

            progress.style.width =
                ((progressActive.length - 1) / (progressSteps.length - 1)) * 100 + "%";
        }
       
    </script>

</body>

</html>
