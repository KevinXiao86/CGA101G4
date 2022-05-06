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
</head>
<%--
Context ctx = new javax.naming.InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
Connection con = ds.getConnection();
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM Taiwan.ROOM_ITEM ");

ResultSetMetaData rsmd = rs.getMetaData();
int numberOfColumns = rsmd.getColumnCount();
--%>
<div>
	<table border="1">
		<thead>
			<tr>
				<th colspan="3">預定人資訊</th>
			</tr>
		</thead>
		<tr>
			<th>預定人姓名</th>
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

	<table border="1">
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
	<table border="1">
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
</div>

<table border="1">
	<FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/addRepCmp/selectRoomId" name="form1">
		<input type="hidden" name="roomtypeId"
			value="${roomtypeVO.roomtypeId}"> <input type="submit"
			value="檢舉廠商">${errorMsgs.repCmp}

	</FORM>
	<FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/roomOrder12/SelectRoomItem" name="form2">
		<input type="hidden" name="roomItemId" value="${list[0].roomItemId}"> 
			<input type="submit" value="評價">${errorMsgs.evaluate}

	</FORM>
	<!-- 	<tr> -->
	<%-- 		<% --%>
	<%--		for (int i = 1; i <= numberOfColumns; i++) {--%>
	<%-- 		%> --%>
	<%-- 		<th><%=rsmd.getColumnName(i)%></th> --%>
	<%-- 		<% --%>
	<%-- 	}--%>
	<%-- 		%> --%>
	<!-- 	</tr> -->






</table>

<%--
con.close();
--%>
</body>


</html>