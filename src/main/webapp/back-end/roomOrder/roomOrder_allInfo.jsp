<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<span>訂單編號</span> 
			<span>${roomOrder.roomOrderId}</span>
		</div>
		<div>
			<span>會員編號</span> 
			<span>${roomOrder.custId}</span>
		</div>
		<div>
			<span>訂單成立日期</span>
			<span><fmt:formatDate value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd " /></span>
		</div>
		<div>
			<span>入住日期</span> 
			<span><fmt:formatDate value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd " /></span>
		</div>
		<div>
			<span>退房日期</span> 
			<span><fmt:formatDate value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd " /></span>
		</div>
		<div>
			<span>訂單狀態</span> 
			<span>${roomOrder.roomOrderStatus}</span>
		</div>
		<div >
			<span>取消原因</span> 
			<span>${roomOrder.cancel}</span>
		</div>
		<div>
			<span>原始金額</span> 
			<span>${roomOrder.roomOrderPrice}</span>
		</div>
		<div>
			<span>總金額</span> 
			<span>${roomOrder.totalPrice}</span>
		</div>
		<div>
			<span>會員優惠券編號</span> 
			<span>${roomOrder.custCopId}</span>
		</div>
		============================================================================
		<div>
			<span>房型編號</span> 
			<span>${roomItemVO.roomId}</span>
		</div>
		<div>
			<span>訂購數量</span> 
			<span>${roomItemVO.roomItemAmount}</span>
		</div>
		<div>
			<span>評價分數</span> 
			<span>${roomItemVO.roomItemEvaluateScore}</span>
		</div>
		<div>
			<span>評價內文</span> 
			<span>${roomItemVO.roomItemEvaluateMsg}</span>
		</div>
	</div>
</body>
</html>