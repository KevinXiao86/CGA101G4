<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商註冊頁面</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<span id="error_msg">${requestScope.registCompany.message}${requestScope.loginCompany.message}</span>
	<form action="company/regist" method="post" enctype="multipart/form-data">
		<label for="cmp_name">廠商名稱:</label>
		<input type="text" name="cmpName" id="cmp_name" value="${requestScope.registCompany.cmpName}${requestScope.loginCompany.cmpName}">${requestScope.errorInfo.cmpName}<br>

		<label for="cmp_tel">廠商電話:</label>
		<input type="tel" name="cmpTel" id="cmp_tel" value="${requestScope.registCompany.cmpTel}${requestScope.loginCompany.cmpTel}">${requestScope.errorInfo.cmpTel}<br>
		
		<label for="bank_account">銀行帳號:</label>
		<input type="tel" name="bankAccount" id="bank_account" value="${requestScope.registCompany.bankAccount}${requestScope.loginCompany.bankAccount}">${requestScope.errorInfo.bankAccount}<br>

		<label for="cmp_mail">廠商電子信箱:</label>
		<input type="email" name="cmpMail" id="cmp_mail" value="${requestScope.registCompany.cmpMail}${requestScope.loginCompany.cmpMail}">${requestScope.errorInfo.cmpMail}<br>

		<label for="cmper">廠商負責人:</label>
		<input type="text" name="cmper" id="cmper" value="${requestScope.registCompany.cmper}${requestScope.loginCompany.cmper}">${requestScope.errorInfo.cmper}<br>

		<label for="cmper_tel">廠商負責人電話:</label>
		<input type="tel" name="cmperTel" id="cmper_tel" value="${requestScope.registCompany.cmperTel}${requestScope.loginCompany.cmperTel}">${requestScope.errorInfo.cmperTel}<br>

		<label for="uploadFile">旅館登記證:</label>
		<input type="file" name="uploadFile" id="uploadFile">${requestScope.errorInfo.uploadFile}<br>
		
		<label for="cmp_account">帳號:</label>
		<input type="text" name="cmpAccount" id="cmp_account" value="${requestScope.registCompany.cmpAccount}${requestScope.loginCompany.cmpAccount}">${requestScope.errorInfo.cmpAccount}<br>

		<label for="cmp_password">密碼:</label>
		<input type="password" name="cmpPassword" id="cmp_password">${requestScope.errorInfo.cmpPassword}<br>

		<label for="cmp_introduce">飯店介紹:</label>
		<textarea rows="6" cols="40" name="cmpIntroduce" id="cmp_introduce">${requestScope.registCompany.cmpIntroduce}${requestScope.loginCompany.cmpIntroduce}</textarea>${requestScope.errorInfo.cmpIntroduce}<br>

		<label for="checkin_time">入住時間:</label>
		<input type="time" id="checkin_time" name="checkinTime" min="00:00" max="24:00" value="${requestScope.registCompany.checkinTime}${requestScope.loginCompany.checkinTime}">${requestScope.errorInfo.checkinTime}<br>

		<label for="checkout_time">退房時間:</label>
		<input type="time" id="checkout_time" name="checkoutTime" min="00:00" max="24:00" value="${requestScope.registCompany.checkoutTime}${requestScope.loginCompany.checkoutTime}">${requestScope.errorInfo.checkoutTime}<br>

		<label for="location">地點:</label>
		<input type="text" id="location" name="location" value="${requestScope.registCompany.location}${requestScope.loginCompany.location}">${requestScope.errorInfo.location}<br>

		<label for="notice">購買須知:</label>
		<textarea rows="6" cols="40" name="notice" id="notice">${requestScope.registCompany.notice}${requestScope.loginCompany.notice}</textarea>${requestScope.errorInfo.notice}<br>

		<label for="canx">取消政策:</label>
		<textarea rows="6" cols="40" name="canx" id="canx">${requestScope.registCompany.canx}${requestScope.loginCompany.canx}</textarea>${requestScope.errorInfo.canx}<br>
		
		<button id="btn">註冊</button>
	</form>
</body>
</html>