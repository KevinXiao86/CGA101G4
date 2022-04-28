<%@page
	import="com.taiwan.service.custPlatMail.impl.CustPlatMailServiceImpl"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.taiwan.beans.CustPlatMailVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
CustPlatMailServiceImpl service = new CustPlatMailServiceImpl();
List<CustPlatMailVO> list = service.getAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>流水號</th>
				<th>客戶Id</th>
				<th>管理員編號</th>
				<th>內容</th>
				<th>寄送時間</th>
				<th>誰
			</tr>
		</thead>
		<tbody>
			<c:forEach var="custPlatMail" items="<%=list%>">
				<tr>
					<td>${custPlatMail.custPlatId}</td>
					<td>${custPlatMail.custId}</td>
					<td>${custPlatMail.empId}</td>
					<td>${custPlatMail.msg}</td>
					<td>${custPlatMail.custPlatTime}</td>
					<td>${custPlatMail.who}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>