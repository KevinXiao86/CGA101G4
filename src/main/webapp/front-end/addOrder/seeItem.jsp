<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.service.roomtype.RoomtypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>台玩</title>
<!--  Favicon title 小圖 -->
<link rel ="icon" href="<%=request.getContextPath() %>/static/img/core-img/favicon.ico">
<style>
 table{
  text-align:center;
 }
</style>
<%
//假設會員10000已登入
int i = 10000;
CustomerServiceImpl custSvc = new CustomerServiceImpl();
CustomerVO custVO = custSvc.getAll(i);
request.setAttribute("custVO", custVO);
%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
	 <!-- Header -->
<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
 <jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>
 
 

 <main id="main" class="main" style="padding-left: 70px; height: 1200px; padding-top: 40px;">

  <div class="pagetitle">
   <h1>訂房訂單</h1>
   <nav>
    <ol class="breadcrumb">
     <li class="breadcrumb-item">首頁</li>
     <li class="breadcrumb-item">瀏覽訂單</li>
     <li class="breadcrumb-item">訂房訂單</li>
     <li class="breadcrumb-item active">訂房訂單明細</li>
    </ol>
   </nav>
  </div>

  <div class="w-80 p-3 "
   style="background-color: #eee; margin: 50px auto 30px;">

   <table border="1" class="table table-bordered" >
		<thead>
			<tr>
				<th colspan="3"  >預定人資訊</th>
			</tr>
		</thead>
		<tr>
			<th >預定人姓名</th>
			<th>E-MAIL</th>
			<th>電話</th>
		</tr>
		<tr>
			<th>${custVO.name}</th>
			<th>${custVO.email}</th>
			<th>${custVO.tel}</th>
		</tr>
	</table>
	<br>

	<table border="1" class="table table-bordered">
		<thead>
			<tr>
				<th colspan="2">住宿訊息</th>
			</tr>
		</thead>
		<tr>
			<th>飯店</th>
			<th>${cmpVO.cmpName }</th>
		</tr>
		<tr>
			<th>房型</th>
			<th>${roomtypeVO.roomtypeName}</th>
		</tr>
		<tr>
			<th>飯店電話</th>
			<th>${cmpVO.cmpTel}</th>
		</tr>
		<tr>
			<th>飯店地址</th>
			<th>${cmpVO.location}</th>
		</tr>
	</table>
	<br>
	<table border="1" class="table table-bordered">
		<thead>
			<tr>
				<th colspan="2">訂單詳情</th>
			</tr>
		</thead>
		<tr>
			<th>房型</th>
			<th>${roomtypeVO.roomtypeName}</th>
		</tr>
		<tr>
			<th>房價</th>
			<th>$${roomtypeVO.roomtypePrice}</th>
		</tr>
		<tr>
			<th>房數</th>
			<th>${list[0].roomItemAmount }</th>
		</tr>
		<tr>
			<th>入住日</th>
			<th>${roomOrderVO.roomOrderCheckinDate }</th>
		</tr>
		<tr>
			<th>退房日</th>
			<th>${roomOrderVO.roomOrderCheckoutDate }</th>
		</tr>
		<tr>
			<th>優惠</th>
			<th>${couponVO.discount}</th>
		</tr>
		<tr>
			<th>總金額</th>
			<th>${roomOrderVO.roomOrderTotalPrice }</th>
		</tr>
	</table>


<table>
	<tr>
	<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/addRepCmp/selectRoomId" name="form1">
		<input type="hidden" name="roomtypeId"
			value="${roomtypeVO.roomtypeId}">  
			<input type="submit" class="btn btn-light"
			value="檢舉廠商">
	<td>${errorMsgs.repCmp}</td>

	</FORM></td>
	<td>&emsp;</td>
	<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder12/SelectRoomItem" name="form2">
		<input type="hidden" name="roomItemId" value="${list[0].roomItemId}"> 
			<input type="submit" class="btn btn-light" value="評價">
	<td>${errorMsgs.evaluate}</td>

	</FORM>





</table>
  </div>
 </main>
 
 
 
 
	 <!-- #### Footer start #### -->
 <jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>
 

</body>


</html>