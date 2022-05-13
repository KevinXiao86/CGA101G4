<%@page import="com.taiwan.beans.CouponVO"%>
<%@page import="java.util.List"%>
<%@page import="com.taiwan.beans.TktItem"%>
<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="com.taiwan.beans.TktOrder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<%
	List<TktItem> tktItemList = (List<TktItem>)request.getAttribute("tktItemList");
	List<TktOrder> tktOrderList = (List<TktOrder>)request.getAttribute("tktOrderList");
	List<CustomerVO> customerList = (List<CustomerVO>)request.getAttribute("customerList");
%>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<title>台玩</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<!-- Style CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/front-main/classy-nav.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/post.css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+3:200,300,regular,500,600,700,800,900,200italic,300italic,italic,500italic,600italic,700italic,800italic,900italic"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/c95ced1229.js"
	crossorigin="anonymous"></script>

</head>

<body>
	
	<!-- Header -->
<%-- 	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include> --%>
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
					<a href="<%=request.getContextPath() %>/front-end/homepage/index.jsp"><img src="<%=request.getContextPath()%>/static/img/ticket-img/logo.jpg"
						id="bear" style="height: 65px;"></a>

					<!-- Top Social Area -->
					<!-- Nav Start -->
					<div class="classynav" id="clas">
						<ul>
							<li><a href="<%=request.getContextPath() %>/cmpList.jsp">住宿</a></li>
							<li><a href="<%=request.getContextPath() %>/front-end/ticket/ticketList.jsp">票券</a></li>
						</ul>
					</div>
					<div class="col-sm-2" style="position:absolute;right:73px" >
						<div class="top-social-area">
							<a href="<%=request.getContextPath()%>/front-end/cart/cartList.jsp" data-toggle="tooltip" data-placement="bottom"
								title="購物車"> <i class="fa-solid fa-cart-shopping"
								aria-hidden="true"></i></a> 
							<a href="#" data-toggle="tooltip" data-placement="bottom" title="登入"> <i
								class="fa-regular fa-user" aria-hidden="true"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- ##### Single Blog Area Start ##### -->
	<div class="single-blog-wrapper section-padding-0-100">

		<!-- Single Blog Area  -->
		<div class="single-blog-area blog-style-2 mb-50">
			<div class="single-blog-thumbnail" style="margin-top: 70px;">
			<c:forEach var="tktImgVO" items="${imgList}" begin="0" end="0">
				<img src="<%=request.getContextPath()%>/${tktImgVO.img}" style="height:535.6px">
			</c:forEach>
			</div>
			<div id="productNum">商品編號 #${tktVO.tktId}</div>
		</div>


		<div class="container">
			<div class="row">
				<!-- ##### Post Content Area ##### -->
				<div class="col-12 col-lg-9">
					<!-- Single Blog Area  -->
					<div class="single-blog-area blog-style-2 mb-50">
						<!-- Blog Content -->
						<div class="single-blog-content">
							<div class="line"></div>
							<div class="post-tag">Lifestyle</div>
							<h4>
								<div class="post-headline mb-0">${tktVO.tktName}</div>
<!--                                 <i class="fa-regular fa-heart" aria-hidden="true" style="--fa-inverse: #1da1f2;" -->
<!--                                     id="love"></i> -->
							</h4>
							<div class="post-meta mb-50">
								<p>
									<a href="#"><i class="fa-solid fa-location-dot"
										aria-hidden="true" style="color: red;"></i> ${tktVO.location}</a>
								</p>
								<p>${tktVO.ttlScore}<i class="fa-solid fa-star"
										style="color: orange;"></i>評價
								</p>
								<p>${tktVO.ttlPeople}comments</p>
							</div>
							<p>
							<hr>
							<i class="fa-solid fa-mobile-screen-button" aria-hidden="true"></i>
							手機出示QR Code
							<div style="display: inline-block; width: 40px;"></div>
							<i class="fa-solid fa-ban" aria-hidden="true"></i> 不可取消
							</p>
							<p>${tktVO.instruction}</p>
						</div>
						<div id="comment"></div>
					</div>


					<!-- Comment Area Start -->
					<hr>
					<div class="comment_area clearfix mt-70">
						<h4 class="title">
							<i class="fa-solid fa-earth-asia"
								style="color: rgb(107, 150, 207);"></i> 旅客評價
						</h4>

						<ol>
						<%
							for(int index = 0; index < tktItemList.size(); index++){
								TktItem tktItem = tktItemList.get(index);
								TktOrder tktOrder = tktOrderList.get(index);
								CustomerVO customerVO = customerList.get(index);
								
						%>
							<c:if test="<%= tktItem.getContent() != null%>">
								<li class="single_comment_area">
									<div class="comment-content d-flex">
										<div class="comment-author">
											<img src="<%= customerVO.getImg()%>" alt="author">
										</div>
										<div class="comment-meta">
											<a class="post-date"><fmt:formatDate value="<%= tktOrder.getOrderdate()%>" pattern="yyyy-MM-dd HH:mm"/></a>
											<p>
												<a class="post-author"><%= customerVO.getName()%></a>
											</p>
											<p><%= tktItem.getContent()%></p>
										</div>
									</div>
								</li>
								</c:if>
						<%
							}
						%>
<%-- 							</c:forEach> --%>
						</ol>
							<div class="load-more-btn mt-100 wow fadeInUp">
								<a href="#" class="btn original-btn" style="margin-top:-80px;">Read More</a>
							</div>
						<div id="canxpolicy"></div>
					</div>

					<!-- About product -->
					<!-- 特別注意是用comment的class -->
					<div class="comment_area clearfix mt-70" >
						<div class="author-info">
							<!-- <div class="line"></div> -->
							<h4 class="title">
								<i class="fa-solid fa-earth-asia"
									style="color: rgb(107, 150, 207);"></i> 商品說明
							</h4>
							<div>
								<p>${tktVO.canxpolicy}</p>
							</div>
							<div>
								<c:forEach var="tktImgVO" items="${imgList}" begin="1">
									<img src="${tktImgVO.img}" style="margin-bottom: 20px" />
								</c:forEach>
							</div>
						</div>
						<div id="notice"></div>
					</div>

					<div class="comment_area clearfix mt-70"  >
						<div class="author-info">
							<h4 class="title">
								<i class="fa-solid fa-earth-asia"
									style="color: rgb(107, 150, 207);"></i> 購買須知
							</h4>
							<div>
								<p>${tktVO.notice}</p>
							</div>
						</div>
						<div id="howuse"></div>
					</div>

					<div class="comment_area clearfix mt-70" >
						<div class="author-info">
							<h4 class="title">
								<i class="fa-solid fa-earth-asia"
									style="color: rgb(107, 150, 207);"></i> 如何使用
							</h4>
							<div>
								<p>${tktVO.howuse}</p>
							</div>
						</div>
						<div id="loc"></div>
					</div>


					<div class="comment_area clearfix mt-70" >
						<div class="author-info" >
							<h4 class="title">
								<i class="fa-solid fa-earth-asia"
									style="color: rgb(107, 150, 207);"></i> 體驗地點
							</h4>
							<div>
								<iframe
									src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d14705.151757291362!2d121.1081636!3d22.8658194!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x95ca249c81a54f18!2z5Yid6bm_54mn5aC0!5e0!3m2!1szh-TW!2stw!4v1647886745869!5m2!1szh-TW!2stw"
									width="600" height="450" style="border: 0;" allowfullscreen=""
									loading="lazy"></iframe>
							</div>
						</div>
					</div>

					<!-- 留言區 -->
<!-- 					<div class="post-a-comment-area mt-70"> -->
<!-- 						<h5>Leave a reply</h5> -->
<!-- 						Reply Form -->
<!-- 						<form action="#" method="post"> -->
<!-- 							<div class="row"> -->
<!-- 								<div class="col-12 col-md-6"> -->
<!-- 									<div class="group"> -->
<!-- 										<input type="text" name="name" id="name" required> <span -->
<!-- 											class="highlight"></span> <span class="bar"></span> <label>Name</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-12 col-md-6"> -->
<!-- 									<div class="group"> -->
<!-- 										<input type="email" name="email" id="email" required> -->
<!-- 										<span class="highlight"></span> <span class="bar"></span> <label>Email</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-12"> -->
<!-- 									<div class="group"> -->
<!-- 										<input type="text" name="subject" id="subject" required> -->
<!-- 										<span class="highlight"></span> <span class="bar"></span> <label>Subject</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-12"> -->
<!-- 									<div class="group"> -->
<!-- 										<textarea name="message" id="message" required></textarea> -->
<!-- 										<span class="highlight"></span> <span class="bar"></span> <label>Comment</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-12"> -->
<!-- 									<button type="submit" class="btn original-btn">Reply</button> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</form> -->
<!-- 					</div> -->
				</div>

				<!-- Widget Area -->
				<div class="sidebar-widget-area" style="width: 260px;">
					<div class="product-summary">
						<h4 class="productName">TWD ${tktVO.price}</h4>
						<span class="stars"></span>
						<p>luctus quis et est. Integer pretium purus</p>
					</div>
					<form name="shoppingForm" action="cart/do" method="post">
						<div style="text-align: center;">
							<input id="min" name="" type="button" value="-" style="width:25px;margin-left: -58px;background-color: #ced7e8;"/>
							<input id="quantity" name="" type="text" value="1" style="width:25px;margin: -5px;text-align: center;"/>
							<input id="add" name="" type="button" value="+"  style="width:25px;background-color: #ced7e8;"/>
							<button class="btn btn-small addToCart" data-product-id="1"
								id="choice">
								<i class="fas fa-cart-plus"></i> 加入購物車
							</button>
						</div>
						
						<input type="hidden" name="amount" id="amount" size="3" value="1"> 
						<input type="hidden" name="tktName" value="${tktVO.tktName}"> 
						<input type="hidden" name="tktId" value="${tktVO.tktId}"> 
						<input type="hidden" name="price" value="${tktVO.price}"> 
						<input type="hidden" name="action" value="add">
					</form>
					<div class="sideList" style="position:sticky; top:100px; line-height: 2;">
						<ul style="margin: 0 80px;"> 
							<li><div style="margin-top:50px;"></div></li>
							<li><a href="<%=request.getContextPath() %>/ticket/selectId#comment" style="font-size:20px;">旅客評價</a></li>
							<li><a href="<%=request.getContextPath() %>/ticket/selectId#canxpolicy" style="font-size:20px;">商品說明</a></li>
							<li><a href="<%=request.getContextPath() %>/ticket/selectId#notice" style="font-size:20px;">購買須知</a></li>
							<li><a href="<%=request.getContextPath() %>/ticket/selectId#howuse" style="font-size:20px;">如何使用</a></li>
							<li><a href="<%=request.getContextPath() %>/ticket/selectId#loc" style="font-size:20px;">體驗地點</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Single Blog Area End ##### -->

	<!-- ##### Instagram Feed Area Start ##### -->
	<div class="instagram-feed-area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="insta-title">
						<h5>Follow us @ Instagram</h5>
					</div>
				</div>
			</div>
		</div>
		<!-- Instagram Slides -->
		<div class="instagram-slides owl-carousel">
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/1.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/2.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/3.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/4.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/5.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/6.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img
					src="<%=request.getContextPath()%>/static/img/instagram-img/7.png"
					alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="#"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Instagram Feed Area End ##### -->

	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>
	

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
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
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

	<script>
// 	$(function(){
// 		let t = $("#quantity");
// 		$("#add").click(function(){
// 			t.val(parseInt(t.val())+1);
// 			$("#min").removeAttr("disabled");                 //當按加1時，解除$("#min")不可讀狀態
// 			setTotal();
// 		})
// 		$("#min").click(function(){
// 	               if (parseInt(t.val())>1) {                     //判斷數量值大於1時才可以減少
// 	                t.val(parseInt(t.val())-1)
// 	                }else{
// 	                $("#min").attr("disabled","disabled")        //當$("#min")為1時，$("#min")不可讀狀態
// 	               }
// 		})
// 	})


		//load more
	    $(function(){
	        $("div").slice(0, 10).show(); // select the first ten
	        $("#load").click(function(e){ // click event for load more
	            e.preventDefault();
	            $("div:hidden").slice(0, 10).show(); // select next 10 hidden divs and show them
	            if($("div:hidden").length == 0){ // check if any hidden divs still exist
	                alert("No more divs"); // alert if there are none left
	            }
	        });
	    });
	
	
	  	//增減購物車
	    $("#add").click(function () {
            //得到當前兄弟文字框的值
            let n = $(this).siblings("#quantity").val();
            n++;
            $(this).siblings("#quantity").val(n);
            
        })
        $("#min").click(function(){
            //得到當前兄弟文字框的值
            let n=$(this).siblings("#quantity").val();
            //當文字框的值減到1時就不再執行n--及後面的程式碼
            if(n==1){
                return false;
            }
            n--;
            $(this).siblings("#quantity").val(n);
        })
        
       	$('#quantity').change( function() {
       		$('#amount').attr("value",$('#quantity').val());
		});        
	 
	</script>	
	
</body>

</html>