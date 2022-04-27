<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>FAQ資料 FAQFindById</title>
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
<body>

	<h1>根據Id搜尋FAQ</h1>

	<%--錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>FAQ編號</th>
			<th>FAQ標題</th>
			<th>FAQ介紹</th>
			<th>撰寫日期</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
			<tr>
				<td>${faqVO.faqId}</td>
				<td>${faqVO.title}</td>
				<td>${faqVO.content}</td>
				<td><fmt:formatDate value="${faqVO.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
					<FORM METHOD="post"	ACTION="faq/faq2Update" style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="faqId" value="${faqVO.faqId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="faq/faqDelete" style="margin-bottom: 0px;">
						<input type="submit" value="刪除">
						<input type="hidden" name="faqId" value="${faqVO.faqId}">
					</FORM>
				</td>
			</tr>
	</table>
	
	<div>
		<a href='back-end/faq/faq_index.jsp'>回FAQ首頁</a>
	</div>
 
</body>
</html>