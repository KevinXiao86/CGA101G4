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
	<span id="error_msg">${requestScope.editCompany.message}</span>
	<form action="company/editCompany" method="post">	
		<!-- 因為修改操作都需要 id, 所以使用隱藏域直接帶上 id -->
		<input type="hidden" name="cmpId" value="${requestScope.editCompany.cmpId}">
		
		<label for="cmp_tel">廠商電話:</label>
		<input type="tel" name="cmpTel" id="cmp_tel" value="${requestScope.editCompany.cmpTel}">${requestScope.errorInfo.cmpTel}<br>
		
		<label for="bank_account">銀行帳號:</label>
		<input type="tel" name="bankAccount" id="bank_account" value="${requestScope.editCompany.bankAccount}">${requestScope.errorInfo.bankAccount}<br>

		<label for="cmp_mail">廠商電子信箱:</label>
		<input type="email" name="cmpMail" id="cmp_mail" value="${requestScope.editCompany.cmpMail}">${requestScope.errorInfo.cmpMail}<br>		
		
		<label for="cmp_introduce">飯店介紹:</label>
		<textarea rows="6" cols="40" name="cmpIntroduce" id="cmp_introduce">${requestScope.editCompany.cmpIntroduce}</textarea>${requestScope.errorInfo.cmpIntroduce}<br>

		<label for="checkin_time">入住時間:</label>
		<input type="time" id="checkin_time" name="checkinTime" min="00:00" max="24:00" value="${requestScope.editCompany.checkinTime}">${requestScope.errorInfo.checkinTime}<br>

		<label for="checkout_time">退房時間:</label>
		<input type="time" id="checkout_time" name="checkoutTime" min="00:00" max="24:00" value="${requestScope.editCompany.checkoutTime}">${requestScope.errorInfo.checkoutTime}<br>

		<label for="location">地點:</label>
		<input type="text" id="location" name="location" value="${requestScope.editCompany.location}">${requestScope.errorInfo.location}<br>

		<label for="notice">購買須知:</label>
		<textarea rows="6" cols="40" name="notice" id="notice">${requestScope.editCompany.notice}</textarea>${requestScope.errorInfo.notice}<br>

		<label for="canx">取消政策:</label>
		<textarea rows="6" cols="40" name="canx" id="canx">${requestScope.editCompany.canx}</textarea>${requestScope.errorInfo.canx}<br>
		
		<button id="btn">提交修改</button>
	</form>
</body>
</html>