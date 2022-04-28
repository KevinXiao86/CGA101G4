<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>faq_index</title>
</head>
<body>

	

	<h1>this is FAQ index</h1>
	<form action="faq/findAll" method="post">
		<input type="submit" value="搜尋全部">
	</form>
	
	<div>
		<a href='back-end/faq/faq_create.jsp'>新增</a>一筆FAQ
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
	<FORM METHOD="post" ACTION="faq/selectById" >
        <b>輸入FAQ編號來做查詢:</b>
        <input type="text" name="faqId">
        <input type="submit" value="送出">
    </FORM>
    </div>
<!--     <div> -->
<!--     <FORM METHOD="post" ACTION="faq/selectByTitle" > -->
<!--         <b>輸入FAQ標題來做查詢:</b> -->
<!--         <input type="text" name="title"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--     </div> -->
    

</body>
</html>