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
<link href="<%=request.getContextPath()%>/static/css/front-main/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/static/css/front-main/bootstrap.min2.css">
<script src="https://kit.fontawesome.com/c95ced1229.js" crossorigin="anonymous"></script>

<style>
* {
  margin: 0;
  padding: 0;
}
body {
	font-family: "helveticaneuemedium";
	background-color: #ffffff;
}

#preloader {
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-ms-grid-row-align: center;
	align-items: center;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	justify-content: center;
	background-color: #0d0d0d;
	position: fixed;
	width: 100%;
	height: 100%;
	z-index: 1000000;
}

#original-load {
	width: 70px;
	height: 70px;
	border-radius: 50%;
	border: 3px;
	border-style: solid;
	border-color: transparent;
	border-top-color: #f1f1f1;
	-webkit-animation: original 2400ms linear infinite;
	animation: original 2400ms linear infinite;
	z-index: 999;
}

#original-load:before {
	content: "";
	position: absolute;
	top: 7.5px;
	left: 7.5px;
	right: 7.5px;
	bottom: 7.5px;
	border-radius: 50%;
	border: 3px;
	border-style: solid;
	border-color: transparent;
	border-top-color: #f1f1f1;
	-webkit-animation: original 2700ms linear infinite;
	animation: original 2700ms linear infinite;
}

#original-load:after {
	content: "";
	position: absolute;
	top: 20px;
	left: 20px;
	right: 20px;
	bottom: 20px;
	border-radius: 50%;
	border: 3px;
	border-style: solid;
	border-color: transparent;
	border-top-color: #f1f1f1;
	-webkit-animation: original 1800ms linear infinite;
	animation: original 1800ms linear infinite;
}

@
-webkit-keyframes original { 0% {
	-webkit-transform: rotate(0deg);
	transform: rotate(0deg);
}

100
%
{
-webkit-transform
:
rotate(
360deg
);
transform
:
rotate(
360deg
);
}
}
@
keyframes original { 0% {
	-webkit-transform: rotate(0deg);
	transform: rotate(0deg);
}

100
%
{
-webkit-transform
:
rotate(
360deg
);
transform
:
rotate(
360deg
);
}
}
.header-area {
	position: relative;
	width: 100%;
	z-index: 100;
	-webkit-transition-duration: 500ms;
	transition-duration: 500ms;
}

.header-area .top-header {
	height: 65px;
}

@media only screen and (max-width: 767px) {
	.header-area .top-header {
		height: 90px;
	}
}

@media only screen and (min-width: 576px) and (max-width: 767px) {
	.header-area .top-header {
		height: 70px;
	}
}

.header-area .top-header .top-social-area {
	text-align: right;
}

@media only screen and (max-width: 767px) {
	.header-area .top-header .top-social-area {
		text-align: left;
	}
}

.header-area .top-header .top-social-area a {
	display: inline-block;
	padding: 5px 15px;
	color: #000000;
	font-size: 13px;
}

.header-area .top-header .top-social-area ul {
	display: inline-block;
}

.header-area .top-header .top-social-area li {
	display: inline-block;
}

.header-area .top-header .top-social-area a:hover, .header-area .top-header .top-social-area a:focus
	{
	color: #444788;
}

@media only screen and (min-width: 768px) and (max-width: 991px) {
	.header-area .top-header .top-social-area a {
		padding: 5px 7px;
	}
}

@media only screen and (max-width: 767px) {
	.header-area .top-header .top-social-area a {
		padding: 5px 7px;
	}
}

@media only screen and (min-width: 576px) and (max-width: 767px) {
	.header-area .top-header .top-social-area a {
		padding: 5px 4px;
	}
}

.header-area .logo-area {
	height: 170px;
	border-top: 1px solid #e1e1e1;
	border-bottom: 1px solid #e1e1e1;
}

@media only screen and (max-width: 767px) {
	.header-area .logo-area {
		height: 100px;
	}
}

#headerFixed {
	background-color: #ffffff;
	margin-top: 0;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
	position: fixed;
	width: 100%;
	height: 70px;
	top: 0;
	left: 0;
	right: 0;
	z-index: 900;
}
ul,
ol {
  margin: 0;
}

ul li,
ol li {
  list-style: none;
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
				<div class="row h-100 align-items-center">
					<a href="<%=request.getContextPath()%>/front-end/homepage/index.jsp">
						<img src="<%=request.getContextPath()%>/static/img/ticket-img/logo.jpg"
							alt="" id="bear" style="height: 65px;">
					</a>

					<!-- Top Social Area -->

					<div class="col-sm-2" style="position: absolute; right: 90px">
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

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script	src="<%=request.getContextPath()%>/static/js/front-main/jquery/jquery-2.2.4.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>


</body>

</html>