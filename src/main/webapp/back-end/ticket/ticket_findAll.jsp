<%@page import="com.taiwan.beans.TicketVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%
List<TicketVO> list = (List<TicketVO>) request.getAttribute("list");
pageContext.setAttribute("list", list);
%>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>所有票券資料 TicketFindAll</title>
<style>
#page-wrapper {
/* 	background-color: rgb(221, 221, 241) !important; */
	/*     height: 600px;   */
}

table {
	width: 1100px;
	background-color: #ced7e8 !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
/* 	border: 3px solid #CCCCFF; */
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>
</head>
<body>


	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">所有票券</h1>
				</div>
			</div>
			<%--錯誤列表 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<table border="2px" class="table table-striped table-hover">
				<tr>
					<th>票券編號</th>
					<th>票券名稱</th>
					<th>價格</th>
					<th>開始日期</th>
					<th>結束日期</th>
					<th>地點</th>
					<th>狀態</th>
					<th>詳情</th>
<!-- 					<th>刪除</th> -->
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach items="${requestScope.list}" var="ticketVO"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${ticketVO.tktId}</td>
						<td>${ticketVO.tktName}</td>
						<td>${ticketVO.price}</td>
						<td><fmt:formatDate value="${ticketVO.startdate}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${ticketVO.enddate}"	pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${ticketVO.location}</td>
						<td>
							<FORM METHOD="post" ACTION="ticket/tktStatusChange"	style="margin-bottom: 0px;">
								<input type="submit" name="status" value="${ticketVO.status}" style='background: ${(ticketVO.status == "上架")?"lightblue": ""};'>
								<input type="hidden" name="tktId" value="${ticketVO.tktId}">
								<input type="hidden" name="whichPage" value="<%=whichPage %>">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post" ACTION="ticket/tkt2Update" style="margin-bottom: 0px;">
								<input type="submit" value="修改票券"> 
								<input type="hidden" name="tktId" value="${ticketVO.tktId}">
								<input type="hidden" name="whichPage" value="<%=whichPage %>">
							</FORM>
						</td>
<!-- 						<td> -->
<!-- 							<FORM METHOD="post" ACTION="ticket/tktDelete" style="margin-bottom: 0px;"> -->
<!-- 								<input type="submit" value="刪除">  -->
<%-- 								<input type="hidden" name="tktId" value="${ticketVO.tktId}"> --%>
<%-- 								<input type="hidden" name="whichPage" value="<%=whichPage %>"> --%>
<!-- 							</FORM> -->
<!-- 						</td> -->
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</div>
	</div>


</body>
</html>