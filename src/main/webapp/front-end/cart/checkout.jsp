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
<style>
	label{
		font-size:14px;
	}
        .your-order {
            background: #EDE1E3 none repeat scroll 0 0;
            padding: 30px 40px ;
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
            font-size: 18px;
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

        .your-order-table table tr.order-total td span {
            color: #c06b81;
            font-size: 20px;
        }
</style>
<body>

	<%
	List<String> list = (List<String>) session.getAttribute("list");
	List<TicketVO> cartlist = (List<TicketVO>) session.getAttribute("tktlist");
	List<Integer> amountList = (List<Integer>) session.getAttribute("amountList");
	List<String> tktImgList = (List<String>) session.getAttribute("tktImgList");
	%>

	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>

	<!-- 頁面 -->
	<form action="#" class="form">
		<h3 class="text-center">結帳</h3>
		<!-- Progress bar -->
		<div class="progressbar">
			<div class="progress" id="progress"></div>
			<div class="progress-step progress-step-active" data-title="商品確認"></div>
			<div class="progress-step progress-step-active" data-title="填寫資料"></div>
			<div class="progress-step" data-title="訂單確認"></div>
		</div>
	</form>
	
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
					
				</div>
				<div class="post-a-comment-area mt-70">
					<form action="<%=request.getContextPath()%>/tktOrder/creator" method="post" novalidate>
						<div class="row">
							<div class="col-12 col-md-6">
								<div class="group">
									<input type="text" name="orderName" value="${name}" id="name"
										autocomplete=""> <span class="highlight"></span> <span
										class="bar"></span> <label>訂購人姓名</label><span style="color:red;">${errorMsgs.orderName}</span>
								</div>
							</div>
							<div class="col-12 col-md-6">
								<div class="group">
									<input type="tel" name="orderMobile" value="${tel}" id="tel">
									<span class="highlight"></span>
									<span class="bar"></span>
									<label>連絡電話</label>
									<span style="color:red;">${errorMsgs.orderMobile}</span>
								</div>
							</div>
							<div class="col-12">
								<div class="group">
									<input type="email" name="orderEmail" value="${email}" id="email">
									<span class="highlight"></span> 
									<span class="bar"></span>
									<label>Email</label>
									<span style="color:red;">${errorMsgs.orderEmail}</span>
								</div>
							</div>
							<div class="col-12">
								<div class="group"
									style="display: inline-flex; position: relative;">
									<input type="text" name="card" class="card" maxlength="4" style="width: 138px; text-align: center;"> － 
									<input type="text" name="card" class="card" maxlength="4" style="width: 138px; text-align: center;"> － 
									<input type="text" name="card" class="card" maxlength="4" style="width: 138px; text-align: center;"> － 
									<input type="text" name="card" class="card" maxlength="4" style="width: 138px; text-align: center;"> 
									<span class="highlight"></span>
									<span class="bar" style="height:35px;"></span>
									<label>信用卡卡號</label>
								</div>
								<span style="color:red;margin-top: -30px;margin-bottom: 25px;">${errorMsgs.card}</span>
							</div>
							<div class="col-12 col-md-6">
								<div class="group" style="border-bottom: groove;border-color: #f4f8fa4a;padding-bottom: 2px;">
							      <select id="month" style="margin-top: 10px;">
								          <option></option>
								          <option>01</option>
								          <option>02</option>
								          <option>03</option>
								          <option>04</option>
								          <option>05</option>
								          <option>06</option>
								          <option>07</option>
								          <option>08</option>
								          <option>09</option>
								          <option>10</option>
								          <option>11</option>
								          <option>12</option>
							      </select>
							      <select id="year">
								          <option></option>
								          <option>2022</option>
								          <option>2023</option>
								          <option>2024</option>
								          <option>2025</option>
								          <option>2026</option>
								          <option>2027</option>
								          <option>2028</option>
								          <option>2029</option>
								          <option>2030</option>
							      </select>
							      <input type="hidden" name="" class="expire"> 
								  <span class="highlight"></span>
							      <span class="bar"></span>
							      <label style="top: -15px;font-size: 12px;color: #30336b;">到期日</label>
								</div>
							    <span style="color:red;margin-top:-30px;">${errorMsgs.expire}</span>
							</div>
							<div class="col-12 col-md-6">
								<div class="group">
									<input type="text" name="cvv" placeholder="CVC" maxlength="3" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
									<span class="highlight"></span>
									<span class="bar"></span>
									<label>安全碼</label>
									<span style="color:red;">${errorMsgs.cvv}</span>
								</div>
							</div>
							<div class="col-12">
								<button type="submit" class="btn original-btn" id="sendOrder" style="color: white; line-height: inherit; margin-left:365px;background-color:black;"><b>送出訂單</b></button>
						        <input type="hidden" name="action" value="insert_order">
								<input type="hidden" name="orderName" value="${name}">
								<input type="hidden" name="orderMobile" value="${tel}">
								<input type="hidden" name="orderEmail" value="${email}">
								<input type="hidden" name="copId" value="${sessionScope.copId}">								
								<input type="hidden" name="discount" value="${sessionScope.discount}">								
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
						<td style="width:180px;">🧸<%=order.getTktName()%></td>
						<td><%=amountList.get(index) %>張</td>
						<td><%=order.getPrice()%>元</td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<hr>
			
		    <div class="your-order" style="width:300px;margin: 40px 80px;">
		        <div class="your-order-table table-responsive">
		            <table>
		                <tr>
		                    <th class="product-name">小計</th>
		                    <th class="product-total">$${total}</th>
		                </tr>
		                <tr class="cart-subtotal">
		                    <th>折扣金額</th>
		                    <td><span style="font-size:18px;">$${discount}</span></td>
		                </tr>
		
		                <tr class="order-total">
		                    <th>總金額</th>
		                    <td><strong><span class="amount">$${total-discount}</span></strong></td>
		                </tr>
		            </table>
		        </div>
		    </div>
			
<!-- 			<div class="cart-item-box" style="margin-left: 50px;"> -->
<!-- 				<table style="width: 200px;font-size:20px;"> -->
<!-- 					<tr> -->
<!-- 						<td style="text-align: start;">小計</td> -->
<!-- 						<td>$${total}</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td style="text-align: start;">折扣金額</td> -->
<!-- 						<td>-$${discount}</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td style="text-align: start;">總金額</td> -->
<!-- 						<td>$${total-discount}</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</div> -->
		</section>
	</div>


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
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>


	<script>
        $(function () {
            $("#sendOrder").click(function () {
//                 Swal.fire({
//                     position: 'center',
//                     icon: 'success',
//                     title: '訂單已成立'
//                 })
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
                let timerInterval;
                Swal.fire({
                  title: "資料驗證中 థ౪థ",
                  html: "請勿關閉視窗",
                  timer: 4000,
                  timerProgressBar: true,
                  didOpen: () => {
                    Swal.showLoading();
                    const b = Swal.getHtmlContainer().querySelector("b");
                    timerInterval = setInterval(() => {
                      b.textContent = Swal.getTimerLeft();
                    }, 100);
                  },
                  willClose: () => {
                    clearInterval(timerInterval);
                    Swal.fire({
                      position: "center",
                      icon: "success",
                      title: "訂單成立",
                      showConfirmButton: false,
                      timer: 2000
                    });
                  }
                }).then((result) => {
                  /* Read more about handling dismissals below */
                  if (result.dismiss === Swal.DismissReason.timer) {
                    console.log("I was closed by the timer");
                  }
                });

            });
        });


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
        
        $("#year").on("blur", function (e) {
	        let date = new Date();
		    let month = date.getMonth() + 1;
		    let year = date.getFullYear();
			
			if(parseInt($('#year').val())===year && parseInt($('#month').val())<=month){
				Swal.fire({
					icon: 'error',
					title: '🙅‍♀️ 請輸入正確月份'
				})
			}
        });
        
        
      //信用卡到期日
//         $(".expire").keypress(function(event){
//           if(event.charCode >= 48 && event.charCode <= 57){
//             if($(this).val().length === 1){
//                 $(this).val($(this).val() + event.key + " / ");
//             }else if($(this).val().length === 0){
//               if(event.key == 1 || event.key == 0){
//                 month = event.key;
//                 return event.charCode;
//               }else{
//                 $(this).val(0 + event.key + " / ");
//               }
//             }else if($(this).val().length > 2 && $(this).val().length < 9){
//               return event.charCode;
//             }
//           }
//           return false;
//       });
    </script>

</body>

</html>


<!-- == -->
<!-- 
訂單編號
訂單明細

寄信 -->