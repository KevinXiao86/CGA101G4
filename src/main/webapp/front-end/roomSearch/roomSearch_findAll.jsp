<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="com.taiwan.controller.roomSelect.*"%>
<%@ page import="com.taiwan.service.roomSelect.*"%>
<%@ page import="com.taiwan.dao.roomSelect.*"%>



<jsp:useBean id="roomSelectVO" scope="request" type="java.util.List<RoomSelectVO>" /> 

<%@ include file="/common/head.jsp"%>
<html>
<head><title>Room複合查詢 - </title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有房間 - listAllRoomTicket.jsp</h3>
		 <h4><a href="front-end/roomSearch/roomSelect_page.jsp"><img src="back-end/emp/images/back1.gif" 
		 width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>廠商名子</th>
		<th>房型</th>
		<th>售價</th>
		<th>地點</th>
		<th>圖片</th>
		

	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="roomSelectVO" items="${roomSelectVO}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' }>
			<td>${roomSelectVO.cmpName}</td>
			<td>${roomSelectVO.roomtypeName}</td>
			<td>${roomSelectVO.roomtypePrice}</td>
			<td>${roomSelectVO.location}</td>
			<td>${roomSelectVO.roomImg}</td>
			
						
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>