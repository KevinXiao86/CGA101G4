<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>

	<h1>this is coupon index</h1>
	<form action="coupon/findAll" method="post">
		<input type="submit" value="搜尋全部">
	</form>
	
	


</body>
</html>