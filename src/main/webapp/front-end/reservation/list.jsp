<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房數管理</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<table>
		<tr>
			<td>房型圖片</td>
			<td>房型名稱</td>
			<td>房型數量</td>
			<td>入住人數</td>
			<td>價格</td>
			<td>狀態</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${requestScope.roomtypes}" var="Roomtype">
			<tr>
				<td>
					<c:forEach items="${Roomtype.roomImgs}" end="0" var="RoomImg">
						<img  height="128px" width="128px" src="${RoomImg.roomImg}">
					</c:forEach>
				</td>
				<td>${Roomtype.roomtypeName}</td>
				<td>${Roomtype.roomtypeAmount}</td>
				<td>${Roomtype.roomtypePeople}</td>
				<td>${Roomtype.roomtypePeople}</td>
				<td>${Roomtype.roomtypePrice}</td>
				<td><a href="reservation/getReservation?roomtypeId=${Roomtype.roomtypeId}&roomtypeAmount=${Roomtype.roomtypeAmount}&">預約表</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>