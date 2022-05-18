<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>訂單資料 - listDateTktOrder.jsp</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<style>
table {
	text-align: center;
}
</style>


</head>
<body>

	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
	<jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>
	<main id="main" class="main"
		style="padding-left: 70px; height: 1200px; padding-top: 40px;">

		<div class="pagetitle">
			<h1>票券訂單</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">首頁</li>
					<li class="breadcrumb-item">瀏覽訂單</li>
					<li class="breadcrumb-item active">票券訂單</li>
				</ol>
			</nav>
		</div>

		<FORM METHOD="post" ACTION="tktOrder/getDate" >
	        <b>查詢訂單:</b><font color=red>${errorMsgs.date}</font><br>
	        <label for="from">From:  </label>
			<input type="date" id="startdate" name="startdate" value="${param.startdate}" required style="width:110px;margin: 10px;">
			<label for="to" style="font-size:16px;">to: </label>
			<input type="date" id="enddate" name="enddate" value="${param.enddate}" required style="width:110px;margin: 10px;">
	        <input type="hidden" name="action" value="get_date">
	        <input type="submit" value="送出" style="width:60px;background-color: lightcoral;color: white;margin-left: 25px;">
	    </FORM>

		<div class="w-80 p-3 "
			style="background-color: #ced7e8; margin: 50px auto 30px;">

			<table border="2" class="table table-hover">
				<thead>
					<tr>
						<th>訂單編號</th>
						<th>會員姓名</th>
						<th>原始金額</th>
						<th>訂購日期</th>
						<th>總金額</th>
						<th>優惠券</th>
						<th>詳情</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tktOrder" items="${orderList}">
						<tr>
							<td>${tktOrder.tktOrderId}</td>
							<td>${customerVO.name}</td>
							<td>$${tktOrder.originalPrice}</td>
							<td><fmt:formatDate value="${tktOrder.orderdate}"
									pattern="yyyy-MM-dd HH:mm" /></td>
							<td>$${tktOrder.ttlPrice}</td>
							<td>
								<c:choose>
									<c:when test = "${tktOrder.custCopId==0}">未使用</c:when>
									<c:otherwise>${tktOrder.custCopId}</c:otherwise>
								</c:choose>
							</td>
							<td>
								<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/tktItem/selectById"
									style="margin-bottom: 0px;">
									<input type="submit" value="查詢明細">
									<input type="hidden" name="tktOrderId" value="${tktOrder.tktOrderId}"> 
									<input type="hidden" name="action" value="get_orderItem">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
	
	<script>
		$(function(){
	        let date = new Date();
	        let day = date.getDate();
	        let month = date.getMonth() + 1;
	        let month2 = date.getMonth() + 2;
	        let year = date.getFullYear();
	        if (month < 10)
	            month = "0" + month;
	        if (month2 < 10)
	            month2 = "0" + month2;
	        if (day < 10)
	            day = "0" + day;
	        let today = year + "-" + month + "-" + day; 
	        let lastday = year + "-" + month2 + "-" + day;  

        	$('#startdate').attr('value', today);
            $('#startdate').attr('max', today);
            $('#startdate').blur(e => {
                $('#enddate').attr({
                    'min': $('#startdate').val(),
                    'max': today
                })
            })	
        });
    </script>
	
</body>
</html>