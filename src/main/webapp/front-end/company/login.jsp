<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商登錄頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<style>
span.errorMsg {
	color: red;
	font-size: 16px;
}

span#errorMsg {
	color: orange;
	font-size: 16px;
}
</style>

<link rel="stylesheet" href="common/css/login.css">
<!-- <script src="common/js/login.js"></script> -->
</head>
<body>
	<div class="form">
		<div class="form-toggle"></div>

		<div class="form-panel one">

			<div class="form-header">
				<h1>廠商登錄</h1>
			</div>

			<div class="form-content">
				<form action="company/login" method="post">
					<span class="errorMsg">${requestScope.loginCompany.message}</span>
					<div class="form-group">
						
						<label for="cmpAccount" style="font-weight: 900;">Account</label> <span class="errorMsg">${requestScope.errorInfo.cmpAccount}</span>
						<input type="text"
							id="cmpAccount" name="cmpAccount" required="required" value="${requestScope.loginCompany.cmpAccount}"/>
					</div>
					<div class="form-group">
						
						<label for="cmpPassword" style="font-weight: 900;">Password</label> <span class="errorMsg">${requestScope.errorInfo.cmpPassword}</span>
						<input type="password"
							id="cmpPassword" name="cmpPassword" required="required" />
					</div>
					<div class="form-group">
						<label class="form-remember"> <input type="checkbox" />Remember
							Me
						</label><a class="form-recovery" href="#" style="font-weight: 900;">返回首頁</a>
					</div>
					<div class="form-group">
						<button type="submit" id="login" style="font-weight: 900;">Log In</button>
					</div>
				</form>
			</div>

		</div>

	</div>

	<script src="common/js/jquery.min.js"></script>
	<script src="common/js/script.js"></script>
	<script type="text/javascript">
	//頁面加載完成
	$(() => {
	    $("#login").click(() => {
	        //1. 先獲取輸入框裡面的值
	        var account = $("#cmpAccount").val();
	        //2. 創建政則表達式
	        var accountPatt = /^\w{4,20}$/;
	        //3. 使用 test 方法做數據校驗
	        //補充: test() 方法用於檢測一個字符串是否匹配某個模式，如果字符串中含有匹配的文本，則返回true，否則返回false。
	        if (!accountPatt.test(account)) {
	            //4. 提示用戶結果
	            $("span.errorMsg").text("帳號必須由字母, 數字下滑線組成,並且長度為 4 ~ 20 位");
	            //5. 阻止提交
	            return false;
	        }


	        //1. 先獲取輸入框裡面的值
	        var password = $("#cmpPassword").val();
	        //2. 創建政則表達式
	        var passwordPatt = /^\w{4,20}$/;
	        //3. 使用 test 方法做數據校驗
	        //補充: test() 方法用於檢測一個字符串是否匹配某個模式，如果字符串中含有匹配的文本，則返回true，否則返回false。
	        if (!passwordPatt.test(password)) {
	            //4. 提示用戶結果
	            $("span.errorMsg").text("密碼必須由字母, 數字下滑線組成,並且長度為 4 ~ 20 位");
	            //5. 阻止提交 
	            return false;
	        }

	        // 去掉錯誤訊息
	        $("span.errorMsg").text("");
	    });
	});
	</script>
</body>
</html>