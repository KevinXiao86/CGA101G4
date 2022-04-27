<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>撰寫一張優惠券</title>
</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<form action="coupon/couponCreator" method="post" enctype="multipart/form-data">
		<label>優惠券名稱</label><input type="text" name="copName" autofocus 	placeholder="請輸入優惠券名稱" value="${param.copName}"><br> 
		<label>優惠券折價金額</label><input type="number" name="discount" step="10" min="0"  placeholder="請輸入折價金額" value="${param.discount}"><br>
		<label>優惠券開始日期</label><input id="startdate" name="startdate" type="datetime-local" value="${param.startdate}"><br> 
		<label>優惠券結束日期</label><input id="enddate" name="enddate" type="datetime-local" value="${param.enddate}"><br> 
		<label>優惠券介紹</label><br>
		<textarea name="introduce" id="content" cols="100" rows="10" style="resize: none;" placeholder="請輸入介紹"  >${param.introduce}</textarea><br>
		<label>請輸入照片</label> <input type="file" name="uploadFile" accept="image/*" required><br>
		<input type="submit" value="提交">
	</form>
	
	<div>
		<a href='back-end/coupon/cop_index.jsp'>回到優惠券首頁</a>
	</div>

</body>
</html>