<%@page import="com.taiwan.beans.Company"%>
<%@page import="com.taiwan.service.impl.CompanyServiceImpl12"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台玩</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<!--  Favicon title 小圖 -->
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<!-- Bootstrap Core CSS -->
<link href="front-end/company/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="front-end/company/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="front-end/company/css/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="front-end/company/css/dataTables/dataTables.responsive.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="front-end/company/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="front-end/company/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style type="text/css">
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 900px;
}

span {
	font-size: 18px;
}

#back_index {
	position: fixed;
	right: 10%;
	bottom: 10%;
}

#back_index a {
	font-size: 20px;
	color: blue;
}

#back_index a:hover {
	color: red;
	text-decoration: none;
}

#pre {
	margin-left: 50%;
}
</style>
</head>
<body>
	<%
	//156行已改抓session
	//假設廠商20011
	// 	int cmpId = 20000;
	// 	CompanyServiceImpl12 cmpSvc12 = new CompanyServiceImpl12();
	// 	Company cmpVO = cmpSvc12.searchCmpByCmpId(cmpId);
	// 	request.setAttribute("cmpVO", cmpVO);
	%>
	<jsp:useBean id="repCustSvc" scope="page"
		class="com.taiwan.service.impl.RepCustServiceImpl" />

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">台玩 - 旅遊平台</a>
			</div>




			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a
							href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&">
								<i class="fa fa-dashboard fa-fw"></i> 廠商首頁
						</a></li>


						<li><a
							href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 房型管理
						</a></li>

						<li><a
							href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 廠商資料
						</a></li>

						<li><a
							href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i> 訂單管理
						</a></li>

						<li><a
							href="<%=request.getContextPath()%>/front-end/repCust12/seeRepCust.jsp">
								<i class="fa fa-dashboard fa-fw"></i> 檢舉管理
						</a></li>

						<li><a href="company/logout"> <i
								class="fa fa-dashboard fa-fw"></i> 登出
						</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">新增檢舉</h1>
					</div>
				</div>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/addRepCmp/addRepCust"
					name="form1">
					<table>
						<tr>
							<td>被檢舉會員編號</td>

							<td><input type="text" name="custId"
								value="${customer.custId}"></td>
							<td>${errorMsgs.custId}</td>
						</tr>
						<tr>
							<td>檢舉原因</td>
							<td><textarea class="form-control"
									aria-label="With textarea" name="reason" maxlength="300"></textarea></td>
							<td>${errorMsgs.reason}</td>
						</tr>
						<tr>
							<td><input type="hidden" name="cmpId"
								value="${loginCompany.cmpId}"></td>
							<td><input type="submit" value="檢舉會員">${errorMsgs.repCmp}</td>
						</tr>
					</table>


				</FORM>
			</div>
		</div>
	</div>


	<!-- jQuery -->
	<script src="front-end/company/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="front-end/company/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="front-end/company/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="front-end/company/js/dataTables/jquery.dataTables.min.js"></script>
	<script
		src="front-end/company/js/dataTables/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="front-end/company/js/startmin.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->





</body>
</html>