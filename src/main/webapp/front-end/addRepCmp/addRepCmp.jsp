<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
//假設會員10000已登入
int i = 10000;
CustomerServiceImpl custSvc = new CustomerServiceImpl();
CustomerVO custVO = custSvc.getAll(i);
request.setAttribute("custVO", custVO);
%>
</head>
<body>
	<FORM METHOD="post"
		ACTION="http://localhost:8081/CGA101G4/addRepCmp/addRepCmp"
		name="form1">
		<table>
			<tr>
				<td>檢舉原因</td>
				<td><input type="text" name="reason"
					 maxlength="300" size="300"></td><td>${errorMsgs.reason}</td>
			</tr>
		</table>
		<input type="hidden" name="roomtypeId" value="${roomtypeId}">
		<input type="hidden" name="custId" value="${custVO.custId}">

		<input type="submit" value="檢舉廠商">${errorMsgs.repCmp}

	</FORM>
</body>
</html>