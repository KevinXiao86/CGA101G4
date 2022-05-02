<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>票券首頁 Ticket_Index</title>
</head>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">票券管理頁面</h1>
				</div>
			</div>

			<form action="ticket/findAll" method="post">
				<input type="submit" value="搜尋全部">
			</form>

			<div>
				<a href='back-end/ticket/ticket_create.jsp'>新增一筆票券</a>
			</div>


			<%-- 	<%--錯誤列表 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<div>
				<FORM METHOD="post" ACTION="ticket/selectById">
					<b>輸入票券編號來做查詢:</b> 
					<input type="text" name="tktId"> 
					<input type="submit" value="送出">
				</FORM>
			</div>
			<div>
				<FORM METHOD="post" ACTION="ticket/selectByTitle">
					<b>輸入票券標題來做查詢:</b> 
					<input type="text" name="tktName"> 
					<input type="submit" value="送出">
				</FORM>
			</div>

		</div>
	</div>



	<!--     <div> -->
	<!--     <FORM METHOD="post" ACTION="coupon/selectByStatus" > -->
	<!--         <b>輸入優惠券狀態來做查詢:</b> -->
	<!--         <select name="status" > -->
	<!-- 			<option value="上架">上架</option> -->
	<!-- 			<option value="下架">下架</option> -->
	<!-- 		</select> -->
	<!--         <input type="submit" value="送出"> -->
	<!--     </FORM> -->
	<!--     </div> -->



</body>
</html>