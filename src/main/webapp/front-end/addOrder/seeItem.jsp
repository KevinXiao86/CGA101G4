<%@page import="com.taiwan.service.roomtype.RoomtypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddOrder</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%--@ include file="/common/head.jsp"--%>
</head>
<%
Context ctx = new javax.naming.InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
Connection con = ds.getConnection();
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM Taiwan.ROOM_ITEM ");

ResultSetMetaData rsmd = rs.getMetaData();
int numberOfColumns = rsmd.getColumnCount();
%>

<table border="1">

	<tr>
		<%
		for (int i = 1; i <= numberOfColumns; i++) {
		%>
		<th><%=rsmd.getColumnName(i)%></th>
		<%
		}
		%>
	</tr>


	<tr>
		<c:forEach var="item" items="${list}">
			<td>${item.roomItemId}</td>
			<td>${item.roomId}</td>
			<td>${item.roomOrderId}</td>
			<td>${item.roomItemAmount}</td>
			<td>${item.roomItemEvaluateScore}</td>
			<td>${item.roomItemEvaluateMsg}</td>
			<td>${item.roomItemPrice}</td>
		</c:forEach>
	</tr>


</table>

<%
con.close();
%>
</body>


</html>