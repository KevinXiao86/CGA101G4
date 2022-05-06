<%@page import="com.taiwan.beans.RoomImg"%>
<%@page import="com.taiwan.beans.Roomtype"%>
<%@page import="com.taiwan.service.roomtype.RoomtypeService"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.company.CompanyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>測試頁面</title>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
<!-- 		圖片顯示區及刪除 -->
	<form action="">
		<c:forEach items="${roomtype.roomImgs}" var="RoomImg">
			<img src="${RoomImg.roomImg}" height="128px" width="128px">
			<input type="checkbox" name="roomImgId" value="${RoomImg.roomImgId}" class="delete_checkbox">
		</c:forEach>
	</form>
</body>
</html>