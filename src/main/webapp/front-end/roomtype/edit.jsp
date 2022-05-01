<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型修改頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<link rel="stylesheet" href="front-end/roomtype/css/add.css" />
<script src="front-end/roomtype/js/add.js"></script>

</head>
<body>
	<form action="roomtype/deleteRoomImg">
		<input type="hidden" name="cmpId" value="${requestScope.roomtype.cmpId}">
		<input type="hidden" name="roomtypeId" value="${requestScope.roomtype.roomtypeId}">
		
		<c:forEach var="RoomImg" items="${requestScope.roomtype.roomImgs}">
			<img src="${RoomImg.roomImg}" height="128px" width="128px" class="uploadedImg">
			<input type="checkbox" name="roomImgId" value="${RoomImg.roomImgId}" class="delete_checkbox">
		</c:forEach>
		<button id="deleteBtn">點擊刪除</button>
	</form>
	<hr>
	
	
	
	<form action="roomtype/editRoomtype" enctype="multipart/form-data" method="post">
		<input type="hidden" name="cmpId" value="${sessionScope.loginCompany.cmpId}">
		<input type="hidden" name="roomtypeId" value="${requestScope.roomtype.roomtypeId}">
		
		<label for="roomtypeName">房型名稱:</label>
    	<input type="text" name="roomtypeName" id="roomtypeName" value="${requestScope.roomtype.roomtypeName}"><br>

		<label for="roomtypeAmount">房型數量:</label>
    	<input type="text" name="roomtypeAmount" id="roomtypeAmount" value="${requestScope.roomtype.roomtypeAmount}"><br>
    	
    	<label for="roomtypePeople">入住人數:</label>
    	<input type="text" name="roomtypePeople" id="roomtypePeople" value="${requestScope.roomtype.roomtypePeople}"><br>

    	<label for="totalScope">總評分:</label>
    	<input type="text" name="totalScore" id="totalScore" value="${requestScope.roomtype.totalScore}"><br>

    	<label for="totalPeople">評分總人數:</label>
    	<input type="text" name="totalPeople" id="totalPeople" value="${requestScope.roomtype.totalPeople}"><br>

    	<label for="roomtypePrice">價格:</label>
    	<input type="text" name="roomtypePrice" id="roomtypePrice" value="${requestScope.roomtype.roomtypePrice}"><br>

    	<label for="roomtypeStatus">狀態:</label>
    	<input type="text" name="roomtypeStatus" id="roomtypeStatus" value="${requestScope.roomtype.roomtypeStatus}"><br>

    	<label for="roomtypeArea">平方公尺:</label>
    	<input type="text" name="roomtypeArea" id="roomtypeArea" value="${requestScope.roomtype.roomtypeArea}"><br>

    	<label for="roomtypeIntroduce">房型介紹</label>
		<textarea rows="6" cols="40" name="roomtypeIntroduce" id="roomtypeIntroduce">${requestScope.roomtype.roomtypeIntroduce}</textarea><br>
		<hr>
		
		<div class="container">
			<h2>上傳圖片</h2>
			<ul class="list">
				<li class="file">
					<!-- multiple 這個屬性可以實現文件選擇框的多選 -->
					<input type="file" name="file" id="file" multiple />
				</li>
			</ul>
		</div>	
		
		<button id="insertBtn">點擊修改</button>
	</form>
</body>
</html>