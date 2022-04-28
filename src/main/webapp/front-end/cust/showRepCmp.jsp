<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<td>檢舉房型</td>
<td>檢舉原因</td>
<td>檢舉時間</td>
<td>狀態</td>
<td>結果</td>
</tr>
</thead>
<tbody>
<c:forEach var="repCmpVO" items="${list}">
<tr>
<td>${repCmpVO.roomId}</td>
<td>${repCmpVO.reason}</td>
<td>${repCmpVO.repCmpDate}</td>
<td>${repCmpVO.status}</td>
<td>${repCmpVO.result}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>