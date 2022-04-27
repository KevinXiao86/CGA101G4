<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>最新消息資料 newsById</title>
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
img{
 width: 150px;
 height: 150px;
}
</style>
</head>
<body>

	<h1>根據編號搜尋最新消息</h1>

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
			<th>最新消息編號</th>
			<th>最新消息標題</th>
			<th>最新消息介紹</th>
			<th>撰寫日期</th>
			<th>圖片</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
<%-- 		<%@ include file="page1.file" %>  --%>

			<tr>
				<td>${news.newsId}</td>
				<td>${news.title}</td>
				<td>${news.content}</td>
				<td><fmt:formatDate value="${news.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><img src="${news.img}"/></td>
				<td>
					<FORM METHOD="post"	ACTION="news/news2Update" style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="newsId" value="${news.newsId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="news/newsDelete" style="margin-bottom: 0px;">
						<input type="submit" value="刪除">
						<input type="hidden" name="newsId" value="${news.newsId}">
					</FORM>
				</td>
			</tr>
	</table>
<%-- 	<%@ include file="page2.file" %> --%>

    <div>
		<a href='back-end/news/news_index.jsp'>回最新消息首頁</a>
	</div>
 
</body>
</html>