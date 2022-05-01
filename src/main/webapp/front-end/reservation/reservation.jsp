<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約表</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<form action="">
	
	</form>
	
	<table>
		<tr>
			<td>日期</td>
			<td>房型數量</td>
			<td>剩餘數量</td>
		</tr>

		<c:forEach items="${requestScope.reservations}" var="Reservations">
			<tr>
				<td>${Reservations.dateString}</td>
				<td>${Reservations.roomtypeAmount}</td>
				<td>${Reservations.roomtypeAmount - Reservations.reserveAmount}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>