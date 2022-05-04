<%@page import="com.taiwan.beans.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.beans.TicketVO"%>

<html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
	<form action="#" class="form">
		<h3 class="text-center">結帳</h3>
		<!-- Progress bar -->
		<div class="progressbar">
			<div class="progress" id="progress"></div>
			<div class="progress-step progress-step-active" data-title="商品確認"></div>
			<div class="progress-step" data-title="填寫資料"></div>
			<div class="progress-step" data-title="訂單確認"></div>
		</div>
	</form>
	<!-- <main class="container"> -->
	<div class="item-flex">
		<!--checkout section-->
		<section class="checkout">
			<h2 class="section-heading">結帳清單</h2>
			<div class="payment-form">
				<div class="payment-method">
					<button class="method selected">
						<ion-icon name="card"></ion-icon>
						<span>Credit Card</span>
						<ion-icon class="checkmark fill" name="checkmark-circle"></ion-icon>
					</button>
					<button class="method">
						<ion-icon name="logo-paypal"></ion-icon>
						<span>PayPal</span>
						<ion-icon class="checkmark" name="checkmark-circle-outline"></ion-icon>
					</button>
				</div>
				<div class="post-a-comment-area mt-70">
					<form action="<%=request.getContextPath()%>/tktOrder/creator" method="post" novalidate>
						<div class="row">
							<div class="col-12 col-md-6">
								<div class="group">
									<input type="text" name="orderName" value="${name}" id="name"
										autocomplete=""> <span class="highlight"></span> <span
										class="bar"></span> <label>訂購人姓名</label>${errorMsgs.orderName}
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="group">
									<input type="tel" name="orderMobile" value="${tel}" id="tel">
									<span class="highlight"></span> <span class="bar"></span> <label>連絡電話</label>${errorMsgs.orderMobile}
								</div>
							</div>
							<div class="col-12">
								<div class="group">
									<input type="email" name="orderEmail" value="${email}"
										id="email"> <span class="highlight"></span> <span
										class="bar"></span> <label>Email</label>${errorMsgs.orderEmail}
								</div>
							</div>
							<div class="col-12">
								<div class="group"
									style="display: inline-flex; position: relative;">
									<input type="text" name="card" class="card" maxlength="4"
										style="width: 150px; text-align: center;"> － <input
										type="text" name="card" class="card" maxlength="4"
										style="width: 150px; text-align: center;"> － <input
										type="text" name="card" class="card" maxlength="4"
										style="width: 150px; text-align: center;"> － <input
										type="text" name="card" class="card" maxlength="4"
										style="width: 150px; text-align: center;"> <span
										class="highlight"></span> <span class="bar"></span> <label>信用卡卡號</label>${errorMsgs.card}
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="group">
									<input type="text" name="cvv" id="cvv"> <span
										class="highlight"></span> <span class="bar"></span> <label>安全碼</label>${errorMsgs.cvv}
								</div>
							</div>
							<div class="col-12">
								<input type="hidden" name="action" value="insert_order">
								<button type="submit" class="btn original-btn">送出訂單</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</section>
		<!--cart section-->
		<section>
			<div class="cart-item-box">
				<h2 class="section-heading">商品明細</h2>
				<table style="width: 350px; border: 1;">
					<%
					for (int index = 0; index < cartlist.size(); index++) {
						TicketVO order = cartlist.get(index);
					%>
					<tr>
						<td><img
							src="<%=request.getContextPath()%>/static/img/cart/green-tomatoes.jpg"
							class="product-img" style="margin: 0 -40px 0 -10px;"></td>
						<td><%=order.getTktName()%></td>
						<td>${amount}張</td>
						<td><%=order.getPrice()%>元</td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<hr>
			<div class="cart-item-box">
				<div class="subtotal">
					<span>小計</span><span> $${total}</span><br> <span>折扣金額</span><span>
						$</span><br> <span>總金額</span><span> $${total }</span>
					<%-- 記得減掉coupon的折扣金額 --%>
				</div>
			</div>
		</section>
	</div>
	<div class="btns-group">
		<a href="" class="btn btn-prev">返回</a>
		<button type="submit" class="btn original-btn" id="sendOrder">送出訂單</button>
		<!--                 <input type="hidden" name="action" value="insert_order"> -->
		<!-- 				<input type="hidden" name="custId" value=""> -->
	</div>



	<!-- #### Footer start #### -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="footer-col">
					<h4>台玩</h4>
					<ul>
						<li><a href="#">關於台玩</a></li>
						<li><a href="#">使用者條款</a></li>
						<li><a href="#">隱私權保護政策</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>旅人&合作夥伴</h4>
					<ul>
						<li><a href="#">關於合作夥伴</a></li>
						<li><a href="#">成為供應商</a></li>
						<li><a href="#">供應商登入</a></li>
						<li><a href="#">人才招募</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>常見問題</h4>
					<ul>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">三大保證</a></li>
						<li><a href="#">聯絡客服</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>follow us</h4>
					<div class="social-links">
						<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i
							class="fab fa-twitter"></i></a> <a href="#"><i
							class="fab fa-instagram"></i></a> <a href="#"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
			</div>
		</div>
		<div id="copyright">
			Copyright &copy; 2022 All rights reserved | This template is made
			with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a
				href="https://colorlib.com" target="_blank">Colorlib</a>
		</div>

	</footer>
	<!-- ##### Footer Area End ##### -->

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

        // 信用卡
        $("input.card").on("keydown", function (e) {
            if ((e.which >= 48 && e.which <= 57) || e.which == 8) {
                if (e.target.value.length == 0 && e.which == 8) {
                    $(this).prev().focus();
                }
            } else {
                e.preventDefault();
            }
        });

        $("input.card").on("keyup", function (e) {
            // \D 代表非數字字元，g 代表全域尋找
            //let str = e.target.value;
            //console.log(e.target.value);

            let str = (e.target.value).replace(/\D/g, "");
            $(this).val(str);
            if (str.length == 4) {
                $(this).next().focus();
            }

        });
    </script>

</body>

</html>


<!-- == -->
<!-- 
訂單編號
訂單明細

寄信 -->