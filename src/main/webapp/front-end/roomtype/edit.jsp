<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型修改頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<form action="roomtype/deleteRoomImg">
		<c:forEach var="RoomImg" items="${requestScope.roomtype.roomImgs}">
			<img src="${RoomImg.roomImg}" height="128px" width="128px" class="uploadedImg">
			<input type="checkbox" name="roomImgId" value="${RoomImg.roomImgId}" class="delete_checkbox">
		</c:forEach>
		<button id="deleteBtn">點擊刪除</button>
	</form>
</body>
</html>