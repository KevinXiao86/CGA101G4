<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商詳情頁面</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
		<label for="cmp_name">廠商名稱:</label>
		<input type="text" name="cmpName" id="cmp_name" value="${requestScope.detailCompany.cmpName}"><br>

		<label for="cmp_tel">廠商電話:</label>
		<input type="tel" name="cmpTel" id="cmp_tel" value="${requestScope.detailCompany.cmpTel}"><br>
		
		<label for="bank_account">銀行帳號:</label>
		<input type="tel" name="bankAccount" id="bank_account" value="${requestScope.detailCompany.bankAccount}"><br>
		
		<label for="cmp_mail">廠商電子信箱:</label>
		<input type="email" name="cmpMail" id="cmp_mail" value="${requestScope.detailCompany.cmpMail}"><br>

		<label for="cmper">廠商負責人:</label>
		<input type="text" name="cmper" id="cmper" value="${requestScope.detailCompany.cmper}"><br>

		<label for="cmper_tel">廠商負責人電話:</label>
		<input type="tel" name="cmperTel" id="cmper_tel" value="${requestScope.detailCompany.cmperTel}"><br>

		<label for="serial_no">旅館登記證:</label>
		<img id="serial_no" alt="" src="${requestScope.detailCompany.serialNo}"/><br>
		
		<label for="cmp_account">帳號:</label>
		<input type="text" name="cmpAccount" id="cmp_account" value="${requestScope.detailCompany.cmpAccount}"><br>

		<label for="cmp_password">密碼:</label>
		<input type="password" name="cmpPassword" id="cmp_password" value="${requestScope.detailCompany.cmpPassword}"><br>

		<label for="cmp_introduce">飯店介紹:</label>
		<textarea rows="6" cols="40" name="cmpIntroduce" id="cmp_introduce">${requestScope.detailCompany.cmpIntroduce}</textarea><br>

		<label for="checkin_time">入住時間:</label>
		<input type="time" id="checkin_time" name="checkinTime" min="00:00" max="24:00" value="${requestScope.detailCompany.checkinTime}"><br>

		<label for="checkout_time">退房時間:</label>
		<input type="time" id="checkout_time" name="checkoutTime" min="00:00" max="24:00" value="${requestScope.detailCompany.checkoutTime}"><br>

		<label for="location">地點:</label>
		<input type="text" id="location" name="location" value="${requestScope.detailCompany.location}"><br>

		<label for="notice">購買須知:</label>
		<textarea rows="6" cols="40" name="notice" id="notice">${requestScope.detailCompany.notice}</textarea><br>

		<label for="canx">取消政策:</label>
		<textarea rows="6" cols="40" name="canx" id="canx">${requestScope.detailCompany.canx}</textarea><br>


	<c:choose>
		<c:when test="${requestScope.detailCompany.auditStatus == '待審核'}">
			<a href="manager/company/audit?cmpId=${requestScope.detailCompany.cmpId}&auditStatus=審核通過">審核通過</a>
			<a href="manager/company/audit?cmpId=${requestScope.detailCompany.cmpId}&auditStatus=審核未通過">審核未通過</a>
		</c:when>

		<c:when test="${requestScope.detailCompany.auditStatus == '審核通過'}">審核完成</c:when>

		<%--有時間在做這個功能 --%>
		<c:when test="${requestScope.detailCompany.auditStatus == '審核未通過'}">
			<a href="manager/company/sendEmail?cmpId=${requestScope.detailCompany.cmpId}">告知補件</a>
		</c:when>
	</c:choose>
</body>
</html>