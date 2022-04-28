<%@page import="com.taiwan.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/seeItemByOrder"
		name="form1">
		<table>
			<jsp:useBean id="orderSvc" scope="page" 
				class="com.taiwan.service.impl.RoomOrderServiceImpl" /> 


			<tr>
				<td>訂單:(已會員10000作範例)</td>
				<td><select name="order">
						<c:forEach var="orderVO" items="${orderSvc.searchRoomOrderbyCustId(10000)}">
							<option value="${orderVO.roomOrderId }"
								>訂單:${orderVO.roomOrderId}</option>
						</c:forEach>
				</select></td>
				<td>${errorMsgs.order}</td>
			</tr>
			<br>

		</table>
		 <input type="submit" value="送出新增">
	</FORM>
</body>
</html>