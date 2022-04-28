<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂單詳細資料 - listOneTktItem.jsp</title>
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

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>訂單資料</h3> -->
<!-- 		 <h4><a href="front-end/tktItem/tktOrderIndex.jsp">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<table>
	<tr>
		<th>票券訂單編號</th>
		<th>票券編號</th>
		<th>購買數量</th>
		<th>評價分數</th>
		<th>評價內文</th>
	</tr>
	<c:forEach var="tktItem" items="${itemList}" >
		<tr>
			<td>${tktItem.tktOrderId}</td>
			<td>${tktItem.tktId}</td>
			<td>${tktItem.amount}</td>
			<td>${tktItem.score}</td>
			<td>${tktItem.content}</td>
	<%-- 		<td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
		</tr>
	</c:forEach>
</table>

</body>
</html>