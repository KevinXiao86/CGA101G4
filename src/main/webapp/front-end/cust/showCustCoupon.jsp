<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="/common/head.jsp"%>


<!-- Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/classy-nav-first.css">

<!-- 搜尋bar -->
<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Cardo:700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css"
	integrity="sha512-UTNP5BXLIptsaj5WdKFrkFov94lDx+eBvbKyoe1YAfjeRPC+gT5kyZ10kOHCfNZqEui1sxmqvodNUx3KbuYI/A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
	integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/front-end/homepage/header.jsp" %>
	<%@ include file="/front-end/cust/side-bar.jsp" %>
	<main id="main" class="main">

    <div class="pagetitle">
      <h1>管理優惠券</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item">首頁</li>
          <li class="breadcrumb-item">會員功能</li>
          <li class="breadcrumb-item active">管理優惠券</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
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
    <section class="section">
      <div class="row">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">優惠券列表</h5>

              <!-- Table with stripped rows -->
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">優惠券名稱</th>
                    <th scope="col">領取日期</th>
                    <th scope="col">使用日期</th>
                    <th scope="col">搭配使用</th>
                    <th scope="col">優惠券金額</th>
                    <th scope="col">狀態</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="custCoupon" items="${custCouponList}">
                  <tr>
                    <th scope="row">${custCoupon.custCopId}</th>
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
                <!-- End Table with stripped rows -->

              </div>
            </div>

        </div>
      </section>

    </main><!-- End #main -->
	  <%@ include file="/front-end/homepage/footer.jsp" %>
	<!--   <div style="display: inline-block; vertical-align: middle">
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
					<td>優惠券名稱</td>
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
	</div> -->
</body>
</html>