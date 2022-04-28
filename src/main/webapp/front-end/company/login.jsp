<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商登陸頁面</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
    <span id="error_msg">${requestScope.loginCompany.message}</span>
    <form action="company/login" method="post">
        <label for="cmpAccount">帳號:</label>
        <input type="text" name="cmpAccount" id="cmpAccount" placeholder="請輸入字母,數字,下滑線" required value="${requestScope.loginCompany.cmpAccount}${requestScope.cmpAccount}">
        <br>

        <label for="cmpPassword">密碼:</label>
        <input type="password" name="cmpPassword" id="cmpPassword" placeholder="請輸入字母,數字,下滑線" required>

        <br>
        <button id="btn">登入</button>

        <br>
        <a href="#">忘記密碼</a>
        <a href="#">註冊</a>
    </form>
</body>
</html>