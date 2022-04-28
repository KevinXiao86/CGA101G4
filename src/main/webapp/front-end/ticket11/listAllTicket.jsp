<%@page import="com.taiwan.service.impl.TicketServiceImpl"%>
<%@page import="com.taiwan.service.*"%>
<%@page import="com.taiwan.beans.TicketVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    List<TicketVO> list = (List<TicketVO>)request.getAttribute("list");
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有票券資料 - listAllTicket.jsp</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
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
	width: 1200px;
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

<table id="table-1">
	<tr><td>
		 <h3>所有票券資料</h3>
		 <h4><a href="front-end/ticket11/ticketIndex.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.key} : ${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>票券編號</th>
		<th>票券名稱</th>
		<th>原始數量</th>
		<th>價錢</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>地點</th>
		<th>票券說明</th>
		<th>體驗地點</th>
		<th style="width:200px">注意事項</th>
		<th>如何使用</th>
		<th>取消政策</th>
		<th>狀態</th>
<!-- 		<th>賣出數量</th> -->
		<th>種類</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="tktVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${tktVO.tktId}</td>
			<td>${tktVO.tktName}</td>
			<td>${tktVO.originalAmount}</td>
			<td>${tktVO.price}</td>
			<td><fmt:formatDate value="${tktVO.startdate}" pattern="yyyy-MM-dd'T'HH:mm"/></td>
			<td><fmt:formatDate value="${tktVO.enddate}" pattern="yyyy-MM-dd'T'HH:mm"/></td>
			<td>${tktVO.location}</td> 
			<td>${tktVO.instruction}</td> 
			<td>${tktVO.address}</td> 
			<td>${tktVO.notice}</td> 
			<td>${tktVO.howuse}</td> 
			<td>${tktVO.canxpolicy}</td> 
			<td>${tktVO.status}</td> 
<%-- 			<td>${tktVO.soldAmount}</td>  --%>
			<td>${tktVO.kind}</td> 
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>