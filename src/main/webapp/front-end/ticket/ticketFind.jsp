<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.beans.TicketVO"%>
<%@page import="java.util.List"%>
<%@page import="com.taiwan.service.impl.TicketServiceImpl"%>
<%@page import="com.taiwan.service.TicketService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<title>熱門活動頁面</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<!-- Style CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/ticket/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/front-main/bootstrap.min2.css">
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

<style type="text/css">
#select_div input[type="text"] {
	padding: 5px 15px;
	border: 2px black solid;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	width: 300px;
	margin-left: 10px;
	margin-top: 5px;
}

#select_div{
	font-size: 15px;
}
#select_div input[type="submit"]{
	width: 150px;
	height: 30px;
	margin-top: 10px;
	margin-left: 76%  ;
}
#select_div b{ 
	margin-left: 50px;
}
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>




</head>

<body>

	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>



	<div id="select_div" style="margin-top: 100px;">
		<FORM METHOD="post" name="form1" ACTION="ticket/SelectByThree">
			<b>所在地點:</b>	<input type="text" name="location" value="">
			<b>票券名稱:</b>	<input type="text" name="tktName" value="">  
			<input type="submit" value="送出"> 
		</FORM>

	</div>
	<div class="shop-2-area" style="margin-top: 30px;">
		<div class="container">
			<div class="row">
				<div class="category-products">
					<div class="shop-category-product">
						<div class="row">
							<div class="category-product">
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active fade in"
										id="gried_view">
										<c:forEach items="${requestScope.ticketVOs}" var="ticketVO">
											<div class="col-md-4 col-sm-6 col-xs-12">
												<div class="single-product">
													<div class="product-img">
														<a href="#"><img src="${ticketVO.tktImgVO.img}" alt=""
															style="width: 400px; height: 300px;" /></a>
													</div>
													<div class="product-content">
														<h5 class="product-name">
															<a href="#" title="Printed Chiffon Dress">${ticketVO.tktName}</a>
														</h5>
														<div class="price-box">
															<span class="price">票價: ${ticketVO.price}</span> <span
																class="price">
																<FORM METHOD="post" ACTION="ticket/selectId" style="margin-bottom: 0px;">
																	<input type="submit" value="詳情"> <input
																		type="hidden" name="tktId" value="${ticketVO.tktId}">
																	<input type="hidden" name="action"
																		value="getOne_For_Display">
																</FORM>
															</span>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>





	<!-- Footer Section Start -->



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
		
</body>
</html>