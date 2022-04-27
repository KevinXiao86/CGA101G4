<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>news_index</title>
</head>
<body>

	

	<h1>this is news index</h1>
	<form action="news/findAll" method="post">
		<input type="submit" value="搜尋全部">
	</form>
	
	<div>
		<a href='back-end/news/news_create.jsp'>新增</a>一筆最新消息
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
	
	<div>
	<FORM METHOD="post" ACTION="news/selectById" >
        <b>輸入最新消息編號來做查詢:</b>
        <input type="text" name="newsId">
        <input type="submit" value="送出">
    </FORM>
    </div>
    <div>
    <FORM METHOD="post" ACTION="news/selectByTitle" >
        <b>輸入最新消息標題來做查詢:</b>
        <input type="text" name="title">
        <input type="submit" value="送出">
    </FORM>
    </div>
    

    


</body>
</html>