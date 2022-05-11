<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
/* 	height: 900px; */
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
	margin-left: 80%;
}
input{
	width: 300px;
}
</style>

</head>

<div id="page-wrapper">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">訂單詳情</h1>
			</div>
		</div>
		<div style="display:flex;">
			<div style=" width: 40%; margin-right: 100px;">
				<div class="form-group">
					<label for="roomOrderId">訂單編號 :</label> 
					<input  type="text" id="roomOrderId" name="roomOrderId" readonly="readonly" value="${roomOrder.roomOrderId}" />
				</div>
				<div class="form-group">
					<label for="custId">會員編號 :</label> 
					<input  type="text" id="custId" name="custId" readonly="readonly" value="${roomOrder.custId}" />
				</div>
				<div class="form-group">
					<label for="name">會員姓名 :</label> 
					<input  type="text" id="name" name="name" readonly="readonly" value="${roomOrder.customer.name}" />
				</div>
				<div class="form-group">
					<label for="cmpId">廠商編號 :</label> 
					<input  type="text" id="cmpId" name="cmpId" readonly="readonly" value="${roomOrder.cmpId}" />
				</div>
				<div class="form-group">
					<label for="roomOrderDate">成立日期 :</label> 
					<input  type="text" id="roomOrderDate" name="roomOrderDate" readonly="readonly" value='<fmt:formatDate value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd " />' />
				</div>
				<div class="form-group">
					<label for="checkinDate">入住日期 :</label> 
					<input  type="text" id="checkinDate" name="checkinDate" readonly="readonly" value='<fmt:formatDate value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd " />' />
				</div>
				<div class="form-group">
					<label for="checkoutDate">退房日期 :</label> 
					<input  type="text" id="checkoutDate" name="checkoutDate" readonly="readonly" value='<fmt:formatDate value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd " />' />
				</div>
				<div class="form-group">
					<label for="roomOrderStatus">訂單狀態 :</label> 
					<input  type="text" id="roomOrderStatus" name="roomOrderStatus" readonly="readonly" value="${roomOrder.roomOrderStatus}" />
				</div>
				<div class="form-group">
					<label for="cmpIntroduce">取消原因 :</label> 
					<span>${errorMsgs.cmpIntroduce}</span><br>
					<textarea cols="50" rows="5" style="resize: none;" name="cmpIntroduce" id="cmpIntroduce" readonly="readonly">${roomOrder.cancel}</textarea>
				</div>
				<div class="form-group">
					<label for="roomOrderPrice">原始金額 :</label> 
					<input  type="text" id="roomOrderPrice" name="roomOrderPrice" readonly="readonly" value="${roomOrder.roomOrderPrice}" />
				</div>
				<div class="form-group">
					<label for="totalPrice">總金額 :</label> 
					<input style="width: 312px;" type="text" id="totalPrice" name="totalPrice" readonly="readonly" value="${roomOrder.totalPrice}" />
				</div>
				<div class="form-group">
					<label for="custCopId">會員優惠券編號 :</label> 
					<input style="width: 260px;" type="text" id="custCopId" name="custCopId" readonly="readonly" value='${(roomOrder.custCopId== null)? "未使用優惠券" : roomOrder.custCopId}' />
				</div>
			</div>
			<div style="width: 40%;">
				<div class="form-group">
					<label for="roomId">房型編號 :</label> 
					<input  type="text" id="roomId" name="roomId" readonly="readonly" value="${roomItemVO.roomId}" />
				</div>
				<div class="form-group">
					<label for="roomtypeName">房型名稱 :</label> 
					<input  type="text" id="roomtypeName" name="roomtypeName" readonly="readonly" value="${roomtype.roomtypeName}" />
				</div>
				<div class="form-group">
					<label for="roomItemAmount">訂購數量 :</label> 
					<input  type="text" id="roomItemAmount" name="roomItemAmount" readonly="readonly" value="${roomItemVO.roomItemAmount}" />
				</div>
				<div class="form-group">
					<label for="roomItemEvaluateScore">評價分數 :</label> 
					<input  type="text" id="roomItemEvaluateScore" name="roomItemEvaluateScore" readonly="readonly" value="${roomItemVO.roomItemEvaluateScore}" />
				</div>
				<div>
					<label for="roomItemEvaluateMsg">評價內文 :</label> 
					<textarea rows="6" cols="40" name="roomItemEvaluateMsg" id="roomItemEvaluateMsg" readonly="readonly" style="resize: none;">${roomItemVO.roomItemEvaluateMsg}</textarea><br>	
				</div>
			</div>
		</div>		
		<button id="pre">上一頁</button>
	</div>
</div>
<script type="text/javascript">
		   const button1=document.querySelector('#pre');
		   button1.addEventListener('click',e=>{history.back();});
	</script>
<body>

</body>
</html>