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
<link rel ="icon" href="<%=request.getContextPath() %>/static/img/core-img/favicon.ico">

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
	
	//假設廠商20011
	int cmpId = 20000;
	CompanyServiceImpl12 cmpSvc12 = new CompanyServiceImpl12();
	Company cmpVO = cmpSvc12.searchCmpByCmpId(cmpId);
	request.setAttribute("cmpVO", cmpVO);
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
						<h1 class="page-header">搜尋全部檢舉</h1>
					</div>
				</div>


<!-- 	<table border="2"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th>檢舉編號</th> -->
<!-- 				<th>被檢舉會員編號</th> -->
<!-- 				<th>檢舉原因</th> -->
<!-- 				<th>檢舉日期</th> -->
<!-- 				<th>檢舉狀態</th> -->
<!-- 				<th>處理結果</th> -->
<!-- 				<th>取消檢舉</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->

<%-- 			<c:forEach var="repCustVO" items="${repCustSvc.searchRepCustByCmpId(cmpVO.cmpId)}"> --%>
<!-- 				<tr> -->
<%-- 					<td>${repCustVO.repCustId}</td> --%>
<%-- 					<td>${repCustVO.custId}</td>				 --%>
<%-- 					<td>${repCustVO.repCustReason}</td> --%>
<%-- 					<td>${repCustVO.repCustDate}</td> --%>
<%-- 					<td>${repCustVO.repCustStatus}</td> --%>
<%-- 					<td>${repCustVO.repCustResult}</td> --%>
<!-- 					<td> -->
<%-- 					<c:if test="${repCustVO.repCustStatus == '未處理'}"> --%>
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repCust12/CancelRepCust" > --%>
<%-- 					<input type="hidden" name="repCustId" value="${repCustVO.repCustId}" > --%>
<!-- 					<input type="submit" value="取消檢舉"> -->
<!-- 					</FORM> -->
<%-- 					</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>


	<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">已有的檢舉如下:</div>


							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">

										<tr>
										<th>檢舉編號</th>
				<th>被檢舉會員編號</th>
				<th>檢舉原因</th>
				<th>檢舉日期</th>
				<th>檢舉狀態</th>
				<th>處理結果</th>
				<th>取消檢舉</th>

										</tr>
										<c:forEach var="repCustVO" items="${repCustSvc.searchRepCustByCmpId(cmpVO.cmpId)}">
				<tr>
					<td>${repCustVO.repCustId}</td>
					<td>${repCustVO.custId}</td>				
					<td>${repCustVO.repCustReason}</td>
					<td>${repCustVO.repCustDate}</td>
					<td>${repCustVO.repCustStatus}</td>
					<td>${repCustVO.repCustResult}</td>
					<td>
					<c:if test="${repCustVO.repCustStatus == '未處理'}">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repCust12/CancelRepCust" >
					<input type="hidden" name="repCustId" value="${repCustVO.repCustId}" >
					<input type="submit" value="取消檢舉">
					</FORM>
					</c:if>
					</td>
				</tr>
			</c:forEach>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
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