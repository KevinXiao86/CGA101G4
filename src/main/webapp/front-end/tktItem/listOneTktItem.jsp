<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<title>台玩</title>

<!-- Favicon title的小圖 -->
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

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
			<h1>票券明細</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">首頁</li>
					<li class="breadcrumb-item">瀏覽訂單</li>
					<li class="breadcrumb-item active">票券訂單</li>
				</ol>
			</nav>
		</div>

		<div class="w-80 p-3 "
			style="background-color: #ced7e8; margin: 50px auto 30px;">

			<table border="2" class="table table-hover">
				<thead>
					<tr>
						<th>訂單編號</th>
						<th>訂購者</th>
						<th>電話</th>
						<th>信箱</th>
						<th>票券名稱</th>
						<th>數量</th>
						<th>評價分數</th>
						<th>評價內文</th>
						<th>回饋</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tktItem" items="${itemList}">
						<tr>
							<td>${tktItem.tktOrderId}</td>
							<td>${tktOrder.orderName}</td>
							<td>${tktOrder.orderMobile}</td>
							<td>${tktOrder.orderEmail}</td>
							<td>${tktItem.ticket.tktName}</td>
							<td>${tktItem.amount}張</td>
							<td>${tktItem.score}</td>
							<td>${tktItem.content}</td>
							<c:if test="${tktItem.score == null}">
								<td><input type="submit" value="立即給予評分" onclick="show()"></td>
							</c:if>
						</tr>

						<div id="showdiv" style="display: none">
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/tktItem/content"
								style="margin-bottom: 0px;">
								評分:
								<div>${errorMsgs.score}</div>
								<input type="number" value="1" min="1" max="5" name="score"	style="width:40px;" required>${param.score}<br> 
								評價內容:
								<div>${errorMsgs.content}</div>
								<textarea name="content" style="width:300px;" placeholder="請輸入500字以內" required>${param.content}</textarea>

								<input type="hidden" name="tktOrderId"
									value="${tktItem.tktOrderId}"> <input type="hidden"
									name="tktId" value="${tktItem.tktId}"> <input
									type="hidden" name="action" value="insert"> 
									<input type="submit" value="送出新增" style="width:80px;background-color: lightcoral;color: white;margin-bottom:30px;">
							</FORM>
						</div>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>


	<script>
		function show() {
			if (document.getElementById('showdiv').style.display == 'none') {
				document.getElementById('showdiv').style.display = 'block';
			}
// 			else {
// 				document.getElementById('showdiv').style.display = 'none';
// 			}
		}
	</script>
</body>

</html>