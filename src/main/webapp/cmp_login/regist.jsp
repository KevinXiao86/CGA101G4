<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商登陸頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<style>
span{
	color: red;
	font-size: 16px;
}
</style>

<link rel="stylesheet" href="common/css/login.css">
<script src="common/js/regist.js"></script>
</head>
<body>
	<div class="form">
		<div class="form-toggle"></div>
		
		<div class="form-panel one">
		
			<div class="form-header">
				<h1>Register Account</h1>
			</div>
			<!-- 錯誤訊息回顯 -->
			<span id="errorMsg">${requestScope.registCompany.message}</span><br><br>
			
			<div class="form-content">
			
				<form action="company/regist" method="post" enctype="multipart/form-data">
					<!-- 隐藏域保存服务端token -->
					<input type="hidden" id="token" name="token" ${sessionScope.token}/>
					
					<div class="form-group">
						<label for="cmpName">廠商名稱</label> 
						<span>${requestScope.errorInfo.cmpName}</span>
						<input type="text" id="cmpName" name="cmpName" required="required" value="${requestScope.registCompany.cmpName}"/>
					</div>
					
					<div class="form-group">
						<label for="cmpTel">廠商電話</label> 
						<span>${requestScope.errorInfo.cmpTel}</span>
						<input type="text" id="cmpTel" name="cmpTel" required="required" value="${requestScope.registCompany.cmpTel}"/>
					</div>
					
					<div class="form-group">
						<label for="bankAccount">銀行帳號</label> 
						<span>${requestScope.errorInfo.headBank}</span>
						<input type="text" id="headBank" name="headBank" placeholder="銀行代號" required style="margin-bottom: 10px;"
							value="${requestScope.registCompany.bankAccount.substring(0, 3)}"> 
						<span>${requestScope.errorInfo.endBank}</span>
						<input type="text" name="endBank" id="endBank" placeholder="銀行帳號" required 
							value="${requestScope.registCompany.bankAccount.substring(4)}">
					</div>
					
					<div class="form-group">
						<label for="cmpMail">信箱</label> 
						<span>${requestScope.errorInfo.cmpMail}</span>
						<input type="text" id="cmpMail" name="cmpMail" required="required" value="${requestScope.registCompany.cmpMail}"/>
					</div>
					
					<div class="form-group">
						<label for="cmper">廠商負責人</label> 
						<span>${requestScope.errorInfo.cmper}</span>
						<input type="text" id="cmper" name="cmper" required="required" value="${requestScope.registCompany.cmper}"/>
					</div>
					
					<div class="form-group">
						<label for="cmperTel">廠商負責人電話</label> 
						<span>${requestScope.errorInfo.cmperTel}</span>
						<input type="text" id="cmperTel" name="cmperTel" required="required" value="${requestScope.registCompany.cmperTel}"/>
					</div>

					<div class="form-group">
						<label for="serialNo">旅宿登記證</label>
						<span>${requestScope.serialNo}</span>
						<input type="file" name="serialNo" id="serialNo">
					</div>

					<div class="form-group">
						<label for="cmpAccount">帳號</label> 
						<span id="errorCmpAccount"></span>
						<span>${requestScope.errorInfo.cmpAccount}</span>
						<input type="text" id="cmpAccount" name="cmpAccount" required="required" value="${requestScope.registCompany.cmpAccount}"/>
					</div>
					
					<div class="form-group">
						<label for="cmpPassword">密碼</label> 
						<span>${requestScope.errorInfo.cmpPassword}</span>
						<input type="password" id="cmpPassword" name="cmpPassword" required="required" />${requestScope.errorInfo.cmpPassword}<br>
					</div>

					<div class="form-group">
						<label for="cmpIntroduce">飯店介紹</label>
						<span>${requestScope.errorInfo.cmpIntroduce}</span>
						<textarea rows="6" cols="40" name="cmpIntroduce" id="cmpIntroduce" required>${requestScope.registCompany.cmpIntroduce}</textarea>
						<br>
					</div>
					
					<div class="form-group">
						<label for="checkinTime">入住時間</label> 
						<span>${requestScope.errorInfo.checkinTime}</span>
						<input type="time" id="checkinTime" name="checkinTime" min="00:00" max="24:00" required="required" value="${requestScope.registCompany.checkinTime}"/>
					</div>
					
					<div class="form-group">
						<label for="checkoutTime">退房時間</label> 
						<span>${requestScope.errorInfo.checkoutTime}</span>
						<input type="time" id="checkoutTime" name="checkoutTime" min="00:00" max="24:00" required="required" value="${requestScope.registCompany.checkoutTime}"/>
					</div>
					
					<div class="form-group">
						<label>地點</label> 
						<span>${requestScope.errorInfo.city}</span>
						<input type="text" id="city" name="city" placeholder="縣市" required="required" style="margin-bottom: 10px;" />
						<span>${requestScope.errorInfo.town}</span>
						<input type="text" id="town" name="town" placeholder="城鎮" required="required" style="margin-bottom: 10px;" /> 
						<span>${requestScope.errorInfo.road}</span>
						<input type="text" id="road" name="road" placeholder="街道" required="required" style="margin-bottom: 10px;" />
					</div>
					
					<div class="form-group">
						<label for="notice">購買須知</label>
						<span>${requestScope.errorInfo.notice}</span>
						<textarea rows="6" cols="40" name="notice" id="notice" required>${requestScope.registCompany.notice}</textarea>
						<br>
					</div>
					
					<div class="form-group">
						<label for="canx">取消政策</label>
						<span>${requestScope.errorInfo.canx}</span>
						<textarea rows="6" cols="40" name="canx" id="canx" required>${requestScope.registCompany.canx}</textarea>
						<br>
					</div>
					
					<div class="form-group">
						<button type="submit" id="regist">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src='common/js/jquery.min.js'></script>
	<script src="common/js/script.js"></script>
</body>
</html>