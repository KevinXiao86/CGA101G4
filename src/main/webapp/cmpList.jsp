<%@page import="com.taiwan.service.company.CompanyService"%>
<%@page import="com.taiwan.beans.Company"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%
String location = request.getParameter("location");
request.setAttribute("location", location);

if (location == null) {
	CompanyService companyService = ControllerUtil.getBean(CompanyService.class);
	List<Company> list = companyService.getAllCompanies();
	pageContext.setAttribute("list", list);
}else{
	String ckin = request.getParameter("ckin");
	request.setAttribute("ckin", ckin);
	String ckout = request.getParameter("ckout");
	request.setAttribute("ckout", ckout);
	
	CompanyService companyService = ControllerUtil.getBean(CompanyService.class);
	List<Company> list = companyService.getAllCompaniesByLocation(location);
	pageContext.setAttribute("list", list);
}
%>
<html>
<head>
<meta charset="UTF-8">
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<title>台玩</title>
<!-- Favicon title的小圖 -->
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

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

<link rel="stylesheet" type="text/css"
	href="front-end/reservation/datetimepicker/jquery.datetimepicker.css" />
<script src="front-end/reservation/datetimepicker/jquery.js"></script>
<script
	src="front-end/reservation/datetimepicker/jquery.datetimepicker.full.js"></script>

<style type="text/css">
.select_div input[type="text"] {
	padding: 5px 15px;
	border: 2px black solid;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	width: 300px;
	margin-left: 10px;
	margin-top: 5px;
}

.select_div {
	font-size: 15px;
}

.select_div input[type="submit"] {
	width: 150px;
	height: 30px;
	margin-top: 10px;
	margin-left: 76%;
}

.select_div b {
	margin-left: 50px;
}

.pageA {
	margin-left: 80%;
}
</style>
</head>
<body>
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>



	<div class="select_div" style="margin-top: 100px;">
		<FORM METHOD="post" name="form1" ACTION="cmpList.jsp">
			<b>所在地點:</b> <input name="location" id="location" type="text" style="width:90px;" value="${requestScope.location}">  
<!-- 			<b>開始日期:</b> <input name="start_date" id="start_date" type="text" style="width:90px;">  -->
			<b>開始日期:</b> <input type="date" name="ckin" style="width:90px;" value="${requestScope.ckin}">
<!-- 			<b>結束日期:</b> <input name="end_date" id="end_date" type="text" style="width:90px;">  -->
			<b>結束日期:</b> <input type="date" name="ckout" style="width:90px;" value="${requestScope.ckout}">
			<input type="submit" value="送出">
		</FORM>

		<%-- 	<%@ include file="page1.file" %>  --%>
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

										<c:forEach items="${list}" var="Company">
											<div class="col-md-4 col-sm-6 col-xs-12">
												<div class="single-product">

													<div class="product-img">


														<c:forEach items="${Company.roomtypes}" end="0"
															var="Roomtype">
															<c:forEach items="${Roomtype.roomImgs}" end="0"
																var="RoomImg">
																<%-- 																<img height="128px" width="128px" src="${RoomImg.roomImg}"> --%>
																<a href="#"><img src="${RoomImg.roomImg}" alt=""
																	style="width: 400px; height: 300px;" /></a>
															</c:forEach>
														</c:forEach>

													</div>

													<div class="product-content">
														<h5 class="product-name">
															<a href="#" title="Printed Chiffon Dress">${Company.cmpName}</a>
														</h5>

														<div class="price-box">

															<c:forEach items="${Company.roomtypes}" end="0"
																var="Roomtype">
																<span class="price" style="color: red;">價格:
																	${Roomtype.roomtypePrice} 起</span>
															</c:forEach>


															<span class="price">


																<FORM METHOD="post" ACTION="roomOrder12/ROSelectCmp" style="margin-bottom: 0px;">
																	<input type="hidden" name="cmpId" value="${Company.cmpId}">
																	<input type="hidden" name="ckin" value="${requestScope.ckin}">
																	<input type="hidden" name="ckout" value="${requestScope.ckout}">
																	<input type="submit" value="詳情"> 
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
	<%-- <%@ include file="page2.file"%> --%>
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