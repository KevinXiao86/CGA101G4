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

		<label for="cust_id">會員編號:</label>
		<input type="text" name="custId" id="cust_id" value="${customer.custId}" readonly="readonly"><br>

		<label for="cust_name">會員姓名:</label>
		<input type="text" name="name" id="cust_name" value="${customer.name}" readonly="readonly"><br>
		
<!-- 		<label for="cust_status">會員狀態:</label> -->
<%-- 		<input type="text" name="custStatus" id="cust_status" value="${customer.custStatus}" readonly="readonly"><br> --%>

		<label for="cust_tel">會員電話:</label>
		<input type="tel" name="tel" id="cust_tel" value="${customer.tel}" readonly="readonly"><br>
		
<!-- 		<label for="bank_account">銀行帳號:</label> -->
<%-- 		<input type="tel" name="bankAccount" id="bank_account" value="${customer.bankAccount}" readonly="readonly"><br> --%>
		
		<label for="cust_mail">會員電子信箱:</label>
		<input type="email" name="email" id="cust_mail" value="${customer.email}" readonly="readonly"><br>

		<label for="address">聯絡地址:</label>
		<input type="text" name="address" id="address" value="${customer.address}" readonly="readonly"><br>

		<label for="idCard">身分證字號:</label>
		<input type="text" name="idCard" id="idCard" value="${customer.idCard}" readonly="readonly"><br>
		
		<label for="cust_account">帳號:</label>
		<input type="text" name="account" id="cust_account" value="${customer.account}" readonly="readonly"><br>
		
		<label for="img">大頭貼:</label>
		<img id="img" alt="" src="${customer.img}"/><br>


		
	<button id="pre">上一頁</button>
    
	<div>
		<a href='back-end/roomOrder/roomOrder_index.jsp'>回到訂房訂單首頁</a>
	</div>
	
	<script>
        const button1=document.querySelector('#pre');
       
        button1.addEventListener('click',e=>{history.back();});
    </script>
	
</body>
</html>