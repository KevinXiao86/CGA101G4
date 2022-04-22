<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商資料</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<form action="company/editCompany" method="post">	
		<!-- 因為修改操作都需要 id, 所以使用隱藏域直接帶上 id -->
		<input type="hidden" name="cmpId" value="${requestScope.company.cmpId}">
		
		<label for="cmp_tel">廠商電話:</label>
		<input type="tel" name="cmpTel" id="cmp_tel" value="${requestScope.company.cmpTel}">${errorInfo.cmpTel}<br>

		<label for="cmp_mail">廠商電子信箱:</label>
		<input type="email" name="cmpMail" id="cmp_mail" value="${requestScope.company.cmpMail}">${errorInfo.cmpMail}<br>		
		
		<label for="cmp_introduce">飯店介紹:</label>
		<textarea rows="6" cols="40" name="cmpIntroduce" id="cmp_introduce">${requestScope.company.cmpIntroduce}</textarea>${errorInfo.cmpIntroduce}<br>

		<label for="checkin_time">入住時間:</label>
		<input type="time" id="checkin_time" name="checkinTime" min="00:00" max="24:00" value="${requestScope.company.checkinTime}">${errorInfo.checkinTime}<br>

		<label for="checkout_time">退房時間:</label>
		<input type="time" id="checkout_time" name="checkoutTime" min="00:00" max="24:00" value="${requestScope.company.checkoutTime}">${errorInfo.checkoutTime}<br>

		<label for="location">地點:</label>
		<input type="text" id="location" name="location" value="${requestScope.company.location}">${errorInfo.location}<br>

		<label for="notice">購買須知:</label>
		<textarea rows="6" cols="40" name="notice" id="notice">${requestScope.company.notice}</textarea>${errorInfo.location}<br>

		<label for="canx">取消政策:</label>
		<textarea rows="6" cols="40" name="canx" id="canx">${requestScope.company.canx}</textarea>${errorInfo.canx}<br>
		
		<button id="btn">提交修改</button>
	</form>
</body>
</html>