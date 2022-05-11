<%@page import="com.taiwan.beans.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台玩</title>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
	<form method="post" action="front-end/cust/CustomerInformation.jsp" style="margin-top:100px;">
		<input type="submit" value="會員基本資料"> 
	</form>

	<form method="post" action="cust/ShowFollow">	
		<input type="submit" value="關注廠商">
	</form>
	<form method="post" action="cust/ShowCustCoupon">
		<input type="submit" value="管理優惠劵">
	</form>
	<form method="post" action="cust/showRepCmp">
		<input type="submit" value="瀏覽檢舉">
	</form>
	<form method="post" action="cust/ShowEmail">
		<input type="submit" value="訊息管理">
	</form>
	<form method="post">
		<input type="submit" value="瀏覽訂單">
	</form>
	<%@ include file="/front-end/homepage/footer.jsp" %>
</body>
</html>