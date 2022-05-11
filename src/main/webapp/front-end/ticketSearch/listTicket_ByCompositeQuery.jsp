<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="com.taiwan.controller.ticket.*"%>
<%@ page import="com.taiwan.service.ticket.*"%>
<%@ page import="com.taiwan.dao.ticket.*"%>



<jsp:useBean id="ticketVOs" scope="request" type="java.util.List<TicketVO>" /> <!-- 於EL此行可省略 -->

<%@ include file="/common/head.jsp"%>
<html>
<head><title>複合查詢 - ticketVOs.jsp</title>

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
		 <h3>所有上架票券 - listAllTicket.jsp</h3>
		 <h4><a href="front-end/ticketSearch/ticketSelect_page.jsp"><img src="back-end/emp/images/back1.gif" 
		 width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>票券編號</th>
		<th>票券名稱</th>
		<th>售價</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>地點</th>
		<th>介紹</th>
		<th>種類</th>

	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="ticketVO" items="${ticketVOs}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' }>
			<td>${ticketVO.tktId}</td>
			<td>${ticketVO.tktName}</td>
			<td>${ticketVO.price}</td>
			<td>${ticketVO.startdate}</td>
			<td>${ticketVO.enddate}</td>
			<td>${ticketVO.location}</td>
			<td>${ticketVO.instruction}</td>
			<td>${ticketVO.kind}</td>	
						
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>