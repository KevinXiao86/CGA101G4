<%@page import="com.taiwan.beans.Theme"%>
<%@page import="com.taiwan.controller.theme.theme2Update"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.theme.ThemeService"%>
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
	
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
	
	<div class="checkout-area" style="margin-top: 100px">
        <div class="container">
            <div class="row">
                <!-- protfolio details start  -->
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="blog-wrapper">
                        <!-- single protfolio start-->
                        <div class="single-blog">
                            <div class="bolg-img nothver">
                                <div id="blog-innner-carousel">
                                    <div class="item-inner">
                                    <img style="width: 100%;" src="${theme.img}" alt="" /><a class="show-img venobox" href="${theme.img}"></a></div>
                                </div>
                            </div>
                            <div class="middel-box fix">
                                <ul class="blog-footer floatleft">
                                    <li><i class="fa fa-calendar"></i><a href="#"><fmt:formatDate value="${theme.createDate}"	pattern="yyyy-MM-dd HH:mm" /></a></li>
                                </ul>
                            </div>
                            <div class="bolg-content"><a href="#">${theme.title}</a>
                                <p>
                                	${theme.content}
                                </p>
                            </div>
                        </div><!-- single protfolio end-->
                    </div>
                </div><!-- protfolio details end  -->
            </div>
        </div>
    </div><!-- protfolio content end -->



	<!-- Footer Section Start -->
	<%-- <%@ include file= "/front-end/framework/footer.file" %> --%>


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