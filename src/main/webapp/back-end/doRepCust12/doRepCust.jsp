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
<!--  Favicon title 小圖 -->
<link rel ="icon" href="<%=request.getContextPath() %>/static/img/core-img/favicon.ico">

<%@ include file="back-end-index.jsp"%>

<style>


table {
	background-color: #ced7e8 !important;
	margin-top: 5px;
	margin-bottom: 5px;
}



th, td {
	padding: 5px;
	text-align: center;
}
</style>


</head>
<body>

	<jsp:useBean id="repCustSvc" scope="page"
		class="com.taiwan.service.impl.RepCustServiceImpl" />
		
		
	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">所有廠商檢舉資料</h1>
				</div>
			</div>
		

			<table border="2px" class="table table-striped table-hover">
				<tr>
				<th>檢舉編號</th>
				<th>被檢舉會員編號</th>
				<th>檢舉原因</th>
				<th>檢舉日期</th>
				<th>檢舉狀態</th>
				<th>處理結果</th>
				<th>處理檢舉</th>
				</tr>

						<c:forEach var="repCustVO" items="${repCustSvc.searchAllRepCust()}" >
				<tr>
					<td>${repCustVO.repCustId}</td>
					<td>${repCustVO.custId}</td>				
					<td>${repCustVO.repCustReason}</td>
					<td>${repCustVO.repCustDate}</td>
					<td>${repCustVO.repCustStatus}</td>
					<td>${repCustVO.repCustResult}</td>
					<td>
					<c:if test="${repCustVO.repCustStatus == '未處理'}">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repCust12/DoRepCust" >
					<input type="hidden" name="repCustId" value="${repCustVO.repCustId}" >
					<input type="submit" value="處理檢舉">
					</FORM>
					</c:if>
					</td>
				</tr>
			</c:forEach>
				
			</table>
			
		</div>
	</div>	
		
		

</body>
</html>