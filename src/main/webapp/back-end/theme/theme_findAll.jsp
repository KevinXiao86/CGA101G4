<%@page import="com.taiwan.beans.Theme"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

List<Theme> list =(List<Theme>)request.getAttribute("list");
pageContext.setAttribute("list", list);

%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>所有熱門活動資料 ThemeFindAll</title>
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

	<h1>所有熱門活動</h1>

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
			<th>熱門活動編號</th>
			<th>熱門活動標題</th>
			<th>熱門活動介紹</th>
			<th>撰寫日期</th>
			<th>圖片</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file" %> 
		<c:forEach items="${list}" var="theme" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${theme.themeId}</td>
				<td>${theme.title}</td>
				<td>${theme.content}</td>
				<td><fmt:formatDate value="${theme.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><img src="${theme.img}"/></td>
				<td>
					<FORM METHOD="post"	ACTION="theme/theme2Update" style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="themeId" value="${theme.themeId}">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="theme/themeDelete" style="margin-bottom: 0px;">
						<input type="submit" value="刪除">
						<input type="hidden" name="themeId" value="${theme.themeId}">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file" %>
	
	<div>
		<a href='back-end/theme/theme_index.jsp'>回熱門活動首頁</a>
	</div>
 
</body>
</html>