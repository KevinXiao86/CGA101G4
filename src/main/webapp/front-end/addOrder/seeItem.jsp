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
<meta charset="UTF-8">
<title>AddOrder</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%--@ include file="/common/head.jsp"--%>
<%
//假設會員10000已登入
int i = 10000;
CustomerServiceImpl custSvc = new CustomerServiceImpl();
CustomerVO custVO = custSvc.getAll(i);
request.setAttribute("custVO", custVO);
%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>

<div class="w-75 p-3 " style="background-color: #eee;">
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


<table border="1">
<table>
	<tr>
	<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/addRepCmp/selectRoomId" name="form1">
		<input type="hidden" name="roomtypeId"
			value="${roomtypeVO.roomtypeId}"> <input type="submit"
			value="檢舉廠商">${errorMsgs.repCmp}

	</FORM></td>
	<td></td>
	<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder12/SelectRoomItem" name="form2">
		<input type="hidden" name="roomItemId" value="${list[0].roomItemId}"> 
			<input type="submit" value="評價">${errorMsgs.evaluate}

	</FORM></td>
</tr>
</table>





</table>
</div>
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>


</html>