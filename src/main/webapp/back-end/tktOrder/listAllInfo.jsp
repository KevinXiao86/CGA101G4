<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂單詳細資料 - listOneTktItem.jsp</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 1000px;
}

table {
	width: 1100px;
	background-color: rgb(221, 221, 241) !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 3px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>
</head>
<body bgcolor='white'>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">票券訂單明細</h1>
				</div>
			</div>

			<table>
				<tr>
					<th>票券訂單編號</th>
					<th>票券名稱</th>
					<th>購買數量</th>
					<th>評價分數</th>
					<th>評價內文</th>
				</tr>
				<c:forEach var="tktItem" items="${tktItems}">
					<tr>
						<td>${tktItem.tktOrderId}</td>
						<td>${tktItem.ticket.tktName}</td>
						<td>${tktItem.amount}</td>
						<td>${(tktItem.score == null)? "還未評分" : tktItem.score}</td>
						<td>${(tktItem.content == null)? "還未評論" : tktItem.content}</td>
					</tr>
				</c:forEach>
			</table>
			
			<button id="pre">上一頁</button>
			

		</div>
	</div>


	<script>
        const button1=document.querySelector('#pre');
        button1.addEventListener('click',e=>{history.back();});
    </script>


</body>
</html>