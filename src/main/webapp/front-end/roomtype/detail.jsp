<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型詳情</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<label for="roomtypeName">房型名稱:</label>
    <input type="text" name="roomtypeName" id="roomtypeName" value="${requestScope.roomtype.roomtypeName}"><br>

    <label for="roomtpyeAmount">房型數量:</label>
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
    <input type="text" name="roomtypeIntroduce" id="roomtypeIntroduce" value="${requestScope.roomtype.roomtypeIntroduce}"><br>
    
    
    <c:forEach var="RoomImg" items="${requestScope.roomtype.roomImgs}">
    	<img src="${RoomImg.roomImg}" height="128px" width="128px" class="uploadedImg">
    </c:forEach>
    <br>
    <a href="roomtype/getAllRoomtypes">返回房型列表</a>
</body>
</html>