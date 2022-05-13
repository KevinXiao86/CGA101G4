<%@page import="com.taiwan.beans.TktOrder"%>
<%@page import="com.taiwan.service.TktOrderService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
List<TktOrder> list = (List<TktOrder>) request.getAttribute("list");
pageContext.setAttribute("list", list);
%>


<html>
<head>
<%-- �R�A�]�t base����,css�˦�,jQuery��� --%>
<%@ include file="/common/head.jsp"%>

<title>�x��</title>

<!-- Favicon title���p�� -->
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<style>
	table{
		text-align:center;
	}
</style>

</head>
<body>

	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
	<jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>
	<main id="main" class="main" style="padding-left: 70px; height: 1200px; padding-top: 40px;">

		<div class="pagetitle">
			<h1>����q��</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">����</li>
					<li class="breadcrumb-item">�s���q��</li>
					<li class="breadcrumb-item active">����q��</li>
				</ol>
			</nav>
		</div>

		<FORM METHOD="post" ACTION="tktOrder/getDate" >
	        <b>�d�߭q��:</b><font color=red>${errorMsgs.date}</font><br>
	        <label for="from">From:  </label>
			<input type="date" id="startdate" name="startdate" value="${param.startdate}" required style="width:110px;margin: 10px;">
			<label for="to" style="font-size:16px;">to: </label>
			<input type="date" id="enddate" name="enddate" value="${param.enddate}" required style="width:110px;margin: 10px;">
	        <input type="hidden" name="action" value="get_date">
	        <input type="submit" value="�e�X" style="width:60px;background-color: lightcoral;color: white;margin-left: 25px;">
	    </FORM>
	
		<div class="w-80 p-3 "
			style="background-color: #ced7e8; margin: 50px auto 30px;">

			<table border="2" class="table table-hover">
				<thead>
					<tr>
						<th>�q��s��</th>
						<th>�|���m�W</th>
						<th>��l���B</th>
						<th>�q�ʤ��</th>
						<th>�`���B</th>
						<th>�u�f��</th>
						<th>�Ա�</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tktOrder" items="${list}">
						<tr>
							<td>${tktOrder.tktOrderId}</td>
							<td>${customerVO.name}</td>
							<td>$${tktOrder.originalPrice}</td>
							<td><fmt:formatDate value="${tktOrder.orderdate}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td>$${tktOrder.ttlPrice}</td>
							<td>
								<c:choose>
									<c:when test = "${tktOrder.custCopId==0}">���ϥ�</c:when>
									<c:otherwise>${tktOrder.custCopId}</c:otherwise>
								</c:choose>
							</td>
							<td>
								<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/tktItem/selectById"
									style="margin-bottom: 0px;">
									<input type="submit" value="�d�ߩ���">
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

	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>


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