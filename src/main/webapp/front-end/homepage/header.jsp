<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.customer_bar{
	display:block;
    margin: 10px;
    border-bottom: 1px solid black;
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
								title="購物車" style="margin-right:63px;"> <i class="fa-solid fa-cart-shopping"
								aria-hidden="true"></i></a>
								 
						<c:if test="${customer==null}">
							<a href="<%=request.getContextPath()%>/front-end/custLogin/CustomerLogin.jsp" data-toggle="tooltip"
								data-placement="bottom" title="登入"> <i
								class="fa-regular fa-user" aria-hidden="true"></i></a>
						</c:if>
						
						<c:if test="${customer!=null}">
							<div title="會員功能" id="picture" href="#" data-toggle="tooltip" data-placement="bottom" style="position: fixed;right: 10px;top: 10px;z-index: 9;height:50px;text-decoration: none;overflow:hidden;text-align:left;">
	            				<img id="cow" src="<%=request.getContextPath()%>/${customer.img}" style="width:45px;height:45px;border-radius: 100%;padding:0;overflow:hidden; margin-left:20px;margin-right:90px;">
	            				</img>
					            <div id="infoLogoutBar"style="box-shadow: 2px 2px 4px black;background-color: #ccc;margin:10px;padding:5px;text-align:left;">
					                <span id="customer_data" class="customer_bar" style="display:block;" >
					                    <a href="<%=request.getContextPath()%>/cust/CustomerInformation" style="padding:0px;">會員功能</a>
					                </span>
					                <span class="customer_bar">
					                   <a href="<%=request.getContextPath()%>/cust/Logout" style="padding:0px;">登出</a>
					                </span>
					            </div>
	        				</div>
	        				<script>
	        				let picture=document.querySelector('#picture');
	        				picture.onclick=function(){
	        					    if(picture.style.height==='50px'){
	        							console.log('1');  
	        						
	        							$(picture).animate({height:150},500);
	        							picture.removeAttribute('data-original-title');
	        							$(picture).tooltip({ 'placement': 'bottom' ,title : '按圖片隱藏此列表'});
	        						}else{	        						
		        						console.log('2');
		        						$(picture).tooltip('disable');	        						
		        						$(picture).animate({height:50},500);	        						
	        						}
	        				}
	        				</script>
						</c:if>
							
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