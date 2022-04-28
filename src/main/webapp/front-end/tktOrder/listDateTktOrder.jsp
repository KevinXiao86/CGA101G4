<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>訂單資料 - listDateTktOrder.jsp</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單資料</h3>
		 <h4><a href="front-end/tktOrder/tktOrderIndex.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 	以下共${tktOrder.orderListSize}幾筆訂單資料  bug~~~~~~~~~~ --%>

<table>
	<tr>
		<th>票券訂單編號</th>
		<th>會員編號</th>
		<th>原始金額</th>
		<th>訂購日期</th>
		<th>總金額</th>
		<th>會員優惠券編號</th>
	</tr>
	<c:forEach var="tktOrder" items="${orderList}">
		<tr>
			<td>${tktOrder.tktOrderId}</td>
			<td>${tktOrder.custId}</td>
			<td>${tktOrder.originalPrice}</td>
			<td><fmt:formatDate value="${tktOrder.orderdate}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td>${tktOrder.ttlPrice}</td>
			<td>${tktOrder.custCopId}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>